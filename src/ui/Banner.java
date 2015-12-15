package ui;

import java.awt.Color;
import java.awt.Graphics2D;

import render.Renderable;
import render.RenderableHolder;
import render.Resource;

public class Banner implements Renderable,Runnable {

	public boolean isVisible = false;

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(Resource.banner, null, 200, 120);
		g.setFont(Resource.wordFont);
		g.setColor(new Color(51, 25, 0));
		g.drawString("PAUSE", 330, 180);
		g.setFont(Resource.skillFont);
		g.drawString("SKILL", 350, 300);
		g.setFont(Resource.pauseFont);
		g.drawString("press 'Q' : exit to MainMenu ", 260, 220);
		g.drawString("press 'esc' : unpause ", 290, 250);
		g.drawString("-SingleTarget-", 240, 330);
		g.drawString("F : fire", 280, 360);
		g.drawString("I : ice", 280, 380);
		g.drawString("-MultiTarget-", 430, 330);
		g.drawString("M : meteor", 445, 360);
		g.drawString("P : poison", 450, 380);
		g.drawString("S : spike", 450, 400);

	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isVisible;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			if(!isVisible){
				synchronized(this){
					try {
						RenderableHolder.getInstance().remove(this);
						this.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

}
