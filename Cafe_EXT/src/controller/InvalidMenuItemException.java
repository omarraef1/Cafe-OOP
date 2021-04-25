package controller;

public class InvalidMenuItemException extends Exception {
	public InvalidMenuItemException(String message) {
		super(message);
	}
	
	public String toString() {
		return "Item not on menu, please try again!\n" 
	+ getMessage();
		
	}
}
