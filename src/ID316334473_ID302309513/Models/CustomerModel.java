package ID316334473_ID302309513.Models;

import ID316334473_ID302309513.ByteConverter;
import javafx.beans.property.SimpleStringProperty;

public class CustomerModel implements iByteable, iSend, iRecieve {
	// Constants

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
		this.name = new SimpleStringProperty(name);
	}

	public SimpleStringProperty getObservablePhoneNumber() {
		return phoneNumber;
	}

	public String getTextualPhoneNumber() {
		return phoneNumber.get();
	}

	private void setPhoneNumber(String PhoneNumber) {
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
	public byte[] toByteArray() {
		byte[] customerBytes = new byte[getLengthInBytes()], bufferBytes = null;
		int currentOffset = 0;

		bufferBytes = ByteConverter.fromInteger(getTextualName().length());
		System.arraycopy(bufferBytes, 0, customerBytes, currentOffset, bufferBytes.length);
		currentOffset += bufferBytes.length;
		bufferBytes = ByteConverter.fromString(getTextualName());
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
		return (4 + getTextualName().length()) + 10 + 1;
	}

	@Override
	public void sendMessage(iRecieve reciever, String message) {
		reciever.receiveMessage(this, message);
	}

	@Override
	public void receiveMessage(iSend sender, String message) {
		sendMessage(SenderThreadModel.getInstance(), getTextualName() + " approved");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (interestedInUpdates ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
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
		CustomerModel other = (CustomerModel) obj;
		if (interestedInUpdates != other.interestedInUpdates)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Customer [Name:%s Phone:%s]", getTextualName(), getTextualPhoneNumber());
	}
}