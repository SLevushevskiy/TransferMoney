package ua.nure.levushevskiy.SummaryTask4.service.impl;

import org.junit.Before;
import org.junit.Test;
import ua.nure.levushevskiy.SummaryTask4.dao.api.UserStatusDAO;
import ua.nure.levushevskiy.SummaryTask4.dto.UserStatusDTO;
import ua.nure.levushevskiy.SummaryTask4.entity.UserStatus;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserStatusServiceImplTest {
    private UserStatusServiceImpl userStatusService;

    private UserStatusDAO userStatusDAO;

    private UserStatus userStatus;

    @Before
    public void init() {
        userStatusDAO = mock(UserStatusDAO.class);
        userStatusService = new UserStatusServiceImpl(userStatusDAO);
        userStatus = new UserStatus(1, "blocked");
    }

    @Test
    public void getById() throws Exception {
        when(userStatusDAO.getUserStatus((int) userStatus.getIdUserStatus())).thenReturn(userStatus);
        UserStatusDTO userStatusDTO = userStatusService.getById((int) userStatus.getIdUserStatus());
        verify(userStatusDAO, atLeastOnce()).getUserStatus((int) userStatus.getIdUserStatus());
        assertEquals(userStatus.getIdUserStatus(), userStatusDTO.getIdUserStatusDTO());
        assertEquals(userStatus.getStatus(), userStatusDTO.getStatus());
    }

}