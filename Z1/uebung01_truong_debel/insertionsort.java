public class InsertionSort {
	public static int[] createSequenceInc(int n) {
		int[] array = new int[n];
		for(int i = 0; i < array.length; i++) {
			array[i] = i+1;
		}
		return array;
	}

	public static int[] createSequenceDec(int n) {
		int[] array = new int[n];
		for(int i = 0; i < array.length; i++) {
			array[i] = n--;
		}
		return array;
	}
	public static int[] createSequenceRand(int n) {
		int[] array = new int[n];
		for(int i = 0; i < array.length; i++) {
			int random = (int) (Math.random()*100);
			array[i] = random;
		}
		return array;
	}
	public static int[] createSequenceAlt(int n) {
		int[] array = new int[n];
		int alt = 0;
		for(int i = 0; i < array.length; i++) {
			if (i%2 == 0) {
				alt = 1;
			} else alt = 2;
			array[i] = alt;
		}
		return array;
	}
	public static void insertionSort(int[] A) {
	/*
	i ← 1
while i < length(A)
    j ← i
    while j > 0 and A[j-1] > A[j]
        swap A[j] and A[j-1]
        j ← j - 1
    end while
    i ← i + 1
end while
	 */
		for (int i = 1; i < A.length; i++) {
			//uses substitution
			//for loop to check backwards
			int j = i;
			while (j > 0 && A[j - 1] > A[j]) {
				//swap A[j] and A[j-1]
				int temp = A[j];
				A[j] = A[j-1];
				A[j-1] = temp;
				j--;
			}
		}
	}

	public static void main(String[] args) {
	/* CONSOLE TEST
	for (int i = 0; i < arrayBound5.length; i++) {
		System.out.print(arrayBound5[i] + ", ");
	}
	 */
		int bound1 = 10;        //3849 ns       | 1440 ns   | 1030 ns   | 1030 ns   | 1140 ns   | 1050 ns   | 1020 ns   | 1000 ns   | 1030 ns   | 960 ns    |
		int bound2 = 1000;      //5898196 ns    | 3910 ns   | 3450 ns   | 3410 ns   | 3390 ns   | 3400 ns   | 7630 ns   | 4129 ns   | 3450 ns   | 3390 ns   |
		int bound3 = 10000;     //23693613 ns   | 34590 ns  | 33120 ns  | 33070 ns  | 33299 ns  | 48629 ns  | 33109 ns  | 33049 ns  | 33030 ns  | 33360 ns  |
		int bound4 = 100000;    //1663003934 ns | 349304 ns | 329044 ns | 327515 ns | 327334 ns | 327335 ns | 327285 ns | 327255 ns | 327185 ns | 327285 ns |
		int bound5 = 200000;    //6679333709 ns | 696159 ns | 676499 ns | 676320 ns | 678640 ns | 677350 ns | 682939 ns | 677939 ns | 676519 ns | 673779 ns |
		System.out.print("createSequenceRand with " + bound5 + " indexes:\n");
		int[] arrayBound5 = createSequenceRand(bound5);
		for (int i = 0; i < 10; i++) {
			long time1 = java.lang.System.nanoTime();
			insertionSort(arrayBound5);
			long time2 = java.lang.System.nanoTime();
			System.out.print((time2 - time1) + " ns | " );
		}

	}

	public <T extends Comparable>void insertionSort(T[] A){
		for (int j = 2; j > A.length; j++) {
			T s = A[j];
			int i = j - 1;
			while (i > 0 && A[i].compareTo(s) > 0){
				A[i+1] = A[i];
				i--;
			}
			A[i+1] = s;
		}
	}

}
