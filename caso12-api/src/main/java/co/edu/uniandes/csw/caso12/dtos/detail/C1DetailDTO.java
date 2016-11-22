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
package co.edu.uniandes.csw.caso12.dtos.detail;

import co.edu.uniandes.csw.caso12.dtos.minimum.*;
import co.edu.uniandes.csw.caso12.entities.C1Entity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@XmlRootElement
public class C1DetailDTO extends C1DTO{



    /**
     * @generated
     */
    public C1DetailDTO() {
        super();
    }

    /**
     * Crea un objeto C1DetailDTO a partir de un objeto C1Entity incluyendo los atributos de C1DTO.
     *
     * @param entity Entidad C1Entity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public C1DetailDTO(C1Entity entity) {
        super(entity);
        
    }

    /**
     * Convierte un objeto C1DetailDTO a C1Entity incluyendo los atributos de C1DTO.
     *
     * @return Nueva objeto C1Entity.
     * @generated
     */
    @Override
    public C1Entity toEntity() {
        C1Entity entity = super.toEntity();
        return entity;
    }

}
