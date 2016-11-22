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

import co.edu.uniandes.csw.caso12.api.IC2Logic;
import co.edu.uniandes.csw.caso12.entities.C2Entity;
import co.edu.uniandes.csw.caso12.persistence.C2Persistence;
import co.edu.uniandes.csw.caso12.entities.C4Entity;
import co.edu.uniandes.csw.caso12.api.IC4Logic;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 * @generated
 */
@Stateless
public class C2Logic implements IC2Logic {

    @Inject private C2Persistence persistence;


    @Inject private IC4Logic c4Logic;

    /**
     * Obtiene el número de registros de C2.
     *
     * @return Número de registros de C2.
     * @generated
     */
    public int countC2s() {
        return persistence.count();
    }

    /**
     * Obtiene la lista de los registros de C2.
     *
     * @return Colección de objetos de C2Entity.
     * @generated
     */
    @Override
    public List<C2Entity> getC2s() {
        return persistence.findAll();
    }

    /**
     * Obtiene la lista de los registros de C2 indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @return Colección de objetos de C2Entity.
     * @generated
     */
    @Override
    public List<C2Entity> getC2s(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    /**
     * Obtiene los datos de una instancia de C2 a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de C2Entity con los datos del C2 consultado.
     * @generated
     */
    public C2Entity getC2(Long id) {
        return persistence.find(id);
    }

    /**
     * Se encarga de crear un C2 en la base de datos.
     *
     * @param entity Objeto de C2Entity con los datos nuevos
     * @return Objeto de C2Entity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public C2Entity createC2(C2Entity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de C2.
     *
     * @param entity Instancia de C2Entity con los nuevos datos.
     * @return Instancia de C2Entity con los datos actualizados.
     * @generated
     */
    @Override
    public C2Entity updateC2(C2Entity entity) {
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de C2 de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @Override
    public void deleteC2(Long id) {
        persistence.delete(id);
    }
  

    /**
     * Obtiene una colección de instancias de C4Entity asociadas a una
     * instancia de C2
     *
     * @param c2Id Identificador de la instancia de C2
     * @return Colección de instancias de C4Entity asociadas a la instancia de C2
     * @generated
     */
    @Override
    public List<C4Entity> listC4(Long c2Id) {
        return getC2(c2Id).getC4();
    }

    /**
     * Obtiene una instancia de C4Entity asociada a una instancia de C2
     *
     * @param c2Id Identificador de la instancia de C2
     * @param c4Id Identificador de la instancia de C4
     * @generated
     */
    @Override
    public C4Entity getC4(Long c2Id, Long c4Id) {
        List<C4Entity> list = getC2(c2Id).getC4();
        C4Entity c4Entity = new C4Entity();
        c4Entity.setId(c4Id);
        int index = list.indexOf(c4Entity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia un C4 existente a un C2
     *
     * @param c2Id Identificador de la instancia de C2
     * @param c4Id Identificador de la instancia de C4
     * @return Instancia de C4Entity que fue asociada a C2
     * @generated
     */
    @Override
    public C4Entity addC4(Long c2Id, Long c4Id) {
        C2Entity c2Entity = getC2(c2Id);
        C4Entity c4Entity = c4Logic.getC4(c4Id);
        c4Entity.setC2(c2Entity);
        return c4Entity;
    }

    /**
     * Remplaza las instancias de C4 asociadas a una instancia de C2
     *
     * @param c2Id Identificador de la instancia de C2
     * @param list Colección de instancias de C4Entity a asociar a instancia de C2
     * @return Nueva colección de C4Entity asociada a la instancia de C2
     * @generated
     */
    @Override
    public List<C4Entity> replaceC4(Long c2Id, List<C4Entity> list) {
        C2Entity c2Entity = getC2(c2Id);
        List<C4Entity> c4List = c4Logic.getC4s();
        for (C4Entity c4 : c4List) {
            if (list.contains(c4)) {
                c4.setC2(c2Entity);
            } else {
                if (c4.getC2() != null && c4.getC2().equals(c2Entity)) {
                    c4.setC2(null);
                }
            }
        }
        c2Entity.setC4(list);
        return c2Entity.getC4();
    }

    /**
     * Desasocia un C4 existente de un C2 existente
     *
     * @param c2Id Identificador de la instancia de C2
     * @param c4Id Identificador de la instancia de C4
     * @generated
     */
    @Override
    public void removeC4(Long c2Id, Long c4Id) {
        C4Entity entity = c4Logic.getC4(c4Id);
        entity.setC2(null);
    }
}
