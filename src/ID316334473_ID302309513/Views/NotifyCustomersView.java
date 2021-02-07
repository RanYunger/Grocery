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
	private ImageView pingImageView;

	// Properties (Getters and Setters)
	public TextArea getLogTextArea() {
		return logTextArea;
	}

	public Button getFinishButton() {
		return finishButton;
	}

	public SenderThreadModel getSenderThread() {
		return SenderThreadModel.getInstance();
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
		finishButton = new Button("Finish");
		pingImageView = UIHandler.buildImage("Ping.png", sceneHeight * 0.7, sceneWidth / 2);

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
		VBox.setMargin(logTextArea, new Insets(20, 20, 5, 20));
		VBox.setMargin(finishButton, new Insets(5, 20, 10, 20));

		gridPane.add(vBox, 0, 0);
		gridPane.add(pingImageView, 1, 0);
		
		stage.setScene(
				new Scene(UIHandler.buildBackground("Grocery.jpg", gridPane, sceneWidth, sceneHeight, backgroundFontSize),
						sceneWidth, sceneHeight));
		UIHandler.setGeneralFeatures(stage);

		stage.show();
	}

	@Override
	protected void addEffects() {
		super.addEffects();
	}

	@Override
	protected Node asNode() {
		return (Node) gridPane;
	}

	public void notifyCustomer(String customerName) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		String message = String.format("[%s] %s recieved a notification.", LocalTime.now().format(formatter),
				customerName);

		logTextArea.appendText(message + "\n");
		UIHandler.playAudio("Whatsapp.wav");
	}

	public void startSenderThread() {
		SenderThreadModel thread = SenderThreadModel.getInstance();

		thread.setNotifyCustomersView(this);
		thread.start();
	}
}