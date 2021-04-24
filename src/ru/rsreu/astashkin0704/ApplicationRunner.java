package ru.rsreu.astashkin0704;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.astashkin0704.scholarship.ScholarshipSheet;
import ru.rsreu.astashkin0704.scholarship.StudyGroup;
import ru.rsreu.astashkin0704.scholarship.utils.ScholarshipSheetArrayInitializer;
import ru.rsreu.astashkin0704.scholarship.utils.ScholarshipSheetArrayTextTableFormer;
import ru.rsreu.astashkin0704.scholarship.utils.ScholarshipSheetListToMapConverter;
import ru.rsreu.astashkin0704.scholarship.utils.ScholarshipSheetUtilite;

public class ApplicationRunner {

	private ApplicationRunner() {

	}

	public static void main(String[] args) {
		StringBuffer output = new StringBuffer();
		List<ScholarshipSheet> list = Arrays.asList(ScholarshipSheetArrayInitializer.getInitializedArray());
		ScholarshipSheet[] tableContainer = new ScholarshipSheet[list.size()];
		output.append(
				ScholarshipSheetArrayTextTableFormer.formTextTable(list.toArray(tableContainer), "runner.sourceList"));
		
		ScholarshipSheetUtilite.sortByDefault(list);
		output.append(ScholarshipSheetArrayTextTableFormer.formTextTable(list.toArray(tableContainer),
				"runner.defaultSortedList"));
		
		ScholarshipSheetUtilite.sortBySizeAndStudyGroupParametres(list);
		output.append(
				ScholarshipSheetArrayTextTableFormer.formTextTable(list.toArray(tableContainer), "runner.sortedList"));
		
		output.append(Resourcer.getString("runner.uniqueSizes"))
				.append(ScholarshipSheetUtilite.getUniqueScholarShipSizeFromList(list).toString());
		
		Set<ScholarshipSheet> set = new HashSet<ScholarshipSheet>(list);
		output.append(
				ScholarshipSheetArrayTextTableFormer.formTextTable(set.toArray(tableContainer), "runner.sourceSet"));
		
		ScholarshipSheetUtilite.deleteFromSetByStudyGroup(set, StudyGroup.GROUP_9413);
		tableContainer = new ScholarshipSheet[set.size()];
		output.append(ScholarshipSheetArrayTextTableFormer.formTextTable(set.toArray(tableContainer),
				"runner.setAfterDelete"));

		Map<String, ScholarshipSheet> map = ScholarshipSheetListToMapConverter.convertScholarshipSheetListToMap(list);
		ScholarshipSheet searchResult = ScholarshipSheetUtilite.searchSheetInMapByFullName(map,
				ScholarshipSheetArrayInitializer.getInstanceForSearchInArray().getFullName());
		output.append(Resourcer.getString("runner.searchByFullName")).append(getMessageFromSearchResult(searchResult));
		
		searchResult = ScholarshipSheetUtilite.searchSheetInMapByFullName(map,
				ScholarshipSheetArrayInitializer.getInstanceForSearchOutArray().getFullName());
		output.append(getMessageFromSearchResult(searchResult));
		
		searchResult = ScholarshipSheetUtilite.searchSheetInMapByValue(map,
				ScholarshipSheetArrayInitializer.getInstanceForSearchInArray());
		output.append(Resourcer.getString("runner.searchByValue")).append(getMessageFromSearchResult(searchResult));
		
		searchResult = ScholarshipSheetUtilite.searchSheetInMapByValue(map,
				ScholarshipSheetArrayInitializer.getInstanceForSearchOutArray());
		output.append(getMessageFromSearchResult(searchResult));
		
		System.out.println(output);
	}

	/**
	 * @param searchResult - element from collection.
	 * @returna message about finding or not finding an element
	 */
	private static String getMessageFromSearchResult(ScholarshipSheet searchResult) {
		if (!searchResult.equals(ScholarshipSheet.NULL_SCHOLARSHIP_SHEET)) {
			return String.format(Resourcer.getString("runner.succesFind"), searchResult.toString());
		} else {
			return Resourcer.getString("runner.findFault");
		}
	}
}
