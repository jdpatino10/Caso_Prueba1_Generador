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
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamStrategyValue;
import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;


/**
 * @generated
 */
@Entity
public class C1Entity extends BaseEntity implements Serializable {

    private Integer number;

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date date;

    @PodamExclude
    @OneToMany(mappedBy = "c1")
    private List<C3Entity> c3 = new ArrayList<>();

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
     * Obtiene el atributo date.
     *
     * @return atributo date.
     * @generated
     */
    public Date getDate(){
        return date;
    }

    /**
     * Establece el valor del atributo date.
     *
     * @param date nuevo valor del atributo
     * @generated
     */
    public void setDate(Date date){
        this.date = date;
    }

    /**
     * Obtiene la colección de c3.
     *
     * @return colección c3.
     * @generated
     */
    public List<C3Entity> getC3() {
        return c3;
    }

    /**
     * Establece el valor de la colección de c3.
     *
     * @param c3 nuevo valor de la colección.
     * @generated
     */
    public void setC3(List<C3Entity> c3) {
        this.c3 = c3;
    }
}
