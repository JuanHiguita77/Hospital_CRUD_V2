package Controller;


import Entity.Medico;
import Model.MedicoModel;

import javax.swing.JOptionPane;
import java.sql.Date;
import java.util.List;

public class MedicoController
{
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
        MedicoModel medicModel = new MedicoModel();

        JOptionPane.showMessageDialog(null, listAll(medicModel.list()));
    }

    public static String listAllMedics()
    {
        MedicoModel medicModel = new MedicoModel();
        String listMedics = "MEDICS LIST \n";

        for (Object medic: medicModel.list()){

            Medico medicNew = (Medico) medic;
            listMedics += medicNew.toString() + "\n";
        }

        return listMedics;
    }

    public static void delete()
    {
        MedicoModel medicModel = new MedicoModel();

        String medicsList = listAllMedics();

        int id = Integer.parseInt(JOptionPane.showInputDialog(medicsList + "Input the Medic ID to delete"));

        //Buscamos primero si existe
        Medico medic = medicModel.findById(id);

        if (medic == null)
        {
            JOptionPane.showInputDialog(null,"Unknown Medic");
        }
        else
        {
            int confirm = JOptionPane.showConfirmDialog(null,"Are you sure to delete? -- > " + medic.toString());

            if (confirm == 1)
            {
                JOptionPane.showMessageDialog(null,"Stopped!");
            }
            else
            {
                medicModel.delete(medic);
                JOptionPane.showMessageDialog(null, "Deleted sucessfully! --> " + medic.toString());
            }
        }
    }

    public static void update()
    {
        MedicoModel medicModel = new MedicoModel();

        String medicsList = listAllMedics();

        int idUpdated = Integer.parseInt( JOptionPane.showInputDialog(medicsList + "Enter Medic ID to edit"));

        Medico medic = medicModel.findById(idUpdated);

        if (medic == null)
        {
            JOptionPane.showMessageDialog(null, "Unknown Medic");
        }
        else
        {
            String name = JOptionPane.showInputDialog("Input the medic name or leave default name", medic.getName());
            String surname = JOptionPane.showInputDialog("Input the medic surname or leave default", medic.getSurname());
            int fk_id_especialidad = Integer.parseInt(JOptionPane.showInputDialog("Input the speciality ID or leave default", medic.getFk_id_especialidad()));

            medic.setName(name);
            medic.setSurname(surname);
            medic.setFk_id_especialidad(fk_id_especialidad);

            medicModel.update(medic);
        }
    }


    public static void create(){

        MedicoModel medicModel = new MedicoModel();
        Medico medic = new Medico();

        String name = JOptionPane.showInputDialog("Insert medic name");
        String surname = JOptionPane.showInputDialog("Insert medic surname");
        int fk_id_especialidad = Integer.parseInt(JOptionPane.showInputDialog("Insert Speciality ID"));

        medic.setName(name);
        medic.setSurname(surname);
        medic.setFk_id_especialidad(fk_id_especialidad);

        //Pasamos el objeto a medico
        medic = (Medico) medicModel.create(medic);

        JOptionPane.showMessageDialog(null, medic.toString());
    }

}
