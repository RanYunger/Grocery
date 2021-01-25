package ID316334473_ID302309513.Models;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class GroceryModel {
	// Constants
	public static final int NO_PRICE = 0;

	// Fields
	private TreeMap<String, ProductModel> allProducts;
	private List<CustomerModel> allCustomers;
	private RandomAccessFile productsBinaryFile;

	// Properties (Getters and Setters)
	public TreeMap<String, ProductModel> getAllProducts() {
		return allProducts;
	}

	public void setAllProducts(TreeMap<String, ProductModel> allProducts) {
		this.allProducts = allProducts;
	}

	public List<CustomerModel> getAllCustomers() {
		return allCustomers;
	}

	public void setAllCustomers(List<CustomerModel> allCustomers) {
		this.allCustomers = allCustomers;
	}

	// Constructors
	public GroceryModel(Comparator<? super String> comparator) {
		setAllProducts(new TreeMap<String, ProductModel>(comparator));
		setAllCustomers(new ArrayList<CustomerModel>());
	}

	// Methods
	public int comparatorByIDAscending(ProductModel p1, ProductModel p2) {
		return p1.compareTo(p2);
	}
}