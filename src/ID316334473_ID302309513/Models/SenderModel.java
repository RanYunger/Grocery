package ID316334473_ID302309513.Models;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SenderModel extends Thread {
	// Constants

	// Fields
	private static SenderModel instance;
	private ArrayList<CustomerModel> recipients;

	// Properties (Getters and Setters)
	public ArrayList<CustomerModel> getRecipients() {
		return recipients;
	}

	private void setRecipients(ArrayList<CustomerModel> recipients) {
		this.recipients = recipients;
	}

	// Constructors
	private SenderModel() {
		setRecipients(new ArrayList<CustomerModel>());
	}

	// Methods
	public static SenderModel getInstance() {
		instance = instance == null ? new SenderModel() : instance;

		return instance;
	}

	@Override
	public void run() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String message = "";
		
		try {
			for (CustomerModel recipient : recipients) {
				message = String.format("[%s] %s recieved a notification.", LocalTime.now().format(formatter), recipient.getTextualName()); 
				// TODO: print message to window (probably huge textbox or something)
				Thread.sleep(2000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
