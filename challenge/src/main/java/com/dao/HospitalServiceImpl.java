package com.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Appointment;
import com.myexceptions.PatientNumberNotFoundException;
import com.util.DBConnection;

public class HospitalServiceImpl implements IHospitalService {
	
    private Connection connection;

    public HospitalServiceImpl() throws IOException, SQLException {
        this.connection = DBConnection.getConnection();
    }
    
    private Appointment getAppoinmentData(ResultSet rs) throws SQLException {
        Appointment app = new Appointment();
        app.setAppointmentId(rs.getInt("appointmentId"));
        app.setPatientId(rs.getInt("patientId"));
        app.setDoctorId(rs.getInt("doctorId"));
        app.setAppointmentDate(rs.getDate("appointmentDate"));
        app.setDescription(rs.getString("description"));
        return app;
    }


    @Override
    public Appointment getAppointmentById(int appointmentId) {
        String query = "select * from appointment where appointmentId = ?";
        ResultSet rs;
        try(PreparedStatement statement = connection.prepareStatement(query)){
        	
        	statement.setInt(1, appointmentId);

    		rs =statement.executeQuery();
    		if(rs.next()) {
   			 return getAppoinmentData(rs);
    		}
    		else {
   	        throw new PatientNumberNotFoundException("Appointment not found for ID: " + appointmentId);
   	        }
   	    }catch (SQLException e) {
    	        e.printStackTrace();
    	} catch (PatientNumberNotFoundException p) {
			   p.printStackTrace();
		}
    	    return null;
    	}

    @Override
	public List<Appointment> getAppointmentsForPatient(int patientId) {
		List<Appointment> appointments = new ArrayList<>();
		String query = "select * from appointment where patientId = ?";
	    try (PreparedStatement stmt = connection.prepareStatement(query)) {
	        stmt.setInt(1, patientId);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            appointments.add(getAppoinmentData(rs));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return appointments;
	}

	@Override
	public List<Appointment> getAppointmentsForDoctor(int doctorId) {
		List<Appointment> appointments = new ArrayList<>();
	    String query = "select * from appointment where doctorId = ?";
	    try (PreparedStatement stmt = connection.prepareStatement(query)) {
	        stmt.setInt(1, doctorId);

	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            appointments.add(getAppoinmentData(rs));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return appointments;
	}

	@Override
	public boolean scheduleAppointment(Appointment appointment) throws SQLIntegrityConstraintViolationException {
		String query = "insert into appointment (appointmentId,patientId, doctorId, appointmentDate, description) values (?, ?, ?, ?, ?)";
	    try (PreparedStatement stmt = connection.prepareStatement(query)) {
	    	stmt.setInt(1, appointment.getAppointmentId());
	        stmt.setInt(2, appointment.getPatientId());
	        stmt.setInt(3, appointment.getDoctorId());
	        stmt.setDate(4, (Date) appointment.getAppointmentDate());
	        stmt.setString(5, appointment.getDescription());
	        int rowsAffected = stmt.executeUpdate();
	        System.out.println("Rows effected :"+rowsAffected);
	        return rowsAffected > 0;
	    }catch (java.sql.SQLIntegrityConstraintViolationException e) {
            System.out.println(" Duplicate entry or integrity constraint violation.");
        } catch (Exception e) {
            System.out.println("An exception occurred: " + e.getMessage());
        }
	    return false;
	}

	@Override
	public boolean updateAppointment(Appointment appointment) {
		String query = "update appointment set patientId = ?, doctorId = ?, appointmentDate = ?, description = ? where appointmentId = ?";

	    try (PreparedStatement stmt = connection.prepareStatement(query)) {
	        stmt.setInt(1, appointment.getPatientId());
	        stmt.setInt(2, appointment.getDoctorId());
	        stmt.setDate(3, (Date) appointment.getAppointmentDate());
	        stmt.setString(4, appointment.getDescription());
	        stmt.setInt(5, appointment.getAppointmentId());
	        int rowsAffected = stmt.executeUpdate();
	        System.out.println("Rows effected :"+rowsAffected);
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	@Override
	public boolean cancelAppointment(int appointmentId) {
		
		String query = "delete from appointment where appointmentId = ?";

	    try (PreparedStatement stmt = connection.prepareStatement(query)) {
	        stmt.setInt(1, appointmentId);
	        int rowsAffected = stmt.executeUpdate();
	        System.out.println("Rows effected :"+rowsAffected);
	        return rowsAffected >0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
		
	}
	
	public void viewAppointments() {
		
		String query = "select * from appointment";
	    try (PreparedStatement stmt = connection.prepareStatement(query)) {
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	        	int appointmentId = rs.getInt("appointmentId");
                int patientId = rs.getInt("patientId");
                int doctorId = rs.getInt("doctorId");
                String appointmentDate = rs.getString("appointmentDate");
                String description = rs.getString("description");

                System.out.println("Appointment ID: " + appointmentId);
                System.out.println("Patient ID: " + patientId);
                System.out.println("Doctor ID: " + doctorId);
                System.out.println("Appointment Date: " + appointmentDate);
                System.out.println("Description: " + description);
                System.out.println("_____________________________________");
                
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	}


}
