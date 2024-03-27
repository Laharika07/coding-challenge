package com.mainmod;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import java.util.List;

import com.dao.HospitalServiceImpl;
import com.entity.Appointment;
import com.myexceptions.PatientNumberNotFoundException;

public class MainModule {
	
	public static void main(String[] args) throws PatientNumberNotFoundException, IOException, SQLException  {
		
		HospitalServiceImpl obj = new HospitalServiceImpl();

        // 1. Get appointment by ID
		int appointmentId = 202; 
		Appointment appointment = obj.getAppointmentById(appointmentId);
		System.out.println("Appointment ID " + appointmentId + " details: " + appointment);
		System.out.println("_____________________________________");

		// 2. Get appointments for patient
		int patientId = 1;
		List<Appointment> patientAppointments = obj.getAppointmentsForPatient(patientId);
		System.out.println("Appointments for patient ID " + patientId + ": " + patientAppointments);
		System.out.println("_____________________________________");

		// 3. Get appointments for doctor
		int doctorId = 101; // Replace with actual doctor ID
		List<Appointment> doctorAppointments = obj.getAppointmentsForDoctor(doctorId);
		System.out.println("Appointments for doctor ID " + doctorId + ": " + doctorAppointments);
		System.out.println("_____________________________________");

		// 4. Schedule appointment
		Appointment a = new Appointment();
		a.setPatientId(2); 
        a.setDoctorId(101); 
        a.setAppointmentDate(Date.valueOf("2024-04-01")); 
        a.setDescription("Follow-up checkup");
        a.setAppointmentId(208);
		boolean isScheduled = obj.scheduleAppointment(a);
		System.out.println("Appointment scheduled: " + isScheduled);
		System.out.println("_____________________________________");

		// 5. Update appointment
		Appointment app = new Appointment();
		app.setPatientId(5); 
        app.setDoctorId(105); 
        app.setAppointmentDate(Date.valueOf("2024-04-01")); 
        app.setDescription("Eye checkup");
        app.setAppointmentId(202);
		boolean isUpdated = obj.updateAppointment(app);
		System.out.println("Appointment updated: " + isUpdated);
		System.out.println("_____________________________________");

		// 6. Cancel appointment
		int appointmentIdToCancel = 201; 
		boolean isCancelled = obj.cancelAppointment(appointmentIdToCancel);
		System.out.println("Appointment cancelled: " + isCancelled);
		System.out.println("_____________________________________");
		
		// View Appointments
		obj.viewAppointments();
    }

}

