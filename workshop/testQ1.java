package workshop;

import java.util.Scanner;

public class testQ1 {

	public static void main(String[] args) {

		int items = 1;
		double cost = 1.0;
		Scanner input = new Scanner(System.in);
		shoppingBag sb = new shoppingBag(0.06);

		try {

			while (items != 0 || cost != 0) {

				System.out.println("Enter in the number of items in your cart or press 0 to exit");

				items = input.nextInt();

				if (items == 0) {

					break;
				}

				System.out.println("Enter in the cost");

				cost = input.nextDouble();

				if (cost == 0) {

					break;
				}

				sb.place(items, cost);

			}

			System.out.print(sb.toString());

			input.close();

		}

		catch (Exception ex) {

			ex.printStackTrace();

		}
	}

}
