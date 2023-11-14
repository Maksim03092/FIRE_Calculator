import java.util.Scanner;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);
    private final static YearValidator yearValidator = new YearValidator();

    public static void main(String[] args) throws MyException {
        String year = scanner.next();
        scanner.close();

        if (!yearValidator.isValid(year)) {
            throw new MyException();
        } else {
            int startYear = Integer.parseInt(year) - 2002;
            System.out.println(findingTheMaximizedWithdrawalPercentage(startYear));
        }

    }

//    Поиск максимизированного процента изъятия
public static double findingTheMaximizedWithdrawalPercentage (int startYear){

    double sumOfMoney, percentageOfWithdrawal = 0;

    while (percentageOfWithdrawal <= 100) {
        sumOfMoney =  calculationOfTheRemainingBalance(startYear, percentageOfWithdrawal);

        if (sumOfMoney < 0){
            break;
        }

        percentageOfWithdrawal += 0.5;
    }

    return percentageOfWithdrawal - 0.5;
}

//    Расчет остаточной суммы при взятии процента(percentageOfWithdrawal)
private static double calculationOfTheRemainingBalance(int startYear, double percentageOfWithdrawal){
    double realProfitability, sumOfMoney = 100;

    for (int index = startYear; index < 20; index++) {
        realProfitability = calculationOfNominalProfitability(Constants.MOEX_RATE[index],
                Constants.MOEX_RATE[index + 1]);
        sumOfMoney -= percentageOfWithdrawal;
        percentageOfWithdrawal *= 1 + (Constants.INFLATION_RATE[index] / 100);
        sumOfMoney *= 1 + realProfitability;
    }

    return sumOfMoney;
}

//    Расчет номинальной доходности
private static double calculationOfNominalProfitability(double previousRate, double currentRate ){
    return (currentRate - previousRate) / previousRate;
}

}
