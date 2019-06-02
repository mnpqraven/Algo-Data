import java.util.ArrayList;
import java.util.Arrays;

public class BiggestElementsSolver {
	/**
	 * 1d + test console
	 * @param args
	 */
	public static void main(String[] args) {
		/** 1.000
		 * bruteforceBE Asc runtime: 1072294
		 * bruteforceBE Desc runtime: 17835245
		 * improvedBE Asc runtime: 3562199
		 * improvedBE Desc runtime: 794515
		 * linearBE Asc runtime: 1680780
		 * linearBE Desc runtime: 1629310
		 *
		 * 10.000
		 * bruteforceBE Asc runtime: 3850878
		 * bruteforceBE Desc runtime: 113789179
		 * improvedBE Asc runtime: 11800873
		 * improvedBE Desc runtime: 8166293
		 * linearBE Asc runtime: 14381578
		 * linearBE Desc runtime: 5897656
		 *
		 * 100.000
		 * bruteforceBE Asc runtime: 19376301
		 * bruteforceBE Desc runtime: 7801237491
		 * improvedBE Asc runtime: 52015969
		 * improvedBE Desc runtime: 33891001
		 * linearBE Asc runtime: 43042990
		 * linearBE Desc runtime: 10088763
		 *
		 * 200.000
		 * bruteforceBE Asc runtime: 27567279
		 * bruteforceBE Desc runtime: 30705593272
		 * improvedBE Asc runtime: 87086775
		 * improvedBE Desc runtime: 51012088
		 * linearBE Asc runtime: 61726040
		 * linearBE Desc runtime: 56062681
		 */
		ArrayList<Integer> ArrayUnsortAsc = new ArrayList<>();
		ArrayList<Integer> ArrayUnsortDesc = new ArrayList<>();
		int n = 1000;

		for(int i = 1; i <= n; i++ ) {
			ArrayUnsortAsc.add(i);
		}
		for(int j = n; j >= 1; j-- ) {
			ArrayUnsortDesc.add(j);
		}

		long beginning = 0, end = 0;
		long runtime;
		//1a test
		beginning = System.nanoTime();
		bruteForceBE(ArrayUnsortAsc,ArrayUnsortAsc.size()/2);
		end = System.nanoTime();
		runtime = end - beginning;
		System.out.println("bruteforceBE Asc runtime: " + runtime);

		beginning = System.nanoTime();
		bruteForceBE(ArrayUnsortDesc,ArrayUnsortDesc.size()/2);
		end = System.nanoTime();
		runtime = end - beginning;
		System.out.println("bruteforceBE Desc runtime: " + runtime);


		//1b test
		beginning = System.nanoTime();
		improvedBE(ArrayUnsortAsc,ArrayUnsortAsc.size()/2);
		end = System.nanoTime();
		runtime = end - beginning;
		System.out.println("improvedBE Asc runtime: " + runtime);

		beginning = System.nanoTime();
		improvedBE(ArrayUnsortDesc,ArrayUnsortAsc.size()/2);
		end = System.nanoTime();
		runtime = end - beginning;
		System.out.println("improvedBE Desc runtime: " + runtime);


		//1c test
		beginning = System.nanoTime();
		linearBE(ArrayUnsortAsc,ArrayUnsortAsc.size()/2);
		end = System.nanoTime();
		runtime = end - beginning;
		System.out.println("linearBE Asc runtime: " + runtime);

		beginning = System.nanoTime();
		linearBE(ArrayUnsortDesc,ArrayUnsortDesc.size()/2);
		end = System.nanoTime();
		runtime = end - beginning;
		System.out.println("linearBE Desc runtime: " + runtime);

	}

