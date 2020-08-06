package com.example.demo.controllers;


import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.models.Recommender;
import com.example.demo.models.WindData;
import com.example.demo.models.WindSurfer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import kong.unirest.HttpResponse;
import kong.unirest.Unirest;



@RestController
@RequestMapping("/api")
public class WindController {
	
	
	
	@PostMapping("/sails")
	public JsonNode cal(@Validated @RequestBody WindSurfer ws) {
		Recommender rs = new Recommender();	
		return rs.sortAndRecommend(ws,getWindDataFromApi(ws));		   
	}
	
	 public List<WindData> getWindDataFromApi(WindSurfer ws){
		

			
			
			String result = getWindSpeed(ws);
			System.out.println(result);
			
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = null;
			JsonNode coordinates = null;
			try {
				node = mapper.readTree(result);
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String phoneType = node.get("data").toString();
			System.out.println(phoneType);
			coordinates = node.get("data").findValue("coordinates");
			
			
			List<JsonNode> dates = StreamSupport
				    .stream(coordinates.findValue("dates").spliterator(), false)
				    .collect(Collectors.toList());
			List<WindData> wd = new ArrayList<WindData>();
			
			for(int i=0; i<dates.size(); i++) {
				WindData p = new WindData();
				p.setOutputdate(dates.get(i).findValue("date").toString() );
				p.setWindSpeed( dates.get(i).findValue("value").asDouble() );
				
			     wd.add(p);
			}		
			for(int i=0; i<wd.size(); i++) {
			
				
				
			    
			}			
		        return wd;
		}
	 public String getWindSpeed(WindSurfer ws) {
		 
		
		    
			String urlToRead = null;
			String lat = Double.toString(ws.getLocation()[0]);
			String lon = Double.toString(ws.getLocation()[1]);
			
			String username = "";///your username 
			String password = "";////and password in Meteomatics account
			urlToRead = "https://"+username+":"+password+"@api.meteomatics.com/now--now+12H:PT1H/wind_speed_20m:ms/"+lat+","+lon+"/json";
			HttpResponse<String> response = Unirest.get(urlToRead).asString();

	        return response.getBody();
	        	
	    }
	   
	 
	 
}



