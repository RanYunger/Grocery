package ID316334473_ID302309513.Controllers;

import ID316334473_ID302309513.Views.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MainController extends WindowController {
	// Constants

	// Fields

	// Properties (Getters and Setters)
	public MainView getMainView() {
		return (MainView) getView();
	}

	// Constructors
	public MainController(MainView view) {
		this(view, 0);
	}

	public MainController(MainView view, int sortOption) {
		super(view);

		MainView mainView = getMainView();

		EventHandler<ActionEvent> addProductButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO: COMPLETE
			}
		};
		EventHandler<ActionEvent> removeLastProductButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO: COMPLETE
			}
		};
		EventHandler<ActionEvent> removeProductByIDButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO: COMPLETE
			}
		};
		EventHandler<ActionEvent> removeAllProductsButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO: COMPLETE
			}
		};
		EventHandler<ActionEvent> notifyCustomersButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO: COMPLETE
			}
		};

		mainView.getAddProductButton().setOnAction(addProductButtonEventHandler);
		mainView.getRemoveLastProductButton().setOnAction(removeLastProductButtonEventHandler);
		mainView.getRemoveProductByIDButton().setOnAction(removeProductByIDButtonEventHandler);
		mainView.getRemoveAllProductsButton().setOnAction(removeAllProductsButtonEventHandler);
		mainView.getNotifyCustomersButton().setOnAction(notifyCustomersButtonEventHandler);
		mainView.getStage().setOnCloseRequest(e -> mainView.writeAllProducts());
	}

	// Methods
}