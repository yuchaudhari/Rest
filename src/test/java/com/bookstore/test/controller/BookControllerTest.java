package com.bookstore.test.controller;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bookstore.entities.*;
import com.bookstore.rest.BookController;
import com.bookstore.service.BookService;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class BookControllerTest {

	@Tested
	private BookController bookController;

	@Injectable
	private BookService bookService;

	

	@Test
	public void testGetAllBooks() {
		
		final Book testBook = new Book();
		new Expectations() {
			{
				bookService.getAll();
				result = Arrays.asList(testBook);
			}
		};
		 ResponseEntity<List<Book>> books = this.bookController.getAllBooks(WebServiceConstants.CLIENT_SECREATE);
		assertEquals(HttpStatus.OK, books.getStatusCode());
		
	}




	@Test
	public void createBook() {
		final Book testBook = new Book();
		new Expectations() {
			{
				bookService.save(testBook);
				result = testBook;
			}
		};
		 ResponseEntity<Book> createBook = this.bookController.createBook(testBook,WebServiceConstants.CLIENT_SECREATE);
		Assertions.assertThat(createBook.getStatusCode()).isEqualTo(HttpStatus.OK);
		
	}

	

	@Test
	public void updateBookDetail() {
		final Book testBook = new Book();
		final Long isbnId = 123L;
		new Expectations() {
			{
				bookService.update(anyLong, testBook);
				result = testBook;
			}
		};
		 ResponseEntity<Book> updateBookDetail = this.bookController.updateBookDetail(isbnId, testBook,WebServiceConstants.CLIENT_SECREATE);
		Assertions.assertThat(updateBookDetail.getStatusCode()).isEqualTo(HttpStatus.OK);
		
	}


	@Test
	public void deleteBook() {
		final Long isbnId = 123L;
		new Expectations() {
			{
				bookService.delete(anyLong);
			}
		};
		 ResponseEntity<Object> deleteBook = this.bookController.deleteBook(isbnId,WebServiceConstants.CLIENT_SECREATE);
		Assertions.assertThat(deleteBook.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}
