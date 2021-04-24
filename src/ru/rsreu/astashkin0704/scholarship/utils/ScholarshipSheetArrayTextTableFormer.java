package ru.rsreu.astashkin0704.scholarship.utils;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.astashkin0704.scholarship.ScholarshipSheet;

public class ScholarshipSheetArrayTextTableFormer {

	private ScholarshipSheetArrayTextTableFormer() {
	}

	/**
	 * Forms complete table with header.
	 * @param array - input array.
	 * @param labelResourceKey - resource key of label for table.
	 * @return string table by array.
	 */
	public static String formTextTable(ScholarshipSheet[] array, String labelResourceKey) {
		return Resourcer.getString(labelResourceKey) + ScholarshipSheetArrayTextTableFormer.formTextTableHeader()
				+ ScholarshipSheetArrayTextTableFormer.formTextTableFromScholarshipSheetArray(array);
	}

	/**
	 * Generates table header.
	 * @return string table header.
	 */
	private static String formTextTableHeader() {
		StringBuilder output = new StringBuilder();
		output.append(String.format(Resourcer.getString("table.format"), Resourcer.getString("table.header.first"),
				Resourcer.getString("table.header.second"), Resourcer.getString("table.header.third")));
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
				output.append(String.format(Resourcer.getString("table.format"), sheet.getFullName(),
						((Integer) sheet.getScholarshipSize()).toString(), sheet.getStudyGroup().getNumber()));
			}
		}
		return output.toString();
	}
}
