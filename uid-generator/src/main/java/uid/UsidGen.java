package uid;

public class UsidGen {
    //
    public static String getStr36(String prefix, String separator, long number, int formatLength) {
        //
        number = (number < 0 ? -number : number);

        Numeral36 numeral36 = Numeral36.getInstance();

        return String.format("%s%s%s",
                prefix, separator,
                numeral36.getStr36(number, formatLength));
    }

    public static String getStr36(String prefix, long number, int formatLength) {
        //
        number = (number < 0 ? -number : number);

        Numeral36 numeral36 = Numeral36.getInstance();
        return String.format("%s-%s", prefix, numeral36.getStr36(number, formatLength));
    }

    public static String getStr36(String prefix, long number) {
        //
        number = (number < 0 ? -number : number);

        Numeral36 numeral36 = Numeral36.getInstance();
        return String.format("%s-%s", prefix, numeral36.getStr36(number));
    }

    public static String getStr36(long number, int formatLength) {
        //
        number = (number < 0 ? -number : number);

        Numeral36 numeral36 = Numeral36.getInstance();
        return numeral36.getStr36(number, formatLength);
    }

    public static String getStr36(long number) {
        //
        number = (number < 0 ? -number : number);

        Numeral36 numeral36 = Numeral36.getInstance();
        return numeral36.getStr36(number);
    }
}