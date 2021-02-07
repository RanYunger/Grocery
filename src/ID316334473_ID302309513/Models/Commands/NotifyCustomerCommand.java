package ID316334473_ID302309513.Models.Commands;

import ID316334473_ID302309513.Models.CustomerModel;
import ID316334473_ID302309513.Views.NotifyCustomersView;

public class NotifyCustomerCommand implements iCommand {
	// Fields
	private NotifyCustomersView notifyCustomersView;
	private CustomerModel recipient;
	
	// Constructors
	public NotifyCustomerCommand(NotifyCustomersView notifyCustomersView, CustomerModel recipient) {
		this.notifyCustomersView = notifyCustomersView;
		this.recipient = recipient;
	}
	
	// Methods
	@Override
	public void execute() {	
		notifyCustomersView.notifyCustomer(recipient.getTextualName());
	}
}