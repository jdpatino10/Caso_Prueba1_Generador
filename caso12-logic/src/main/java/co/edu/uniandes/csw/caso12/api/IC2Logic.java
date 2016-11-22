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

import co.edu.uniandes.csw.caso12.entities.C2Entity;
import co.edu.uniandes.csw.caso12.entities.C4Entity;
import java.util.List;

public interface IC2Logic {
    public int countC2s();
    public List<C2Entity> getC2s();
    public List<C2Entity> getC2s(Integer page, Integer maxRecords);
    public C2Entity getC2(Long id);
    public C2Entity createC2(C2Entity entity); 
    public C2Entity updateC2(C2Entity entity);
    public void deleteC2(Long id);
    public List<C4Entity> listC4(Long c2Id);
    public C4Entity getC4(Long c2Id, Long c4Id);
    public C4Entity addC4(Long c2Id, Long c4Id);
    public List<C4Entity> replaceC4(Long c2Id, List<C4Entity> list);
    public void removeC4(Long c2Id, Long c4Id);
}
