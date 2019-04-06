package com.matheusmf.demogateway.configs;

import org.springframework.beans.factory.annotation.Value;

/*
Classe utilitária para obter as urls e paths do gateway e apis.
*/
public class UrlConfiguration {

    @Value("${gateway.url}")
    private String gatewayUrl;
    
    @Value("${gateway.example.api.1.url}")
    private String exampleApi1Url;

    @Value("${gateway.example.api.2.url}")
    private String exampleApi2Url;

	public String getGatewayUrl() {
		return this.gatewayUrl;
	}

	public void setGatewayUrl(String gatewayUrl) {
		this.gatewayUrl = gatewayUrl;
    }
    
    public String getExampleApi1Url() {
		return this.exampleApi1Url;
	}

	public void setExampleApi1Url(String exampleApi1Url) {
		this.exampleApi1Url = exampleApi1Url;
    }
    
    public String getExampleApi2Url() {
		return this.exampleApi2Url;
	}

	public void setExampleApi2Url(String exampleApi2Url) {
		this.exampleApi2Url = exampleApi2Url;
	}


    


}