package Model;

import Entity.Cita;
import Entity.Especialidad;
import Entity.Medico;
import database.CRUD;
import database.ConfigDB;

import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoModel implements CRUD
{
    @Override
    public Object create(Object object)
    {

        Connection conexion = ConfigDB.openConnection();

        Medico medic = (Medico) object;

        try {
            //El id se pone automatico por la base de datos
            String sql = "INSERT INTO medico (nombre, apellidos, fk_id_especialidad) VALUES (?, ?, ?);";

            PreparedStatement preparedStatement = conexion.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,medic.getName());
            preparedStatement.setString(2,medic.getSurname());
            preparedStatement.setInt(3,medic.getFk_id_especialidad());

            preparedStatement.execute();

            ResultSet respuesta = preparedStatement.getGeneratedKeys();

            //Le damos el id correspondiente a cada nueva entidad
            while (respuesta.next()){
                medic.setId_medico(respuesta.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "New Medic Added.");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return medic;
    }

    @Override
    public List<Object> list()
    {

        Connection conexion = ConfigDB.openConnection();

        List<Object> medicList = new ArrayList<>();

        try
        {
            String sqlQuery = "SELECT * FROM medico JOIN especialidad ON especialidad.id_especialidad = medico.fk_id_especialidad;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            ResultSet resultado = preparedStatement.executeQuery();

            while (resultado.next())
            {

                Medico medic = new Medico();
                Especialidad speciality = new Especialidad();

                medic.setId_medico(resultado.getInt("medico.id_medico"));
                medic.setName(resultado.getString("medico.nombre"));
                medic.setSurname(resultado.getString("medico.apellidos"));
                medic.setFk_id_especialidad(resultado.getInt("medico.fk_id_especialidad"));

                speciality.setId_especialidad(resultado.getInt("especialidad.id_especialidad"));
                speciality.setName(resultado.getString("especialidad.nombre"));
                speciality.setDescription(resultado.getString("especialidad.descripcion"));

                medic.setSpeciality(speciality);
                medicList.add(medic);
            }
        }
        catch (SQLException error)
        {
            JOptionPane.showMessageDialog(null, "Model Error in list metod" + error.getMessage());
        }

        ConfigDB.closeConnection();

        return medicList;
    }

    @Override
    public boolean delete(Object object)
    {
        Medico medic = (Medico) object;

        Connection  conexion = ConfigDB.openConnection();

        boolean isDeleted = false;

        try
        {
            String sqlQuery = "DELETE FROM medico WHERE id_medico = ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, medic.getId_medico());

            if (preparedStatement.executeUpdate() > 0)
            {
                isDeleted = true;
            }

            JOptionPane.showMessageDialog(null, "Succesfull Delete");

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error deleting in Medic Model: " +  e.getMessage());
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
            Medico medic = (Medico) object;

            String sqlQuery = "UPDATE medico SET nombre = ?, apellidos = ?, fk_id_especialidad = ? WHERE id_medico = ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            System.out.println("Id medico actual: " + medic.getId_medico());
            //Se le pasa posicion y dato al statement
            preparedStatement.setString(1, medic.getName());
            preparedStatement.setString(2, medic.getSurname());
            preparedStatement.setInt(3, medic.getFk_id_especialidad());
            preparedStatement.setInt(4, medic.getId_medico());

            int resultado = preparedStatement.executeUpdate();

            if (resultado > 0)
            {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Updated Successfully");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Dont Updated X.x");
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null , "Model Medic Updating Error --> " + e.getMessage());
        }
        finally
        {
            ConfigDB.closeConnection();
        }

        return isUpdated;
    }

   /* public Medico findById(int id_medic)
    {
        Connection conexion = ConfigDB.openConnection();

        //Global
        Medico medic = null;

        try
        {
            String sqlQuery = "SELECT * FROM medico WHERE id_medico = ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            //Le pasamos el ID al query
            preparedStatement.setInt(1, id_medic);

            ResultSet resultado = preparedStatement.executeQuery();

            //Asignamos los datos encontrados
            if(resultado.next())
            {
                medic = new Medico();

                medic.setId_medico(resultado.getInt("id_medico"));
                medic.setName(resultado.getString("nombre"));
                medic.setSurname(resultado.getString("apellidos"));
                medic.setFk_id_especialidad(resultado.getInt("fk_id_especialidad"));

            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Medic model error" + e.getMessage());
        }

        ConfigDB.closeConnection();

        return medic;
    }*/

    public List<Object> findDoctorBySpecialization(String speciality)
    {
        Connection conexion = ConfigDB.openConnection();

        List<Object> listSpecilitiesDoctors = new ArrayList<>();

        //Global
        Medico medic = null;
        Especialidad specialityObject = null;

        try
        {
                String sqlQuery = "SELECT *, especialidad.nombre FROM medico JOIN especialidad ON medico.fk_id_especialidad = especialidad.id_especialidad WHERE especialidad.nombre LIKE ?;";

                PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

                //Le pasamos el ID al query
                preparedStatement.setString(1, "%" + speciality + "%");

                ResultSet resultado = preparedStatement.executeQuery();

                //Asignamos los datos encontrados
                while(resultado.next())
                {
                    medic = new Medico();
                    specialityObject = new Especialidad();

                    medic.setId_medico(resultado.getInt("id_medico"));
                    medic.setName(resultado.getString("nombre"));
                    medic.setSurname(resultado.getString("apellidos"));
                    medic.setFk_id_especialidad(resultado.getInt("fk_id_especialidad"));

                    specialityObject.setName(resultado.getString("especialidad.nombre"));
                    medic.setSpeciality(specialityObject);

                    listSpecilitiesDoctors.add(medic);
                }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Doctor specialities search model error" + e.getMessage());
        }

        ConfigDB.closeConnection();

        return listSpecilitiesDoctors;

    }
}
