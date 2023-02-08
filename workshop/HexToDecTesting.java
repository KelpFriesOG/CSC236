package workshop;

public class HexToDecTesting {

    public static void main(String[] args) {

        System.out.println(hex2Dec("BAD"));
        System.out.println(hex2Dec("BAC98"));
        System.out.println(hex2Dec("BabA73"));

    }

    public static int hex2Dec(String hexString) {

        int power = 0;
        int sum = 0;

        for (int index = hexString.length() - 1; index >= 0; index--) {

            char hexDigit = hexString.charAt(index);

            if (Character.isDigit(hexDigit)) {
                sum += Character.getNumericValue(hexDigit) * Math.pow(16, power);
            } else {
                if (hexDigit == 'A' || hexDigit == 'a') {
                    sum += 10 * Math.pow(16, power);
                } else if (hexDigit == 'B' || hexDigit == 'b') {
                    sum += 11 * Math.pow(16, power);
                } else if (hexDigit == 'C' || hexDigit == 'c') {
                    sum += 12 * Math.pow(16, power);
                } else if (hexDigit == 'D' || hexDigit == 'd') {
                    sum += 13 * Math.pow(16, power);
                } else if (hexDigit == 'E' || hexDigit == 'e') {
                    sum += 14 * Math.pow(16, power);
                } else if (hexDigit == 'F' || hexDigit == 'f') {
                    sum += 15 * Math.pow(16, power);
                } else {
                    System.err.println("Invalid character found in string!");
                    return -1;
                }
            }

            power++;

        }

        return sum;

    }

}
