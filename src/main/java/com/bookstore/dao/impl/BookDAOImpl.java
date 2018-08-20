package com.bookstore.dao.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import com.bookstore.dao.*;
import com.bookstore.entities.Book;


/**
 *         DAOimpl layer to work with book entity.        
 */
@Repository
public class BookDAOImpl implements BookDAO<Book> {

		List<Book> books = new ArrayList<Book>();

	@PostConstruct
	public void init() {
		
		Book temp = new Book();
		long t = 1l;
		for(int i = 0;i <= 3; i++)
		{
			temp.setBookName("Abook"+i);
			temp.setAuthor("AAuthor"+i);
			temp.setNumberOfBooks(i);
			temp.setBookid(t+i);
			books.add(temp);
		}
		

	}

	@Override
	public List<Book> getAll() {
		return books;
	}

	@Override
	public Book searchBook(final Long bookId) {
		if(null != books)
		{
			for(int i = 0 ;i<= books.size() ; i++)
			{
				if(bookId.equals(books.get(i).getBookid()))
				{
					return books.get(i);
				}
			}
			
		}
		return null;
		
	}

	@SuppressWarnings("unused")
	@Override
	public Book createBook( Book book) {
		
		for(int i =0; i<=books.size(); i++)
		{
			if(books.get(i).getBookid().equals(book.getBookid()))
			{
				throw new RuntimeException("Id already present.");
			}else{
				books.add(book);
				return book;
			}
		}
		return book;
		
	}

	@SuppressWarnings("unused")
	@Override
	public Book updateBook( Long bookId,  Book book) {
		
		for(int k = 0; k<=books.size();k++)
		{
			if(books.get(k).getBookid().equals(bookId))
			{
				books.remove(books.get(k));
				books.add(book);
				return book;
				
			}else{
				throw new RuntimeException("Invalid book id");
			}
		}
		return book;
	}

	@Override
	public void deleteBook( Long bookId) {
		
		for(int i = 0;i<=books.size();i++)
		{
			if(bookId.equals(books.get(i).getBookid()))
			{
				books.remove(books.get(i));
			}
		}
		
	}

}
