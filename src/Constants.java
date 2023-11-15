import java.util.Scanner;

public class Constants {
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final YearValidator YEAR_VALIDATOR = new YearValidator();
    public static final int START_YEAR = 2002;
    public static final double MAXIMUM_SUM_OF_MONEY = 100;
    public static final double MINIMUM_SUM_OF_MONEY = 0;
    public static final double MINIMUM_WITHDRAWAL_PERCENTAGE = 0;
    public static final double MAXIMUM_WITHDRAWAL_PERCENTAGE = 100;
    public static final double WITHDRAWAL_PERCENTAGE_CHANGE_COEFFICIENT = 0.5;
    public static final int PERCENTAGE_100 = 100;
    public static final double[] MOEX_RATE = {
            417.42,
            673.72,
            722.81,
            1323.32,
            2216.63,
            2472.38,
            810.922,
            1793.24,
            2209.46,
            1835.14,
            1934.43,
            1967.83,
            1828.06,
            2305.50,
            3042.00,
            3015.71,
            3564.05,
            4887.25,
            5567.28,
            6731.43,
            4170.35
    };

    //Инфляция с 2002 по 2022 сверху вниз
    public static final double[] INFLATION_RATE = {
            15.06,
            11.99,
            11.74,
            10.91,
            9.00,
            11.87,
            13.28,
            8.80,
            8.78,
            6.10,
            6.58,
            6.45,
            11.36,
            12.91,
            5.38,
            2.52,
            4.27,
            03.05,
            4.91,
            8.39,
            11.92,
    };

}
