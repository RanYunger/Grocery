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
	public void start(Stage primaryStage) throws Exception {
//		FileIterator fileIterator = new FileIterator();
//		CustomerModel ranCust = new CustomerModel("Ran", "052-8934721", true),
//				nattyCust = new CustomerModel("Natty", "052-1894325", false);
//		ProductModel prod1 = new ProductModel("1", "milk", 3, 4, ranCust),
//				prod2 = new ProductModel("2", "eggs", 10, 12, null),
//				prod3 = new ProductModel("3", "yogurt", 50, 55, nattyCust);
//
//		fileIterator.writeProductToFile(prod1);
//		fileIterator.writeProductToFile(prod2);
//		fileIterator.writeProductToFile(prod3);
//
//		Iterator<ProductModel> it = fileIterator.iterator();
//		while (it.hasNext())
//			System.out.println(it.next().toString());

		if (FileIterator.isFileReady()) {
			MainView mainView = new MainView(primaryStage);
			MainController mainController = new MainController(mainView);
		} else {
			EntryView entryView = new EntryView(primaryStage);
			EntryController entryController = new EntryController(entryView);
		}
	}

	public static void main(String[] args) {
		launch();
	}
}
