package com.example.demo.models;


public class WindData {	  
	  
	
	private String outputdate;
	
	private double windSpeed;
	
	
	
	
	// Constructors
	  public WindData() {}
	  
	  public WindData(double ws, String od) {
	
	    this.outputdate = od;
	    this.windSpeed = ws;
	  }
	  
	  
	  public String getOutputdate() {
			return outputdate;
		}

		public void setOutputdate(String outputdate) {
			this.outputdate = outputdate;
		}

		public double getWindSpeed() {
			return windSpeed;
		}

		public void setWindSpeed(double windSpeed) {
			this.windSpeed = windSpeed;
		}

}