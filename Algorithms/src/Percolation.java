import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private int n;
	private WeightedQuickUnionUF uf;
	private int[] status; // 0-block,1-open

	// create n-by-n grid, with all sites blocked
	public Percolation(int n) {
		if (n <= 0)
			throw new IllegalArgumentException();
		this.n = n;
		uf = new WeightedQuickUnionUF(n * n + 2);
		for (int i = 0; i < n; i++) {
			uf.union(n * n, i);
		}
		for (int i = n * (n - 1); i < n * n; i++) {
			uf.union(n * n + 1, i);
		}
		status = new int[n * n];
	}

	// open site (row, col) if it is not open already
	public void open(int row, int col) {
		if (!isLegal(row, col))
			throw new IndexOutOfBoundsException("The node is illegal");

		status[getId(row, col)] = 1;

		if (isLegal(row - 1, col))
			if (isOpen(row - 1, col))
				uf.union(getId(row, col), getId(row - 1, col));
		if (isLegal(row + 1, col))
			if (isOpen(row + 1, col))
				uf.union(getId(row, col), getId(row + 1, col));
		if (isLegal(row, col - 1))
			if (isOpen(row, col - 1))
				uf.union(getId(row, col), getId(row, col - 1));
		if (isLegal(row, col + 1))
			if (isOpen(row, col + 1))
				uf.union(getId(row, col), getId(row, col + 1));
	}

	// is site (row, col) open?
	public boolean isOpen(int row, int col) {
		if (!isLegal(row, col))
			throw new IndexOutOfBoundsException("The node is illegal");

		if (status[getId(row, col)] == 1)
			return true;
		else
			return false;
	}

	// is site (row, col) full?
	public boolean isFull(int row, int col) {
		if (!isLegal(row, col))
			throw new IndexOutOfBoundsException("The node is illegal");

		if (isOpen(row, col) && uf.connected(getId(row, col), n * n))
			return true;
		else
			return false;
	}

	// number of open sites
	public int numberOfOpenSites() {
		int sites = 0;
		for (int i = 0; i < n * n; i++) {
			if (status[i] == 1)
				sites++;
		}
		return sites;
	}

	// does the system percolate?
	public boolean percolates() {
		if (uf.connected(n * n, n * n + 1))
			return true;
		else
			return false;
	}

	private boolean isLegal(int row, int col) {
		if (row < 1 | row > n | col < 1 | col > n)
			return false;
		else
			return true;
	}

	private int getId(int row, int col) {
		return (row - 1) * n + col - 1;
	}

	// test client (optional)
	public static void main(String[] args) {
		
		In in = new In(args[0]);      // input file
        int n = in.readInt();         // n-by-n percolation system
        Percolation perc = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        System.out.println(perc.percolates());
	
	}

}
