package ID316334473_ID302309513.Controllers;

import ID316334473_ID302309513.UIHandler;
import ID316334473_ID302309513.Models.SenderThreadModel;
import ID316334473_ID302309513.Views.NotifyCustomersView;
import ID316334473_ID302309513.Views.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;

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

		EventHandler<MouseEvent> bellImageViewEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				UIHandler.playAudio("ForWhomTheBELLTolls.mp3");
			}
		};
		EventHandler<ActionEvent> finishButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SenderThreadModel.getInstance().interrupt();
				UIHandler.showWarning(notifyCustomersView.getStage(), "Sending interrupted", true);
			}
		};
		EventHandler<WindowEvent> viewCloseEventHandler = new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				SenderThreadModel.getInstance().interrupt();
				UIHandler.getMainView().getNotifyCustomersButton().setDisable(false);
			}
		};

		notifyCustomersView.getBellImageView().setOnMouseClicked(bellImageViewEventHandler);
		notifyCustomersView.getFinishButton().setOnAction(finishButtonEventHandler);
		notifyCustomersView.getStage().setOnCloseRequest(viewCloseEventHandler);
	}

	// Methods
}