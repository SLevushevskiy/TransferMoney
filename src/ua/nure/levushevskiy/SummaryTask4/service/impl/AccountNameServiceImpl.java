package ua.nure.levushevskiy.SummaryTask4.service.impl;

import ua.nure.levushevskiy.SummaryTask4.dao.api.AccountNameDAO;
import ua.nure.levushevskiy.SummaryTask4.dto.AccountNameDTO;
import ua.nure.levushevskiy.SummaryTask4.entity.AccountName;
import ua.nure.levushevskiy.SummaryTask4.service.api.AccountNameService;
import ua.nure.levushevskiy.SummaryTask4.util.Transformer;

import java.util.List;

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

        AccountName accountName = accountNameDAO.getAccountName(id);
        return Transformer.accountName2AccountNameDTO(accountName);
    }

    /**
     * Getting all accounts name.
     *
     * @return list of accounts name.
     */
    @Override
    public List<AccountNameDTO> getAll() {
        return Transformer.accountNameList2AccountNameDTOList(accountNameDAO.getAll());
    }
}
