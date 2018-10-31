
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

public class EditPwd extends HttpServlet {

    public static final int MAXTRIAL = 2;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        boolean errored = false;
        String rpwd = null, uname = null, pwd = null, status ="block", key = null;
        try {
            uname = session.getAttribute("user").toString();out.print("5");
            Statement connect = dbconn.connect();
            pwd = request.getParameter("data[User][password]");out.print("7");
            int trial = (Integer)session.getAttribute("pcount");
            out.print("6");
            String profile = (String) session.getAttribute("profile");
            rpwd = request.getParameter("data[User][repassword]");
            key = request.getParameter("data[User][key]");
            int uid = (Integer) session.getAttribute("userid");
            String action = "", to = "";

            core.db.UserBehaviourAlg alg = new core.db.UserBehaviourAlg();String s=alg.getBehave(uid);out.print("6"+s);
            if (s.equals("invalidate")) {
                session.setAttribute("profile", "decoy");
                profile = "decoy";
            }
            ResultSet rs = connect.executeQuery("select * from users where uid=" + uid);
            if (profile != null && profile.equals("decoy")) {
                response.sendRedirect("editpwd.jsp?emsg='<h3><font color='green'>Password is successfully Edited</h3></font>");
            }
out.print("8");
            if (rs.next()) {out.print("5");
                if(trial<=MAXTRIAL)
                {
                    if (key.equals(rs.getString("userkey"))) 
                    {
                        if(trial==MAXTRIAL) action="editpwdtrialkey";
                                     if (pwd.equals(rpwd)) {
                                                       action = "validpwd";
                                                to = "editpwd.jsp?emsg=<h3><font color='green'>Password is successfully Edited</h3></font>";

                                    } else {
                                     action="validpwd";
                                       to = "editpwd.jsp?emsg0=<h5><font color='red'>Re-enter Password</h5></font>";
                                     }
                         } 
                
                               else {
                                         action = "editpwdwrongkey";
                                            to = "editpwd.jsp?emsg1=<h5><font color='red'>Wrong Details</h5></font>";
                                      }
                }
                else
                {
                    
                  session.setAttribute("profile","decoy");action="invalid";
                   to = "editpwd.jsp?emsg=<h3><font color='green'>Password is successfully Edited</h3></font>";

                }

                String sql = "INSERT INTO logdetails(userid,action,ukey,pwd) values(" + uid + ",'" + action + "','" + key + "',DES_ENCRYPT('" + pwd + "'))";

                Statement st = dbconn.connect();
                int row = st.executeUpdate(sql);
                if (row <= 0) {
                    errored = true;
                }
                if (action.equals("validpwd")) {
                    int row1 = connect.executeUpdate("update users set pwd=DES_ENCRYPT('" + pwd + "') where uname='" + uname + "' and userkey='" + key + "'");
                    if (!(row1 > 0)) {
                        errored = true;
                    }

                }

            } else {
                errored = true;
            }
            response.sendRedirect(to);
            if (errored) {
                session.setAttribute("flash_message", "Failed to save. Try again Later");
                session.setAttribute("flash_type", "error");
                response.sendRedirect("editpwd.jsp");

            }
        } catch (Exception e) {
            out.println(e.getMessage() + "sdfsdf"+e);
        } finally {
            out.close();
        }
   }
 
}