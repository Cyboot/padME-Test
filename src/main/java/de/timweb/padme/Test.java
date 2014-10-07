package de.timweb.padme;

import net.java.games.input.Controller;
import net.java.games.input.Controller.Type;
import net.java.games.input.ControllerEnvironment;
import de.timweb.padme.util.Init;

public class Test {

	public static void main(String[] args) {
		Init.setLibraryPath();
		Init.useDirectInputPlugin();

		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for (Controller controller : controllers) {
			if (controller.getType() == Type.UNKNOWN)
				continue;

			System.out.println(controller + "\t\t" + controller.getType());
		}
	}
}
