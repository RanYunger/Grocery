package ID316334473_ID302309513;

import java.io.File;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

import ID316334473_ID302309513.Controllers.MainController;
import ID316334473_ID302309513.Models.CustomerModel;
import ID316334473_ID302309513.Models.ProductModel;
import ID316334473_ID302309513.Views.MainView;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

// Helper class which contains all UI methods, plus extra helpful methods.
public class UIHandler {
	// Constants

	// Fields
	private static MainController mainController;
	private static MainView mainView;
	private static int sortOption; // 0 = ID Ascending, 1 = ID Descending, 2 = Insertion Order
	private static Comparator<ProductModel> comparator;

	private static boolean isAudioOn = true;
	private static MediaPlayer mediaPlayer;
	private static Media media;

	// Properties
	public static MainController getMainController() {
		return mainController;
	}

	public static void setMainController(MainController mainController) {
		UIHandler.mainController = mainController;
	}

	public static MainView getMainView() {
		return mainView;
	}

	public static void setMainView(MainView mainView) {
		UIHandler.mainView = mainView;
	}

	public static int getSortOption() {
		return sortOption;
	}

	public static void setSortOption(int sortOption) {
		UIHandler.sortOption = sortOption < 0 ? 0 : sortOption;
		setComparator(new Comparator<ProductModel>() {
			@Override
			public int compare(ProductModel o1, ProductModel o2) {
				return o1.compareTo(o2);
			}
		});
	}

	public static Comparator<ProductModel> getComparator() {
		return comparator;
	}

	private static void setComparator(Comparator<ProductModel> comparator) {
		UIHandler.comparator = comparator;
	}

	public static boolean isAudioOn() {
		return isAudioOn;
	}

	public static void toggleAudio() {
		if (mediaPlayer != null) {
			mediaPlayer.setMute(isAudioOn);
			isAudioOn = !isAudioOn;
		}
	}

