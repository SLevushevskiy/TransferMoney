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
     * @param debit - debit.
     * @return - true (if status was updated).
     */
    boolean debitAccountAmound(int accountId, double debit);

    /**
     * Updates the account credit.
     *
     * @param accountId     - account ID.
     * @param credit - credit.
     * @return - true (if status was updated).
     */
    boolean creditAccountAmound(int accountId, double credit);
}
