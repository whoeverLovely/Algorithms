package elementarySortsPractice;

public class InsertionSort {

	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	private static void exch(Comparable[] a, int p, int q) {
		Comparable swap = a[p];
		a[p] = a[q];
		a[q] = swap;
	}

	public static boolean isSorted(Comparable[] a) {
		for (int i = 1; i < a.length; i++) {
			if (a[i].compareTo(a[i - 1]) < 0)
				return false;
		}
		return true;
	}

	public void insertionSort(Comparable[] a) {
		int n = a.length;
		for (int i = 1; i < n; i++) {
			for (int j = i; j > 0; j--) {
				if (less(a[j], a[j - 1])) {
					exch(a, j, j - 1);
				}
				else break;
			}
		}
	}

	public static void main(String[] args) {
		InsertionSort is = new InsertionSort();
		Integer[] a = { 4, 6, 3, 8, 5, 9, 1, 2, 3, 4 };
		System.out.println("before sorting, " + is.isSorted(a));
		is.insertionSort(a);
		System.out.println("after sorting " + is.isSorted(a));
	}

}
