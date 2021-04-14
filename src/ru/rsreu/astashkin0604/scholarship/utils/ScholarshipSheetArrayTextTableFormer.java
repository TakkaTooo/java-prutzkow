package ru.rsreu.astashkin0604.scholarship.utils;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.astashkin0604.scholarship.ScholarshipSheet;

public class ScholarshipSheetArrayTextTableFormer {

	private ScholarshipSheetArrayTextTableFormer() {
	}

	public static String formTextTable(ScholarshipSheet[] array, String labelResourceKey) {
		return Resourcer.getString(labelResourceKey) + ScholarshipSheetArrayTextTableFormer.formTextTableHeader()
				+ ScholarshipSheetArrayTextTableFormer.formTextTableFromScholarshipSheetArray(array);
	}

	private static String formTextTableHeader() {
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
	private static String formTextTableFromScholarshipSheetArray(ScholarshipSheet[] array) {
		StringBuilder output = new StringBuilder();
		for (ScholarshipSheet sheet : array) {
			if (sheet != ScholarshipSheet.NULL_SCHOLARSHIP_SHEET) {
				output.append(String.format(Resourcer.getString("runner.table.format"), sheet.getFullName(),
						((Integer) sheet.getScholarshipSize()).toString(), sheet.getStudyGroup().getNumber()));
			}
		}
		return output.toString();
	}
}
