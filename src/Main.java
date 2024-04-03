

import Controller.CitaController;
import Controller.EspecialidadController;
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
        String specialityOptionMenu;
        String citeOptionMenu;

        boolean exit = false;

        do
        {
            optionPrincipalMenu = JOptionPane.showInputDialog("""
                    1 - Patients Menu
                    2 - Medics Menu
                    3 - Speciality Menu
                    4 - Cites Menu
                    5 - Exit
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
                                    2 - Search Medics By Specialities
                                    3 - Add New Medic
                                    4 - Update Medic
                                    5 - Delete Medic
                                    6 - Exit Medic Menu
                                    """);

                            switch (medicOptionMenu)
                            {
                                case "1":
                                    MedicoController.listMedics();
                                    break;

                                case "2":
                                    MedicoController.findDoctorBySpecialization();
                                    break;

                                case "3":
                                    MedicoController.create();
                                    break;

                                case "4":
                                    MedicoController.update();
                                    break;

                                case "5":
                                    MedicoController.delete();
                                    break;

                                case "6":
                                    exit = true;
                                    break;
                            }
                        }while (!exit);
                break;
                case "3":
                    do
                    {
                        specialityOptionMenu = JOptionPane.showInputDialog("""
                                    1 - List All Specialities
                                    2 - Add New Speciality
                                    3 - Update Speciality
                                    4 - Delete Speciality
                                    5 - Exit Speciality Menu
                                    """);

                        switch (specialityOptionMenu)
                        {
                            case "1":
                                EspecialidadController.listSpeciality();
                                break;

                            case "2":
                                EspecialidadController.create();
                                break;

                            case "3":
                                EspecialidadController.update();
                                break;

                            case "4":
                                EspecialidadController.delete();
                                break;

                            case "5":
                                exit = true;
                                break;
                        }
                    }while (!exit);
                    break;
                case "4":
                    do
                    {
                        citeOptionMenu = JOptionPane.showInputDialog("""
                                    1 - List All Cites
                                    2 - Search By Cite Date
                                    3 - Add New Cite
                                    4 - Update Cite
                                    5 - Delete Cite
                                    6 - Exit Cites Menu
                                    """);

                        switch (citeOptionMenu)
                        {
                            case "1":
                                CitaController.listCites();
                                break;

                            case "2":
                                CitaController.findByDate();
                                break;

                            case "3":
                                CitaController.create();
                                break;

                            case "4":
                                CitaController.update();
                                break;

                            case "5":
                                CitaController.delete();
                                break;

                            case "6":
                                exit = true;
                                break;
                        }
                    }while (!exit);
                    break;
            }
        }while(!optionPrincipalMenu.equals("5"));
    }
}