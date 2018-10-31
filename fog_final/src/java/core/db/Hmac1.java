
package core.db;

import javax.crypto.*;

public class Hmac1 {
  public static String hmac(String str) throws Exception {


      
        KeyGenerator kg = KeyGenerator.getInstance("HmacMD5");
        SecretKey sk = kg.generateKey();

        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(sk);
        byte[] result = mac.doFinal(str.getBytes());
        return result.toString();
    }  
  
  public static void main(String args[])
  {
      
      Hmac1 h=new Hmac1();
     try{
         
         core.db.UserBehaviourAlg alg =new core.db.UserBehaviourAlg();
         System.out.print("Behave"+alg.getBehave(12)+"\n");
         
         System.out.print("Hiiiiii"+hmac("Srujana")+"zzzzzzzz"+hmac("Srujana").toString());
         
         System.out.print(hmac("ShreeRam"));
     }
      catch(Exception e)
      {
      }
    
  }
}
