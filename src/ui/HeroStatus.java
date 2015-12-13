package ui;

import java.awt.Color;
import java.awt.Graphics2D;

import render.Renderable;
import render.Resource;

public class HeroStatus implements Renderable {

	private int score, health, mana, level;
	private boolean isDead;

	public HeroStatus() {
		score = 0;
		health = 100;
		mana = 100;
		level = 1;
		isDead = false;
	}

	public boolean isDead() {
		return isDead;
	}

	public void addScore(int score) {
		this.score += score;
	}

	public void regenMana(int rate) {
		if (this.mana + rate <= 100)
			this.mana += rate;
		else
			this.mana = 100;
	}

	public boolean lossMana(int mana) {
		if (this.mana - mana >= 0) {
			this.mana -= mana;
			return true;
		}
		return false;
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
	
	public void regenHealth(int rate) {
		if (this.health + rate <= 100)
			this.health += rate;
		else
			this.health = 100;
	}

	public boolean lossHealth(int health) {
		if (this.health - health >= 0) {
			this.health -= health;
			return true;
		}
		this.isDead = true;
		return false;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(Resource.statusBG, null, 0,0);
		g.setColor(Color.WHITE);
		g.drawString("SCORE: " + score, 100, 80);
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 1;
	}

}
