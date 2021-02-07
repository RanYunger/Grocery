package ID316334473_ID302309513.Controllers;

import java.util.Map;
import java.util.Optional;

import ID316334473_ID302309513.UIHandler;
import ID316334473_ID302309513.Models.ProductModel;
import ID316334473_ID302309513.Models.Commands.RemoveAllProductsCommand;
import ID316334473_ID302309513.Models.Commands.RemoveProductCommand;
import ID316334473_ID302309513.Models.Commands.UndoCommand;
import ID316334473_ID302309513.Views.AddProductView;
import ID316334473_ID302309513.Views.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;

public class MainController extends WindowController {
	// Constants

	// Fields

	// Properties (Getters and Setters)
	public MainView getMainView() {
		return (MainView) getView();
	}

	// Constructors
	public MainController(MainView view) {
		super(view);

		MainView mainView = getMainView();

		EventHandler<ActionEvent> addProductButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				AddProductView addProductView = new AddProductView();
				AddProductController addProductController = new AddProductController(addProductView);

				addProductController.addEventHandlersToGeneralButtons();
			}
		};
		EventHandler<ActionEvent> removeLastProductButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new UndoCommand().execute();
			}
		};
		EventHandler<ActionEvent> removeSelectedProductButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				TableView<Map.Entry<String, ProductModel>> productsTableView = mainView.getProductsTableView();
				ProductModel selectedProduct = null;
				Optional<ButtonType> userChoice = null;

				if (productsTableView.getSelectionModel().getSelectedIndex() == -1)
					UIHandler.showError(mainView.getStage(), "Choose a product to remove");
				else {
					selectedProduct = productsTableView.getSelectionModel().getSelectedItem().getValue();
					userChoice = UIHandler.showConfirmation(mainView.getStage(), "Nothing special here. GTF back up!");
					if ((userChoice.isPresent()) && (userChoice.get() == ButtonType.YES))
						new RemoveProductCommand(selectedProduct).execute();
				}
			}
		};
		EventHandler<ActionEvent> removeAllProductsButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Optional<ButtonType> userChoice = UIHandler.showConfirmation(mainView.getStage(),
						"Nothing special here. GTF back up!");

				if ((userChoice.isPresent()) && (userChoice.get() == ButtonType.YES)) {
					new RemoveAllProductsCommand().execute();
					UIHandler.showFatalError(UIHandler.getMainView().getStage(), "Everything is gone!",
							"NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\nOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO!");
				}
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
		mainView.getRemoveSelectedProductButton().setOnAction(removeSelectedProductButtonEventHandler);
		mainView.getRemoveAllProductsButton().setOnAction(removeAllProductsButtonEventHandler);
		mainView.getNotifyCustomersButton().setOnAction(notifyCustomersButtonEventHandler);
		// mainView.getStage().setOnCloseRequest(e -> mainView.writeAllProducts());
	}

	// Methods
}