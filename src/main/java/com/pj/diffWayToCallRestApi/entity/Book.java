package com.pj.diffWayToCallRestApi.entity;

import java.util.Objects;

public class Book {

	public Book(String id, String name, String author) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
		this.author = author;
	}

	public Book() {
		// TODO Auto-generated constructor stub
	}

	String id;
	
	String name;
	
	String author;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	
	
}
