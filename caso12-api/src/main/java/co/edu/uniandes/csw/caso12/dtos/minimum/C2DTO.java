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
package co.edu.uniandes.csw.caso12.dtos.minimum;

import co.edu.uniandes.csw.caso12.entities.C2Entity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @generated
 */
@XmlRootElement
public class C2DTO implements Serializable{

    private String test;
    private Integer number;

    /**
     * @generated
     */
    public C2DTO() {
    }

    /**
     * Crea un objeto C2DTO a partir de un objeto C2Entity.
     *
     * @param entity Entidad C2Entity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public C2DTO(C2Entity entity) {
	   if (entity!=null){
        this.test=entity.getTest();
        this.number=entity.getNumber();
       }
    }

    /**
     * Convierte un objeto C2DTO a C2Entity.
     *
     * @return Nueva objeto C2Entity.
     * @generated
     */
    public C2Entity toEntity() {
        C2Entity entity = new C2Entity();
        entity.setTest(this.getTest());
        entity.setNumber(this.getNumber());
    return entity;
    }

    /**
     * Obtiene el atributo test.
     *
     * @return atributo test.
     * @generated
     */
    public String getTest() {
        return test;
    }

    /**
     * Establece el valor del atributo test.
     *
     * @param test nuevo valor del atributo
     * @generated
     */
    public void setTest(String test) {
        this.test = test;
    }

    /**
     * Obtiene el atributo number.
     *
     * @return atributo number.
     * @generated
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * Establece el valor del atributo number.
     *
     * @param number nuevo valor del atributo
     * @generated
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

}
