package ua.nure.levushevskiy.SummaryTask4.service.impl;

import org.apache.log4j.Logger;
import ua.nure.levushevskiy.SummaryTask4.dao.api.AccountDAO;
import ua.nure.levushevskiy.SummaryTask4.dto.AccountDTO;
import ua.nure.levushevskiy.SummaryTask4.entity.Account;
import ua.nure.levushevskiy.SummaryTask4.service.api.AccountNameService;
import ua.nure.levushevskiy.SummaryTask4.service.api.AccountService;
import ua.nure.levushevskiy.SummaryTask4.service.api.AccountStatusService;
import ua.nure.levushevskiy.SummaryTask4.service.api.UserService;
import ua.nure.levushevskiy.SummaryTask4.util.Transformer;

import java.util.List;

public class AccountServiceImpl implements AccountService{
    /**
     * The Logger object for logging events of DAO class.
     */
    private static final Logger LOG = Logger.getLogger(AccountServiceImpl.class);

    /**
     * The object to interact with the table 'account' in database.
     */
    private final AccountDAO accountDAO;

    /**
     * Object to Convert UserService to UserServiceDTO.
     */
    private final UserService userService;

    /**
     * Object to Convert AccountStatusService to AccountStatusServiceDTO.
     */
    private final AccountStatusService accountStatusService;

    /**
     * Object to Convert AccountNameService to AccountNameServiceDTO.
     */
    private final AccountNameService accountNameService;


    public AccountServiceImpl(final AccountDAO accountDAO,final UserService userService,final AccountStatusService accountStatusService,final AccountNameService accountNameService) {
        this.accountDAO = accountDAO;
        this.userService = userService;
        this.accountStatusService = accountStatusService;
        this.accountNameService = accountNameService;
    }

    /**
     * Saving account.
     *
     * @param accountDTO - saved object.
     * @return - AccountDTO object.
     */
    @Override
    public AccountDTO saveAccount(AccountDTO accountDTO) {
        Account account = Transformer.accountDTO2Account(accountDTO);
        accountDTO =  Transformer.account2AccountDTO(accountDAO.save(account),userService, accountNameService, accountStatusService);
        LOG.info("New account was added!");
        //LOG.info("Sending confirmation letter.");
        return accountDTO;
    }

    /**
     * Getting account by id.
     *
     * @param id - account id.
     * @return - AccountDTO object.
     */
    @Override
    public AccountDTO getById(int id) {
        return Transformer.account2AccountDTO(accountDAO.getById(id), userService, accountNameService, accountStatusService);
    }

    /**
     * Getting all accounts.
     *
     * @return list of accounts.
     */
    @Override
    public List<AccountDTO> getAll() {
        return Transformer.accountList2AccountDTOList(accountDAO.getAll(), userService, accountNameService, accountStatusService);
    }

    /**
     * Update account status by id.
     *
     * @param id     - account id.
     * @param status - new status.
     * @return - true (if object was updated).
     */
    @Override
    public boolean updateAccountStatusById(int id, String status) {
        return accountDAO.updateAccountStatus(id, status);
    }
}
