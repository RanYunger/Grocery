package ID316334473_ID302309513;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;

import ID316334473_ID302309513.Models.ProductModel;

public class FileHandler {
	// Constants
	public static final String PATH = System.getProperty("user.dir") + "\\bin\\products.txt";

	// Fields
	private static RandomAccessFile binFile;

	// Methods
	public static boolean isFileReady() {
		File file = new File(PATH);

		try {
			if (!file.createNewFile())
				return file.length() > 0;
		} catch (IOException e) {
		}

		return false;
	}

	public static ProductModel readProductFromFile() {
		// TODO: COMPLETE
		return null;
	}

	public static boolean writeProductToFile() {
		// TODO: COMPLETE
		return true;
	}

	public static long findProductByID(String productID) {
		// TODO: COMPLETE
		return -1;
	}
}
