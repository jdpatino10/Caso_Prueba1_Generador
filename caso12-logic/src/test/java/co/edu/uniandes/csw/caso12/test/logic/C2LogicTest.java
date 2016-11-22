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

import co.edu.uniandes.csw.caso12.ejbs.C2Logic;
import co.edu.uniandes.csw.caso12.api.IC2Logic;
import co.edu.uniandes.csw.caso12.entities.C2Entity;
import co.edu.uniandes.csw.caso12.persistence.C2Persistence;
import co.edu.uniandes.csw.caso12.entities.C4Entity;
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
public class C2LogicTest {

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
    private IC2Logic c2Logic;

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
    private List<C2Entity> data = new ArrayList<C2Entity>();

    /**
     * @generated
     */
    private List<C4Entity> c4Data = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(C2Entity.class.getPackage())
                .addPackage(C2Logic.class.getPackage())
                .addPackage(IC2Logic.class.getPackage())
                .addPackage(C2Persistence.class.getPackage())
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
        em.createQuery("delete from C4Entity").executeUpdate();
        em.createQuery("delete from C2Entity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
            for (int i = 0; i < 3; i++) {
                C4Entity c4 = factory.manufacturePojo(C4Entity.class);
                em.persist(c4);
                c4Data.add(c4);
            }
        for (int i = 0; i < 3; i++) {
            C2Entity entity = factory.manufacturePojo(C2Entity.class);

            em.persist(entity);
            data.add(entity);

            if (i == 0) {
                c4Data.get(i).setC2(entity);
            }
        }
    }
    /**
     * Prueba para crear un C2
     *
     * @generated
     */
    @Test
    public void createC2Test() {
        C2Entity newEntity = factory.manufacturePojo(C2Entity.class);
        C2Entity result = c2Logic.createC2(newEntity);
        Assert.assertNotNull(result);
        C2Entity entity = em.find(C2Entity.class, result.getId());
        Assert.assertEquals(newEntity.getTest(), entity.getTest());
        Assert.assertEquals(newEntity.getNumber(), entity.getNumber());
    }

    /**
     * Prueba para consultar la lista de C2s
     *
     * @generated
     */
    @Test
    public void getC2sTest() {
        List<C2Entity> list = c2Logic.getC2s();
        Assert.assertEquals(data.size(), list.size());
        for (C2Entity entity : list) {
            boolean found = false;
            for (C2Entity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un C2
     *
     * @generated
     */
    @Test
    public void getC2Test() {
        C2Entity entity = data.get(0);
        C2Entity resultEntity = c2Logic.getC2(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getTest(), resultEntity.getTest());
        Assert.assertEquals(entity.getNumber(), resultEntity.getNumber());
    }

    /**
     * Prueba para eliminar un C2
     *
     * @generated
     */
    @Test
    public void deleteC2Test() {
        C2Entity entity = data.get(0);
        c2Logic.removeC4(entity.getId(), c4Data.get(0).getId());
        c2Logic.deleteC2(entity.getId());
        C2Entity deleted = em.find(C2Entity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un C2
     *
     * @generated
     */
    @Test
    public void updateC2Test() {
        C2Entity entity = data.get(0);
        C2Entity pojoEntity = factory.manufacturePojo(C2Entity.class);

        pojoEntity.setId(entity.getId());

        c2Logic.updateC2(pojoEntity);

        C2Entity resp = em.find(C2Entity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getTest(), resp.getTest());
        Assert.assertEquals(pojoEntity.getNumber(), resp.getNumber());
    }

    /**
     * Prueba para obtener una instancia de C4 asociada a una instancia C2
     *
     * @generated
     */
    @Test
    public void getC4Test() {
        C2Entity entity = data.get(0);
        C4Entity c4Entity = c4Data.get(0);
        C4Entity response = c2Logic.getC4(entity.getId(), c4Entity.getId());

        Assert.assertEquals(c4Entity.getId(), response.getId());
        Assert.assertEquals(c4Entity.getName(), response.getName());
    }

    /**
     * Prueba para obtener una colección de instancias de C4 asociadas a una instancia C2
     *
     * @generated
     */
    @Test
    public void listC4Test() {
        List<C4Entity> list = c2Logic.listC4(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }

    /**
     *Prueba para asociar un C4 existente a un C2
     *
     * @generated
     */
    @Test
    public void addC4Test() {
        C2Entity entity = data.get(0);
        C4Entity c4Entity = c4Data.get(1);
        C4Entity response = c2Logic.addC4(entity.getId(), c4Entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(c4Entity.getId(), response.getId());
    }

    /**
     * Prueba para remplazar las instancias de C4 asociadas a una instancia de C2
     *
     * @generated
     */
    @Test
    public void replaceC4Test() {
        C2Entity entity = data.get(0);
        List<C4Entity> list = c4Data.subList(1, 3);
        c2Logic.replaceC4(entity.getId(), list);

        entity = c2Logic.getC2(entity.getId());
        Assert.assertFalse(entity.getC4().contains(c4Data.get(0)));
        Assert.assertTrue(entity.getC4().contains(c4Data.get(1)));
        Assert.assertTrue(entity.getC4().contains(c4Data.get(2)));
    }

    /**
     * Prueba para desasociar un C4 existente de un C2 existente
     *
     * @generated
     */
    @Test
    public void removeC4Test() {
        c2Logic.removeC4(data.get(0).getId(), c4Data.get(0).getId());
        C4Entity response = c2Logic.getC4(data.get(0).getId(), c4Data.get(0).getId());
        Assert.assertNull(response);
    }
}

