package ru.rsreu.astashkin0604.scholarship.utils;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.astashkin0604.scholarship.ScholarshipSheet;

public class ScholarshipSheetArrayTextTableGenerator {

	private ScholarshipSheetArrayTextTableGenerator() {
		// TODO Auto-generated constructor stub
	}

	
	public static String tryGetTextTable(ScholarshipSheet[] array) {
		try {
			return ScholarshipSheetArrayTextTableGenerator.getTextTableFromScholarshipSheetArray(array);
		} catch (NullPointerException e) {
			return ScholarshipSheetArrayTextTableGenerator.getTextTableHeader();
		}
	}
	
	public static String getTextTableHeader() {
		StringBuilder output = new StringBuilder();
		output.append(String.format(Resourcer.getString("runner.table.format"),
				Resourcer.getString("runner.table.header.first"), Resourcer.getString("runner.table.header.second"),
				Resourcer.getString("runner.table.header.third")));
		return output.toString();
	}
	
	/**
	 * Generates a text table from a ScholarshipSheet array.
	 * 
	 * @param array - input array.
	 * @return String - text table from a ScholarshipSheet array.
	 */
	private static String getTextTableFromScholarshipSheetArray(ScholarshipSheet[] array) {
		StringBuilder output = new StringBuilder();
		output.append(getTextTableHeader());
		for (ScholarshipSheet sheet : array) {
			output.append(String.format(Resourcer.getString("runner.table.format"), sheet.getFullName(),
					((Integer) sheet.getScholarshipSize()).toString(), sheet.getStudyGroup().getNumber()));
		}
		return output.toString();
	}
}
