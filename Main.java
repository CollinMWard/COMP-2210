import java.math.BigInteger;
import java.util.Scanner;
public class Main {
   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      System.out.print("Enter the base: ");
      BigInteger base = scanner.nextBigInteger();
      System.out.print("Enter the exponent: ");
      BigInteger exponent = scanner.nextBigInteger();
      System.out.print("Enter the modulus: ");
      BigInteger modulus = scanner.nextBigInteger();
      BigInteger result = base.modPow(exponent, modulus);
      System.out.println(base + "^" + exponent + " mod " + modulus + " = " +
         result);
      scanner.close();
   }
}
