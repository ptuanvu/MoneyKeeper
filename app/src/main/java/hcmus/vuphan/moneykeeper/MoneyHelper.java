package hcmus.vuphan.moneykeeper;

import java.util.Calendar;
import java.util.List;

import hcmus.vuphan.moneykeeper.model.Wallet;

/**
 * Created by monster on 23/06/2016.
 */
public class MoneyHelper {

    public static int GetCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime().getMonth();
    }

    public static String MoneyParserWithoutVND(int money) {
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
        return result;
    }

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

    public static Wallet GetCurWallet() {
        List<Wallet> wallets = Wallet.listAll(Wallet.class);
        return wallets.get(0);
    }
}
