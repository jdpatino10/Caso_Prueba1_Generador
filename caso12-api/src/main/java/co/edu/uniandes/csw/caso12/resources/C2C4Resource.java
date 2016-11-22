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
import co.edu.uniandes.csw.caso12.api.IC2Logic;
import co.edu.uniandes.csw.caso12.dtos.detail.C4DetailDTO;
import co.edu.uniandes.csw.caso12.entities.C4Entity;
import java.util.ArrayList;
/**
 * URI: c2s/{c2sId: \\d+}/c4
 * @generated
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class C2C4Resource {

    @Inject private IC2Logic c2Logic;
    @Context private HttpServletResponse response;

    /**
     * Convierte una lista de C4Entity a una lista de C4DetailDTO.
     *
     * @param entityList Lista de C4Entity a convertir.
     * @return Lista de C4DetailDTO convertida.
     * @generated
     */
    private List<C4DetailDTO> c4ListEntity2DTO(List<C4Entity> entityList){
        List<C4DetailDTO> list = new ArrayList<>();
        for (C4Entity entity : entityList) {
            list.add(new C4DetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de C4DetailDTO a una lista de C4Entity.
     *
     * @param dtos Lista de C4DetailDTO a convertir.
     * @return Lista de C4Entity convertida.
     * @generated
     */
    private List<C4Entity> c4ListDTO2Entity(List<C4DetailDTO> dtos){
        List<C4Entity> list = new ArrayList<>();
        for (C4DetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

    /**
     * Obtiene una colección de instancias de C4DetailDTO asociadas a una
     * instancia de C2
     *
     * @param c2sId Identificador de la instancia de C2
     * @return Colección de instancias de C4DetailDTO asociadas a la instancia de C2
     * @generated
     */
    @GET
    public List<C4DetailDTO> listC4(@PathParam("c2sId") Long c2sId) {
        return c4ListEntity2DTO(c2Logic.listC4(c2sId));
    }

    /**
     * Obtiene una instancia de C4 asociada a una instancia de C2
     *
     * @param c2sId Identificador de la instancia de C2
     * @param c4Id Identificador de la instancia de C4
     * @generated
     */
    @GET
    @Path("{c4Id: \\d+}")
    public C4DetailDTO getC4(@PathParam("c2sId") Long c2sId, @PathParam("c4Id") Long c4Id) {
        return new C4DetailDTO(c2Logic.getC4(c2sId, c4Id));
    }

    /**
     * Asocia un C4 existente a un C2
     *
     * @param c2sId Identificador de la instancia de C2
     * @param c4Id Identificador de la instancia de C4
     * @return Instancia de C4DetailDTO que fue asociada a C2
     * @generated
     */
    @POST
    @Path("{c4Id: \\d+}")
    public C4DetailDTO addC4(@PathParam("c2sId") Long c2sId, @PathParam("c4Id") Long c4Id) {
        return new C4DetailDTO(c2Logic.addC4(c2sId, c4Id));
    }

    /**
     * Remplaza las instancias de C4 asociadas a una instancia de C2
     *
     * @param c2sId Identificador de la instancia de C2
     * @param c4s Colección de instancias de C4DTO a asociar a instancia de C2
     * @return Nueva colección de C4DTO asociada a la instancia de C2
     * @generated
     */
    @PUT
    public List<C4DetailDTO> replaceC4(@PathParam("c2sId") Long c2sId, List<C4DetailDTO> c4s) {
        return c4ListEntity2DTO(c2Logic.replaceC4(c2sId, c4ListDTO2Entity(c4s)));
    }

    /**
     * Desasocia un C4 existente de un C2 existente
     *
     * @param c2sId Identificador de la instancia de C2
     * @param c4Id Identificador de la instancia de C4
     * @generated
     */
    @DELETE
    @Path("{c4Id: \\d+}")
    public void removeC4(@PathParam("c2sId") Long c2sId, @PathParam("c4Id") Long c4Id) {
        c2Logic.removeC4(c2sId, c4Id);
    }
}
