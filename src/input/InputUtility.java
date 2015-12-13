package input;

public class InputUtility {

	private static boolean[] keyPressed = new boolean[256];
	private static String spell = "";

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

	public static String getSpell() {
		return spell;
	}

	public static void setSpell(String s) {
		spell += s;
	}

	public static void clearSpell() {
		spell = "";
	}
}
