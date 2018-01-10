package ua.nure.levushevskiy.SummaryTask4.dao.api;

import ua.nure.levushevskiy.SummaryTask4.entity.AccountName;

public interface AccountNameDAO {

    /**
     *
     * Gets the name object by account ID.
     *
     * @param accountId     - account ID.
     * @return - accountName object
     */
    AccountName getAccountName(int accountId);

    /**
     *
     * Sets the name object by account ID.
     *
     * @param accountId     - user ID.
     * @param name     - name.
     * @return - accountName object
     */
    AccountName setAccountName(int accountId, String name);
}
