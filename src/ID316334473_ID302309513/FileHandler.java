package ID316334473_ID302309513;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import ID316334473_ID302309513.Models.CustomerModel;
import ID316334473_ID302309513.Models.ProductModel;

public class FileHandler {
	// Constants
	public static final String PATH = System.getProperty("user.dir") + "\\bin\\products.txt";

	// Fields
	private static ObjectInputStream fileReader;
	private static ObjectOutputStream fileWriter;

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

	public static ProductModel[] readAllProducts() {
		ArrayList<ProductModel> allProducts = new ArrayList<ProductModel>();
		ProductModel currentProduct = null;
		
		try {
			fileReader = new ObjectInputStream(new FileInputStream(PATH));
			currentProduct = readProductFromFile();
			if(currentProduct != null)
				allProducts.add(currentProduct);
			
			fileReader.close();
		} catch (EOFException e) {
			System.out.println("Reached the end of file!");
		} catch (Exception ex) {
			return null;
		}
		
		return (ProductModel[]) allProducts.toArray();
	}

	public static ProductModel readProductFromFile() {
		ProductModel product = null;
		CustomerModel customer = null;
		String id, name;
		int queueNumber, costPrice, sellingPrice;

		try {
			id = fileReader.readUTF();
			name = fileReader.readUTF();
			queueNumber = fileReader.readInt();
			costPrice = fileReader.readInt();
			sellingPrice = fileReader.readInt();
			customer = readCustomerFromFile();

			product = new ProductModel(id, name, costPrice, sellingPrice, customer);

		} catch (EOFException e) {
			System.out.println("Reached the end of file!");
		} catch (Exception ex) {
			return null;
		}

		return product;
	}

	public static boolean writeProductToFile(ProductModel product) {
		try {
			fileWriter = new ObjectOutputStream(new FileOutputStream(PATH, true));

			fileWriter.writeUTF(product.getTextualID());
			fileWriter.writeUTF(product.getTextualName());
			fileWriter.writeInt(product.getNumericQueueNumber());
			fileWriter.writeInt(product.getNumericCostPrice());
			fileWriter.writeInt(product.getNumericSellingPrice());
			writeCustomerToFile(product.getCustomer());

			fileWriter.close();
		} catch (Exception ex) {
			return false;
		}

		return true;
	}

	private static CustomerModel readCustomerFromFile() {
		CustomerModel customer = null;
		String name, phoneNumber;
		Boolean interested;

		try {
			name = fileReader.readUTF();
			phoneNumber = fileReader.readUTF();
			interested = fileReader.readBoolean();

			customer = new CustomerModel(name, phoneNumber, interested);
		} catch (EOFException e) {
			System.out.println("Reached the end of file!");
		} catch (Exception ex) {
			return null;
		}

		return customer;
	}

	private static boolean writeCustomerToFile(CustomerModel customer) {
		try {
			fileWriter.writeUTF(customer.getTextualName());
			fileWriter.writeUTF(customer.getTextualPhoneNumber());
			fileWriter.writeBoolean(customer.isInterestedInUpdates());
		} catch (Exception ex) {
			return false;
		}

		return true;
	}
}