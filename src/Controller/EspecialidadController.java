package Controller;

import Entity.Especialidad;
import Model.EspecialidadModel;
import utils.Utils;

import javax.swing.JOptionPane;
import java.util.List;

public class EspecialidadController 
{

    //Creamos la instancia del Modelo de donde sacaremos los metodos necesarios
    public static EspecialidadModel instanceSpecialityModel()
    {
        return new EspecialidadModel();
    }

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
        JOptionPane.showMessageDialog(null, listAll(instanceSpecialityModel().list()));
    }

    public static void delete()
    {
        Object[] deleteOptions = Utils.listToarray(instanceSpecialityModel().list());

        Especialidad optionSelected = (Especialidad) JOptionPane.showInputDialog(null,"Select a Speciality", "",
            JOptionPane.QUESTION_MESSAGE,
            null,
            deleteOptions,
            deleteOptions[0]);

        instanceSpecialityModel().delete(optionSelected);

        Especialidad speciality = instanceSpecialityModel().findById(optionSelected.getId_especialidad());
        JOptionPane.showMessageDialog(null, "Deleted sucessfully! --> " + speciality.toString());
    }

    public static void update()
    {
        Object[] deleteOptions = Utils.listToarray(instanceSpecialityModel().list());


        Especialidad optionSelected = (Especialidad) JOptionPane.showInputDialog(null,
                "Select a Speciality To update",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                deleteOptions,
                deleteOptions[0]);

        String name = JOptionPane.showInputDialog(null, "Input the new Name", optionSelected.getName());
        String description = JOptionPane.showInputDialog("Input the new Descripton", optionSelected.getDescription());

        //Usando la instancia del modelo y los metodos, creamos el objeto que se espera pasandole los datos correspondientes
        instanceSpecialityModel().update(new Especialidad(name, description));
    }

    public static void create(){

        String name = JOptionPane.showInputDialog("Insert speciality name");
        String description = JOptionPane.showInputDialog("Insert speciality Description");

        //Usando la instancia del modelo y los metodos, creamos el objeto que se espera pasandole los datos correspondientes
        instanceSpecialityModel().create(new Especialidad(name, description));

    }


}
