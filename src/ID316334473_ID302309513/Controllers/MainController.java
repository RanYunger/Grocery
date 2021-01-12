package ID316334473_ID302309513.Controllers;

import ID316334473_ID302309513.UIHandler;
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
		super(view);

		MainView mainView = getMainView();
		EventHandler<MouseEvent> playersImageViewEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
//				UIHandler.playAudio("Marching.mp3");
//
//				PlayersView playersView = new PlayersView();
//				PlayersController playersController = new PlayersController(playersView);
//
//				mainView.close();
//				playersController.addEventHandlersToGeneralButtons();
			}
		};
		EventHandler<MouseEvent> tournamentsImageViewEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
//				UIHandler.playAudio("Whistle.mp3");
//
//				TournamentsView tournamentsView = new TournamentsView();
//				TournamentsController tournamentsController = new TournamentsController(tournamentsView);
//
//				mainView.close();
//				tournamentsController.addEventHandlersToGeneralButtons();
			}
		};
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

		mainView.getPlayersImageView().setOnMouseClicked(playersImageViewEventHandler);
		mainView.getTournamentsImageView().setOnMouseClicked(tournamentsImageViewEventHandler);
		mainView.getTrophiesImageView().setOnMouseClicked(trophiesImageViewEventHandler);
	}

	// Methods
}
