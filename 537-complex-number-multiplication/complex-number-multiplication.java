class Solution {
    public String complexNumberMultiply(String num1, String num2) {
        String[] a = num1.split("\\+");
        String[] b = num2.split("\\+");
        int aReal = Integer.parseInt(a[0]);
        int aImag = Integer.parseInt(a[1].replace("i", ""));
        int bReal = Integer.parseInt(b[0]);
        int bImag = Integer.parseInt(b[1].replace("i", ""));
        int real = (aReal * bReal) - (aImag * bImag);
        int imag = (aReal * bImag) + (aImag * bReal);
        return real + "+" + imag + "i";
    }
}