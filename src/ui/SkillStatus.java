package ui;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import render.Renderable;
import render.Resource;

public class SkillStatus implements Renderable {

	double fireAngle = 0.0;
	public boolean fireCasting = true;

	@Override
	public void draw(Graphics2D g) {

		
		
		g.drawImage(Resource.skillBox, null, 250, 0);
		
		if (fireCasting) {
			fireAngle += Math.toRadians(5);
			while (fireAngle > 2 * Math.PI)
				fireAngle -= 2 * Math.PI;
		}
		
		AffineTransform backup = g.getTransform();
		AffineTransform trans = new AffineTransform();
		trans.rotate(fireAngle, 290, 37);
		g.transform(trans);
		g.drawImage(Resource.fireStatus, null, 265, 12);

		g.setTransform(backup);			
		
		g.drawImage(Resource.iceStatus, null, 341, 12);
		g.drawImage(Resource.meteorStatus, null, 417, 12);
		g.drawImage(Resource.poisonStatus, null, 495, 12);
		g.drawImage(Resource.spikeStatus, null, 571, 12);
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
