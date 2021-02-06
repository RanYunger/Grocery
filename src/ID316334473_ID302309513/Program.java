package ID316334473_ID302309513;

import ID316334473_ID302309513.Controllers.EntryController;
import ID316334473_ID302309513.Controllers.MainController;
import ID316334473_ID302309513.Views.EntryView;
import ID316334473_ID302309513.Views.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application {
	// Methods
	@Override
	public void start(Stage primaryStage) {
		if (FileHandler.isFileReady()) {
			MainView mainView = new MainView();
			MainController mainController = new MainController(mainView);

			mainController.addEventHandlersToGeneralButtons();
			UIHandler.setMainView(mainView);
			UIHandler.setMainController(mainController);
			UIHandler.setSortOption(2); // Insertion Order
		} else {
			EntryView entryView = new EntryView(primaryStage);
			EntryController entryController = new EntryController(entryView);
		}
	}

	public static void main(String[] args) {
		launch();
	}
}

// FOR DEBUG PURPOSES (WILL BE DELETED)
//CustomerModel ranCust = new CustomerModel("Ran", "0528934721", true),
//		nattyCust = new CustomerModel("Natty", "0521894325", false);
//ProductModel wP1 = new ProductModel("1", "Milk", 3, 4, ranCust),
//		wP2 = new ProductModel("2", "Eggs", 10, 12, ranCust),
//		wP3 = new ProductModel("3", "Yogurt", 50, 55, nattyCust),
//		wP4 = new ProductModel("4", "Chocolate", 13, 15, null),
//		wP5 = new ProductModel("4", "Meat", 13, 15, null),
//		wP6 = new ProductModel("4", "Vegetables", 13, 15, nattyCust);
//
//fileHandler.writeProductToFile(wP1);
//fileHandler.writeProductToFile(wP2);
//fileHandler.writeProductToFile(wP3);
//fileHandler.writeProductToFile(wP4);
//fileHandler.writeProductToFile(wP5);
//fileHandler.writeProductToFile(wP6);
//
//ProductModel currProduct = null;
//Iterator<ProductModel> it = fileHandler.iterator();
//
//while (it.hasNext()) {
//	currProduct = it.next();
//	System.out.println(currProduct.toString());
//}
//
//System.out.println(String.format("p1 length = %d, p2 length = %d, p3 length = %d, p4 length = %d", wP1.getLengthInBytes(),
//		wP2.getLengthInBytes(), wP3.getLengthInBytes(), wP4.getLengthInBytes()));
//System.out.println("Offset of product 2 = 0x" + fileHandler.seekProduct(wP2.getTextualID()));
//it.remove();
//
//it = fileHandler.iterator();
//while (it.hasNext()) {
//	currProduct = it.next();
//	System.out.println(currProduct.toString());
//}