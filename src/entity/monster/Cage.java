package entity.monster;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import entity.Hero;
import entity.Land;

public class Cage {

	private static final Cage instance = new Cage();
	private List<Monster> cage = new CopyOnWriteArrayList<Monster>();

	public static Cage getInstance() {
		return instance;
	}

	public void add(String n, Land l, Hero h) {
		if (n.equalsIgnoreCase("golem"))
			cage.add(new Golem(800, 265, l, h));
		else if (n.equalsIgnoreCase("yeti"))
			cage.add(new Yeti(800, 243, l, h));
		else
			return;
	}
	public synchronized void remove(int i){
		cage.remove(i);
	}

	public void updateAll() {
		for (Monster m : cage)
			m.update();
	}

	public List<Monster> getCage() {
		return cage;
	}
	
}
