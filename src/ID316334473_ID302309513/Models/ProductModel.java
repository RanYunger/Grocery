package ID316334473_ID302309513.Models;

import ID316334473_ID302309513.UIHandler;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public abstract class ProductModel implements Comparable<ProductModel> {
	// Constants
	public static final int NO_PRICE = 0;

	// Fields
	private SimpleStringProperty name;
	private SimpleIntegerProperty costPrice, sellingPrice;
	//private ClientModel client;

	// Properties (Getters and Setters)
	public SimpleStringProperty getObservableName() {
		return name;
	}

	public String getTextualName() {
		return name.get();
	}

	private void setName(String name) {
		if (name.isBlank())
			UIHandler.showError("CitizenModel's name must contain at least 1 letter.");
		this.name = new SimpleStringProperty(name);
	}

	public SimpleIntegerProperty getObservableCostPrice() {
		return costPrice;
	}

	public int getNumericCostPrice() {
		return costPrice.get();
	}

	public void setCostPrice(int costPrice) {
		if (costPrice < NO_PRICE)
			UIHandler.showError("Product's cost price must be a non-negative number.");
		this.costPrice = new SimpleIntegerProperty(costPrice);
	}

	public SimpleIntegerProperty getObservableSellingPrice() {
		return sellingPrice;
	}

	public int getNumericSellingPrice() {
		return sellingPrice.get();
	}

	public void setSellingPrice(int sellingPrice) {
		if (sellingPrice < NO_PRICE)
			UIHandler.showError("Product's selling price must be a non-negative number.");
		this.sellingPrice = new SimpleIntegerProperty(sellingPrice);
	}
	
	// Constructors
	public ProductModel(String name, int costPrice, int sellingPrice) {
		setName(name);
		setCostPrice(costPrice);
		setSellingPrice(sellingPrice);
	}

	// Methods

	@Override
	public int compareTo(ProductModel other) {
		return getTextualName().compareTo(other.getTextualName());
	}
}