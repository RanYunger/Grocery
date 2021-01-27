package ID316334473_ID302309513;

import java.io.File;

import ID316334473_ID302309513.Controllers.MainController;
import ID316334473_ID302309513.Models.CustomerModel;
import ID316334473_ID302309513.Models.ProductModel;
import ID316334473_ID302309513.Views.MainView;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;

// Helper class which contains all UI methods, plus extra helpful methods.
public class UIHandler {
	// Constants

	// Fields
	private static MainController mainController;
	private static MainView mainView;

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
		node.setOnMouseEntered(entered -> node.getScene().setCursor(Cursor.HAND));
		node.setOnMouseExited(entered -> node.getScene().setCursor(Cursor.DEFAULT));
	}

	public static void addAudioToImageView(ImageView imageView, String audioFileName) {
		imageView.setOnMouseClicked(click -> playAudio(audioFileName));
		addCursorEffectsToNode(imageView);
	}

	@SuppressWarnings("static-access")
	public static void playAudio(String audioFileName) {
		String path = String.format("%s\\bin\\%s", System.getProperty("user.dir"), audioFileName);

		// Validations
		if (mediaPlayer != null)
			mediaPlayer.stop();

		media = new Media(new File(path).toURI().toString());
		mediaPlayer = new MediaPlayer(media);

		mediaPlayer.setCycleCount(audioFileName.equals("WeWillRockYou.mp3") ? mediaPlayer.INDEFINITE : 0);

		if (isAudioOn)
			mediaPlayer.play();
	}

	private static void showAlert(AlertType alertType, Window owner, String title, String header, String message,
			String audioFileName, boolean showAndWait) {
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

		if (!audioFileName.isBlank())
			playAudio(audioFileName);

		if (showAndWait)
			alert.showAndWait();
		else
			alert.show();
	}

	public static void showSuccess(Window owner, String message, boolean hasAudio) {
		showAlert(AlertType.INFORMATION, owner, "Success", message, "", hasAudio ? "Yayyy.mp3" : "", hasAudio);
	}

	public static void showWarning(Window owner, String message, boolean hasAudio) {
		showAlert(AlertType.WARNING, owner, "Warning", message, "", hasAudio ? "UhOh.mp3" : "", hasAudio);
	}

	public static void showError(Window owner, String message) {
		showAlert(AlertType.ERROR, owner, "Error", message, "", "Awww.mp3", true);
	}

	public static void showError(Window owner, String header, String message) {
		showAlert(AlertType.ERROR, owner, "Error", header, message, "Awww.mp3", true);
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
	public static TableView<ProductModel> buildProductsTableView() {
		TableView<ProductModel> tableView = new TableView<ProductModel>();
		TableColumn<ProductModel, String> productIDTableColumn, productNameTableColumn;
		TableColumn<ProductModel, Number> productCostPriceTableColumn, productSellingPriceTableColumn,
				productProfitTableColumn;

		productIDTableColumn = new TableColumn<ProductModel, String>("ID");
		productIDTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableID());
		productIDTableColumn.setMinWidth(150);

		productNameTableColumn = new TableColumn<ProductModel, String>("Name");
		productNameTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableName());
		productNameTableColumn.setMinWidth(150);

		productCostPriceTableColumn = new TableColumn<ProductModel, Number>("Cost Price");
		productCostPriceTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableCostPrice());
		productCostPriceTableColumn.setMinWidth(150);

		productSellingPriceTableColumn = new TableColumn<ProductModel, Number>("Selling Price");
		productSellingPriceTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableSellingPrice());
		productSellingPriceTableColumn.setMinWidth(150);

		productProfitTableColumn = new TableColumn<ProductModel, Number>("Profit");
		productProfitTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableProfit());
		productProfitTableColumn.setMinWidth(150);

		tableView.getColumns().addAll(productIDTableColumn, productNameTableColumn, productCostPriceTableColumn,
				productSellingPriceTableColumn, productProfitTableColumn);
		for (TableColumn<?, ?> tableColumn : tableView.getColumns()) {
			tableColumn.setStyle("-fx-alignment: CENTER;");
			tableColumn.setEditable(false);
			tableColumn.setReorderable(false);
			tableColumn.setSortable(false);
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
				audioImageView = buildImage(isAudioOn ? "AudioOn.png" : "AudioOff.png", imageHeight, imageHeight),
				homeImageView = buildImage("Home.png", imageHeight, imageHeight);
		VBox topVBox = new VBox();
		Label creatorLabel = new Label("Ran & Natty's"), topLabel = new Label("Grocery™"),
				bottomLabel = new Label("The only place to purchase shit");
		StackPane stackPane = new StackPane();

		topVBox.setAlignment(Pos.CENTER);
		creatorLabel.setFont(new Font(20));
		topLabel.setFont(new Font(fontSize));
		bottomLabel.setFont(new Font(fontSize));

		topVBox.getChildren().addAll(creatorLabel, topLabel);
		VBox.setMargin(creatorLabel, new Insets(10, 0, 0, 0));
		VBox.setMargin(topLabel, new Insets(0, 0, -10, 0));

		stackPane.getChildren().addAll(backgroundImage, topVBox, bottomLabel, node);
		stackPane.getChildren().addAll(audioImageView, homeImageView);
		StackPane.setMargin(audioImageView, new Insets(height, width * 0.95, height * 1.9, 10));
		StackPane.setMargin(homeImageView, new Insets(height, 10, height * 1.9, width * 0.95));
		StackPane.setMargin(topVBox, new Insets(height * 0.92, 0, height * 1.8, 0));
		StackPane.setMargin(bottomLabel, new Insets(height * 0.92, 0, height * 0.08, 0));

		return stackPane;
	}

	public static ImageView buildImage(String imageName, double height, double width) {
		Image image = new Image(imageName, height, width, false, false);

		return new ImageView(image);
	}
}