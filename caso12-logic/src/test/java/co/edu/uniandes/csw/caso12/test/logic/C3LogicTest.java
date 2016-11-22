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
package co.edu.uniandes.csw.caso12.test.logic;

import co.edu.uniandes.csw.caso12.ejbs.C3Logic;
import co.edu.uniandes.csw.caso12.api.IC3Logic;
import co.edu.uniandes.csw.caso12.entities.C3Entity;
import co.edu.uniandes.csw.caso12.persistence.C3Persistence;
import co.edu.uniandes.csw.caso12.entities.C1Entity;
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
public class C3LogicTest {

    /**
     * @generated
     */

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IC3Logic c3Logic;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    private UserTransaction utx;

    /**
     * @generated
     */
    private List<C3Entity> data = new ArrayList<C3Entity>();

    /**
     * @generated
     */
    private List<C1Entity> c1Data = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(C3Entity.class.getPackage())
                .addPackage(C3Logic.class.getPackage())
                .addPackage(IC3Logic.class.getPackage())
                .addPackage(C3Persistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void configTest() {
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
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from C3Entity").executeUpdate();
        em.createQuery("delete from C1Entity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
            for (int i = 0; i < 3; i++) {
                C1Entity c1 = factory.manufacturePojo(C1Entity.class);
                em.persist(c1);
                c1Data.add(c1);
            }
        for (int i = 0; i < 3; i++) {
            C3Entity entity = factory.manufacturePojo(C3Entity.class);
                entity.setC1(c1Data.get(0));

            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un C3
     *
     * @generated
     */
    @Test
    public void createC3Test() {
        C3Entity newEntity = factory.manufacturePojo(C3Entity.class);
        C3Entity result = c3Logic.createC3(newEntity);
        Assert.assertNotNull(result);
        C3Entity entity = em.find(C3Entity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de C3s
     *
     * @generated
     */
    @Test
    public void getC3sTest() {
        List<C3Entity> list = c3Logic.getC3s();
        Assert.assertEquals(data.size(), list.size());
        for (C3Entity entity : list) {
            boolean found = false;
            for (C3Entity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un C3
     *
     * @generated
     */
    @Test
    public void getC3Test() {
        C3Entity entity = data.get(0);
        C3Entity resultEntity = c3Logic.getC3(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * Prueba para eliminar un C3
     *
     * @generated
     */
    @Test
    public void deleteC3Test() {
        C3Entity entity = data.get(0);
        c3Logic.deleteC3(entity.getId());
        C3Entity deleted = em.find(C3Entity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un C3
     *
     * @generated
     */
    @Test
    public void updateC3Test() {
        C3Entity entity = data.get(0);
        C3Entity pojoEntity = factory.manufacturePojo(C3Entity.class);

        pojoEntity.setId(entity.getId());

        c3Logic.updateC3(pojoEntity);

        C3Entity resp = em.find(C3Entity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }
}

