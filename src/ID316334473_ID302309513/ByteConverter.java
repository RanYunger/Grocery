package ID316334473_ID302309513;

import java.nio.ByteBuffer;

import ID316334473_ID302309513.Models.CustomerModel;
import ID316334473_ID302309513.Models.ProductModel;

public class ByteConverter {
	// Constants

	// Methods
	public static byte[] fromInteger(int value) {
		return ByteBuffer.allocate(4).putInt(value).array();
	}

	public static byte[] fromString(String str) {
		return str.getBytes();
	}

	public static byte fromBoolean(boolean value) {
		return (byte) (value ? 1 : 0);
	}

	@SuppressWarnings("static-access")
	public static int toInteger(byte[] bytes, int offset) {
		return ByteBuffer.allocate(4).wrap(bytes, offset, 4).getInt();
	}

	public static String toString(byte[] bytes, int offset, int length) {
		return new String(bytes, offset, length);
	}

	public static ProductModel toProduct(byte[] bytes) {
		CustomerModel customer = null;
		String id, name;
		int offset = 0, currentLength, costPrice, sellingPrice, profit;

		currentLength = toInteger(bytes, offset);
		id = toString(bytes, 4, currentLength);
		offset += 4 + currentLength;

		currentLength = toInteger(bytes, offset);
		name = toString(bytes, offset + 4, currentLength);
		offset += 4 + currentLength;

		costPrice = toInteger(bytes, offset);
		offset += 4;

		sellingPrice = toInteger(bytes, offset);
		offset += 4;

		profit = toInteger(bytes, offset);
		offset += 4;

		customer = bytes[offset] == 1 ? toCustomer(bytes, ++offset) : null;

		return new ProductModel(id, name, costPrice, sellingPrice, customer);
	}

	public static CustomerModel toCustomer(byte[] bytes, int offset) {
		String name, phoneNumber;
		int currentLength;

		currentLength = toInteger(bytes, offset);
		name = toString(bytes, offset + 4, currentLength);
		offset += 4 + currentLength;

		currentLength = toInteger(bytes, offset);
		phoneNumber = toString(bytes, offset + 4, currentLength);
		offset += 4 + currentLength;

		return new CustomerModel(name, phoneNumber, bytes[offset] == 1);
	}
}
