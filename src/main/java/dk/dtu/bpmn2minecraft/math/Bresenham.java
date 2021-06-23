package dk.dtu.bpmn2minecraft.math;

import java.util.ArrayList;
import java.util.List;

public class Bresenham {

	public static List<Point> findLine(Point start, Point end) {
		int x0 = start.x;
		int y0 = start.z;
		int x1 = end.x;
		int y1 = end.z;
		List<Point> line = new ArrayList<Point>();

		int dx = Math.abs(x1 - x0);
		int dy = Math.abs(y1 - y0);

		int sx = x0 < x1 ? 1 : -1;
		int sy = y0 < y1 ? 1 : -1;

		int err = dx - dy;
		int e2;

		while (true) {
			line.add(new Point(x0,y0));

			if (x0 == x1 && y0 == y1)
				break;

			e2 = 2 * err;
			if (e2 > -dy) {
				err = err - dy;
				x0 = x0 + sx;
			}

			if (e2 < dx) {
				err = err + dx;
				y0 = y0 + sy;
			}
		}
		return line;
	}
}
