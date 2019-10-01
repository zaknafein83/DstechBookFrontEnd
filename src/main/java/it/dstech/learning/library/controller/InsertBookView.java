package it.dstech.learning.library.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.core.UriBuilder;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;

import it.dstech.learning.library.model.Book;

@Named
@SessionScoped
public class InsertBookView {
	private static final String CREATE_BOOK = "http://localhost:8888/api/add";

	private Book book;

	@PostConstruct
	public void init() {
		book = new Book();
	}

	public String createBook() throws RestClientException, URISyntaxException {
		String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Cookie", "JSESSIONID=" + sessionId);


		HttpEntity<Book> requestEntity = new HttpEntity<>(getBook(), headers);

		
        ResponseEntity<String> response = restTemplate.exchange(new URI(CREATE_BOOK), HttpMethod.POST, requestEntity, String.class);

//		RequestEntity request = RequestEntity.post(getPostURI()).accept(MediaType.APPLICATION_JSON).body(getBook());
//		ResponseEntity<String> response = restTemplate.exchange(CREATE_BOOK,HttpMethod.POST,requestEntity, String.class);

//		restTemplate.exchange(getPostURI(),, responseType)
//		ResponseEntity<String> response = restTemplate.postForEntity(getPostURI(), getBook(), String.class);
		System.out.println(response);
		if (response != null && response.getHeaders().getLocation().getPath().contains("success")) {
			return "helloworld.xhtml";
		}
		return "error.xhtml";

	}


	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
