package com.bookstore.dao;

import java.util.List;

public interface BookDAO<Book> {

	List<Book> getAll();

	Book searchBook(final Long bookId);

	Book createBook(final Book book);

	Book updateBook(final Long bookId, final Book book);

	void deleteBook(final Long bookId);
}
