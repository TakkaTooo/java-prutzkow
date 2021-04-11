package ru.rsreu.astashkin0604.pathutils;

class StringJoiner {

	private StringJoiner() {
	}

	public static String join(String separator, String... args) {
		if (args.length == 0) {
			return "";
		}

		if (args.length == 1) {
			return args[0];
		}

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < args.length - 1; i++) {
			result.append(args[i]);
			if (!args[i].endsWith(separator)) {
				result.append(separator);
			}
		}
		result.append(args[args.length - 1]);

		return result.toString();
	}

}
