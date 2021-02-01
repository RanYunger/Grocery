package ID316334473_ID302309513.Models;

import ID316334473_ID302309513.ByteConverter;
import ID316334473_ID302309513.UIHandler;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProductModel implements Comparable<ProductModel>, iByteable {
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
			UIHandler.showError(null, "Product's name must contain at least 1 letter.");
		this.name = new SimpleStringProperty(name);
	}

	public SimpleStringProperty getObservableID() {
		return id;
	}

	public String getTextualID() {
		return id.get();
	}

	private void setID(String ID) {
		if (ID.isBlank())
			UIHandler.showError(null, "Product's name must contain at least 1 letter.");
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
			UIHandler.showError(null, "Product's queue number must be a non-negative number.");
		this.queueNumber = new SimpleIntegerProperty(QueueNumber);
	}

	public SimpleIntegerProperty getObservableCostPrice() {
		return costPrice;
	}

	public int getNumericCostPrice() {
		return costPrice.get();
	}

	private void setCostPrice(int costPrice) {
		if (costPrice < NO_PRICE)
			UIHandler.showError(null, "Product's cost price must be a non-negative number.");
		this.costPrice = new SimpleIntegerProperty(costPrice);
	}

	public SimpleIntegerProperty getObservableSellingPrice() {
		return sellingPrice;
	}

	public int getNumericSellingPrice() {
		return sellingPrice.get();
	}

	private void setSellingPrice(int sellingPrice) {
		if (sellingPrice < NO_PRICE)
			UIHandler.showError(null, "Product's selling price must be a non-negative number.");
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

	private void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	// Constructors
	public ProductModel(String ID, String name, int costPrice, int sellingPrice, String customerName,
			String phoneNumber, boolean interestedInUpdates) {
		this(ID, name, costPrice, sellingPrice,
				new CustomerModel(customerName, phoneNumber, interestedInUpdates));
	}

	public ProductModel(String ID, String name, int costPrice, int sellingPrice, CustomerModel customer) {
		setID(ID);
		setName(name);
		setQueueNumber(queueNumberGenerator++);
		setCostPrice(costPrice);
		setSellingPrice(sellingPrice);
		setProfit(sellingPrice - costPrice);
		setCustomer(customer);
	}

	// Methods
	@Override
	public int compareTo(ProductModel other) {
		return getTextualID().compareTo(other.getTextualID());
	}

	@Override
	public String toString() {
		return String.format("Product [ID:%s Name:%s (#%d) Cost:%d Selling:%d]", getTextualID(), getTextualName(),
				getNumericQueueNumber(), getNumericCostPrice(), getNumericSellingPrice());
	}

	@Override
	public byte[] toByteArray() {
		byte[] productBytes = new byte[getLengthInBytes()], customerBytes = customer.toByteArray(), bufferBytes = null;
		int currentOffset = 0;

		bufferBytes = ByteConverter.fromInteger(getTextualID().length());
		System.arraycopy(bufferBytes, 0, customerBytes, currentOffset, bufferBytes.length);
		currentOffset += bufferBytes.length;
		bufferBytes = ByteConverter.fromString(getTextualID());
		System.arraycopy(bufferBytes, 0, customerBytes, currentOffset, bufferBytes.length);
		currentOffset += bufferBytes.length;

		bufferBytes = ByteConverter.fromInteger(getTextualName().length());
		System.arraycopy(bufferBytes, 0, customerBytes, currentOffset, bufferBytes.length);
		currentOffset += bufferBytes.length;
		bufferBytes = ByteConverter.fromString(getTextualName());
		System.arraycopy(bufferBytes, 0, customerBytes, currentOffset, bufferBytes.length);
		currentOffset += bufferBytes.length;

		bufferBytes = ByteConverter.fromInteger(getNumericQueueNumber());
		System.arraycopy(bufferBytes, 0, customerBytes, currentOffset, bufferBytes.length);
		currentOffset += bufferBytes.length;

		bufferBytes = ByteConverter.fromInteger(getNumericCostPrice());
		System.arraycopy(bufferBytes, 0, customerBytes, currentOffset, bufferBytes.length);
		currentOffset += bufferBytes.length;

		bufferBytes = ByteConverter.fromInteger(getNumericSellingPrice());
		System.arraycopy(bufferBytes, 0, customerBytes, currentOffset, bufferBytes.length);
		currentOffset += bufferBytes.length;

		bufferBytes = ByteConverter.fromInteger(getNumericProfit());
		System.arraycopy(bufferBytes, 0, customerBytes, currentOffset, bufferBytes.length);
		currentOffset += bufferBytes.length;

		System.arraycopy(customerBytes, 0, productBytes, currentOffset, customerBytes.length);

		return productBytes;
	}

	@Override
	public int getLengthInBytes() {
		return (4 + getTextualID().length()) + (4 + getTextualName().length()) + 16 + customer.getLengthInBytes();
	}
}