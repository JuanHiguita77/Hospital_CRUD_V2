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
        String list = "--- ESPECIALITIES LIST --- \n";

        for (Object speciality: objectList){
            Especialidad especialityNew = (Especialidad) speciality;
            list += especialityNew.toString() + "\n";
        }

        return list;
    }

    public static void listSpeciality()
    {
        EspecialidadModel specialityModel = new EspecialidadModel();

        JOptionPane.showMessageDialog(null, listAll(specialityModel.list()));
    }

    public static String listAllSpecialities()
    {
        EspecialidadModel specialityModel = new EspecialidadModel();
        String listSpeciality = "ESPECIALITIES LIST \n";

        for (Object speciality: specialityModel.list()){

            Especialidad specialityNew = (Especialidad) speciality;
            listSpeciality += specialityNew.toString() + "\n";
        }

        //Devuelve un string
        return listSpeciality;
    }

    public static void delete()
    {
        EspecialidadModel specialityModel = new EspecialidadModel();

        String specialitiesList = listAllSpecialities();

        int id = Integer.parseInt(JOptionPane.showInputDialog(specialitiesList + "Input the Speciality ID to delete"));

        //Buscamos primero si existe
        Especialidad speciality = specialityModel.findById(id);

        if (speciality == null)
        {
            JOptionPane.showInputDialog(null,"Unknown Speciality");
        }
        else
        {
            int confirm = JOptionPane.showConfirmDialog(null,"Are you sure to delete? -- > " + speciality.toString());

            if (confirm == 1)
            {
                JOptionPane.showMessageDialog(null,"Stopped!");
            }
            else
            {
                specialityModel.delete(speciality);
                JOptionPane.showMessageDialog(null, "Deleted sucessfully! --> " + speciality.toString());
            }
        }
    }

    public static void update()
    {
        EspecialidadModel specialityModel = new EspecialidadModel();

        String specialitiesList = listAllSpecialities();

        int idUpdated = Integer.parseInt( JOptionPane.showInputDialog(specialitiesList + "Enter Speciality ID to edit"));

        Especialidad speciality = specialityModel.findById(idUpdated);

        if (speciality == null)
        {
            JOptionPane.showMessageDialog(null, "Unknown Speciality");
        }
        else
        {
            String name = JOptionPane.showInputDialog("Input the speciality name or leave default name", speciality.getName());
            String description = JOptionPane.showInputDialog("Input the speciality surname or leave default", speciality.getDescription());

            speciality.setName(name);
            speciality.setDescription(description);

            specialityModel.update(speciality);
        }
    }


    public static void create(){

        EspecialidadModel specialityModel = new EspecialidadModel();
        Especialidad speciality = new Especialidad();

        String name = JOptionPane.showInputDialog("Insert speciality name");
        String descripcion = JOptionPane.showInputDialog("Insert speciality Description");

        speciality.setName(name);
        speciality.setDescription(descripcion);

        //Pasamos el objeto a medico
        speciality = (Especialidad) specialityModel.create(speciality);

        JOptionPane.showMessageDialog(null, speciality.toString());
    }
}
