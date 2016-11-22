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
import co.edu.uniandes.csw.caso12.entities.C4Entity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@XmlRootElement
public class C4DetailDTO extends C4DTO{


    @PodamExclude
    private C2DTO c2;

    /**
     * @generated
     */
    public C4DetailDTO() {
        super();
    }

    /**
     * Crea un objeto C4DetailDTO a partir de un objeto C4Entity incluyendo los atributos de C4DTO.
     *
     * @param entity Entidad C4Entity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public C4DetailDTO(C4Entity entity) {
        super(entity);
        if (entity.getC2()!=null){
        this.c2 = new C2DTO(entity.getC2());
        }
        
    }

    /**
     * Convierte un objeto C4DetailDTO a C4Entity incluyendo los atributos de C4DTO.
     *
     * @return Nueva objeto C4Entity.
     * @generated
     */
    @Override
    public C4Entity toEntity() {
        C4Entity entity = super.toEntity();
        if (this.getC2()!=null){
        entity.setC2(this.getC2().toEntity());
        }
        return entity;
    }

    /**
     * Obtiene el atributo c2.
     *
     * @return atributo c2.
     * @generated
     */
    public C2DTO getC2() {
        return c2;
    }

    /**
     * Establece el valor del atributo c2.
     *
     * @param c2 nuevo valor del atributo
     * @generated
     */
    public void setC2(C2DTO c2) {
        this.c2 = c2;
    }

}
