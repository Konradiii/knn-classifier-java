import java.util.*;

public class Klasyfikator {

    public static double distance(double[] a, double[] b) {
        double sum = 0;

        for (int i = 0; i < a.length; i++) {
            sum += Math.pow(a[i] - b[i], 2);
        }

        return Math.sqrt(sum);
    }

    public static String classify(List<DataPoint> train, double[] test, int k) {

        List<Map.Entry<Double, String>> distances = new ArrayList<>();

        for (DataPoint dp : train) {
            double dist = distance(dp.cechy, test);
            distances.add(new AbstractMap.SimpleEntry<>(dist, dp.gatunek));
        }

        distances.sort(Comparator.comparing(Map.Entry::getKey));

        Map<String, Integer> votes = new HashMap<>();

        for (int i = 0; i < k; i++) {
            String label = distances.get(i).getValue();
            votes.put(label, votes.getOrDefault(label, 0) + 1);
        }

        return Collections.max(votes.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public static double evaluate(List<DataPoint> train, List<DataPoint> test, int k) {
        int correct = 0;

        for (DataPoint dp : test) {
            String predicted = classify(train, dp.cechy, k);
            if (predicted.equals(dp.gatunek)) {
                correct++;
            }
        }

        return 100.0 * correct / test.size();
    }
}