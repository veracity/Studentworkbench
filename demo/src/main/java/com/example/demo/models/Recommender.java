package com.example.demo.models;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class Recommender {	  
	  
	  public WindSurfer ws;
	 
	  
	
	// Constructors
	  public Recommender() {}
	  
	  public Recommender(WindSurfer ws) {
		  this.ws = ws;
		  
	  }
	  
	  public JsonNode sortAndRecommend(WindSurfer ws, List<WindData> wd)
	  {	
		  
		  
		  ObjectMapper mapper = new ObjectMapper();
		  JsonNode rootNode = mapper.createObjectNode();
	  
		  if(ws.getSailOrBoard() == 2)
		  {
			  return offerForBoard(ws);
		  }else  if(ws.getSailOrBoard() == 1)
		  {
			  return offerForSail(ws,wd);
		  }else{ 
			  ((ObjectNode) rootNode).put("Err","SailOrBoard data not found!");
			  return rootNode;	  
		  }		
		
	  }
	
	  public JsonNode offerForSail(WindSurfer ws, List<WindData> wd)
	  {
		  ObjectMapper mapper = new ObjectMapper();
		  JsonNode rootNode = mapper.createObjectNode();
		  JsonNode sails = mapper.createObjectNode();
		  
		  
		  
		  for (int i = 0; i < wd.size(); i++) {
			    WindData element = wd.get(i);
			    double choice = calculateSailSize(ws.getMysails(), element.getWindSpeed(), ws.getMyweight());
			      JsonNode datas = mapper.createObjectNode();
			      if(choice == 0.0) {
				  ((ObjectNode) datas).put("RecommendedSize","windspeed is too low" );
			      }else {
			    	  ((ObjectNode) datas).put("RecommendedSize",choice );
			      }
				  System.out.println( element.getWindSpeed());
				  System.out.println( "WindSpeedup");
				  System.out.println( element.getOutputdate());
				  System.out.println(choice);
				  ((ObjectNode) datas).put("Date",element.getOutputdate() );
				  ((ObjectNode) datas).put("Value",element.getWindSpeed() );
				 
				  ((ObjectNode) sails).set(String.valueOf(i),datas);
			}
		 
		  ((ObjectNode) rootNode).set("Sails", sails);
		  System.out.println( rootNode.toString());
		  JsonNode boards = mapper.createObjectNode();
		  ((ObjectNode) boards).put("Not used", "Not used");
		  ((ObjectNode) rootNode).set("Boards", boards);  
		  
		  return rootNode;
	  }
	  public double calculateSailSize(String sails, Double wd, Double weight) {
		  Double windSpeed = wd; 
		  Long sailsize = null;
		  Long subtract = null;
		  Long smallest = (long) 1000;
		  Double choice = 0.0;
		 
		  sailsize = (long) (1.34*weight/(windSpeed/1.94384449)); //Formula to calculate = (1.34*weight/(wind in m,s / 1.94384449 knots))
		  System.out.println( sailsize);
		  String sail = sails;
		  String[] namesList = sail.split(",");
		  
		  
		  double[] doubleArray = Arrays.stream(namesList).mapToDouble(Double::parseDouble).toArray();
		  
		  for (int i = 0; i < doubleArray.length; i++) { 
			  
			  subtract = (long) Math.abs(doubleArray[i] - sailsize);
			  
			  System.out.println(doubleArray[i]);
			  if(smallest > subtract) {
				  smallest = subtract;
				  choice = (double) doubleArray[i];
				  
			  }
	        } 
		  return choice;
	  }
	  public JsonNode offerForBoard(WindSurfer ws)
	  {
		  ObjectMapper mapper = new ObjectMapper();
		  JsonNode rootNode = mapper.createObjectNode();

		  JsonNode boards = mapper.createObjectNode();
		  
		  if(ws.getExperienceLevel().contentEquals("Beginner"))
		  {
			  Double beginnerBoard = (ws.getMyweight()+10)*2.17;
			  ((ObjectNode) boards).put("Beginner Board", Math.round(beginnerBoard));
			  
			  Double cruisingLongBoard = (ws.getMyweight()*2.2)*0.8+90;
			  ((ObjectNode) boards).put("Cruising Longboard", Math.round(cruisingLongBoard));
 
		  }else if(ws.getExperienceLevel().contentEquals("Intermediate"))
		  {
			  
			  Double firstShortBoard = ws.getMyweight()*1.3+30;
			  ((ObjectNode) boards).put("First Shortboard", Math.round(firstShortBoard));
			  
			  Double minimumSizeUphaulable = ws.getMyweight()+30;
			  ((ObjectNode) boards).put("Minimum Size Uphaulable", Math.round(minimumSizeUphaulable));
 
		  }else if(ws.getExperienceLevel().contentEquals("Advanced"))
		  {
			  
			  Double moderateSinker = (ws.getMyweight()+7)*1.06;
			  ((ObjectNode) boards).put("Moderate Sinker", Math.round(moderateSinker));
			  Double seriousSinker = (ws.getMyweight()+7)*0.937;
			  ((ObjectNode) boards).put("Serious Sinker", Math.round(seriousSinker));
			  Double radicalSinker = (ws.getMyweight()+7)*0.8125;
			  ((ObjectNode) boards).put("Radical Sinker", Math.round(radicalSinker));
 
		  }
		  
		  ((ObjectNode) rootNode).set("Boards", boards);
		  
		  JsonNode sails = mapper.createObjectNode();
		  ((ObjectNode) sails).put("Not used", "Not used");
		  ((ObjectNode) rootNode).set("Sails", sails);
		  
		  return rootNode;
	  }
  
	  
	
	
}