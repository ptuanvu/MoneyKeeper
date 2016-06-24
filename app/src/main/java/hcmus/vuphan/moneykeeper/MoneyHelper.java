package hcmus.vuphan.moneykeeper;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import hcmus.vuphan.moneykeeper.model.Catalog;
import hcmus.vuphan.moneykeeper.model.ChiTieuThang;
import hcmus.vuphan.moneykeeper.model.Giaodich;
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
        boolean amTien = false;
        if (money < 0) {
            sMoney = sMoney.substring(1);
            amTien = true;
        }
        String result = "";
        int rank = sMoney.length() / 3;

        if (sMoney.length() > 3) {
            if (sMoney.length() % 2 == 0) {
                rank--;
            }
            for (int i = rank ; i >= 1; i--) {
                int spos = rank == i ? 0 : sMoney.length() - 3*(i + 1);
                int epos = sMoney.length() - 3*i;
                result += sMoney.substring(spos, epos) + ".";
            }

            int spos = sMoney.length() - 3;
            int epos = sMoney.length();
            result += sMoney.substring(spos, epos);
        } else {
            result = sMoney;
        }

        if (amTien) {
            result = "-" + result;
        }
        return result;
    }

    public static ChiTieuThang GetChiTieuThangByMonth(int month) {
        List<ChiTieuThang> chiTieuThangs = ChiTieuThang.listAll(ChiTieuThang.class);
        ChiTieuThang curCTT = null;
        for (ChiTieuThang chiTieuThang :
                chiTieuThangs) {
            if (chiTieuThang.getThoiGian().getMonth() == month)
            {
                curCTT = chiTieuThang;
                break;
            }
        }

        return curCTT;
    }

    public static List<Giaodich> GetGiaoDichByChiTieuThang(Long id) {
        List<Giaodich> result = Giaodich.find(Giaodich.class, "idthang = ?", String.valueOf(id));
        return result;
    }


    public static int FindOnList(List<Catalog> catalogs, String ID) {
        for (int i = 0 ; i < catalogs.size(); i ++) {
            if (catalogs.get(i).getId() == Long.valueOf(ID))
                return i;
        }

        return -1;
    }

    public static String MoneyParser(int money) {
        String sMoney = String.valueOf(money);
        boolean amTien = false;
        if (money < 0) {
            sMoney = sMoney.substring(1);
            amTien = true;
        }
        String result = "";
        int rank = sMoney.length() / 3;

        if (sMoney.length() > 3) {
            if (sMoney.length() % 2 == 0) {
                rank--;
            }
            for (int i = rank ; i >= 1; i--) {
                int spos = rank == i ? 0 : sMoney.length() - 3*(i + 1);
                int epos = sMoney.length() - 3*i;
                result += sMoney.substring(spos, epos) + ".";
            }

            int spos = sMoney.length() - 3;
            int epos = sMoney.length();
            result += sMoney.substring(spos, epos);
        } else {
            result = sMoney;
        }
        result += " VND";

        if (amTien) {
            result = "-" + result;
        }
        return result;
    }

    public static Wallet GetCurWallet() {
        List<Wallet> wallets = Wallet.listAll(Wallet.class);
        return wallets.get(0);
    }
}
