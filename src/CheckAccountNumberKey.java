public class CheckAccountNumberKey extends Source{

    public static String checkAccountNumberKey(String accountNumber, String bankCode) {

        int[] bankCodeAndAccountNumber = new int [23];
        int[] bankCodeAndAccountNumberMultipleIndex = new int[23];
        int checkSum =0;

        if(bankCode.charAt(6) == '0' && bankCode.charAt(7) == '0') {
            for(int i = 1; i < 3; i++) {
                bankCodeAndAccountNumber[i] = bankCode.charAt(i + 3) - '0';
            }
        }else {
            for(int i = 0; i < 3; i++) {
                bankCodeAndAccountNumber[i] = bankCode.charAt((bankCode.length() - 3) + i) - '0';
            }
        }

        for(int i = 0; i < accountNumber.length(); i++) {
            bankCodeAndAccountNumber[i + 3] = accountNumber.charAt(i) - '0';
        }

        for(int i = 0; i < bankCodeAndAccountNumber.length; i++) {
            bankCodeAndAccountNumberMultipleIndex[i] = bankCodeAndAccountNumber[i] * INDEXES[i];
        }

        for(int i = 0; i < bankCodeAndAccountNumberMultipleIndex.length; i++) {
            checkSum += bankCodeAndAccountNumberMultipleIndex[i];
        }

        if(checkSum % 10 == 0) {
            return "The account number is correct";
        } else {
            return "The account number is incorrect";
        }
    }
}
