package de.timweb.padme;


import net.java.games.input.Controller;
import net.java.games.input.Controller.Type;
import net.java.games.input.ControllerEnvironment;
import de.timweb.padme.util.Init;

public class RumbleTest {
	public static void main(String[] args) throws InterruptedException {
		Init.setLibraryPath();
		Init.useDirectInputPlugin();

		ControllerEnvironment cEnvironment = ControllerEnvironment.getDefaultEnvironment();
		Controller[] cControllers = cEnvironment.getControllers();

		System.out.println("JInput has found: " + cControllers.length + " Controllers");

		for (int i = 0; i < cControllers.length; i++) {
			if (cControllers[i].getType() != Type.UNKNOWN) {
				System.out.println("n Controller Name: " + cControllers[i].getName());
				System.out.println("    Type: " + (cControllers[i].getType()));
				System.out.println("    Rumble: "
						+ ((cControllers[i].getRumblers().length > 0) ? "Yes" : "No"));

				for (int o = 0; o < cControllers[i].getRumblers().length; o++) {
					System.out.println("        Rumbling Axis: "
							+ cControllers[i].getRumblers()[o].getAxisName());
					cControllers[i].getRumblers()[o].rumble(0.5f);
					Thread.sleep(1000);
				}
			}
		}
	}
}