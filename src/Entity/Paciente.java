package Entity;

import java.sql.Date;

public class Paciente
{
    private int id_paciente;
    private String name;
    private String surname;
    private Date date_birth;
    private String document;

    public Paciente() {
    }

    public Paciente(int id_paciente, String name, String surname, Date date_birth, String document) {
        this.id_paciente = id_paciente;
        this.name = name;
        this.surname = surname;
        this.date_birth = date_birth;
        this.document = document;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(Date date_birth) {
        this.date_birth = date_birth;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "Paciente --> " +
                "id_paciente: " + id_paciente +
                " name: " + name +
                " surname: " + surname +
                " date_birth: " + date_birth +
                " document: " + document;
    }
}
