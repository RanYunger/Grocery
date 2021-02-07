package ID316334473_ID302309513.Models.Commands;

import java.util.Map;

import ID316334473_ID302309513.UIHandler;
import ID316334473_ID302309513.Models.ProductModel;
import javafx.scene.control.TableView;

public class RemoveAllProductsCommand implements iCommand {
	// Fields
	private RemoveProductCommand removeCurrentProductCommand;

	// Constructors
	public RemoveAllProductsCommand() {
		removeCurrentProductCommand = null;
	}

	// Methods
	@Override
	public void execute() {
		TableView<Map.Entry<String, ProductModel>> productsTableView = UIHandler.getMainView().getProductsTableView();

		for (Map.Entry<String, ProductModel> productEntry : productsTableView.getItems()) {
			removeCurrentProductCommand = new RemoveProductCommand(productEntry.getValue());	
			removeCurrentProductCommand.execute();
		}
	}
}