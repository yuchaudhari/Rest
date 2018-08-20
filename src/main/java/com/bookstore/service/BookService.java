package com.bookstore.service;

import java.util.List;

import com.bookstore.entities.Book;

public interface BookService  {
	List<Book> getAll();

	Book get(Long bookId);

	Book save(Book book);

	Book update(Long bookId, Book book);

	void delete(long bookId);
}
