package com.matheusmf.demogateway.compositions;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.matheusmf.demogateway.payloads.Api2Response;

@Component
public class Api1Composition extends Composition {
	
	@Override
	public void makeObjectsComposition(JSONObject jsonObject) {
		this.makeApi2Composition(jsonObject);
	}
	
	private void makeApi2Composition(JSONObject jsonObject) {
		
		try {
			if(jsonObject.has("api2Object") && !jsonObject.isNull("api2Object")) {
				JSONObject api2Object = jsonObject.getJSONObject("api2Object");
				if(api2Object.has("id")) {
					String api2ObjectId = api2Object.get("id").toString();
					if(isNotNullOrNotEmpty(api2ObjectId)) {
						api2Object.put("name", this.getName(api2ObjectId));
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	private String getName(String api2ObjectId) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Api2Response> response = 
					restTemplate.getForEntity(this.urls.getExampleApi2Url() + "/object?id=" + api2ObjectId, Api2Response.class);
			
			if(response.getStatusCode().equals(HttpStatus.OK)) {
				return response.getBody().getName();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
