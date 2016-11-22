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
package co.edu.uniandes.csw.caso12.ejbs;

import co.edu.uniandes.csw.caso12.api.IC1Logic;
import co.edu.uniandes.csw.caso12.entities.C1Entity;
import co.edu.uniandes.csw.caso12.persistence.C1Persistence;
import co.edu.uniandes.csw.caso12.entities.C3Entity;
import co.edu.uniandes.csw.caso12.api.IC3Logic;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 * @generated
 */
@Stateless
public class C1Logic implements IC1Logic {

    @Inject private C1Persistence persistence;


    @Inject private IC3Logic c3Logic;

    /**
     * Obtiene el número de registros de C1.
     *
     * @return Número de registros de C1.
     * @generated
     */
    public int countC1s() {
        return persistence.count();
    }

    /**
     * Obtiene la lista de los registros de C1.
     *
     * @return Colección de objetos de C1Entity.
     * @generated
     */
    @Override
    public List<C1Entity> getC1s() {
        return persistence.findAll();
    }

    /**
     * Obtiene la lista de los registros de C1 indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @return Colección de objetos de C1Entity.
     * @generated
     */
    @Override
    public List<C1Entity> getC1s(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    /**
     * Obtiene los datos de una instancia de C1 a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de C1Entity con los datos del C1 consultado.
     * @generated
     */
    public C1Entity getC1(Long id) {
        return persistence.find(id);
    }

    /**
     * Se encarga de crear un C1 en la base de datos.
     *
     * @param entity Objeto de C1Entity con los datos nuevos
     * @return Objeto de C1Entity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public C1Entity createC1(C1Entity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de C1.
     *
     * @param entity Instancia de C1Entity con los nuevos datos.
     * @return Instancia de C1Entity con los datos actualizados.
     * @generated
     */
    @Override
    public C1Entity updateC1(C1Entity entity) {
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de C1 de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @Override
    public void deleteC1(Long id) {
        persistence.delete(id);
    }
  

    /**
     * Obtiene una colección de instancias de C3Entity asociadas a una
     * instancia de C1
     *
     * @param c1Id Identificador de la instancia de C1
     * @return Colección de instancias de C3Entity asociadas a la instancia de C1
     * @generated
     */
    @Override
    public List<C3Entity> listC3(Long c1Id) {
        return getC1(c1Id).getC3();
    }

    /**
     * Obtiene una instancia de C3Entity asociada a una instancia de C1
     *
     * @param c1Id Identificador de la instancia de C1
     * @param c3Id Identificador de la instancia de C3
     * @generated
     */
    @Override
    public C3Entity getC3(Long c1Id, Long c3Id) {
        List<C3Entity> list = getC1(c1Id).getC3();
        C3Entity c3Entity = new C3Entity();
        c3Entity.setId(c3Id);
        int index = list.indexOf(c3Entity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia un C3 existente a un C1
     *
     * @param c1Id Identificador de la instancia de C1
     * @param c3Id Identificador de la instancia de C3
     * @return Instancia de C3Entity que fue asociada a C1
     * @generated
     */
    @Override
    public C3Entity addC3(Long c1Id, Long c3Id) {
        C1Entity c1Entity = getC1(c1Id);
        C3Entity c3Entity = c3Logic.getC3(c3Id);
        c3Entity.setC1(c1Entity);
        return c3Entity;
    }

    /**
     * Remplaza las instancias de C3 asociadas a una instancia de C1
     *
     * @param c1Id Identificador de la instancia de C1
     * @param list Colección de instancias de C3Entity a asociar a instancia de C1
     * @return Nueva colección de C3Entity asociada a la instancia de C1
     * @generated
     */
    @Override
    public List<C3Entity> replaceC3(Long c1Id, List<C3Entity> list) {
        C1Entity c1Entity = getC1(c1Id);
        List<C3Entity> c3List = c3Logic.getC3s();
        for (C3Entity c3 : c3List) {
            if (list.contains(c3)) {
                c3.setC1(c1Entity);
            } else {
                if (c3.getC1() != null && c3.getC1().equals(c1Entity)) {
                    c3.setC1(null);
                }
            }
        }
        c1Entity.setC3(list);
        return c1Entity.getC3();
    }

    /**
     * Desasocia un C3 existente de un C1 existente
     *
     * @param c1Id Identificador de la instancia de C1
     * @param c3Id Identificador de la instancia de C3
     * @generated
     */
    @Override
    public void removeC3(Long c1Id, Long c3Id) {
        C3Entity entity = c3Logic.getC3(c3Id);
        entity.setC1(null);
    }
}
