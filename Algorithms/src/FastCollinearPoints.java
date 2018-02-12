import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
	private int counts;
	private LineSegment[] init;

	// finds all line segments containing 4 or more points
	public FastCollinearPoints(Point[] points) {
		if (points == null)
			throw new IllegalArgumentException();
		
		int n = points.length;

		init = new LineSegment[n/4];
		Point[] pp = null;
		for (int p = 0; p < n; p++) {
			if(points[p] == null)
				throw new IllegalArgumentException();
			Comparator<Point> c = points[p].slopeOrder();
			pp = points;
			Arrays.sort(pp, p + 1, n, c);
			
			Point[] temp = new Point[n-p];
			temp[0] = points[p];
			for (int q = p+1, i = 1; q < n-1; q++) {
				if(pp[p].compareTo(pp[q]) == 0)
					throw new IllegalArgumentException();
				
				temp[i] = pp[q];
				if(c.compare(pp[q], pp[q+1]) == 0) {
					i++;
					if(q+1 == n-1) {
						temp[i] = pp[q+1];
						helper(temp,i+1);
					}
				} else {
					helper(temp,i+1);
					i = 1;
					temp = new Point[n-q];
					temp[0] = points[p];
				}
			}
		}
	}
	
	private void helper(Point[] temp, int i) {
		if(i>=4) {
			Arrays.sort(temp,0,i); 
			if(init[init.length-1] != null) {
				LineSegment[] tempLS = new LineSegment[init.length*2];
				for(int j = 0; j < init.length; j++)
					tempLS[j] = init[j];
				init = tempLS;
			}
		
			init[counts] = new LineSegment(temp[0],temp[i-1]);
			counts++;
		}
	}

	// the number of line segments
	public int numberOfSegments() {
		return counts;

	}

	// the line segments
	public LineSegment[] segments() {
		LineSegment[] segments = new LineSegment[counts];

		for (int i = 0; i < init.length; i++) {
			if (init[i] != null) {
				segments[i] = init[i];
			}
		}
		return segments;
	}
	
}
