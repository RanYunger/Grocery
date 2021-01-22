package ID316334473_ID302309513.Models;

import ID316334473_ID302309513.UIHandler;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public abstract class ProductModel implements Comparable<ProductModel> {
	// Constants
	public static final int NO_PRICE = 0, FIRST_QUEUE_NUMBER = 1;
	private static int queueNumberGenerator = FIRST_QUEUE_NUMBER;

	// Fields
	private SimpleStringProperty id, name;
	private SimpleIntegerProperty queueNumber, costPrice, sellingPrice, profit;
	private CustomerModel customer;

	// Properties (Getters and Setters)
	public SimpleStringProperty getObservableName() {
		return name;
	}

	public String getTextualName() {
		return name.get();
	}

	private void setName(String name) {
		if (name.isBlank())
			UIHandler.showError("Product's name must contain at least 1 letter.");
		this.name = new SimpleStringProperty(name);
	}
	
	public SimpleStringProperty getObservableID() {
		return id;
	}

	public String getTextualID() {
		return id.get();
	}

	public void setID(String ID) {
		if (ID.isBlank())
			UIHandler.showError("Product's name must contain at least 1 letter.");
		this.id = new SimpleStringProperty(ID);
	}
	
	public SimpleIntegerProperty getObservableQueueNumber() {
		return queueNumber;
	}

	public int getNumericQueueNumber() {
		return queueNumber.get();
	}

	private void setQueueNumber(int QueueNumber) {
		if (QueueNumber < FIRST_QUEUE_NUMBER)
			UIHandler.showError("Product's queue number must be a non-negative number.");
		this.queueNumber = new SimpleIntegerProperty(QueueNumber);
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
	
	public SimpleIntegerProperty getObservableProfit() {
		return profit;
	}

	public int getNumericProfit() {
		return profit.get();
	}

	private void setProfit(int profit) {
		this.profit = new SimpleIntegerProperty(profit);
	}

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	// Constructors
	public ProductModel(String name, int costPrice, int sellingPrice) {
		setName(name);
		setQueueNumber(queueNumberGenerator++);
		setCostPrice(costPrice);
		setSellingPrice(sellingPrice);
		setProfit(sellingPrice - costPrice);
		setCustomer(null);
	}

	// Methods
	@Override
	public int compareTo(ProductModel other) {
		return getTextualID().compareTo(other.getTextualID());
	}
}