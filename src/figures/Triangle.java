package figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.List;

public class Triangle extends Shape {

	private int xpoints[] = new int[3];
	private int ypoints[] = new int[3];

	public Triangle(List<Point> points) {
		this.setLocation((Point)points.get(0).clone());
		for (int i = 0; i < 3; i++) {
			xpoints[i] = points.get(i).x;
			ypoints[i] = points.get(i).y;
		}
	}

	public void move(Point p) {
		for (int i = 0; i < 3; i++) {
			xpoints[i] += getDelta(p).x;
			ypoints[i] += getDelta(p).y;
		}
		super.move(p);
	}
	
	@Override
	public boolean contains(Point p) {
		Polygon area = new Polygon(xpoints, ypoints, 3);
		return area.contains(p);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getBgColor());
		g.fillPolygon(xpoints, ypoints, 3);
		g.setColor(this.getBorderColor());
		g.drawPolygon(xpoints, ypoints, 3);
	}
}
