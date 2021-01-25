package ID316334473_ID302309513.Views;

import ID316334473_ID302309513.UIHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EntryView extends WindowView {
	// Constants

	// Fields
	private ImageView cashRegistryImageView;
	private VBox vBox;
	private Label instructionsLabel;
	private HBox hBox;
	private RadioButton ascendingIDRadioButton, descendingIDRadioButton, insertionRadioButton;
	private Button enterButton;

	// Properties (Getters and Setters)
	public ImageView getCashRegistryImageView() {
		return cashRegistryImageView;
	}

	public Button getEnterButton() {
		return enterButton;
	}

	public RadioButton[] getSortOptionRadioButtons() {
		return new RadioButton[] { ascendingIDRadioButton, descendingIDRadioButton, insertionRadioButton };
	}

	// Constructors
	public EntryView() {
		this(new Stage());
	}

	public EntryView(Stage primaryStage) {
		super(primaryStage);

		buildScene();
		addEffects();
	}

	// Methods
	@Override
	protected void buildScene() {
		double sceneWidth = 400, sceneHeight = 250, viewFontSize = 15;

		cashRegistryImageView = UIHandler.buildImage("CashRegistry.png", 250, 200);
		vBox = new VBox();
		instructionsLabel = new Label("Sorting Method");
		hBox = new HBox();
		ascendingIDRadioButton = new RadioButton("ID Ascending");
		descendingIDRadioButton = new RadioButton("ID Descending");
		insertionRadioButton = new RadioButton("Insertion Order");
		enterButton = new Button("Enter");

		hBox.setAlignment(Pos.CENTER);
		vBox.setAlignment(Pos.CENTER);
		instructionsLabel.setFont(new Font(viewFontSize));
		instructionsLabel.setTextFill(Color.BLACK);
		ascendingIDRadioButton.setSelected(true);

		vBox.getChildren().addAll(instructionsLabel, ascendingIDRadioButton, descendingIDRadioButton,
				insertionRadioButton, enterButton);
		VBox.setMargin(instructionsLabel, new Insets(0, 0, 10, 0));
		VBox.setMargin(ascendingIDRadioButton, new Insets(10, 0, 10, 0));
		VBox.setMargin(descendingIDRadioButton, new Insets(10, 0, 10, 0));
		VBox.setMargin(insertionRadioButton, new Insets(10, 0, 10, 0));
		VBox.setMargin(enterButton, new Insets(10, 0, 0, 0));

		hBox.getChildren().addAll(cashRegistryImageView, vBox);
		HBox.setMargin(cashRegistryImageView, new Insets(0, 10, 0, 0));
		HBox.setMargin(vBox, new Insets(0, 0, 10, 0));

		stage.setTitle("Welcome to our system!");
		UIHandler.setIcon(stage);
		stage.setScene(new Scene(hBox, sceneWidth, sceneHeight));
		stage.setResizable(false);
		addEffects();

		stage.show();
	}

	@Override
	protected void addEffects() {
		super.addEffects();

		UIHandler.addCursorEffectsToNode(cashRegistryImageView);
	}

	@Override
	protected Node asNode() {
		return (Node) vBox;
	}
}
