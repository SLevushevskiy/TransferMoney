package ua.nure.levushevskiy.SummaryTask4.dao.api;

import ua.nure.levushevskiy.SummaryTask4.entity.Account;

/**
 * An interface that contains methods for interacting with the database.
 * Namely with the table Account.
 */
public interface AccountDAO extends DAO<Account, Integer> {

    /**
     * Updates the account status.
     *
     * @param accountId     - account ID.
     * @param accountStatus - status.
     * @return - true (if status was updated).
     */
    boolean updateAccountStatus(int accountId, String accountStatus);

    /**
     * Updates the account amound.
     *
     * @param accountId     - account ID.
     * @param paymentTotal - payment total.
     * @return - true (if status was updated).
     */
    boolean changeAccountAmound(int accountId, double paymentTotal);

}
