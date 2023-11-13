import java.util.Scanner;

public class Main {

    public final static Scanner scanner = new Scanner(System.in);
    public final static YearValidator yearValidator = new YearValidator();

    public static void main(String[] args) throws MyException {

        String year = scanner.next();
        scanner.close();

        if (!yearValidator.isValid(year)) {
            throw new MyException();
        }
        else {
            int startYear = Integer.parseInt(year) - 2002;
            System.out.println(calculationOfTheWithdrawalPercentage(startYear));
        }
    }

//    Расчет процента изъятия
    public static double calculationOfTheWithdrawalPercentage(int startYear){

        double sumOfMoney, percentageOfWithdrawal, startingPercentageOfWithdrawal = 0, realProfitability;

        while (startingPercentageOfWithdrawal <= 100) {
            sumOfMoney = 100;
            percentageOfWithdrawal = startingPercentageOfWithdrawal;
            for (int index = startYear; index < 20; index++) {
                realProfitability = calculationOfNominalProfitability(Constants.MOEX_RATE[index], Constants.MOEX_RATE[index + 1]);
                sumOfMoney -= percentageOfWithdrawal;
                percentageOfWithdrawal *= 1 + (Constants.INFLATION_RATE[index] / 100);
                sumOfMoney *= 1 + realProfitability;
            }
            if (sumOfMoney < 0){
                break;
            }
            startingPercentageOfWithdrawal += 0.5;
        }
        return startingPercentageOfWithdrawal - 0.5;
    }

//    Расчет номинальной доходности
    private static double calculationOfNominalProfitability(double previousRate, double currentRate ){
        return (currentRate - previousRate) / previousRate;
    }
}
