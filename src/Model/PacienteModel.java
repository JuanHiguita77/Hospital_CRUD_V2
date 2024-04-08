package Model;

import Entity.Paciente;
import database.CRUD;
import database.ConfigDB;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteModel implements CRUD
{
    @Override
    public Object create(Object object)
    {

        Connection conexion = ConfigDB.openConnection();

        Paciente patient = (Paciente) object;

        try {
            //El id se pone automatico por la base de datos
            String sql = "INSERT INTO paciente (nombre, apellidos, fecha_nacimiento, documento_identidad) VALUES (?, ?, ?, ?);";

            PreparedStatement preparedStatement = conexion.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,patient.getName());
            preparedStatement.setString(2,patient.getSurname());
            preparedStatement.setDate(3,patient.getDate_birth());
            preparedStatement.setString(4,patient.getDocument());

            preparedStatement.execute();

            ResultSet respuesta = preparedStatement.getGeneratedKeys();

            //Le damos el id correspondiente a cada nueva entidad
            while (respuesta.next()){
                patient.setId_paciente(respuesta.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "New Patient Added.");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return patient;
    }

    @Override
    public List<Object> list()
    {

        Connection conexion = ConfigDB.openConnection();

        List<Object> patientList = new ArrayList<>();

        try
        {
            String sqlQuery = "SELECT * FROM paciente;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            ResultSet resultado = preparedStatement.executeQuery();

            while (resultado.next())
            {

                Paciente patient = new Paciente();

                patient.setId_paciente(resultado.getInt("id_paciente"));
                patient.setName(resultado.getString("nombre"));
                patient.setSurname(resultado.getString("apellidos"));
                patient.setDate_birth(resultado.getDate("fecha_nacimiento"));
                patient.setDocument(resultado.getString("documento_identidad"));

                patientList.add(patient);
            }
        }
        catch (SQLException error)
        {
            JOptionPane.showMessageDialog(null, "Model Error in list metod" + error.getMessage());
        }

        ConfigDB.closeConnection();

        return patientList;
    }

    @Override
    public boolean delete(Object object)
    {
        Paciente patient = (Paciente) object;

        Connection conexion = ConfigDB.openConnection();

        boolean isDeleted = false;

        try
        {
            String sqlQuery = "DELETE FROM paciente WHERE id_paciente = ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, patient.getId_paciente());

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
            JOptionPane.showMessageDialog(null, "Error in delete Model: " +  e.getMessage());
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
            Paciente patient = (Paciente) object;

            String sqlQuery = "UPDATE paciente SET nombre = ?, apellidos = ?, fecha_nacimiento = ?, documento_identidad = ? WHERE id_paciente = ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            //Se le pasa posicion y dato al statement
            preparedStatement.setInt(5, patient.getId_paciente());
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getSurname());
            preparedStatement.setDate(3, patient.getDate_birth());
            preparedStatement.setString(4, patient.getDocument());

            int resultado = preparedStatement.executeUpdate();

            if (resultado > 0)
            {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Updated Successfully");
            }

        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null , "Model Patient Updating Error --> " + e.getMessage());
        }
        finally
        {
            ConfigDB.closeConnection();
        }

        return isUpdated;
    }

    //No viene del CRUD, es propio
    public Paciente findByDocument(String document)
    {
        Connection conexion = ConfigDB.openConnection();

        //Global
        Paciente patient = null;

        try
        {
            String sqlQuery = "SELECT * FROM paciente WHERE documento_identidad = ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            //Le pasamos el ID al query
            preparedStatement.setString(1, document);

            ResultSet resultado = preparedStatement.executeQuery();

            //Asignamos los datos encontrados
            if(resultado.next())
            {
                patient = new Paciente();

                patient.setId_paciente(resultado.getInt("id_paciente"));
                patient.setName(resultado.getString("nombre"));
                patient.setSurname(resultado.getString("apellidos"));
                patient.setDate_birth(resultado.getDate("fecha_nacimiento"));
                patient.setDocument(resultado.getString("documento_identidad"));

            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Patient model error" + e.getMessage());
        }

        ConfigDB.closeConnection();

        return patient;
    }

    public Paciente findById(int id_patient)
    {
        Connection conexion = ConfigDB.openConnection();

        //Global
        Paciente patient = null;

        try
        {
            String sqlQuery = "SELECT * FROM paciente WHERE id_paciente = ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            //Le pasamos el ID al query
            preparedStatement.setInt(1, id_patient);

            ResultSet resultado = preparedStatement.executeQuery();

            //Asignamos los datos encontrados
            if(resultado.next())
            {
                patient = new Paciente();

                patient.setId_paciente(resultado.getInt("id_paciente"));
                patient.setName(resultado.getString("nombre"));
                patient.setSurname(resultado.getString("apellidos"));
                patient.setDate_birth(resultado.getDate("fecha_nacimiento"));
                patient.setDocument(resultado.getString("documento_identidad"));

            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Patient model error" + e.getMessage());
        }

        ConfigDB.closeConnection();

        return patient;
    }
}
