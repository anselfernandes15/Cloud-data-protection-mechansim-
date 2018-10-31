
package com.consumer;

import core.db.dbconn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DataAccess extends HttpServlet {

    public static final int MAXTRIAL = 3;
    public static final int IPTRIAL = 4;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        ResultSet rs2 = null, rs3 = null, rs4 = null;
        boolean errored = false;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {//out.print("sdfdf");
            String to = "";
            int uid = (Integer) session.getAttribute("userid");
            session.setAttribute("download", null);
            core.db.UserBehaviourAlg alg = new core.db.UserBehaviourAlg();
            String profile = (String) session.getAttribute("profile");
            int trial = (Integer) session.getAttribute("kcount");
            System.out.println("trial: " + trial);
            String uname = (String) session.getAttribute("user"), hmac = "";
            String key = request.getParameter("key");//out.print("ssssss"+key);
            String ans1 = request.getParameter("a1");//out.print("ssssss"+key);
            String ans2 = request.getParameter("a2");//out.print("ssssss"+key);
            
            String fid = (String) session.getAttribute("fid"), action = ""; //out.print("xxxx"+key);   

            Statement connect = dbconn.connect();// out.print("15<br>"+uname+key); 

            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }
            int ipflag = 0;
            rs4 = connect.executeQuery("select count(*) as Rowcount from logdetails where userid=" + uid + " and ipaddress='" + ipAddress + "'");  //3 is max ipaddresses 
            if (rs4.next()) {
                if (IPTRIAL <= rs4.getInt("Rowcount")) {
                    ipflag = 1;
                }
            }
            String behave = alg.getBehave(uid);
            System.out.println("profile " + profile);
            System.out.println("behave " + behave);
            System.out.println("ipflag " + ipflag);
            if ((profile != null && profile.equals("decoy")) || (behave.equals("invalidate")) || (ipflag == 1)) {
                session.setAttribute("profile", "decoy");
                System.out.println("1");
                response.sendRedirect("download?fid=" + 0 + "&orgid=" + fid);
                return;
            }
            rs2 = connect.executeQuery("select * from files where uid=1 and file_id=" + fid);
            if (rs2.next()) {
                hmac = core.db.Hmac.hmac(rs2.getString("file_name") + rs2.getInt("file_size"));

                rs3 = connect.executeQuery("select * from files where hmac='" + hmac + "'");
                if (rs3.next()) {
                    session.setAttribute("profile", "decoy");
                    String sql = "INSERT INTO logdetails(userid,action,ukey,fid) values(" + uid + ",'decoy','" + key + "','" + fid + "')";

                    connect.executeUpdate(sql);
                    System.out.println("2");
                    response.sendRedirect("download?fid=" + fid);
                    return;
                }
                System.out.println("to=" + to);

            } else {
                String sql = "select DES_DECRYPT(answer),DES_DECRYPT(answer2)  from users where uname='" + uname + "' and userkey='" + key + "'"; // and answer=DES_ENCRYPT('" + ans1 + "'   and answer2=// answer=DES_ENCRYPT('" + ans1 + "') and answer2=DES_ENCRYPT('" + ans2 + "')";
                ResultSet rs = connect.executeQuery(sql);
                Statement st = dbconn.connect(); //out.print("15<br>"); 
                System.out.println("" + sql);
                if (!rs.next()) {
                    System.out.println("trail " + trial);
                    if (trial < MAXTRIAL) {
                        action = "invalid";
                        to = "accessfile.jsp?keymsg=Invalid Key&id=" + fid;
                    } else {
                        action = "wrongkey";
                        session.setAttribute("profile", "decoy");
                        to = "download?fid=" + 0 + "&orgid=" + fid;
                    }

                } else {
                    String x1=rs.getString(1);
                    String x2=rs.getString(2);
                    System.out.println("ans1="+x1);
                    System.out.println("ans1="+x2);

                    if(x1.equals(ans1) && x2.equals(ans2))
                    {
                    out.println("<br>Record Found");
                    if (trial >= MAXTRIAL) { //"trialkey";
                        if (alg.getBehave(uid).equals("invalidate")) {
                            action = "trialkey";
                        }
                        session.setAttribute("profile", "decoy");
                        to = "download?fid=" + 0 + "&orgid=" + fid;

                    } else {
                        action = "validkey";

                        to = "download?fid=" + fid + "&orgid=" + fid;
                    }
                }
                }
                sql = "INSERT INTO logdetails(userid,action,ukey,fid) values(" + uid + ",'" + action + "','" + key + "','" + fid + "')";

                //out.print("EXE");
                int row = st.executeUpdate(sql);

                System.out.println("to=" + to);
                System.out.println("3");
                response.sendRedirect(to);//rd.forward(request, response);
                return;
            }
            if (errored) {
                session.setAttribute("flash_message", "Failed to save. Try again");
                session.setAttribute("flash_type", "error");
                System.out.println("4");
                response.sendRedirect("accessfile.jsp?id=" + fid);

            } else {
                System.out.println("to " + to);
                if (to == null || to.equals("")) {
                    to = "download?fid=" + fid;
                }
                System.out.println("5");
                response.sendRedirect(to);
            }

        } catch (Exception e) {
            e.printStackTrace();


        } finally {

        }
    }

}
