

import Controller.MedicoController;
import Controller.PacienteController;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args)
    {
        PacienteController patientController = new PacienteController();

        String optionPrincipalMenu;
        String patientOptionMenu;
        String medicOptionMenu;

        boolean exit = false;

        do
        {
            optionPrincipalMenu = JOptionPane.showInputDialog("""
                    1 - Patients Menu
                    2 - Medics Menu
                    3 - Exit
                    """);

            switch (optionPrincipalMenu)
            {
                case "1":
                        do
                        {
                            patientOptionMenu = JOptionPane.showInputDialog("""
                                1 - List All Patients
                                2 - Add New Author
                                3 - Search Patient By Document
                                4 - Update Patient
                                5 - Delete Patient
                                6 - Exit Patients Menu
                                """);

                            switch (patientOptionMenu)
                            {
                                case "1":
                                    patientController.listPatients();
                                    break;

                                case "2":
                                    patientController.create();
                                    break;

                                case "3":
                                    patientController.findByDocument();
                                    break;

                                case "4":
                                    patientController.update();
                                    break;

                                case "5":
                                    patientController.delete();
                                    break;

                                case "6":
                                    exit = true;
                                    break;
                            }
                        }while (!exit);

                break;

                case "2":
                        do
                        {
                            medicOptionMenu = JOptionPane.showInputDialog("""
                                    1 - List All Medics
                                    2 - Add New Medic
                                    3 - Update Medic
                                    4 - Delete Medic
                                    5 - Exit Medic Menu
                                    """);

                            switch (medicOptionMenu)
                            {
                                case "1":
                                    MedicoController.listMedics();
                                    break;

                                case "2":
                                    MedicoController.create();
                                    break;

                                case "3":
                                    MedicoController.update();
                                    break;

                                case "4":
                                    MedicoController.delete();
                                    break;

                                case "5":
                                    exit = true;
                                    break;
                            }
                        }while (!exit);
                break;
            }
        }while(!optionPrincipalMenu.equals("3"));
    }
}