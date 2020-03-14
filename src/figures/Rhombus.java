package figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class Rhombus extends Shape {

	private int xpoints[] = new int[4];
	private int ypoints[] = new int[4];

	public Rhombus(Point p1, Point p2) {
		if (p1.x > p2.x) {
			int tmp = p1.x;
			p1.x = p2.x;
			p2.x = tmp;
		}

		if (p1.y > p2.y) {
			int tmp = p1.y;
			p1.y = p2.y;
			p2.y = tmp;
		}

		this.setLocation(p1);

		int w = (int) (p2.x - p1.x);
		int h = (int) (p2.y - p1.y);
		xpoints[0] = p1.x + (int) w / 2;
		ypoints[0] = p1.y;
		xpoints[1] = p2.x;
		ypoints[1] = p1.y + (int) h / 2;
		xpoints[2] = xpoints[0];
		ypoints[2] = p2.y;
		xpoints[3] = p1.x;
		ypoints[3] = ypoints[1];
	}
	
	@Override
	public boolean contains(Point p) {
		Polygon area = new Polygon(xpoints, ypoints, 4);
		return area.contains(p);
	}

	public void move(Point p) {
		for (int i = 0; i < 4; i++) {
			xpoints[i] += getDelta(p).x;
			ypoints[i] += getDelta(p).y;
		}
		super.move(p);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getBgColor());
		g.fillPolygon(xpoints, ypoints, 4);
		g.setColor(this.getBorderColor());
		g.drawPolygon(xpoints, ypoints, 4);
	}
}
