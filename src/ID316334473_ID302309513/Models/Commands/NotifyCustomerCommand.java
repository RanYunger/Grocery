package ID316334473_ID302309513.Models.Commands;

import ID316334473_ID302309513.Views.NotifyCustomersView;

public class NotifyCustomerCommand implements iCommand {
	// Fields
	private NotifyCustomersView notifyCustomersView;
	private String message;

	// Properties (Getters and Setters)
	private void SetNotifyCustomersView(NotifyCustomersView notifyCustomersView) {
		this.notifyCustomersView = notifyCustomersView;
	}

	private void setMessage(String message) {
		this.message = message;
	}

	// Constructors
	public NotifyCustomerCommand(NotifyCustomersView notifyCustomersView, String message) {
		SetNotifyCustomersView(notifyCustomersView);
		setMessage(message);
	}

	// Methods
	@Override
	public void execute() {
		notifyCustomersView.addToLog(message);
	}
}