public class Tester {
    public static void main(String[] args) {
        //test1();
        test2();
    }

    static void test1() {
        double minmaxAverageTotal = 0;

        for(int i = 0 ; i < 100 ; ++i) {
            double someValue = 0;
            int iteration = 1200;

            double maxValue = Double.MIN_VALUE;
            double minValue = Double.MAX_VALUE;

            double minmaxAverage = 0;

            for(int j = 0 ; j < iteration ; ++j) {
                double currentValue = (Math.random() * 3);
                someValue += currentValue;

                if(currentValue > maxValue) {
                    maxValue = currentValue;
                }
                if(currentValue < minValue) {
                    minValue = currentValue;
                }
            }

            minmaxAverage = (maxValue+minValue) / 2;
            minmaxAverageTotal += minmaxAverage;

            System.out.println("\nENTRY #"+i);
            System.out.println((someValue/iteration));
            System.out.println("MAX Value: " + maxValue);
            System.out.println("MIN Value: " + minValue);
            System.out.println("MIN/MAX Avg.: " + minmaxAverage);
        }

        System.out.println("\n\n\n");
        System.out.println("Total Avg. of MIN/MAX Avg.: " + minmaxAverageTotal/100);
    }

    static void test2() {

        int iterations = 100;

        int failCount = 0;

        double Y = 0.015;

        for(int i = 0 ; i < iterations ; ++i) {
            double X = 3 * Math.random();

            if(X < Y) {
                ++failCount;
            }
        }

        double failureRate = (double) failCount / iterations;

        System.out.println("FAILURE RATE: " + failureRate);
    }
}
