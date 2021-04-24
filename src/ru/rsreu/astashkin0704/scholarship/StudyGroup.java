package ru.rsreu.astashkin0704.scholarship;

import com.prutzkow.resourcer.Resourcer;

/**
 * Describes the nature of the study group for students
 * 
 * @author Maxim Astashkin
 *
 */
public enum StudyGroup {

	GROUP_9413(Resourcer.getString("studygroup.text.9413")), GROUP_943(Resourcer.getString("studygroup.text.943")),
	GROUP_944(Resourcer.getString("studygroup.text.944"));

	/**
	 * Group number textual representation
	 */
	private String number;

	StudyGroup(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}
}
