public class MyTestingClass {
    private final String s;

    private int hash = 0;

    /*
     * Calculates hash combining two hashes with different base values and modules.
     */
    private void calcHash(){
        long m = (int)1e9 + 9, m2 = 998244353;
        long pw = 41, pw2 = 53;
        long hash = 0, hash2 = 0;
        for(int i = 0; i < s.length(); i++){
            hash = ((hash*pw)%m + (s.charAt(i)))%m;
            hash2 = ((hash2*pw2)%m2 + (s.charAt(i)))%m2;
        }
        this.hash = ((int) (hash)
                + (int) (hash2));
    }
    /*
     * Constructor stores the string,
     * and calls hashing method,
     * so that the hash calculated only once.
     */
    public MyTestingClass(String s) {
        this.s = s;
        calcHash();
    }

    @Override
    public int hashCode(){
        return hash;
    }

}
