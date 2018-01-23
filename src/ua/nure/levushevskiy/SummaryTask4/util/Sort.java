package ua.nure.levushevskiy.SummaryTask4.util;

import ua.nure.levushevskiy.SummaryTask4.dto.AccountDTO;
import ua.nure.levushevskiy.SummaryTask4.dto.PaymentDTO;

import java.util.Comparator;
import java.util.List;

/**
 * A class containing a method for sort list.
 */
public class Sort {

    //Comparator для сортировки списка или объектов по id.
    public static Comparator<AccountDTO> AccountIdCompare = new Comparator<AccountDTO>() {
        @Override
        public int compare(AccountDTO e1, AccountDTO e2) {
            return (int)(e1.getIdAccount() - e2.getIdAccount());
        }
    };

    //Comparator для сортировки списка или объектов по amound.
    public static Comparator<AccountDTO> AccountAmoundCompare = new Comparator<AccountDTO>() {
        @Override
        public int compare(AccountDTO e1, AccountDTO e2) {
            if(e1.getAmound() - e2.getAmound()<0)
                return -1;
            else if(e1.getAmound() - e2.getAmound()>0)
                return 1;
            else
                return 0;
        }
    };

    //Comparator для сортировки списка или объектов по name.
    public static Comparator<AccountDTO> AccountNameCompare = new Comparator<AccountDTO>() {
        @Override
        public int compare(AccountDTO e1, AccountDTO e2) {
            return (e1.getAccountNameDTO().getName().compareTo(e2.getAccountNameDTO().getName()));
        }
    };

    //Comparator для сортировки списка или объектов по id.
    public static Comparator<PaymentDTO> PaymentIdCompare = new Comparator<PaymentDTO>() {
        @Override
        public int compare(PaymentDTO e1, PaymentDTO e2) {
            return (int)(e1.getIdPayment() - e2.getIdPayment());
        }
    };

    //Comparator для сортировки списка или объектов по date.
    public static Comparator<PaymentDTO> PaymentDateCompare = new Comparator<PaymentDTO>() {
        @Override
        public int compare(PaymentDTO e1, PaymentDTO e2) {
            return e1.getDatePayment().compareTo(e2.getDatePayment());
        }
    };
}
