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
package co.edu.uniandes.csw.caso12.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;


/**
 * @generated
 */
@Entity
public class C2Entity extends BaseEntity implements Serializable {

    private String test;

    private Integer number;

    @PodamExclude
    @OneToMany(mappedBy = "c2")
    private List<C4Entity> c4 = new ArrayList<>();

    /**
     * Obtiene el atributo test.
     *
     * @return atributo test.
     * @generated
     */
    public String getTest(){
        return test;
    }

    /**
     * Establece el valor del atributo test.
     *
     * @param test nuevo valor del atributo
     * @generated
     */
    public void setTest(String test){
        this.test = test;
    }

    /**
     * Obtiene el atributo number.
     *
     * @return atributo number.
     * @generated
     */
    public Integer getNumber(){
        return number;
    }

    /**
     * Establece el valor del atributo number.
     *
     * @param number nuevo valor del atributo
     * @generated
     */
    public void setNumber(Integer number){
        this.number = number;
    }

    /**
     * Obtiene la colección de c4.
     *
     * @return colección c4.
     * @generated
     */
    public List<C4Entity> getC4() {
        return c4;
    }

    /**
     * Establece el valor de la colección de c4.
     *
     * @param c4 nuevo valor de la colección.
     * @generated
     */
    public void setC4(List<C4Entity> c4) {
        this.c4 = c4;
    }
}
