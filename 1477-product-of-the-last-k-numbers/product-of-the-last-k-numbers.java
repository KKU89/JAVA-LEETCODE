class ProductOfNumbers {
    private java.util.ArrayList<Integer> prefix;

    public ProductOfNumbers() {
        prefix = new java.util.ArrayList<>();
        prefix.add(1);
    }
    
    public void add(int num) {
        if (num == 0) {
            prefix = new java.util.ArrayList<>();
            prefix.add(1);
        } else {
            prefix.add(prefix.get(prefix.size() - 1) * num);
        }
    }
    
    public int getProduct(int k) {
        int n = prefix.size();
        if (k > n - 1) return 0;
        return prefix.get(n - 1) / prefix.get(n - 1 - k);
    }
}