package figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class Ray extends LineSegment {

	public Ray(Point start, Point end) {
		super(start, end);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getBorderColor());
		int x1 = this.getLocation().x;
		int y1 = this.getLocation().y;
		int x2 = this.getEnd().x;
		int y2 = this.getEnd().y;

		if (x1 == x2) {
			if (y1 > y2) {
				g.drawLine(x1, y1, x2, -10000);
			} else {
				g.drawLine(x1, y1, x2, 10000);
			}
		} else if (y1 == y2) {
			if (x1 > x2) {
				g.drawLine(x1, y1, -10000, y2);
			} else {
				g.drawLine(x1, y1, 10000, y2);
			}
		} else {
			if (y1 > y2) {
				g.drawLine(x1, y1, (x2 * y1 - x1 * y2) / (y1 - y2), 0);
			} else {
				g.drawLine(x1, y1, (x2 * y1 - x1 * y2 - 10000 * (x2 - x1)) / (y1 - y2), 10000);
			}
		}

		g.drawOval(x1 - 2, y1 - 2, 4, 4);
		g.fillOval(x1 - 2, y1 - 2, 4, 4);
	}
	
	@Override
	public boolean contains(Point p) {
		Ellipse2D area = new Ellipse2D.Double(this.getLocation().x - 3, this.getLocation().y - 3, 6, 6);
		return area.contains(p);
	};
}
