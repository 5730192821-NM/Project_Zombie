package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import render.Renderable;
import render.RenderableHolder;
import render.Resource;

public class Word implements Renderable {

	private String[] words;
	private int[] x;
	private boolean[] press;
	private boolean isVisible = false;

	public Word(String s) {
		words = new String[s.length()];
		x = new int[s.length()];
		press = new boolean[s.length()];
		press[0] = true;

		for (int i = 0; i < s.length(); i++) {
			words[i] = s.substring(i, i + 1);
		}

		if (s.length() % 2 == 1) {
			x[((s.length() + 1) / 2) - 1] = 0;
			for (int i = 1; i <= (s.length() - 1) / 2; i++) {
				x[((s.length() + 1) / 2) + i - 1] = i * 100;
				x[((s.length() + 1) / 2) - i - 1] = i * -100;
			}
		} else {
			x[(s.length() / 2) - 1] = -50;
			x[(s.length() / 2)] = 50;
			for (int i = 1; i <= (s.length() / 2) - 1; i++) {
				x[(s.length() / 2) - 1 - i] = (-50) + i * (-100);
				x[(s.length() / 2) + i] = (50) + i * 100;
			}

		}
		RenderableHolder.getInstance().add(this);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setFont(Resource.standardFont);
		for (int i = 0; i < words.length; i++) {
			if (press[i])
				g.setColor(Color.RED);
			else
				g.setColor(Color.BLACK);
			g.drawString(words[i], 400 + x[i], 200);
		}
	}
	
	public void setVisible(boolean b){
		this.isVisible = b;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isVisible;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean isEqual(Word w){
		if (this.words.length != w.words.length)
			return false;
		return true;
	}

	//Don't used yet
	public void cast(String s) {
		boolean in = false;
		for (int i = 0; i < words.length; i++) {
			if (!press[i] && words[i].equalsIgnoreCase(s)) {
				in = true;
				press[i] = true;
			}
		}
		if (!in) {
			// Disappear
			return;
		}
	}

}
