package com.bookstore.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bookstore.dao.impl.BookDAOImpl;
import com.bookstore.entities.Book;
import com.bookstore.service.BookService;

/**
 *         Service class to implement business logic on books.
 */
@Service
public class BookServiceImpl implements BookService {

	

	@Resource
	private BookDAOImpl bookDAOImpl;

	@Override
	public List<Book> getAll() {
		
		return bookDAOImpl.getAll();
	}

	@Override
	public Book get(final Long bookId) {
		
		return bookDAOImpl.searchBook(bookId);
	}

	@Override
	public Book save(final Book book) {
		
		return bookDAOImpl.createBook(book);
	}

	@Override
	public Book update(final Long bookId, final Book book) {
		
		return bookDAOImpl.updateBook(bookId, book);
	}

	@Override
	public void delete( long bookId) {
		
		bookDAOImpl.deleteBook(bookId);
	}

}
