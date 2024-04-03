package Controller;

import Entity.Cita;
import Model.CitaModel;

import javax.swing.JOptionPane;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class CitaController 
{
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
        CitaModel citaModel = new CitaModel();

        JOptionPane.showMessageDialog(null, listAll(citaModel.list()));
    }

    public static String listAllCites()
    {
        CitaModel citaModel = new CitaModel();
        String listCites = "CITES LIST \n";

        for (Object cite: citaModel.list()){

            Cita citeNew = (Cita) cite;
            listCites += citeNew.toString() + "\n";
        }

        return listCites;
    }

    public static void delete()
    {
        CitaModel citaModel = new CitaModel();

        String citesList = listAllCites();

        int id = Integer.parseInt(JOptionPane.showInputDialog(citesList + "Input the Cite ID to delete"));

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

        String citesList = listAllCites();

        int idUpdated = Integer.parseInt( JOptionPane.showInputDialog(citesList + "Enter Cite ID to edit"));

        Cita cite = citaModel.findById(idUpdated);

        if (cite == null)
        {
            JOptionPane.showMessageDialog(null, "Unknown Cite");
        }
        else
        {
            int fk_id_patient = Integer.parseInt( JOptionPane.showInputDialog("Input the cite name or leave default name", cite.getId_patient()));
            int fk_id_medic  = Integer.parseInt( JOptionPane.showInputDialog("Input the cite surname or leave default", cite.getId_medic()));
            Date date_cite = Date.valueOf( JOptionPane.showInputDialog("Input the speciality ID or leave default", cite.getCite_date ()));
            Time hour_cite = Time.valueOf( JOptionPane.showInputDialog("Input the speciality ID or leave default", cite.getCite_hour()));
            String reason = JOptionPane.showInputDialog("Input the speciality ID or leave default", cite.getReason());

            cite.setId_patient(fk_id_patient);
            cite.setId_medic(fk_id_medic);
            cite.setCite_date(date_cite);
            cite.setCite_hour(hour_cite);
            cite.setReason(reason);

            citaModel.update(cite);
        }
    }


    public static void create(){

        CitaModel citaModel = new CitaModel();
        Cita cite = new Cita();

        String reason = JOptionPane.showInputDialog("Insert cite Reason");
        Date citeDate = Date.valueOf(JOptionPane.showInputDialog("Insert cite Date"));
        Time citeHour = Time.valueOf(JOptionPane.showInputDialog("Insert cite Hour"));
        int id_patient = Integer.parseInt(JOptionPane.showInputDialog("Insert ID Patient"));
        int id_medic = Integer.parseInt(JOptionPane.showInputDialog("Insert ID Medic"));

        cite.setReason(reason);
        cite.setCite_date(citeDate);
        cite.setCite_hour(citeHour);
        cite.setId_patient(id_patient);
        cite.setId_medic(id_medic);

        //Pasamos el objeto a cita
        cite = (Cita) citaModel.create(cite);

        JOptionPane.showMessageDialog(null, cite.toString());
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

            JOptionPane.showMessageDialog(null, cite.toString());
        }
    }
}
