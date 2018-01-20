package ufPractice;

public class QuickUnion {
	int[] id;
	int n;
	public QuickUnion(int n) {
		this.n = n;
		id = new int[n];
		for(int i = 0; i < n; i++) {
			id[i] = i;
		}
	}
	
	public int root(int i) {
		
		while(i != id[i]) {
			i = id[i];
		}
		return i;
	}
	
	public void union(int p,int q) {
		int rootP = root(p);
		int rootQ = root(q);
		id[rootP] = rootQ;
	}
	
	public boolean find(int p, int q) {
		int rootP = root(p);
		int rootQ = root(q);
		return rootP == rootQ;
	}
	
	public static void main(String[] args) {
		QuickUnion qf = new QuickUnion(10);
		qf.union(0, 5);
		qf.union(3, 7);
		qf.union(3, 2);
		qf.union(0, 7);
		qf.union(9, 1);
		
		System.out.println(qf.find(5,3));
		System.out.println(qf.find(1, 0));
		System.out.println(qf.find(1, 9));
	}

}
