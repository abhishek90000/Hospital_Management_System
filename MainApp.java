package com.hospital;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;
import com.hospital.model.Appointment;
import com.hospital.model.Billing;
import com.hospital.service.HospitalService;

public class MainApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final HospitalService service = new HospitalService();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Hospital Management System ---");
            System.out.println("1. Add Doctor");
            System.out.println("2. View Doctor");
            System.out.println("3. Update Doctor");
            System.out.println("4. Delete Doctor");
            System.out.println("5. Add Patient");
            System.out.println("6. View Patient");
            System.out.println("7. Update Patient");
            System.out.println("8. Delete Patient");
            System.out.println("9. Schedule Appointment");
            System.out.println("10. View Appointments");
            System.out.println("11. Add Billing");
            System.out.println("12. View Billing");
            System.out.println("13. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1 -> addDoctor();
                case 2 -> viewDoctor();
                case 3 -> updateDoctor();
                case 4 -> deleteDoctor();
                case 5 -> addPatient();
                case 6 -> viewPatient();
                case 7 -> updatePatient();
                case 8 -> deletePatient();
                case 9 -> scheduleAppointment();
                case 10 -> viewAppointments();
                case 11 -> addBilling();
                case 12 -> viewBilling();
                case 13 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addDoctor() {
        System.out.print("Enter Doctor's Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Specialization: ");
        String specialization = scanner.nextLine();
        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine();
        service.addDoctor(name, specialization, contact);
        System.out.println("Doctor added successfully.");
    }

    private static void viewDoctor() {
        System.out.print("Enter Doctor ID: ");
        int id = scanner.nextInt();
        Doctor doctor = service.getDoctorById(id);
        if (doctor != null) {
            System.out.println("Doctor Details: " + doctor);
        } else {
            System.out.println("Doctor not found.");
        }
    }

    private static void updateDoctor() {
        System.out.print("Enter Doctor ID to Update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Doctor doctor = service.getDoctorById(id);
        if (doctor != null) {
            System.out.print("Enter New Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter New Specialization: ");
            String specialization = scanner.nextLine();
            System.out.print("Enter New Contact Number: ");
            String contact = scanner.nextLine();
            service.updateDoctor(id, name, specialization, contact);
            System.out.println("Doctor updated successfully.");
        } else {
            System.out.println("Doctor not found.");
        }
    }

    private static void deleteDoctor() {
        System.out.print("Enter Doctor ID to Delete: ");
        int id = scanner.nextInt();
        if (service.deleteDoctor(id)) {
            System.out.println("Doctor deleted successfully.");
        } else {
            System.out.println("Doctor not found.");
        }
    }

    private static void addPatient() {
        System.out.print("Enter Patient's Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        service.addPatient(name, age, contact, address);
        System.out.println("Patient added successfully.");
    }

    private static void viewPatient() {
        System.out.print("Enter Patient ID: ");
        int id = scanner.nextInt();
        Patient patient = service.getPatientById(id);
        if (patient != null) {
            System.out.println("Patient Details: " + patient);
        } else {
            System.out.println("Patient not found.");
        }
    }

    private static void updatePatient() {
        System.out.print("Enter Patient ID to Update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Patient patient = service.getPatientById(id);
        if (patient != null) {
            System.out.print("Enter New Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter New Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter New Contact Number: ");
            String contact = scanner.nextLine();
            System.out.print("Enter New Address: ");
            String address = scanner.nextLine();
            service.updatePatient(id, name, age, contact, address);
            System.out.println("Patient updated successfully.");
        } else {
            System.out.println("Patient not found.");
        }
    }

    private static void deletePatient() {
        System.out.print("Enter Patient ID to Delete: ");
        int id = scanner.nextInt();
        if (service.deletePatient(id)) {
            System.out.println("Patient deleted successfully.");
        } else {
            System.out.println("Patient not found.");
        }
    }

    private static void scheduleAppointment() {
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Doctor ID: ");
        int doctorId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Appointment Date (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine();
        try {
            Date appointmentDate = dateFormat.parse(dateStr);
            service.scheduleAppointment(patientId, doctorId, appointmentDate, "Scheduled");
            System.out.println("Appointment scheduled successfully.");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    private static void viewAppointments() {
        List<Appointment> appointments = service.getAllAppointments();
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            System.out.println("Appointments List:");
            appointments.forEach(System.out::println);
        }
    }

    private static void addBilling() {
        System.out.print("Enter Patient ID for Billing: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Billing Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter Billing Date (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine();
        try {
            Date billingDate = dateFormat.parse(dateStr);
            service.addBilling(patientId, amount, billingDate);
            System.out.println("Billing record added successfully.");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    private static void viewBilling() {
        System.out.print("Enter Patient ID to view billing: ");
        int patientId = scanner.nextInt();
        List<Billing> billings = service.getBillingsByPatientId(patientId);

        if (billings.isEmpty()) {
            System.out.println("No billing records found for this patient.");
        } else {
            System.out.println("Billing Details:");
            for (Billing billing : billings) {
                System.out.println("Billing ID: " + billing.getId());
                System.out.println("Amount: $" + billing.getAmount());
                System.out.println("Date: " + billing.getDate());
                System.out.println("----------------------------");
            }
        }
    }
}  