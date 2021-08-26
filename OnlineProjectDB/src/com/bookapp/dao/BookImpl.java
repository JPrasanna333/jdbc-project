package com.bookapp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotfoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public class BookImpl implements BookInter {

	@Override
	public void addBook(Book book) {
		PreparedStatement ps = null;

		String sql = "insert into book values(?,?,?,?,?)";
		try {
			ps = ModelDAO.getConnection().prepareStatement(sql);
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getCategory());
			ps.setDouble(4, book.getPrice());
			ps.setInt(5, book.getBookid());

			if (ps.execute() == false) {
				System.out.println("Table is  inserted");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ModelDAO.closeConnection();
		}

	}

	@Override
	public boolean deleteBook(int bookid) throws BookNotFoundException {
		PreparedStatement ps = null;

		boolean check = true;
		String sql = "delete from book where bookid=?";
		try {
			ps = ModelDAO.getConnection().prepareStatement(sql);
			ps.setInt(1, bookid);
			check = ps.execute();
			if (check != false) {
				throw new BookNotFoundException("Book is not foun for the given id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ModelDAO.closeConnection();
		}
		return check;
	}

	@Override
	public boolean updateBook(int bookid, double price) throws BookNotFoundException {
		PreparedStatement ps = null;
		boolean b = true;
		String updateQuery = "update book set price=? where bookid=?";
		try {
			ps = ModelDAO.getConnection().prepareStatement(updateQuery);
			ps.setDouble(1, price);
			ps.setInt(2, bookid);
			b = ps.execute();
			if (b) {
				throw new BookNotFoundException("Book is not found");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ModelDAO.closeConnection();
		}
		
		return b;
	}

	@Override
	public Book getBookById(int bookid) throws BookNotFoundException {
		PreparedStatement ps = null;
		Boolean flag = false;
		Book book = new Book();
		String idSql = ("Select * from book where bookid=?");
		try {
			ps = ModelDAO.getConnection().prepareStatement(idSql);
			ps.setInt(1, bookid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String title = rs.getString(1);
				String author = rs.getString(2);
				String category = rs.getString(3);
				double price = rs.getDouble(4);
				int bookid1 = rs.getInt(5);
				book.setTitle(title);
				book.setAuthor(author);
				book.setCatogory(category);
				book.setPrice(price);
				book.setBookid(bookid1);
				flag = true;
			}
			if (!flag) {
				throw new BookNotFoundException("No  bookid was founded");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ModelDAO.closeConnection();
		}
		return book;
	}

	@Override
	public List<Book> getBookByAuthor(String author) throws AuthorNotfoundException {
		PreparedStatement ps = null;
		List<Book> bookByAuthor = new ArrayList<>();
		try {
			String authorSql = "select * from book where author=?";
			ps = ModelDAO.getConnection().prepareStatement(authorSql);
			ps.setString(1, author);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				String title = rs.getString(1);
				String author1 = rs.getString(2);
				String category = rs.getString(3);
				double price = rs.getDouble(4);
				int bookid1 = rs.getInt(5);
				book.setTitle(title);
				book.setAuthor(author1);
				book.setCatogory(category);
				book.setPrice(price);
				book.setBookid(bookid1);
				bookByAuthor.add(book);
			}
			if (bookByAuthor.isEmpty()) {
				throw new AuthorNotfoundException("Author was not matched");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ModelDAO.closeConnection();
		}
		return bookByAuthor;
	}

	@Override
	public List<Book> getBooksByCategory(String catogory) throws CategoryNotFoundException {
		PreparedStatement ps = null;
		List<Book> booksByCategory = new ArrayList<>();
		try {
			String categorySql = "select * from book where category =?";
			ps = ModelDAO.getConnection().prepareStatement(categorySql);
			ps.setString(1, catogory);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				String title = rs.getString(1);
				String author1 = rs.getString(2);
				String category = rs.getString(3);
				double price = rs.getDouble(4);
				int bookid1 = rs.getInt(5);
				book.setTitle(title);
				book.setAuthor(author1);
				book.setCatogory(category);
				book.setPrice(price);
				book.setBookid(bookid1);
				booksByCategory.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ModelDAO.closeConnection();
		}
		if (booksByCategory.isEmpty()) {
			throw new CategoryNotFoundException("Category was not matched");
		}
		return booksByCategory;

	}

	@Override
	public List<Book> getAllBooks() throws BookNotFoundException {
		PreparedStatement ps = null;
		List<Book> allBooks = new ArrayList<>();
		try {
			String categorySql = "select * from book";
			ps = ModelDAO.getConnection().prepareStatement(categorySql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				String title = rs.getString(1);
				String author1 = rs.getString(2);
				String category = rs.getString(3);
				double price = rs.getDouble(4);
				int bookid1 = rs.getInt(5);
				book.setTitle(title);
				book.setAuthor(author1);
				book.setCatogory(category);
				book.setPrice(price);
				book.setBookid(bookid1);
				allBooks.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ModelDAO.closeConnection();
		}
		if (allBooks.isEmpty()) {
			throw new BookNotFoundException("no book available");
		}
		return allBooks;
	}

}
