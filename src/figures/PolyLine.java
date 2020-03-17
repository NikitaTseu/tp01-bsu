package figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.List;

public class PolyLine extends Figure {
	private LineSegment[] edges;
	private int n;
	
	public PolyLine(List<Point> list, int n) {
		this.n = n;
		edges = new LineSegment[n - 1];
		for (int i = 0; i < n - 1; i++) {
			edges[i] = new LineSegment(list.get(i), list.get(i + 1));
			edges[i].setBorderColor(this.getBorderColor());
		}
		this.setLocation(list.get(0));
	}

	@Override
	public void move(Point p) {
		int dx = getDelta(p).x;
		int dy = getDelta(p).y;
		for (LineSegment edge : edges) {
			edge.getLocation().x += dx;
			edge.getLocation().y += dy;
		}
		edges[n - 2].getEnd().x += dx; 
		edges[n - 2].getEnd().y += dy; 
		super.move(p);
	}

	@Override
	public void draw(Graphics g) {
		for (LineSegment edge : edges) {
			edge.draw(g);
		}
		g.drawOval(this.getLocation().x - 2, this.getLocation().y - 2, 4, 4);
		g.fillOval(this.getLocation().x - 2, this.getLocation().y - 2, 4, 4);
	}

	public LineSegment[] getEdges() {
		return edges;
	}

	public void setEdges(LineSegment[] edges) {
		this.edges = edges;
	}
	
	@Override
	public boolean contains(Point p) {
		Ellipse2D area = new Ellipse2D.Double(this.getLocation().x - 3, this.getLocation().y - 3, 6, 6);
		return area.contains(p);
	};
	
	@Override
	public void setBorderColor(Color borderColor) {
		for (LineSegment edge : edges) {
			edge.setBorderColor(borderColor);
		}
	}
}
