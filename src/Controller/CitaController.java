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
        CitaModel citaModel = new CitaModel();

        instanceModelCite().list();

        int id = Integer.parseInt(JOptionPane.showInputDialog("Input the Cite ID to delete"));

        //Buscamos primero si existe
        Cita cite = citaModel.findById(id);

        if (cite == null)
        {
            JOptionPane.showInputDialog(null,"Unknown Cite");
        }
        else
        {
            int confirm = JOptionPane.showConfirmDialog(null,"Are you sure to delete? -- > " + cite.toString());

            if (confirm == 1)
            {
                JOptionPane.showMessageDialog(null,"Stopped!");
            }
            else
            {
                citaModel.delete(cite);
                JOptionPane.showMessageDialog(null, "Deleted sucessfully! --> " + cite.toString());
            }
        }
    }

    public static void update()
    {
        CitaModel citaModel = new CitaModel();

        int idUpdated = Integer.parseInt( JOptionPane.showInputDialog("Enter Cite ID to edit"));

        Cita cite = citaModel.findById(idUpdated);

        if (cite == null)
        {
            JOptionPane.showMessageDialog(null, "Unknown Cite");
        }
        else
        {
            int fk_id_patient = Integer.parseInt( JOptionPane.showInputDialog("Input the ID Patient or leave default", cite.getId_patient()));
            int fk_id_medic  = Integer.parseInt( JOptionPane.showInputDialog("Input the ID Medic or leave default", cite.getId_medic()));
            Date date_cite = Date.valueOf( JOptionPane.showInputDialog("Input the Dace´s cite ID or leave default", cite.getCite_date ()));
            Time hour_cite = Time.valueOf( JOptionPane.showInputDialog("Input the Hour´s cite ID or leave default", cite.getCite_hour()));
            String reason = JOptionPane.showInputDialog("Input the Reason´s cite or leave default", cite.getReason());

            cite.setId_patient(fk_id_patient);
            cite.setId_medic(fk_id_medic);
            cite.setCite_date(date_cite);
            cite.setCite_hour(hour_cite);
            cite.setReason(reason);

            citaModel.update(cite);
        }
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
        CitaModel citeModel = new CitaModel();
        Cita cite = new Cita();

        Date citeDate = Date.valueOf( JOptionPane.showInputDialog("Input the cite´s date to search"));

        Cita citeRecived = citeModel.findByDate(citeDate);

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
