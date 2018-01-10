package ua.nure.levushevskiy.SummaryTask4.dao.api;

import ua.nure.levushevskiy.SummaryTask4.entity.AccountStatus;

public interface AccountStatusDAO {
    /**
     *
     * Gets the status object by account ID.
     *
     * @param accountId     - account ID.
     * @return - accountStatus object
     */
    AccountStatus getAccountStatus(int accountId);

    /**
     *
     * Sets the status object by account ID.
     *
     * @param accountId     - user ID.
     * @param status     - status.
     * @return - accountStatus object
     */
    AccountStatus setAccountStatus(int accountId, String status);
}
