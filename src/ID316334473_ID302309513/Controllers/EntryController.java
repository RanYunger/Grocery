package ID316334473_ID302309513.Controllers;

import ID316334473_ID302309513.UIHandler;
import ID316334473_ID302309513.Views.EntryView;
import ID316334473_ID302309513.Views.MainView;
import ID316334473_ID302309513.Views.View;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;

public class EntryController extends WindowController {
	// Constants

	// Fields

	// Properties (Getters and Setters)
	public EntryView getEntryView() {
		return (EntryView) getView();
	}

	// Constructors
	public EntryController(View view) {
		super(view);

		EntryView entryView = getEntryView();
		RadioButton[] sortOptionRadioButtons = entryView.getSortOptionRadioButtons();

		EventHandler<MouseEvent> cashRegistryImageViewEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				UIHandler.playAudio("Cha-Ching.wav");
			}
		};
		ChangeListener<Boolean> ascendingIDRadioButtonChangeListener = new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					UIHandler.playAudio("WhistleAscending.wav");

					sortOptionRadioButtons[1].setSelected(false);
					sortOptionRadioButtons[2].setSelected(false);
				}
			}
		};
		ChangeListener<Boolean> descendingIDRadioButtonChangeListener = new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					UIHandler.playAudio("WhistleDescending.wav");

					sortOptionRadioButtons[0].setSelected(false);
					sortOptionRadioButtons[2].setSelected(false);
				}
			}
		};
		ChangeListener<Boolean> insertionOrderRadioButtonChangeListener = new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					UIHandler.playAudio("Waheep.wav");

					sortOptionRadioButtons[0].setSelected(false);
					sortOptionRadioButtons[1].setSelected(false);
				}
			}
		};
		EventHandler<MouseEvent> enterButtonEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				int sortOption = -1; // 0 = ID Ascending, 1 = ID Descending, 2 = Insertion Order

				for (int i = 0; i < sortOptionRadioButtons.length; i++)
					if (sortOptionRadioButtons[i].isSelected()) {
						sortOption = i;
						break;
					}
				if (sortOption < 0) {
					UIHandler.showError(entryView.getStage(), "Choose sorting method");
					return;
				}

				MainView mainView = new MainView();
				MainController mainController = new MainController(mainView);

				UIHandler.setMainView(mainView);
				UIHandler.setMainController(mainController);
				UIHandler.setSortOption(sortOption);

				entryView.close();
				mainController.addEventHandlersToGeneralButtons();				
			}
		};

		entryView.getCashRegistryImageView().setOnMouseClicked(cashRegistryImageViewEventHandler);
		sortOptionRadioButtons[0].selectedProperty().addListener(ascendingIDRadioButtonChangeListener);
		sortOptionRadioButtons[1].selectedProperty().addListener(descendingIDRadioButtonChangeListener);
		sortOptionRadioButtons[2].selectedProperty().addListener(insertionOrderRadioButtonChangeListener);
		entryView.getEnterButton().setOnMouseClicked(enterButtonEventHandler);
	}

	// Methods
}
