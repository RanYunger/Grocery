package ID316334473_ID302309513.Models;

import ID316334473_ID302309513.UIHandler;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DiscountModel {
	// Constants
	public static final int NO_DISCOUNT = 0; 

	// Fields
	private SimpleStringProperty productName;
	private SimpleIntegerProperty percentage;

	// Properties (Getters and Setters)
	public SimpleStringProperty getObservableProductName() {
		return productName;
	}

	public String getTextualProductName() {
		return productName.get();
	}

	private void setProductName(String productName) {
		if (productName.isBlank())
			UIHandler.showError("Discount's product name must contain at least 1 letter.");
		this.productName = new SimpleStringProperty(productName);
	}
	
	public SimpleIntegerProperty getObservablePercentage() {
		return percentage;
	}

	public int getNumericPercentage() {
		return percentage.get();
	}

	private void setPercentage(int QueueNumber) {
		if (QueueNumber < NO_DISCOUNT)
			UIHandler.showError("Discount's percentage number must be a non-negative number.");
		this.percentage = new SimpleIntegerProperty(QueueNumber);
	}

	// Constructors
	public DiscountModel(String productName, int percentage) {
		setProductName(productName);
		setPercentage(percentage);
	}

	// Methods
}
