package it.dstech.learning.test;

import java.lang.reflect.Type;
import java.util.List;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import it.dstech.learning.library.model.Book;

public class RestTest {

	@Test
	public void testRest() {
		RestTemplate restTemplate = new RestTemplate();
		Gson gson = new Gson();
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8888/book", String.class);
		Type listType = new TypeToken<List<Book>>(){}.getType();
		List<Book> posts = gson.fromJson(response.getBody(), listType);


		for (Book book : posts) {
			System.out.println(book.getAuthor() + " " + book.getTitle());
		}

	}
}
