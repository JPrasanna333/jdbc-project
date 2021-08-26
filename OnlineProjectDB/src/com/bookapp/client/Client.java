package com.bookapp.client;

import java.util.Scanner;

import com.bookapp.bean.Book;
import com.bookapp.dao.BookImpl;
import com.bookapp.dao.BookInter;
import com.bookapp.exception.AuthorNotfoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public class Client {

	public static void main(String[] args) {
		BookInter service = new BookImpl();
		Book book = new Book();

		Scanner kb = new Scanner(System.in);

		while (true) {

			System.out.println(
					"\nPlease enter your choice\n" + "1.To get all the Book\n" + "2.To get the book by the id\n"
							+ "3.To get book by the author\n" + "4.To get book by the category\n"
							+ "5.to add the book\n" + "6.To update a book\n" + "7.To delete a Book\n10.To exit");
			int choice = kb.nextInt();
			switch (choice) {
			case 1:
				try {
					for (Book allBook : service.getAllBooks()) {
						System.out.println(allBook);
					}

				} catch (BookNotFoundException e) {

					System.out.println(e.getMessage());
				}
				break;
			case 2:
				System.out.println("\nPlease enter the ID of the book you want to find");
				int id = kb.nextInt();
				try {
					System.out.println(service.getBookById(id));
				} catch (BookNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("\nPlease enter the author name of the book you want to find");
				String author = kb.next();
				try {
					for (Book bookAuthor : service.getBookByAuthor(author)) {
						System.out.println(bookAuthor);
					}
				} catch (AuthorNotfoundException e) {

					System.out.println(e.getMessage());
				}
				break;

			case 4:
				System.out.println("\nPlease enter category of the book you want to find");
				String category = kb.next();
				try {
					for (Book bookCategory : service.getBooksByCategory(category)) {
						System.out.println(bookCategory);
					}
				} catch (CategoryNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 5:
				System.out.println("Enter title");
				String titlename = kb.next();
				System.out.println("Enter author name");
				String authorname = kb.next();
				System.out.println("Enter the category");
				String categoryName = kb.next();
				System.out.println("Enter the price");
				double priceValue = kb.nextDouble();
				System.out.println("Enter the book id");
				int idValue = kb.nextInt();
				book.setTitle(titlename);
				book.setAuthor(authorname);
				book.setCatogory(categoryName);
				book.setPrice(priceValue);
				book.setBookid(idValue);
				service.addBook(book);
				break;

			case 6: {
				System.out.println("Enter the id of the book to update");
				int updateId = kb.nextInt();
				System.out.println("Enter the price of the book to update");
				double updatePrice = kb.nextDouble();
				try {
					if (service.updateBook(updateId, updatePrice) != true) {
						System.out.println("Book is updated");
					} else {
						System.out.println("Book not updated");
					}
				} catch (BookNotFoundException e) {
					e.printStackTrace();
				}

			}
			break;

			case 7:
				System.out.println("Enter the id of the book to delete");
				int idDelete = kb.nextInt();
				try {
					Boolean b = service.deleteBook(idDelete);
					if (b != true) {
						System.out.println("Book is deleted");

					} else {
						System.out.println("Book is not deleted");

					}
				} catch (BookNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 8:
				System.out.println("exiting...");
				kb.close();
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice");

			}

		}

	}

}
