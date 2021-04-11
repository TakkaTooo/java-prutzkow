package ru.rsreu.astashkin0604.scholarship.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.astashkin0604.scholarship.ScholarshipSheet;

/**
 * Enum describes the operation with the file (copy, move)
 * 
 * @author Maxim Astashkin
 *
 */
public enum FileOperation {

	COPY {
		@Override
		void performOpertaion(String sourcePath, String destinationPath) throws IOException, ClassNotFoundException {
			//Files.copy(new File(sourcePath).toPath(), new File(destinationPath).toPath());
			ObjectInputStream inputStream = null; 
			ObjectOutputStream outputStream = null; 
			 try {	
				 inputStream = new ObjectInputStream(new FileInputStream(new File(sourcePath)));
				 outputStream = new ObjectOutputStream(new FileOutputStream(new File(destinationPath)));
				 outputStream.writeObject(((ScholarshipSheet[]) inputStream.readObject()));
				 
			 } finally {
				 try {
					 inputStream.close();
					 outputStream.close(); 
				 } catch (NullPointerException e) {
					 throwIOExceptionByFilesAvailability(sourcePath, destinationPath);
				 }	 
			 }
		}
	},
	MOVE {
		@Override
		void performOpertaion(String sourcePath, String destinationPath) throws IOException, ClassNotFoundException {
			//Files.move(new File(sourcePath).toPath(), new File(destinationPath).toPath());
			COPY.performOpertaion(sourcePath, destinationPath);
			File moving = new File(sourcePath);
			try {
				moving.delete();
			} catch (SecurityException e) {
				System.out.println(String.format(Resourcer.getString("files.file.securityExceptionFormat"), moving.getAbsoluteFile()));
			}
			
		}
	};

	/**
	 * Performing an operation with a file (copy, move).
	 * 
	 * @param sourcePath      - source file path.
	 * @param destinationPath - the path to the resulting file.
	 * @throws IOException if the source file does not exist or the resulting file
	 *                     has already been created.
	 * @throws ClassNotFoundException 
	 */
	abstract void performOpertaion(String sourcePath, String destinationPath) throws IOException, ClassNotFoundException;

	/**
	 * Generates and throw an exception for 2 situations (if the source file does
	 * not exist or the resulting file has already been created)
	 * 
	 * @param sourcePath      - source file path.
	 * @param destinationPath - the path to the resulting file
	 */
	public static void throwIOExceptionByFilesAvailability(String sourcePath, String destinationPath)
			throws IOException {
		File sourceFile = new File(sourcePath);
		File destinationFile = new File(destinationPath);
		if (!sourceFile.exists()) {
			throw new IOException(
					String.format(Resourcer.getString("files.file.exceptionFormat"), sourceFile.getAbsolutePath()));
		} else {
			throw new IOException(String.format(Resourcer.getString("files.file.exceptionExistsFormat"),
					destinationFile.getAbsolutePath()));
		}
	}
}
