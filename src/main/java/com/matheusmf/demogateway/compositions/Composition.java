package com.matheusmf.demogateway.compositions;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.matheusmf.demogateway.configs.UrlConfiguration;

public abstract class Composition {
	
	@Autowired
    protected UrlConfiguration urls;
	
	abstract public void makeObjectsComposition(JSONObject jsonObject);
	
	public String compose(String response) {
		try {
			if(response.startsWith("[")) {
				JSONArray jsonArrayResponse = new JSONArray(response);
				int jsonArraySize = jsonArrayResponse.length();
				for (int i = 0; i < jsonArraySize; i++) {
		    		if(jsonArrayResponse.get(i) instanceof JSONObject) {
		    			JSONObject childObject = jsonArrayResponse.getJSONObject(i);
		    			this.makeComposition(childObject);
		    		}
		    	}
				response = jsonArrayResponse.toString();
				
			} else {
				JSONObject jsonResponse = new JSONObject(response);
				this.makeComposition(jsonResponse);
				response = jsonResponse.toString();
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	protected void makeComposition(JSONObject jsonObject) throws JSONException {
		this.makeObjectsComposition(jsonObject);
		
		Iterator<String> keys = jsonObject.keys();
		
		while(keys.hasNext()) {
			String objectKey = keys.next();
			
			if (jsonObject.get(objectKey) instanceof JSONObject) {
				JSONObject childObject = (JSONObject) jsonObject.get(objectKey);
		        this.makeComposition(childObject);
		    } else if (jsonObject.get(objectKey) instanceof JSONArray) {
		    	JSONArray jsonArray = (JSONArray) jsonObject.get(objectKey);
		    	for (int i = 0; i < jsonArray.length(); i++) {
		    		if(jsonArray.get(i) instanceof JSONObject) {
		    			JSONObject childObject = jsonArray.getJSONObject(i);
		    			this.makeComposition(childObject);
		    		}
		    	}
			}
		}
	}
	
	protected Boolean isNotNullOrNotEmpty(String string) {
		return string != null && !string.isEmpty() && string != "null";
	}
	
}
