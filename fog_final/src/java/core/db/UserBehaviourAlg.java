package core.db;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserBehaviourAlg {

    public float avg, value;
    public final List<String> l = new ArrayList<String>();
    public static final float THREASH = 0.5f;
    public static final int COUNT = 4, MIN_RECORDS = 5;
    public int count, valid, invalid, uid, rows;
    public String operation, pwd, fileids, fid, decoys, status, key, hmacOutput, behave, filetype;

    public UserBehaviourAlg() {
        l.add("wrongkey");
        l.add("invalid");
        l.add("trialkey");
        l.add("decoy");
        l.add("editpwdwrongkey");
        l.add("editpwdtrialkey");
        l.add("wrongpwd");
        l.add("trialpwd");
    }

    public String getBehave(int userid) {
        try {
            uid = userid;
            Statement st = dbconn.connect();
            Statement st1 = dbconn.connect();
            ResultSet rs1 = null, rs2 = null, rs3 = null;
            String sql = "select count(*) as rowcount from logdetails join users on logdetails.userid=users.uid where logdetails.userid=" + uid;
            System.out.println("sql: " + sql);
            ResultSet rs11 = st.executeQuery(sql);
            rs11.next();
            int ResultCount = rs11.getInt("rowcount");
            if (ResultCount <= MIN_RECORDS) {
                return "validate";//initial state 
            } else {
                sql = "select * from logdetails join users on logdetails.userid=users.uid where logdetails.userid=" + uid;
                System.out.println("sql: " + sql);
                ResultSet rs = st.executeQuery(sql);
                if (!rs.first()) {
                }
                while (rs.next()) {
                    invalid = 0;
                    //step1 evaluating the operation
                    operation = rs.getString("action");
                    System.out.print("\noperation" + operation + invalid);
                    if (operation == null || operation.equals("viewfiles")) {
                        continue;
                    }
                    if (operation != null && l.contains(operation)) {
                        invalid++;
                        if (operation.equalsIgnoreCase("decoy")) {
                            value = 1;
                            continue;
                        }
                    }
                    //step2 evaluating the accessed file//USE HMAC here
                    fid = rs.getString("fid");
                    System.out.print(" \nfiletype" + filetype + invalid);
                    if (fid != null) {
                        sql = "select * from files where file_id=" + fid;
                        System.out.println("sql: " + sql);
                        rs2 = st1.executeQuery(sql);
                        if (rs2.next()) {
                            hmacOutput = Hmac1.hmac(rs2.getString("file_name") + rs2.getInt("file_size"));
                        }
                        sql = "select * from files where hmac='" + hmacOutput + "'";
                        System.out.println("sql: " + sql);
                        rs3 = st1.executeQuery(sql);
                        if (rs3.next()) {
                            invalid++;
                        }
                    }
                    //step3 evaluating the key value during file access & edit pwd;
                    key = rs.getString("ukey");
                    System.out.print("\nkey" + key + "  " + invalid);
                    if (key != null) {
                        sql = "select * from users where uid=" + uid + " and userkey='" + key + "'";
                        System.out.println("sql: " + sql);
                        rs1 = st1.executeQuery(sql);
                        if (!rs1.first() || operation.equals("trialkey") || operation.equals("editpwdtrialkey")) {
                            invalid++;
                        }
                    }
                    //step4 login action 
                    pwd = rs.getString("pwd");
                    System.out.print("\npwd" + pwd + invalid);
                    if (pwd != null) {
                        sql = "select * from users where uid=" + uid + " and pwd=DES_DECRYPT('" + pwd + "')";
                        System.out.println("sql: " + sql);
                        rs1 = st1.executeQuery(sql);
                        if (!rs1.first() || operation.equals("trialpwd") || operation.equals("editpwdtrialkey")) {
                            invalid++;
                        }
                    }
                    System.out.print("\ninvalid" + invalid + "value" + value);
                    value = value + (((float) invalid) / ((float) COUNT));
                    if (value >= THREASH) {
                        rows++;
                    }
                }

                avg = ResultCount / 2;
                System.out.print("\navg" + avg);
                if (rows >= ResultCount / 2)//checking whether it is beyond the threashhold value
                {
                    behave = "invalidate";
                } else {
                    behave = "validate";
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return behave;
    }

    public static void main(String args[]) {
        Hmac h = new Hmac();
        try {
            core.db.UserBehaviourAlg alg = new core.db.UserBehaviourAlg();
            System.out.print("\nBehave" + alg.getBehave(12) + "\n");
            System.out.print("\nBehave" + alg.getBehave(14) + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
