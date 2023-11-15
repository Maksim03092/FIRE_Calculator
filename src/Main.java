import java.util.Scanner;

public class Main {
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static YearValidator YEAR_VALIDATOR = new YearValidator();
    private static final int START_YEAR = 2002;
    private static final double MAXIMUM_SUM_OF_MONEY = 100;
    private static final double MINIMUM_SUM_OF_MONEY = 0;
    private static final double MINIMUM_WITHDRAWAL_PERCENTAGE = 0;
    private static final double MAXIMUM_WITHDRAWAL_PERCENTAGE = 100;
    private static final double WITHDRAWAL_PERCENTAGE_CHANGE_COEFFICIENT = 0.5;

    public static void main(String[] args) throws WrongYearException {
        String year = SCANNER.next();
        SCANNER.close();

        if (!YEAR_VALIDATOR.isValid(year)) {
            throw new WrongYearException();
        }
        int yearOfTheBeginningOfLifeOnInterest = Integer.parseInt(year) - START_YEAR;
        System.out.println(findingTheMaximizedWithdrawalPercentage(yearOfTheBeginningOfLifeOnInterest));
    }

    /**
     * Поиск максимизированного процента изъятия
     * @param yearOfTheBeginningOfLifeOnInterest - год начала жизни на проценты
     * @return возвращает максимизированный процент изъятия
     */
    public static double findingTheMaximizedWithdrawalPercentage(int yearOfTheBeginningOfLifeOnInterest) {
        double sumOfMoney, percentageOfWithdrawal = MINIMUM_WITHDRAWAL_PERCENTAGE;

        while (percentageOfWithdrawal <= MAXIMUM_WITHDRAWAL_PERCENTAGE) {
            sumOfMoney = calculationOfTheRemainingBalance(yearOfTheBeginningOfLifeOnInterest, percentageOfWithdrawal);
            if (sumOfMoney < MINIMUM_SUM_OF_MONEY) {
                break;
            }
            percentageOfWithdrawal += WITHDRAWAL_PERCENTAGE_CHANGE_COEFFICIENT;
        }

        return percentageOfWithdrawal - WITHDRAWAL_PERCENTAGE_CHANGE_COEFFICIENT;
    }

    /**
     * Расчет остаточной суммы при взятии определённого процента
     * @param yearOfTheBeginningOfLifeOnInterest - год начала жизни на проценты
     * @param percentageOfWithdrawal - процент изъятия
     * @return возвращает остаточную сумму
     */
    private static double calculationOfTheRemainingBalance(int yearOfTheBeginningOfLifeOnInterest,
                                                           double percentageOfWithdrawal) {
        double realProfitability, sumOfMoney = MAXIMUM_SUM_OF_MONEY;

        for (int index = yearOfTheBeginningOfLifeOnInterest; index < 20; index++) {
            realProfitability = calculationOfNominalProfitability(Constants.MOEX_RATE[index],
                                                                  Constants.MOEX_RATE[index + 1]);
            sumOfMoney -= percentageOfWithdrawal;
            percentageOfWithdrawal *= 1 + (Constants.INFLATION_RATE[index] / 100);
            sumOfMoney *= 1 + realProfitability;
        }

        return sumOfMoney;
    }

    /**
     * Расчет номинальной доходности
     * @param previousRate - предыдущий тариф
     * @param currentRate - текущий тариф
     * @return возвращает номинальной доходности
     */
    private static double calculationOfNominalProfitability(double previousRate, double currentRate) {
        return (currentRate - previousRate) / previousRate;
    }

}
