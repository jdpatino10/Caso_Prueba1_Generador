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
package co.edu.uniandes.csw.caso12.tests.selenium.pages.c1;

import co.edu.uniandes.csw.caso12.dtos.minimum.C1DTO;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.sql.Date;

public class C1DetailPage {

    @FindBy(id = "delete-c1")
    private WebElement deleteBtn;

    @FindBy(id = "edit-c1")
    private WebElement editBtn;

    @FindBy(id = "list-c1")
    private WebElement listBtn;

    
    @FindBy(id = "name")
    private WebElement name;
    @FindBy(id = "number")
    private WebElement number;
    @FindBy(id = "date")
    private WebElement date;

    public void list() {
        listBtn.click();
    }

    public void edit() {
        editBtn.click();
    }

    public void delete() {
        deleteBtn.click();
    }

    public C1DTO getData() {
        C1DTO c1 = new C1DTO();        
        c1.setName(this.name.getText());        
        c1.setNumber(Integer.parseInt(this.number.getText()));
        c1.setDate(Date.valueOf(this.date.getText()));
        return c1;
    }
}
