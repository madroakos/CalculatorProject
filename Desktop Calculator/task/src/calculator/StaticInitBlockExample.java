package calculator;

class MagicNumber {

    private final static int[] NUMBERS;
    private static int next = 0;

    private int number;

    static {
        NUMBERS = new int[] { 1, 3, 7, 15, 31, 63 };
    }

    {
        this.number = NUMBERS[next % NUMBERS.length];
        next++;
    }

    public MagicNumber(int base) {
        this.number += base;
        System.out.println(number);
    }
}