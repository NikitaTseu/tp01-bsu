package application;

public enum FigureType {
    LINE("Line"), 
    CIRCLE("Circle"), 
    ELLIPSE("Elipse"), 
    LINESEGMENT("Line segment"), 
    RAY("Ray"), 
    CUSTOMPOLYGON("Custom polygon", true, 3, -1), 
    REGULARPOLYGON("Regular polygon", true, 3, 2), 
    POLYLINE("Polyline", true, 2, -1), 
    RECTANGLE("Rectangle"), 
    RHOMBUS("Rhombus"), 
    TRIANGLE("Triangle", 3);
	
	private String menu;
	boolean needN;
	int defN;
	int bflim;
	
	private FigureType(String menu) {
    	this(menu, false, 0, 2);
    }
    
    private FigureType(String menu, int bflim) {
    	this(menu, false, 0, bflim);
    }
    
    private FigureType(String menu, boolean needN, int defN, int bflim) {
    	this.menu = menu;
    	this.needN = needN;
    	this.defN = defN;
    	this.bflim = bflim;
    }
    
    public String getMenu() {
    	return menu;
    }
}

