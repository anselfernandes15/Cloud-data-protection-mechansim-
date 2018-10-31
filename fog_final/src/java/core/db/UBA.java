
package core.db;

import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.*;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.data.general.DefaultPieDataset;


public class UBA {
   boolean errored = false;

    public final List<String> l = new ArrayList<String>();
    public double []score={5,5,1,1,1,1,1,1,1,1};
 public   double []tscore={0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
 
public int uid;
    public UBA() {
        l.add("validpwd");
        l.add("viewfiles");
        l.add("wrongkey");
        l.add("invalid");
        l.add("trialkey");
        l.add("decoy");
        l.add("editpwdwrongkey");
        l.add("editpwdtrialkey");
        l.add("wrongpwd");
        l.add("trialpwd");
    }

    public boolean  getBehavior(String value,int userid,int threshold) {
        System.out.println("Calculating UBA");
            double a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0;
             double vk=0,vf=0;

        String behave="",sql;
        try {
            uid = userid;
  

                Statement st3 = dbconn.connect();
                String uk="";
                ResultSet r1 = null, r2 = null, r3 = null;

                        sql = "select DES_DECRYPT(userkey)  from users where uid=" + uid;
                        System.out.println("sql: " + sql);
                        r2 = st3.executeQuery(sql);
                        if (r2.next())
                        {
             uk=r2.getString(1);

                        }
 System.out.println("$$key   "+uk);
                  String sq ="select action,DES_DECRYPT(ukey) from logdetails join users on logdetails.userid=users.uid where logdetails.userid=" + uid;
            System.out.println("$$$sql: " + sq);

            Statement st4 = dbconn.connect();

            ResultSet r11 = st4.executeQuery(sq);
     String be="";
           
              
  
        int count=0;
               while(r11.next())
               {
              be=r11.getString(1);
                 String ukk=r11.getString(2);
                
                   
                 if (be != null)
              {
           
                  if(be.equals("validpwd"))
                      vk++;
                   if(be.equals("viewfiles"))
                  vf++;
              if(be.equals("wrongkey"))
                  a++;
               if(be.equals("invalid"))
                  b++;
               if(be.equals("trialkey"))
                  c++;
              if(be.equals("decoy"))
                  d++;
              if(be.equals("editpwdwrongkey"))
                  e++;
              if(be.equals("editpwdtrialkey"))
                  f++;
  if(be.equals("wrongpwd"))
                  g++;
 if(be.equals("trialpwd"))
                  h++;

                   }
              count++;

               }
System.out.println("count= "+count);
    tscore[0]=vk * score[0];
        tscore[1]=vf * score[1];
    tscore[2]=a * score[2];
tscore[3]=b * score[3];
tscore[4]=c * score[4];
tscore[5]=d * score[5];
tscore[6]=e * score[6];
tscore[7]=f * score[7];
tscore[8]=g * score[8];
tscore[9]=h * score[9];
double totalscore=0.0;
for(int i=0;i<10;i++)
     totalscore=totalscore+tscore[i];//----------------(1)

System.out.println("Valid pwd used "+vk+"outof"+count+" Svore ="+tscore[0]);
System.out.println("View Files "+vf+"outof"+count+" Svore ="+tscore[1]);

System.out.println("wrongkey "+a+"outof"+count+" Svore ="+tscore[2]);
System.out.println("invalid "+b+"outof"+count+" Svore ="+tscore[3]);
System.out.println("trialkey "+c+"outof"+count+" Svore ="+tscore[4]);
System.out.println("decoy "+d+"outof"+count+" Svore ="+tscore[5]);
System.out.println("editpwdwrongkey "+e+"outof"+count+" Svore ="+tscore[6]);
System.out.println("editpwdtrialkey "+f+"outof"+count+" Svore ="+tscore[7]);
System.out.println("wrongpwd "+g+"outof"+count+" Svore ="+tscore[8]);
System.out.println("trialpwd "+h+"outof"+count+" Svore ="+tscore[9]);
System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
System.out.println("Total Score "+totalscore);
double avgscore=Math.round(totalscore/count);
System.out.println("Average Score "+avgscore);//--------------(2)(mean)
double abscmnt=0.0;

double dd=count*1.0;
System.out.println(avgscore*dd);
abscmnt=Math.abs(avgscore*dd-totalscore);//-----------------(3);

System.out.println("Absolute central moment calculation: "+abscmnt);
          /////The calculation of the variance of the event
double variance=0.0;
double []temp=new double[10];

double v=0.0;
for(int i=0;i<10;i++)
{
    temp[i] =( tscore[i] - avgscore)*( tscore[i] - avgscore);
v=v+temp[i];
        }
variance=Math.round(v/count);
       System.out.println("Variance of the calculation: "+variance);//-------------(4)
       double dispersion=Math.round(Math.sqrt(Math.PI/2)*Math.sqrt(variance));
       System.out.println("Dispersion of operation: "+dispersion);//-------------(5)
       double entropy=Math.round(Math.sqrt(Math.abs(variance-Math.pow(dispersion,2))));
        System.out.println("Entropy: "+entropy);//-------------(6)
        double tv=vk+vf;
        double tiv=count-tv;
        String utype="";
        if(tv>=count/2)
            utype="Genuine";
        else
            utype="Anomalous";
           System.out.println("Total Valid: "+tv);//-------------(6)
       System.out.println("TotalInvalid: "+tiv);//-------------(6)
      System.out.println("Type of user: "+utype);//-------------(6)
   String sq1 = "delete from uba where uid="+uid+" and uname='"+value+"' and threshold="+threshold;

            Statement connect = dbconn.connect(); //out.print("15<br>");
 System.out.println("sql:" + sq1);
//                out.print("dbbberror3");
                int rows = connect.executeUpdate(sq1);
                if (!(rows > 0)) {
                    errored = true;
                     System.out.print("Invalid data");
                }
 else
      System.out.print("Previous data deleted ");


           String userInsertQuery = "INSERT INTO uba(uid,uname,totalscore,avgscore,abscmnt,variance,dispersion,entropy,status,threshold) values(" + uid + ",'" + value+"',"+totalscore+ "," +avgscore+ "," +abscmnt+ "," +variance+ "," +dispersion+ "," +entropy + ",'"+utype+"',"+threshold+")";

           // Statement connect = dbconn.connect(); //out.print("15<br>");
 System.out.println("sql:" + userInsertQuery);
//                out.print("dbbberror3");
                 rows = connect.executeUpdate(userInsertQuery);
                if (!(rows > 0)) {
                    errored = true;
                     System.out.print("Invalid data");
                }
 else
      System.out.print("UBA Data stored");

        }        catch (Exception em) {
            em.printStackTrace();
        }
 final DefaultPieDataset data = new DefaultPieDataset();

 if(vk>=threshold)
  data.setValue("validpwd", new Double(vk));
  if(vf>=threshold)
  data.setValue("viewfiles", new Double(vf));
  if(a>=threshold)
  data.setValue("wrongkey", new Double(a));
  if(b>=threshold)
  data.setValue("invalid", new Double(b));
  if(c>=threshold)
  data.setValue("trialkey", new Double(c));
  if(d>=threshold)
  data.setValue("decoy", new Double(d));
  if(e>=threshold)
  data.setValue("editpwdwrongkey", new Double(e));
  if(f>=threshold)
  data.setValue("editpwdtrialkey", new Double(f));
  if(g>=threshold)
  data.setValue("wrongpwd", new Double(g));
//delete old chart

  JFreeChart chart = ChartFactory.createPieChart
            ("User Behaviour Analysis for user:"+value+" Threshold: "+threshold, data, true, true, false);
            try {
                final ChartRenderingInfo info = new
              ChartRenderingInfo(new StandardEntityCollection());
                final File file1 = new File("E:\\fog computing final\\fog_final\\fog_final\\web\\img\\piechart.png");
                ChartUtilities.saveChartAsPNG(file1, chart, 600, 400, info);
            }
            catch (Exception m) {
                System.out.println(m);
            }
            

         return true;
    }
 public void  getAllBehavior() {
     int threshold=1;
        System.out.println("Calculating UBA");
          
String uname=null;
        String behave="",sql;
        try {
             sql = "select distinct userid from logdetails where userid !=1";



                Statement st3 = dbconn.connect();
                String uk="";
                ResultSet r1 = null, r2 = null, r3 = null,r4=null;

                        //sql = "select DES_DECRYPT(userkey)  from users where uid=" + uid;
                        System.out.println("sql: " + sql);
                        r2 = st3.executeQuery(sql);
                        while (r2.next())
                        {
           uid=r2.getInt(1);


 System.out.println("$$User ID   "+uid);
                  String sq ="select uname,action,DES_DECRYPT(ukey) from logdetails join users on logdetails.userid=users.uid where logdetails.userid=" + uid;
            System.out.println("$$$sql: " + sq);

            Statement st4 = dbconn.connect();

            ResultSet r11 = st4.executeQuery(sq);
     String be="";

  double a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0;
             double vk=0,vf=0;

        int count=0;
               while(r11.next())
               {
                     
                   uname=r11.getString(1);
              be=r11.getString(2);
                 String ukk=r11.getString(3);


                 if (be != null)
              {

                  if(be.equals("validkey"))
                      vk++;
                   if(be.equals("viewfiles"))
                  vf++;
              if(be.equals("wrongkey"))
                  a++;
               if(be.equals("invalid"))
                  b++;
               if(be.equals("trialkey"))
                  c++;
              if(be.equals("decoy"))
                  d++;
              if(be.equals("editpwdwrongkey"))
                  e++;
              if(be.equals("editpwdtrialkey"))
                  f++;
  if(be.equals("wrongpwd"))
                  g++;
 if(be.equals("trialpwd"))
                  h++;

                   }
           

               }
         count= (int) (vk + vf + a + b + c + d + e + f + g + h);
System.out.println("count= "+count);
    tscore[0]=vk * score[0];
        tscore[1]=vf * score[1];
    tscore[2]=a * score[2];
tscore[3]=b * score[3];
tscore[4]=c * score[4];
tscore[5]=d * score[5];
tscore[6]=e * score[6];
tscore[7]=f * score[7];
tscore[8]=g * score[8];
tscore[9]=h * score[9];
double totalscore=0.0;
for(int i=0;i<10;i++)
     totalscore=totalscore+tscore[i];//----------------(1)

System.out.println("Valid key used "+vk+"outof"+count+" Svore ="+tscore[0]);
System.out.println("View Files "+vf+"outof"+count+" Svore ="+tscore[1]);

System.out.println("wrongkey "+a+"outof"+count+" Svore ="+tscore[2]);
System.out.println("invalid "+b+"outof"+count+" Svore ="+tscore[3]);
System.out.println("trialkey "+c+"outof"+count+" Svore ="+tscore[4]);
System.out.println("decoy "+d+"outof"+count+" Svore ="+tscore[5]);
System.out.println("editpwdwrongkey "+e+"outof"+count+" Svore ="+tscore[6]);
System.out.println("editpwdtrialkey "+f+"outof"+count+" Svore ="+tscore[7]);
System.out.println("wrongpwd "+g+"outof"+count+" Svore ="+tscore[8]);
System.out.println("trialpwd "+h+"outof"+count+" Svore ="+tscore[9]);
System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
System.out.println("Total Score "+totalscore);
double avgscore=Math.round(totalscore/count);
System.out.println("Average Score "+avgscore);//--------------(2)(mean)
double abscmnt=0.0;

double dd=count*1.0;
System.out.println(avgscore*dd);
abscmnt=Math.abs(avgscore*dd-totalscore);//-----------------(3);

System.out.println("Absolute central moment calculation: "+abscmnt);
          /////The calculation of the variance of the event
double variance=0.0;
double []temp=new double[10];

double v=0.0;
for(int i=0;i<10;i++)
{
    temp[i] =( tscore[i] - avgscore)*( tscore[i] - avgscore);
v=v+temp[i];
        }
variance=Math.round(v/count);
       System.out.println("Variance of the calculation: "+variance);//-------------(4)
       double dispersion=Math.round(Math.sqrt(Math.PI/2)*Math.sqrt(variance));
       System.out.println("Dispersion of operation: "+dispersion);//-------------(5)
       double entropy=Math.round(Math.sqrt(Math.abs(variance-Math.pow(dispersion,2))));
        System.out.println("Entropy: "+entropy);//-------------(6)
        double tv=vk+vf;
        double tiv=count-tv;
        String utype="";
        if(tv>=count/2)
            utype="Genuine";
        else
            utype="Anomalous";
           System.out.println("Total Valid: "+tv);//-------------(6)
       System.out.println("TotalInvalid: "+tiv);//-------------(6)
      System.out.println("Type of user: "+utype);//-------------(6)
   String sq1 = "delete from uba where uid="+uid+" and uname='"+uname+"' and threshold=1";

            Statement connect = dbconn.connect(); //out.print("15<br>");
 System.out.println("sql:" + sq1);
//                out.print("dbbberror3");
                int rows = connect.executeUpdate(sq1);
                if (!(rows > 0)) {
                    errored = true;
                     System.out.print("Invalid data");
                }
 else
      System.out.print("Previous data deleted ");


           String userInsertQuery = "INSERT INTO uba(uid,uname,totalscore,avgscore,abscmnt,variance,dispersion,entropy,status,threshold) values(" + uid + ",'" + uname+"',"+totalscore+ "," +avgscore+ "," +abscmnt+ "," +variance+ "," +dispersion+ "," +entropy + ",'"+utype+"',"+threshold+")";

           // Statement connect = dbconn.connect(); //out.print("15<br>");
 System.out.println("sql:" + userInsertQuery);
//                out.print("dbbberror3");
                 rows = connect.executeUpdate(userInsertQuery);
                if (!(rows > 0)) {
                    errored = true;
                     System.out.print("Invalid data");
                }
 else
      System.out.print("UBA Data stored");

                  final DefaultPieDataset data = new DefaultPieDataset();

 if(vk>=threshold)
  data.setValue("validkey", new Double(vk));
  if(vf>=threshold)
  data.setValue("viewfiles", new Double(vf));
  if(a>=threshold)
  data.setValue("wrongkey", new Double(a));
  if(b>=threshold)
  data.setValue("invalid", new Double(b));
  if(c>=threshold)
  data.setValue("trialkey", new Double(c));
  if(d>=threshold)
  data.setValue("decoy", new Double(d));
  if(e>=threshold)
  data.setValue("editpwdwrongkey", new Double(e));
  if(f>=threshold)
  data.setValue("editpwdtrialkey", new Double(f));
  if(g>=threshold)
  data.setValue("wrongpwd", new Double(g));
//delete old chart

  JFreeChart chart = ChartFactory.createPieChart
            ("User Behaviour Analysis for user:"+uname+" Threshold: "+threshold, data, true, true, false);
            try {
                final ChartRenderingInfo info = new
              ChartRenderingInfo(new StandardEntityCollection());
                final File file1 = new File("E:\\fog computing final\\fog_final\\fog_final\\web\\img\\"+uid+threshold+".png");
                ChartUtilities.saveChartAsPNG(file1, chart, 600, 400, info);
            }
            catch (Exception m) {
                System.out.println(m);
            }

            }
        }        catch (Exception em) {
            em.printStackTrace();
        }



    }
    public static void main(String args[]) {
   
        try {
            core.db.UBA alg = new core.db.UBA();
   alg.getAllBehavior();
      //  if(alg.getBehavior("Reshma",9,1))
         //   System.out.println("Done UBA");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
