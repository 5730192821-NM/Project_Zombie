package entity;

import input.InputUtility;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import entity.monster.Cage;
import entity.monster.Monster;
import entity.monster.Yeti;
import entity.skill.*;
import render.*;

public class Hero extends Moving implements Renderable {

	private int frameCount = 0, count = 0, countA = 0, frameCountA = 0,
			direction = 1, temp, countSpell = 0, i = 5;
	private int gravity = 1, velocityY;
	private boolean isJumped;
	private boolean isRight, isLeft;
	private boolean isSkill, isCasting;
	private Land land;
	private Background background;
	private Skill[] skills = new Skill[6];
	private static Word[] words = new Word[5];
	private int STR, INT, hp, attack, attackRange = 0;
	private Monster nearMon;

	// direction 1 : RIGHT direction 2 : LEFT

	public Hero(int x, int y, Land land, Background background) {
		super(x, y);
		this.land = land;
		this.background = background;
		temp = y;
		isJumped = false;
		velocityY = 0;
		isRight = false;
		isSkill = false;
		isCasting = false;
		STR = 10;
		INT = 10;
		hp = STR * 20;
		attack = INT * 2000;

		skills[0] = new IceSkill(x, y, direction);
		skills[1] = new FireSkill(x, y, direction);
		skills[2] = new MeteorSkill(x, y, direction);
		skills[3] = new PoisonSkill(x, y, direction);
		skills[4] = new SpikeSkill(x, y, direction);
		skills[5] = new SpikeSkill(x, y, direction); // dummy For Exception

		words[0] = new Word("ICE");
		words[1] = new Word("FIRE");
		words[2] = new Word("METEOR");
		words[3] = new Word("POISON");
		words[4] = new Word("SPIKE");

		nearMon = new Yeti(1000, 1000, land, this);
	}

	@Override
	public void update() {

		// Idle Spell Animation
		if (isCasting) {
			if (countA == 8) {
				countA = 0;
				frameCountA++;
				if (frameCountA >= 10) {
					isCasting = false;
					frameCountA = 0;
					countA = 0;
				}
			}
			countA++;
		} else {
			// Idle Animation Controller
			if (count == 10) {
				count = 0;
				frameCount++;
				frameCount %= 6;
			}
			count++;
		}

		// Spell Time Casting
		if (countSpell == 200) {
			countSpell = 0;
			// InputUtility.clearSpell();
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
			if (x > 200 || land.isStart()) {
				if (x + 20 > 0)
					x -= 5;
			} else if (!(x > 200 || land.isStart())) {
				isLeft = true;
				land.setX(-5);
				background.setX(-2);
				land.setEnd(false);
			}
		}
		if (InputUtility.getKeyPressed(KeyEvent.VK_RIGHT)) {
			direction = 1;
			if (x < 400 || land.isEnd()) {
				if (x < 720)
					x += 5;
			} else if (!(x < 400 || land.isEnd())) {
				isRight = true;
				land.setX(5);
				background.setX(2);
				land.setStart(false);
			}
		}

		// Casting Skill
		if (isSkill) {
			if (x >= 400 && isRight) {
				skills[i].setX(5);
			} else if (x <= 200 && isLeft) {
				skills[i].setX(-5);
			}

			// interaction
			for (Monster m : Cage.getInstance().getCage()) {

				// ice + fire
				if (i == 0 || i == 1) {
					if (skills[i].getFrameCount() == 1
							&& m.getX() <= nearMon.getX() + 50
							&& nearMon.getX() - 50 <= m.getX()) 
							m.hit(this, skills[i]);
				} else if (i == 2) { // meteor
					if (skills[i].getFrameCount() < 3 && skills[i].getAttackRange() != -1000
							&& (skills[i].getAttackRange() - 20 < m.getX() + 150)
							&& (m.getX() < skills[i].getAttackRange() + 100)) {
						m.hit(this, skills[i]);
					}
				} else if (i == 3) { // poison
					if (skills[i].getAttackRange() != -1000
							&& (skills[i].getAttackRange() - 5 < m.getX() + 150)
							&& (m.getX() < skills[i].getAttackRange() + 5)) {
						m.hit(this, skills[i]);
					}
				} else if (i == 4) { // spike
					if (skills[i].getFrameCount() < 5 && (skills[i].getAttackRange() < m.getX() + 100)
							&& (m.getX() < skills[i].getAttackRange() + 450)) {
						m.hit(this, skills[i]);
					}
				}
			}
			skills[i].update();

			if (!skills[0].isPlaying() && !skills[1].isPlaying()
					&& !skills[2].isPlaying() && !skills[3].isPlaying()
					&& !skills[4].isPlaying()) {
				isSkill = false;
				i = 5;
			}
		} else {
			if ((words[0].getWord().length == InputUtility.getSpell().length())) {
				if (words[0].cast(InputUtility.getSpell())) {
					skills[0] = new IceSkill(nearMon.getX(), y,
							nearMon.getDirection());
					RenderableHolder.getInstance().add(skills[0]);
					skills[0].play();
					isSkill = true;
					countSpell = 200;
					isCasting = true;
					i = 0;
					InputUtility.clearSpell();
				}
			}
			if ((words[1].getWord().length == InputUtility.getSpell().length())) {
				if (words[1].cast(InputUtility.getSpell())) {
					skills[1] = new FireSkill(nearMon.getX(), y,
							nearMon.getDirection());
					RenderableHolder.getInstance().add(skills[1]);
					skills[1].play();
					isSkill = true;
					countSpell = 200;
					isCasting = true;
					i = 1;
					InputUtility.clearSpell();
				}
			}
			if ((words[2].getWord().length == InputUtility.getSpell().length())) {
				if (words[2].cast(InputUtility.getSpell())) {
					skills[2] = new MeteorSkill(x, y, direction);
					RenderableHolder.getInstance().add(skills[2]);
					skills[2].play();
					isSkill = true;
					countSpell = 200;
					isCasting = true;
					i = 2;
					InputUtility.clearSpell();
				}
			}
			if ((words[3].getWord().length == InputUtility.getSpell().length())) {
				if (words[3].cast(InputUtility.getSpell())) {
					skills[3] = new PoisonSkill(x, y, direction);
					RenderableHolder.getInstance().add(skills[3]);
					skills[3].play();
					isSkill = true;
					countSpell = 200;
					isCasting = true;
					i = 3;
					InputUtility.clearSpell();
				}
			}
			if ((words[4].getWord().length == InputUtility.getSpell().length())) {
				if (words[4].cast(InputUtility.getSpell())) {
					skills[4] = new SpikeSkill(x, y, direction);
					RenderableHolder.getInstance().add(skills[4]);
					skills[4].play();
					isSkill = true;
					countSpell = 200;
					isCasting = true;
					i = 4;
					InputUtility.clearSpell();
				}
			}

		}

		for (int j = 0; j < Cage.getInstance().getCage().size(); j++) {
			if (Cage.getInstance().getCage().get(j).isDead()) {
				Cage.getInstance().getCage().remove(j);
			}
		}

		isRight = false;
		isLeft = false;
	}

