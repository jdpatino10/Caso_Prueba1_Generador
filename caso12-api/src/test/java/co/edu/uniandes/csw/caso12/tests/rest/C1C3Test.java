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
import co.edu.uniandes.csw.caso12.entities.C1Entity;
import co.edu.uniandes.csw.caso12.entities.C3Entity;
import co.edu.uniandes.csw.caso12.dtos.detail.C1DetailDTO;
import co.edu.uniandes.csw.caso12.dtos.detail.C3DetailDTO;
import co.edu.uniandes.csw.caso12.resources.C1Resource;
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
 * Testing URI: c1s/
 */
@RunWith(Arquillian.class)
public class C1C3Test {

    private WebTarget target;
    private PodamFactory factory = new PodamFactoryImpl();
    private final String apiPath = Utils.apiPath;
    private final String username = Utils.username;
    private final String password = Utils.password;

    private final int Ok = Status.OK.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();

    private final static List<C3Entity> oraculo = new ArrayList<>();

    private final String c1Path = "c1s";
    private final String c3Path = "c3";

    private C1Entity fatherC1Entity;

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
                .addPackage(C1Resource.class.getPackage())
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
        List<C3Entity> records = em.createQuery("SELECT u FROM C3Entity u").getResultList();
        for (C3Entity record : records) {
            em.remove(record);
        }
        em.createQuery("delete from C1Entity").executeUpdate();
        oraculo.clear();
    }

   /**
     * Datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
            fatherC1Entity = factory.manufacturePojo(C1Entity.class);
            em.persist(fatherC1Entity);

            for (int i = 0; i < 3; i++) {
                C3Entity c3 = factory.manufacturePojo(C3Entity.class);
                em.persist(c3);
                if(i<2){                
                    c3.setC1(fatherC1Entity);
                }
                oraculo.add(c3);
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
                .path(c1Path)
                .path(fatherC1Entity.getId().toString())
                .path(c3Path);
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
     *Prueba para asociar un C3 existente a un C1
     *
     * @generated
     */
    @Test
    public void addC3Test() {
        Cookie cookieSessionId = login(username, password);

        C3DetailDTO c3 = new C3DetailDTO(oraculo.get(2));

        Response response = target.path(c3.getId().toString())
                .request().cookie(cookieSessionId)
                .post(Entity.entity(c3, MediaType.APPLICATION_JSON));

        C3DetailDTO c3Test = (C3DetailDTO) response.readEntity(C3DetailDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(c3.getId(), c3Test.getId());
    }

    /**
     * Prueba para obtener una colección de instancias de C3 asociadas a una instancia C1
     *
     * @generated
     */
    @Test
    public void listC3Test() throws IOException {
        Cookie cookieSessionId = login(username, password);

        Response response = target
                .request().cookie(cookieSessionId).get();

        String c3List = response.readEntity(String.class);
        List<C3DetailDTO> c3ListTest = new ObjectMapper().readValue(c3List, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(2, c3ListTest.size());
    }

    /**
     * Prueba para obtener una instancia de C3 asociada a una instancia C1
     *
     * @generated
     */
    @Test
    public void getC3Test() throws IOException {
        Cookie cookieSessionId = login(username, password);
        C3DetailDTO c3 = new C3DetailDTO(oraculo.get(0));

        C3DetailDTO c3Test = target.path(c3.getId().toString())
                .request().cookie(cookieSessionId).get(C3DetailDTO.class);

        Assert.assertEquals(c3.getId(), c3Test.getId());
        Assert.assertEquals(c3.getName(), c3Test.getName());
    }

    /**
     * Prueba para desasociar un C3 existente de un C1 existente
     *
     * @generated
     */
    @Test
    public void removeC3Test() {
        Cookie cookieSessionId = login(username, password);

        C3DetailDTO c3 = new C3DetailDTO(oraculo.get(0));

        Response response = target.path(c3.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
}
