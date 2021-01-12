package ID316334473_ID302309513.Views;

import ID316334473_ID302309513.UIHandler;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

//An abstract class containing integral components for derivative views
public abstract class WindowView extends View {
	// Constants

	// Fields
	protected ImageView audioImageView, homeImageView, infoImageView;

	// Properties (Getters and Setters)
	public ImageView getAudioImageView() {
		return audioImageView;
	}

	protected void setAudioImageView(ImageView audioImageView) {
		this.audioImageView = audioImageView;
	}

	public ImageView getHomeImageView() {
		return homeImageView;
	}

	protected void setHomeImageView(ImageView homeImageView) {
		this.homeImageView = homeImageView;
	}

	public ImageView getInfoImageView() {
		return infoImageView;
	}

	protected void setInfoImageView(ImageView infoImageView) {
		this.infoImageView = infoImageView;
	}

	// Constructors
	public WindowView() {
		super();
	}

	public WindowView(Stage stage) {
		super(stage);
	}

	// Methods
	protected abstract void buildScene();

	protected void addEffects() {
		Scene scene = stage.getScene();
		ObservableList<Node> rootNodes = scene.getRoot().getChildrenUnmodifiable();
		ImageView imageViewNode;
		Node currentNode;

		for (int i = 0; i < rootNodes.size(); i++) {
			currentNode = rootNodes.get(i);
			if (currentNode instanceof ImageView) {
				imageViewNode = (ImageView) currentNode;
				if (imageViewNode.getImage().getUrl().contains("Audio")) {
					setAudioImageView(imageViewNode);
					UIHandler.addAudioToImageView(audioImageView, "Sparkle.mp3");
				} else if (imageViewNode.getImage().getUrl().contains("Home")) {
					setHomeImageView(imageViewNode);
					UIHandler.addAudioToImageView(imageViewNode, "Sparkle.mp3");
				} else if (imageViewNode.getImage().getUrl().contains("Info")) {
					setInfoImageView(imageViewNode);
					UIHandler.addAudioToImageView(imageViewNode, "Sparkle.mp3");
				}
			}
		}
	}
}
