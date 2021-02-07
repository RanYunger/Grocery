package ID316334473_ID302309513.Models.Commands;

import java.util.Iterator;
import java.util.Stack;

import ID316334473_ID302309513.FileHandler;
import ID316334473_ID302309513.UIHandler;
import ID316334473_ID302309513.Models.MementoModel;
import ID316334473_ID302309513.Models.ProductModel;
import ID316334473_ID302309513.Views.MainView;

public class RemoveProductCommand implements iCommand {
	// Fields
	private ProductModel removedProduct;

	// Constructors
	public RemoveProductCommand(ProductModel productToRemove) {
		this.removedProduct = productToRemove;
	}

	// Methods
	@Override
	public void execute() {
		FileHandler fileHandler = new FileHandler();
		MainView mainView = UIHandler.getMainView();
		Iterator<ProductModel> iterator = fileHandler.iterator();
		String productID = removedProduct.getTextualID();
		Stack<MementoModel> mementoStack = mainView.getMementoStack(), tempStack = new Stack<MementoModel>();

		// Removes the product from the file
		FileHandler.findProduct(removedProduct.getTextualID());
		iterator.remove();

		// Removes the product from the suitable observable collection
		if (UIHandler.getSortOption() == 2) // Insertion Order
			mainView.getAllProductsUnsorted().remove(productID);
		else
			mainView.getAllProductsSorted().remove(productID);

		// Removes the product from the memento stack
		while (!(mementoStack.isEmpty()) && (mementoStack.peek().getLastAddedProduct() != removedProduct))
			tempStack.push(mementoStack.pop());
		mementoStack.clear();
		while (!tempStack.isEmpty())
			mementoStack.push(tempStack.pop());
				
		mainView.readAllProducts();	
		
		UIHandler.showSuccess(mainView.getStage(),
				String.format("Product #%s removed successfuly!", removedProduct.getTextualID()),
				true);
	}
}