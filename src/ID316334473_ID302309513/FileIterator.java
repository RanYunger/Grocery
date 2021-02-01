package ID316334473_ID302309513;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Iterator;

import ID316334473_ID302309513.Models.ProductModel;

public class FileIterator implements Iterable<ProductModel> {
	// Constants
	public final static String PATH = System.getProperty("user.dir") + "\\bin\\products.txt";

	// Fields
	private RandomAccessFile raf;

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

	public long getOffsetByProductID(String productID) {
		byte[] buffer = new byte[productID.length()];
		long offset = -1;

		try {
			raf = new RandomAccessFile(PATH, "r");

			do {
				raf.seek(++offset);
				raf.read(buffer);
			} while (((offset + buffer.length) < raf.length()) && (new String(buffer) != productID));

			raf.close();
			
			return offset;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return -1;
	}

	public ProductModel readProductFromFile(long offset) {
		byte[] productBytes = null;

		try {
			raf = new RandomAccessFile(PATH, "r");

			raf.seek(offset);
			productBytes = new byte[raf.readInt()];
			raf.read(productBytes);

			raf.close();
			
			return ByteConverter.toProduct(productBytes);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return null;
	}

	public void writeProductToFile(ProductModel product) {
		try {
			raf = new RandomAccessFile(PATH, "w");

			raf.seek(raf.length());
			raf.writeInt(product.getLengthInBytes());
			raf.write(product.toByteArray());

			raf.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	// Iterator Implementation
	@Override
	public Iterator<ProductModel> iterator() {
		return new ProductIterator();
	}

	private class ProductIterator implements Iterator<ProductModel> {
		@Override
		public boolean hasNext() {
			try {
				return raf.getFilePointer() < raf.length();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

			return false;
		}

		@Override
		public ProductModel next() {
			try {
				return readProductFromFile(raf.getFilePointer());
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

			return null;
		}

		@Override
		public void remove() {
			// TODO: COMPLETE (overruns the product raf looks on)
		}
	}
}