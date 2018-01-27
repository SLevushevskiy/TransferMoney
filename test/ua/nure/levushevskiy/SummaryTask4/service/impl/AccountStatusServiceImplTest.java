package ua.nure.levushevskiy.SummaryTask4.service.impl;

import org.junit.Before;
import org.junit.Test;
import ua.nure.levushevskiy.SummaryTask4.dao.api.AccountStatusDAO;
import ua.nure.levushevskiy.SummaryTask4.dto.AccountStatusDTO;
import ua.nure.levushevskiy.SummaryTask4.entity.AccountStatus;
import ua.nure.levushevskiy.SummaryTask4.service.api.AccountStatusService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AccountStatusServiceImplTest {

    private AccountStatusServiceImpl accountStatusService;

    private AccountStatusDAO accountStatusDAO;

    private AccountStatus accountStatus;

    @Before
    public void init() {
        accountStatusDAO = mock(AccountStatusDAO.class);
        accountStatusService = new AccountStatusServiceImpl(accountStatusDAO);
        accountStatus = new AccountStatus(1, "blocked");
    }

    @Test
    public void getById() throws Exception {
        when(accountStatusDAO.getAccountStatus((int) accountStatus.getIdAccountStatus())).thenReturn(accountStatus);
        AccountStatusDTO accountStatusDTO = accountStatusService.getById((int) accountStatus.getIdAccountStatus());
        verify(accountStatusDAO, atLeastOnce()).getAccountStatus((int) accountStatus.getIdAccountStatus());
        assertEquals(accountStatus.getIdAccountStatus(), accountStatusDTO.getIdAcccountStatus());
        assertEquals(accountStatus.getStatus(), accountStatusDTO.getStatus());
    }

}