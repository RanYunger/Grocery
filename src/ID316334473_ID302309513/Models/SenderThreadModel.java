package ID316334473_ID302309513.Models;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import ID316334473_ID302309513.UIHandler;
import javafx.collections.ObservableList;

public class SenderThreadModel extends Thread {
	// Constants

	// Fields
	private static SenderThreadModel instance;
	private ObservableList<CustomerModel> recipients;

	// Properties (Getters and Setters)
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String message = "";

		try {
			for (CustomerModel recipient : recipients) {
				message = String.format("[%s] %s recieved a notification.", LocalTime.now().format(formatter),
						recipient.getTextualName());
				// TODO: print message to window (probably huge textbox or something)
				Thread.sleep(2000);
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
