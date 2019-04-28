class InsertionSort {
public static int[] createSequenceInt(int n) {
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
		int random = (int) Math.random();
		array[i] = random;
	}
	return array;
}
public static int[] createSequenceAlt(int n) {
	int[] array = new int[n];
	int alt = 0;
	for(int i = 0; i < array.length; i++) {
		if (alt == 0 || alt == 2) {
		alt = 1;
		} else alt = 2;
		array[i] = alt;
	}
	return array;
}
public static void insertionSort(int[] A) {
	for(int i = 0; i < A.length; i++) {
		//uses substitution
		int temp = A[i];
		//for loop to check backwards
		for (int j = i; j > 0; j--) {
			if(A[j] < A[i]) {
				//replacement
				for(int I = i;;I++) {
				A[I] = A[j];
				A[I+1] = A[I];
				}
			}
		}
	}
}


//psvm Aufgabe
public static void main(String[] args) {

}

}
