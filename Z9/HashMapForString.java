public class OpenHashMapForStrings {

    public final int m;
    String[] T;
    String deleted = "delete flag";

    OpenHashMapForStrings(int m) {
        this.m = m;
        T = new String[m-1];
    }

    public int hashMap1(String a) {
        int k = 0;
        for(int i = 0; i < a.length(); i++) {
            k += a.charAt(i);
        }
        return k % m;
    }

    public int hashMap2(String a) {
        int k = 0;
        for(int i = 0; i < a.length(); i++) {
            k += a.charAt(i);
        }
        return (k % (m-1));
    }

    public int linH(String a, int i) {
        return ((hashMap1(a) + i) % m);
    }

    //b.
    public int quadH(String a, int i) {
        return ((int)(hashMap1(a) + .5*i + .5*(i*i)) % m);
    }

    public int dblH(String a, int i) {
        return ((hashMap1(a) + i* hashMap2(a)) % m);
    }

    /**
     * 1a
     * @param a
     * @return
     */
    public int linearInsert(String a) {
        for(int i = 0; i < m; i++) {
            int j = linH(a, i);
            if(T[j] == null || T[j] == deleted) {
                T[j] = a;
                System.out.println("passed");
                return j;
            }
        }
        System.out.println("error");
        return -1;
    }

    public int linearSearch(String a) {
        int i = 0;
        int j = linH(a, i);
        while(!(T[j] == null) && !(i == m)) {
            j = linH(a, i);
            if(T[j] == a) {
                return j;
            }
            i++;
        }
        return -1;
    }

    public void linDelete(String a) {
        int j = linearSearch(a);
        T[j] = deleted;
    }

    /**
     * 1b
     * @param a
     * @return
     */
    public int quadInsert(String a) {
        for(int i = 0; i < m; i++) {
            int j = quadH(a, i);
            if(T[j] == null || T[j] == deleted) {
                T[j] = a;
                System.out.println("passed");
                return j;
            }
        }
        System.out.println("error");
        return -1;
    }

    public int quadSearch(String a) {
        int i = 0;
        int j = quadH(a, i);
        while(!(T[j] == null) && !(i == m)) {
            j = quadH(a, i);
            if(T[j] == a) {
                return j;
            }
            i++;
        }
        return -1;
    }

    public void quadDelete(String a) {
        int j = quadSearch(a);
        T[j] = deleted;
    }

    /**
     * 1c
     * @param a
     * @return
     */
    public int dblInsert(String a) {
        for(int i = 0; i < m; i++) {
            int j = dblH(a, i);
            if(T[j] == null || T[j] == deleted) {
                T[j] = a;
                System.out.println("passed");
                return j;
            }
        }
        System.out.println("failed");
        return -1;
    }

    public int dblSearch(String a) {
        int i = 0;
        int j = dblH(a, i);
        while(!(T[j] == null) && !(i == m)) {
            j = dblH(a, i);
            if(T[j] == a) {
                return j;
            }
            i++;
        }
        return -1;
    }

    public void dblDelete(String a) {
        int j = dblSearch(a);
        T[j] = deleted;
    }
}
