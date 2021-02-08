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
import javafx.stage.Stage;
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
		Stage stage = mainView.getStage();

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
					UIHandler.showError(stage, "No Products to remove", "");
				else if (productsTableView.getSelectionModel().getSelectedIndex() == -1)
					UIHandler.showError(stage, "Choose a product to remove", "");
				else {
					selectedProduct = productsTableView.getSelectionModel().getSelectedItem().getValue();
					userChoice = UIHandler.showConfirmation(stage, "Are you sure?",
							"\"Look, Ran, another one fell for that...\" (¬_¬)ﾉ( ^_^)／");
					if ((userChoice.isPresent()) && (userChoice.get() == ButtonType.YES)) {
						new RemoveProductCommand(selectedProduct).execute();
						UIHandler.showSuccess(stage,
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
					UIHandler.showError(stage, "No Products to remove", "");
				else {
					userChoice = UIHandler.showConfirmation(stage, "Are you sure?",
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
					UIHandler.showError(stage, "No customers to notify", "");
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
				Optional<ButtonType> userChoice = UIHandler.showConfirmation(stage, "Leaving so soon?",
						"(ʘᗩʘ’)(ʘᗩʘ’) \"Why? There's so much more for you to see...\"");

				if ((userChoice.isPresent()) && (userChoice.get() == ButtonType.YES)) {
					userChoice = UIHandler.showConfirmation(stage, "Do you really want to leave?",
							"(︶︹︺)(︶︹︺) \"But... all the easter eggs we've planted! The dinasours!\"");

					if ((userChoice.isPresent()) && (userChoice.get() == ButtonType.YES)) {
						userChoice = UIHandler.showConfirmation(stage, "Nothing we can do to make you stay?",
								"(ಥ﹏ಥ)(ಥ﹏ಥ) \"All we tried to do is make you laugh. Have you no soul?\"");
						if ((userChoice.isPresent()) && (userChoice.get() == ButtonType.YES))
							UIHandler.showSuccess(stage,
									"(ಥ _ʖಥ)ᕕ(ಥʖ̯ಥ) ᕗ \"Fine! Go then! Clearly you have no respect towards the effort of others!\"",
									false);

						try {
							Thread.sleep(3000);
						} catch (Exception ex) {
						}
					}
				} else
					event.consume();
			}
		};

		mainView.getAddProductButton().setOnAction(addProductButtonEventHandler);
		mainView.getRemoveLastProductButton().setOnAction(removeLastProductButtonEventHandler);
		mainView.getRemoveSelectedProductButton().setOnAction(removeSelectedProductButtonEventHandler);
		mainView.getRemoveAllProductsButton().setOnAction(removeAllProductsButtonEventHandler);
		mainView.getNotifyCustomersButton().setOnAction(notifyCustomersButtonEventHandler);
		stage.setOnCloseRequest(viewCloseEventHandler);
	}

// Methods
}