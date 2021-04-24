package ru.rsreu.astashkin0604;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.astashkin0604.pathutils.FilePathFormer;
import ru.rsreu.astashkin0604.pathutils.FilesPathGetter;
import ru.rsreu.astashkin0604.scholarship.ScholarshipSheet;
import ru.rsreu.astashkin0604.scholarship.utils.FileOperation;
import ru.rsreu.astashkin0604.scholarship.utils.ScholarshipSheetArrayInitializer;
import ru.rsreu.astashkin0604.scholarship.utils.ScholarshipSheetArrayTextTableFormer;
import ru.rsreu.astashkin0604.scholarship.utils.ScholarshipSheetFileService;

public class ApplicationRunner {

	private static final String BASIC_FILE_PATH = FilesPathGetter.getBasicFilePath();

	private static final String BACKUP_FILE_PATH = FilesPathGetter.getBackupFilePath();

	private static final String MOVING_FILE_PATH = FilesPathGetter.getPathForMoveingFile();

	private static final ScholarshipSheet[] BASIC_ARRAY = ScholarshipSheetArrayInitializer.getInitializedArray();

	private ApplicationRunner() {

	}

	public static void main(String[] args) {
		// Creating folders
		String folderName;
		folderName = Resourcer.getString("files.folder.source.name");
		if (FolderCreator.createFolderByPath(folderName)) {
			System.out.println(FolderCreator.generateSuccessMessage(new File(folderName).getAbsolutePath()));
		}

		folderName = Resourcer.getString("files.folder.move.name");
		if (FolderCreator.createFolderByPath(folderName)) {
			System.out.println(FolderCreator.generateSuccessMessage(new File(folderName).getAbsolutePath()));
		}

		folderName = FilePathFormer.formPath(File.separator, Resourcer.getString("files.folder.move.name"),
				Resourcer.getString("files.folder.copy.name"));
		if (FolderCreator.createFolderByPath(folderName)) {
			System.out.println(FolderCreator.generateSuccessMessage(new File(folderName).getAbsolutePath()));
		}

		StringBuilder operationMessage = new StringBuilder();
		// Create file from array
		try {
			ScholarshipSheetFileService.createFileFromScholarshipSheetArray(BASIC_ARRAY, BASIC_FILE_PATH);
			operationMessage.append(String.format(Resourcer.getString("files.file.successFormat"), BASIC_FILE_PATH));
		} catch (IOException e) {
			operationMessage.append(e.getMessage());
		}
		System.out.println(operationMessage);
		operationMessage = new StringBuilder();

		// Create bak file
		try {
			FileOperation.COPYING.performOpertaion(BASIC_FILE_PATH, BACKUP_FILE_PATH);
			operationMessage.append(String.format(Resourcer.getString("files.file.successFormat"), BACKUP_FILE_PATH));
		} catch (IOException e) {
			operationMessage.append(e.getMessage());
		}
		System.out.println(operationMessage);

		operationMessage = new StringBuilder();

		// Moving file
		System.out.print(String.format(Resourcer.getString("runner.confirmation.messageFormat"), BASIC_FILE_PATH,
				MOVING_FILE_PATH));
		Scanner in = new Scanner(System.in);
		try {
			if (performMovingByUserDecision(in.nextLine(), BASIC_FILE_PATH, MOVING_FILE_PATH)) {
				operationMessage.append(String.format(Resourcer.getString("files.file.successMovingFormat"),
						BASIC_FILE_PATH, MOVING_FILE_PATH));
			}

		} catch (IOException | ClassNotFoundException | IllegalArgumentException e) {
			operationMessage.append(e.getMessage());
		} finally {
			in.close();
		}
		System.out.println(operationMessage);

		// Comapring arrays
		ScholarshipSheet[] fromBackup = { ScholarshipSheet.NULL_SCHOLARSHIP_SHEET };
		ScholarshipSheet[] fromMoving = { ScholarshipSheet.NULL_SCHOLARSHIP_SHEET };

		try {
			fromBackup = ScholarshipSheetFileService.getScholarshipSheetArrayFromFile(BACKUP_FILE_PATH);
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			fromMoving = ScholarshipSheetFileService.getScholarshipSheetArrayFromFile(MOVING_FILE_PATH);
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		StringBuilder tablesWithComparingResults = new StringBuilder();
		tablesWithComparingResults
				.append(ScholarshipSheetArrayTextTableFormer.formTextTable(BASIC_ARRAY, "runner.message.source"))
				.append(ScholarshipSheetArrayTextTableFormer.formTextTable(fromBackup, "runner.message.bak"))
				.append(ScholarshipSheetArrayTextTableFormer.formTextTable(fromMoving, "runner.message.moving"))
				.append(ArraysComparingService.formCompareResults(BASIC_ARRAY, fromBackup,
						"runner.message.comparing.first"))
				.append(ArraysComparingService.formCompareResults(BASIC_ARRAY, fromMoving,
						"runner.message.comparing.second"))
				.append(ArraysComparingService.formCompareResults(fromBackup, fromMoving,
						"runner.message.comparing.third"));
		System.out.println(tablesWithComparingResults);
	}

	/**
	 * Performs a file move operation depending on the string value entered by the
	 * user
	 * 
	 * @param userDecision    - user entered string ("y" - operation will perform).
	 * @param sourcePath      - path to source file
	 * @param destinationPath - destination file path
	 * @throws IOException              - in case the source file does not exist or
	 *                                  the destination file has already been
	 *                                  created.
	 * @throws IllegalArgumentException - if the user string is not y and n
	 * @throws ClassNotFoundException
	 */
	private static boolean performMovingByUserDecision(String userDecision, String sourcePath, String destinationPath)
			throws IOException, IllegalArgumentException, ClassNotFoundException {
		String userString = userDecision.toLowerCase();
		if (userString.equals(Resourcer.getString("runner.yes.confirmation"))) {
			FileOperation.MOVING.performOpertaion(sourcePath, destinationPath);
			return true;
		} else if (!userString.equals(Resourcer.getString("runner.no.confirmation"))) {
			throw new IllegalArgumentException(Resourcer.getString("runner.exception.message"));
		}
		return false;
	}

}
