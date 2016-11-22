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

import co.edu.uniandes.csw.caso12.api.IC3Logic;
import co.edu.uniandes.csw.caso12.entities.C3Entity;
import co.edu.uniandes.csw.caso12.persistence.C3Persistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 * @generated
 */
@Stateless
public class C3Logic implements IC3Logic {

    @Inject private C3Persistence persistence;


    /**
     * Obtiene el número de registros de C3.
     *
     * @return Número de registros de C3.
     * @generated
     */
    public int countC3s() {
        return persistence.count();
    }

    /**
     * Obtiene la lista de los registros de C3.
     *
     * @return Colección de objetos de C3Entity.
     * @generated
     */
    @Override
    public List<C3Entity> getC3s() {
        return persistence.findAll();
    }

    /**
     * Obtiene la lista de los registros de C3 indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @return Colección de objetos de C3Entity.
     * @generated
     */
    @Override
    public List<C3Entity> getC3s(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    /**
     * Obtiene los datos de una instancia de C3 a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de C3Entity con los datos del C3 consultado.
     * @generated
     */
    public C3Entity getC3(Long id) {
        return persistence.find(id);
    }

    /**
     * Se encarga de crear un C3 en la base de datos.
     *
     * @param entity Objeto de C3Entity con los datos nuevos
     * @return Objeto de C3Entity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public C3Entity createC3(C3Entity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de C3.
     *
     * @param entity Instancia de C3Entity con los nuevos datos.
     * @return Instancia de C3Entity con los datos actualizados.
     * @generated
     */
    @Override
    public C3Entity updateC3(C3Entity entity) {
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de C3 de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @Override
    public void deleteC3(Long id) {
        persistence.delete(id);
    }
  
}
