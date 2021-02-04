package ID316334473_ID302309513.Models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import ID316334473_ID302309513.FileHandler;

public class GroceryModel {
	// Constants

	// Fields
	private TreeMap<String, ProductModel> allProducts;
	private List<CustomerModel> allCustomers;
	private FileHandler fileHandler;

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
		fileHandler = new FileHandler();
		
		readAllProducts();
	}

	// Methods
	private void readAllProducts() {
		Iterator<ProductModel> it = fileHandler.iterator();
		ProductModel currentProduct = null;
		CustomerModel currentCustomer = null;
		
		while (it.hasNext()) {
			currentProduct = it.next();
			allProducts.put(currentProduct.getTextualID(), currentProduct);
			currentCustomer = currentProduct.getCustomer();
			if(currentCustomer != null)
				allCustomers.add(currentCustomer);
		}
	}
}