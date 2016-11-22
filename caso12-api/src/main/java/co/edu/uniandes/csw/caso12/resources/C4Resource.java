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
import co.edu.uniandes.csw.caso12.api.IC4Logic;
import co.edu.uniandes.csw.caso12.dtos.detail.C4DetailDTO;
import co.edu.uniandes.csw.caso12.entities.C4Entity;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

/**
 * URI: c4s/
 * @generated
 */
@Path("/c4s")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class C4Resource {

    @Inject private IC4Logic c4Logic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;

   
    /**
     * Convierte una lista de C4Entity a una lista de C4DetailDTO.
     *
     * @param entityList Lista de C4Entity a convertir.
     * @return Lista de C4DetailDTO convertida.
     * @generated
     */
    private List<C4DetailDTO> listEntity2DTO(List<C4Entity> entityList){
        List<C4DetailDTO> list = new ArrayList<>();
        for (C4Entity entity : entityList) {
            list.add(new C4DetailDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de C4
     *
     * @return Colección de objetos de C4DetailDTO
     * @generated
     */
    @GET
    public List<C4DetailDTO> getC4s() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", c4Logic.countC4s());
            return listEntity2DTO(c4Logic.getC4s(page, maxRecords));
        }
        return listEntity2DTO(c4Logic.getC4s());
    }

    /**
     * Obtiene los datos de una instancia de C4 a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de C4DetailDTO con los datos del C4 consultado
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public C4DetailDTO getC4(@PathParam("id") Long id) {
        return new C4DetailDTO(c4Logic.getC4(id));
    }

    /**
     * Se encarga de crear un C4 en la base de datos
     *
     * @param dto Objeto de C4DetailDTO con los datos nuevos
     * @return Objeto de C4DetailDTOcon los datos nuevos y su ID
     * @generated
     */
    @POST
    @StatusCreated
    public C4DetailDTO createC4(C4DetailDTO dto) {
        return new C4DetailDTO(c4Logic.createC4(dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de C4
     *
     * @param id Identificador de la instancia de C4 a modificar
     * @param dto Instancia de C4DetailDTO con los nuevos datos
     * @return Instancia de C4DetailDTO con los datos actualizados
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public C4DetailDTO updateC4(@PathParam("id") Long id, C4DetailDTO dto) {
        C4Entity entity = dto.toEntity();
        entity.setId(id);
        return new C4DetailDTO(c4Logic.updateC4(entity));
    }

    /**
     * Elimina una instancia de C4 de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteC4(@PathParam("id") Long id) {
        c4Logic.deleteC4(id);
    }
    
}
