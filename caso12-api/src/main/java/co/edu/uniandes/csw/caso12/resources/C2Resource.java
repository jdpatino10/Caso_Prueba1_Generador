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
import co.edu.uniandes.csw.caso12.api.IC2Logic;
import co.edu.uniandes.csw.caso12.dtos.detail.C2DetailDTO;
import co.edu.uniandes.csw.caso12.entities.C2Entity;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

/**
 * URI: c2s/
 * @generated
 */
@Path("/c2s")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class C2Resource {

    @Inject private IC2Logic c2Logic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;

   
    /**
     * Convierte una lista de C2Entity a una lista de C2DetailDTO.
     *
     * @param entityList Lista de C2Entity a convertir.
     * @return Lista de C2DetailDTO convertida.
     * @generated
     */
    private List<C2DetailDTO> listEntity2DTO(List<C2Entity> entityList){
        List<C2DetailDTO> list = new ArrayList<>();
        for (C2Entity entity : entityList) {
            list.add(new C2DetailDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de C2
     *
     * @return Colección de objetos de C2DetailDTO
     * @generated
     */
    @GET
    public List<C2DetailDTO> getC2s() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", c2Logic.countC2s());
            return listEntity2DTO(c2Logic.getC2s(page, maxRecords));
        }
        return listEntity2DTO(c2Logic.getC2s());
    }

    /**
     * Obtiene los datos de una instancia de C2 a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de C2DetailDTO con los datos del C2 consultado
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public C2DetailDTO getC2(@PathParam("id") Long id) {
        return new C2DetailDTO(c2Logic.getC2(id));
    }

    /**
     * Se encarga de crear un C2 en la base de datos
     *
     * @param dto Objeto de C2DetailDTO con los datos nuevos
     * @return Objeto de C2DetailDTOcon los datos nuevos y su ID
     * @generated
     */
    @POST
    @StatusCreated
    public C2DetailDTO createC2(C2DetailDTO dto) {
        return new C2DetailDTO(c2Logic.createC2(dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de C2
     *
     * @param id Identificador de la instancia de C2 a modificar
     * @param dto Instancia de C2DetailDTO con los nuevos datos
     * @return Instancia de C2DetailDTO con los datos actualizados
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public C2DetailDTO updateC2(@PathParam("id") Long id, C2DetailDTO dto) {
        C2Entity entity = dto.toEntity();
        entity.setId(id);
        C2Entity oldEntity = c2Logic.getC2(id);
        entity.setC4(oldEntity.getC4());
        return new C2DetailDTO(c2Logic.updateC2(entity));
    }

    /**
     * Elimina una instancia de C2 de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteC2(@PathParam("id") Long id) {
        c2Logic.deleteC2(id);
    }
    public void existsC2(Long c2sId){
        C2DetailDTO c2 = getC2(c2sId);
        if (c2== null) {
            throw new WebApplicationException(404);
        }
    }
    
    
    @Path("{c2sId: \\d+}/c4")
    public Class<C2C4Resource> getC2C4Resource(@PathParam("c2sId") Long c2sId){
        existsC2(c2sId);
        return C2C4Resource.class;
    }
    
}
