package ru.rsreu.astashkin0604.pathutils;

import java.io.File;

import com.prutzkow.resourcer.Resourcer;

public class FilesPathGetter {

	private FilesPathGetter() {

	}

	/**
	 * Generates the path to the source file in the source folder.
	 * 
	 * @return String path to the source file in the Source folder.
	 */
	public static String getBasicFilePath() {
		return new File(FilePathFormer.formPath(File.separator, Resourcer.getString("files.folder.source.name"),
				Resourcer.getString("files.file.data.name"))).getAbsolutePath();
	}

	/**
	 * Generates the path to the backup source file.
	 * 
	 * @return String path to the backup source file.
	 */
	public static String getBackupFilePath() {
		String fileName = FilePathFormer.formFileNameWithExtension(Resourcer.getString("files.file.data.name"),
				Resourcer.getString("files.file.backup.extension"));
		String filePath = FilePathFormer.formPath(File.separator, Resourcer.getString("files.folder.move.name"),
				Resourcer.getString("files.folder.copy.name"), fileName);
		return new File(filePath).getAbsolutePath();
	}

	/**
	 * Generates a path to move the source file.
	 * 
	 * @return string path to move the source file.
	 */
	public static String getPathForMoveingFile() {
		return new File(FilePathFormer.formPath(File.separator, Resourcer.getString("files.folder.move.name"),
				Resourcer.getString("files.file.data.name"))).getAbsolutePath();
	}
}