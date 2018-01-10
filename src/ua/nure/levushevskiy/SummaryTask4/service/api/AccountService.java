package ua.nure.levushevskiy.SummaryTask4.service.api;

import ua.nure.levushevskiy.SummaryTask4.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    /**
     * Saving account.
     *
     * @param accountDTO - saved object.
     * @return - AccountDTO object.
     */
    AccountDTO saveAccount(AccountDTO accountDTO);

    /**
     * Getting account by id.
     *
     * @param id - account id.
     * @return - AccountDTO object.
     */
    AccountDTO getById(int id);

    /**
     * Getting all accounts.
     *
     * @return list of accounts.
     */
    List<AccountDTO> getAll();

    /**
     * Update account status by id.
     *
     * @param id - account id.
     * @param status - new status.
     * @return - true (if object was updated).
     */
    boolean updateAccountStatusById(int id,String status);

}
