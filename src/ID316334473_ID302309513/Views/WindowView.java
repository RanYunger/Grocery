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
	protected ImageView audioImageView;

	// Properties (Getters and Setters)
	public ImageView getAudioImageView() {
		return audioImageView;
	}

	protected void setAudioImageView(ImageView audioImageView) {
		this.audioImageView = audioImageView;
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
					UIHandler.addCursorEffectsToNode(imageViewNode);
				}
			}
		}
	}
}
