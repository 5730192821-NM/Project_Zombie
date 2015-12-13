package entity;

import input.InputUtility;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import entity.skill.*;
import render.*;

public class Hero extends Moving implements Renderable {

	private int frameCount = 0, count = 0, countA=0,frameCountA=0, direction = 1, temp, countSpell = 0;
	private int gravity = 1, velocityY;
	private boolean isJumped;
	private boolean isMid;
	private boolean isSkill,isCasting;
	private Land land;
	private Skill[] skills = new Skill[5];
	private static Word [] words = new Word[5];
	
	// direction 1 : RIGHT direction 2 : LEFT

	public Hero(int x, int y, Land land) {
		super(x, y);
		this.land = land;
		temp = y;
		isJumped = false;
		velocityY = 0;
		isMid = false;
		isSkill = false;
		isCasting=false;

		skills[0] = new IceSkill(x, y, direction);
		skills[1] = new FireSkill(x, y, direction);
		skills[2] = new MeteorSkill(x, y, direction);
		skills[3] = new PoisonSkill(x, y, direction);
		skills[4] = new SpikeSkill(x, y, direction);
		
		words[0] = new Word("ICE");
		words[1] = new Word("FIRE");
		words[2] = new Word("METEOR");
		words[3] = new Word("POISON");
		words[4] = new Word("SPIKE");
	}

	@Override
	public void update() {
		
		// Spell Animation
		if(isCasting){
			if (countA == 8) {
				countA = 0;
				frameCountA++;
				if (frameCountA >= 10){
					isCasting=false;
					frameCountA=0;
					countA=0;
				}
			}
			countA++;
		} else{
			// Idle Animation Controller
			if (count == 10) {
				count = 0;
				frameCount++;
				frameCount %= 6;
			}
			count++;
		}
		
		// Spell Time Casting
		if(countSpell == 200){
			countSpell = 0;
			//InputUtility.clearSpell();
		}
		countSpell++;
		
		// Clear by yourself
		if (InputUtility.getKeyPressed(KeyEvent.VK_ENTER)) {
			countSpell = 0;
			InputUtility.clearSpell();
		}

		// Jump
		if (InputUtility.getKeyPressed(KeyEvent.VK_UP)) {
			if (!isJumped) {
				isJumped = true;
				velocityY = -17;
			}
		}
		if (isJumped) {
			velocityY += gravity;
			y += velocityY;
			if (y > temp) {
				y = temp;
				isJumped = false;
				velocityY = 0;
			}
		}

		// Moving
		if (InputUtility.getKeyPressed(KeyEvent.VK_LEFT)) {
			direction = 2;
			if (x + 20 > 0)
				x -= 5;
			isMid = false;
		}
		if (InputUtility.getKeyPressed(KeyEvent.VK_RIGHT)) {
			direction = 1;
			if (x < 400 || land.isEnd()) {
				if (x < 720)
					x += 5;
			} else {
				isMid = true;
				land.setX(5);
			}
		}

		// Casting Skill
		if (isSkill) {
			if (x >= 400 && isMid) {
				skills[0].setX(5);
				skills[1].setX(5);
				skills[2].setX(5);
				skills[3].setX(5);
				skills[4].setX(5);
			}
			skills[0].update();
			skills[1].update();
			skills[2].update();
			skills[3].update();
			skills[4].update();

			if (!skills[0].isPlaying() && !skills[1].isPlaying()
					&& !skills[2].isPlaying() && !skills[3].isPlaying()
					&& !skills[4].isPlaying()) {
				isSkill = false;
			}
		} else {
			if((words[0].getWord().length == InputUtility.getSpell().length())){
				if(words[0].cast(InputUtility.getSpell())){
					skills[0] = new IceSkill(x, y, direction);
					RenderableHolder.getInstance().add(skills[0]);
					skills[0].play();
					isSkill = true;
					countSpell=200;
					isCasting=true;
					InputUtility.clearSpell();
				}
			}
			if((words[1].getWord().length == InputUtility.getSpell().length())){
				if(words[1].cast(InputUtility.getSpell())){
					skills[1] = new FireSkill(x, y, direction);
					RenderableHolder.getInstance().add(skills[1]);
					skills[1].play();
					isSkill = true;
					countSpell=200;
					isCasting=true;
					InputUtility.clearSpell();
				}
			}
			if((words[2].getWord().length == InputUtility.getSpell().length())){
				if(words[2].cast(InputUtility.getSpell())){
					skills[2] = new MeteorSkill(x, y, direction);
					RenderableHolder.getInstance().add(skills[2]);
					skills[2].play();
					isSkill = true;
					countSpell=200;
					isCasting=true;
					InputUtility.clearSpell();
				}
			}
			if((words[3].getWord().length == InputUtility.getSpell().length())){
				if(words[3].cast(InputUtility.getSpell())){
					skills[3] = new PoisonSkill(x, y, direction);
					RenderableHolder.getInstance().add(skills[3]);
					skills[3].play();
					isSkill = true;
					countSpell=200;
					isCasting=true;
					InputUtility.clearSpell();
				}
			}
			if((words[4].getWord().length == InputUtility.getSpell().length())){
				if(words[4].cast(InputUtility.getSpell())){		
					skills[4] = new SpikeSkill(x, y, direction);
					RenderableHolder.getInstance().add(skills[4]);
					skills[4].play();
					isSkill = true;
					countSpell=200;
					isCasting=true;
					InputUtility.clearSpell();
				}
			}
			
		} 
		
		isMid = false;
	}

	@Override
	public void draw(Graphics2D g) {
		if(isCasting){
			if (direction == 2) {
			g.drawImage(Resource.hero_a.getSubimage(Resource.hero_a.getWidth() / 10
					* frameCountA, 0, Resource.hero_a.getWidth() / 10,
					Resource.hero_a.getHeight()), null, x-20, y-20);
			} else if (direction == 1) {
			g.drawImage(Resource.hero_af.getSubimage(Resource.hero_af.getWidth()
					/ 10 * (9 - frameCountA), 0, Resource.hero_af.getWidth() / 10,
					Resource.hero_af.getHeight()), null, x-20, y-20);
			}
		}
		else { 
			if (direction == 2) {
				g.drawImage(Resource.hero.getSubimage(Resource.hero.getWidth() / 6
						* frameCount, 0, Resource.hero.getWidth() / 6,
						Resource.hero.getHeight()), null, x, y);
			} else if (direction == 1) {
				g.drawImage(Resource.hero_f.getSubimage(Resource.hero_f.getWidth()
						/ 6 * (5 - frameCount), 0, Resource.hero_f.getWidth() / 6,
						Resource.hero.getHeight()), null, x, y);
			}
		}
	}
	
	public int getX(){
		return x;
	}
	
	public boolean isMid(){
		return isMid;
	}
	public void setMid(boolean isMid){
		this.isMid=isMid;
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public int getZ() {
		return 0;
	}

	public boolean getIsMid() {
		return isMid;
	}

}