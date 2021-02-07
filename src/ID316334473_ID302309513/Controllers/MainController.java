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
import ID316334473_ID302309513.Views.NotifyCustomersView;
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
					UIHandler.showError(mainView.getStage(), "Choose a product to remove", "");
				else {
					selectedProduct = productsTableView.getSelectionModel().getSelectedItem().getValue();
					userChoice = UIHandler.showConfirmation(mainView.getStage(),
							"Look, Ran, another idiot fell for that... (¬_¬)ﾉ( ^_^)／");
					if ((userChoice.isPresent()) && (userChoice.get() == ButtonType.YES)) {
						new RemoveProductCommand(selectedProduct).execute();
						UIHandler.showSuccess(mainView.getStage(),
								String.format("Product #%s removed successfuly!", selectedProduct.getTextualID()),
								true);
					}
				}
			}
		};
		EventHandler<ActionEvent> removeAllProductsButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Optional<ButtonType> userChoice = UIHandler.showConfirmation(mainView.getStage(),
						"Look, Ran, another idiot fell for that... (¬_¬)ﾉ( ^_^)／");

				if ((userChoice.isPresent()) && (userChoice.get() == ButtonType.YES)) {
					new RemoveAllProductsCommand().execute();
					UIHandler.showFatalError(UIHandler.getMainView().getStage(), "Everything is gone!",
							"─▄▀▀▀▀▄─█──█────▄▀▀█─▄▀▀▀▀▄─█▀▀▄\r\n" + "─█────█─█──█────█────█────█─█──█\r\n"
									+ "─█────█─█▀▀█────█─▄▄─█────█─█──█\r\n" + "─▀▄▄▄▄▀─█──█────▀▄▄█─▀▄▄▄▄▀─█▄▄▀\r\n"
									+ "\r\n" + "─────────▄██████▀▀▀▀▀▀▄\r\n" + "─────▄█████████▄───────▀▀▄▄\r\n"
									+ "──▄█████████████───────────▀▀▄\r\n" + "▄██████████████─▄▀───▀▄─▀▄▄▄──▀▄\r\n"
									+ "███████████████──▄▀─▀▄▄▄▄▄▄────█\r\n" + "█████████████████▀█──▄█▄▄▄──────█\r\n"
									+ "███████████──█▀█──▀▄─█─█─█───────█\r\n"
									+ "████████████████───▀█─▀██▄▄──────█\r\n"
									+ "█████████████████──▄─▀█▄─────▄───█\r\n"
									+ "█████████████████▀███▀▀─▀▄────█──█\r\n"
									+ "████████████████──────────█──▄▀──█\r\n"
									+ "████████████████▄▀▀▀▀▀▀▄──█──────█\r\n"
									+ "████████████████▀▀▀▀▀▀▀▄──█──────█\r\n"
									+ "▀████████████████▀▀▀▀▀▀──────────█\r\n" + "──███████████████▀▀─────█──────▄▀\r\n"
									+ "──▀█████████████────────█────▄▀\r\n" + "────▀████████████▄───▄▄█▀─▄█▀\r\n"
									+ "──────▀████████████▀▀▀──▄███\r\n" + "──────████████████████████─█\r\n"
									+ "─────████████████████████──█\r\n" + "────████████████████████───█\r\n"
									+ "────██████████████████─────█\r\n" + "────██████████████████─────█\r\n"
									+ "────██████████████████─────█\r\n" + "────██████████████████─────█\r\n"
									+ "────██████████████████▄▄▄▄▄█\r\n" + "\r\n"
									+ "─────────────█─────█─█──█─█───█\r\n" + "─────────────█─────█─█──█─▀█─█▀\r\n"
									+ "─────────────█─▄█▄─█─█▀▀█──▀█▀\r\n" + "─────────────██▀─▀██─█──█───█");
				}
			}
		};
		EventHandler<ActionEvent> notifyCustomersButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (mainView.getAllCustomers().isEmpty()) {
					UIHandler.showError(mainView.getStage(), "No customers to notify", "");
					return;
				}

				NotifyCustomersView notifyCustomersView = new NotifyCustomersView();
				NotifyCustomersController notifyCustomersController = new NotifyCustomersController(
						notifyCustomersView);

				mainView.getNotifyCustomersButton().setDisable(true);
				notifyCustomersController.addEventHandlersToGeneralButtons();
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