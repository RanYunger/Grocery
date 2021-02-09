package ID316334473_ID302309513.Views;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import ID316334473_ID302309513.UIHandler;
import ID316334473_ID302309513.Models.SenderThreadModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class NotifyCustomersView extends WindowView {
	// Fields
	private GridPane gridPane;
	private VBox vBox;
	private TextArea logTextArea;
	private Button finishButton;
	private ImageView bellImageView;

	// Properties (Getters and Setters)
	public TextArea getLogTextArea() {
		return logTextArea;
	}

	public Button getFinishButton() {
		return finishButton;
	}

	public ImageView getBellImageView() {
		return bellImageView;
	}

	// Constructors
	public NotifyCustomersView() {
		super();

		buildScene();
		addEffects();

		startSenderThread();
	}

	// Methods
	@Override
	protected void buildScene() {
		double sceneWidth = 750, sceneHeight = 500, backgroundFontSize = 30, viewFontSize = 20;

		gridPane = new GridPane();
		vBox = new VBox();
		logTextArea = new TextArea();
		finishButton = new Button("Finish", UIHandler.buildImage("Stop.png", 30, 30));
		bellImageView = UIHandler.buildImage("Bell.png", 300, 300);

		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(0).setPercentHeight(100);

		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(0).setPercentWidth(50);
		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(1).setPercentWidth(50);

		vBox.setAlignment(Pos.CENTER);
		logTextArea.setEditable(false);
		logTextArea.setMinHeight(300);
		logTextArea.setOpacity(0.8);
		finishButton.setFont(new Font(viewFontSize));

		vBox.getChildren().addAll(logTextArea, finishButton);
		VBox.setMargin(logTextArea, new Insets(10, 30, 0, 50));
		VBox.setMargin(finishButton, new Insets(10, 50, 0, 50));

		gridPane.add(vBox, 0, 0);
		gridPane.add(bellImageView, 1, 0);

		stage.setScene(new Scene(
				UIHandler.buildBackground("Grocery.jpg", gridPane, sceneWidth, sceneHeight, backgroundFontSize),
				sceneWidth, sceneHeight));
		UIHandler.setGeneralFeatures(stage);

		stage.show();
	}

	@Override
	protected void addEffects() {
		super.addEffects();

		UIHandler.addCursorEffectsToNode(finishButton);
		UIHandler.addCursorEffectsToNode(bellImageView);
	}

	@Override
	protected Node asNode() {
		return (Node) gridPane;
	}

	public void addToLog(String message) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

		logTextArea.appendText(String.format("[%s] %s\n", LocalTime.now().format(formatter), message));
		UIHandler.playAudio("Whatsapp.wav");
	}

	public void startSenderThread() {
		SenderThreadModel thread = SenderThreadModel.getInstance();

		thread.setNotifyCustomersView(this);
		thread.start();
	}
}