package Controller;


import Entity.Especialidad;
import Entity.Medico;
import Model.EspecialidadModel;
import Model.MedicoModel;
import utils.Utils;

import javax.swing.JOptionPane;
import java.sql.Date;
import java.util.List;

public class MedicoController
{
    public static MedicoModel instanceMedicModel()
    {
        return new MedicoModel();
    }

    //Listar factorizado para cualquier objeto de lista
    public static String listAll(List<Object> objectList)
    {
        String list = "--- MEDICS LIST --- \n";

        for (Object medic: objectList){
            Medico medicNew = (Medico) medic;
            list += medicNew.toString() + "\n";
        }

        return list;
    }

    public static void listMedics()
    {
        JOptionPane.showMessageDialog(null, listAll(instanceMedicModel().list()));
    }

    public static void delete()
    {
        Object[] medicSelected = Utils.listToarray(MedicoController.instanceMedicModel().list());

        Medico optionSelected = (Medico) JOptionPane.showInputDialog(null,
                "Select a Medic to delete",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                medicSelected,
                medicSelected[0]);

        instanceMedicModel().delete(optionSelected);
    }

    public static void update()
    {
        //Listas pasadas a array para el JOptionPane
        Object[] medics = Utils.listToarray(MedicoController.instanceMedicModel().list());
        Object[] specialities = Utils.listToarray(EspecialidadController.instanceSpecialityModel().list());

        Medico optionSelected = (Medico) JOptionPane.showInputDialog(null,
                "Select a Medic to Modify",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                medics,
                medics[0]);

        String name = JOptionPane.showInputDialog(null,"Input the medic name or leave default name", optionSelected.getName());
        String surname = JOptionPane.showInputDialog(null, "Input the medic surname or leave default", optionSelected.getSurname());

        Especialidad optionSelectedSpeciality = (Especialidad) JOptionPane.showInputDialog(null,
                "Select a Specility to Change",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                specialities,
                specialities[0]);

        optionSelected.setName(name);
        optionSelected.setSurname(surname);
        optionSelected.setFk_id_especialidad(optionSelected.getFk_id_especialidad());
        optionSelected.setSpeciality(optionSelectedSpeciality);

        instanceMedicModel().update(optionSelected);

    }


    public static void create()
    {

        String name = JOptionPane.showInputDialog("Insert medic name");
        String surname = JOptionPane.showInputDialog("Insert medic surname");

        Object[] specialitySelected = Utils.listToarray(EspecialidadController.instanceSpecialityModel().list());

        Especialidad optionSelected = (Especialidad) JOptionPane.showInputDialog(null,
                "Select a Speciality´s " + name,
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                specialitySelected,
                specialitySelected[0]);

        //Pasamos el objeto a medico
        instanceMedicModel().create(new Medico(name, surname, optionSelected.getId_especialidad(), optionSelected));
    }

    public static void findDoctorBySpecialization()
    {
        MedicoModel medicoModel = new MedicoModel();

        String specialityName = JOptionPane.showInputDialog("Type the speciality name to search All doctors");

        String listMedicsSpecialities = "LIST DOCTORS WITH " + specialityName + " FINDED \n";

        for (Object medic: medicoModel.findDoctorBySpecialization(specialityName))
        {
            Medico newMedic = (Medico) medic;
            listMedicsSpecialities += newMedic.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null ,listMedicsSpecialities);
    }
}
