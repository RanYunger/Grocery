package ID316334473_ID302309513;

import ID316334473_ID302309513.Controllers.EntryController;
import ID316334473_ID302309513.Controllers.MainController;
import ID316334473_ID302309513.Views.EntryView;
import ID316334473_ID302309513.Views.MainView;
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
		if (FileHandler.isFileReady()) {
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
