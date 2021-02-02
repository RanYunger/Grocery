package ID316334473_ID302309513;

import java.util.Iterator;

import ID316334473_ID302309513.Models.CustomerModel;
import ID316334473_ID302309513.Models.ProductModel;
import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application {
	// Methods
	@Override
	public void start(Stage primaryStage) throws Exception {
		FileHandler fileHandler = new FileHandler();
		CustomerModel ranCust = new CustomerModel("Ran", "052-8934721", true),
				nattyCust = new CustomerModel("Natty", "052-1894325", false);
		ProductModel wP1 = new ProductModel("1", "milk", 3, 4, ranCust),
				wP2 = new ProductModel("2", "eggs", 10, 12, null),
				wP3 = new ProductModel("3", "yogurt", 50, 55, nattyCust);

		fileHandler.writeProductToFile(wP1);
		fileHandler.writeProductToFile(wP2);
		fileHandler.writeProductToFile(wP3);

		ProductModel currProduct = null;
		Iterator<ProductModel> it = fileHandler.iterator();

		while (it.hasNext()) {
			currProduct = it.next();
			System.out.println(currProduct.toString());
		}

		System.out.println(String.format("p1 length = %d, p2 length = %d, p3 length = %d", wP1.getLengthInBytes(), wP2.getLengthInBytes(),
				wP3.getLengthInBytes()));
		System.out.println("Offset of product 2 = " + fileHandler.getOffsetByProductID(wP2.getTextualID()));
		it.remove();

		it = fileHandler.iterator();
		while (it.hasNext()) {
			currProduct = it.next();
			System.out.println(currProduct.toString());
		}

//		if (FileIterator.isFileReady()) {
//			MainView mainView = new MainView(primaryStage);
//			MainController mainController = new MainController(mainView);
//		} else {
//			EntryView entryView = new EntryView(primaryStage);
//			EntryController entryController = new EntryController(entryView);
//		}
	}

	public static void main(String[] args) {
		launch();
	}
}
