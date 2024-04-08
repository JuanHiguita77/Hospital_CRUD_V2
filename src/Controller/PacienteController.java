package Controller;

import Entity.Medico;
import Entity.Paciente;
import Model.PacienteModel;
import utils.Utils;

import javax.swing.JOptionPane;
import java.sql.Date;
import java.util.List;

public class PacienteController
{

    public static PacienteModel instancePatientModel()
    {
        return new PacienteModel();
    }

    //Listar factorizado para cualquier objeto de lista
    public static String listAll(List<Object> objectList)
    {
        String list = "--- PATIENTS LIST --- \n";

        for (Object patient: objectList){
            Paciente patientNew = (Paciente) patient;
            list += patientNew.toString() + "\n";
        }

        return list;
    }

    public static void listPatients()
    {
        JOptionPane.showMessageDialog(null, listAll(instancePatientModel().list()));
    }

    public static void delete()
    {
        Object[] patientOptions = Utils.listToarray(instancePatientModel().list());

        Paciente optionSelected = (Paciente) JOptionPane.showInputDialog(null,
                "Select Patient to Delete",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                patientOptions,
                patientOptions[0]);

        instancePatientModel().delete(optionSelected);
    }

    public static void update()
    {
        Object[] patientOptions = Utils.listToarray(instancePatientModel().list());

        Paciente optionSelected = (Paciente) JOptionPane.showInputDialog(null,
                "Select Patient to Modify",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                patientOptions,
                patientOptions[0]);

        optionSelected.setName(JOptionPane.showInputDialog(null, "Input New Name or leave defult", optionSelected.getName()));
        optionSelected.setSurname(JOptionPane.showInputDialog(null, "Input New Surname or leave defult", optionSelected.getSurname()));
        optionSelected.setDate_birth(Date.valueOf(JOptionPane.showInputDialog(null, "Input New Birth Date or leave defult", optionSelected.getDate_birth())));
        optionSelected.setDocument(JOptionPane.showInputDialog(null, "Input New Document or leave defult", optionSelected.getDocument()));

        instancePatientModel().update(optionSelected);
    }


    public static void create()
    {
        String name = JOptionPane.showInputDialog("Insert patient name");
        String surname = JOptionPane.showInputDialog("Insert patient surname");
        Date dateBirth = Date.valueOf(JOptionPane.showInputDialog("Insert patient date Birth 'YYYY-MM-DD'"));
        String document = JOptionPane.showInputDialog("Insert patient document");

        instancePatientModel().create(new Paciente(name, surname, dateBirth, document));
    }

    public static void findByDocument()
    {
        Paciente patient = new Paciente();

        String patient_document = JOptionPane.showInputDialog("Input the patient Document to search");

        Paciente patientReceived = instancePatientModel().findByDocument(patient_document);

        if (patientReceived == null)
        {
            JOptionPane.showMessageDialog(null, "Document´s Patient not Found");
        }
        else
        {
            patient.setId_paciente(patientReceived.getId_paciente());
            patient.setName(patientReceived.getName());
            patient.setSurname(patientReceived.getSurname());
            patient.setDate_birth(patientReceived.getDate_birth());
            patient.setDocument(patientReceived.getDocument());

            JOptionPane.showMessageDialog(null, patient.toString());
        }
    }
}
