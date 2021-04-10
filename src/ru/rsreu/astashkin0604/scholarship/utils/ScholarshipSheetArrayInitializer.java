package ru.rsreu.astashkin0604.scholarship.utils;

import ru.rsreu.astashkin0604.scholarship.ScholarshipSheet;
import ru.rsreu.astashkin0604.scholarship.StudyGroup;

/**
 * Class - array initializer to implement the task in accordance with the option
 * 
 * @author Maxik
 *
 */
public class ScholarshipSheetArrayInitializer {

	private static final ScholarshipSheet[] INITIALIZED_ARRAY = {
			new ScholarshipSheet("Agafonov Pavel Alexeyevich", 1000, StudyGroup.GROUP_9413),
			new ScholarshipSheet("Chistyakov Pavel Andreevich", 2000, StudyGroup.GROUP_9413),
			new ScholarshipSheet("Ronzhin Alexey Vladimirovich", 1000, StudyGroup.GROUP_943),
			new ScholarshipSheet("Shaidulin Yuri Alexandrovich", 2000, StudyGroup.GROUP_943),
			new ScholarshipSheet("Pochtarev Artem Vladimirovich", 1000, StudyGroup.GROUP_944) };

	private ScholarshipSheetArrayInitializer() {

	}

	public static ScholarshipSheet[] getInitializedArray() {
		return INITIALIZED_ARRAY;
	}

}
