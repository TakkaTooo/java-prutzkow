package ru.rsreu.astashkin0704.scholarship;

import java.util.Comparator;

/**
 * Comparator for sorting collections of ScholarshipSheet by two parametres
 */
public class ScholarshipSheetCompare implements Comparator<ScholarshipSheet> {

	public ScholarshipSheetCompare() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(ScholarshipSheet o1, ScholarshipSheet o2) {
		int result = o1.compareTo(o2);
		if (result == 0) {
			result = extractIntegerStudyGroupNumber(o1).compareTo(extractIntegerStudyGroupNumber(o2));
		}
		return result;
	}

	/**
	 * Parse study group number string to Integer If the group contains a letter
	 * designation at the end, it is ignored.
	 * 
	 * @param sheet - The list from which you want to extract the group number.
	 * @return
	 */
	private static Integer extractIntegerStudyGroupNumber(ScholarshipSheet sheet) {
		Integer result;
		try {
			result = Integer.parseInt(sheet.getStudyGroup().getNumber());
		} catch (NumberFormatException e) {
			String number = sheet.getStudyGroup().getNumber();
			result = Integer.parseInt(number.substring(0, number.length()));
		}
		return result;
	}
}
