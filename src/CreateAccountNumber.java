import java.util.Arrays;
import java.util.Random;

public class CreateAccountNumber extends Source{

    public static void createAccountNumber(String balanceAccount, String currencyCode, String bankCode, int createdAccounts) {

        while(createdAccounts > 0) {

            createdAccounts -= 1;

            int[] accountNumber = new int[20];
            int[] bankCodeAndAccountNumber = new int [23];
            int[] bankCodeAndAccountNumberMultipleIndex = new int[23];
            int checkSum =0;
            int key = 0;

            if(bankCode.charAt(6) == '0' && bankCode.charAt(7) == '0') {
                for(int i = 1; i < 3; i++) {
                    bankCodeAndAccountNumber[i] = bankCode.charAt(i + 3) - '0';
                }
            }else {
                for(int i = 0; i < 3; i++) {
                    bankCodeAndAccountNumber[i] = bankCode.charAt((bankCode.length() - 3) + i) - '0';
                }
            }

            for(int i = 0; i < 5; i++) {
                bankCodeAndAccountNumber[3 + i] = balanceAccount.charAt(i) - '0';
            }

            for(int i = 8; i < 11; i++) {
                bankCodeAndAccountNumber[i] = currencyCode.charAt(i - 8) - '0';
            }

            int min = 1;
            long max = 9999999999L;
            Random random = new Random();
            long randomNumber = random.nextLong() % (max - 1) + max;

            String randomNumberString = Long.toString(randomNumber);

            for(int i =  randomNumberString.length() - 1; i >=0; i--) {
                bankCodeAndAccountNumber[22 + i - randomNumberString.length() + 1] = randomNumberString.charAt(i) - '0';
            }

            for(int i = 0; i < bankCodeAndAccountNumber.length; i++) {
                bankCodeAndAccountNumberMultipleIndex[i] = bankCodeAndAccountNumber[i] * INDEXES[i];
            }

            for(int i = 0; i < bankCodeAndAccountNumberMultipleIndex.length; i++) {
                checkSum += bankCodeAndAccountNumberMultipleIndex[i];
            }

            key = (checkSum % 10) * 3 % 10;

            for(int i = 0; i < 20; i++) {
                if(i == 8) {
                    accountNumber[i] = key;
                }else {
                    accountNumber[i] = bankCodeAndAccountNumber[i + 3];
                }
            }

            System.out.println(Arrays.toString(accountNumber).replaceAll("[\\[\\], ]", ""));
        }
    }
}