	// Methods
	public static void addCursorEffectsToNode(Node node) {
		EventHandler<MouseEvent> mouseEnteredEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				node.getScene().setCursor(Cursor.HAND);
				// TODO: COMPLETE (increase Node size by 1%)
			}
		};

		EventHandler<MouseEvent> mouseExiteEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				node.getScene().setCursor(Cursor.DEFAULT);
				// TODO: COMPLETE (return node to its original size)
			}
		};

		node.setOnMouseEntered(mouseEnteredEventHandler);
		node.setOnMouseExited(mouseExiteEventHandler);
	}

	public static void playAudio(String audioFileName) {
		String path = String.format("%s\\bin\\%s", System.getProperty("user.dir"), audioFileName);

		// Validations
		if (mediaPlayer != null)
			mediaPlayer.stop();

		media = new Media(new File(path).toURI().toString());
		mediaPlayer = new MediaPlayer(media);

		if (isAudioOn)
			mediaPlayer.play();
	}

	private static Optional<ButtonType> showAlert(AlertType alertType, Window owner, String title, String header,
			String message, String audioFileName, boolean showAndWait) {
		Alert alert = new Alert(alertType);

		alert.initOwner(owner);
		alert.setX(alert.getOwner().getX() + alert.getOwner().getWidth() - alert.getWidth());
		alert.setY(alert.getOwner().getY() + alert.getOwner().getHeight() - alert.getHeight());

		alert.setTitle(title);
		alert.setHeaderText(header);

		if ((message != null) && (!message.isBlank())) {
			TextArea textArea = new TextArea(message);

			textArea.setEditable(false);
			alert.getDialogPane().setExpandableContent(new ScrollPane(textArea));
		}

		if (alertType == AlertType.CONFIRMATION) {
			alert.getDialogPane().setExpanded(true);
			alert.getButtonTypes().clear();
			alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
		}

		if (!audioFileName.isBlank())
			playAudio(audioFileName);

		if (showAndWait)
			return alert.showAndWait();

		alert.show();

		return null;
	}

	public static void showSuccess(Window owner, String message, boolean hasAudio) {
		showAlert(AlertType.INFORMATION, owner, "Success", message, "", hasAudio ? "Alright.wav" : "", hasAudio);
	}

	public static void showRemoveAllProductSuccess(Window owner, String header, String message) {
		showAlert(AlertType.INFORMATION, owner, "Success", header, message, "NOOOOOOOOOO.wav", true);
	}

	public static void showWarning(Window owner, String message, boolean hasAudio) {
		showAlert(AlertType.WARNING, owner, "Warning", message, "", hasAudio ? "UhOh.mp3" : "", hasAudio);
	}

	public static void showError(Window owner, String header, String message) {
		showAlert(AlertType.ERROR, owner, "Error", header, message, "Nope.mp3", true);
	}

	public static Optional<ButtonType> showConfirmation(Window owner, String header, String message) {
		return showAlert(AlertType.CONFIRMATION, owner, "Confirmation", header, message, "AreYouSure.wav", true);
	}

	public static void setGeneralFeatures(Stage stage) {
		setIcon(stage);
		stage.setTitle("Grocery");
		stage.setResizable(false);
	}

	public static void setIcon(Stage stage) {
		stage.getIcons().add(UIHandler.buildImage("Cart.png", 0, 0).getImage());
	}

	@SuppressWarnings("unchecked")
	public static TableView<Map.Entry<String, ProductModel>> buildProductsTableView() {
		TableView<Map.Entry<String, ProductModel>> tableView = new TableView<Map.Entry<String, ProductModel>>();
		TableColumn<Map.Entry<String, ProductModel>, String> productIDTableColumn, productNameTableColumn;
		TableColumn<Map.Entry<String, ProductModel>, Number> productCostPriceTableColumn,
				productSellingPriceTableColumn, productProfitTableColumn;

		productIDTableColumn = new TableColumn<Map.Entry<String, ProductModel>, String>("ID");
		productIDTableColumn.setCellValueFactory(cell -> (cell.getValue()).getValue().getObservableID());
		productIDTableColumn.setMinWidth(140);

		productNameTableColumn = new TableColumn<Map.Entry<String, ProductModel>, String>("Name");
		productNameTableColumn.setCellValueFactory(cell -> (cell.getValue()).getValue().getObservableName());
		productNameTableColumn.setMinWidth(140);

		productCostPriceTableColumn = new TableColumn<Map.Entry<String, ProductModel>, Number>("Cost Price");
		productCostPriceTableColumn.setCellValueFactory(cell -> (cell.getValue()).getValue().getObservableCostPrice());
		productCostPriceTableColumn.setMinWidth(140);

		productSellingPriceTableColumn = new TableColumn<Map.Entry<String, ProductModel>, Number>("Selling Price");
		productSellingPriceTableColumn
				.setCellValueFactory(cell -> (cell.getValue()).getValue().getObservableSellingPrice());
		productSellingPriceTableColumn.setMinWidth(140);

		productProfitTableColumn = new TableColumn<Map.Entry<String, ProductModel>, Number>("Profit");
		productProfitTableColumn.setCellValueFactory(cell -> (cell.getValue()).getValue().getObservableProfit());
		productProfitTableColumn.setMinWidth(140);

		tableView.getColumns().addAll(productIDTableColumn, productNameTableColumn, productCostPriceTableColumn,
				productSellingPriceTableColumn, productProfitTableColumn);
		for (TableColumn<?, ?> tableColumn : tableView.getColumns()) {
			tableColumn.setStyle("-fx-alignment: CENTER;");
			tableColumn.setEditable(false);
			tableColumn.setReorderable(false);
			tableColumn.setSortable(tableColumn.equals(productIDTableColumn));
			tableColumn.setResizable(false);
		}

		return tableView;
	}

	@SuppressWarnings("unchecked")
	public static TableView<CustomerModel> buildCustomersTableView() {
		TableView<CustomerModel> tableView = new TableView<CustomerModel>();
		TableColumn<CustomerModel, String> customerNameTableColumn, customerPhoneNumberTableColumn;
		TableColumn<CustomerModel, ImageView> interestedInUpdatesTableColumn;

		customerNameTableColumn = new TableColumn<CustomerModel, String>("Name");
		customerNameTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableName());
		customerNameTableColumn.setMinWidth(150);

		customerPhoneNumberTableColumn = new TableColumn<CustomerModel, String>("Phone Number");
		customerPhoneNumberTableColumn.setCellValueFactory(cell -> cell.getValue().getObservablePhoneNumber());
		customerPhoneNumberTableColumn.setMinWidth(150);

		interestedInUpdatesTableColumn = new TableColumn<CustomerModel, ImageView>("Interested In Updates");
		interestedInUpdatesTableColumn.setCellValueFactory(cell -> new SimpleObjectProperty<ImageView>(
				cell.getValue().isInterestedInUpdates() ? buildImage("V.png", 10, 10) : buildImage("X.png", 10, 10)));
		interestedInUpdatesTableColumn.setMinWidth(200);

		tableView.getColumns().addAll(customerNameTableColumn, customerPhoneNumberTableColumn,
				interestedInUpdatesTableColumn);
		for (TableColumn<?, ?> tableColumn : tableView.getColumns()) {
			tableColumn.setStyle("-fx-alignment: CENTER;");
			tableColumn.setEditable(false);
			tableColumn.setReorderable(false);
			tableColumn.setSortable(false);
			tableColumn.setResizable(false);
		}

		return tableView;
	}

	public static StackPane buildBackground(String backgroundImageName, Node node, double width, double height,
			double fontSize) {
		double imageHeight = 30;
		ImageView backgroundImage = buildImage(backgroundImageName, width, height),
				audioImageView = buildImage(isAudioOn ? "AudioOn.png" : "AudioOff.png", imageHeight, imageHeight);
		VBox topVBox = new VBox();
		Text creatorText = new Text("Ran & Natty's"), topText = new Text("Not Shufersal™"),
				bottomText = new Text("We're so getting sued");
		StackPane stackPane = new StackPane();

		topVBox.setAlignment(Pos.CENTER);
		creatorText.setFont(new Font(20));
		topText.setFont(new Font(fontSize));
		bottomText.setFont(new Font(fontSize));

		topVBox.getChildren().addAll(creatorText, topText);
		VBox.setMargin(creatorText, new Insets(10, 0, 0, 0));
		VBox.setMargin(topText, new Insets(0, 0, -10, 0));

		stackPane.getChildren().addAll(backgroundImage, topVBox, bottomText, node);
		stackPane.getChildren().addAll(audioImageView);
		StackPane.setMargin(audioImageView, new Insets(height, width * 0.95, height * 1.9, 10));
		StackPane.setMargin(topVBox, new Insets(height * 0.92, 0, height * 1.8, 0));
		StackPane.setMargin(bottomText, new Insets(height * 0.92, 0, height * 0.08, 0));

		return stackPane;
	}

	public static ImageView buildImage(String imageName, double height, double width) {
		Image image = new Image(imageName, height, width, false, false);

		return new ImageView(image);
	}
}