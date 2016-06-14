
package cursandofuerte;

import java.sql.*;
/**
 *
 * Se hace la conexión a la base de datos
 */
public class ConexionBD {
    Connection cn;
    
    public Connection conexion(){
     try{   
        Class.forName("com.mysql.jdbc.Driver");
        cn = DriverManager.getConnection("jdbc:mysql://localhost/cursandoFuerte","root","");
   
     }catch(Exception e){
        System.out.println(e.getMessage());
    }return cn;
        
    
    }
    Statement createStatement(){
        throw new UnsupportedOperationException("No soportado");
    }
}
