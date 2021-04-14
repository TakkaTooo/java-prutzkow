package ru.rsreu.astashkin0604.scholarship.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.prutzkow.resourcer.Resourcer;

/**
 * Enum describes the operation with the file (copy, move)
 * 
 * @author Maxim Astashkin
 *
 */
public enum FileOperation {
	/**
	 * Copy operation
	 */
	COPYING {
		@Override
		public void performOpertaion(String sourcePath, String destinationPath) throws IOException {
			OutputStream outputStream = null;
			File file = new File(sourcePath);
			FileInputStream fileInputStream = null;
			byte[] bFile = new byte[(int) file.length()];
			try {
				fileInputStream = new FileInputStream(file);
				fileInputStream.read(bFile);
				fileInputStream.close();
				outputStream = new FileOutputStream(new File(destinationPath));
				outputStream.write(bFile);
			} finally {
				try {
					fileInputStream.close();
					outputStream.close();
				} catch (NullPointerException e) {
					throw new IOException(
							String.format(Resourcer.getString("files.file.copy.exception"), file.getAbsoluteFile()));
				}

			}
		}
	},
	/**
	 * Move operation
	 */
	MOVING {
		@Override
		public void performOpertaion(String sourcePath, String destinationPath) throws IOException {
			COPYING.performOpertaion(sourcePath, destinationPath);
			File moving = new File(sourcePath);
			try {
				moving.delete();
			} catch (SecurityException e) {
				throw new IOException(String.format(Resourcer.getString("files.file.securityExceptionFormat"),
						moving.getAbsolutePath()));
			}
		}
	};

	/**
	 * Performing an operation with a file (copy, move).
	 * 
	 * @param sourcePath      - source file path.
	 * @param destinationPath - the path to the resulting file.
	 * @throws IOException            if the source file does not exist or the
	 *                                resulting file has already been created.
	 * @throws ClassNotFoundException
	 */
	public abstract void performOpertaion(String sourcePath, String destinationPath) throws IOException;

}
