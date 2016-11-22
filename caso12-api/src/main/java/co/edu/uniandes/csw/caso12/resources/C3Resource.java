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
import co.edu.uniandes.csw.caso12.api.IC3Logic;
import co.edu.uniandes.csw.caso12.dtos.detail.C3DetailDTO;
import co.edu.uniandes.csw.caso12.entities.C3Entity;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

/**
 * URI: c3s/
 * @generated
 */
@Path("/c3s")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class C3Resource {

    @Inject private IC3Logic c3Logic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;

   
    /**
     * Convierte una lista de C3Entity a una lista de C3DetailDTO.
     *
     * @param entityList Lista de C3Entity a convertir.
     * @return Lista de C3DetailDTO convertida.
     * @generated
     */
    private List<C3DetailDTO> listEntity2DTO(List<C3Entity> entityList){
        List<C3DetailDTO> list = new ArrayList<>();
        for (C3Entity entity : entityList) {
            list.add(new C3DetailDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de C3
     *
     * @return Colección de objetos de C3DetailDTO
     * @generated
     */
    @GET
    public List<C3DetailDTO> getC3s() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", c3Logic.countC3s());
            return listEntity2DTO(c3Logic.getC3s(page, maxRecords));
        }
        return listEntity2DTO(c3Logic.getC3s());
    }

    /**
     * Obtiene los datos de una instancia de C3 a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de C3DetailDTO con los datos del C3 consultado
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public C3DetailDTO getC3(@PathParam("id") Long id) {
        return new C3DetailDTO(c3Logic.getC3(id));
    }

    /**
     * Se encarga de crear un C3 en la base de datos
     *
     * @param dto Objeto de C3DetailDTO con los datos nuevos
     * @return Objeto de C3DetailDTOcon los datos nuevos y su ID
     * @generated
     */
    @POST
    @StatusCreated
    public C3DetailDTO createC3(C3DetailDTO dto) {
        return new C3DetailDTO(c3Logic.createC3(dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de C3
     *
     * @param id Identificador de la instancia de C3 a modificar
     * @param dto Instancia de C3DetailDTO con los nuevos datos
     * @return Instancia de C3DetailDTO con los datos actualizados
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public C3DetailDTO updateC3(@PathParam("id") Long id, C3DetailDTO dto) {
        C3Entity entity = dto.toEntity();
        entity.setId(id);
        return new C3DetailDTO(c3Logic.updateC3(entity));
    }

    /**
     * Elimina una instancia de C3 de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteC3(@PathParam("id") Long id) {
        c3Logic.deleteC3(id);
    }
    
}
