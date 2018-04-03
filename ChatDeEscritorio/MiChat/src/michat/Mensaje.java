/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package michat;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author ION Xtreme
 */
public class Mensaje {
    
    private String Usuario="";
    private String mensaje="";
    private Timestamp fecha=null;
    
    public Mensaje(String usuario,String mensaje, Date fecha){
        
        this.Usuario=usuario;
        this.mensaje=mensaje;
        this.fecha=new Timestamp(fecha.getTime());
        
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
    
    
}
