package ID316334473_ID302309513.Controllers;

import ID316334473_ID302309513.UIHandler;
import ID316334473_ID302309513.ValidPatterns;
import ID316334473_ID302309513.Models.CustomerModel;
import ID316334473_ID302309513.Models.ProductModel;
import ID316334473_ID302309513.Models.Commands.AddProductCommand;
import ID316334473_ID302309513.Views.AddProductView;
import ID316334473_ID302309513.Views.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddProductController extends WindowController {
	// Constants

	// Fields

	// Properties (Getters and Setters)
	public AddProductView getAddProductView() {
		return (AddProductView) getView();
	}

	// Constructors
	public AddProductController(View view) {
		super(view);

		AddProductView addProductView = getAddProductView();

		EventHandler<MouseEvent> cartImageViewEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				UIHandler.playAudio("Cha-Ching.wav");
			}
		};
		EventHandler<ActionEvent> submitButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				TextField productIDTextField = addProductView.getIDTextField(),
						productNameTextField = addProductView.getProductNameTextField(),
						customerNameTextField = addProductView.getCustomerNameTextField(),
						phoneNumberTextField = addProductView.getPhoneNumberTextField();
				Spinner<Integer> costPriceSpinner = addProductView.getCostPriceSpinner(),
						sellingPriceSpinner = addProductView.getSellingPriceSpinner();
				CheckBox interestedCheckBox = addProductView.getIntestedCheckBox();
				String productID = productIDTextField.getText(), productName = productNameTextField.getText(),
						customerName = customerNameTextField.getText(), phoneNumber = phoneNumberTextField.getText();
				int costPrice = costPriceSpinner.getValue(), sellingPrice = sellingPriceSpinner.getValue();
				CustomerModel customer = null;

				// Validations
				if (!productID.matches(ValidPatterns.PRODUCT_ID.getPattern())) {
					UIHandler.showError(addProductView.getStage(), "Invalid Product ID!");
					return;
				}
				if (!productName.matches(ValidPatterns.PRODUCT_NAME.getPattern())) {
					UIHandler.showError(addProductView.getStage(), "Invalid Product Name!");
					return;
				}
				if ((!customerName.isBlank()) && (!phoneNumber.isBlank())) {
					if (!customerName.matches(ValidPatterns.CUSTOMER_FULL_NAME.getPattern())) {
						UIHandler.showError(addProductView.getStage(), "Invalid Customer Name!");
						return;
					}
					if (!phoneNumber.matches(ValidPatterns.CUSTOMER_PHONE_NUMBER.getPattern())) {
						UIHandler.showError(addProductView.getStage(), "Invalid Phone Number!");
						return;
					}
					customer = new CustomerModel(customerName, phoneNumber, interestedCheckBox.isSelected());
				}

				new AddProductCommand(new ProductModel(productID, productName, costPrice, sellingPrice, customer)).execute();
				addProductView.close();
				UIHandler.showSuccess(UIHandler.getMainView().getStage(), "A new product was added successfully!",
						true);
			}
		};

		addProductView.getCartImageView().setOnMouseClicked(cartImageViewEventHandler);
		addProductView.getSubmitButton().setOnAction(submitButtonEventHandler);
	}

	// Methods
}
