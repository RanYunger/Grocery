package ID316334473_ID302309513;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Iterator;

import ID316334473_ID302309513.Models.ProductModel;

public class FileHandler implements Iterable<ProductModel> {
	// Constants
	public final static String PATH = System.getProperty("user.dir") + "\\bin\\products.txt";

	// Fields
	private RandomAccessFile raf;
	private long fileOffset;

	// Constructors
	public FileHandler() {
		fileOffset = 0;
	}

	// Methods
	public static boolean isFileReady() {
		File file = new File(PATH);

		try {
			if (!file.createNewFile())
				return file.length() > 0;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return false;
	}

	public long seekProduct(String productID) {
		byte[] buffer = new byte[productID.length()];
		String readProductID = null;

		try {
			raf = new RandomAccessFile(PATH, "r");

			fileOffset = 8;
			while ((fileOffset + buffer.length) < raf.length()) {
				raf.seek(fileOffset++);
				raf.read(buffer);
				readProductID = ByteConverter.toString(buffer, 0, buffer.length);
				if (readProductID.compareTo(productID) == 0) {
					fileOffset -= 9; // 8 to first byte of product, 1 for the increment in loop

					return fileOffset;
				}
			}

			raf.close();

			return -1;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return -1;
	}

	public ProductModel readProductFromFile() {
		byte[] productBytes = null;

		try {
			productBytes = new byte[raf.readInt()];
			raf.read(productBytes);
			fileOffset = raf.getFilePointer();

			return ByteConverter.toProduct(productBytes);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return null;
	}

	public void writeProductToFile(ProductModel product) {
		byte[] productBytes = product.toByteArray();

		try {
			raf = new RandomAccessFile(PATH, "rw");

			raf.seek(raf.length());
			raf.writeInt(productBytes.length);
			raf.write(productBytes);

			raf.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	// Iterator Implementation
	@Override
	public Iterator<ProductModel> iterator() {
		fileOffset = 0;

		return new ProductIterator();
	}

	private class ProductIterator implements Iterator<ProductModel> {
		@Override
		public boolean hasNext() {
			boolean hasNext = false;

			try {
				raf = new RandomAccessFile(PATH, "r");

				hasNext = fileOffset < raf.length();

				raf.close();

				return hasNext;
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

			return false;
		}

		@Override
		public ProductModel next() {
			ProductModel readProduct = null;

			try {
				raf = new RandomAccessFile(PATH, "r");

				raf.seek(fileOffset);
				readProduct = readProductFromFile();

				raf.close();

				return readProduct;

			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

			return null;
		}

		@Override
		public void remove() {
			byte[] restOfBytes = null;
			long prevLength, removeOffset = fileOffset;
			int bytesToSkip = 0;

			try {
				raf = new RandomAccessFile(PATH, "rw");

				prevLength = raf.length();
				raf.seek(Long.parseLong("" + fileOffset, 16));

				bytesToSkip = raf.readInt();
				raf.skipBytes(bytesToSkip);
				fileOffset = raf.getFilePointer();

				restOfBytes = new byte[(int) (raf.length() - bytesToSkip)];
				raf.read(restOfBytes);

				raf.seek(Long.parseLong("" + removeOffset, 16));
				raf.write(restOfBytes);
				fileOffset = removeOffset; // return to where the removed product was
				raf.setLength(prevLength - bytesToSkip - 4);

				raf.close();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
}