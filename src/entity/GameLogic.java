package entity;

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
	
	public GameLogic() {
		this.land = new Land(0, 0);
		this.hero = new Hero(20, 370, this.land);
		this.heroStatus = new HeroStatus();
		this.skillStatus = new SkillStatus();
		this.pauseButton = new PauseButton();
		
		//How to summon here;
		Cage.getInstance().add("golem", this.land, this.hero);
		Cage.getInstance().add("yeti", this.land, this.hero);
		
		RenderableHolder.getInstance().add(land);
		RenderableHolder.getInstance().add(hero);
		RenderableHolder.getInstance().add(heroStatus);
		RenderableHolder.getInstance().add(skillStatus);
		RenderableHolder.getInstance().add(pauseButton);
	}

	public void update() {
		if (castFail) {
			castFail = false;
			iCast = false;
			fCast = false;
			mCast = false;
			pCast = false;
			sCast = false;
			InputUtility.clearSpell();
		}

		// Add Word
		if (InputUtility.getSpell() != "") {
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
						InputUtility.getSpell()))
					RenderableHolder.getInstance().remove(e);
			}
		}
		Cage.getInstance().updateAll();
		land.update();
		hero.update();
	}
}
