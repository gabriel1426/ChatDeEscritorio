/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package michat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ION Xtreme
 */
public class Conexion {
    
    private  Connection conexion=null;
    
    
    public  Connection connect(){
String url = "jdbc:mysql://sandbox2.ufps.edu.co/1151211";
String user = "1151211";
String pass = "1151211";




try{
        this.conexion = DriverManager.getConnection(url, user,pass);
        
        
}catch(SQLException e){
System.out.println(e.getMessage());
}

return this.conexion;
}
    
}
