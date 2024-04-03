package Entity;

import java.sql.Time;
import java.sql.Date;

public class Cita
{
    private int id_cite;
    private int id_patient;
    private int id_medic;
    private Date cite_date;
    private Time cite_hour;
    private String reason;
    private String patient;
    private String medic;

    public Cita() {
    }

    /*public Cita(int id_cite, int id_patient, int id_medic, Date cite_date, Time cite_hour, String reason) {
        this.id_cite = id_cite;
        this.id_patient = id_patient;
        this.id_medic = id_medic;
        this.cite_date = cite_date;
        this.cite_hour = cite_hour;
        this.reason = reason;
    }*/

    public Cita(int id_cite, int id_patient, int id_medic, Date cite_date, Time cite_hour, String reason, String patient, String medic) {
        this.id_cite = id_cite;
        this.id_patient = id_patient;
        this.id_medic = id_medic;
        this.cite_date = cite_date;
        this.cite_hour = cite_hour;
        this.reason = reason;
        this.patient = patient;
        this.medic = medic;
    }

    public int getId_cite() {
        return id_cite;
    }

    public void setId_cite(int id_cite) {
        this.id_cite = id_cite;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public int getId_medic() {
        return id_medic;
    }

    public void setId_medic(int id_medic) {
        this.id_medic = id_medic;
    }

    public Date getCite_date() {
        return cite_date;
    }

    public void setCite_date(Date cite_date) {
        this.cite_date = cite_date;
    }

    public Time getCite_hour() {
        return cite_hour;
    }

    public void setCite_hour(Time cite_hour) {
        this.cite_hour = cite_hour;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getMedic() {
        return medic;
    }

    public void setMedic(String medic) {
        this.medic = medic;
    }

    @Override
    public String toString() {
        return "Cita --> " +
                "id_cite=" + id_cite +
                " id_patient=" + id_patient +
                " id_medic=" + id_medic +
                " cite_date=" + cite_date +
                " cite_hour=" + cite_hour +
                " reason='" + reason;
    }
}


