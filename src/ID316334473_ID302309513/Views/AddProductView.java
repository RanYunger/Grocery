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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AddProductView extends WindowView {
	// Constants

	// Fields
	private GridPane gridPane;
	private VBox vBox;
	private HBox productIDHBox, productNameHBox, costPriceHBox, sellingPriceHBox, customerNameHBox, phoneNumberHBox,
			interestedHBox;
	private Label headerLabel, productIDLabel, productNameLabel, costPriceLabel, sellingPriceLabel, customerNameLabel,
			phoneNumberLabel, interestedLabel;
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
		double sceneWidth = 750, sceneHeight = 500, backgroundFontSize = 30, viewFontSize = 20;

		gridPane = new GridPane();
		vBox = new VBox();
		productIDHBox = new HBox();
		productNameHBox = new HBox();
		costPriceHBox = new HBox();
		sellingPriceHBox = new HBox();
		customerNameHBox = new HBox();
		phoneNumberHBox = new HBox();
		interestedHBox = new HBox();
		headerLabel = new Label("New Product");
		productIDLabel = new Label("ID:");
		productNameLabel = new Label("Name:");
		costPriceLabel = new Label("Cost:");
		sellingPriceLabel = new Label("Selling:");
		customerNameLabel = new Label("Customer:");
		phoneNumberLabel = new Label("Phone:");
		interestedLabel = new Label("Notify:");
		productIDTextField = new TextField();
		productNameTextField = new TextField();
		customerNameTextField = new TextField();
		phoneNumberTextField = new TextField();
		costPriceSpinner = new Spinner<Integer>(0, 100, 0, 1);
		sellingPriceSpinner = new Spinner<Integer>(0, 100, 0, 1);
		interestedCheckBox = new CheckBox();
		submitButton = new Button("Submit");
		cartImageView = UIHandler.buildImage("Groceries.png", sceneHeight * 0.7, sceneWidth / 2);

		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(0).setPercentHeight(100);

		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(0).setPercentWidth(50);
		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(1).setPercentWidth(50);

		vBox.setAlignment(Pos.CENTER);
		headerLabel.setFont(new Font(backgroundFontSize));
		headerLabel.setTextFill(Color.WHITE);
		productIDLabel.setFont(new Font(viewFontSize));
		productIDLabel.setTextFill(Color.WHITE);
		productNameLabel.setFont(new Font(viewFontSize));
		productNameLabel.setTextFill(Color.WHITE);
		costPriceLabel.setFont(new Font(viewFontSize));
		costPriceLabel.setTextFill(Color.WHITE);
		sellingPriceLabel.setFont(new Font(viewFontSize));
		sellingPriceLabel.setTextFill(Color.WHITE);
		customerNameLabel.setFont(new Font(viewFontSize));
		customerNameLabel.setTextFill(Color.WHITE);
		phoneNumberLabel.setFont(new Font(viewFontSize));
		phoneNumberLabel.setTextFill(Color.WHITE);
		interestedLabel.setFont(new Font(viewFontSize));
		interestedLabel.setTextFill(Color.WHITE);
		productIDTextField.setMinWidth(200);
		productIDTextField.setTooltip(new Tooltip("Format: 4 digits"));
		productNameTextField.setMinWidth(200);
		productNameTextField.setTooltip(new Tooltip("Format: One capitalized word (i.e. Milk)"));
		customerNameTextField.setMinWidth(200);
		customerNameTextField.setTooltip(new Tooltip("Format: firstname surename (capitalized) (i.e. John Doe)"));
		phoneNumberTextField.setMinWidth(200);
		phoneNumberTextField.setTooltip(new Tooltip("Format: 10 digits (i.e. 0529182736"));
		costPriceSpinner.setMinWidth(200);
		costPriceSpinner.editorProperty().get().setAlignment(Pos.CENTER);
		sellingPriceSpinner.setMinWidth(200);
		sellingPriceSpinner.editorProperty().get().setAlignment(Pos.CENTER);
		interestedCheckBox.setSelected(true);
		submitButton.setFont(new Font(viewFontSize));

		productIDHBox.getChildren().addAll(productIDLabel, productIDTextField);
		HBox.setMargin(productIDLabel, new Insets(0, 0, 0, 10));
		HBox.setMargin(productIDTextField, new Insets(0, 10, 0, 110));

		productNameHBox.getChildren().addAll(productNameLabel, productNameTextField);
		HBox.setMargin(productNameLabel, new Insets(0, 0, 0, 10));
		HBox.setMargin(productNameTextField, new Insets(0, 10, 0, 80));

		costPriceHBox.getChildren().addAll(costPriceLabel, costPriceSpinner);
		HBox.setMargin(costPriceLabel, new Insets(0, 0, 0, 10));
		HBox.setMargin(costPriceSpinner, new Insets(0, 10, 0, 90));

		sellingPriceHBox.getChildren().addAll(sellingPriceLabel, sellingPriceSpinner);
		HBox.setMargin(sellingPriceLabel, new Insets(0, 0, 0, 10));
		HBox.setMargin(sellingPriceSpinner, new Insets(0, 10, 0, 70));

		customerNameHBox.getChildren().addAll(customerNameLabel, customerNameTextField);
		HBox.setMargin(customerNameLabel, new Insets(0, 0, 0, 10));
		HBox.setMargin(customerNameTextField, new Insets(0, 10, 0, 42));

		phoneNumberHBox.getChildren().addAll(phoneNumberLabel, phoneNumberTextField);
		HBox.setMargin(phoneNumberLabel, new Insets(0, 0, 0, 10));
		HBox.setMargin(phoneNumberTextField, new Insets(0, 10, 0, 70));

		interestedHBox.getChildren().addAll(interestedLabel, interestedCheckBox);
		HBox.setMargin(interestedLabel, new Insets(0, 0, 0, 10));
		HBox.setMargin(interestedCheckBox, new Insets(5, 10, 0, 160));

		vBox.getChildren().addAll(headerLabel, productIDHBox, productNameHBox, costPriceHBox, sellingPriceHBox,
				customerNameHBox, phoneNumberHBox, interestedHBox, submitButton);
		VBox.setMargin(headerLabel, new Insets(10, 0, 5, 0));
		VBox.setMargin(productIDHBox, new Insets(5, 0, 5, 0));
		VBox.setMargin(productNameHBox, new Insets(5, 0, 5, 0));
		VBox.setMargin(costPriceHBox, new Insets(5, 0, 5, 0));
		VBox.setMargin(sellingPriceHBox, new Insets(5, 0, 5, 0));
		VBox.setMargin(customerNameHBox, new Insets(5, 0, 5, 0));
		VBox.setMargin(phoneNumberHBox, new Insets(5, 0, 5, 0));
		VBox.setMargin(interestedHBox, new Insets(5, 0, 5, 0));
		VBox.setMargin(submitButton, new Insets(5, 0, 10, 0));

		gridPane.add(vBox, 0, 0);
		gridPane.add(cartImageView, 1, 0);

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
