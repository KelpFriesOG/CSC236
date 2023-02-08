package workshop;

public class HexToDec2 {

    public static void main(String[] args) {

        try {
            System.out.println(parseHex("A5"));
            System.out.println(parseHex("FAA"));
            // System.out.println(parseHex("T10"));
            // Comment the line above to not get an exception!
            System.out.println(parseHex("ABC"));
            System.out.println(parseHex("10A"));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

    }

    public static int parseHex(String hexStr) throws NumberFormatException {

        int sum = 0;
        int power = 0;

        for (int index = hexStr.length() - 1; index >= 0; index--) {
            sum += convertHexToDec(hexStr.charAt(index)) * Math.pow(16, power);
            power++;
        }

        return sum;

    }

    public static int convertHexToDec(char ch) throws NumberFormatException {

        if (Character.isDigit(ch)) {
            return Character.getNumericValue(ch);
        } else if (ch == 'A' || ch == 'a') {
            return 10;
        } else if (ch == 'B' || ch == 'b') {
            return 11;
        } else if (ch == 'C' || ch == 'c') {
            return 12;
        } else if (ch == 'D' || ch == 'd') {
            return 13;
        } else if (ch == 'E' || ch == 'e') {
            return 14;
        } else if (ch == 'F' || ch == 'f') {
            return 15;
        } else {
            throw new NumberFormatException("Invalid hex character!");
        }

    }

}
