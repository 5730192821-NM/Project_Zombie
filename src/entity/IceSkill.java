package entity;

import java.awt.Graphics2D;

import render.Renderable;
import render.Resource;

public class IceSkill implements Renderable{
	
	private static boolean isPlaying;
	private static int frameCount,count,x,y,frameWidth,frameHeight;
	
	public IceSkill(int x,int y,int direction){
		if(direction==1)
			IceSkill.x=x+300;
		else IceSkill.x=x-270;
		IceSkill.y=320;
		try {
			frameWidth = Resource.ice1.getWidth() / 7;
			frameHeight = Resource.ice1.getHeight();
		} catch (NullPointerException e) {
			frameWidth = 0;
			frameHeight = 0;
		}
	}
	
	public static boolean isPlaying(){
		return isPlaying;
	}
	
	public static void update(){
		if(isPlaying){
			if(count==10){
				count=0;
				frameCount++;
				if(frameCount==7)
					stop();
			}
			count++;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		if (isPlaying && (Resource.ice1 != null)) {
			g.drawImage(Resource.ice1.getSubimage(Resource.ice1.getWidth() / 7 * frameCount,0, frameWidth, frameHeight), null, x, y);
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
