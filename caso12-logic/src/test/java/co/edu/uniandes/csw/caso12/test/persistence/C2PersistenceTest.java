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
import co.edu.uniandes.csw.caso12.entities.C2Entity;
import co.edu.uniandes.csw.caso12.persistence.C2Persistence;
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
public class C2PersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(C2Entity.class.getPackage())
                .addPackage(C2Persistence.class.getPackage())
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
    private C2Persistence c2Persistence;

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
        em.createQuery("delete from C2Entity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<C2Entity> data = new ArrayList<C2Entity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            C2Entity entity = factory.manufacturePojo(C2Entity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un C2.
     *
     * @generated
     */
    @Test
    public void createC2Test() {
        PodamFactory factory = new PodamFactoryImpl();
        C2Entity newEntity = factory.manufacturePojo(C2Entity.class);
        C2Entity result = c2Persistence.create(newEntity);

        Assert.assertNotNull(result);

        C2Entity entity = em.find(C2Entity.class, result.getId());

        Assert.assertEquals(newEntity.getTest(), entity.getTest());
        Assert.assertEquals(newEntity.getNumber(), entity.getNumber());
    }

    /**
     * Prueba para consultar la lista de C2s.
     *
     * @generated
     */
    @Test
    public void getC2sTest() {
        List<C2Entity> list = c2Persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (C2Entity ent : list) {
            boolean found = false;
            for (C2Entity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un C2.
     *
     * @generated
     */
    @Test
    public void getC2Test() {
        C2Entity entity = data.get(0);
        C2Entity newEntity = c2Persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getTest(), newEntity.getTest());
        Assert.assertEquals(entity.getNumber(), newEntity.getNumber());
    }

    /**
     * Prueba para eliminar un C2.
     *
     * @generated
     */
    @Test
    public void deleteC2Test() {
        C2Entity entity = data.get(0);
        c2Persistence.delete(entity.getId());
        C2Entity deleted = em.find(C2Entity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un C2.
     *
     * @generated
     */
    @Test
    public void updateC2Test() {
        C2Entity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        C2Entity newEntity = factory.manufacturePojo(C2Entity.class);

        newEntity.setId(entity.getId());

        c2Persistence.update(newEntity);

        C2Entity resp = em.find(C2Entity.class, entity.getId());

        Assert.assertEquals(newEntity.getTest(), resp.getTest());
        Assert.assertEquals(newEntity.getNumber(), resp.getNumber());
    }
}
