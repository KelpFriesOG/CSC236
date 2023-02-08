package workshop;

import java.util.Scanner;

public class shoppingBag {

	int items;
	double price;
	double totalCost;
	double subTotal;
	double taxRate;

	Scanner input = new Scanner(System.in);

	//// Constructors/////////

	public shoppingBag() {

	}

	public shoppingBag(double taxRate) {

		this.taxRate = taxRate;

	}

	//// Getters & Setters/////////
	public int getItems(int items) {
		return items;
	}

	public void setItems(int items) {
		this.items = items;
	}

	public double getPrice(double cost) {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	///////// Methods///////////////Methods////////

	public void place(int items, double cost) {

		subTotal = subTotal + (items * cost);
		this.items += items;

	}

	public double getSubTotal() {// total without tax included//

		return subTotal;

	}

	public double total() {

		totalCost = subTotal + (subTotal * taxRate);

		return totalCost;

	}

	////////////// toString Method////////////////////////
	public String toString() {
		String result = "The bag contains " + items +
				" items. The retail cost of the items is " + getSubTotal() +
				" dollars. \nTotal cost is " + total() + " dollars.";
		return result;
	}

}
