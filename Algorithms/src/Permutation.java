import edu.princeton.cs.algs4.StdIn;

public class Permutation {
	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> rq = new RandomizedQueue<String>();

		for (int i = 0; i < k; i++)
			rq.enqueue(StdIn.readString());
		
		for (int i = 0; i < k; i++)
			System.out.println(rq.dequeue());

	}
	
//	public static void main(String[] args) {
//		In in = new In("queues/duplicates.txt");      // input file
//        
//		int k = Integer.parseInt(args[0]);
//		RandomizedQueue<String> rq = new RandomizedQueue<String>();
//
//		for (int i = 0; i < k; i++)
//			rq.enqueue(in.readString());
//		
//		for (int i = 0; i < k; i++)
//			System.out.println(rq.dequeue());
//	}
}
