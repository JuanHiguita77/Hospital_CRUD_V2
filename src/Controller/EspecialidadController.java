package Controller;

import Entity.Especialidad;
import Model.EspecialidadModel;

import javax.swing.JOptionPane;
import java.util.List;

public class EspecialidadController 
{
    //Listar factorizado para cualquier objeto de lista
    public static String listAll(List<Object> objectList)
    {
        String list = "--- MEDICS LIST --- \n";

        for (Object especiality: objectList){
            Especialidad especialityNew = (Especialidad) especiality;
            list += especialityNew.toString() + "\n";
        }

        return list;
    }

    public static void listEspeciality()
    {
        EspecialidadModel especialityModel = new EspecialidadModel();

        JOptionPane.showMessageDialog(null, listAll(especialityModel.list()));
    }

    public static String listAllMedics()
    {
        EspecialidadModel especialityModel = new EspecialidadModel();
        String listEspeciality = "MEDICS LIST \n";

        for (Object especiality: especialityModel.list()){

            Especialidad especialityNew = (Especialidad) especiality;
            listEspeciality += especialityNew.toString() + "\n";
        }

        return listEspeciality;
    }

    public static void delete()
    {
        EspecialidadModel especialityModel = new EspecialidadModel();

        String medicsList = listAllMedics();

        int id = Integer.parseInt(JOptionPane.showInputDialog(medicsList + "Input the Especiality ID to delete"));

        //Buscamos primero si existe
        Especialidad especiality = especialityModel.findById(id);

        if (especiality == null)
        {
            JOptionPane.showInputDialog(null,"Unknown Especiality");
        }
        else
        {
            int confirm = JOptionPane.showConfirmDialog(null,"Are you sure to delete? -- > " + especiality.toString());

            if (confirm == 1)
            {
                JOptionPane.showMessageDialog(null,"Stopped!");
            }
            else
            {
                especialityModel.delete(especiality);
                JOptionPane.showMessageDialog(null, "Deleted sucessfully! --> " + especiality.toString());
            }
        }
    }

    public static void update()
    {
        EspecialidadModel especialityModel = new EspecialidadModel();

        String medicsList = listAllMedics();

        int idUpdated = Integer.parseInt( JOptionPane.showInputDialog(medicsList + "Enter Especiality ID to edit"));

        Especialidad especiality = especialityModel.findById(idUpdated);

        if (especiality == null)
        {
            JOptionPane.showMessageDialog(null, "Unknown Especiality");
        }
        else
        {
            String name = JOptionPane.showInputDialog("Input the especiality name or leave default name", especiality.getName());
            String surname = JOptionPane.showInputDialog("Input the especiality surname or leave default", especiality.getSurname());
            int fk_id_especialidad = Integer.parseInt(JOptionPane.showInputDialog("Input the speciality ID or leave default", especiality.getFk_id_especialidad()));

            especiality.setName(name);
            especiality.setSurname(surname);
            especiality.setFk_id_especialidad(fk_id_especialidad);

            especialityModel.update(especiality);
        }
    }


    public static void create(){

        EspecialidadModel especialityModel = new EspecialidadModel();
        Especialidad especiality = new Especialidad();

        String name = JOptionPane.showInputDialog("Insert especiality name");
        String surname = JOptionPane.showInputDialog("Insert especiality surname");
        int fk_id_especialidad = Integer.parseInt(JOptionPane.showInputDialog("Insert Speciality ID"));

        especiality.setName(name);
        especiality.setSurname(surname);
        especiality.setFk_id_especialidad(fk_id_especialidad);

        //Pasamos el objeto a medico
        especiality = (Especialidad) especialityModel.create(especiality);

        JOptionPane.showMessageDialog(null, especiality.toString());
    }
}
