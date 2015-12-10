package entity;

import input.InputUtility;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import render.Renderable;

public class Hero extends Living implements Renderable {
	
	public Hero(int x) {
		super(x);
		// TODO Auto-generated constructor stub
	}

	public void update() {
		if (InputUtility.getKeyPressed(KeyEvent.VK_LEFT)) {
			x -= 5;
			y =  (-1)*(int)(Math.sqrt(Math.pow(320, 2) - Math.pow(x - 330, 2))) + 460;
		} else if (InputUtility.getKeyPressed(KeyEvent.VK_RIGHT)) {
			x += 5;
			y =  (-1)*(int)(Math.sqrt(Math.pow(320, 2) - Math.pow(x - 330, 2))) + 460;		
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillOval(x, y, 20, 20);
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

}
