/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package michat;

import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ION Xtreme
 */
public class Negocio extends Thread  implements Runnable{
   private Conexion conexion;
   private Connection con=null;
   private Chat miChat;
   private Consultas consultas;
   
   
   public Negocio(Chat miChat){
        this.miChat=miChat;
        this.obtenerConexion();
        consultas= new Consultas();
        
   }
    
   public void DefinirUsuario(String usuario){
       consultas.setUsuario(usuario);
   }
   public void limpiar(){
         this.consultas.limpiar();
     }
     
     
    public void obtenerConexion(){
       this.conexion=new Conexion();
       this.con= conexion.connect();
     }
    
    public void consultarMensaje() throws SQLException{
        this.miChat.txtMensajes.setText(this.consultas.consultarMensaje(con));
        this.miChat.txtMensajes.setCaretPosition(this.miChat.txtMensajes.getDocument().getLength());
    }
    public void insertarMensaje(String mensaje) throws SQLException{
    Date hora= new Date();
    DateFormat formato= new SimpleDateFormat("HH:mm");
    
    Mensaje mens = new Mensaje(this.consultas.getUsuario(), mensaje, hora);
    this.consultas.insertarMensaje(con, mens);
    }
    
    @Override
    public void run() {
       try {
           while(true){
           this.consultarMensaje();
               sleep(1000);
           }
       } catch (SQLException ex) {
           Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
       } catch (InterruptedException ex) {
           Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
}
