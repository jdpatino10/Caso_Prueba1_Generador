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
import co.edu.uniandes.csw.caso12.entities.C2Entity;
import co.edu.uniandes.csw.caso12.entities.C4Entity;
import co.edu.uniandes.csw.caso12.dtos.detail.C2DetailDTO;
import co.edu.uniandes.csw.caso12.dtos.detail.C4DetailDTO;
import co.edu.uniandes.csw.caso12.resources.C2Resource;
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
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/*
 * Testing URI: c2s/
 */
@RunWith(Arquillian.class)
public class C2C4Test {

    private WebTarget target;
    private PodamFactory factory = new PodamFactoryImpl();
    private final String apiPath = Utils.apiPath;
    private final String username = Utils.username;
    private final String password = Utils.password;

    private final int Ok = Status.OK.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();

    private final static List<C4Entity> oraculo = new ArrayList<>();

    private final String c2Path = "c2s";
    private final String c4Path = "c4";

    private C2Entity fatherC2Entity;

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
                .addPackage(C2Resource.class.getPackage())
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
        List<C4Entity> records = em.createQuery("SELECT u FROM C4Entity u").getResultList();
        for (C4Entity record : records) {
            em.remove(record);
        }
        em.createQuery("delete from C2Entity").executeUpdate();
        oraculo.clear();
    }

   /**
     * Datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
            fatherC2Entity = factory.manufacturePojo(C2Entity.class);
            em.persist(fatherC2Entity);

            for (int i = 0; i < 3; i++) {
                C4Entity c4 = factory.manufacturePojo(C4Entity.class);
                em.persist(c4);
                if(i<2){                
                    c4.setC2(fatherC2Entity);
                }
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
                .path(c2Path)
                .path(fatherC2Entity.getId().toString())
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
        Response response = createWebTarget()
                .path("users")
                .path("login")
                .request()
                .post(Entity.entity(user, MediaType.APPLICATION_JSON));
        if (response.getStatus() == Ok) {
            return response.getCookies().get(JWT.cookieName);
        } else {
            return null;
        }
    }

    /**
     *Prueba para asociar un C4 existente a un C2
     *
     * @generated
     */
    @Test
    public void addC4Test() {
        Cookie cookieSessionId = login(username, password);

        C4DetailDTO c4 = new C4DetailDTO(oraculo.get(2));

        Response response = target.path(c4.getId().toString())
                .request().cookie(cookieSessionId)
                .post(Entity.entity(c4, MediaType.APPLICATION_JSON));

        C4DetailDTO c4Test = (C4DetailDTO) response.readEntity(C4DetailDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(c4.getId(), c4Test.getId());
    }

    /**
     * Prueba para obtener una colección de instancias de C4 asociadas a una instancia C2
     *
     * @generated
     */
    @Test
    public void listC4Test() throws IOException {
        Cookie cookieSessionId = login(username, password);

        Response response = target
                .request().cookie(cookieSessionId).get();

        String c4List = response.readEntity(String.class);
        List<C4DetailDTO> c4ListTest = new ObjectMapper().readValue(c4List, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(2, c4ListTest.size());
    }

    /**
     * Prueba para obtener una instancia de C4 asociada a una instancia C2
     *
     * @generated
     */
    @Test
    public void getC4Test() throws IOException {
        Cookie cookieSessionId = login(username, password);
        C4DetailDTO c4 = new C4DetailDTO(oraculo.get(0));

        C4DetailDTO c4Test = target.path(c4.getId().toString())
                .request().cookie(cookieSessionId).get(C4DetailDTO.class);

        Assert.assertEquals(c4.getId(), c4Test.getId());
        Assert.assertEquals(c4.getName(), c4Test.getName());
    }

    /**
     * Prueba para desasociar un C4 existente de un C2 existente
     *
     * @generated
     */
    @Test
    public void removeC4Test() {
        Cookie cookieSessionId = login(username, password);

        C4DetailDTO c4 = new C4DetailDTO(oraculo.get(0));

        Response response = target.path(c4.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
}
