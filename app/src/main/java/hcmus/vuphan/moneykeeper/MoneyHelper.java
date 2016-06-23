package hcmus.vuphan.moneykeeper;

/**
 * Created by monster on 23/06/2016.
 */
public class MoneyHelper {
    public static String MoneyParser(int money) {
        String sMoney = String.valueOf(money);
        String result = "";
        int rank = sMoney.length() / 3;

        if (rank > 1) {
            for (int i = rank - 1; i >= 1; i--) {
                int spos = rank == i ? 0 : sMoney.length() - 3*(i + 1);
                int epos = sMoney.length() - 3*i;
                result += sMoney.substring(spos, epos) + ".";
            }

            int spos = sMoney.length() - 3;
            int epos = sMoney.length();
            result += sMoney.substring(spos, epos);
        }
        result += " VND";
        return result;
    }
}
