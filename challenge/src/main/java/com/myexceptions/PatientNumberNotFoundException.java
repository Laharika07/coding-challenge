package com.myexceptions;

public class PatientNumberNotFoundException extends Exception {
	
	
	private String exceptionMessage;
	public PatientNumberNotFoundException(String exceptionMessage) {
		super();
		this.exceptionMessage = exceptionMessage;
		
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}

}
