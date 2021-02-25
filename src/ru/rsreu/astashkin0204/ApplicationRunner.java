package ru.rsreu.astashkin0204;

import java.util.Scanner;

public class ApplicationRunner {

	/**
	 * Default private constructor so that you cannot create instances of the
	 * utility class
	 */
	private ApplicationRunner() {

	}

	/**
	 * Entry point
	 * 
	 * @param args - Launch parameters
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String output = null;
		System.out.print("Enter number of strings: n = ");
		int stringCount = Integer.parseInt(in.next());
		in.nextLine();

		String[] stringArray = new String[stringCount];
		for (int i = 0; i < stringArray.length; i++) {
			System.out.printf("Enter %d.string:\n", i + 1);
			stringArray[i] = in.nextLine();
		}

		for (int i = 0; i < stringArray.length; i++) {
			System.out.printf("Parameters for %d.string\n", i + 1);

			System.out.print("Enter quantity of entrys: ");
			int entryQuantity = Integer.parseInt(in.next());
			in.nextLine();

			System.out.println("Enter search substring: ");
			String searching = in.nextLine();

			System.out.println("Enter the substring to insert: ");
			String inserting = in.nextLine();

			stringArray[i] = stringArray[i].trim();

			try {
				output = StringByEntryInserter.insertNewSubstrToInpString(stringArray[i], entryQuantity, searching,
						inserting);
			} catch (IncorrectInputStringException e) {
				output = e.toString();
			} finally {
				System.out.println(output);
			}
		}

		in.close();
	}

}
