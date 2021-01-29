package ID316334473_ID302309513.Models;

import java.io.Serializable;

import ID316334473_ID302309513.UIHandler;
import javafx.beans.property.SimpleStringProperty;

public class CustomerModel implements Serializable {
	// Constants
	private static final long serialVersionUID = 1L;
	
	public static final int NO_PRICE = 0;

	// Fields
	private SimpleStringProperty name, phoneNumber;
	private boolean interestedInUpdates;

	// Properties (Getters and Setters)
	public SimpleStringProperty getObservableName() {
		return name;
	}

	public String getTextualName() {
		return name.get();
	}

	private void setName(String name) {
		if (name.isBlank())
			UIHandler.showError(null, "CustomerModel's name must contain at least 1 letter.");
		this.name = new SimpleStringProperty(name);
	}

	public SimpleStringProperty getObservablePhoneNumber() {
		return phoneNumber;
	}

	public String getTextualPhoneNumber() {
		return phoneNumber.get();
	}

	private void setPhoneNumber(String PhoneNumber) {
		if (PhoneNumber.isBlank())
			UIHandler.showError(null, "CustomerModel's phone Number must contain at least 1 letter.");
		this.phoneNumber = new SimpleStringProperty(PhoneNumber);
	}

	public boolean isInterestedInUpdates() {
		return interestedInUpdates;
	}

	public void setInterestedInUpdates(boolean interested) {
		this.interestedInUpdates = interested;
	}

	// Constructors
	public CustomerModel(String name, String phoneNumber) {
		this(name, phoneNumber, false);
	}

	public CustomerModel(String name, String phoneNumber, boolean interestedInUpdates) {
		setName(name);
		setPhoneNumber(phoneNumber);
		setInterestedInUpdates(interestedInUpdates);
	}

	// Methods
	@Override
	public String toString() {
		return String.format("Customer [%s %s]", name, phoneNumber);
	}
}
