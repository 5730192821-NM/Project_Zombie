package entity;

import java.awt.Graphics2D;

import render.Renderable;
import render.Resource;

public class SpikeSkill implements Renderable{
	
	private static boolean isPlaying;
	private static int frameCount,count,x,y,frameWidth,frameHeight,direction;
	
	public SpikeSkill(int x,int y,int direction){
		SpikeSkill.direction=direction;
		if(direction==1)
			SpikeSkill.x=x-220;
		else SpikeSkill.x=x-240;
		SpikeSkill.y=328;
		try {
			frameWidth = Resource.spike1.getWidth() / 9;
			frameHeight = Resource.spike1.getHeight();
		} catch (NullPointerException e) {
			frameWidth = 0;
			frameHeight = 0;
		}
	}
	
	public static void setX(int x){
		SpikeSkill.x -= x; 
	}
	
	public static boolean isPlaying(){
		return isPlaying;
	}
	
	public static void update(){
		if(isPlaying){
			if(count==10){
				count=0;
				frameCount++;
				if(frameCount==9)
					stop();
			}
			count++;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		if (isPlaying && (Resource.spike1 != null)) {
			g.drawImage(Resource.spike1.getSubimage(Resource.spike1.getWidth() / 9 * frameCount,0, frameWidth, frameHeight), null, x, y);
		}
	}
	
	public void play(){
		isPlaying=true;
		frameCount=0;
		count=0;
	}
	
	public static void stop(){
		isPlaying=false;
		frameCount=0;
		count=0;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isPlaying;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

}
