package com.pj.diffWayToCallRestApi.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pj.diffWayToCallRestApi.entity.Book;

@Service
public class BookService {

	static List<Book> allBooks;
	static List<Book> newBooks;
	
	// It executes only once, when the class is first loaded into memory.
	static {
		allBooks = new ArrayList<>();
		allBooks.add(new Book("1", "c programming", "Manish paul"));
		allBooks.add(new Book("2", "c++ programming", "Ramsingh"));
		allBooks.add(new Book("3", "Java programming", "Vimal"));
		allBooks.add(new Book("4", "Oracle", "Ajay dhara"));
		allBooks.add(new Book("5", "Pascal", "Surajmal"));
		
		newBooks = new ArrayList<>();
		newBooks.add(new Book("1", "Spring boot", "Manish paul"));
		newBooks.add(new Book("2", "Microservices", "Ramsingh"));
		
	}
	
	
	public List<Book> getAllBooks() {
		return allBooks;
	}
	
	public List<Book> getNewBooks() {
		return newBooks;
	}


	public List<Book> updateAuthor(String id, String authorName) {
		// TODO Auto-generated method stub
		for (Book book : allBooks) {
			if (book.getId().equalsIgnoreCase(id)) {
				book.setAuthor(authorName);
			}
		}
		return allBooks;
	}


	public List<Book> addBook(Book book) {
		// TODO Auto-generated method stub
		allBooks.add(book);
		return allBooks;
	}


	public List<Book> deleteBook(String bookId) {
		// TODO Auto-generated method stub
		Iterator<Book> iterator = allBooks.iterator();
		while (iterator.hasNext()) {
			Book book = iterator.next();
			if (book.getId().equalsIgnoreCase(bookId)) {
				iterator.remove();
				break;
			}
		}
		return allBooks;
	}

	public List<Book> updateBook(Book updatedBook) {
		// TODO Auto-generated method stub
		Iterator<Book> iterator = allBooks.iterator();
		while (iterator.hasNext()) {
			Book book = iterator.next();
			if (book.getId().equalsIgnoreCase(updatedBook.getId())) {
				book.setName(updatedBook.getName());
				book.setAuthor(updatedBook.getAuthor());
				break;
			}
		}
		return allBooks;
	}
}
