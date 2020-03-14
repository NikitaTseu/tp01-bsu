package figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class CustomPolygon extends Shape {
	private int n;
	private int[] xpoints;
	private int[] ypoints;
	List<Point> points = new ArrayList<Point>();

	public CustomPolygon(List<Point> list, int n) {
		this.n = n;
		for (int i = 0; i < n; i++) {
			points.add(list.get(i));
		}
		this.setLocation(list.get(0));
		setPointArrays();
	}

	private void setPointArrays() {
		xpoints = new int[n + 1];
		ypoints = new int[n + 1];

		for (int i = 0; i < n; i++) {
			xpoints[i] = points.get(i).x;
			ypoints[i] = points.get(i).y;
		}
		
		xpoints[n] = points.get(0).x;
		ypoints[n] = points.get(0).y;
	}
	
	@Override
	public void move(Point p) {
		for (int i = 0; i < n + 1; i++) {
			xpoints[i] += getDelta(p).x;
			ypoints[i] += getDelta(p).y;
		}
		super.move(p);
	}
	
	@Override
	public boolean contains(Point p) {
		Polygon area = new Polygon(xpoints, ypoints, n);
		return area.contains(p);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getBgColor());
		g.fillPolygon(xpoints, ypoints, n);
		g.setColor(this.getBorderColor());
		g.drawPolygon(xpoints, ypoints, n);
	}
}
