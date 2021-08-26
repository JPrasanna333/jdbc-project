package com.bookapp.dao;

import java.util.List;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotfoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public interface BookInter {
	void addBook(Book book);

	boolean deleteBook(int bookid) throws BookNotFoundException;

	boolean updateBook(int bookid, double price) throws BookNotFoundException;

	Book getBookById(int bookid) throws BookNotFoundException;

	List<Book> getBookByAuthor(String author) throws AuthorNotfoundException;

	List<Book> getBooksByCategory(String catogory) throws CategoryNotFoundException;

	List<Book> getAllBooks() throws BookNotFoundException;

}
