package ID316334473_ID302309513.Models;

import ID316334473_ID302309513.UIHandler;
import ID316334473_ID302309513.Models.Commands.NotifyCustomerCommand;
import ID316334473_ID302309513.Views.NotifyCustomersView;
import javafx.collections.ObservableList;

public class SenderThreadModel extends Thread implements iSender, iReciever {
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
			Thread.sleep(2000);
			notifyCustomersView.getLogTextArea().appendText("Ping initiating...\n");

			for (CustomerModel recipient : recipients) {
				Thread.sleep(2000);
				if (recipient.isInterestedInUpdates())
					new NotifyCustomerCommand(notifyCustomersView, recipient).execute();
			}

			notifyCustomersView.getLogTextArea().appendText("Done.\n");
		} catch (InterruptedException ex) {
			instance = null; // in order for thread to start again on command
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public void sendMessage(iReciever reciever, String message) {
		// TODO: COMPLETE
	}

	@Override
	public void receiveMessage(iSender sender, String message) {
		// TODO: COMPLETE
	}
}