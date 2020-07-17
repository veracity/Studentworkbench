package com.example.demo.models;


public class WindSurfer {	  
	  
	private String mysails;
	
	private double myweight;
	private String experienceLevel;
	private  double location[];
	private int sailOrBoard;
	
	
	
	
	// Constructors
	  public WindSurfer() {}
	  
	  public WindSurfer(double weight, String experienceLevel, double location[], String sails,int sa) {
	
	    this.experienceLevel = experienceLevel;
	    this.myweight = weight;
	    this.location = location;
	    this.mysails = sails;
	    this.sailOrBoard = sa;
	  }
	
	
	  public int getSailOrBoard() {
			return sailOrBoard;
		}

		public void setSailOrBoard(int sailOrBoard) {
			this.sailOrBoard = sailOrBoard;
		}


		public String getMysails() {
			return mysails;
		}

		public void setMysails(String mysails) {
			this.mysails = mysails;
		}


		public double getMyweight() {
			return myweight;
		}

		public void setMyweight(double myweight) {
			this.myweight = myweight;
		}

		public String getExperienceLevel() {
			return experienceLevel;
		}

		public void setExperienceLevel(String experienceLevel) {
			this.experienceLevel = experienceLevel;
		}

		public double getLocation()[] {
			return location;
		}

		public void setLocation(double location[]) {
			this.location = location;
		}
	
}

