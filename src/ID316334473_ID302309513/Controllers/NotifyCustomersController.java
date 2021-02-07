package ID316334473_ID302309513.Controllers;

import ID316334473_ID302309513.UIHandler;
import ID316334473_ID302309513.Views.NotifyCustomersView;
import ID316334473_ID302309513.Views.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class NotifyCustomersController extends WindowController {
	// Fields

	// Properties (Getters and Setters)
	public NotifyCustomersView getNotifyCustomersView() {
		return (NotifyCustomersView) getView();
	}

	// Constructors
	public NotifyCustomersController(View view) {
		super(view);

		NotifyCustomersView notifyCustomersView = getNotifyCustomersView();

		EventHandler<ActionEvent> finishButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				notifyCustomersView.getSenderThread().interrupt();
				UIHandler.showWarning(notifyCustomersView.getStage(), "Sending interrupted", true);
			}
		};

		notifyCustomersView.getFinishButton().setOnAction(finishButtonEventHandler);
		notifyCustomersView.getStage()
				.setOnCloseRequest(e -> UIHandler.getMainView().getNotifyCustomersButton().setDisable(false));
	}

	// Methods
}
