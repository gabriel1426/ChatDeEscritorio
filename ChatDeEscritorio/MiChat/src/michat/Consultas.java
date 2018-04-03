/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package michat;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ION Xtreme
 */
public class Consultas {

 
   private String usuario="";
   private int cont=0;
   private int ultimo=0;
   
   
   public void limpiar(){
       this.cont=0;
   }
    
    public String consultarMensaje(Connection conexion) throws SQLException{
        
        Statement sm = conexion.createStatement();
        String fecha;
        
        if (this.cont==0){
        

         ResultSet rs2= sm.executeQuery("SELECT MAX(id) from mensajes");
         
         while(rs2.next()){
             if(rs2.getString(1)!=null){
             ultimo=Integer.parseInt(rs2.getString(1));
             
             }
         }
         this.cont=1;
         }
        
         ResultSet rs= sm.executeQuery("select * from mensajes where id >"+ultimo);
         String mens="";
         
         while(rs.next()){
             
             if(rs.getString(2).equalsIgnoreCase(this.usuario)){
             fecha=rs.getString(4).split(" ")[1].split(":")[0]+":";
             fecha+=rs.getString(4).split(" ")[1].split(":")[1];
                
             mens+="<div style=\"border:3px double #DCDCDC;padding:1.2em;background-color:#E8FFE8;border-radius:15px;-khtml-border-radius:15px;-moz-border-radius:15px;-webkit-border-radius:15px;\">"+
                     "<div style=\"text-align:right;\" ><font  size=\'5\' color=\'green\'>"+rs.getString(2)+":        "+"</font>";
             mens+="<font size=\'5\' color=\'blue\'>"+rs.getString(3)+"</font><br>"+fecha+"</div></div>";
             
             }else {
                 fecha=rs.getString(4).split(" ")[1].split(":")[0]+":";
             fecha+=rs.getString(4).split(" ")[1].split(":")[1];
             mens+="<div style=\"border:3px double #DCDCDC;padding:1.2em;background-color:#E8EEFF;border-radius:15px;-khtml-border-radius:15px;-moz-border-radius:15px;-webkit-border-radius:15px;\" ><div><font  size=\'5\' color=\'green\'>"+rs.getString(2)+":        "+"</font>";
             mens+="<font size=\'5\' color=\'blue\'>"+rs.getString(3)+"</font><br>";
             mens+=fecha+"</div></div>";
             }
          
             
         }
//         this.miChat.txtMensajes.setText(mens);
        
        
        return mens;
        
      
    }
    public void insertarMensaje(Connection conexion,Mensaje mensaje) throws SQLException{
       
       PreparedStatement pp= conexion.prepareStatement("Insert into mensajes(emisor,mensaje,hora) VALUES ('"+mensaje.getUsuario()+"','"+mensaje.getMensaje()+"','"+mensaje.getFecha()+"')");
       pp.executeUpdate();
    }
    
    
    
     public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }
    
    
    
}
