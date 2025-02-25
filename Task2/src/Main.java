public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // make testcase
        int[] coins1 = {1, 2, 3};
        int sum1 = 4;

        // execute
        long startTime = System.nanoTime();
        System.out.println("answer : " + solution.execute(coins1, sum1));
        long endTime = System.nanoTime();
        System.out.println("Execution Time: " + (endTime - startTime) / 1_000_000.0 + " ms");

        // make testcase
        int[] coins2 = {2, 5, 3, 6};
        int sum2 = 10;

        // execute
        startTime = System.nanoTime();
        System.out.println("answer2 : " + solution.execute(coins2, sum2));
        endTime = System.nanoTime();
        System.out.println("Execution Time: " + (endTime - startTime) / 1_000_000.0 + " ms");
    }
}