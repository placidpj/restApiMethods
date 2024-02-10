package com.pj.diffWayToCallRestApi.controller;

import java.util.Enumeration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pj.diffWayToCallRestApi.entity.Book;
import com.pj.diffWayToCallRestApi.service.BookService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class BookController {

	@Autowired
	BookService bookService;
	
	@GetMapping("/books")
	public List<Book> getBooks(@RequestHeader("api-version") String apiVersion) {
		if (apiVersion.equalsIgnoreCase("1")) {
			return bookService.getNewBooks();
		}
		return bookService.getAllBooks();
	}
	
	// partial update of the resource
	@PatchMapping("/books/{id}")
	public List<Book> updateAuthor(@PathVariable("id") String id, @RequestParam("author") String author) {
		return bookService.updateAuthor( id, author);
	}
	
	// Complete update of the resource
	@PutMapping("/books")
	public List<Book> updateBook(@RequestBody Book book) {
		return bookService.updateBook(book);
	}
	
	@PostMapping("/books")
	public List<Book> addBook(@RequestBody Book book) {
		return bookService.addBook(book);
	}
	
	@DeleteMapping("/books/{id}")
	public List<Book> deleteBook(@PathVariable("id") String bookId) {
		return bookService.deleteBook(bookId);
	}
	
	@RequestMapping(value = "/books", method = RequestMethod.HEAD)
	public ResponseEntity<String> getBookMetadata() {
		HttpHeaders httpHeaders = new HttpHeaders();
		if (!bookService.getAllBooks().isEmpty()) {
			httpHeaders.add("size", "80 GB");
			httpHeaders.add("url", "http://bookDownload.google.com");
			httpHeaders.add("Language", "English");
			return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}
	
	@RequestMapping(value = "/books", method = RequestMethod.OPTIONS)
	public ResponseEntity<String> getBookOptions() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("allow-request-from-origin", "*");
		httpHeaders.add("allowed http methods", "PUT, POST, PATCH, DELETE, GET, HEAD, OPTIONS, TRACE");
		httpHeaders.add("allow-headers", "content-type, Authorization, Max-age");
		return new ResponseEntity<String>(httpHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/books", method = RequestMethod.TRACE)
	public ResponseEntity<String> handleTraceRequest(HttpServletRequest request) {
		StringBuilder result = new StringBuilder();
		result.append(request.getMethod()).append(" ").append(request.getRequestURI()).append(" ")
				.append(request.getProtocol()).append("\n");
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			result.append(headerName).append(":").append(request.getHeader(headerName)).append("\n");
		}
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "message/http");
        headers.set("Custom-Header", "SomeValue");
        
		return new ResponseEntity<>(result.toString(), headers, HttpStatus.OK.value());
	}
}