	@Override
	public void draw(Graphics2D g) {
		if (isCasting) {
			if (direction == 2) {
				g.drawImage(Resource.hero_a.getSubimage(
						Resource.hero_a.getWidth() / 10 * frameCountA, 0,
						Resource.hero_a.getWidth() / 10,
						Resource.hero_a.getHeight()), null, x - 20, y - 20);
			} else if (direction == 1) {
				g.drawImage(Resource.hero_af.getSubimage(
						Resource.hero_af.getWidth() / 10 * (9 - frameCountA),
						0, Resource.hero_af.getWidth() / 10,
						Resource.hero_af.getHeight()), null, x - 20, y - 20);
			}
		} else {
			if (direction == 2) {
				g.drawImage(Resource.hero.getSubimage(Resource.hero.getWidth()
						/ 6 * frameCount, 0, Resource.hero.getWidth() / 6,
						Resource.hero.getHeight()), null, x, y);
			} else if (direction == 1) {
				g.drawImage(
						Resource.hero_f.getSubimage(Resource.hero_f.getWidth()
								/ 6 * (5 - frameCount), 0,
								Resource.hero_f.getWidth() / 6,
								Resource.hero.getHeight()), null, x, y);
			}
		}

		// Find near

		for (Monster m : Cage.getInstance().getCage()) {
			if (skills[i].getFrameCount() != 0)
				break;
			if (nearMon.isDead() || nearMon.getHp() == 0)
				nearMon = new Yeti(1000, 1000, land, this);
			if (this.direction == m.getDirection())
				if (Math.abs(m.getX() + 120 - x) <= Math.abs(nearMon.getX()
						+ 120 - x))
					nearMon = m;
		}

		g.setColor(Color.RED);
		g.setFont(Resource.biggerFont);
		g.drawString("NearMon X : " + nearMon.getX(), 50, 150);

	}

	public int getInt() {
		return INT;
	}

	public int getAttack() {
		return attack;
	}

	public int getX() {
		return x;
	}

	public boolean isLeft() {
		return isLeft;
	}

	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
	}

	public boolean isRight() {
		return isRight;
	}

	public void setRight(boolean isRight) {
		this.isRight = isRight;
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public int getZ() {
		return 1;
	}

	public boolean getIsMid() {
		return isRight;
	}

}