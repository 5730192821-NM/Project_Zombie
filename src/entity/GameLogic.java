package entity;

import java.awt.event.KeyEvent;
import java.util.List;

import entity.monster.Cage;
import input.InputUtility;
import render.Renderable;
import render.RenderableHolder;
import ui.*;

public class GameLogic {

	private Land land;
	private Hero hero;
	private HeroStatus heroStatus;
	private SkillStatus skillStatus;
	private PauseButton pauseButton;
	private boolean castFail = false, iCast = false, fCast = false,
			mCast = false, pCast = false, sCast = false;
	private Word ice = null, fire = null, meteor = null, poison = null,
			spike = null;
	private int tick = 0;
	private Background background;
	private boolean isPause = false;
	private int speed = 20;

	public GameLogic() {

		this.land = new Land(0, 0);
		this.background = new Background(0, 0);
		this.hero = new Hero(20, 370, this.land, this.background);
		this.heroStatus = new HeroStatus();
		this.skillStatus = new SkillStatus();
		this.pauseButton = new PauseButton();

		Cage.getInstance().add("yeti", this.land, this.hero);

		RenderableHolder.getInstance().add(land);
		RenderableHolder.getInstance().add(background);
		RenderableHolder.getInstance().add(hero);
		RenderableHolder.getInstance().add(heroStatus);
		RenderableHolder.getInstance().add(skillStatus);
		RenderableHolder.getInstance().add(pauseButton);
	}

	public void update() {

		// Sleep speed
		if (InputUtility.getKeyPressed(KeyEvent.VK_1))
			setSpeed(speed+1);
		else if (InputUtility.getKeyPressed(KeyEvent.VK_2))
			setSpeed(speed-1);

		// Pause
		if (InputUtility.getSpaceTriggered())
			this.setPause(!isPause);
		InputUtility.setSpaceTriggered(false);

		if (this.isPause())
			return;

		if (InputUtility.getSpell() != "") {
			if (InputUtility.getSpell().length() > 6
					|| ("IFMPS").indexOf(InputUtility.getSpell()
							.substring(0, 1)) < 0)
				InputUtility.clearSpell();
		}

		if (castFail) {
			castFail = false;
			iCast = false;
			fCast = false;
			mCast = false;
			pCast = false;
			sCast = false;
			InputUtility.clearSpell();
		}

		boolean cast = iCast || fCast || mCast || pCast || sCast;

		// Add Word
		if (InputUtility.getSpell() != "") {
			if (!cast) {
				if (InputUtility.getSpell().equalsIgnoreCase("I")) {
					ice = new Word("ICE");
					ice.setVisible(true);
					iCast = true;
					RenderableHolder.getInstance().add(ice);
				} else if (InputUtility.getSpell().equalsIgnoreCase("F")) {
					fire = new Word("FIRE");
					fire.setVisible(true);
					fCast = true;
					RenderableHolder.getInstance().add(fire);
				} else if (InputUtility.getSpell().equalsIgnoreCase("M")) {
					meteor = new Word("METEOR");
					meteor.setVisible(true);
					mCast = true;
					RenderableHolder.getInstance().add(meteor);
				} else if (InputUtility.getSpell().equalsIgnoreCase("P")) {
					poison = new Word("POISON");
					poison.setVisible(true);
					pCast = true;
					RenderableHolder.getInstance().add(poison);
				} else if (InputUtility.getSpell().equalsIgnoreCase("S")) {
					spike = new Word("SPIKE");
					spike.setVisible(true);
					sCast = true;
					RenderableHolder.getInstance().add(spike);
				} else if (InputUtility.getSpell().length() == 1) {
					castFail = true;
				}
			}

			// Fill Color
			String lastWord = InputUtility.getSpell().substring(
					InputUtility.getSpell().length() - 1,
					InputUtility.getSpell().length());

			if (iCast)
				ice.cast(lastWord);
			else if (fCast)
				fire.cast(lastWord);
			else if (mCast && !lastWord.equalsIgnoreCase("E"))
				meteor.cast(lastWord);
			else if (mCast)
				meteor.setRed(InputUtility.getSpell().length());
			else if (pCast && !lastWord.equalsIgnoreCase("O"))
				poison.cast(lastWord);
			else if (pCast)
				poison.setRed(InputUtility.getSpell().length());
			else if (sCast)
				spike.cast(lastWord);
		}

		List<Renderable> list = RenderableHolder.getInstance()
				.getRenderableList();

		// Remove Word
		for (Renderable e : list) {
			if (e instanceof Word) {
				if (((Word) e).getString().indexOf(InputUtility.getSpell()) < 0) {
					RenderableHolder.getInstance().remove(e);
					castFail = true;
				}
				if (((Word) e).getString().equalsIgnoreCase(
						InputUtility.getSpell())) {
					RenderableHolder.getInstance().remove(e);
					iCast = false;
					fCast = false;
					mCast = false;
					pCast = false;
					sCast = false;
				}
			}

		}

		/*
		 * if (tick >= 200) { tick = 0;
		 * 
		 * // How to summon here; Cage.getInstance().add("golem", this.land,
		 * this.hero); Cage.getInstance().add("yeti", this.land, this.hero); }
		 */
		Cage.getInstance().updateAll();
		hero.update();
		background.update();
		land.update();
		tick++;
	}

	public boolean isPause() {
		return isPause;
	}

	public void setPause(boolean isPause) {
		this.isPause = isPause;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		if (speed > 0)
			this.speed = speed;
		else
			this.speed = 0;
	}

}
