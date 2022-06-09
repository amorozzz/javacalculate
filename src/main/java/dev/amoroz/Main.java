package dev.amoroz;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final int[] DECIMAL = {100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] ROMAN = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String value = reader.readLine( );
            System.out.println(calc(value));
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String calc(String input) throws Exception {
        String regex = "[+\\-*/]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find( ) && input.split(regex).length==2) {
            String operation = matcher.group( );
            String part1 = input.split(regex)[0].trim( );
            String part2 = input.split(regex)[1].trim( );
            boolean isRoman = false;
            int a;
            int b;

            if (getIntFromRoman(part1) != 0
                    && getIntFromRoman(part2) != 0) {
                a = getIntFromRoman(part1);
                b = getIntFromRoman(part2);
                isRoman = true;
            } else {
                a = Integer.parseInt(part1);
                b = Integer.parseInt(part2);
            }

            if (a > 10 || b > 10) {
                throw new Exception( );
            } else {
                int result;

                switch (operation) {
                    case "+":
                        result = a + b;
                        break;

                    case "-":
                        result = a - b;
                        break;

                    case "*":
                        result = a * b;
                        break;

                    case "/":
                        result = a / b;
                        break;

                    default:
                        throw new Exception( );
                }

                if (isRoman) {
                    if (result > 0) {
                        return (getRomanFromInt(result));
                    } else {
                        throw new Exception( );
                    }
                } else {
                    return String.valueOf(result);
                }
            }
        } else {
            throw new Exception( );
        }
    }

    private static int getIntFromRoman(String value) {
        int result = 0;

        for (int i = 0; i < DECIMAL.length; i++ ) {
            while (value.indexOf(ROMAN[i]) == 0) {
                result += DECIMAL[i];
                value = value.substring(ROMAN[i].length());
            }
        }

        return result;
    }

    private static String getRomanFromInt(int num) {
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < DECIMAL.length; i++) {
            while(num >= DECIMAL[i]) {
                num = num - DECIMAL[i];
                result.append(DECIMAL[i]);
            }
        }

        return result.toString();
    }
}
