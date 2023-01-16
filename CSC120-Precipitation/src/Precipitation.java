import java.util.*;
import java.io.*;

public class Precipitation {
    public static final int FIRST_YEAR = 1932;
    public static final int LAST_YEAR = 2013;

    private static double[][][] read() throws FileNotFoundException {

        // set up a Scanner to read
        Scanner scanner = new Scanner(new File("precipitation-data.txt"));
        // set up a 3-d array to read data in
        double[][][] datas = new double[LAST_YEAR - FIRST_YEAR + 1][12][31];
        // read data using a triple loop
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            String[] infos = line.split(" +");
            int year = Integer.parseInt(infos[0]) - FIRST_YEAR;
            int month = Integer.parseInt(infos[1]) - 1;
            double[] dateData = datas[year][month];
            for (int i = 2; i < infos.length; i++) {
                dateData[i - 2] = Double.parseDouble(infos[i]);
            }
        }

        // close the scanner
        scanner.close();
        // return data;
        return datas;
    }

    public static double getDayData(double[][][] data, int year, int month, int day) {
        try {
            return data[year - FIRST_YEAR][month - 1][day - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return -1;
        }
    }

    public static void stat(double[][][] data, int y1, int y2, int m1, int m2, int d1, int d2) {
        int count = 0;
        double sum = 0, max = -1, min = 100000, value;
        // triple loop for updating
        for (int year = y1; year <= y2; year++) {
            for (int month = m1; month <= m2; month++) {
                for (int day = d1; day <= d2; day++) {
                    value = getDayData(data, year, month, day);
                    if (value >= 0) {
                        count++;
                        sum += value;
                        if (value > max) {
                            max = value;
                        }
                        if (value < min) {
                            min = value;
                        }
                    }
                }
            }
        }
        // report the stat
        System.out.println("No.      " + count);
        System.out.printf( "Ave.     %.3f\n", sum / count);
        System.out.printf( "Max.     %.3f\n", max);
        System.out.printf( "Min.     %.3f\n", +min);
    }

    public static void main(String[] args) throws FileNotFoundException {
        double[][][] data = read();
        int month, day, year;
        Scanner keyboard = new Scanner(System.in);
        int selection = 1;
        do {
            System.out.print(
                    "1. Day value\n" +
                            "2. Month stat\n" +
                            "3. Annual stat\n" +
                            "4. All data stat\n" +
                            "0. Quit\n" +
                            "Enter your choice: ");
            selection = keyboard.nextInt();

            switch (selection) {
                case 1:
                    System.out.print("Enter year month day: ");
                    year = keyboard.nextInt();
                    month = keyboard.nextInt();
                    day = keyboard.nextInt();
                    stat(data, year, year, month, month, day, day);
                    break;
                case 2:
                    System.out.print("Enter year month: ");
                    year = keyboard.nextInt();
                    month = keyboard.nextInt();
                    stat(data, year, year, month, month, 1, 31);
                    break;
                case 3:
                    System.out.print("Enter year: ");
                    year = keyboard.nextInt();
                    stat(data, year, year, 1, 12, 1, 31);
                    break;
                case 4:
                    stat(data, FIRST_YEAR, LAST_YEAR, 1, 12, 1, 31);
                    break;
                case 0:
                    System.out.println("%");
            }
        } while (selection != 0);
    }
}
