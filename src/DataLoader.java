
import java.io.*;
import java.util.*;

    public class DataLoader {

        public static List<DataPoint> loadData(String filename) throws IOException {
            List<DataPoint> data = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(filename));

            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                double[] cechy = new double[4];
                for (int i = 0; i < 4; i++) {
                    cechy[i] = Double.parseDouble(parts[i]);
                }

                String gatunek = parts[4];
                data.add(new DataPoint(cechy, gatunek));
            }

            br.close();
            return data;
        }

        public static void splitData(List<DataPoint> data,
                                     List<DataPoint> train,
                                     List<DataPoint> test,
                                     double ratio) {

            Collections.shuffle(data);
            int splitIndex = (int)(data.size() * ratio);

            for (int i = 0; i < data.size(); i++) {
                if (i < splitIndex) train.add(data.get(i));
                else test.add(data.get(i));
            }
        }
    }

