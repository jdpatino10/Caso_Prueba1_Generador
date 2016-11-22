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
package co.edu.uniandes.csw.caso12.api;

import co.edu.uniandes.csw.caso12.entities.C1Entity;
import co.edu.uniandes.csw.caso12.entities.C3Entity;
import java.util.List;

public interface IC1Logic {
    public int countC1s();
    public List<C1Entity> getC1s();
    public List<C1Entity> getC1s(Integer page, Integer maxRecords);
    public C1Entity getC1(Long id);
    public C1Entity createC1(C1Entity entity); 
    public C1Entity updateC1(C1Entity entity);
    public void deleteC1(Long id);
    public List<C3Entity> listC3(Long c1Id);
    public C3Entity getC3(Long c1Id, Long c3Id);
    public C3Entity addC3(Long c1Id, Long c3Id);
    public List<C3Entity> replaceC3(Long c1Id, List<C3Entity> list);
    public void removeC3(Long c1Id, Long c3Id);
}
