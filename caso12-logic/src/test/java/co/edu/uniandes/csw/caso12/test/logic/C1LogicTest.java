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

import co.edu.uniandes.csw.caso12.ejbs.C1Logic;
import co.edu.uniandes.csw.caso12.api.IC1Logic;
import co.edu.uniandes.csw.caso12.entities.C1Entity;
import co.edu.uniandes.csw.caso12.persistence.C1Persistence;
import co.edu.uniandes.csw.caso12.entities.C3Entity;
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
public class C1LogicTest {

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
    private IC1Logic c1Logic;

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
    private List<C1Entity> data = new ArrayList<C1Entity>();

    /**
     * @generated
     */
    private List<C3Entity> c3Data = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(C1Entity.class.getPackage())
                .addPackage(C1Logic.class.getPackage())
                .addPackage(IC1Logic.class.getPackage())
                .addPackage(C1Persistence.class.getPackage())
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
                C3Entity c3 = factory.manufacturePojo(C3Entity.class);
                em.persist(c3);
                c3Data.add(c3);
            }
        for (int i = 0; i < 3; i++) {
            C1Entity entity = factory.manufacturePojo(C1Entity.class);

            em.persist(entity);
            data.add(entity);

            if (i == 0) {
                c3Data.get(i).setC1(entity);
            }
        }
    }
    /**
     * Prueba para crear un C1
     *
     * @generated
     */
    @Test
    public void createC1Test() {
        C1Entity newEntity = factory.manufacturePojo(C1Entity.class);
        C1Entity result = c1Logic.createC1(newEntity);
        Assert.assertNotNull(result);
        C1Entity entity = em.find(C1Entity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getNumber(), entity.getNumber());
        Assert.assertEquals(newEntity.getDate(), entity.getDate());
    }

    /**
     * Prueba para consultar la lista de C1s
     *
     * @generated
     */
    @Test
    public void getC1sTest() {
        List<C1Entity> list = c1Logic.getC1s();
        Assert.assertEquals(data.size(), list.size());
        for (C1Entity entity : list) {
            boolean found = false;
            for (C1Entity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un C1
     *
     * @generated
     */
    @Test
    public void getC1Test() {
        C1Entity entity = data.get(0);
        C1Entity resultEntity = c1Logic.getC1(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getNumber(), resultEntity.getNumber());
        Assert.assertEquals(entity.getDate(), resultEntity.getDate());
    }

    /**
     * Prueba para eliminar un C1
     *
     * @generated
     */
    @Test
    public void deleteC1Test() {
        C1Entity entity = data.get(0);
        c1Logic.removeC3(entity.getId(), c3Data.get(0).getId());
        c1Logic.deleteC1(entity.getId());
        C1Entity deleted = em.find(C1Entity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un C1
     *
     * @generated
     */
    @Test
    public void updateC1Test() {
        C1Entity entity = data.get(0);
        C1Entity pojoEntity = factory.manufacturePojo(C1Entity.class);

        pojoEntity.setId(entity.getId());

        c1Logic.updateC1(pojoEntity);

        C1Entity resp = em.find(C1Entity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getNumber(), resp.getNumber());
        Assert.assertEquals(pojoEntity.getDate(), resp.getDate());
    }

    /**
     * Prueba para obtener una instancia de C3 asociada a una instancia C1
     *
     * @generated
     */
    @Test
    public void getC3Test() {
        C1Entity entity = data.get(0);
        C3Entity c3Entity = c3Data.get(0);
        C3Entity response = c1Logic.getC3(entity.getId(), c3Entity.getId());

        Assert.assertEquals(c3Entity.getId(), response.getId());
        Assert.assertEquals(c3Entity.getName(), response.getName());
    }

    /**
     * Prueba para obtener una colección de instancias de C3 asociadas a una instancia C1
     *
     * @generated
     */
    @Test
    public void listC3Test() {
        List<C3Entity> list = c1Logic.listC3(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }

    /**
     *Prueba para asociar un C3 existente a un C1
     *
     * @generated
     */
    @Test
    public void addC3Test() {
        C1Entity entity = data.get(0);
        C3Entity c3Entity = c3Data.get(1);
        C3Entity response = c1Logic.addC3(entity.getId(), c3Entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(c3Entity.getId(), response.getId());
    }

    /**
     * Prueba para remplazar las instancias de C3 asociadas a una instancia de C1
     *
     * @generated
     */
    @Test
    public void replaceC3Test() {
        C1Entity entity = data.get(0);
        List<C3Entity> list = c3Data.subList(1, 3);
        c1Logic.replaceC3(entity.getId(), list);

        entity = c1Logic.getC1(entity.getId());
        Assert.assertFalse(entity.getC3().contains(c3Data.get(0)));
        Assert.assertTrue(entity.getC3().contains(c3Data.get(1)));
        Assert.assertTrue(entity.getC3().contains(c3Data.get(2)));
    }

    /**
     * Prueba para desasociar un C3 existente de un C1 existente
     *
     * @generated
     */
    @Test
    public void removeC3Test() {
        c1Logic.removeC3(data.get(0).getId(), c3Data.get(0).getId());
        C3Entity response = c1Logic.getC3(data.get(0).getId(), c3Data.get(0).getId());
        Assert.assertNull(response);
    }
}

