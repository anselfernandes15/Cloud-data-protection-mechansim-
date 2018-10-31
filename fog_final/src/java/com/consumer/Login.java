package com.consumer;

import core.db.dbconn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
public class Login extends HttpServlet {

    public static final int MAXTRIAL = 3;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = getServletContext();
        String verifyOTP = (String) context.getAttribute("DOTP");
        String otp = request.getParameter("otp");
        HttpSession session = request.getSession(true);

        if (verifyOTP.equals(otp)) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String to = "";
            int trial = 0;
            if (session.getAttribute("lcount") != null) {
                trial = (Integer) session.getAttribute("lcount");
            }
            int uid = 0;
            String action = "";
            boolean errored = false;
            String uname = null, pwd = null, status = "block", pwd1 = null;
            try {
                Statement connect = dbconn.connect();
                uname = request.getParameter("data[User][username]");
                pwd = request.getParameter("data[User][password]");
                System.out.println("sql:" + "select DES_DECRYPT(pwd) from users where uname='" + uname + "' and pwd=DES_ENCRYPT('" + pwd + "')");

                System.out.println("sql:" + "select uid,uname,pwd,status from users where uname='" + uname + "' and pwd=DES_ENCRYPT('" + pwd + "')");
                ResultSet rs = connect.executeQuery("select uid,uname,DES_DECRYPT(pwd),status from users where uname='" + uname + "'");
                Statement s1 = dbconn.connect();
                 Statement ss1 = dbconn.connect();
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }
                if (rs.next()) {
                    pwd1 = rs.getString(3);
                    System.out.println("pwd1:" + pwd1);
                    if (rs.getString("uname").equals("admin") && pwd1.equals("admin")) {
                        session.setAttribute("user", "admin");
                        response.sendRedirect("adminhome.jsp");
                    } else if (rs.getString("uname").equals("TPA") && pwd1.equals("TPA")) {
                        response.sendRedirect("tpahome.jsp");
                    }


                    uid = rs.getInt("uid");
                    if (!status.equals(rs.getString("status"))) {

                        if (pwd.equals(pwd1)) {
                            java.util.Date d1=new java.util.Date();
String t1=d1.toString();
System.out.println(t1);
                           /* if(session==null)
                            {
                                session.setAttribute("un", rs.getString("uname") + ",");
                                session.setAttribute("lt",t1); //d1.toString()+",");
                                }
                            else
                            {*/
String xx=(String)session.getAttribute("un");
if(xx!=null)
{

    String str = xx+"," + rs.getString("uname") + "#" +t1+"#"+session.getId();
                            session.setAttribute("un",str.toString() );
                                }
 else
{
    String str =  rs.getString("uname") +"#"+t1+"#"+session.getId();
                            session.setAttribute("un",str.toString() );
                                }
                          // Date d1=new Date();
  int roww = ss1.executeUpdate("INSERT INTO login (userid,uname,status) values(" + uid + ",'"+rs.getString("uname")+"','login')");

                            session.setAttribute("ustatus", "logged");
                            if (trial == MAXTRIAL) {
                                action = "trialpwd";
                                session.setAttribute("profile", "decoy");

                            }

                            if (rs.getString("status").equals("admin")) {
                                session.setAttribute("user", "admin");
                                to = "adminhome.jsp";

                            } else {
                                session.setAttribute("user", uname);
                             //  List<String> users = (List<String>)session.getAttribute("users");
 //if(users==null) {
    //    users = new ArrayList<String>();
   // }
   // users.add(""+rs.getInt(1));
 //   session.setAttribute("users",""+rs.getInt(1));
                              //  ServletContext cx = request.getSession().getServletContext();


                                     //  }

                                 //Application.setAttribute("message", theSharedObject);
                             session.setAttribute("userid", +rs.getInt(1));
                                to = "userhome.jsp";
                            }

                        } else {
                            to = "login.jsp?msg=<h5><font color='red'>Wrong Password</h5></font>";
                        }
                    } else {
                        to = "login.jsp?msg1=<h5><font color='red'>Account Blocked</h5></font>";
                    }

                } else {
                    to = "login.jsp?msg1=<h5><font color='red'>Invalid User</h5></font>";
                }
                if (action.equals("trialpwd")) {
                    int row = s1.executeUpdate("INSERT INTO logdetails (userid,action,pwd,ipaddress) values(" + uid + ",'" + action + "',DES_ENCRYPT('" + pwd + "'),'" + ipAddress + "')");

                }
                ResultSet rs4 = connect.executeQuery("select * from logdetails where userid=" + uid + " and ipaddress='" + ipAddress + "'");  //3 is max ipaddresses 
                if (!rs4.first()) {
                    s1.executeUpdate("Insert into logdetails (userid,ipaddress) values(" + uid + ",'" + ipAddress + "')");
                }
                System.out.println("to:" + to);
                response.sendRedirect(to);

                if (errored) {
                    session.setAttribute("flash_message", "Failed to save. Try again");
                    session.setAttribute("flash_type", "error");
                    response.sendRedirect("login.jsp");
                }
//            } }
            } catch (Exception e) {
                out.println(e + "sdfsdf");
            } finally {
                out.close();
            }
        } else {
            session.setAttribute("flash_message", "Entered wrong OTP");
            session.setAttribute("flash_type", "error");
            response.sendRedirect("OTPVerify.jsp");
        }
    }
 }
