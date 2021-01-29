package ID316334473_ID302309513;

import ID316334473_ID302309513.Models.CustomerModel;
import ID316334473_ID302309513.Models.ProductModel;
import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application {
	// Constants

	// Fields

	// Properties (Getters and Setters)

	// Constructors

	// Methods
	@Override
	public void start(Stage primaryStage) throws Exception {
		CustomerModel ranCust = new CustomerModel("Ran", "052-8934721", true),
				nattyCust = new CustomerModel("Natty", "052-1894325", false);
		ProductModel prod1 = new ProductModel("1", "milk", 3, 4, ranCust),
				prod2 = new ProductModel("2", "eggs", 10, 12, null),
				prod3 = new ProductModel("3", "yogurt", 50, 55, nattyCust);

		// if (FileHandler.isFileReady()) {
		FileHandler.writeProductToFile(prod1);
		FileHandler.writeProductToFile(prod2);
		FileHandler.writeProductToFile(prod3);
//		}

		ProductModel p1, p2, p3;
		p1 = FileHandler.readProductFromFile();
		p2 = FileHandler.readProductFromFile();
		p3 = FileHandler.readProductFromFile();

		System.out.println(p1.toString());
		System.out.println(p2.toString());
		System.out.println(p3.toString());

//		if (FileHandler.isFileReady()) {
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
