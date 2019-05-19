        public class auf1 {

    // Java code for linearly searching x in arr[]. If x
// is present then return its location, otherwise
// return -1
    public static int linSearch(int arr[], int x)
    {
        int n = arr.length;
        for(int i = 0; i < n; i++)
        {
            if(arr[i] == x)
                return i;
        }
        return -1;
    }

    public static int binSearch(int arr[], int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;

            // If the element is present at the middle itself
            if (arr[mid] == x)
                return mid;

            // If element is smaller than mid, then it can only be present in left subarray
            if (arr[mid] > x)
                return binSearch(arr, l, mid - 1, x);

            // Else the element can only be present in right subarray
            return binSearch(arr, mid + 1, r, x);
        }

        // We reach here when element is not present in array
        return -1;
    }

    public static int binSearchNew(int arr[], int l, int r, int x) {
        if ( r >= l) {
            // Find the 2 middle elements
            int mid1 = l + (r-l)/3;
            int mid2 = r - ((r-l)*2)/3;

            // If the element is present at either middle element
            if (arr[mid1] == x)
                return mid1;
            if (arr[mid2] == x)
                return mid2;

            // If the element is smaller than 1st mid, then it can only be present in the respective leftmost subarray
            if (x < arr[mid1])
                return binSearchNew(arr, l, mid1 - 1, x);
            // Else if the element is bigger than 2nd mid, then it can only be present in the rightmost subarray
            if (arr[mid2] < x)
                return binSearchNew(arr, mid2 + 1, r, x);

            // Else the element can only be present in the middle subarray
            return binSearchNew(arr, mid1 + 1, mid2 - 1, x);
        }
        return -1;
    }

    /**
     * hilfs methode aus blatt 1 fuer auf1d
     * @param n
     * @return
     */
    public static int[] createSequenceInc(int n) {
        int[] array = new int[n];
        for(int i = 0; i < array.length; i++) {
            array[i] = i+1;
        }
        return array;
    }

    public static void main(String args[])
    {
        /**
         * TEST CONSOLE, auskommentiert um Suchsmethode zu testen
         */
        /*
        int arr[] = {1, 2, 9, 12, 22};
        int x = 12;

        int resultLin = linSearch(arr, x);
        if(resultLin == -1) System.out.print("Element not found");
        else System.out.print("Element found at index " + resultLin + "\n");

        int resultBin = binSearch(arr, 0, arr.length - 1, x);
        if (resultBin == -1) System.out.println("Element not found");
        else System.out.println("Element found at index " + resultBin);

        int resultBinNew = binSearchNew(arr, 0, arr.length - 1, x);
        if (resultBinNew == -1) System.out.println("Element not found");
        else System.out.println("Element found at index " + resultBinNew);
         */

        /* //Heap space causing OutOfMemoryError exception
        int[] array4 = createSequenceInc(685154321);
        int random4 = (int) (Math.random()*685154321);
        System.out.println(random4);
         */
        int invalid = -5;
        int sample1 = 100000;
        int sample2 = 1000000;
        int sample3 = 100000000;
        int sample4 = 685154321;
        timeRecord(sample1,1);
        timeRecord(sample2,1);
        timeRecord(sample3,1);
        timeRecord(sample4,1);
        timeRecord(sample1,2);
        timeRecord(sample2,2);
        timeRecord(sample3,2);
        timeRecord(sample4,2);
        timeRecord(sample1,3);
        timeRecord(sample2,3);
        timeRecord(sample3,3);
        timeRecord(sample4,3);
        // 1000 linSearch searches of 100000 elements took 11849012 ns
        // 1000 linSearch searches of 1000000 elements took 102661422 ns
        // 1000 linSearch searches of 100000000 elements took 15797247731 ns
        // 1000 linSearch searches of 685154321 elements took 174881226454 ns
        // 1000 binSearch searches of 100000 elements took 548358 ns
        // 1000 binSearch searches of 1000000 elements took 160769 ns
        // 1000 binSearch searches of 100000000 elements took 158959 ns
        // 1000 binSearch searches of 685154321 elements took 134820 ns
        // 1000 binSearchNew searches of 100000 elements took 668408 ns
        // 1000 binSearchNew searches of 1000000 elements took 354519 ns
        // 1000 binSearchNew searches of 100000000 elements took 176729 ns
        // 1000 binSearchNew searches of 685154321 elements took 162719 ns

        //invalid number -5
        // worst case (-5)
        //1000 linSearch searches of 100000 elements took 24058636 ns
        //1000 linSearch searches of 1000000 elements took 208093409 ns
        //1000 linSearch searches of 100000000 elements took 32000429306 ns
        //1000 linSearch searches of 685154321 elements took 223288971604 ns
        //1000 binSearch searches of 100000 elements took 484991 ns
        //1000 binSearch searches of 1000000 elements took 174920 ns
        //1000 binSearch searches of 100000000 elements took 155560 ns
        //1000 binSearch searches of 685154321 elements took 124240 ns
        //1000 binSearchNew searches of 100000 elements took 609761 ns
        //1000 binSearchNew searches of 1000000 elements took 210360 ns
        //1000 binSearchNew searches of 100000000 elements took 128130 ns
        //1000 binSearchNew searches of 685154321 elements took 117580 ns

    }

    /**
     * hilft methode zum Lesekeit von main-Methode
     * @param sampleRate
     * @param engine
     */
    public static void timeRecord(int sampleRate, int engine) {
        int[] array1 = createSequenceInc(sampleRate);    //100k
        //int random1 = (int) (Math.random()*sampleRate);
        int random1 = -5;
        String engineStr = "null";
        if (engine == 1) engineStr = " linSearch ";
        if (engine == 2) engineStr = " binSearch ";
        if (engine == 3) engineStr = " binSearchNew ";

        long time1 = java.lang.System.nanoTime();
        //search for 1k times
        for (int i = 0; i <= 1000; i++) {
            if (engine == 1) { //linSearch
                    linSearch(array1,random1); }
            else if (engine == 2) { //binSearch
                binSearch(array1,0,array1.length-1,random1); }
            else if (engine == 3) { //binSearchNew
                binSearchNew(array1,0,array1.length-1,random1); }
        }
        long time2 = java.lang.System.nanoTime();
        System.out.print("1000" + engineStr + "searches of " + sampleRate + " elements took " + (time2 - time1) + " ns \n");
    }
}
