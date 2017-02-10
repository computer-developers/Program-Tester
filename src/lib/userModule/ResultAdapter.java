/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.userModule;

/**
 *
 * @author Neel PAtel
 */
class ResultAdapter implements IntResult{
     final long runTime;
     final int code;
     final String message;
     ResultAdapter(long time,int code,String message){
          this.message=message;
          this.runTime=time;
          this.code=code;
     }
     @Override
     public long getRunTime() throws TimelimitExceededException {
          return runTime;
     }

     @Override
     public String getMessage() {
          return message;
     }

     @Override
     public int getMessageCode() {
          return code;
     }
     
}
