package figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class LineSegment extends Figure {
	private Point end;

	public LineSegment(Point start, Point end) {
		this.setLocation(start);
		this.end = end;
	}

	@Override
	public void move(Point p) {
		end.x += getDelta(p).x;
		end.y += getDelta(p).y;
		super.move(p);
	};

	public void draw(Graphics g) {
		g.setColor(this.getBorderColor());
        g.drawLine(this.getLocation().x, this.getLocation().y, end.x, end.y);
	}

	public Point getEnd() {
		return end;
	}

	public void setEnd(Point end) {
		this.end = end;
	}

	@Override
	public boolean contains(Point p) {
		Ellipse2D area1 = new Ellipse2D.Double(this.getLocation().x - 3, this.getLocation().y - 3, 6, 6);
		Ellipse2D area2 = new Ellipse2D.Double(this.getEnd().x - 3, this.getEnd().y - 3, 6, 6);
		return (area1.contains(p) || area2.contains(p));
	};
}
