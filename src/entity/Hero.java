package entity;

import input.InputUtility;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import render.Renderable;
import render.Resource;

public class Hero extends Moving implements Renderable {

	private int frameCount=0,count=0,direction=1;
	
	public Hero(double a,int r) {
		super(a,r);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update() {
		if(count==10){
			count=0;
			frameCount++;
			if(frameCount>3)
				frameCount%=4;
		}
		count++;
		if (InputUtility.getKeyPressed(KeyEvent.VK_LEFT)) {
			direction=2;
			angle -= Math.toRadians(2);
			/*angle += 0.02;
			this.x = 350 + (int) ((Math.sin(angle) * radius));
			this.y = 500 + (int) ((Math.cos(angle) * radius));*/

		} else if (InputUtility.getKeyPressed(KeyEvent.VK_RIGHT)) {
			direction=1;
			angle += Math.toRadians(2);/*
			angle -= 0.02;
			this.x = 350 + (int) ((Math.sin(angle) * radius));
			this.y = 500 + (int) ((Math.cos(angle) * radius));*/
		}
	}

	@Override
	public void draw(Graphics2D g) {
		AffineTransform backup = g.getTransform();
		AffineTransform trans = new AffineTransform();
		trans.rotate(angle, 350, 500);
		g.transform(trans);
		if(direction==2){
		g.drawImage(Resource.hero.getSubimage(Resource.hero.getWidth()/4*frameCount, 0, Resource.hero.getWidth()/4, Resource.hero.getHeight() ), null, x, y);
		} else if(direction==1){
			g.drawImage(Resource.hero_f.getSubimage(Resource.hero_f.getWidth()/4*(3-frameCount), 0, Resource.hero_f.getWidth()/4, Resource.hero.getHeight() ), null, x, y);
		}
		g.setTransform(backup);
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
