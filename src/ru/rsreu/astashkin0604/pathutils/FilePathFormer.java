package ru.rsreu.astashkin0604.pathutils;

public class FilePathFormer {

	private FilePathFormer() {
	}

	public static String formPath(String separator, String... pathElements) {
		String path = StringJoiner.join(separator, pathElements);
		return path;
	}
}
