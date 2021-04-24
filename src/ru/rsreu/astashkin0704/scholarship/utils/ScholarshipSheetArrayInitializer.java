package ru.rsreu.astashkin0704.scholarship.utils;

import ru.rsreu.astashkin0704.scholarship.ScholarshipSheet;
import ru.rsreu.astashkin0704.scholarship.StudyGroup;

/**
 * Class - array initializer to implement the task in accordance with the
 * option.
 * 
 */
public class ScholarshipSheetArrayInitializer {

	/**
	 * Instance for searching, which is in an array.
	 */
	private static final ScholarshipSheet SEARCH_INSTANCE_IN_ARRAY = new ScholarshipSheet(
			"Pochtarev Artem Vladimirovich", 1000, StudyGroup.GROUP_944);

	/**
	 * Instance for search that is not in the array.
	 */
	private static final ScholarshipSheet SEARCH_INSTANCE_OUT_ARRAY = new ScholarshipSheet("Sokolova Yulia Anatolievna",
			1500, StudyGroup.GROUP_943);

	private static final ScholarshipSheet[] INITIALIZED_ARRAY = {
			new ScholarshipSheet("Agafonov Pavel Alexeyevich", 1000, StudyGroup.GROUP_9413),
			new ScholarshipSheet("Chistyakov Pavel Andreevich", 2000, StudyGroup.GROUP_9413),
			new ScholarshipSheet("Ronzhin Alexey Vladimirovich", 1000, StudyGroup.GROUP_943),
			new ScholarshipSheet("Shaidulin Yuri Alexandrovich", 2000, StudyGroup.GROUP_943),
			SEARCH_INSTANCE_IN_ARRAY };

	private ScholarshipSheetArrayInitializer() {

	}

	public static ScholarshipSheet[] getInitializedArray() {
		return INITIALIZED_ARRAY;
	}

	/**
	 * @return instance for searching, which is in an array.
	 */
	public static ScholarshipSheet getInstanceForSearchInArray() {
		return SEARCH_INSTANCE_IN_ARRAY;
	}

	/**
	 * @return instance for search that is not in the array.
	 */
	public static ScholarshipSheet getInstanceForSearchOutArray() {
		return SEARCH_INSTANCE_OUT_ARRAY;
	}
}
