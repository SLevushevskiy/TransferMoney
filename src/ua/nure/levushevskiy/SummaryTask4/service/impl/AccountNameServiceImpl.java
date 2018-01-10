package ua.nure.levushevskiy.SummaryTask4.service.impl;

import ua.nure.levushevskiy.SummaryTask4.dao.api.AccountNameDAO;
import ua.nure.levushevskiy.SummaryTask4.dto.AccountNameDTO;
import ua.nure.levushevskiy.SummaryTask4.service.api.AccountNameService;

public class AccountNameServiceImpl implements AccountNameService {
    /**
     * The object to interact with the table 'account_name' in database.
     */
    AccountNameDAO accountNameDAO;

    public AccountNameServiceImpl(AccountNameDAO accountNameDAO) {
        this.accountNameDAO = accountNameDAO;
    }

    /**
     * Getting account name by id.
     *
     * @param id - account name id.
     * @return - AccountNameDTO object.
     */
    @Override
    public AccountNameDTO getById(int id) {
        return null;
    }
}
