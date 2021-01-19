package cabalonga_crud;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Controller {
    View_Home vH;
    View_Search vS;
    View_Add vA;
    View_update vU;
    public Controller(View_Home vH, View_Search vS, View_Add vA, View_update vU){
        this.vH = vH;
        this.vS = vS;
        this.vA = vA;
        this.vU = vU;
        vU.allListener(new ActionBtn());
        vH.allListener(new ActionBtn());
        vS.allListener(new ActionBtn());
        vA.allListener(new ActionBtn());
    }
    class ActionBtn implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
        //View Home Buttons
        if(e.getSource() == vH.btn_Add){
            vA.setVisible(true);
           // vH.setVisible(false);
        }
        if(e.getSource() == vH.btn_Search){
            String x = vH.search.getText();
            //vS.BookNo.setText(x);
            try {
            String url = "jdbc:derby://localhost:1527/research"; 
            Connection conn = DriverManager.getConnection(url,"root","1234"); 
            Statement st = conn.createStatement(); 
            
            String query ="SELECT * FROM archive WHERE id = '"+x+"'";
            
            ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                    String idno = rs.getString("id");
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    vS.BookNo.setText(idno);
                    if(idno.equals("")|| vS.BookNo.getText().equals("")){
                        vH.notif.setText("Table Data NoT Found!");
                    }
                    else{
                        vH.notif.setText("Table Data Displayed!");
                    }
                    vS.Title.setText(title);
                    vS.Author.setText(author);
                }
            
            conn.close();
             
        } catch (Exception ae)  {
            vH.notif.setText("Table Data NoT Displayed!");
        }
            
            vH.btn_Refresh.setEnabled(false);
            vS.setVisible(true);
        }
        if(e.getSource() == vH.btn_Update){
            String x = vH.update.getText();
            vU.idno.setText(x);
            try {
            String url = "jdbc:derby://localhost:1527/research"; 
            Connection conn = DriverManager.getConnection(url,"root","1234"); 
            Statement st = conn.createStatement(); 
            
            String query ="SELECT title, author FROM archive WHERE id = '"+x+"'";
            
            ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                    
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    vU.title.setText(title);
                    vU.author.setText(author);
                }
            vH.notif.setText("Table Data Displayed!");
            conn.close();
             
        } catch (Exception aee)  {
            vH.notif.setText("Table Data NoT Displayed!");
        }
            
            vH.btn_Refresh.setEnabled(false);
            vU.setVisible(true);
        }
        if(e.getSource() == vH.btn_Delete){
            DefaultTableModel tblModel = (DefaultTableModel)vH.Table.getModel();
            tblModel.setRowCount(0);
            String idno = vH.delete.getText();
            String retvalue=Model.delete_data(idno);
            vH.notif.setText(retvalue);
            vH.btn_Refresh.setEnabled(true);
            vH.delete.setText("");
        }
        if(e.getSource() == vH.btn_Refresh){
            try {
            String url = "jdbc:derby://localhost:1527/research"; 
            Connection conn = DriverManager.getConnection(url,"root","1234"); 
            Statement st = conn.createStatement(); 
            String query ="SELECT * FROM archive";
            ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                
                    String idno = rs.getString("id");
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    String tbData[] = {idno, title, author};
                    DefaultTableModel tblModel = (DefaultTableModel)vH.Table.getModel();
                    tblModel.addRow(tbData);
                
                }
            conn.close();
             
        } catch (Exception ae) {
            vH.notif.setText("Table Data NoT Refreshed!");
        }
            vH.notif.setText("Table Data Refreshed!");
            vH.btn_Refresh.setEnabled(false);
            
        }
        //View Home Buttons Closing
        //View Add Buttons
        if(e.getSource() == vA.btn_Submit){
            String a = vA.jTextField1.getText() ;
            String b = vA.jTextField2.getText();
            String c = vA.jTextField3.getText();
            String retvalue = Model.Add_Data(c, a, b);
            vH.notif.setText(retvalue);
            vA.setVisible(false);
            DefaultTableModel tblModel = (DefaultTableModel)vH.Table.getModel();
            tblModel.setRowCount(0);
            vH.btn_Refresh.setEnabled(true);
        }
        //View Add Buttons Closing
        //View Search Buttons
        if(e.getSource() == vS.btn_Back){
            vS.BookNo.setText("");
            vS.Author.setText("");
            vS.Title.setText("");
            vH.notif.setText("---------------------------");
            vS.setVisible(false);
            DefaultTableModel tblModel = (DefaultTableModel)vH.Table.getModel();
            tblModel.setRowCount(0);
            vH.search.setText("");
            vH.btn_Refresh.setEnabled(true);
        }
        //View Search Buttons Closing
        //View Update Buttons
        if(e.getSource() == vU.btn_Update){
            vU.setVisible(true);
            DefaultTableModel tblModel = (DefaultTableModel)vH.Table.getModel();
            tblModel.setRowCount(0);
            String idno = vU.idno.getText();
            String title = vU.title.getText();
            String author = vU.author.getText();
            String retvalue=Model.update_data(idno,title, author);
            vH.notif.setText(retvalue);
            
            vH.btn_Refresh.setEnabled(true);
            vU.setVisible(false);
            
            vH.update.setText("");
        }
        
        //View Update Buttons Closing
        }
    }
}
