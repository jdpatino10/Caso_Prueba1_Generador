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
import co.edu.uniandes.csw.caso12.entities.C3Entity;
import co.edu.uniandes.csw.caso12.persistence.C3Persistence;
import java.util.ArrayList;
import java.util.List;

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
public class C3PersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(C3Entity.class.getPackage())
                .addPackage(C3Persistence.class.getPackage())
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
    private C3Persistence c3Persistence;

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
        em.createQuery("delete from C3Entity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<C3Entity> data = new ArrayList<C3Entity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            C3Entity entity = factory.manufacturePojo(C3Entity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un C3.
     *
     * @generated
     */
    @Test
    public void createC3Test() {
        PodamFactory factory = new PodamFactoryImpl();
        C3Entity newEntity = factory.manufacturePojo(C3Entity.class);
        C3Entity result = c3Persistence.create(newEntity);

        Assert.assertNotNull(result);

        C3Entity entity = em.find(C3Entity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de C3s.
     *
     * @generated
     */
    @Test
    public void getC3sTest() {
        List<C3Entity> list = c3Persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (C3Entity ent : list) {
            boolean found = false;
            for (C3Entity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un C3.
     *
     * @generated
     */
    @Test
    public void getC3Test() {
        C3Entity entity = data.get(0);
        C3Entity newEntity = c3Persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Prueba para eliminar un C3.
     *
     * @generated
     */
    @Test
    public void deleteC3Test() {
        C3Entity entity = data.get(0);
        c3Persistence.delete(entity.getId());
        C3Entity deleted = em.find(C3Entity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un C3.
     *
     * @generated
     */
    @Test
    public void updateC3Test() {
        C3Entity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        C3Entity newEntity = factory.manufacturePojo(C3Entity.class);

        newEntity.setId(entity.getId());

        c3Persistence.update(newEntity);

        C3Entity resp = em.find(C3Entity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}
