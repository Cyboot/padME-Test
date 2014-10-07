package de.timweb.padme;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.Controller.Type;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;
import net.java.games.input.Rumbler;
import de.timweb.padme.util.Init;

/**
 * This class shows how to use the event queue system in JInput. It will show
 * how to get the controllers, how to get the event queue for a controller, and
 * how to read and process events from the queue.
 * 
 * @author Endolf
 */
public class ReadAllEvents {
	public ReadAllEvents() {
		Init.setLibraryPath();
		Init.useDirectInputPlugin();
	}

	private void run() {
		for (Controller controller : ControllerEnvironment.getDefaultEnvironment().getControllers()) {
			System.out.println(controller);
		}

		while (true) {
			step();

			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
		}
	}

	private void step() {
		/* Get the available controllers */
		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		if (controllers.length == 0) {
			System.out.println("Found no controllers.");
			System.exit(0);
		}

		for (Controller controller : controllers) {
			if (controller.getType() != Type.GAMEPAD && controller.getType() != Type.STICK)
				continue;
			controller.poll();

			/* Get the controllers event queue */
			EventQueue queue = controller.getEventQueue();
			Event event = new Event();

			while (queue.getNextEvent(event)) {
				StringBuffer buffer = new StringBuffer("'" + controller.getName());
				buffer.append("' @ ");
				buffer.append(event.getNanos() / 1000000).append("> ");
				Component comp = event.getComponent();
				buffer.append(comp.getName()).append("(" + comp.getIdentifier())
						.append(") changed to ");
				float value = event.getValue();

				Rumbler[] rumblers = controller.getRumblers();

				System.out.println("Rumblers: " + rumblers.length);

				if (comp.isAnalog()) {
					if (Math.abs(value) > 0.1)
						buffer.append(value);
				} else {
					buffer.append(value);
					// if (value == 1.0f) {
					// buffer.append("On");
					// } else {
					// buffer.append("Off");
					// }
				}

				System.out.println(buffer.toString());
			}
		}
	}

	public static void main(String[] args) {
		new ReadAllEvents().run();
	}
}