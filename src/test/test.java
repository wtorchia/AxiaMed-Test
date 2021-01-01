package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class test {
	
	
	static public class EntriesResponse{
		
		static public class Entry{
			
			public String API;
			public String Description;
			public String Auth;
			public String HTTPS;
			public String Cors;
			public String Link;
			public String Category;
		}
		
		public String count;
		public List<Entry> entries;
		
	}
	
	static public class HealthResponse{
		
		public String alive; 
		
	}

	
/*

Full coverage for title param:

title - Done

title + description
title + auth	
title + https	
title + cors	
title + category

title + description + auth
title + description + https	
title + description + cors	
title + description + category	

title + auth + https
title + auth + cors
title + auth + catagory

title + http + cors	
title + http + category

tittle + description + auth + https
tittle + description + auth + cors	
tittle + description + auth + category

tittle + auth + category + https
tittle + auth + category + cors

title + http + cors + category

title + description + auth + https + cors + category

Will need to be done for each param for full coverage.
*/
	
	
	@Test
	public void getEntriesByCatagoryTest() throws IOException  {
		
		String quesryString = "Animals";
		
		String url = "https://api.publicapis.org/entries?category=" + quesryString;
		 
	
		try {
		
		
		    StringBuilder content = getAPIResponse(url); 
		    
		    ObjectMapper objectMapper = new ObjectMapper();
		    
		    EntriesResponse entryResponse = objectMapper.readValue(content.toString(), new TypeReference<EntriesResponse>(){});
		    
		    
		    assert(Integer.parseInt(entryResponse.count) == 13);
		    
		    
		    for(EntriesResponse.Entry entry : entryResponse.entries) {
		    	
		    	assert(entry.Category.contains(quesryString));
		    }
		    
		    assert(entryResponse.entries.get(0).API.equals("Cat Facts"));
		    assert(entryResponse.entries.get(0).Description.equals("Daily cat facts"));
		    assert(entryResponse.entries.get(0).Auth.equals(""));
		    assert(entryResponse.entries.get(0).HTTPS.equals("true"));
		    assert(entryResponse.entries.get(0).Cors.equals("no"));
		    assert(entryResponse.entries.get(0).Link.equals("https://alexwohlbruck.github.io/cat-facts/"));
		    assert(entryResponse.entries.get(0).Category.equals("Animals"));
		    
		    
		    assert(entryResponse.entries.get(12).API.equals("Shibe.Online"));
		    assert(entryResponse.entries.get(12).Description.equals("Random pictures of Shibu Inu, cats or birds"));
		    assert(entryResponse.entries.get(12).Auth.equals(""));
		    assert(entryResponse.entries.get(12).HTTPS.equals("true"));
		    assert(entryResponse.entries.get(12).Cors.equals("yes"));
		    assert(entryResponse.entries.get(12).Link.equals("http://shibe.online/"));
		    assert(entryResponse.entries.get(12).Category.equals("Animals"));
		   
		} 
		catch(Exception e){
			
			System.out.println(e);
			
			Assert.fail();
			
		}
	}
	
	
	@Test
	public void getEntriesByTitleTest() throws IOException  {
		
		
		String quesryString = "the";
		
		String url = "https://api.publicapis.org/entries?title=" + quesryString;
		 
		
		try {
		
		
		    StringBuilder content = getAPIResponse(url); 
		    
		    ObjectMapper objectMapper = new ObjectMapper();
		    
		    EntriesResponse entryResponse = objectMapper.readValue(content.toString(), new TypeReference<EntriesResponse>(){});
		    
		    assert(Integer.parseInt(entryResponse.count) == 19);
		    
		    for(EntriesResponse.Entry entry : entryResponse.entries) {
		    	
		    	assert(entry.API.toLowerCase().contains(quesryString));
		    }
		    
		   
		    
		    assert(entryResponse.entries.get(0).API.equals("The Report of the Week"));
		    assert(entryResponse.entries.get(0).Description.equals("Food & Drink Reviews"));
		    assert(entryResponse.entries.get(0).Auth.equals(""));
		    assert(entryResponse.entries.get(0).HTTPS.equals("true"));
		    assert(entryResponse.entries.get(0).Cors.equals("unknown"));
		    assert(entryResponse.entries.get(0).Link.equals("https://github.com/andyklimczak/TheReportOfTheWeek-API"));
		    assert(entryResponse.entries.get(0).Category.equals("Food & Drink"));
		    
		    
		    assert(entryResponse.entries.get(18).API.equals("Yahoo! Weather"));
		    assert(entryResponse.entries.get(18).Description.equals("Weather"));
		    assert(entryResponse.entries.get(18).Auth.equals("apiKey"));
		    assert(entryResponse.entries.get(18).HTTPS.equals("true"));
		    assert(entryResponse.entries.get(18).Cors.equals("unknown"));
		    assert(entryResponse.entries.get(18).Link.equals("https://developer.yahoo.com/weather/"));
		    assert(entryResponse.entries.get(18).Category.equals("Weather"));
		   
		} 
		catch(Exception e){
			
			System.out.println(e);
			
			Assert.fail();
			
		}
	}
	
	@Test
	public void getEntriesByDescriptionTest() throws IOException  {
		
		
		String quesryString = "weather";
		
		String url = "https://api.publicapis.org/entries?description=" + quesryString;
		 
		
		try {
		
		
		    StringBuilder content = getAPIResponse(url); 
		    
		    ObjectMapper objectMapper = new ObjectMapper();
		    
		    EntriesResponse entryResponse = objectMapper.readValue(content.toString(), new TypeReference<EntriesResponse>(){});
		    
		    
		    assert(Integer.parseInt(entryResponse.count) == 11);
		    
		    for(EntriesResponse.Entry entry : entryResponse.entries) {
		    	
		    	assert(entry.Description.toLowerCase().contains(quesryString));
		    }
		   
		    
		    assert(entryResponse.entries.get(0).API.equals("AirVisual"));
		    assert(entryResponse.entries.get(0).Description.equals("Air quality and weather data"));
		    assert(entryResponse.entries.get(0).Auth.equals("apiKey"));
		    assert(entryResponse.entries.get(0).HTTPS.equals("true"));
		    assert(entryResponse.entries.get(0).Cors.equals("unknown"));
		    assert(entryResponse.entries.get(0).Link.equals("https://airvisual.com/api"));
		    assert(entryResponse.entries.get(0).Category.equals("Environment"));
		    
		    assert(entryResponse.entries.get(10).API.equals("Yahoo! Weather"));
		    assert(entryResponse.entries.get(10).Description.equals("Weather"));
		    assert(entryResponse.entries.get(10).Auth.equals("apiKey"));
		    assert(entryResponse.entries.get(10).HTTPS.equals("true"));
		    assert(entryResponse.entries.get(10).Cors.equals("unknown"));
		    assert(entryResponse.entries.get(10).Link.equals("https://developer.yahoo.com/weather/"));
		    assert(entryResponse.entries.get(10).Category.equals("Weather"));
		    
		    
		} 
		catch(Exception e){
			
			System.out.println(e);
			
			Assert.fail();
			
		}
	}
	
	@Test
	public void getEntriesByAuthTest() throws IOException  {
		
		
		String quesryString = "apiKey";
		
		String url = "https://api.publicapis.org/entries?auth=" + quesryString;
		 
		
		try {
		
		
		    StringBuilder content = getAPIResponse(url); 
		    
		    ObjectMapper objectMapper = new ObjectMapper();
		    
		    EntriesResponse entryResponse = objectMapper.readValue(content.toString(), new TypeReference<EntriesResponse>(){});
		    
		    assert(Integer.parseInt(entryResponse.count) == 247);
		    
		    for(EntriesResponse.Entry entry : entryResponse.entries) {
		    	
		    	assert(entry.Auth.contains(quesryString));
		    	
		    }
		   
		   
		    
		    assert(entryResponse.entries.get(0).API.equals("Cats"));
		    assert(entryResponse.entries.get(0).Description.equals("Pictures of cats from Tumblr"));
		    assert(entryResponse.entries.get(0).Auth.equals("apiKey"));
		    assert(entryResponse.entries.get(0).HTTPS.equals("true"));
		    assert(entryResponse.entries.get(0).Cors.equals("unknown"));
		    assert(entryResponse.entries.get(0).Link.equals("https://docs.thecatapi.com/"));
		    assert(entryResponse.entries.get(0).Category.equals("Animals"));
		    
		    assert(entryResponse.entries.get(246).API.equals("Yahoo! Weather"));
		    assert(entryResponse.entries.get(246).Description.equals("Weather"));
		    assert(entryResponse.entries.get(246).Auth.equals("apiKey"));
		    assert(entryResponse.entries.get(246).HTTPS.equals("true"));
		    assert(entryResponse.entries.get(246).Cors.equals("unknown"));
		    assert(entryResponse.entries.get(246).Link.equals("https://developer.yahoo.com/weather/"));
		    assert(entryResponse.entries.get(246).Category.equals("Weather"));
		    
		    
		} 
		catch(Exception e){
			
			System.out.println(e);
			
			Assert.fail();
			
		}
	}
	
	@Test
	public void getEntriesByHTTPSTest() throws IOException  {
		
		
		String quesryString = "false";
		
		String url = "https://api.publicapis.org/entries?https=" + quesryString;
		 
		
		try {
		
		
		    StringBuilder content = getAPIResponse(url); 
		    
		    ObjectMapper objectMapper = new ObjectMapper();
		    
		    EntriesResponse entryResponse = objectMapper.readValue(content.toString(), new TypeReference<EntriesResponse>(){});
		    
		    assert(Integer.parseInt(entryResponse.count) == 87);
		    
		    for(EntriesResponse.Entry entry : entryResponse.entries) {
		    	
		    	assert(entry.HTTPS.contains(quesryString));
		    	
		    }
		    
		    
		    assert(entryResponse.entries.get(0).API.equals("IUCN"));
		    assert(entryResponse.entries.get(0).Description.equals("IUCN Red List of Threatened Species"));
		    assert(entryResponse.entries.get(0).Auth.equals("apiKey"));
		    assert(entryResponse.entries.get(0).HTTPS.equals("false"));
		    assert(entryResponse.entries.get(0).Cors.equals("unknown"));
		    assert(entryResponse.entries.get(0).Link.equals("http://apiv3.iucnredlist.org/api/v3/docs"));
		    assert(entryResponse.entries.get(0).Category.equals("Animals"));
		    
		    
		    assert(entryResponse.entries.get(86).API.equals("ODWeather"));
		    assert(entryResponse.entries.get(86).Description.equals("Weather and weather webcams"));
		    assert(entryResponse.entries.get(86).Auth.equals(""));
		    assert(entryResponse.entries.get(86).HTTPS.equals("false"));
		    assert(entryResponse.entries.get(86).Cors.equals("unknown"));
		    assert(entryResponse.entries.get(86).Link.equals("http://api.oceandrivers.com/static/docs.html"));
		    assert(entryResponse.entries.get(86).Category.equals("Weather"));
		    
		    
		} 
		catch(Exception e){
			
			System.out.println(e);
			
			Assert.fail();
			
		}
	}
	
	
	@Test
	public void getEntriesByCorsTest() throws IOException  {
		
		
		String quesryString = "yes";
		
		String url = "https://api.publicapis.org/entries?cors=" + quesryString;
		 
		
		try {
		
		
		    StringBuilder content = getAPIResponse(url); 
		    
		    ObjectMapper objectMapper = new ObjectMapper();
		    
		    EntriesResponse entryResponse = objectMapper.readValue(content.toString(), new TypeReference<EntriesResponse>(){});
		    
		    assert(Integer.parseInt(entryResponse.count) == 103);
		    
		    for(EntriesResponse.Entry entry : entryResponse.entries) {
		    	
		    	assert(entry.Cors.contains(quesryString));
		    	
		    }
		    
		    
	
		    assert(entryResponse.entries.get(0).API.equals("Dogs"));
		    assert(entryResponse.entries.get(0).Description.equals("Based on the Stanford Dogs Dataset"));
		    assert(entryResponse.entries.get(0).Auth.equals(""));
		    assert(entryResponse.entries.get(0).HTTPS.equals("true"));
		    assert(entryResponse.entries.get(0).Cors.equals("yes"));
		    assert(entryResponse.entries.get(0).Link.equals("https://dog.ceo/dog-api/"));
		    assert(entryResponse.entries.get(0).Category.equals("Animals"));

		    
		    assert(entryResponse.entries.get(102).API.equals("Storm Glass"));
		    assert(entryResponse.entries.get(102).Description.equals("Global marine weather from multiple sources"));
		    assert(entryResponse.entries.get(102).Auth.equals("apiKey"));
		    assert(entryResponse.entries.get(102).HTTPS.equals("true"));
		    assert(entryResponse.entries.get(102).Cors.equals("yes"));
		    assert(entryResponse.entries.get(102).Link.equals("https://stormglass.io/"));
		    assert(entryResponse.entries.get(102).Category.equals("Weather"));
		    
		    
		} 
		catch(Exception e){
			
			System.out.println(e);
			
			Assert.fail();
			
		}
	}
	
	
	
	
	@Test
	public void getRandomEntryTest() {
		
		var url = "https://api.publicapis.org/random?auth=null";
		
		var catURL = "https://api.publicapis.org/categories";
		 
		
		try {
			
			StringBuilder catContent = getAPIResponse(catURL);
			
			ObjectMapper objectMapper = new ObjectMapper();
		
			List<String> catResponse =  objectMapper.readValue(catContent.toString(), new TypeReference<List<String>>(){});
			
		
		    StringBuilder content = getAPIResponse(url); 
		    
		    EntriesResponse entryResponse = objectMapper.readValue(content.toString(), new TypeReference<EntriesResponse>(){});
		    
		    assert(Integer.parseInt(entryResponse.count) == 1);
		    
		    
		    assert(catResponse.contains(entryResponse.entries.get(0).Category));
		   
		} 
		catch(Exception e){
			
			System.out.println(e);
			
			Assert.fail();
			
		}
		
	}
	
	
	@Test
	public void getAllCatagoriesTest() {
		
		var url = "https://api.publicapis.org/categories";
		
		try {
			
				StringBuilder content = getAPIResponse(url);
				
			    
				ObjectMapper objectMapper = new ObjectMapper();
			    
				List<String> catResponse =  objectMapper.readValue(content.toString(), new TypeReference<List<String>>(){});
			  
			    assert(catResponse.size() == 45);
			    
			    assert(catResponse.get(0).equals("Animals"));
			    assert(catResponse.get(20).equals("Geocoding"));
			    assert(catResponse.get(21).equals("Government"));
			    assert(catResponse.get(22).equals("Health"));
			    assert(catResponse.get(42).equals("Vehicle"));
			    assert(catResponse.get(43).equals("Video"));
			    assert(catResponse.get(44).equals("Weather"));
			 
		    
		   
		} 
		catch(Exception e){
			
			System.out.println(e);

			Assert.fail();
		}
		
	}

	
	@Test
	public void getHealthTest() {
		
		var url = "https://api.publicapis.org/health";
		
		try {
			
				StringBuilder content = getAPIResponse(url);
				
				ObjectMapper objectMapper = new ObjectMapper();
			    
				HealthResponse healthResponse =  objectMapper.readValue(content.toString(), new TypeReference<HealthResponse>(){});
			  
			   
			    assert(healthResponse.alive.equals("true"));
			 
		    
		   
		} 
		catch(Exception e){
			
			System.out.println(e);

			Assert.fail();
		}
		
		
		
	}
	

	private StringBuilder getAPIResponse(String url) throws IOException {
		 
		HttpURLConnection con = null;
		
		StringBuilder content = null;
		
		try {
		
		    var myurl = new URL(url);
		    
		    
		    con = (HttpURLConnection) myurl.openConnection();
		
		    con.setRequestMethod("GET");
		
		    try (BufferedReader in = new BufferedReader(
		            new InputStreamReader(con.getInputStream()))) {
		
		        String line;
		        content = new StringBuilder();
		
		        while ((line = in.readLine()) != null) {
		
		            content.append(line);
		            content.append(System.lineSeparator());
		        }
		    }
		
		} finally {
		
		    con.disconnect();
		}
		
		return content;
		
	}
	
}
