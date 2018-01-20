package ufPractice;

public class QuickFind {
	int[] id;
	int n;
	public QuickFind(int n) {
		this.n = n;
		id = new int[n];
		for(int i = 0; i < n; i++) {
			id[i] = i;
		}
	}
	
	public void union(int p, int q) {
		int pVal = id[p];
		int qVal = id[q];
		for(int i = 0; i < n; i++) {
			if(id[i] == pVal)
				id[i] = qVal;
		}
	}
	
	public boolean find(int p, int q) {
		return id[p]==id[q];
	}
	
	public static void main(String[] args) {
		QuickFind qf = new QuickFind(10);
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
