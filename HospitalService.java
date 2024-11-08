package com.hospital.service;

import com.hospital.config.HibernateUtil;
import com.hospital.model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class HospitalService {

    // ---------------------- Doctor Management ----------------------

    // Add a new doctor
    public void addDoctor(String name, String specialization, String contact) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Doctor doctor = new Doctor();
            doctor.setName(name);
            doctor.setSpecialization(specialization);
            doctor.setContact(contact);
            session.save(doctor);
            tx.commit();
        }
    }

    // Retrieve a doctor by ID
    public Doctor getDoctorById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Doctor.class, id);
        }
    }

    // Update doctor details
    public void updateDoctor(int id, String name, String specialization, String contact) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Doctor doctor = session.get(Doctor.class, id);
            if (doctor != null) {
                doctor.setName(name);
                doctor.setSpecialization(specialization);
                doctor.setContact(contact);
                session.update(doctor);
                tx.commit();
            }
        }
    }

    // Delete a doctor
    public boolean deleteDoctor(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Doctor doctor = session.get(Doctor.class, id);
            if (doctor != null) {
                session.delete(doctor);
                tx.commit();
                return true;
            }
            return false;
        }
    }

    // ---------------------- Patient Management ----------------------

    // Add a new patient
    public void addPatient(String name, int age, String contact, String address) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Patient patient = new Patient();
            patient.setName(name);
            patient.setAge(age);
            patient.setContact(contact);
            patient.setAddress(address);
            session.save(patient);
            tx.commit();
        }
    }

    // Retrieve a patient by ID
    public Patient getPatientById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Patient.class, id);
        }
    }

    // Update patient details
    public void updatePatient(int id, String name, int age, String contact, String address) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Patient patient = session.get(Patient.class, id);
            if (patient != null) {
                patient.setName(name);
                patient.setAge(age);
                patient.setContact(contact);
                patient.setAddress(address);
                session.update(patient);
                tx.commit();
            }
        }
    }

    // Delete a patient
    public boolean deletePatient(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Patient patient = session.get(Patient.class, id);
            if (patient != null) {
                session.delete(patient);
                tx.commit();
                return true;
            }
            return false;
        }
    }

    // ---------------------- Appointment Management ----------------------

    // Schedule an appointment
    public void scheduleAppointment(int patientId, int doctorId, Date appointmentDate, String status) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Patient patient = session.get(Patient.class, patientId);
            Doctor doctor = session.get(Doctor.class, doctorId);

            if (patient != null && doctor != null) {
                Appointment appointment = new Appointment();
                appointment.setPatient(patient);
                appointment.setDoctor(doctor);
                appointment.setAppointmentDate(appointmentDate);
                appointment.setStatus(status);
                session.save(appointment);
                tx.commit();
            } else {
                System.out.println("Invalid Patient or Doctor ID.");
            }
        }
    }

    // Retrieve all appointments
    public List<Appointment> getAllAppointments() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Appointment", Appointment.class).list();
        }
    }

    // Retrieve appointments by patient ID
    public List<Appointment> getAppointmentsByPatientId(int patientId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Appointment> query = session.createQuery(
                "from Appointment where patient.id = :patientId", Appointment.class);
            query.setParameter("patientId", patientId);
            return query.list();
        }
    }

    // ---------------------- Billing Management ----------------------

    // Add a billing record
    public void addBilling(int patientId, double amount, Date date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Patient patient = session.get(Patient.class, patientId);

            if (patient != null) {
                Billing billing = new Billing();
                billing.setPatient(patient);
                billing.setAmount(amount);
                billing.setDate(date);
                session.save(billing);
                tx.commit();
            } else {
                System.out.println("Invalid Patient ID.");
            }
        }
    }

    // Retrieve billing records by patient ID
    public List<Billing> getBillingsByPatientId(int patientId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Billing> query = session.createQuery(
                "from Billing where patient.id = :patientId", Billing.class);
            query.setParameter("patientId", patientId);
            return query.list();
        }
    }

    // Retrieve all billing records
    public List<Billing> getAllBillings() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Billing", Billing.class).list();
        }
    }
}
