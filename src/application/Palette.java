package application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class Palette extends JFrame {

	private MenuBar menuBar;
	private Menu chooseFigure, chooseColor;
	private MenuItem backgroundColor, borderColor;
	private Color bgColor = Color.PINK, brColor = Color.BLACK;
	private DrawPanel drawPanel;
	
	private Map<String, FigureType> names = new HashMap<String, FigureType>()
	{{
		for(FigureType ft: FigureType.values()) {
			put(ft.getMenu(), ft);
		}
	}};

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
		for(FigureType ft: FigureType.values()) {
			chooseFigure.add(new MenuItem(ft.getMenu()));
		}
		
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
			FigureType ft = names.get(event.getActionCommand());
			drawPanel.clearPointBuffer();
			
			if(ft.needN) {
				int tmpn = getN(ft.defN);
				if (tmpn >= ft.defN) {
					figureType = ft;
					drawPanel.setN(tmpn);
					if(ft.bflim == -1) {
						drawPanel.setBufferLimit(tmpn);
					}
					else {
						drawPanel.setBufferLimit(ft.bflim);
					}
				}
			}
			else {
				figureType = ft;
				drawPanel.setBufferLimit(ft.bflim);
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