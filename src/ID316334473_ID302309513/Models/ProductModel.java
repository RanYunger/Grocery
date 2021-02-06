package ID316334473_ID302309513.Models;

import ID316334473_ID302309513.ByteConverter;
import ID316334473_ID302309513.UIHandler;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProductModel implements Comparable<ProductModel>, iByteable {
	// Constants
	public static final int NO_PRICE = 0, FIRST_QUEUE_NUMBER = 1;
	private static int QUEUE_GENERATOR = FIRST_QUEUE_NUMBER;

	// Fields
	private int queueNumber;
	private SimpleStringProperty id, name;
	private SimpleIntegerProperty costPrice, sellingPrice, profit;
	private CustomerModel customer;

	// Properties (Getters and Setters)
	public int getQueueNumber() {
		return queueNumber;
	}

	public SimpleStringProperty getObservableID() {
		return id;
	}

	public String getTextualID() {
		return id.get();
	}

	private void setID(String ID) {
		this.id = new SimpleStringProperty(ID);
	}

	public SimpleStringProperty getObservableName() {
		return name;
	}

	public String getTextualName() {
		return name.get();
	}

	private void setName(String name) {
		this.name = new SimpleStringProperty(name);
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
		this(ID, name, costPrice, sellingPrice, new CustomerModel(customerName, phoneNumber, interestedInUpdates));
	}

	public ProductModel(String ID, String name, int costPrice, int sellingPrice, CustomerModel customer) {
		queueNumber = QUEUE_GENERATOR++;
		setID(ID);
		setName(name);
		setCostPrice(costPrice);
		setSellingPrice(sellingPrice);
		setProfit(sellingPrice - costPrice);
		setCustomer(customer);
	}

	// Methods
	@Override
	public int compareTo(ProductModel other) {
		return compareTo(other, UIHandler.getSortOption());
	}

	public int compareTo(ProductModel other, int sortOption) {
		if (sortOption == 2) // Insertion Order
			return queueNumber - other.getQueueNumber();

		int thisNumericID = Integer.parseInt(getTextualID()), otherNumericID = Integer.parseInt(other.getTextualID());

		return sortOption == 1 ? thisNumericID - otherNumericID : otherNumericID - thisNumericID;
	}

	@Override
	public byte[] toByteArray() {
		byte[] productBytes = new byte[getLengthInBytes()],
				customerBytes = customer != null ? customer.toByteArray() : null, bufferBytes = null;
		byte productIDSeperator = (byte) '#';
		int currentOffset = 0;

		bufferBytes = ByteConverter.fromInteger(getTextualID().length());
		System.arraycopy(bufferBytes, 0, productBytes, currentOffset, bufferBytes.length);
		currentOffset += bufferBytes.length;
		productBytes[currentOffset++] = productIDSeperator;
		bufferBytes = ByteConverter.fromString(getTextualID());
		System.arraycopy(bufferBytes, 0, productBytes, currentOffset, bufferBytes.length);
		currentOffset += bufferBytes.length;
		productBytes[currentOffset++] = productIDSeperator;

		bufferBytes = ByteConverter.fromInteger(getTextualName().length());
		System.arraycopy(bufferBytes, 0, productBytes, currentOffset, bufferBytes.length);
		currentOffset += bufferBytes.length;
		bufferBytes = ByteConverter.fromString(getTextualName());
		System.arraycopy(bufferBytes, 0, productBytes, currentOffset, bufferBytes.length);
		currentOffset += bufferBytes.length;

		bufferBytes = ByteConverter.fromInteger(getNumericCostPrice());
		System.arraycopy(bufferBytes, 0, productBytes, currentOffset, bufferBytes.length);
		currentOffset += bufferBytes.length;

		bufferBytes = ByteConverter.fromInteger(getNumericSellingPrice());
		System.arraycopy(bufferBytes, 0, productBytes, currentOffset, bufferBytes.length);
		currentOffset += bufferBytes.length;

		productBytes[currentOffset++] = ByteConverter.fromBoolean(customer != null);
		if (customer != null)
			System.arraycopy(customerBytes, 0, productBytes, currentOffset, customerBytes.length);

		return productBytes;
	}

	@Override
	public int getLengthInBytes() {
		int customerLengthInBytes = customer != null ? customer.getLengthInBytes() : 0;

		return (4 + (1 + getTextualID().length() + 1)) + (4 + getTextualName().length()) + 8
				+ (1 + customerLengthInBytes);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((costPrice == null) ? 0 : costPrice.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((profit == null) ? 0 : profit.hashCode());
		result = prime * result + ((sellingPrice == null) ? 0 : sellingPrice.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductModel other = (ProductModel) obj;
		if (costPrice == null) {
			if (other.costPrice != null)
				return false;
		} else if (!costPrice.equals(other.costPrice))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (profit == null) {
			if (other.profit != null)
				return false;
		} else if (!profit.equals(other.profit))
			return false;
		if (sellingPrice == null) {
			if (other.sellingPrice != null)
				return false;
		} else if (!sellingPrice.equals(other.sellingPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String customerStr = customer == null ? "NO CUSTOMER" : customer.toString();

		return String.format("Product [ID:%s Name:%s Cost:%d Selling:%d %s]", getTextualID(), getTextualName(),
				getNumericCostPrice(), getNumericSellingPrice(), customerStr);
	}
}