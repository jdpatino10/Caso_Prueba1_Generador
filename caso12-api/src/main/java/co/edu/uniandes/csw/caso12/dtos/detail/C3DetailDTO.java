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
import co.edu.uniandes.csw.caso12.entities.C3Entity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@XmlRootElement
public class C3DetailDTO extends C3DTO{


    @PodamExclude
    private C1DTO c1;

    /**
     * @generated
     */
    public C3DetailDTO() {
        super();
    }

    /**
     * Crea un objeto C3DetailDTO a partir de un objeto C3Entity incluyendo los atributos de C3DTO.
     *
     * @param entity Entidad C3Entity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public C3DetailDTO(C3Entity entity) {
        super(entity);
        if (entity.getC1()!=null){
        this.c1 = new C1DTO(entity.getC1());
        }
        
    }

    /**
     * Convierte un objeto C3DetailDTO a C3Entity incluyendo los atributos de C3DTO.
     *
     * @return Nueva objeto C3Entity.
     * @generated
     */
    @Override
    public C3Entity toEntity() {
        C3Entity entity = super.toEntity();
        if (this.getC1()!=null){
        entity.setC1(this.getC1().toEntity());
        }
        return entity;
    }

    /**
     * Obtiene el atributo c1.
     *
     * @return atributo c1.
     * @generated
     */
    public C1DTO getC1() {
        return c1;
    }

    /**
     * Establece el valor del atributo c1.
     *
     * @param c1 nuevo valor del atributo
     * @generated
     */
    public void setC1(C1DTO c1) {
        this.c1 = c1;
    }

}
