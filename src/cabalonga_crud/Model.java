package cabalonga_crud;

import java.sql.*;

public class Model {
    
    static String retvalue = "";
    public static String delete_data(String x){
        try {
            String url = "jdbc:derby://localhost:1527/research"; 
            Connection conn = DriverManager.getConnection(url,"root","1234"); 
            Statement st = conn.createStatement(); 
            st.executeUpdate("DELETE from archive WHERE id='"+x+"'");
            retvalue = "Data "+x+" Deleted! Please Refresh!";
            conn.close();
        } catch (Exception e) {
            retvalue = ("Data "+x+" Can't be deleted!");
        }
        return retvalue;
    }
    public static String update_data(String x, String y, String z){
        try {
            String url = "jdbc:derby://localhost:1527/research"; 
            Connection conn = DriverManager.getConnection(url,"root","1234"); 
            Statement st = conn.createStatement(); 
            st.executeUpdate("update archive set title='"+y+"',author = '"+z+"'  where id='"+x+"'");
            retvalue = "Data "+x+" Updated! Please Refresh!";
            conn.close();
        } catch (Exception e) {
            retvalue = "Data "+x+" Not Updated! Please Refresh!";
        }
        return retvalue;
    }
    static String idno, title, author;
    public static void Refresh_Table(){
        try {
            String url = "jdbc:derby://localhost:1527/employee"; 
            Connection conn = DriverManager.getConnection(url,"root","1234"); 
            Statement st = conn.createStatement(); 
            String query ="SELECT * FROM archive";
            ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                    idno = rs.getString("id");
                    title = rs.getString("title");
                    author = rs.getString("author");
                }
            conn.close();
             
        } catch (Exception e) {
            System.out.print("Not Refreshed!");
        }
    }
    
    public static String Add_Data(String x, String Title, String Author){
        try {
                String url = "jdbc:derby://localhost:1527/research"; 
                Connection conn = DriverManager.getConnection(url,"root","1234");
                Statement st = conn.createStatement();
                System.out.print("Ka connect ka sa DB ");
                st.executeUpdate("INSERT INTO archive "+ "VALUES('"+x+"','"+Title+"','"+Author+"')");
                retvalue = "Data Registered! Please click Refresh!";
            } catch (Exception e) {
                retvalue = "Archive Must Not Contain Book Number Duplicate!";
            }
        return retvalue;
    }
     public static String retrieve(String x){
        try {
            String url = "jdbc:derby://localhost:1527/employee"; 
            Connection conn = DriverManager.getConnection(url,"root","1234"); 
            Statement st = conn.createStatement(); 
            String query ="SELECT * FROM archive WHERE id = '"+x+"'";
            ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                    idno = rs.getString("id");
                    title = rs.getString("title");
                    author = rs.getString("author");
                }
            conn.close();
             
        } catch (Exception e) {
            System.out.print("Not Refreshed!");
        }
        return title;
    }
}
