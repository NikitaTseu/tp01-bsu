package figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class RegularPolygon extends Shape {

	private int n;
	private int xpoints[] = new int[1000];
	private int ypoints[] = new int[1000];

	public RegularPolygon(Point location, Point p, int n) {
		this.setLocation(location);
		this.n = n;
		int r = (int) location.distance(p);
		
		for (int i = 0; i < n; i++) {
			xpoints[i] = (int) (r * Math.cos(2 * Math.PI * i / n)) + location.x;
			ypoints[i] = (int) (r * Math.sin(2 * Math.PI * i / n)) + location.y;
		}
	}

	public void move(Point p) {
		for (int i = 0; i < n; i++) {
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
