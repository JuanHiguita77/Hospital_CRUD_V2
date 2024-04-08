package Model;

import Entity.Especialidad;
import database.CRUD;
import database.ConfigDB;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadModel implements CRUD
{
    @Override
    public Object create(Object object)
    {

        Connection conexion = ConfigDB.openConnection();

        Especialidad especiality = (Especialidad) object;

        try {
            //El id se pone automatico por la base de datos
            String sql = "INSERT INTO especialidad (nombre, descripcion, id_especialidad) VALUES (?, ?, ?);";

            PreparedStatement preparedStatement = conexion.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,especiality.getName());
            preparedStatement.setString(2,especiality.getDescription());
            preparedStatement.setInt(3,especiality.getId_especialidad());

            preparedStatement.execute();

            ResultSet respuesta = preparedStatement.getGeneratedKeys();

            //Le damos el id correspondiente a cada nueva entidad
            while (respuesta.next()){
                especiality.setId_especialidad(respuesta.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "New Especiality Added.");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return especiality;
    }

    @Override
    public List<Object> list()
    {

        Connection conexion = ConfigDB.openConnection();

        List<Object> especialityList = new ArrayList<>();

        try
        {
            String sqlQuery = "SELECT * FROM especialidad;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            ResultSet resultado = preparedStatement.executeQuery();

            while (resultado.next())
            {

                Especialidad especiality = new Especialidad();
                
                especiality.setName(resultado.getString("nombre"));
                especiality.setDescription(resultado.getString("descripcion"));
                especiality.setId_especialidad(resultado.getInt("id_especialidad"));

                especialityList.add(especiality);
            }
        }
        catch (SQLException error)
        {
            JOptionPane.showMessageDialog(null, "Model Error in list metod" + error.getMessage());
        }

        ConfigDB.closeConnection();

        return especialityList;
    }

    @Override
    public boolean delete(Object object)
    {
        Especialidad especiality = (Especialidad) object;

        Connection  conexion = ConfigDB.openConnection();

        boolean isDeleted = false;

        try
        {
            String sqlQuery = "DELETE FROM especialidad WHERE id_especialidad = ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, especiality.getId_especialidad());

            int resultadoFilasAfectadas = preparedStatement.executeUpdate();

            if (resultadoFilasAfectadas > 0)
            {
                isDeleted = true;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Dont deleted x.x");
            }

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error deleting in Especiality Model: " +  e.getMessage());
        }

        ConfigDB.closeConnection();

        return isDeleted;
    }

    @Override
    public boolean update(Object object)
    {

        Connection conexion = ConfigDB.openConnection();

        boolean isUpdated = false;

        try
        {
            Especialidad especiality = (Especialidad) object;

            String sqlQuery = "UPDATE especialidad SET nombre = ?, descripcion = ? WHERE id_especialidad = ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            //Se le pasa posicion y dato al statement
            preparedStatement.setString(1, especiality.getName());
            preparedStatement.setString(2, especiality.getDescription());
            preparedStatement.setInt(3, especiality.getId_especialidad());

            int resultado = preparedStatement.executeUpdate();

            if (resultado > 0)
            {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Updated Successfully");
            }

        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null , "Model Especiality Updating Error --> " + e.getMessage());
        }
        finally
        {
            ConfigDB.closeConnection();
        }

        return isUpdated;
    }

    public Especialidad findById(int id_especialidad)
    {
        Connection conexion = ConfigDB.openConnection();

        //Global
        Especialidad especiality = null;

        try
        {
            String sqlQuery = "SELECT * FROM especialidad WHERE id_especialidad = ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            //Le pasamos el ID al query
            preparedStatement.setInt(1, id_especialidad);

            ResultSet resultado = preparedStatement.executeQuery();

            //Asignamos los datos encontrados
            if(resultado.next())
            {
                especiality = new Especialidad();

                especiality.setId_especialidad(resultado.getInt("id_especialidad"));
                especiality.setName(resultado.getString("nombre"));
                especiality.setDescription(resultado.getString("descripcion"));
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Especiality model error" + e.getMessage());
        }

        ConfigDB.closeConnection();

        return especiality;
    }
}
