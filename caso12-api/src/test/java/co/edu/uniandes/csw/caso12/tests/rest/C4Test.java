/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.caso12.tests.rest;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.caso12.entities.C4Entity;
import co.edu.uniandes.csw.caso12.dtos.detail.C4DetailDTO;
import co.edu.uniandes.csw.caso12.resources.C4Resource;
import co.edu.uniandes.csw.caso12.tests.Utils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.UserTransaction;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/*
 * Testing URI: c4s/
 */
@RunWith(Arquillian.class)
public class C4Test {

    private WebTarget target;
    private final String apiPath = Utils.apiPath;
    private final String username = Utils.username;
    private final String password = Utils.password;
    PodamFactory factory = new PodamFactoryImpl();

    private final int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();

    private final static List<C4Entity> oraculo = new ArrayList<>();

    private final String c4Path = "c4s";


    @ArquillianResource
    private URL deploymentURL;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                // Se agrega las dependencias
                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
                        .importRuntimeDependencies().resolve()
                        .withTransitivity().asFile())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(C4Resource.class.getPackage())
                // El archivo que contiene la configuracion a la base de datos.
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias.
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo shiro.ini es necesario para injeccion de dependencias
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/shiro.ini"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
    }

    private WebTarget createWebTarget() {
        return ClientBuilder.newClient().target(deploymentURL.toString()).path(apiPath);
    }

    @PersistenceContext(unitName = "Caso12PU")
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private void clearData() {
        em.createQuery("delete from C4Entity").executeUpdate();
        oraculo.clear();
    }

   /**
     * Datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    public void insertData() {
        for (int i = 0; i < 3; i++) {            
            C4Entity c4 = factory.manufacturePojo(C4Entity.class);
            c4.setId(i + 1L);
            em.persist(c4);
            oraculo.add(c4);
        }
    }

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void setUpTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        target = createWebTarget()
                .path(c4Path);
    }

    /**
     * Login para poder consultar los diferentes servicios
     *
     * @param username Nombre de usuario
     * @param password Clave del usuario
     * @return Cookie con información de la sesión del usuario
     * @generated
     */
    public Cookie login(String username, String password) {
        UserDTO user = new UserDTO();
        user.setUserName(username);
        user.setPassword(password);
        user.setRememberMe(true);
        Response response = createWebTarget().path("users").path("login").request()
                .post(Entity.entity(user, MediaType.APPLICATION_JSON));
        if (response.getStatus() == Ok) {
            return response.getCookies().get(JWT.cookieName);
        } else {
            return null;
        }
    }

    /**
     * Prueba para crear un C4
     *
     * @generated
     */
    @Test
    public void createC4Test() throws IOException {
        C4DetailDTO c4 = factory.manufacturePojo(C4DetailDTO.class);
        Cookie cookieSessionId = login(username, password);

        Response response = target
            .request().cookie(cookieSessionId)
            .post(Entity.entity(c4, MediaType.APPLICATION_JSON));

        C4DetailDTO  c4Test = (C4DetailDTO) response.readEntity(C4DetailDTO.class);

        Assert.assertEquals(Created, response.getStatus());

        Assert.assertEquals(c4.getName(), c4Test.getName());

        C4Entity entity = em.find(C4Entity.class, c4Test.getId());
        Assert.assertNotNull(entity);
    }

    /**
     * Prueba para consultar un C4
     *
     * @generated
     */
    @Test
    public void getC4ByIdTest() {
        Cookie cookieSessionId = login(username, password);

        C4DetailDTO c4Test = target
            .path(oraculo.get(0).getId().toString())
            .request().cookie(cookieSessionId).get(C4DetailDTO.class);
        
        Assert.assertEquals(c4Test.getId(), oraculo.get(0).getId());
        Assert.assertEquals(c4Test.getName(), oraculo.get(0).getName());
    }

    /**
     * Prueba para consultar la lista de C4s
     *
     * @generated
     */
    @Test
    public void listC4Test() throws IOException {
        Cookie cookieSessionId = login(username, password);

        Response response = target
            .request().cookie(cookieSessionId).get();

        String listC4 = response.readEntity(String.class);
        List<C4DetailDTO> listC4Test = new ObjectMapper().readValue(listC4, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(oraculo.size(), listC4Test.size());
    }

    /**
     * Prueba para actualizar un C4
     *
     * @generated
     */
    @Test
    public void updateC4Test() throws IOException {
        Cookie cookieSessionId = login(username, password);
        C4DetailDTO c4 = new C4DetailDTO(oraculo.get(0));

        C4DetailDTO c4Changed = factory.manufacturePojo(C4DetailDTO.class);

        c4.setName(c4Changed.getName());

        Response response = target
            .path(c4.getId().toString())
            .request().cookie(cookieSessionId)
            .put(Entity.entity(c4, MediaType.APPLICATION_JSON));

        C4DetailDTO c4Test = (C4DetailDTO) response.readEntity(C4DetailDTO.class);

        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(c4.getName(), c4Test.getName());
    }

    /**
     * Prueba para eliminar un C4
     *
     * @generated
     */
    @Test
    public void deleteC4Test() {
        Cookie cookieSessionId = login(username, password);
        C4DetailDTO c4 = new C4DetailDTO(oraculo.get(0));
        Response response = target
            .path(c4.getId().toString())
            .request().cookie(cookieSessionId).delete();

        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
}
