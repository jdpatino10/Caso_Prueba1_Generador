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
package co.edu.uniandes.csw.caso12.tests.selenium.pages.c2;

import co.edu.uniandes.csw.caso12.dtos.minimum.C2DTO;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class C2DetailPage {

    @FindBy(id = "delete-c2")
    private WebElement deleteBtn;

    @FindBy(id = "edit-c2")
    private WebElement editBtn;

    @FindBy(id = "list-c2")
    private WebElement listBtn;

    
    @FindBy(id = "test")
    private WebElement test;
    @FindBy(id = "number")
    private WebElement number;

    public void list() {
        listBtn.click();
    }

    public void edit() {
        editBtn.click();
    }

    public void delete() {
        deleteBtn.click();
    }

    public C2DTO getData() {
        C2DTO c2 = new C2DTO();        
        c2.setTest(this.test.getText());        
        c2.setNumber(Integer.parseInt(this.number.getText()));
        return c2;
    }
}
