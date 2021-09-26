public class FinanceCalculator {
  private static int  MONTHS_IN_YEAR = 12;

  public static double calculateFutureValue(double monthlyPayment, double rate, int years){
    int months = years * MONTHS_IN_YEAR;
    double interestRate = (1 + rate / 100);
    double presentValue = months * monthlyPayment;
    double futureValue = presentValue * (Math.pow(interestRate, months));
    return futureValue;
  }
}
