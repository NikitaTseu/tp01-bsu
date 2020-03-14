package application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Palette extends JFrame {

	private MenuBar menuBar;
	private Menu chooseFigure, chooseColor;
	private MenuItem line, polygon, circle, ellipse, lineSegment, ray, customPolygon, polyLine, rectangle, rhombus,
			regularPolygon;
	private MenuItem backgroundColor, borderColor;
	private Color bgColor = Color.PINK, brColor = Color.BLACK;
	private DrawPanel drawPanel;

	private FigureType figureType = FigureType.LINESEGMENT;

	public Palette() {
		setTitle("Our Lovely Paint");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 900);
		setLocationRelativeTo(null);
		createAll(this);
	}

	public void createAll(Palette mainPalette) {
		drawPanel = new DrawPanel(mainPalette);
		mainPalette.add(drawPanel);

		menuBar = new MenuBar();
		mainPalette.setMenuBar(menuBar);
		chooseFigure = new Menu("Choose figure");
		chooseFigure.add(lineSegment = new MenuItem("Line segment"));
		chooseFigure.add(ray = new MenuItem("Ray"));
		chooseFigure.add(line = new MenuItem("Line"));
		chooseFigure.add(circle = new MenuItem("Circle"));
		chooseFigure.add(ellipse = new MenuItem("Ellipse"));
		chooseFigure.add(polygon = new MenuItem("Regular polygon"));
		chooseFigure.add(customPolygon = new MenuItem("Custom polygon"));
		chooseFigure.add(polyLine = new MenuItem("Polyline"));
		chooseFigure.add(rectangle = new MenuItem("Rectangle"));
		chooseFigure.add(rhombus = new MenuItem("Rhombus"));
		chooseFigure.add(regularPolygon = new MenuItem("Triangle"));

		chooseColor = new Menu("Choose color");
		chooseColor.add(backgroundColor = new MenuItem("Background Color"));
		chooseColor.add(borderColor = new MenuItem("Border Color"));

		menuBar.add(chooseFigure);
		menuBar.add(chooseColor);

		backgroundColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color tmpColor = JColorChooser.showDialog(mainPalette, "ColorChooser", bgColor);
				if (tmpColor != null) {
					bgColor = tmpColor;
				}
			}
		});

		borderColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color tmpColor = JColorChooser.showDialog(mainPalette, "ColorChooser", brColor);
				if (tmpColor != null) {
					brColor = tmpColor;
				}
			}
		});

		chooseFigure.addActionListener(figureListener);
	}

	ActionListener figureListener = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			drawPanel.clearPointBuffer();
			int n = -1;
			switch (event.getActionCommand()) {
			case "Line":
				figureType = FigureType.LINE;
				drawPanel.setBufferLimit(2);
				break;

			case "Custom polygon":
				n = getN(3);
				if (n > 0) {
					figureType = FigureType.CUSTOMPOLYGON;
					drawPanel.setBufferLimit(n);
				}
				break;

			case "Polyline":
				n = getN(3);
				if (n > 0) {
					figureType = FigureType.POLYLINE;
					drawPanel.setBufferLimit(n);
				}
				break;

			case "Rectangle":
				figureType = FigureType.RECTANGLE;
				drawPanel.setBufferLimit(2);
				break;

			case "Rhombus":
				figureType = FigureType.RHOMBUS;
				drawPanel.setBufferLimit(2);
				break;

			case "Regular polygon":
				n = getN(3);
				if (n > 0) {
					figureType = FigureType.REGULARPOLYGON;
					drawPanel.setN(n);
				}
				break;

			case "Line segment":
				figureType = FigureType.LINESEGMENT;
				drawPanel.setBufferLimit(2);
				break;

			case "Ray":
				figureType = FigureType.RAY;
				drawPanel.setBufferLimit(2);
				break;

			case "Circle":
				figureType = FigureType.CIRCLE;
				drawPanel.setBufferLimit(2);
				break;

			case "Ellipse":
				figureType = FigureType.ELLIPSE;
				drawPanel.setBufferLimit(2);
				break;

			case "Triangle":
				figureType = FigureType.TRIANGLE;
				drawPanel.setBufferLimit(3);
				break;

			default:
				figureType = FigureType.LINESEGMENT;
				drawPanel.setBufferLimit(2);
			}
		}
	};

	private int getN(int minN) {
		String s = JOptionPane.showInputDialog(drawPanel, "n = ");

		if (s == null || s.equals(""))
			JOptionPane.showMessageDialog(drawPanel, "Incorrect input! Figure won't be changed.", "Error",
					JOptionPane.ERROR_MESSAGE);
		else {
			try {
				if (Integer.parseInt(s) >= minN) {
					return Integer.parseInt(s);
				} else {
					JOptionPane.showMessageDialog(drawPanel, "You should enter a number greater then " + (minN - 1));
					return getN(minN);
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(drawPanel, "Incorrect input! Figure won't be changed.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		return -1;
	}

	public Color getBgColor() {
		return bgColor;
	}

	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}

	public Color getBrColor() {
		return brColor;
	}

	public void setBrColor(Color brColor) {
		this.brColor = brColor;
	}

	public FigureType getFigureType() {
		return figureType;
	}

	public void setFigureType(FigureType figureType) {
		this.figureType = figureType;
	}
}