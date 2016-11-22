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
import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import static org.jboss.arquillian.graphene.Graphene.waitGui;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class C1CreatePage {

    @FindBy(id = "name")
    private WebElement nameInput;
    @FindBy(id = "number")
    private WebElement numberInput;
    @FindBy(id = "date")
    private WebElement dateInput;

    @FindBy(id = "save-c1")
    private WebElement saveBtn;

    @FindBy(id = "cancel-c1")
    private WebElement cancelBtn;

    public void saveC1(C1DTO c1) {
         waitGui().until().element(nameInput).is().visible();
         nameInput.clear();
         nameInput.sendKeys(c1.getName());
         waitGui().until().element(numberInput).is().visible();
         numberInput.clear();
         numberInput.sendKeys(c1.getNumber().toString());
         waitGui().until().element(dateInput).is().visible();
         dateInput.clear();
         dateInput.sendKeys(c1.getDate().toString());
        guardAjax(saveBtn).click();
    }
}
