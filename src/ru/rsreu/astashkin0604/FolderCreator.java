package ru.rsreu.astashkin0604;

import java.io.File;
import java.io.IOException;

import com.prutzkow.resourcer.Resourcer;

public class FolderCreator {

	private FolderCreator() {

	}

	/**
	 * Creates a folder at the given path.
	 * 
	 * @param folderPath - folder path.
	 * @return true - if folder has been created, else - false.
	 * @throws IOException in case the folder has already been created.
	 */
	public static boolean createFolderByPath(String folderPath) {
		File folderCreator = new File(folderPath);
		return folderCreator.mkdir();
	}

	/**
	 * Generates a message about successful folder creation.
	 * 
	 * @param folderPath.
	 * @return String a message about successful folder creation.
	 */
	public static String generateSuccessMessage(String folderPath) {
		return String.format(Resourcer.getString("files.folder.successFormat"), folderPath);
	}

}
