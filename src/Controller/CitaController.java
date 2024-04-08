package Controller;

import Entity.Cita;
import Entity.Medico;
import Entity.Paciente;
import Model.CitaModel;
import utils.Utils;

import javax.swing.JOptionPane;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class CitaController 
{
    public static CitaModel instanceModelCite()
    {
        return new CitaModel();
    }

    //Listar factorizado para cualquier objeto de lista
    public static String listAll(List<Object> objectList)
    {
        String list = "--- CITES LIST --- \n";

        for (Object cite: objectList){
            Cita citeNew = (Cita) cite;
            list += citeNew.toString() + "\n";
        }

        return list;
    }

    public static void listCites()
    {
        JOptionPane.showMessageDialog(null, listAll(instanceModelCite().list()));
    }

    public static void delete()
    {
        Object[] citaList =  Utils.listToarray(instanceModelCite().list());

        Cita optionSelectedPatient = (Cita) JOptionPane.showInputDialog(null,
                "Select Cite to Delete",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                citaList,
                citaList[0]);

        instanceModelCite().delete(optionSelectedPatient);
    }

    public static void update()
    {
        Object[] patientsList = Utils.listToarray(PacienteController.instancePatientModel().list());
        Object[] medicsList = Utils.listToarray(MedicoController.instanceMedicModel().list());
        Object[] citeList = Utils.listToarray(CitaController.instanceModelCite().list());

        Cita citeSelected = (Cita) JOptionPane.showInputDialog(null,
                "Select a Cite to Update",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                citeList,
                citeList[0]);

        citeSelected.setPatient((Paciente) JOptionPane.showInputDialog(null,
                "Select Patient to Change at Cite",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                patientsList,
                patientsList[0]));

        citeSelected.setMedic((Medico) JOptionPane.showInputDialog(null,
                "Select Medic to Change at Cite",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                medicsList,
                medicsList[0]));

        citeSelected.setId_cite(citeSelected.getId_cite());

        citeSelected.setId_patient(citeSelected.getPatient().getId_paciente());
        citeSelected.setId_medic(citeSelected.getMedic().getId_medico());

        citeSelected.setCite_date(Date.valueOf( JOptionPane.showInputDialog("Input the Date´s cite 'YYYY-MM-DD' or leave default", citeSelected.getCite_date ())));
        citeSelected.setCite_hour(Time.valueOf( JOptionPane.showInputDialog("Input the Hour´s cite 'HH:MM:SS' or leave default", citeSelected.getCite_hour())));

        citeSelected.setReason(JOptionPane.showInputDialog("Input the Reason´s cite or leave default", citeSelected.getReason()));

        instanceModelCite().update(citeSelected);
    }

    public static void create()
    {
        Object[] patientsList = Utils.listToarray(PacienteController.instancePatientModel().list());
        Object[] medicsList = Utils.listToarray(MedicoController.instanceMedicModel().list());

        String reason = JOptionPane.showInputDialog("Insert cite Reason");
        Date citeDate = Date.valueOf(JOptionPane.showInputDialog("Insert cite Date 'YYYY-MM-DD'"));
        Time citeHour = Time.valueOf(JOptionPane.showInputDialog("Insert cite Hour 'HH:MM:SS'"));

        Paciente optionSelectedPatient = (Paciente) JOptionPane.showInputDialog(null,
                "Select Patient to Add at Cite",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                patientsList,
                patientsList[0]);

        Medico optionSelectedMedic = (Medico) JOptionPane.showInputDialog(null,
                "Select Medic to Add at Cite",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                medicsList,
                medicsList[0]);

        instanceModelCite().create(new Cita(optionSelectedPatient.getId_paciente(), optionSelectedMedic.getId_medico(), citeDate, citeHour, reason, optionSelectedPatient, optionSelectedMedic));
    }

    public static void findByDate()
    {
        Cita cite = new Cita();

        Date citeDate = Date.valueOf( JOptionPane.showInputDialog("Input the cite´s date to search"));

        Cita citeRecived = instanceModelCite().findByDate(citeDate);

        if (citeRecived == null) {
            JOptionPane.showMessageDialog(null, "Cite not Found");
        }
        else
        {
            cite.setId_cite(citeRecived.getId_cite());
            cite.setId_patient(citeRecived.getId_patient());
            cite.setId_medic(citeRecived.getId_medic());
            cite.setCite_date(citeRecived.getCite_date());
            cite.setCite_hour(citeRecived.getCite_hour());
            cite.setReason(citeRecived.getReason());
            cite.setPatient(citeRecived.getPatient());
            cite.setMedic(citeRecived.getMedic());

            JOptionPane.showMessageDialog(null, cite.toString());
        }
    }
}
