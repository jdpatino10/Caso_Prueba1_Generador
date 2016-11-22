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

import co.edu.uniandes.csw.caso12.api.IC4Logic;
import co.edu.uniandes.csw.caso12.entities.C4Entity;
import co.edu.uniandes.csw.caso12.persistence.C4Persistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 * @generated
 */
@Stateless
public class C4Logic implements IC4Logic {

    @Inject private C4Persistence persistence;


    /**
     * Obtiene el número de registros de C4.
     *
     * @return Número de registros de C4.
     * @generated
     */
    public int countC4s() {
        return persistence.count();
    }

    /**
     * Obtiene la lista de los registros de C4.
     *
     * @return Colección de objetos de C4Entity.
     * @generated
     */
    @Override
    public List<C4Entity> getC4s() {
        return persistence.findAll();
    }

    /**
     * Obtiene la lista de los registros de C4 indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @return Colección de objetos de C4Entity.
     * @generated
     */
    @Override
    public List<C4Entity> getC4s(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    /**
     * Obtiene los datos de una instancia de C4 a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de C4Entity con los datos del C4 consultado.
     * @generated
     */
    public C4Entity getC4(Long id) {
        return persistence.find(id);
    }

    /**
     * Se encarga de crear un C4 en la base de datos.
     *
     * @param entity Objeto de C4Entity con los datos nuevos
     * @return Objeto de C4Entity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public C4Entity createC4(C4Entity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de C4.
     *
     * @param entity Instancia de C4Entity con los nuevos datos.
     * @return Instancia de C4Entity con los datos actualizados.
     * @generated
     */
    @Override
    public C4Entity updateC4(C4Entity entity) {
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de C4 de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @Override
    public void deleteC4(Long id) {
        persistence.delete(id);
    }
  
}
