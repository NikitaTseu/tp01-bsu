package figures;

import java.awt.Point;
import java.util.List;

public class Rhombus extends CustomPolygon{
	
	public Rhombus(List<Point> list) {
		super(list, 2);
		this.setN(4);
	}
	
	@Override
	protected void setPointArrays() {
		int[] tmpx = new int[4];
		int[] tmpy = new int[4];
		
		Point p1 = this.getPoints().get(0);
		Point p2 = this.getPoints().get(1);
		
		if (p1.x > p2.x) {
			int tmp = p1.x;
			p1.x = p2.x;
			p2.x = tmp;
		}

		if (p1.y > p2.y) {
			int tmp = p1.y;
			p1.y = p2.y;
			p2.y = tmp;
		}

		int w = (int) (p2.x - p1.x);
		int h = (int) (p2.y - p1.y);
		
		tmpx[0] = p1.x + (int) w / 2;
		tmpy[0] = p1.y;
		tmpx[1] = p2.x;
		tmpy[1] = p1.y + (int) h / 2;
		tmpx[2] = tmpx[0];
		tmpy[2] = p2.y;
		tmpx[3] = p1.x;
		tmpy[3] = tmpy[1];
		
		this.setXpoints(tmpx);
		this.setYpoints(tmpy);
	}
}
