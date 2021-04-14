package ru.rsreu.astashkin0604.pathutils;

public class FilePathFormer {

	private FilePathFormer() {
	}

	public static String formPath(String separator, String... pathElements) {
		String path = StringJoiner.join(separator, pathElements);
		return path;
	}

	/**
	 * Forms the file name in the form "fileName.extension".
	 * @return forming name of the file.
	 */
	public static String formFileNameWithExtension(String fileName, String extension) {
		return fileName + "." + extension;
	}
}
