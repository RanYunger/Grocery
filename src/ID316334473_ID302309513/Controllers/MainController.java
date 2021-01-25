package ID316334473_ID302309513.Controllers;

import ID316334473_ID302309513.Views.MainView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MainController extends WindowController {
	// Constants

	// Fields

	// Properties (Getters and Setters)
	public MainView getMainView() {
		return (MainView) getView();
	}

	// Constructors
	public MainController(MainView view) {
		this(view, 0);
	}
	
	public MainController(MainView view, int sortOption) {
		super(view);

		MainView mainView = getMainView();

		EventHandler<MouseEvent> trophiesImageViewEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
//				UIHandler.playAudio("WeAreTheChampions.mp3");
//
//				TrophiesView trophiesView = new TrophiesView();
//				TrophiesController trophiesController = new TrophiesController(trophiesView);
//
//				mainView.close();
//				trophiesController.addEventHandlersToGeneralButtons();
			}
		};

//		mainView.getTournamentsImageView().setOnMouseClicked(tournamentsImageViewEventHandler);
//		mainView.getTrophiesImageView().setOnMouseClicked(trophiesImageViewEventHandler);
	}

	// Methods
}