package model;

import java.util.HashMap;

import controller.InvalidMenuItemException;

public class Menu {
	
	private HashMap<String, MenuItem> items;
	
	public Menu() {
		items = new HashMap<>();
		items.put("JamessJicama", new JamesJicama());
		items.put("AustensAustere", new AustensAustere());
		items.put("TimsTorture", new TimsTorture());
		items.put("RyansRoast", new RyansRoast());
		items.put("ChunkyMilk", new ChunkyMilk());
		items.put("Water", new Water());
		items.put("Coke", new Coke());
	}
	
	public MenuItem getItem(String in) throws InvalidMenuItemException{
		if(items.get(in)==null) {
			throw new InvalidMenuItemException("Not on menu");
		}
		return items.get(in);
		
	}
	
	public String toString() {
		String ret = "Menu:\n";

		for(String item: items.keySet()) {
			ret += "  " + item + "\n";
		}
		return ret;
	}
}
