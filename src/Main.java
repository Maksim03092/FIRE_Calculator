public class Main {
    public static void main(String[] args) throws WrongYearException {
        String year = Constants.SCANNER.next();
        Constants.SCANNER.close();

        if (!Constants.YEAR_VALIDATOR.isValid(year)) {
            throw new WrongYearException();
        }
        int yearOfTheBeginningOfLifeOnInterest = Integer.parseInt(year) - Constants.START_YEAR;
        System.out.println(findingTheMaximizedWithdrawalPercentage(yearOfTheBeginningOfLifeOnInterest));
    }

    /**
     * Поиск максимизированного процента изъятия
     * @param yearOfTheBeginningOfLifeOnInterest - год начала жизни на проценты
     * @return возвращает максимизированный процент изъятия
     */
    public static double findingTheMaximizedWithdrawalPercentage(int yearOfTheBeginningOfLifeOnInterest) {
        double sumOfMoney, percentageOfWithdrawal = Constants.MINIMUM_WITHDRAWAL_PERCENTAGE;

        while (percentageOfWithdrawal <= Constants.MAXIMUM_WITHDRAWAL_PERCENTAGE) {
            sumOfMoney = calculationOfTheRemainingBalance(yearOfTheBeginningOfLifeOnInterest, percentageOfWithdrawal);
            if (sumOfMoney < Constants.MINIMUM_SUM_OF_MONEY) {
                break;
            }
            percentageOfWithdrawal += Constants.WITHDRAWAL_PERCENTAGE_CHANGE_COEFFICIENT;
        }

        return percentageOfWithdrawal - Constants.WITHDRAWAL_PERCENTAGE_CHANGE_COEFFICIENT;
    }

    /**
     * Расчет остаточной суммы при взятии определённого процента
     * @param yearOfTheBeginningOfLifeOnInterest - год начала жизни на проценты
     * @param percentageOfWithdrawal - процент изъятия
     * @return возвращает остаточную сумму
     */
    private static double calculationOfTheRemainingBalance(int yearOfTheBeginningOfLifeOnInterest,
                                                           double percentageOfWithdrawal) {
        double realProfitability, sumOfMoney = Constants.MAXIMUM_SUM_OF_MONEY;

        for (int index = yearOfTheBeginningOfLifeOnInterest; index < Constants.MOEX_RATE .length - 1; index++) {
            realProfitability = calculationOfNominalProfitability(Constants.MOEX_RATE[index],
                    Constants.MOEX_RATE[index + 1]);
            sumOfMoney -= percentageOfWithdrawal;
            percentageOfWithdrawal *= 1 + calculationOfTheInflationCoefficient(index);
            sumOfMoney *= 1 + realProfitability;
        }

        return sumOfMoney;
    }

    /**
     * Расчёт коэффициента инфляции
     * @param index год
     * @return возвращает коэффициент инфляции
     */
    private static double calculationOfTheInflationCoefficient(int index){
        return Constants.INFLATION_RATE[index] / Constants.PERCENTAGE_100;
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
