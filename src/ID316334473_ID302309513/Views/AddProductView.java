package ID316334473_ID302309513.Views;

import ID316334473_ID302309513.UIHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class AddProductView extends WindowView {
	// Constants

	// Fields
	private GridPane gridPane;
	private VBox vBox;
	private HBox productIDHBox, productNameHBox, costPriceHBox, sellingPriceHBox, customerNameHBox, phoneNumberHBox;
	private Label productIDLabel, productNameLabel, costPriceLabel, sellingPriceLabel, customerNameLabel,
			phoneNumberLabel;
	private TextField productIDTextField, productNameTextField, customerNameTextField, phoneNumberTextField;
	private Spinner<Integer> costPriceSpinner, sellingPriceSpinner;
	private CheckBox interestedCheckBox;
	private Button submitButton;
	private ImageView cartImageView;

	// Properties (Getters and Setters)
	public TextField getIDTextField() {
		return productIDTextField;
	}

	public TextField getProductNameTextField() {
		return productNameTextField;
	}

	public Spinner<Integer> getCostPriceSpinner() {
		return costPriceSpinner;
	}

	public Spinner<Integer> getSellingPriceSpinner() {
		return sellingPriceSpinner;
	}

	public TextField getCustomerNameTextField() {
		return customerNameTextField;
	}

	public TextField getPhoneNumberTextField() {
		return phoneNumberTextField;
	}

	public CheckBox getIntestedCheckBox() {
		return interestedCheckBox;
	}

	public Button getSubmitButton() {
		return submitButton;
	}

	public ImageView getCartImageView() {
		return cartImageView;
	}

	// Constructors
	public AddProductView() {
		super();

		buildScene();
		addEffects();
	}

	// Methods
	@Override
	protected void buildScene() {
		double sceneWidth = 700, sceneHeight = 500, backgroundFontSize = 30;

		// TODO: align labels + textboxes
		gridPane = new GridPane();
		vBox = new VBox();
		productIDHBox = new HBox();
		productNameHBox = new HBox();
		costPriceHBox = new HBox();
		sellingPriceHBox = new HBox();
		customerNameHBox = new HBox();
		phoneNumberHBox = new HBox();
		productIDLabel = new Label("ID:");
		productNameLabel = new Label("Product Name:");
		costPriceLabel = new Label("Cost Price:");
		sellingPriceLabel = new Label("Selling Price:");
		customerNameLabel = new Label("Customer Name:");
		phoneNumberLabel = new Label("Phone Number:");
		productIDTextField = new TextField();
		productNameTextField = new TextField();
		customerNameTextField = new TextField();
		phoneNumberTextField = new TextField();
		costPriceSpinner = new Spinner<Integer>(0, 100, 0, 1);
		sellingPriceSpinner = new Spinner<Integer>(0, 100, 0, 1);
		interestedCheckBox = new CheckBox("Interested in updates");
		submitButton = new Button("Submit");
		cartImageView = UIHandler.buildImage("Cart.png", 250, 250);

		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(0).setPercentHeight(100);

		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(0).setPercentWidth(50);
		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(1).setPercentWidth(50);

		vBox.setAlignment(Pos.CENTER);
		productIDHBox.setAlignment(Pos.CENTER);
		productNameHBox.setAlignment(Pos.CENTER);
		costPriceHBox.setAlignment(Pos.CENTER);
		sellingPriceHBox.setAlignment(Pos.CENTER);
		customerNameHBox.setAlignment(Pos.CENTER);
		phoneNumberHBox.setAlignment(Pos.CENTER);

		productIDTextField.setMinWidth(100);
		productIDTextField.setTooltip(new Tooltip("Format: 4 digits"));
		productNameTextField.setMinWidth(100);
		productNameTextField.setTooltip(new Tooltip("Format: One capitalized word (i.e. Milk)"));
		customerNameTextField.setMinWidth(100);
		customerNameTextField.setTooltip(new Tooltip("Format: firstname surename (capitalized) (i.e. John Doe)"));
		phoneNumberTextField.setMinWidth(100);
		phoneNumberTextField.setTooltip(new Tooltip("Format: 10 digits (i.e. 0529182736"));
		costPriceSpinner.setMinWidth(100);
		sellingPriceSpinner.setMinWidth(100);

		productIDHBox.getChildren().addAll(productIDLabel, productIDTextField);
		HBox.setMargin(productIDLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(productIDTextField, new Insets(0, 10, 0, 10));

		productNameHBox.getChildren().addAll(productNameLabel, productNameTextField);
		HBox.setMargin(productNameLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(productNameTextField, new Insets(0, 10, 0, 10));

		costPriceHBox.getChildren().addAll(costPriceLabel, costPriceSpinner);
		HBox.setMargin(costPriceLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(costPriceSpinner, new Insets(0, 10, 0, 10));

		sellingPriceHBox.getChildren().addAll(sellingPriceLabel, sellingPriceSpinner);
		HBox.setMargin(sellingPriceLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(sellingPriceSpinner, new Insets(0, 10, 0, 10));

		customerNameHBox.getChildren().addAll(customerNameLabel, customerNameTextField);
		HBox.setMargin(customerNameLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(customerNameTextField, new Insets(0, 10, 0, 10));

		phoneNumberHBox.getChildren().addAll(phoneNumberLabel, phoneNumberTextField);
		HBox.setMargin(phoneNumberHBox, new Insets(0, 10, 0, 10));
		HBox.setMargin(phoneNumberTextField, new Insets(10, 10, 0, 10));

		vBox.getChildren().addAll(productIDHBox, productNameHBox, costPriceHBox, sellingPriceHBox, customerNameHBox,
				phoneNumberHBox, interestedCheckBox, submitButton);
		VBox.setMargin(productIDHBox, new Insets(0, 0, 10, 0));
		VBox.setMargin(productNameHBox, new Insets(10, 0, 0, 0));
		VBox.setMargin(costPriceHBox, new Insets(10, 0, 0, 0));
		VBox.setMargin(sellingPriceHBox, new Insets(10, 0, 0, 0));
		VBox.setMargin(customerNameHBox, new Insets(10, 0, 0, 0));
		VBox.setMargin(phoneNumberHBox, new Insets(10, 0, 0, 0));
		VBox.setMargin(interestedCheckBox, new Insets(10, 0, 0, 0));
		VBox.setMargin(submitButton, new Insets(10, 0, 0, 0));

		gridPane.add(vBox, 1, 0);
		gridPane.add(cartImageView, 0, 0);
		GridPane.setMargin(vBox, new Insets(0, 50, 0, 0));
		GridPane.setMargin(cartImageView, new Insets(0, 0, 0, 50));

		stage.setScene(new Scene(
				UIHandler.buildBackground("Grocery.jpg", gridPane, sceneWidth, sceneHeight, backgroundFontSize),
				sceneWidth, sceneHeight));
		UIHandler.setGeneralFeatures(stage);

		stage.show();

	}

	@Override
	protected void addEffects() {
		super.addEffects();
	}

	@Override
	protected Node asNode() {
		return (Node) gridPane;
	}
}
