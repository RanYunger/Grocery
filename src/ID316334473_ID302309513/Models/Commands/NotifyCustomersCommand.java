package ID316334473_ID302309513.Models.Commands;

import ID316334473_ID302309513.UIHandler;
import ID316334473_ID302309513.Models.CustomerModel;
import ID316334473_ID302309513.Models.SenderThreadModel;
import ID316334473_ID302309513.Views.MainView;
import javafx.collections.ObservableList;

public class NotifyCustomersCommand implements iCommand {
	// Fields
	
	// Constructors
	public NotifyCustomersCommand() {
		
	}
	
	// Methods
	@Override
	public void execute() {
		MainView mainView = UIHandler.getMainView();
		ObservableList<CustomerModel> allCustomers = mainView.getAllCustomers();

		for (CustomerModel customer : allCustomers) {
			if(customer.isInterestedInUpdates()) {
				SenderThreadModel.getInstance().start();
			}
		}
	}
}