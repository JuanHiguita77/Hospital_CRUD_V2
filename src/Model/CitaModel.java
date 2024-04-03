package Model;

import Entity.Cita;
import database.CRUD;
import database.ConfigDB;

import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaModel implements CRUD
{
    @Override
    public Object create(Object object) {

        Connection conexion = ConfigDB.openConnection();

        Cita cite = (Cita) object;

        try {
            //El id se pone automatico por la base de datos
            String sql = "INSERT INTO cita (fk_id_paciente, fk_id_medico, fecha_cita, hora_cita, motivo) VALUES (?, ?, ?, ?, ?);";

            PreparedStatement preparedStatement = conexion.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1,cite.getId_patient());
            preparedStatement.setInt(2,cite.getId_medic());
            preparedStatement.setDate(3,cite.getCite_date());
            preparedStatement.setTime(4,cite.getCite_hour());
            preparedStatement.setString(5,cite.getReason());

            preparedStatement.execute();

            ResultSet respuesta = preparedStatement.getGeneratedKeys();

            //Le damos el id correspondiente a cada nueva entidad
            while (respuesta.next()){
                cite.setId_patient(respuesta.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "New Cite Added.");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return cite;
    }

    @Override
    public List<Object> list() {

        Connection conexion = ConfigDB.openConnection();

        List<Object> citeList = new ArrayList<>();

        try
        {
            String sqlQuery = "SELECT *, paciente.nombre AS nombre_paciente, medico.nombre AS nombre_medico FROM cita JOIN paciente ON cita.fk_id_paciente = paciente.id_paciente JOIN medico ON cita.fk_id_medico = medico.id_medico;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            ResultSet resultado = preparedStatement.executeQuery();

            while (resultado.next())
            {

                Cita cite = new Cita();

                cite.setId_cite(resultado.getInt("id_cita"));
                cite.setId_patient(resultado.getInt("fk_id_paciente"));
                cite.setId_medic(resultado.getInt("fk_id_medico"));
                cite.setCite_date(resultado.getDate("fecha_cita"));
                cite.setCite_hour(resultado.getTime("hora_cita"));
                cite.setReason(resultado.getString("motivo"));
                cite.setPatient(resultado.getString("nombre_paciente"));
                cite.setMedic(resultado.getString("nombre_medico"));

                citeList.add(cite);
            }
        }
        catch (SQLException error)
        {
            JOptionPane.showMessageDialog(null, "Model Error in list metod" + error.getMessage());
        }

        ConfigDB.closeConnection();

        return citeList;
    }

    @Override
    public boolean delete(Object object)
    {
        Cita cite = (Cita) object;

        Connection  conexion = ConfigDB.openConnection();

        boolean isDeleted = false;

        try
        {
            String sqlQuery = "DELETE FROM cita WHERE id_cita = ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, cite.getId_cite());

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
            JOptionPane.showMessageDialog(null, "Error deleting in Cite Model: " +  e.getMessage());
        }

        ConfigDB.closeConnection();

        return isDeleted;
    }

    @Override
    public boolean update(Object object) {

        Connection conexion = ConfigDB.openConnection();

        boolean isUpdated = false;

        try
        {
            Cita cite = (Cita) object;

            String sqlQuery = "UPDATE cita SET fk_id_paciente = ?, fk_id_medico = ?, fecha_cita = ?, hora_cita = ?, motivo = ? WHERE id_cita = ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);

            //Se le pasa posicion y dato al statement

            preparedStatement.setInt(1, cite.getId_patient());
            preparedStatement.setInt(2, cite.getId_medic());
            preparedStatement.setDate(3, cite.getCite_date());
            preparedStatement.setTime(4, cite.getCite_hour());
            preparedStatement.setString(5, cite.getReason());
            preparedStatement.setInt(6, cite.getId_cite());

            int resultado = preparedStatement.executeUpdate();

            if (resultado > 0)
            {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Updated Successfully");
            }

        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null , "Model Cite Updating Error --> " + e.getMessage());
        }
        finally
        {
            ConfigDB.closeConnection();
        }

        return isUpdated;
    }

    public Cita findById(int id_cite)
    {
        Connection conexion = ConfigDB.openConnection();

        //Global
        Cita cite = null;

        try
        {
            String sqlQuery = "SELECT * FROM cita WHERE id_cita = ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            //Le pasamos el ID al query
            preparedStatement.setInt(1, id_cite);

            ResultSet resultado = preparedStatement.executeQuery();

            //Asignamos los datos encontrados
            if(resultado.next())
            {
                cite = new Cita();

                cite.setId_cite(resultado.getInt("id_cita"));
                cite.setId_patient(resultado.getInt("fk_id_paciente"));
                cite.setId_medic(resultado.getInt("fk_id_medico"));
                cite.setCite_date(resultado.getDate("fecha_cita"));
                cite.setCite_hour(resultado.getTime("hora_cita"));
                cite.setReason(resultado.getString("motivo"));

            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Cite model error" + e.getMessage());
        }

        ConfigDB.closeConnection();

        return cite;
    }

    public Cita findByDate(Date dateCite)
    {
        Connection conexion = ConfigDB.openConnection();

        //Global
        Cita cite = null;

        try
        {
            String sqlQuery = "SELECT *, paciente.nombre AS nombre_paciente, medico.nombre AS nombre_medico FROM cita JOIN paciente ON cita.fk_id_paciente = paciente.id_paciente JOIN medico ON cita.fk_id_medico = medico.id_medico WHERE fecha_cita = ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            //Le pasamos el ID al query
            preparedStatement.setDate(1, dateCite);

            ResultSet resultado = preparedStatement.executeQuery();

            //Asignamos los datos encontrados
            if(resultado.next())
            {
                cite = new Cita();

                cite.setId_cite(resultado.getInt("id_cita"));
                cite.setId_patient(resultado.getInt("fk_id_paciente"));
                cite.setId_medic(resultado.getInt("fk_id_medico"));
                cite.setCite_date(resultado.getDate("fecha_cita"));
                cite.setCite_hour(resultado.getTime("hora_cita"));
                cite.setReason(resultado.getString("motivo"));
                cite.setPatient(resultado.getString("nombre_paciente"));
                cite.setMedic(resultado.getString("nombre_medico"));

            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Cite date search model error" + e.getMessage());
        }

        ConfigDB.closeConnection();

        return cite;

    }

}
