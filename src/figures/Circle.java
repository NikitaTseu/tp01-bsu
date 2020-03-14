package figures;

import java.awt.Point;

public class Circle extends Ellipse {
	public Circle(Point location, Point p) {
		super(location, p);
		int r = (int) Point.distance(location.x, location.y, p.x, p.y);
		this.setLocation(new Point(location.x - r, location.y - r));
		this.setH(2 * r);
		this.setW(2 * r);
	}
}
