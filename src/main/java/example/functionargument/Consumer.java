package example.functionargument;

public class Consumer {
    public void exec() {
        int sum = sum2int(1,2,(a, b) -> a + b);
        int mul = multiply(2, (a) -> a*3);

        System.out.println(sum + " " + mul);
    }

    private int sum2int(int a, int b, TwoIntArgFunc twoIntArgFunc) {
        return twoIntArgFunc.exec(a,b);
    }

    private int multiply(int a, OneIntArgFunc oneIntArgFunc) {
        return oneIntArgFunc.exec(a);
    }
}
