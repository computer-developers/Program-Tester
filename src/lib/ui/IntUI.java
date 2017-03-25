/*
* Copyright 2017 Program Tester Team
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at

*     http://www.apache.org/licenses/LICENSE-2.0

* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
* package lib.adminModule;
*/
package lib.ui;

import java.io.Closeable;

/**
 *
 * @author Parth Doshi
 */
public interface IntUI extends Closeable{
     /**
      * implementation of this method should show the {@code String}
        {@code message} to the user.
      * @param message 
      */
     public void showMessage(String message);
     
     /**
      * implementation of this method should prompt for input to the user.
      * {@code message} should be shown to the user when getting input.
      * @param message String message
      * @return input given by user.
      */
     public String Prompt(String message);
     
     /**
      * implementation of this method should initiate the process of execution.
      */
     public void start();
     
     /**
      * {@inheritDoc }
      */
     public void close();
}
