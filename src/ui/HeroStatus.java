package ui;

import java.awt.Color;
import java.awt.Graphics2D;

import render.Renderable;
import render.Resource;

public class HeroStatus implements Renderable {

	private int score, health, mana, level,maxHp,maxMp;
	private double xHp,xMp;
	private boolean isDead;

	public HeroStatus() {
		score = 0;
		level = 1;
		isDead = false;
	}
	
	public void update(){
		xHp= (health*1.0)/(maxHp*1.0);
		xMp= (mana*1.0)/(maxMp*1.0);
	}
	
	public void setMaxHp(int x){
		maxHp = x;
	}
	
	public void setCurrentHp(int x){
		health = x;
	}
	
	public void setMaxMp(int x){
		maxMp = x;
	}
	
	public void setCurrentMp(int x){
		mana = x;
	}

	public boolean isDead() {
		return isDead;
	}

	public void addScore(int score) {
		this.score += score;
	}

	public void changeLevel() {
		level++;
	}

	public int getLevel() {
		return level;
	}
	
	public void resetLevel() {
		level = 1;
	}

	@Override
	public void draw(Graphics2D g) {
		//g.drawImage(Resource.statusBG, null, 0,0);
		g.setColor(Color.WHITE);
		g.setFont(Resource.standardFont);
		g.drawString("SCORE: " + score, 100, 80);
		g.setColor(Color.BLACK);
		g.fillRect(90,20,120,15);
		g.setColor(Color.RED);
		g.fillRect(93, 23, (int)(xHp*114), 9);
		g.setColor(Color.BLACK);
		g.fillRect(90,40,120,15);
		g.setColor(Color.BLUE);
		g.fillRect(93, 43, (int)(xMp*114), 9);
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public int getZ() {
		return 2;
	}

}
