package ID316334473_ID302309513.Models;

import ID316334473_ID302309513.ByteConverter;
import ID316334473_ID302309513.UIHandler;
import javafx.beans.property.SimpleStringProperty;

public class CustomerModel implements iByteable {
	// Constants
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

	private void setInterestedInUpdates(boolean interested) {
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
		return String.format("Customer [Name:%s Phone:%s]", getTextualName(), getTextualPhoneNumber());
	}

	@Override
	public byte[] toByteArray() {
		byte[] customerBytes = new byte[getLengthInBytes()], bufferBytes = null;
		int currentOffset = 0;

		bufferBytes = ByteConverter.fromInteger(getTextualName().length());
		System.arraycopy(bufferBytes, 0, customerBytes, currentOffset, bufferBytes.length);
		currentOffset += bufferBytes.length;
		bufferBytes = ByteConverter.fromString(getTextualName());
		System.arraycopy(bufferBytes, 0, customerBytes, currentOffset, bufferBytes.length);
		currentOffset += bufferBytes.length;

		bufferBytes = ByteConverter.fromInteger(getTextualPhoneNumber().length());
		System.arraycopy(bufferBytes, 0, customerBytes, currentOffset, bufferBytes.length);
		currentOffset += bufferBytes.length;
		bufferBytes = ByteConverter.fromString(getTextualPhoneNumber());
		System.arraycopy(bufferBytes, 0, customerBytes, currentOffset, bufferBytes.length);
		currentOffset += bufferBytes.length;

		customerBytes[customerBytes.length - 1] = ByteConverter.fromBoolean(interestedInUpdates);

		return customerBytes;
	}

	@Override
	public int getLengthInBytes() {
		return (4 + getTextualName().length()) + (4 + getTextualPhoneNumber().length()) + 1;
	}
}