package ID316334473_ID302309513.Models;

import ID316334473_ID302309513.UIHandler;
import ID316334473_ID302309513.Models.Commands.NotifyCustomerCommand;
import ID316334473_ID302309513.Views.NotifyCustomersView;
import javafx.collections.ObservableList;

public class SenderThreadModel extends Thread implements iSend, iRecieve {
	// Constants

	// Fields
	private static SenderThreadModel instance;

	private NotifyCustomersView notifyCustomersView;
	private ObservableList<CustomerModel> recipients;

	// Properties (Getters and Setters)
	public void setNotifyCustomersView(NotifyCustomersView notifyCustomersView) {
		this.notifyCustomersView = notifyCustomersView;
	}

	public ObservableList<CustomerModel> getRecipients() {
		return recipients;
	}

	private void setRecipients(ObservableList<CustomerModel> recipients) {
		this.recipients = recipients;
	}

	// Constructors
	private SenderThreadModel() {
		setRecipients(UIHandler.getMainView().getAllCustomers());
	}

	// Methods
	public static SenderThreadModel getInstance() {
		instance = instance == null ? new SenderThreadModel() : instance;

		return instance;
	}

	@Override
	public void run() {
		try {
			notifyCustomersView.getLogTextArea().appendText("Ping initiating...\n");

			for (CustomerModel recipient : recipients) {
				Thread.sleep(2000);
				if (recipient.isInterestedInUpdates())
					sendMessage(recipient, String.format("Notification sent to %s", recipient.getTextualName()));
			}

			notifyCustomersView.getFinishButton().setDisable(true);
			notifyCustomersView.getLogTextArea().appendText("Ping complete.\n");
		} catch (Exception ex) {
		} finally {
			instance = null; // in order for thread to start again on command
		}
	}

	@Override
	public void sendMessage(iRecieve reciever, String message) {
		new NotifyCustomerCommand(notifyCustomersView, message).execute();
		reciever.receiveMessage(this, message);
	}

	@Override
	public void receiveMessage(iSend sender, String message) {
		new NotifyCustomerCommand(notifyCustomersView, message).execute();
	}
}