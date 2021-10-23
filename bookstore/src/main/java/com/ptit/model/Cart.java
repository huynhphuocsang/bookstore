package com.ptit.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
@Data
public class Cart {
	private final List<Items> items; 
	
	private BigDecimal total;

	public Cart() {
		super();
		this.items = new ArrayList<Items>();
		this.total = new BigDecimal(0); 
		
	} 
	public Items getItem(Book book) {
		for(Items item: items) {
			if(item.getBook().getIdBook() == book.getIdBook()) {
				return item; 
			}
		}
		return null; 
	}
	
	public int getItemCount() {
		return items.size(); 
	}
	
	public void addItem() {
		
	}
	public void addItem(Book book, int quantity) {
		Items item = this.getItem(book); 
		if(item!=null) {
			item.setQuantityOfBooks(item.getQuantityOfBooks()+quantity);
		}
		else {
			item = new Items(); 
			item.setBook(book);
			item.setQuantityOfBooks(quantity);
			items.add(item); 
			
		}
		this.total =this.total.add(book.getPrice().multiply(new BigDecimal(quantity)));  
	}
	
	public void updateItem(Book book, int quantity) {
		Items item = this.getItem(book); 
		if(item!=null) {
			item.setQuantityOfBooks(quantity);
			
			this.total  = BigDecimal.valueOf(0); 
			
			this.callTotalPrice();
		}
		
	}
	public void removeItem(Book book) {
		Items item = this.getItem(book); 
		if(item!=null) {
			items.remove(item); 
			
			this.total  = BigDecimal.valueOf(0); 
			this.callTotalPrice();
		}
	}
	public void clear() {
		this.items.clear();
		this.total = BigDecimal.valueOf(0); 
	}
	public boolean isEmpty() {
		return items.isEmpty(); 
	}
	public void callTotalPrice() {
		for(Items i: items) {
			BigDecimal priceOfItem = i.getBook().getPrice().multiply(BigDecimal.valueOf(i.getQuantityOfBooks())); 
			
			total = total.add(priceOfItem); 
		}
	}
}









