	/**
	 * 1a
	 * @param a
	 * @param k
	 * @return
	 */
	public static ArrayList<Integer> bruteForceBE(ArrayList<Integer> a, int k) {
		if (k > a.size()) {
			System.out.println("K is bigger as the given array");
		} else {
			//Insertion-Sort here
			for (int i = 1; i < a.size(); i++) {
				int key = a.get(i);
				int j = i-1;

				while(j >= 0 && a.get(j) > key) {
					a.set(j + 1, a.get(j));
					j = j - 1;
				}
				a.set(j + 1, key);
			}
		}
		return getK(a,k);
	}

	/**
	 * 1b
	 * @param a
	 * @param k
	 * @return
	 */
	public static ArrayList<Integer> improvedBE(ArrayList<Integer> a, int k) {
		if (k > a.size()) {
			System.out.println("K is bigger as the given array");
		} else {
			sort(a,0,a.size()-1);
		}
		return getK(a,k);
	}
	//Merge-Sort O(n log n)
	private static void sort(ArrayList<Integer> a, int left, int right) {
		if (left < right) {
			int mid = (left+right)/2;
			//sort both halves
			sort(a, left, mid);
			sort(a, mid + 1, right);
			//merge back
			merge(a, left, mid, right);
		}
	}

	private static void merge(ArrayList<Integer> a, int left, int mid, int right) {
        //get size of subarrays
		int chunk1 = mid - left + 1;
		int chunk2 = right - mid;
		ArrayList<Integer> tempArrLeft = new ArrayList<>();
		ArrayList<Integer> tempArrRight = new ArrayList<>();
		//pipe data into temps
		for (int i=0; i < chunk1; ++i) {
			tempArrLeft.add(a.get(left + i));
		}
		for (int j=0; j < chunk2; ++j) {
			tempArrRight.add(a.get(mid + 1 + j));
		}

		//merge temp arrays
		int i = 0, j = 0;
		int k = left;
		while (i < chunk1 && j < chunk2) {
			if (tempArrLeft.get(i) <= tempArrRight.get(j)) {
				a.set(k, tempArrLeft.get(i));
				i++;
			} else {
				a.set(k, tempArrRight.get(j));
				j++;
			}
			k++;
		}
		while (i < chunk1) {
			a.set(k, tempArrLeft.get(i));
			i++;
			k++;
		}
		while (j < chunk2) {
			a.set(k, tempArrRight.get(j));
			j++;
			k++;
		}
	}

	/**
	 * 1c
	 * @param a
	 * @param k
	 * @return
	 */
	public static ArrayList<Integer> linearBE(ArrayList<Integer> a, int k){
		if (k > a.size()) {
			System.out.println("K is bigger as the given array");
		} else {
			radixsort(a, a.size());
		}
		return getK(a,k);
	}

	static void countSort(ArrayList<Integer> a, int n, int exp) {
		int output[] = new int[n];
		int i;

		int count[] = new int[10];
		Arrays.fill(count,0);
		for (i = 0; i < n; i++) {
			count[(a.get(i)/exp)%10]++;
		}
		//updates count[i] to represent array's index
		for (i = 1; i < 10; i++) {
			count[i] += count[i - 1];
		}
		for (i = n - 1; i >= 0; i--) {
			output[count[ (a.get(i)/exp)%10 ] - 1] = a.get(i);
			count[(a.get(i)/exp)%10]--;
		}
		//insert sorted values
		for (i = 0; i < n; i++) {
			a.set(i, output[i]);
		}
	}

	static void radixsort(ArrayList<Integer> a, int n){
		int max = a.get(0);
		for (int i = 1; i < n; i++) {
			if (a.get(i) > max) {
				max = a.get(i);
			}
		}
		for (int exp = 1; max/exp > 0; exp *= 10) {
			countSort(a, n, exp);
		}
	}

	/** k-Element output
	 * @param a
	 * @param k
	 * @return
	 */
	private static ArrayList<Integer> getK(ArrayList<Integer> a, int k) {
		ArrayList<Integer> array = new ArrayList<>();
		int key = a.size() - 1;
		for (int i = 1; i < k + 1; i++) {
			array.add(a.get(key));
			key--;
		}
		return array;
	}
}
