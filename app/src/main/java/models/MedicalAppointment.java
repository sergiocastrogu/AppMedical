package models;

import java.util.Date;

public class MedicalAppointment {
    private int id;
    private int userDoctorId;
    private int userPatientId;
    private Date date;
    private int stateId;
    private String observations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserDoctorId() {
        return userDoctorId;
    }

    public void setUserDoctorId(int userDoctorId) {
        this.userDoctorId = userDoctorId;
    }

    public int getUserPatientId() {
        return userPatientId;
    }

    public void setUserPatientId(int userPatientId) {
        this.userPatientId = userPatientId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
