package ru.rsreu.astashkin0704.scholarship.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ru.rsreu.astashkin0704.scholarship.ScholarshipSheet;

/**
 * ScholarshipSheet to Map converter class, where the key is fullName.
 */
public class ScholarshipSheetListToMapConverter {

	private ScholarshipSheetListToMapConverter() {
	}

	/**
	 * Converts List of ScholarshipSheet to map with key - fullName.
	 * 
	 * @param list - input list.
	 * @return generated Map.
	 */
	public static Map<String, ScholarshipSheet> convertScholarshipSheetListToMap(List<ScholarshipSheet> list) {
		Map<String, ScholarshipSheet> result = new HashMap<String, ScholarshipSheet>();
		Iterator<ScholarshipSheet> iterator = list.iterator();
		while (iterator.hasNext()) {
			ScholarshipSheet current = iterator.next();
			result.put(current.getFullName(), current);
		}
		return result;
	}
}
