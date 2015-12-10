package input;

public class InputUtility {
	
	private static boolean[] keyPressed = new boolean[256];

	public static boolean getKeyPressed(int key) {
		if (key < 0 || key > 255)
			return false;
		return keyPressed[key];
	}

	public static void setKeyPressed(int key, boolean keyPressed) {
		if (key < 0 || key > 255)
			return;
		InputUtility.keyPressed[key] = keyPressed;
	}


}
