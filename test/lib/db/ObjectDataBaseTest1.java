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
package lib.db;

import java.nio.file.Paths;
import lib.problemDefination.IntObjectBase;
import lib.problemDefination.IntProgramDetail;
import lib.problemDefination.IntTestCase;
import ui.gui.util.TestCaseCreaterPanelTest;

/**
 *
 * @author Neel Patel
 */
public class ObjectDataBaseTest1 {
    public static void main(String ... ar){
        TestCaseCreaterPanelTest j=new TestCaseCreaterPanelTest();
        j.getProgramDetailCreaterPanel1().setCon(ObjectDataBaseTest1::cons);
        j.setVisible(true);
    }
    
    public static void cons(IntTestCase pd){
        IntObjectBase oh = new ObjectDataBase(Paths.get("data.db"));
        System.out.println(oh.insertTestCase(pd));
    }
    
    public static void print(long pid){
        ObjectHandler oh = new ObjectHandler(Paths.get("data.db"));
        IntProgramDetail pd=(IntProgramDetail)oh.readObject("p"+pid);
        System.out.println(pd.getTitle());
        System.out.println(pd.getDescription());
        System.out.println(pd.getInput());
        System.out.println(pd.getOutput());
        System.out.println(pd.getSampleInput());
        System.out.println(pd.getSampleOutput());
        System.out.println(pd.getCredit());
    }
}
