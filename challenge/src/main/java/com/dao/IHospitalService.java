package com.dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.entity.Appointment;
import com.myexceptions.PatientNumberNotFoundException;

public interface IHospitalService {
	
	Appointment getAppointmentById(int appointmentId) throws PatientNumberNotFoundException;
	
	List<Appointment> getAppointmentsForPatient(int patientId);
	
	List<Appointment> getAppointmentsForDoctor(int doctorId);
	
	boolean scheduleAppointment(Appointment appointment) throws SQLIntegrityConstraintViolationException;
	
	boolean updateAppointment(Appointment appointment);

    boolean cancelAppointment(int appointmentId);
    
    



}
