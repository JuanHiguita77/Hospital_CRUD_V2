package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Esta clase se encargará de establecer y cerrar la conexión con la base de datos
public class ConfigDB {
    //Este atributo tendrá el estado de la conexión
    public static Connection objConnection = null;

    //Método para conectar la BD
    public  static  Connection openConnection(){

        try{
            //Llamamos el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Creamos las variables de conexión
            String url = "jdbc:mysql://uvts4jolc8l8hstj:MtcknBrdqq8yX9Mfwzop@bjdrfccv8z0nfpdkjnlt-mysql.services.clever-cloud.com:3306/bjdrfccv8z0nfpdkjnlt";
            String user = "uvts4jolc8l8hstj";
            String password = "MtcknBrdqq8yX9Mfwzop";

            //Establecer la conexión
            objConnection = (Connection) DriverManager.getConnection(url,user,password);
            System.out.println("Me conecté perfectamente!!!!");


        }//Fallo por driver
        catch (ClassNotFoundException e)
        {
            System.out.println("Error con el driver" + e.getMessage());
        }//Fallo por conexion
        catch (SQLException e)
        {
            System.out.println("Error al conectar base de datos " + e.getStackTrace());

        }

        return  objConnection;
    }

    //Método para finalizar una conexión
    public static void closeConnection(){
        try{
            //Si hay una conexión activa entonces la cierra
            if ( objConnection != null){
                objConnection.close();
                System.out.println("Se finalizó la conexión con éxito");
            }
        }catch (SQLException e){
            System.out.println("Error: "+ e.getMessage());
        }
    }
}
