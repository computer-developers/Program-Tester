import java.util.*;
public class Email {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        //System.out.println("Enter email id:");
        int n=s.nextInt();
        for(int i=0;i<n;i++){
        String e=s.next();
        Email obj=new Email(e);
        System.out.println(e +" "+obj.check());
        //System.out.println(e.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\..*$"));
        }
    }  
    String email;
    int p=0;
    Email(String email){
        this.email=email;
    }
    boolean check(){
        return a();
    }
    boolean a(){
        if(p>=email.length()){
            return false;
        }
        if(Character.isLetterOrDigit(email.charAt(p))){
            p++;
            return a();
        }
        if(email.charAt(p)=='@'){
            p++;
            return b();
        }
        return false;
    }
    boolean b(){
        if(p>=email.length()){
            return false;
        }
        if(Character.isLetterOrDigit(email.charAt(p))){
            p++;
            return c();
        }
        return false;
    }
    boolean c(){
        if(p>=email.length()){
            return false;
        }
        if(Character.isLetterOrDigit(email.charAt(p))){
            p++;
            return c();
        }
        if(email.charAt(p)=='.'){
            p++;
            return d();
        }
        return false;
    }
    boolean d(){
        if(p>=email.length()){
            return true;
        }
        if(Character.isLetterOrDigit(email.charAt(p))){
            p++;
            return d();
        }
        return false;
    }
  
}