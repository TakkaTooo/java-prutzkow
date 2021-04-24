package ru.rsreu.astashkin0704.scholarship;

import java.io.Serializable;
import java.util.Objects;

import com.prutzkow.resourcer.Resourcer;

/**
 * Describes the essence of the scholarship sheet
 * 
 * @author Maxim Astashkin
 *
 */
public class ScholarshipSheet implements Comparable<ScholarshipSheet>, Serializable {

	private static final long serialVersionUID = -4282382041909255512L;

	public static final ScholarshipSheet NULL_SCHOLARSHIP_SHEET = new ScholarshipSheet() {

		private static final long serialVersionUID = -7119405955319705968L;

		@Override
		public int compareTo(ScholarshipSheet o) {
			return -1;
		}

		@Override
		public String toString() {
			return "";
		}

	};

	/**
	 * Default value for full name of student;
	 */
	private static final String DEFAULT_FULL_NAME = "";

	/**
	 * Default value for the size of the scholarship
	 */
	private static final int DEFAULT_SCHOLARSHIP_SIZE = 0;

	/**
	 * Default value for study group of student;
	 */
	private static final StudyGroup DEFAULT_GROUP = StudyGroup.GROUP_9413;

	/**
	 * Surname, name, middle name of the student
	 */
	private String fullName = DEFAULT_FULL_NAME;

	/**
	 * Scholarship size
	 */
	private int scholarshipSize = DEFAULT_SCHOLARSHIP_SIZE;

	/**
	 * Student study group
	 */
	private StudyGroup studyGroup = DEFAULT_GROUP;

	public ScholarshipSheet(String fullName, int scholarshipSize, StudyGroup studyGroup) {
		this.fullName = fullName;
		if (scholarshipSize > 0) {
			this.scholarshipSize = scholarshipSize;
		}
		this.studyGroup = studyGroup;
	}

	private ScholarshipSheet() {

	}

	public ScholarshipSheet(String fullName, StudyGroup studyGroup) {
		this(fullName, 0, studyGroup);
	}

	public String getFullName() {
		return fullName;
	}

	public int getScholarshipSize() {
		return scholarshipSize;
	}

	public StudyGroup getStudyGroup() {
		return studyGroup;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fullName, scholarshipSize, studyGroup);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ScholarshipSheet)) {
			return false;
		}
		ScholarshipSheet other = (ScholarshipSheet) obj;
		return Objects.equals(fullName, other.fullName) && scholarshipSize == other.scholarshipSize
				&& studyGroup == other.studyGroup;
	}

	@Override
	public int compareTo(ScholarshipSheet o) {
		return ((Integer) this.scholarshipSize).compareTo((Integer) o.scholarshipSize);
	}

	@Override
	public String toString() {
		return String.format(Resourcer.getString("scholarshipsheet.formatstring"), fullName, scholarshipSize,
				studyGroup.getNumber());
	}
}
