package ru.rsreu.astashkin0704.scholarship.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import ru.rsreu.astashkin0704.scholarship.ScholarshipSheet;
import ru.rsreu.astashkin0704.scholarship.ScholarshipSheetCompare;
import ru.rsreu.astashkin0704.scholarship.StudyGroup;

/**
 * A utility class that implements such operations with collections of
 * ScholarshipSheet as: search in a map by value, by key; removal from the set
 * by the value of the study group; standard sorting, sorting by 2 fields;
 * retrieving unique scholarship sizes
 */
public class ScholarshipSheetUtilite {

	private ScholarshipSheetUtilite() {
	}

	/**
	 * Searches for an instance by value in a map.
	 * 
	 * @param map   - input map.
	 * @param value - searching value.
	 * @return found instance or NULL_SCHOLARSHIP_SHEET
	 */
	public static ScholarshipSheet searchSheetInMapByValue(Map<String, ScholarshipSheet> map, ScholarshipSheet value) {
		List<ScholarshipSheet> values = new ArrayList<ScholarshipSheet>(map.values());
		int index = Collections.binarySearch(values, value);
		if (index >= 0) {
			return values.get(index);
		} else {
			return ScholarshipSheet.NULL_SCHOLARSHIP_SHEET;
		}
	}

	/**
	 * Searches for an instance by fullName (key) in a map.
	 * 
	 * @param map      - input map.
	 * @param fullName - searching key.
	 * @return found instance or NULL_SCHOLARSHIP_SHEET
	 */
	public static ScholarshipSheet searchSheetInMapByFullName(Map<String, ScholarshipSheet> map, String fullName) {
		if (map.containsKey(fullName)) {
			return map.get(fullName);
		} else {
			return ScholarshipSheet.NULL_SCHOLARSHIP_SHEET;
		}
	}

	/**
	 * Removes an item from the set by the value of the study group.
	 * 
	 * @param set        - input set.
	 * @param studyGroup - deleting key.
	 */
	public static void deleteFromSetByStudyGroup(Set<ScholarshipSheet> set, StudyGroup studyGroup) {
		Iterator<ScholarshipSheet> iterator = set.iterator();
		while (iterator.hasNext()) {
			StudyGroup current = iterator.next().getStudyGroup();
			if (current.equals(studyGroup)) {
				iterator.remove();
			}
		}
	}

	/**
	 * Sorts list by scholarship size.
	 * 
	 * @param list - sorting list.
	 */
	public static void sortByDefault(List<ScholarshipSheet> list) {
		Collections.sort(list);
	}

	/**
	 * Sorts list by scholarship size and study group number.
	 * 
	 * @param list - sorting list.
	 */
	public static void sortBySizeAndStudyGroupParametres(List<ScholarshipSheet> list) {
		Collections.sort(list, new ScholarshipSheetCompare());
	}

	/**
	 * Returns a variety of scholarship sizes from a list.
	 * 
	 * @param list - input list.
	 * @return SortedSet of sizes.
	 */
	public static SortedSet<Integer> getUniqueScholarShipSizeFromList(List<ScholarshipSheet> list) {
		return new TreeSet<Integer>(ScholarshipSheetUtilite.extractScholarShipSizesFromList(list));
	}

	/**
	 * Extracts list of scholarships sizes from List<ScholarshipSheet>.
	 * 
	 * @param list - input list.
	 * @return List<Integer> of scholarships sizes.
	 */
	private static List<Integer> extractScholarShipSizesFromList(List<ScholarshipSheet> list) {
		Iterator<ScholarshipSheet> iterator = list.iterator();
		List<Integer> result = new ArrayList<Integer>();
		while (iterator.hasNext()) {
			result.add(iterator.next().getScholarshipSize());
		}
		return result;
	}
}
