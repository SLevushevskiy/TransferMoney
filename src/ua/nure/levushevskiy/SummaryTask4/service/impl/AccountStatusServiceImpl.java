package ua.nure.levushevskiy.SummaryTask4.service.impl;

import ua.nure.levushevskiy.SummaryTask4.dao.api.AccountStatusDAO;
import ua.nure.levushevskiy.SummaryTask4.dto.AccountStatusDTO;
import ua.nure.levushevskiy.SummaryTask4.entity.AccountStatus;
import ua.nure.levushevskiy.SummaryTask4.service.api.AccountStatusService;
import ua.nure.levushevskiy.SummaryTask4.util.Transformer;

/**
 * Implementation of the interface AccountStatusServiceImpl.
 */
public class AccountStatusServiceImpl implements AccountStatusService {

    /**
     * The object to interact with the table 'accountStatus' in database.
     */
    private final AccountStatusDAO accountStatusDAO;

    public AccountStatusServiceImpl(AccountStatusDAO accountStatusDAO) {
        this.accountStatusDAO = accountStatusDAO;
    }

    /**
     * Getting account status by id.
     *
     * @param id - account status id.
     * @return - AccountStatusDTO object.
     */
    @Override
    public AccountStatusDTO getById(int id) {
        AccountStatus accountStatus = accountStatusDAO.getAccountStatus(id);
        return Transformer.accountStatus2AccountStatusDTO(accountStatus);
    }
}
