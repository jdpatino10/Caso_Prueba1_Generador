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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.caso12.api.IC1Logic;
import co.edu.uniandes.csw.caso12.dtos.detail.C3DetailDTO;
import co.edu.uniandes.csw.caso12.entities.C3Entity;
import java.util.ArrayList;
/**
 * URI: c1s/{c1sId: \\d+}/c3
 * @generated
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class C1C3Resource {

    @Inject private IC1Logic c1Logic;
    @Context private HttpServletResponse response;

    /**
     * Convierte una lista de C3Entity a una lista de C3DetailDTO.
     *
     * @param entityList Lista de C3Entity a convertir.
     * @return Lista de C3DetailDTO convertida.
     * @generated
     */
    private List<C3DetailDTO> c3ListEntity2DTO(List<C3Entity> entityList){
        List<C3DetailDTO> list = new ArrayList<>();
        for (C3Entity entity : entityList) {
            list.add(new C3DetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de C3DetailDTO a una lista de C3Entity.
     *
     * @param dtos Lista de C3DetailDTO a convertir.
     * @return Lista de C3Entity convertida.
     * @generated
     */
    private List<C3Entity> c3ListDTO2Entity(List<C3DetailDTO> dtos){
        List<C3Entity> list = new ArrayList<>();
        for (C3DetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

    /**
     * Obtiene una colección de instancias de C3DetailDTO asociadas a una
     * instancia de C1
     *
     * @param c1sId Identificador de la instancia de C1
     * @return Colección de instancias de C3DetailDTO asociadas a la instancia de C1
     * @generated
     */
    @GET
    public List<C3DetailDTO> listC3(@PathParam("c1sId") Long c1sId) {
        return c3ListEntity2DTO(c1Logic.listC3(c1sId));
    }

    /**
     * Obtiene una instancia de C3 asociada a una instancia de C1
     *
     * @param c1sId Identificador de la instancia de C1
     * @param c3Id Identificador de la instancia de C3
     * @generated
     */
    @GET
    @Path("{c3Id: \\d+}")
    public C3DetailDTO getC3(@PathParam("c1sId") Long c1sId, @PathParam("c3Id") Long c3Id) {
        return new C3DetailDTO(c1Logic.getC3(c1sId, c3Id));
    }

    /**
     * Asocia un C3 existente a un C1
     *
     * @param c1sId Identificador de la instancia de C1
     * @param c3Id Identificador de la instancia de C3
     * @return Instancia de C3DetailDTO que fue asociada a C1
     * @generated
     */
    @POST
    @Path("{c3Id: \\d+}")
    public C3DetailDTO addC3(@PathParam("c1sId") Long c1sId, @PathParam("c3Id") Long c3Id) {
        return new C3DetailDTO(c1Logic.addC3(c1sId, c3Id));
    }

    /**
     * Remplaza las instancias de C3 asociadas a una instancia de C1
     *
     * @param c1sId Identificador de la instancia de C1
     * @param c3s Colección de instancias de C3DTO a asociar a instancia de C1
     * @return Nueva colección de C3DTO asociada a la instancia de C1
     * @generated
     */
    @PUT
    public List<C3DetailDTO> replaceC3(@PathParam("c1sId") Long c1sId, List<C3DetailDTO> c3s) {
        return c3ListEntity2DTO(c1Logic.replaceC3(c1sId, c3ListDTO2Entity(c3s)));
    }

    /**
     * Desasocia un C3 existente de un C1 existente
     *
     * @param c1sId Identificador de la instancia de C1
     * @param c3Id Identificador de la instancia de C3
     * @generated
     */
    @DELETE
    @Path("{c3Id: \\d+}")
    public void removeC3(@PathParam("c1sId") Long c1sId, @PathParam("c3Id") Long c3Id) {
        c1Logic.removeC3(c1sId, c3Id);
    }
}
