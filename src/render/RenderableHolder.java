package render;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RenderableHolder {
	
	private static final RenderableHolder instance = new RenderableHolder();
	private List<Renderable> entities = new CopyOnWriteArrayList<Renderable>();

	public static RenderableHolder getInstance() {
		return instance;
	}

	public RenderableHolder() {
	}

	public void add(Renderable o) {
		entities.add(o);
		/*Collections.sort(entities, new Comparator<IRenderable>() {

			@Override
			public int compare(IRenderable o1, IRenderable o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o2.getZ(), o1.getZ());
			}
		});*/
	}
	
	public void remove(Renderable o) {
		entities.remove(o);
	}


	public List<Renderable> getRenderableList() {
		return entities;

	}


}
