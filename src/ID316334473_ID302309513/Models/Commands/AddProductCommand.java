package ID316334473_ID302309513.Models.Commands;

import ID316334473_ID302309513.FileHandler;
import ID316334473_ID302309513.UIHandler;
import ID316334473_ID302309513.Models.MementoModel;
import ID316334473_ID302309513.Models.ProductModel;
import ID316334473_ID302309513.Views.MainView;

public class AddProductCommand implements iCommand {
	// Fields
	private ProductModel addedProduct;

	// Properties (Getters and Setters)
	private void SetAddedProduct(ProductModel productToAdd) {
		this.addedProduct = productToAdd;
	}

	// Constructors
	public AddProductCommand(ProductModel productToAdd) {
		SetAddedProduct(productToAdd);
	}

	// Methods
	@Override
	public void execute() {
		MainView mainView = UIHandler.getMainView();

		FileHandler.writeProductToFile(addedProduct, UIHandler.getSortOption());
		mainView.getMementoStack().push(new MementoModel(addedProduct));

		mainView.readAllProducts();
	}
}