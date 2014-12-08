package org.bip.spec.pubsub;

import java.util.HashMap;

import org.bip.annotations.ComponentType;
import org.bip.annotations.Guard;
import org.bip.annotations.Port;
import org.bip.annotations.Ports;
import org.bip.annotations.Transition;
import org.bip.api.BIPActor;
import org.bip.api.PortType;

@Ports({ @Port(name = "getCommand", type = PortType.enforceable) })
@ComponentType(initial = "0", name = "org.bip.spec.CommandHandler")
public class CommandHandler {
	
	private BIPActor topicManager;
	private Command currentCommand;
	
	public CommandHandler(BIPActor topicManager) {
		this.topicManager = topicManager;
	}

	@Transition(name = "getCommand", source = "0", target = "0")
	public void getCommand(Command command) {
		this.currentCommand = command;
	}

	@Guard(name = "hasCommandToHandle")
	public boolean hasCommandToHandle() {
		return currentCommand != null;
	}
	
	@Transition(name = "", source = "0", target = "0", guard = "hasCommandToHandle")
	public void internalHandleCommand() {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("value", currentCommand);
		topicManager.inform("execute", data);
		currentCommand = null;
		System.out.println("Handling command internally");
	}
	
	// // transition from initial state to work state.
	// public void getTopicManager(BIPActor topicManager) {
	// this.topicManager = topicManager;
	// }
		
}
