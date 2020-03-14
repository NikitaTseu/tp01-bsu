package figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class Line extends Ray {

	public Line(Point start, Point end) {
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
			g.drawLine(x1, -10000, x1, 10000);
		} else if (y1 == y2) {
			g.drawLine(-10000, y1, 10000, y1);
		} else {
			g.drawLine(0, (x2 * y1 - x1 * y2) / (x2 - x1), 10000, (x2 * y1 - x1 * y2 - 10000 * (y1 - y2)) / (x2 - x1));
		}

		g.drawOval(x1 - 2, y1 - 2, 4, 4);
		g.fillOval(x1 - 2, y1 - 2, 4, 4);
		g.drawOval(x2 - 2, y2 - 2, 4, 4);
		g.fillOval(x2 - 2, y2 - 2, 4, 4);
	}
	
	@Override
	public boolean contains(Point p) {
		Ellipse2D area1 = new Ellipse2D.Double(this.getLocation().x - 3, this.getLocation().y - 3, 6, 6);
		Ellipse2D area2 = new Ellipse2D.Double(this.getEnd().x - 3, this.getEnd().y - 3, 6, 6);
		return (area1.contains(p) || area2.contains(p));
	};
}
