public class MyTestingClass {
    private final String s;

    private int hash = 0;

    /*
     * Calculates hash combining two with different base values.
     */
    private void calcHash(){
        long pw = 11, pw2 = 23;
        long hash = 0, hash2 = 0;
        for(int i = 0; i < s.length(); i++){
            hash = ((hash*pw) + (s.charAt(i)));
            hash2 = ((hash2*pw2) + (s.charAt(i)));
        }
        this.hash = (int) (hash2) + (int) (hash2-hash);
    }

    public MyTestingClass(String s) {
        this.s = s;
        calcHash();
    }

    @Override
    public int hashCode(){
        return hash;
    }

}
