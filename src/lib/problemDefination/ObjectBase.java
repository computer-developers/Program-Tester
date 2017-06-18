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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Neel Patel
 */
public class ObjectBase implements IntObjectBase{
    private Map<String,Object> data=Collections.synchronizedMap(new HashMap<>());
    
    public ObjectBase(){

    }
    
    @Override
    public IntTestCase getTestCase(long pid, long index) {
        return (IntTestCase)data.get("p"+pid+"i"+index);
    }

    @Override
    public IntProgramDetail getProgramDetail(long pid) {
        return (IntProgramDetail)data.get("p"+pid);
    }

    @Override
    public List<Long> getAllPids() {
        return data.keySet().stream().filter(s->s.matches("^p[0-9]{0,14}$"))
                .map(x->Long.getLong(x.substring(1)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> getAllIndexes(long pid) {
        return data.keySet().stream().filter(s->s.matches("^p"+pid+"i[0-9]{0,14}$"))
                .map(x->Long.getLong(x.split("i",2)[1]))
                .collect(Collectors.toList());
    }

    @Override
    public boolean insertProgramDetail(IntProgramDetail pd) {
        data.put("p"+pd.getProgramID(),pd);
        return true;
    }

    @Override
    public boolean insertTestCase(IntTestCase tc) {
        if(data.containsKey("p"+tc.programID())){
            data.put("p"+tc.programID()+"i"+tc.index(),tc);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeProgramDetail(long pid) {
        try{
            if(data.remove("p"+pid)!=null)
                return true;
            return false;
        }catch(Exception ex){
            return false;
        }
    }

    @Override
    public boolean removeTestCase(long pid, long index) {
        try{
            if(data.remove("p"+pid+"i"+index)!=null)
                return true;
            return false;
        }catch(Exception ex){
            return false;
        }
    }
    
}
