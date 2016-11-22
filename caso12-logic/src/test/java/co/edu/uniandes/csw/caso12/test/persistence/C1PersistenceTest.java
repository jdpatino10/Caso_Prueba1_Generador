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
package co.edu.uniandes.csw.caso12.test.persistence;
import co.edu.uniandes.csw.caso12.entities.C1Entity;
import co.edu.uniandes.csw.caso12.persistence.C1Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class C1PersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(C1Entity.class.getPackage())
                .addPackage(C1Persistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */

    /**
     * @generated
     */
    @Inject
    private C1Persistence c1Persistence;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    UserTransaction utx;

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
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
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from C1Entity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<C1Entity> data = new ArrayList<C1Entity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            C1Entity entity = factory.manufacturePojo(C1Entity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un C1.
     *
     * @generated
     */
    @Test
    public void createC1Test() {
        PodamFactory factory = new PodamFactoryImpl();
        C1Entity newEntity = factory.manufacturePojo(C1Entity.class);
        C1Entity result = c1Persistence.create(newEntity);

        Assert.assertNotNull(result);

        C1Entity entity = em.find(C1Entity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getNumber(), entity.getNumber());
    }

    /**
     * Prueba para consultar la lista de C1s.
     *
     * @generated
     */
    @Test
    public void getC1sTest() {
        List<C1Entity> list = c1Persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (C1Entity ent : list) {
            boolean found = false;
            for (C1Entity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un C1.
     *
     * @generated
     */
    @Test
    public void getC1Test() {
        C1Entity entity = data.get(0);
        C1Entity newEntity = c1Persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getNumber(), newEntity.getNumber());
        Assert.assertEquals(entity.getDate(), newEntity.getDate());
    }

    /**
     * Prueba para eliminar un C1.
     *
     * @generated
     */
    @Test
    public void deleteC1Test() {
        C1Entity entity = data.get(0);
        c1Persistence.delete(entity.getId());
        C1Entity deleted = em.find(C1Entity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un C1.
     *
     * @generated
     */
    @Test
    public void updateC1Test() {
        C1Entity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        C1Entity newEntity = factory.manufacturePojo(C1Entity.class);

        newEntity.setId(entity.getId());

        c1Persistence.update(newEntity);

        C1Entity resp = em.find(C1Entity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getNumber(), resp.getNumber());
    }
}
