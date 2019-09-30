package it.dstech.learning.library.controller;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.ws.rs.core.UriBuilder;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import it.dstech.learning.library.model.Book;

@Named
public class BookListView {
	public String MY_REST = "/book";

	ClientConfig config = new DefaultClientConfig();
	Client client = Client.create(config);
	WebResource service = client.resource(getBaseURI());
	WebResource restWS = service.path(MY_REST);

	private List<Book> listaLibri;

	@PostConstruct
	public List<Book> listaLibri() {
		RestTemplate restTemplate = new RestTemplate();
		Gson gson = new Gson();
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8888/book", String.class);
		Type listType = new TypeToken<List<Book>>() {
		}.getType();
		List<Book> posts = gson.fromJson(response.getBody(), listType);
		this.setListaLibri(posts);
		return this.getListaLibri();

	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8888").build();
	}

	public List<Book> getListaLibri() {
		return listaLibri;
	}

	public void setListaLibri(List<Book> listaLibri) {
		this.listaLibri = listaLibri;
	}

}
