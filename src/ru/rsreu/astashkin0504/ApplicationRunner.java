package ru.rsreu.astashkin0504;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.astashkin0504.vegetables.Vegetable;

public class ApplicationRunner {

	/**
	 * Default private constructor - so that you cannot create instances of the
	 * utility class.
	 */
	private ApplicationRunner() {

	}

	public static void main(String[] args) {
		StringBuilder output = new StringBuilder();
		Vegetable[] salad = ChefService.createSalad();
		output.append(Resourcer.getString("outputMessage.createdSalad"))
				.append(ChefService.getStringSaladRepresentation(salad))
				.append(Resourcer.getString("outputMessage.totalCalorieContent"))
				.append(ChefService.getSaladCalorieContent(salad)).append(" ")
				.append(Resourcer.getString("outputMessage.kcal")).append("\n");
		ChefService.sortSaladVegetables(salad);
		output.append(Resourcer.getString("outputMessage.sorterSalad"))
				.append(ChefService.getStringSaladRepresentation(salad));
		System.out.println(output);
	}
}
