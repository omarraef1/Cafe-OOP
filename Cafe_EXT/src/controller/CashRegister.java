package controller;

import java.util.Scanner;

import model.Beverage;
import model.FoodItem;
import model.Menu;
import model.MenuItem;
import model.PunchCard;
import model.SalesQuery;

public class CashRegister {

	public static void main(String[] args) {
		Menu menu = new Menu();
		LoyaltyProgram loyalty = new LoyaltyProgram();
		SalesLog salesLog = new SalesLog();

		Scanner input = new Scanner(System.in);

		// loop control variable for do/while
		boolean storeOpen = true;
		do {
			// display greeting messages and set up user's punch card
			System.out.println("Welcome to BeyondBrad, what's your name? ");
			
			String customerName = input.next();

			System.out.print(menu);

			PunchCard userCard = loyalty.getPunchCard(customerName);
			userCard.addVisit();

			// keeps track of the running total of orders for this transaction
			double currentTab = 0;

			System.out.print("What would you like to order? ");
			String userInput = input.next().trim();
			while (!userInput.equalsIgnoreCase("no")) {
				try {
				MenuItem itemPurchased = menu.getItem(userInput);
				
				System.out.println(itemPurchased.getQuip());
				currentTab += itemPurchased.getPrice();
				salesLog.addItem(itemPurchased);
				}
				 catch (InvalidMenuItemException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}

				// prompt for next input
				System.out.print("Anything else? (enter item name or \"no\" to ring out) ");
				userInput = input.next().trim();
				
			}

			// apply discount
			if(userCard.isNthVisit(LoyaltyProgram.VISITS_FOR_DISCOUNT)) {
				System.out.println("Congratulations! You get a discount for being a loyal customer.");
				currentTab *= (1 - LoyaltyProgram.DISCOUNT); 
			}

			// display goodbye messages
			System.out.printf("Cool, that will be $%.2f, take care!\n", currentTab);
			
			System.out.println("Enter:\n\t\"exit\" to close down the store");
			System.out.println("\t\"next\" for the next customer");
			System.out.println("\t\"stats\" for the day's sales");	
			
			String command = input.next().trim().toLowerCase();	
			
			switch(command) {
			case "exit":
				storeOpen = false;
				break;
			case "stats":
				printStatistics(salesLog);
				break;
			}
		} while (storeOpen);
		
		input.close();
	}
	
	public static void printStatistics(SalesLog log) {
		System.out.print("Number of items sold today: ");
		
		// runQuery() will call the passed lambda function once per
		// item in the sales log. It will take the returned value
		// of the lambda, add it up and return the sum.
		//
		// If we pass a function that for each item simply returns 1,
		// the sum of 1 added N times is N, the total number of items 
		SalesQuery countQuery = item -> 1;
		System.out.println(log.runQuery(countQuery));
		
		System.out.print("Total income today: ");
		
		// We can also query the item passed and get a property of the
		// item like it's price.
		SalesQuery totalQuery = item -> item.getPrice();
		System.out.println(log.runQuery(totalQuery));
		
		// TODO Display how many items sold today were Beverages
		// Do not add any properties to Beverages, simply determine
		// if the item in the SalesLog IsA Beverage
		System.out.print("Total Beverages today: ");
		SalesQuery bevQuery = item -> {
			int c = 0;
			if(item instanceof Beverage) {
				c++;
			}
			return c;
		};
		System.out.println(log.runQuery(bevQuery));
		
		
		// TODO Display the total money spent on Food items.
		// This is similar to each of the three queries above.
		System.out.print("Total Food income today: ");
		
		SalesQuery foodQuery = item -> {
			double c = 0.0;
			if(item instanceof FoodItem) {
				c += item.getPrice();
			}
			return c;
		};
		
		System.out.println(log.runQuery(foodQuery));
	}
}
