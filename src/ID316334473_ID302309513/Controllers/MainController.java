package ID316334473_ID302309513.Controllers;

import java.util.Map;
import java.util.Optional;

import ID316334473_ID302309513.UIHandler;
import ID316334473_ID302309513.Models.ProductModel;
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
				// TODO: COMPLETE
			}
		};
		EventHandler<ActionEvent> removeSelectedProductButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				TableView<Map.Entry<String, ProductModel>> productsTableView = mainView.getProductsTableView();
				ProductModel selectedProduct = productsTableView.getSelectionModel().getSelectedItem().getValue();
				Optional<ButtonType> userChoice = null;

				if (selectedProduct != null) {
					userChoice = UIHandler.showConfirmation(mainView.getStage(), "Nothing special here. GTF back up!");
					if ((userChoice.isPresent()) && (userChoice.get() == ButtonType.YES)) {
						mainView.removeProduct(selectedProduct);
						UIHandler.showSuccess(mainView.getStage(),
								String.format("Product #%s deleted successfuly!", selectedProduct.getTextualID()),
								true);
					}
				}
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
		mainView.getRemoveSelectedProductButton().setOnAction(removeSelectedProductButtonEventHandler);
		mainView.getRemoveAllProductsButton().setOnAction(removeAllProductsButtonEventHandler);
		mainView.getNotifyCustomersButton().setOnAction(notifyCustomersButtonEventHandler);
		// mainView.getStage().setOnCloseRequest(e -> mainView.writeAllProducts());
	}

	// Methods
}