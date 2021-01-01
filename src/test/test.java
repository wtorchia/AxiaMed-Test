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

	
	@Test
	public void getAllAnimalsEntriesTest() throws IOException  {
		
		
		var url = "https://api.publicapis.org/entries?category=animals&https=true";
		 
		
		try {
		
		
		    StringBuilder content = getAPIResponse(url); 
		    
		    ObjectMapper objectMapper = new ObjectMapper();
		    
		    EntriesResponse entryResponse = objectMapper.readValue(content.toString(), new TypeReference<EntriesResponse>(){});
		    
		    assert(Integer.parseInt(entryResponse.count) == 11);
		    
		    
		    assert(entryResponse.entries.get(0).API.equals("Cat Facts"));
		    assert(entryResponse.entries.get(0).Description.equals("Daily cat facts"));
		    assert(entryResponse.entries.get(0).Auth.equals(""));
		    assert(entryResponse.entries.get(0).HTTPS.equals("true"));
		    assert(entryResponse.entries.get(0).Cors.equals("no"));
		    assert(entryResponse.entries.get(0).Link.equals("https://alexwohlbruck.github.io/cat-facts/"));
		    assert(entryResponse.entries.get(0).Category.equals("Animals"));
		    
		    
		    assert(entryResponse.entries.get(10).API.equals("Shibe.Online"));
		    assert(entryResponse.entries.get(10).Description.equals("Random pictures of Shibu Inu, cats or birds"));
		    assert(entryResponse.entries.get(10).Auth.equals(""));
		    assert(entryResponse.entries.get(10).HTTPS.equals("true"));
		    assert(entryResponse.entries.get(10).Cors.equals("yes"));
		    assert(entryResponse.entries.get(10).Link.equals("http://shibe.online/"));
		    assert(entryResponse.entries.get(10).Category.equals("Animals"));
		   
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
