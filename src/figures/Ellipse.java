package figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Shape {
	private int h, w;

	public Ellipse(Point a1, Point a2) {
		Point p1 = (Point) a1.clone();
		Point p2 = (Point) a2.clone();
		
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
		
		w = (int) (p2.x - p1.x);
		h = (int) (p2.y - p1.y);
		
		this.setLocation(p1);
	}
	
	@Override
	public boolean contains(Point p) {
		Ellipse2D area = new Ellipse2D.Double(this.getLocation().x, this.getLocation().y, w, h);
		return area.contains(p);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getBgColor());
		g.fillOval(this.getLocation().x, this.getLocation().y, w, h);
		g.setColor(this.getBorderColor());
		g.drawOval(this.getLocation().x, this.getLocation().y, w, h);
	}

	public void setH(int h) {
		this.h = h;
	}

	public void setW(int w) {
		this.w = w;
	}
}
