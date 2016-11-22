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
package co.edu.uniandes.csw.caso12.resources;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.caso12.api.IC1Logic;
import co.edu.uniandes.csw.caso12.dtos.detail.C1DetailDTO;
import co.edu.uniandes.csw.caso12.entities.C1Entity;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

/**
 * URI: c1s/
 * @generated
 */
@Path("/c1s")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class C1Resource {

    @Inject private IC1Logic c1Logic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;

   
    /**
     * Convierte una lista de C1Entity a una lista de C1DetailDTO.
     *
     * @param entityList Lista de C1Entity a convertir.
     * @return Lista de C1DetailDTO convertida.
     * @generated
     */
    private List<C1DetailDTO> listEntity2DTO(List<C1Entity> entityList){
        List<C1DetailDTO> list = new ArrayList<>();
        for (C1Entity entity : entityList) {
            list.add(new C1DetailDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de C1
     *
     * @return Colección de objetos de C1DetailDTO
     * @generated
     */
    @GET
    public List<C1DetailDTO> getC1s() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", c1Logic.countC1s());
            return listEntity2DTO(c1Logic.getC1s(page, maxRecords));
        }
        return listEntity2DTO(c1Logic.getC1s());
    }

    /**
     * Obtiene los datos de una instancia de C1 a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de C1DetailDTO con los datos del C1 consultado
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public C1DetailDTO getC1(@PathParam("id") Long id) {
        return new C1DetailDTO(c1Logic.getC1(id));
    }

    /**
     * Se encarga de crear un C1 en la base de datos
     *
     * @param dto Objeto de C1DetailDTO con los datos nuevos
     * @return Objeto de C1DetailDTOcon los datos nuevos y su ID
     * @generated
     */
    @POST
    @StatusCreated
    public C1DetailDTO createC1(C1DetailDTO dto) {
        return new C1DetailDTO(c1Logic.createC1(dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de C1
     *
     * @param id Identificador de la instancia de C1 a modificar
     * @param dto Instancia de C1DetailDTO con los nuevos datos
     * @return Instancia de C1DetailDTO con los datos actualizados
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public C1DetailDTO updateC1(@PathParam("id") Long id, C1DetailDTO dto) {
        C1Entity entity = dto.toEntity();
        entity.setId(id);
        C1Entity oldEntity = c1Logic.getC1(id);
        entity.setC3(oldEntity.getC3());
        return new C1DetailDTO(c1Logic.updateC1(entity));
    }

    /**
     * Elimina una instancia de C1 de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteC1(@PathParam("id") Long id) {
        c1Logic.deleteC1(id);
    }
    public void existsC1(Long c1sId){
        C1DetailDTO c1 = getC1(c1sId);
        if (c1== null) {
            throw new WebApplicationException(404);
        }
    }
    
    
    @Path("{c1sId: \\d+}/c3")
    public Class<C1C3Resource> getC1C3Resource(@PathParam("c1sId") Long c1sId){
        existsC1(c1sId);
        return C1C3Resource.class;
    }
    
}
