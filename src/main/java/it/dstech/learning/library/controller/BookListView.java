package it.dstech.learning.library.controller;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import it.dstech.learning.library.model.Book;

@Named
@SessionScoped
public class BookListView {

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


	public List<Book> getListaLibri() {
		return listaLibri;
	}

	public void setListaLibri(List<Book> listaLibri) {
		this.listaLibri = listaLibri;
	}

}
