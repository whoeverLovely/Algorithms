package ufPractice;

public class QuickUnionImpro {
	int[] id;
	int n;
	int[] size;
	public QuickUnionImpro(int n) {
		this.n = n;
		id = new int[n];
		size = new int[n];
		for(int i = 0; i < n; i++) {
			id[i] = i;
			size[i] = 1;
		}
	}
	
	public int root(int i) {
		while(i != id[i]) {
			i = id[i];
			id[i] = id[id[i]];
		}
		return i;
	}
	
	public void union(int p, int q) {
		int pRoot = root(p);
		int qRoot = root(q);
		
		if(size[p] < size[q]) {
			id[pRoot] = qRoot;
			size[qRoot] = size[qRoot] + size[pRoot];
		} else {
			id[qRoot] = pRoot;
			size[pRoot] = size[pRoot] + size[qRoot];
		}
	}
	
	public boolean find(int p, int q) {
		int rootP = root(p);
		int rootQ = root(q);
		return rootP == rootQ;
	}
	
	public static void main(String[] args) {
		QuickUnionImpro qf = new QuickUnionImpro(10);
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
