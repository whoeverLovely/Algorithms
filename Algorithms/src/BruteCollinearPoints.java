import java.util.Arrays;

public class BruteCollinearPoints {

	private int counts;
	private LineSegment[] init;

	// finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points) {
		if(points == null)
			throw new IllegalArgumentException();
		
		Point[] pp = points;
		
		Arrays.sort(pp);
		int n = pp.length;
		init = new LineSegment[n/4];
		
		for(int p = 0; p < n; p++) {
			if(pp[p] == null)
				throw new IllegalArgumentException();
			for(int q = p+1; q < n; q++) {
				if(pp[p].compareTo(pp[q]) == 0)
					throw new IllegalArgumentException();
				for(int r = q+1; r < n; r++) {
					if(pp[q].compareTo(pp[r]) == 0)
						throw new IllegalArgumentException();
					for(int s = r+1; s < n; s++) {
						if(pp[r].compareTo(pp[s]) == 0)
							throw new IllegalArgumentException();
						
						double slopeP_Q = pp[p].slopeTo(pp[q]);
						double slopeQ_R = pp[q].slopeTo(pp[r]);
						double slopeR_S = pp[r].slopeTo(pp[s]);
						
						if(Double.compare(slopeP_Q, slopeQ_R) == 0 && Double.compare(slopeQ_R, slopeR_S) == 0) {
							if(init[init.length-1] != null) {
								LineSegment[] temp = new LineSegment[init.length*2];
								for(int i = 0; i < init.length; i++)
									temp[i] = init[i];
								init = temp;
							}
						
							init[counts] = new LineSegment(pp[p],pp[s]);
							counts++;
						}
					}
				}
			}
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

	public static void main(String[] args) {
		
	}
}
