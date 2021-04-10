package ru.rsreu.astashkin0604;

import java.io.IOException;
import java.util.Scanner;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.astashkin0604.scholarship.ScholarshipSheet;
import ru.rsreu.astashkin0604.scholarship.utils.FileOperation;
import ru.rsreu.astashkin0604.scholarship.utils.ScholarshipSheetFileService;

/**
 * A class that implements methods for performing a task in accordance with a
 * task option.
 * 
 * @author Maxim Astashkin.
 *
 */
public class TaskService {

	private TaskService() {

	}

	/**
	 * Moves the file and returns a message about the result of the operation
	 * 
	 * @param sourcePath      - path to source file
	 * @param destinationPath - destination file path
	 * @return a message about the result of the operation
	 */
	public static String moveFileAndGetMessage(String sourcePath, String destinationPath) {
		Scanner in = new Scanner(System.in);
		try {
			if (performMovingByUserDecision(in.nextLine(), sourcePath, destinationPath)) {
				return String.format(Resourcer.getString("files.file.successMovingFormat"), sourcePath,
						destinationPath);
			}

		} catch (IOException | IllegalArgumentException e) {
			return e.getMessage();
		} finally {
			in.close();
		}
		return "";
	}

	/**
	 * Creates a backup of the file and returns a message about the operation
	 * 
	 * @param sourcePath - path to source file
	 * @param backupPath - backup file path
	 * @return a message about the operation
	 */
	public static String createBackupAndGetMessage(String sourcePath, String backupPath) {
		try {
			ScholarshipSheetFileService.performOperationWithFile(sourcePath, backupPath, FileOperation.COPY);
			return String.format(Resourcer.getString("files.file.successFormat"), backupPath);
		} catch (IOException e) {
			return e.getMessage();
		}
	}

	/**
	 * Creates a file from the given array and returns a message about the operation
	 * result.
	 * 
	 * @param filePath - creating file path.
	 * @param array    - the array by which the file is built.
	 * @return a message about the operation result.
	 */
	public static String createFileFromArrayAndGetMessage(String filePath, ScholarshipSheet[] array) {
		try {
			ScholarshipSheetFileService.createFileFromScholarshipSheetArray(array, filePath);
			return String.format(Resourcer.getString("files.file.successFormat"), filePath);
		} catch (IOException e) {
			return e.getMessage();
		}
	}

	/**
	 * Converts a comparison array (consisting of -1 0 and 1) to a textual
	 * representation of the comparison of two arrays 1. First equal second (for
	 * example).
	 * 
	 * @param array - comparison array.
	 * @return text representation of arrays comparing.
	 */
	public static String parseComparativeArrayToString(int[] array) {
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			String comparative = null;
			if (array[i] == 0) {
				comparative = Resourcer.getString("runner.message.equal");
			} else if (array[i] > 0) {
				comparative = Resourcer.getString("runner.message.more");
			} else {
				comparative = Resourcer.getString("runner.message.less");
			}

			output.append(String.format(Resourcer.getString("runner.message.compareFormat"), i + 1, comparative));
		}

		return output.toString();
	}

	/**
	 * Compares element-wise two arrays and stores the comparison results in the
	 * array.
	 * 
	 * @param first  - first array.
	 * @param second - second array.
	 * @return comparison results by array.
	 */
	public static int[] compareArraysElements(ScholarshipSheet[] first, ScholarshipSheet[] second) {

		int[] output = new int[Math.min(first.length, second.length)];
		for (int i = 0; i < first.length && i < second.length; i++) {
			output[i] = first[i].compareTo(second[i]);
		}
		return output;
	}

	/**
	 * Performs a file move operation depending on the string value entered by the
	 * user
	 * 
	 * @param userDecision    - user entered string ("y" - operation will perform).
	 * @param sourcePath      - path to source file
	 * @param destinationPath - destination file path
	 * @return true - if operation completed, else - false;
	 * @throws IOException              - in case the source file does not exist or
	 *                                  the destination file has already been
	 *                                  created.
	 * @throws IllegalArgumentException - if the user string is not y and n
	 */
	private static boolean performMovingByUserDecision(String userDecision, String sourcePath, String destinationPath)
			throws IOException, IllegalArgumentException {
		String userString = userDecision.toLowerCase();
		if (userString.equals(Resourcer.getString("runner.yes.confirmation"))) {
			ScholarshipSheetFileService.performOperationWithFile(sourcePath, destinationPath, FileOperation.MOVE);
			return true;
		} else if (!userString.equals(Resourcer.getString("runner.no.confirmation"))) {
			throw new IllegalArgumentException(Resourcer.getString("runner.exception.message"));
		}
		return false;
	}
}
