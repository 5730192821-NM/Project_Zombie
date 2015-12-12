package entity;

import render.RenderableHolder;
import ui.*;

public class GameLogic {
	
	private Land land;
	private Hero hero;
	private HeroStatus heroStatus;
	private SkillStatus skillStatus;
	private PauseButton pauseButton;

	public GameLogic (){
		this.land = new Land(0,0);
		this.hero = new Hero(20,315,this.land);
		this.heroStatus = new HeroStatus();
	    this.skillStatus = new SkillStatus();
		this.pauseButton = new PauseButton();

		RenderableHolder.getInstance().add(land);
		RenderableHolder.getInstance().add(hero);
		RenderableHolder.getInstance().add(heroStatus);
		RenderableHolder.getInstance().add(skillStatus);
		RenderableHolder.getInstance().add(pauseButton);
	}
	
	public void update(){
		land.update();
		hero.update();
	}
}


