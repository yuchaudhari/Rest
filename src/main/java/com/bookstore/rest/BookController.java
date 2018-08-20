package com.bookstore.rest;


import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.entities.*;
import com.bookstore.service.BookService;


/**
 * 
 *         Rest controller for book service.
 */
@RestController
@RequestMapping(WebServiceConstants.BOOK)
public class BookController {

	@Autowired
	private BookService bookService;
	
	private static String decode(final String encoded) {
		byte[] decodedBytes = Base64.getDecoder().decode(encoded);
		String decodedString = new String(decodedBytes);
        return decodedString;
    }

		@RequestMapping(value = WebServiceConstants.SEPARATOR, method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Book>> getAllBooks(@RequestParam String encoded) {
		String resString = decode(encoded);
		 List<Book> books = null;
		if(resString.equals(WebServiceConstants.CLIENT_SECREATE))
		{
			books = bookService.getAll();
		if (CollectionUtils.isEmpty(books)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		}
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}

	/**
	 * return book for a given id.
	 */
	@RequestMapping(value = WebServiceConstants.SEARCH, method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Book> getBook(@PathVariable  Long bookId,@RequestParam String encoded) {
		String resString = decode(encoded);
		if(resString.equals(WebServiceConstants.CLIENT_SECREATE))
		{
		 Book book= bookService.get(bookId);
		if (book != null) {
			return new ResponseEntity<Book>(book, HttpStatus.OK);
		}
		
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Creates a new book.
	 *
	 */
	@RequestMapping(value = WebServiceConstants.SEPARATOR, method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Book> createBook(@RequestBody final Book book,@RequestParam String encoded) {
		String resString = decode(encoded);
		if(resString.equals(WebServiceConstants.CLIENT_SECREATE))
		{
		 Book dbSave = bookService.save(book);
		if (dbSave != null) {
			return new ResponseEntity<Book>(dbSave, HttpStatus.OK);
		}
		
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 
	 * Updates a book.
	 * 
	 */
	@RequestMapping(value = WebServiceConstants.SEARCH, method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Book> updateBookDetail(@PathVariable final Long bookId,
			@RequestBody final Book book,@RequestParam String encoded) {
		String resString = decode(encoded);
		if(resString.equals(WebServiceConstants.CLIENT_SECREATE))
		{
		 Book updatedBook = bookService.update(bookId, book);
		if (updatedBook != null) {
			return new ResponseEntity<Book>(updatedBook, HttpStatus.OK);
		}
		
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Deletes a book for given id.
	 */
	@RequestMapping(value = WebServiceConstants.SEARCH, method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Object> deleteBook(@PathVariable final Long bookId,@RequestParam String encoded) {
		String resString = decode(encoded);
		if(resString.equals(WebServiceConstants.CLIENT_SECREATE))
		{
		bookService.delete(bookId);
		return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
