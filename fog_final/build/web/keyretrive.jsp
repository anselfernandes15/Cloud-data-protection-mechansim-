<%@page import="com.consumer.Mail"%>
<%@page import="core.db.dbconn"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>

<%    
    System.out.println("in keyretive************jsp");
    String qstform = request.getParameter("Question");
    System.out.println("qstform=" + qstform);
    String ansform = request.getParameter("Answer");
    System.out.println("ansform =" + ansform);
    
    Statement st = null;
    Statement st1 = dbconn.connect();;
    
    ResultSet rs = null;
    ResultSet rs1 = null;

    //  String userid = (String) session.getAttribute("id");
    String user = (String) session.getAttribute("user");
    System.out.println("user=" + user);
    try {
        st = dbconn.connect();
        //  st1 = dbconn.connect();
        //  String sql = "select * from file_table ";
        // String sql = "SELECT u.username , f.original_filename , f.updated_filename from users u , file_table f where u.id=f.user_id";
        String sql = "SELECT DES_DECRYPT(question),DES_DECRYPT(answer),userkey,uname,email from users where uname='" + user + "'";
        
        
        rs = st.executeQuery(sql);
        
        if (rs.next()) {
            String question_table = rs.getString(1);
            String answer_table = rs.getString(2);
            String key_table = rs.getString(3);
            String uname = rs.getString(4);
            String email = rs.getString(5);
            System.out.println("key_table = " + key_table);
            
            System.out.println("question_table= " + question_table);
            System.out.println("answer_table= " + answer_table);
            
            if ((question_table.equals(qstform)) && (answer_table.equals(ansform))) {
                System.out.println("in ifstatement");
                Mail.sendUserDetails(uname, email, key_table);
                session.setAttribute("flash_message", "Your Key has been mailed to you!");
                session.setAttribute("flash_type", "success");
                response.sendRedirect("userhome.jsp");
                
            } else {
                Integer count = (Integer) session.getAttribute("key_count");
                System.out.println("keycount " + count);
                
                if (count >= 3) {
//                    session.setAttribute("flash_message", "Account Blocked ");
//                    session.setAttribute("flash_type", "error");

//                    sql  = "Update users set status='block' where uname='"+user+"'";
                    
                    System.out.println("inside count");
//                    int rows = dbconn.connect().executeUpdate(sql);
                    String sql1 = "SELECT uname,userkey,email from users where uname='" + user + "'";
                    System.out.println("sql1" + sql1);
                    rs1 = st1.executeQuery(sql1);
                    if (rs1.next()) {
                        System.out.println("inside if");
//                        String uname = rs1.getString("uname");
//                        System.out.println("uname" + uname);
//                        String email = rs1.getString("email");
//                        System.out.println("email:" + email);
                        String key = rs1.getString("userkey");
                        System.out.println("userkey:sendMail" + key);
//                        Mail.sendUserDetails(uname, email, key);
                    } else {
                        System.out.println("inside else of rs");
                    }


//                    response.sendRedirect("login.jsp");
                }                
                session.setAttribute("key_count", ++count);
                session.setAttribute("flash_message", "Wrong Answer ");
                session.setAttribute("flash_type", "fail");
                response.sendRedirect("keyrecovery.jsp");
            }

            /*  
             if (rs.getString("questi;on").equals("qstform") && rs.getString("answer").equals("ansform")) {
             System.out.println(" in if statement getting answer");
             session.setAttribute("flash_message", "Successfully Retrived");
             session.setAttribute("flash_type", "success");
             response.sendRedirect("keyrecovery.jsp");
             }
             */
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    
%>

