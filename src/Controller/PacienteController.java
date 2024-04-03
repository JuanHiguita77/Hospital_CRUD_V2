package Controller;

import Entity.Paciente;
import Model.PacienteModel;

import javax.swing.JOptionPane;
import java.sql.Date;
import java.util.List;

public class PacienteController
{

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
        PacienteModel patientModel = new PacienteModel();

        JOptionPane.showMessageDialog(null, listAll(patientModel.list()));
    }

    public static String listAllPatients()
    {
        PacienteModel patientModel = new PacienteModel();
        String listPatients = "PATIENTS LIST \n";

        for (Object patient: patientModel.list()){

            Paciente patientNew = (Paciente) patient;
            listPatients += patientNew.toString() + "\n";
        }

        return listPatients;
    }

    public static void delete()
    {
        PacienteModel patientModel = new PacienteModel();

        String patientsList = listAllPatients();

        int id = Integer.parseInt(JOptionPane.showInputDialog(patientsList + "Input the Patient ID to delete"));

        //Buscamos primero si existe
        Paciente patient = patientModel.findById(id);

        if (patient == null)
        {
            JOptionPane.showInputDialog(null,"Unknown Patient");
        }
        else
        {
            int confirm = JOptionPane.showConfirmDialog(null,"Are you sure to delete? -- > " + patient.toString());

            if (confirm == 1)
            {
                JOptionPane.showMessageDialog(null,"Stopped!");
            }
            else
            {
                patientModel.delete(patient);
                JOptionPane.showMessageDialog(null, "Deleted sucessfully! --> " + patient.toString());
            }
        }
    }

    public static void update()
    {
        PacienteModel patientModel = new PacienteModel();

        String patientsList = listAllPatients();

        int idUpdated = Integer.parseInt( JOptionPane.showInputDialog(patientsList + "Enter Patient ID to edit"));

        Paciente patient = patientModel.findById(idUpdated);

        if (patient == null)
        {
            JOptionPane.showMessageDialog(null, "Unknown Patient");
        }
        else
        {
            String name = JOptionPane.showInputDialog("Input the patient name or leave default name", patient.getName());
            String surname = JOptionPane.showInputDialog("Input the patient surname or leave default", patient.getSurname());
            Date dateBirth = Date.valueOf( JOptionPane.showInputDialog("Input the patient date birth or leave default 'YYYY-MM-DD'", patient.getDate_birth()));
            String document = JOptionPane.showInputDialog("Input the patient document or leave default", patient.getDocument());

            patient.setName(name);
            patient.setSurname(surname);
            patient.setDate_birth(dateBirth);
            patient.setDocument(document);

            patientModel.update(patient);
        }
    }


    public static void create(){

        PacienteModel patientModel = new PacienteModel();
        Paciente patient = new Paciente();

        String name = JOptionPane.showInputDialog("Insert patient name");
        String surname = JOptionPane.showInputDialog("Insert patient surname");
        Date dateBirth = Date.valueOf(JOptionPane.showInputDialog("Insert patient date Birth 'YYYY-MM-DD'"));
        String document = JOptionPane.showInputDialog("Insert patient document");


        patient.setName(name);
        patient.setSurname(surname);
        patient.setDate_birth(dateBirth);
        patient.setDocument(document);

        //Pasamos el objeto a paciente
        patient = (Paciente) patientModel.create(patient);

        JOptionPane.showMessageDialog(null, patient.toString());
    }

    /*
    public static void findById()
    {
        PacienteModel patientModel = new PacienteModel();
        Paciente patient = new Paciente();

        int id_patient = Integer.parseInt(JOptionPane.showInputDialog("Input the patient ID to search"));

        Paciente patientReceived =  patientModel.findById(id_patient);

        if (patientReceived == null)
        {
            JOptionPane.showMessageDialog(null, "ID patient not Found");
        }
        else
        {
            patient.setId_paciente(patientReceived.getId_paciente());
            patient.setName(patientReceived.getName());
            patient.setSurname(patientReceived.getSurname());
            patient.setDate_birth(patientReceived.getDate_birth());
            patient.setDocument(patientReceived.getDocument());

            JOptionPane.showMessageDialog(null, patient.toString());
        }*/

    public static void findByDocument() {
        PacienteModel patientModel = new PacienteModel();
        Paciente patient = new Paciente();

        String patient_document = JOptionPane.showInputDialog("Input the patient Document to search");

        Paciente patientReceived = patientModel.findByDocument(patient_document);

        if (patientReceived == null) {
            JOptionPane.showMessageDialog(null, "Document´s patient not Found");
        } else {
            patient.setId_paciente(patientReceived.getId_paciente());
            patient.setName(patientReceived.getName());
            patient.setSurname(patientReceived.getSurname());
            patient.setDate_birth(patientReceived.getDate_birth());
            patient.setDocument(patientReceived.getDocument());

            JOptionPane.showMessageDialog(null, patient.toString());
        }
    }
}
