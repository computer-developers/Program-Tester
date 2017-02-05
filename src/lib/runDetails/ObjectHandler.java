package lib.runDetails;
import java.io.*;
import java.nio.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import java.util.zip.*;
import javax.crypto.*;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.*;

/**
 * this is factory class which provide methods to read or write {@code IntIODetail}
   object to the specified streams.
 * @author Neel Patel
 */
class ObjectHandler {
     static class EncryptOutputStream extends OutputStream{
          private int key=25;
          private OutputStream op;
          EncryptOutputStream(OutputStream op){
               this.op=op;
          }
          
          @Override
          public synchronized void write(int b) throws IOException {
                    op.write((b+key)%256);
          }
          
          @Override
          public synchronized void write(byte b[]) throws IOException {
                    this.write(b,0,b.length);
          }
          
          @Override
          public synchronized void write(byte b[],int off,int len) throws IOException {
               synchronized(b){
                    op.write(b,off,len);
                    for(int i=0;i<len;i++)
                         b[off+i]=(byte)((b[off+i]+key)%256);
               }
          }
     }
     
     static class DecryptInputStream extends InputStream{
          private int key=25;
          private InputStream ip;
          DecryptInputStream(InputStream ip){
               this.ip=ip;
          }
          
          @Override
          public synchronized int read() throws IOException {
               int d=read();
               if(d<0)
                    return d;
               return (256+(d-key))%256;
          }
          @Override
          public synchronized int read(byte b[],int off,int len) throws IOException {
               int l=ip.read(b, off, len);
               for(int i=0;i<l;i++)
                    b[off+i]=(byte)((256+(b[off+i]-key))%256);
               return l;
          }
          
     }
     
     private ObjectHandler(){}
     
     /**
      * this method writes object to the output stream.
      * this method writes the object {@param o} to the output stream {@param out}.
      * it close the output stream after writing done.
      * it return true if the object written to the stream successfully, return
        false otherwise.
      * it throws IOException if the underlying stream throws.
      * @param out Output stream on which object {@param o} will be written.
      * @param o object of type {@code IntIODetail} which will be written on the
        stream.
      * @return true if object written successfully, false otherwise
      * @throws IOException if the underlying stream throws.
      */
     public static boolean writeObj(OutputStream out,IntIODetail o) throws IOException{
          synchronized(out){
                    ObjectOutputStream objOut=new ObjectOutputStream(out);
               try{
                    objOut.writeObject(o);
               }catch(InvalidClassException | NotSerializableException e){
                    return false; 
               }
               return true;
          }
     }
     
     /**
      * this method reads object to the input stream.
      * this method reads the object from input stream {@param in}.
      * it close the input stream after reading done.
      * it return object if the object read from the stream successfully, return
        null otherwise.
      * it throws IOException if the underlying stream throws.
      * @param in input stream from which object will be read.
      * @return object if object read successfully, null otherwise
      * @throws IOException if the underlying stream throws.
      */
     public static IntIODetail readObj(InputStream in) throws IOException{
          synchronized(in){
               ObjectInputStream objIn=new ObjectInputStream(in);
               try{
                    IntIODetail ob=(IntIODetail)objIn.readObject();
                    return ob;
               } catch(ClassNotFoundException | InvalidClassException 
                         | StreamCorruptedException | OptionalDataException e) {
                    return null;
               }
          }
     }
     
     /**
      * this method writes compressed object to the output stream.
      * this method writes the object {@param o} to the output stream {@param out}.
      * this method first mask the {@param out} with {@link ZipOutputStream}
        then call {@link writeObj} method.
      * it close the output stream after writing done.
      * it return true if the object written to the stream successfully, return
        false otherwise.
      * it throws IOException if the underlying stream throws.
      * @param out Output stream on which object {@param o} will be written.
      * @param o object of type {@code IntIODetail} which will be written on the
        stream.
      * @return true if object written successfully, false otherwise
      * @throws IOException if the underlying stream throws.
      */
     public static boolean writeCompObj(OutputStream out,IntIODetail o)
               throws IOException{
          CipherOutputStream cstream = null;
          try {        
               byte[] keyBytes = "1234123412341234".getBytes();  //example
               final byte[] ivBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 
                    0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f }; //example

               final SecretKey key = new SecretKeySpec(keyBytes, "AES");
               final IvParameterSpec IV = new IvParameterSpec(ivBytes);
               final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding (128)");
               cipher.init(Cipher.ENCRYPT_MODE, key, IV);
               cstream = new CipherOutputStream(out, cipher);

          } catch(Exception ex) {
               ex.printStackTrace();
          }
          return writeObj(cstream,o);
     }
     
     /**
      * this method reads Compressed object to the input stream.
      * this method reads the object from input stream {@param in}.
      * this method mask {@param in} with {@link ZipInputStream} then
        call the method {@link readObj}.
      * it close the input stream after reading done.
      * it return object if the object read from the stream successfully, return
        null otherwise.
      * it throws IOException if the underlying stream throws.
      * @param in input stream from which object will be read.
      * @return object if object read successfully, null otherwise
      * @throws IOException if the underlying stream throws.
      */
     public static IntIODetail readCompObj(InputStream in)
               throws IOException{
          CipherInputStream cin=null;
          byte[] keyBytes = "1234123412341234".getBytes();
          try {
          final byte[] ivBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09,
                      0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f };

          final SecretKey secretkey = new SecretKeySpec(keyBytes, "AES");
          final IvParameterSpec IV = new IvParameterSpec(ivBytes);
          final Cipher decipher = Cipher.getInstance("AES/CBC/PKCS5Padding (128)");
               decipher.init(Cipher.DECRYPT_MODE, secretkey, IV);
               cin=new CipherInputStream(in,decipher);
          } catch(InvalidKeyException | InvalidAlgorithmParameterException
                    | NoSuchPaddingException |NoSuchAlgorithmException ex) {
          }
          return readObj(cin);
     }
     
}
