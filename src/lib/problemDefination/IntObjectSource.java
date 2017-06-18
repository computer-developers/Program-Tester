/*
 * The MIT License
 *
 * Copyright 2017 Neel Patel.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package lib.problemDefination;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neel Patel
 */
public interface IntObjectSource {
    default List<IntProgramDetail> getAllProgramDetails(){
        List<IntProgramDetail> l=new ArrayList<>();
        for(long i:getAllPids())
            l.add(getProgramDetail(i));
        return l;
    }
    
    default List<IntTestCase> getAllTestCases(){
        List<IntTestCase> l=new ArrayList<>();
        for(long i:getAllPids())
            l.addAll(getTestCases(i));
        return l;
    }
    
    default List<IntTestCase> getTestCases(long pid){
        List<IntTestCase> l=new ArrayList<>();
        for(long i:getAllIndexes(pid))
            l.add(getTestCase(pid,i));
        return l;
    }
    
    IntTestCase getTestCase(long pid,long index);
    IntProgramDetail getProgramDetail(long pid);
    List<Long> getAllPids();
    List<Long> getAllIndexes(long pid);
}
