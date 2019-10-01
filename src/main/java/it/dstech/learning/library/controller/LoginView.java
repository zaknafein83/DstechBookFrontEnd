package it.dstech.learning.library.controller;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Named
@SessionScoped
public class LoginView {
	private static final String LOGIN = "http://localhost:8888/login";

	private String username;
	private String password;

	public String send() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("username", getUsername());
		map.add("password", getPassword());
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		ResponseEntity<String> response = restTemplate.postForEntity(LOGIN, request, String.class);
		if (response.getHeaders().getLocation().getPath().contains("success")) {
			
			System.out.println(response.getHeaders().get("Set-Cookie"));
			return "inserisciLibro.xhtml";
		}
		return "error.xhtml";

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
