package ID316334473_ID302309513.Views;

import ID316334473_ID302309513.UIHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainView extends WindowView {
	// Constants

	// Fields
	private HBox optionsHBox;
	private VBox playersVBox, tournamentsVBox, hallOfFameVBox;
	private ImageView playersImageView, tournamentsImageView, trophiesImageView;
	private Label playersLabel, tournamentsLabel, hallOfFameLabel;

	// Properties (Getters and Setters)
	public ImageView getPlayersImageView() {
		return playersImageView;
	}

	public ImageView getTournamentsImageView() {
		return tournamentsImageView;
	}

	public ImageView getTrophiesImageView() {
		return trophiesImageView;
	}

	// Constructors
	public MainView() {
		this(new Stage());
	}
	public MainView(Stage primaryStage) {
		super(primaryStage);

		buildScene();
		//addEffects();
	}

	// Methods
	@Override
	protected void buildScene() {
		double sceneWidth = 1500, sceneHeight = 700, backgroundFontSize = 50, viewFontSize = 30;

//		optionsHBox = new HBox();
//		playersVBox = new VBox();
//		tournamentsVBox = new VBox();
//		hallOfFameVBox = new VBox();
//		playersImageView = UIHandler.buildImage("Players.png", 350, 300);
//		tournamentsImageView = UIHandler.buildImage("Tournaments.png", 350, 300);
//		trophiesImageView = UIHandler.buildImage("Trophies.png", 350, 300);
//		playersLabel = new Label("Players");
//		tournamentsLabel = new Label("Tournaments");
//		hallOfFameLabel = new Label("Hall of Fame");
//
//		optionsHBox.setAlignment(Pos.CENTER);
//		playersVBox.setAlignment(Pos.CENTER_LEFT);
//		tournamentsVBox.setAlignment(Pos.CENTER);
//		hallOfFameVBox.setAlignment(Pos.CENTER_RIGHT);
//		playersLabel.setFont(new Font(viewFontSize));
//		playersLabel.setTextFill(Color.WHITE);
//		tournamentsLabel.setFont(new Font(viewFontSize));
//		tournamentsLabel.setTextFill(Color.WHITE);
//		hallOfFameLabel.setFont(new Font(viewFontSize));
//		hallOfFameLabel.setTextFill(Color.WHITE);
//
//		playersVBox.getChildren().addAll(playersImageView, playersLabel);
//		VBox.setMargin(playersImageView, new Insets(0, 0, 10, 0));
//		VBox.setMargin(playersLabel, new Insets(10, 0, 0, 125));
//
//		tournamentsVBox.getChildren().addAll(tournamentsImageView, tournamentsLabel);
//		VBox.setMargin(tournamentsImageView, new Insets(0, 0, 10, 0));
//		VBox.setMargin(tournamentsLabel, new Insets(10, 0, 0, 0));
//
//		hallOfFameVBox.getChildren().addAll(trophiesImageView, hallOfFameLabel);
//		VBox.setMargin(trophiesImageView, new Insets(0, 0, 10, 0));
//		VBox.setMargin(hallOfFameLabel, new Insets(10, 95, 0, 0));
//
//		optionsHBox.getChildren().addAll(playersVBox, tournamentsVBox, hallOfFameVBox);
//		HBox.setMargin(playersVBox, new Insets(0, 50, 0, 0));
//		HBox.setMargin(tournamentsVBox, new Insets(0, 50, 0, 50));
//		HBox.setMargin(hallOfFameVBox, new Insets(0, 0, 0, 50));

		stage.setScene(new Scene(
				UIHandler.buildBackground("Grocery.jpg", new StackPane(), sceneWidth, sceneHeight, backgroundFontSize),
				sceneWidth, sceneHeight));
		UIHandler.setGeneralFeatures(stage);

		stage.show();
	}

	@Override
	protected void addEffects() {
		super.addEffects();

		UIHandler.addCursorEffectsToNode(playersImageView);
		UIHandler.addCursorEffectsToNode(tournamentsImageView);
		UIHandler.addCursorEffectsToNode(trophiesImageView);
	}

	@Override
	protected Node asNode() {
		return (Node) optionsHBox;
	}
}