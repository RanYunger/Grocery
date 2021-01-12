package ID316334473_ID302309513;

import java.util.ArrayList;

import ID316334473_ID302309513.Controllers.MainController;
import ID316334473_ID302309513.Views.MainView;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.stage.Stage;

public class Program extends Application {
	// Constants

	// Fields

	// Properties (Getters and Setters)

	// Constructors

	// Methods
	@Override
	public void start(Stage primaryStage) throws Exception {
		MainView mainView = new MainView(primaryStage);
//		MainController mainController = new MainController(mainView);

//		UIHandler.playAudio("WeWillRockYou.mp3");
//		mainController.addEventHandlersToGeneralButtons();
//		UIHandler.setMainView(mainView);
//		UIHandler.setMainController(mainController);
	}

	public static void main(String[] args) {
		launch();
	}
}
