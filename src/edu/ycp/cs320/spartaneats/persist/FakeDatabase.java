package edu.ycp.cs320.spartaneats.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.spartaneats.model.Account;
import edu.ycp.cs320.spartaneats.model.Drink;
import edu.ycp.cs320.spartaneats.model.Item;


public class FakeDatabase implements IDatabase {
	
	private List<Account> accountList;
	private List<Item> itemList;
	private List<Drink> drinkList;
 	
	public FakeDatabase() {
		accountList = new ArrayList<Account>();
		itemList = new ArrayList<Item>();
		drinkList = new ArrayList<Drink>();
		
		// Add initial data
		readInitialData();
		
		System.out.println(accountList.size() + " account");
		System.out.println(itemList.size() + " items");
		System.out.println(drinkList.size() + " drinks");
	}

	public void readInitialData() {
		try {
			accountList.addAll(InitialData.getAccount());
			itemList.addAll(InitialData.getItem());
			drinkList.addAll(InitialData.getDrink());
		} catch (IOException e) {
			throw new IllegalStateException("Couldn't read initial data", e);
		}
	}
	
	@Override
	public List<Pair<Author, Book>> findAuthorAndBookByTitle(String title) {
		List<Pair<Author, Book>> result = new ArrayList<Pair<Author,Book>>();
		for (Book book : bookList) {
			if (book.getTitle().equals(title)) {
				Author author = findAuthorByAuthorId(book.getAuthorId());
				result.add(new Pair<Author, Book>(author, book));
			}
		}
		return result;
	}

	private Author findAuthorByAuthorId(int authorId) {
		for (Author author : authorList) {
			if (author.getAuthorId() == authorId) {
				return author;
			}
		}
		return null;
	}

	@Override
	public List<Pair<Author, Book>> findAuthorandBookByAuthorLastName(String lastname) {
		int authorId = -1;
		List<Pair<Author,Book>> result = new ArrayList<Pair<Author, Book>>();
		for (Author author : authorList) {
			if (author.getLastname().equals(lastname)) {
				authorId = author.getAuthorId();
				for (Book book : bookList) {
					if(book.getAuthorId() == authorId) {
						result.add(new Pair<Author, Book>(author, book));
					}
				}
				
			}
		}
		return result;

	}

	@Override
	public List<Account> findAccountbyUserName(String userName) {
		List<Account> result = new ArrayList<Account>();
		for (Account account : accountList) {
			if (account.getUserName().equals(userName)) {
				result.add(account);
			}
		}
		return result;
	}

	@Override
	public List<Account> findAccountbyStudentID(String ID) {
		List<Account> result = new ArrayList<Account>();
		for (Account account : accountList) {
			if (account.getStudentID().equals(ID)) {
				result.add(account);
			}
		}
		return result;
	}

	@Override
	public List<Account> findAccountbyName(String lastName) {
		List<Account> result = new ArrayList<Account>();
		for (Account account : accountList) {
			if (account.getLastName().equals(lastName)) {
				result.add(account);
			}
		}
		return result;
	}

	@Override
	public List<Account> findAccountbyAccountID(int iD) {
		List<Account> result = new ArrayList<Account>();
		for (Account account : accountList) {
			if (account.getStudentID().equals(iD)) {
				result.add(account);
			}
		}
		return result;
	}

	@Override
	public List<Drink> findDrinkbyName(String name) {
		List<Drink> result = new ArrayList<Drink>();
		for (Drink drink : drinkList) {
			if (drink.getItemName().equals(name)) {
				result.add(drink);
			}
		}
		return result;
	}

	@Override
	public List<Drink> findAllDrinks() {
		List<Drink> result = new ArrayList<Drink>();
		for (Drink drink : drinkList) {
			result.add(drink);
		}
		return result;
	}

	@Override
	public List<Item> findItembyName(String name) {
		List<Item> result = new ArrayList<Item>();
		for (Item item : itemList) {
			if (item.getItemName().equals(name)) {
				result.add(item);
			}
		}
		return result;
	}

	@Override
	public List<Item> findAllItems() {
		List<Item> result = new ArrayList<Item>();
		for (Item item : itemList) {
			result.add(item);
		}
		return result;
	}

}
