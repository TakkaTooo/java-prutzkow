package ru.rsreu.astashkin0604;

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
		return new File(String.format("%s%s%s", Resourcer.getString("files.folder.source.name"), File.separator,
				Resourcer.getString("files.file.data.name"))).getAbsolutePath();
	}

	/**
	 * Generates the path to the backup source file.
	 * 
	 * @return String path to the backup source file.
	 */
	public static String getBackupFilePath() {
		return new File(String.format(Resourcer.getString("files.file.backup.pathFormat"),
				Resourcer.getString("files.folder.move.name"), File.separator,
				Resourcer.getString("files.folder.copy.name"), File.separator,
				Resourcer.getString("files.file.data.name"), Resourcer.getString("files.file.backup.extension")))
						.getAbsolutePath();
	}

	/**
	 * Generates a path to move the source file.
	 * 
	 * @return string path to move the source file.
	 */
	public static String getPathForMoveingFile() {
		return new File(String.format(Resourcer.getString("files.file.moving.pathFormat"),
				Resourcer.getString("files.folder.move.name"), File.separator,
				Resourcer.getString("files.file.data.name"))).getAbsolutePath();
	}
}
