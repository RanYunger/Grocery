package ID316334473_ID302309513.Models.Commands;

import java.util.Stack;

import ID316334473_ID302309513.UIHandler;
import ID316334473_ID302309513.Models.MementoModel;

public class UndoCommand implements iCommand {
	// Fields

	// Constructors
	public UndoCommand() {
	}

	// Methods
	@Override
	public void execute() {
		Stack<MementoModel> mementoStack = UIHandler.getMainView().getMementoStack();
		MementoModel memento = null;

		if (!mementoStack.isEmpty()) {
			memento = mementoStack.pop();
			new RemoveProductCommand(memento.getLastAddedProduct()).execute();
		}
	}
}