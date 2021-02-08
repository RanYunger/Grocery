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
import javafx.stage.WindowEvent;

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

				if ((mainView.getAllProductsSorted().isEmpty()) && (mainView.getAllProductsUnsorted().isEmpty()))
					UIHandler.showError(mainView.getStage(), "No Products to remove", "");
				else if (productsTableView.getSelectionModel().getSelectedIndex() == -1)
					UIHandler.showError(mainView.getStage(), "Choose a product to remove", "");
				else {
					selectedProduct = productsTableView.getSelectionModel().getSelectedItem().getValue();
					userChoice = UIHandler.showConfirmation(mainView.getStage(),
							"\"Look, Ran, another one fell for that...\" (¬_¬)ﾉ( ^_^)／");
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
				Optional<ButtonType> userChoice = null;

				if ((mainView.getAllProductsSorted().isEmpty()) && (mainView.getAllProductsUnsorted().isEmpty()))
					UIHandler.showError(mainView.getStage(), "No Products to remove", "");
				else {
					userChoice = UIHandler.showConfirmation(mainView.getStage(),
							"\"Look, Ran, another one fell for that...\" (¬_¬)ﾉ( ^_^)／");
					if ((userChoice.isPresent()) && (userChoice.get() == ButtonType.YES)) {
						new RemoveAllProductsCommand().execute();
						UIHandler.showRemoveAllProductSuccess(UIHandler.getMainView().getStage(),
								"All products removed successfuly!",
								"─▄▀▀▀▀▄─█──█────▄▀▀█─▄▀▀▀▀▄─█▀▀▄\r\n" + "─█────█─█──█────█────█────█─█──█\r\n"
										+ "─█────█─█▀▀█────█─▄▄─█────█─█──█\r\n"
										+ "─▀▄▄▄▄▀─█──█────▀▄▄█─▀▄▄▄▄▀─█▄▄▀\r\n" + "\r\n"
										+ "─────────▄██████▀▀▀▀▀▀▄\r\n" + "─────▄█████████▄───────▀▀▄▄\r\n"
										+ "──▄█████████████───────────▀▀▄\r\n" + "▄██████████████─▄▀───▀▄─▀▄▄▄──▀▄\r\n"
										+ "███████████████──▄▀─▀▄▄▄▄▄▄────█\r\n"
										+ "█████████████████▀█──▄█▄▄▄──────█\r\n"
										+ "███████████──█▀█──▀▄─█─█─█───────█\r\n"
										+ "████████████████───▀█─▀██▄▄──────█\r\n"
										+ "█████████████████──▄─▀█▄─────▄───█\r\n"
										+ "█████████████████▀███▀▀─▀▄────█──█\r\n"
										+ "████████████████──────────█──▄▀──█\r\n"
										+ "████████████████▄▀▀▀▀▀▀▄──█──────█\r\n"
										+ "████████████████▀▀▀▀▀▀▀▄──█──────█\r\n"
										+ "▀████████████████▀▀▀▀▀▀──────────█\r\n"
										+ "──███████████████▀▀─────█──────▄▀\r\n"
										+ "──▀█████████████────────█────▄▀\r\n" + "────▀████████████▄───▄▄█▀─▄█▀\r\n"
										+ "──────▀████████████▀▀▀──▄███\r\n" + "──────████████████████████─█\r\n"
										+ "─────████████████████████──█\r\n" + "────████████████████████───█\r\n"
										+ "────██████████████████─────█\r\n" + "────██████████████████─────█\r\n"
										+ "────██████████████████─────█\r\n" + "────██████████████████─────█\r\n"
										+ "────██████████████████▄▄▄▄▄█\r\n" + "\r\n"
										+ "─────────────█─────█─█──█─█───█\r\n" + "─────────────█─────█─█──█─▀█─█▀\r\n"
										+ "─────────────█─▄█▄─█─█▀▀█──▀█▀\r\n" + "─────────────██▀─▀██─█──█───█");
					} else
						UIHandler.playAudio("Chicken.mp3");
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
		EventHandler<WindowEvent> viewCloseEventHandler = new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				Optional<ButtonType> userChoice = UIHandler.showConfirmation(mainView.getStage(),
						"(ಥ_ʖಥ)(ಥ_ʖಥ) \"Please don't go! stay with us! Didn't you have fun?\"");

				try {
					if ((userChoice.isPresent()) && (userChoice.get() == ButtonType.YES)) {
						UIHandler.playAudio("ThankYouComeAgain.wav"); // TODO: INCREASE VOLUME
					}
				} catch (Exception ex) {
				}
			}
		};

		mainView.getAddProductButton().setOnAction(addProductButtonEventHandler);
		mainView.getRemoveLastProductButton().setOnAction(removeLastProductButtonEventHandler);
		mainView.getRemoveSelectedProductButton().setOnAction(removeSelectedProductButtonEventHandler);
		mainView.getRemoveAllProductsButton().setOnAction(removeAllProductsButtonEventHandler);
		mainView.getNotifyCustomersButton().setOnAction(notifyCustomersButtonEventHandler);
		mainView.getStage().setOnCloseRequest(viewCloseEventHandler);
	}

	// Methods
}