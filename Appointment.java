package com.hospital.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor doctor;
    @Temporal(TemporalType.DATE)
    private Date appointmentDate;
    private String status;
	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Appointment(int id, Patient patient, Doctor doctor, Date appointmentDate, String status) {
		super();
		this.id = id;
		this.patient = patient;
		this.doctor = doctor;
		this.appointmentDate = appointmentDate;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
	    return "Appointment{" +
	            "id=" + id +
	            ", appointmentDate=" + appointmentDate +
	            ", doctorId=" + doctor.getId() +
	            ", doctorName=" + doctor.getName() +
	            ", patientId=" + patient.getId() +
	            ", patientName=" + patient.getName() +
	            ", status='" + status + '\'' +
	            '}';
	}

}
