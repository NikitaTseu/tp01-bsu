package figures;

import java.awt.Point;
import java.util.List;

public class RegularPolygon extends CustomPolygon {

	public RegularPolygon(List<Point> list, int n) {
		super(list, 2);
		this.setN(n);
		setPointArrays();
	}
	
	@Override
	protected void setPointArrays() {
		int[] tmpx = new int[this.getN() + 10];
		int[] tmpy = new int[this.getN() + 10];
		
		int r = (int) this.getPoints().get(0).distance(this.getPoints().get(1));
		
		for (int i = 0; i < this.getN(); i++) {
			tmpx[i] = (int) (r * Math.cos(2 * Math.PI * i / this.getN())) + this.getLocation().x;
			tmpy[i] = (int) (r * Math.sin(2 * Math.PI * i / this.getN())) + this.getLocation().y;
		}
		
		this.setXpoints(tmpx);
		this.setYpoints(tmpy);
	}
}
