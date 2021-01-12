package ID316334473_ID302309513.Views;

import javafx.scene.Node;
import javafx.stage.Stage;

// An abstract class containing integral components for derivative views
public abstract class View {
	// Constants

	// Fields
	protected Stage stage;

	// Properties (Getters and Setters)
	public Stage getStage() {
		return stage;
	}

	protected final void setStage(Stage stage) {
		this.stage = stage;
	}

	// Constructors
	public View() {
		setStage(new Stage());
	}

	public View(Stage stage) {
		setStage(stage);
	}

	// Methods
	protected abstract void buildScene();
	
	protected abstract void addEffects();
	
	protected abstract Node asNode();

	public void close() {
		stage.close();
	}
}
