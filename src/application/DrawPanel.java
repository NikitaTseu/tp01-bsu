package application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import figures.*;
import figures.Rectangle;
import figures.Shape;

@SuppressWarnings("serial")
public class DrawPanel extends JPanel {

	private Palette mainPalette; // it's my parent Panel
	private List<Figure> figureList = new ArrayList<Figure>();
	private List<Point> pointBuffer = new ArrayList<Point>();

	private int bufferLimit = 2;
	private int n = 3;
	private BufferedImage bufferedImage = new BufferedImage(1200, 900, BufferedImage.TYPE_INT_ARGB);

	public DrawPanel(Palette palette) {
		this.mainPalette = palette;

		this.addMouseListener(adapter);
		this.addMouseMotionListener(adapter);

	}
	
	private MouseAdapter adapter = new MouseAdapter() {
		Point startDragging = new Point(0, 0);
		boolean dragging = false;
		int dx = 0, dy = 0;

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				pointBuffer.add(new Point(e.getX(), e.getY()));

				// in this case we should create and draw a new figure
				if (pointBuffer.size() == bufferLimit) {
					Figure newFigure = createFigure();
					figureList.add(newFigure);
					clearPointBuffer();
				}
				redraw();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON3) {
				startDragging = e.getPoint();
				for (int i = figureList.size() - 1; i >= 0; i--) {
					if (figureList.get(i).contains(startDragging)) {
						clearPointBuffer();
						dragging = true;
						figureList.add(figureList.remove(i));
						dx = figureList.get(figureList.size() - 1).getLocation().x - startDragging.x;
						dy = figureList.get(figureList.size() - 1).getLocation().y - startDragging.y;
						break;
					}
				}
			}
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			if (dragging) {
				figureList.get(figureList.size() - 1).move(new Point(e.getX() + dx, e.getY() + dy));
				redraw();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (dragging && e.getButton() == MouseEvent.BUTTON3) {
				dragging = false;
				figureList.get(figureList.size() - 1).move(new Point(e.getX() + dx, e.getY() + dy));
				redraw();
			}
		}
	};

	private Figure createFigure() {
		Figure newFigure = null;

		switch (mainPalette.getFigureType()) {
		case LINE:
			newFigure = new Line(pointBuffer.get(0), pointBuffer.get(1));
			break;

		case RAY:
			newFigure = new Ray(pointBuffer.get(0), pointBuffer.get(1));
			break;

		case LINESEGMENT:
			newFigure = new LineSegment(pointBuffer.get(0), pointBuffer.get(1));
			break;

		case TRIANGLE:
			newFigure = new Triangle(pointBuffer);
			break;

		case RHOMBUS:
			newFigure = new Rhombus(pointBuffer);
			break;

		case ELLIPSE:
			newFigure = new Ellipse(pointBuffer.get(0), pointBuffer.get(1));
			break;

		case CIRCLE:
			newFigure = new Circle(pointBuffer.get(0), pointBuffer.get(1));
			break;

		case RECTANGLE:
			newFigure = new Rectangle(pointBuffer);
			break;

		case REGULARPOLYGON:
			newFigure = new RegularPolygon(pointBuffer, n);
			break;

		case POLYLINE:
			newFigure = new PolyLine(pointBuffer, bufferLimit);
			break;

		case CUSTOMPOLYGON:
			newFigure = new CustomPolygon(pointBuffer, bufferLimit);
			break;

		default:
			break;
		}

		// setting colors for the new figure
		if (newFigure instanceof Shape) {
			((Shape) newFigure).setBgColor(mainPalette.getBgColor());
		}
		newFigure.setBorderColor(mainPalette.getBrColor());

		return newFigure;
	}

	public void clearPointBuffer() {
		pointBuffer.clear();
		redraw();
	}

	public void redraw() {
		Graphics2D g2 = (Graphics2D)this.bufferedImage.getGraphics();
		Graphics2D g = (Graphics2D)this.getGraphics();
		
		g2.setColor(Color.WHITE);
		g2.fillRect(-10000, -10000, 20000, 20000);

		for (int i = 0; i < figureList.size(); i++) {
			figureList.get(i).draw(g2);
		}
		
		for (int i = 0; i < pointBuffer.size(); i++) {
			g2.setColor(Color.BLACK);
			g2.drawOval(pointBuffer.get(i).x - 2, pointBuffer.get(i).y - 2, 4, 4);
			g2.fillOval(pointBuffer.get(i).x - 2, pointBuffer.get(i).y - 2, 4, 4);
		}
		g2.setColor(Color.WHITE);
		this.paintComponent(g);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        g.drawImage(bufferedImage, 0, 0, null);
    }

	public void setBufferLimit(int bufferLimit) {
		this.bufferLimit = bufferLimit;
	}

	public void setN(int n) {
		this.n = n;
	}
}
