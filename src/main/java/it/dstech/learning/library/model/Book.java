package it.dstech.learning.library.model;

import java.io.Serializable;

public class Book implements Serializable {

	private int id;
	private String title;
	private String author;
	private int year;
	private int total_copy;

	private int free_copy;

	public int getFree_copy() {
		return free_copy;
	}

	public void setFree_copy(int free_copy) {
		this.free_copy = free_copy;
	}

	public Book() {
	}

	public Book(Book book) {
		this.id = book.getId();
		this.title = book.getTitle();
		this.setAuthor(book.getAuthor());
		this.year = book.getYear();
		this.total_copy = book.getTotal_copy();
		this.free_copy = book.getFree_copy();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getTotal_copy() {
		return total_copy;
	}

	public void setTotal_copy(int total_copy) {
		this.total_copy = total_copy;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
