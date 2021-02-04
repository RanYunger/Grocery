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
		fileOffset = 00;
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
		byte[] buffer = new byte[2 + productID.length()];
		long prevOffset = fileOffset;
		String readProductID = null;

		try {
			productID = String.format("#%s#", productID);

			fileOffset = 0x08;
			while ((fileOffset + buffer.length) < raf.length()) {
				raf.seek(fileOffset);
				raf.read(buffer);
				readProductID = ByteConverter.toString(buffer, 0, buffer.length);
				if (readProductID.compareTo(productID) == 0) {
					fileOffset -= 0x08; // 4 product length + 4 id length

					return fileOffset;
				}

				fileOffset++;
			}
			fileOffset = prevOffset;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return -1;
	}

	public ProductModel readProductFromFile() {
		byte[] productBytes = null;
		int productLength;

		try {
			productLength = raf.readInt();
			productBytes = new byte[productLength];
			raf.read(productBytes);
			fileOffset = raf.getFilePointer();

			return ByteConverter.toProduct(productBytes);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return null;
	}

	public void writeProductToFile(ProductModel product) {
		byte[] productBytes = product.toByteArray(), restOfBytes = new byte[0];
		long productOffset = -1, prevLength = 0, diff = 0, bytesToSkip;

		try {
			raf = new RandomAccessFile(PATH, "rw");
			productOffset = seekProduct(product.getTextualID());
			prevLength = raf.length();

			// Overwriting the product
			if (productOffset != -1) {
				raf.seek(productOffset);
				bytesToSkip = raf.readInt();
				diff = productBytes.length - bytesToSkip;
				raf.skipBytes((int) bytesToSkip);
				restOfBytes = new byte[(int) (raf.length() - raf.getFilePointer())];
				raf.read(restOfBytes);
			}

			raf.seek(productOffset != -1 ? productOffset : prevLength);
			raf.writeInt(productBytes.length);
			raf.write(productBytes);
			raf.write(restOfBytes);
			fileOffset = raf.getFilePointer();
			raf.setLength(restOfBytes.length > 0 ? prevLength + diff : raf.length());

			raf.close();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	// Iterator Implementation
	@Override
	public Iterator<ProductModel> iterator() {
		fileOffset = 0x00;

		return new ProductIterator();
	}

	private class ProductIterator implements Iterator<ProductModel> {
		@Override
		public boolean hasNext() {
			boolean hasNext = false;

			try {
				raf = new RandomAccessFile(PATH, "r");

				hasNext = (raf.length() - fileOffset) > 8;

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

				raf.seek(fileOffset);
				bytesToSkip = raf.readInt();
				raf.skipBytes(bytesToSkip);
				fileOffset = raf.getFilePointer();

				restOfBytes = new byte[(int) (raf.length() - bytesToSkip)];
				raf.read(restOfBytes);

				raf.seek(removeOffset);
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