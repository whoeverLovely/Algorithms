package elementarySortsPractice;

public class SelectionSort {
	
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
	private static void exch(Comparable[]a, int i, int j) {
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	public void selectionSort(Comparable[] a) {
		int n = a.length;
		for(int i = 0; i < n; i++) {
			int min = i;
			for(int j = i+1; j < n; j++) {
				if(less(a[j],a[min]))
					min = j;
			}
			exch(a,i,min);
		}
	}
	
	public boolean isSorted(Comparable[] a) {
		for(int i = 1; i < a.length; i++) {
			if(a[i].compareTo(a[i-1]) < 0)
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		Integer[] a = {5,4,7,3,9,2,4,6};
		SelectionSort ss = new SelectionSort();
		System.out.println("before sorting, " + ss.isSorted(a));
		ss.selectionSort(a);
		System.out.println("after sorting " + ss.isSorted(a));
		
	}

}
