package ru.rsreu.astashkin0504;

import com.prutzkow.resourcer.Resourcer;

public class ApplicationRunner {

	/**
	 * Default private constructor - so that you cannot create instances of the
	 * utility class.
	 */
	private ApplicationRunner() {

	}

	public static void main(String[] args) {
		StringBuilder output = new StringBuilder();
		Salad salad = new Salad(VegetablesInitializer.initializeVegetablesArray());
		output.append(Resourcer.getString("outputMessage.createdSalad")).append(salad).append("\n")
				.append(Resourcer.getString("outputMessage.totalCalorieContent"))
				.append(salad.getTotalCaloriesContent()).append(Resourcer.getString("outputMessage.kcal"));
		ChefService.sortSaladVegetables(salad);
		output.append(Resourcer.getString("outputMessage.sorterSalad")).append(salad).append("\n")
				.append(Resourcer.getString("outputMessage.searchingResult"))
				.append(ChefService.searchVegetable(salad, VegetablesInitializer.getSearchingVegetableInstance()));

		System.out.println(output);
	}
}
