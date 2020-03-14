package figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Figure {
	private Point location;
	private Color borderColor = Color.BLACK;

	public abstract void draw(Graphics g);
	
	public Point getDelta(Point p) {
		return new Point(p.x - location.x, p.y - location.y);
	}

	public void move(Point p) {
		location = p;
	}
	
	public Color getBorderColor() {
		return borderColor;
	}
	
	public abstract boolean contains(Point p);

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public void setLocation(Point newLoc) {
		location = newLoc;
	};

	public Point getLocation() {
		return location;
	};
}
