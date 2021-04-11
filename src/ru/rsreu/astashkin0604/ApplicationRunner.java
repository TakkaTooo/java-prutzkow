package ru.rsreu.astashkin0604;

import java.io.IOException;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.astashkin0604.pathutils.FilesPathGetter;
import ru.rsreu.astashkin0604.scholarship.ScholarshipSheet;
import ru.rsreu.astashkin0604.scholarship.utils.ScholarshipSheetArrayInitializer;
import ru.rsreu.astashkin0604.scholarship.utils.ScholarshipSheetArrayTextTableGenerator;
import ru.rsreu.astashkin0604.scholarship.utils.ScholarshipSheetFileService;

public class ApplicationRunner {

	private static final String BASIC_FILE_PATH = FilesPathGetter.getBasicFilePath();

	private static final String BACKUP_FILE_PATH = FilesPathGetter.getBackupFilePath();

	private static final String MOVING_FILE_PATH = FilesPathGetter.getPathForMoveingFile();

	private static final ScholarshipSheet[] BASIC_ARRAY = ScholarshipSheetArrayInitializer.getInitializedArray();

	private ApplicationRunner() {

	}

	public static void main(String[] args) {
		System.out.println(FoldersStructureCreator.createFoldersStructureWithGettingMessage());
		System.out.println(TaskService.createFileFromArrayAndGetMessage(BASIC_FILE_PATH,
				ScholarshipSheetArrayInitializer.getInitializedArray()));
		System.out.println(TaskService.createBackupAndGetMessage(BASIC_FILE_PATH, BACKUP_FILE_PATH));

		System.out.print(String.format(Resourcer.getString("runner.confirmation.messageFormat"), BASIC_FILE_PATH, MOVING_FILE_PATH));
		System.out.println(TaskService.moveFileAndGetMessage(BASIC_FILE_PATH, MOVING_FILE_PATH));

		ScholarshipSheet[] fromBackup = { ScholarshipSheet.NULL_SCHOLARSHIP_SHEET };
		ScholarshipSheet[] fromMoving = { ScholarshipSheet.NULL_SCHOLARSHIP_SHEET };

		try {
			fromBackup = ScholarshipSheetFileService.getScholarshipSheetArrayFromFile(BACKUP_FILE_PATH);
			fromMoving = ScholarshipSheetFileService.getScholarshipSheetArrayFromFile(MOVING_FILE_PATH);
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		StringBuilder output = new StringBuilder();
		output.append(Resourcer.getString("runner.message.source"))
				.append(ScholarshipSheetArrayTextTableGenerator.tryGetTextTable(BASIC_ARRAY));

		output.append(Resourcer.getString("runner.message.bak"))
				.append(ScholarshipSheetArrayTextTableGenerator.tryGetTextTable(fromBackup))
				.append(Resourcer.getString("runner.message.moving"))
				.append(ScholarshipSheetArrayTextTableGenerator.tryGetTextTable(fromMoving));

		System.out.println(compareResults(fromBackup, fromMoving));
	}

	private static String compareResults(ScholarshipSheet[] fromBackup, ScholarshipSheet[] fromMoving) {
		StringBuilder output = new StringBuilder();
		int[] comparative = TaskService.compareArraysElements(BASIC_ARRAY, fromBackup);
		output.append(Resourcer.getString("runner.message.compareFirst"))
				.append(TaskService.parseComparativeArrayToString(comparative));
		comparative = TaskService.compareArraysElements(BASIC_ARRAY, fromMoving);
		output.append(Resourcer.getString("runner.message.compareSecond"))
				.append(TaskService.parseComparativeArrayToString(comparative));
		comparative = TaskService.compareArraysElements(fromBackup, fromMoving);
		output.append(Resourcer.getString("runner.message.compareThird"))
				.append(TaskService.parseComparativeArrayToString(comparative));
		return output.toString();
	}
}
