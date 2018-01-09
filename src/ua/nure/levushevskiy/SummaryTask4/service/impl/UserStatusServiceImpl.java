package ua.nure.levushevskiy.SummaryTask4.service.impl;

import ua.nure.levushevskiy.SummaryTask4.dao.api.UserStatusDAO;
import ua.nure.levushevskiy.SummaryTask4.dto.UserStatusDTO;
import ua.nure.levushevskiy.SummaryTask4.entity.UserStatus;
import ua.nure.levushevskiy.SummaryTask4.service.api.UserStatusService;
import ua.nure.levushevskiy.SummaryTask4.util.Transformer;


/**
 * Implementation of the interface UserStatusServiceImpl.
 */
public class UserStatusServiceImpl implements UserStatusService {

    /**
     * The object to interact with the table 'userStatus' in database.
     */
    private final UserStatusDAO userStatusDAO;

    public  UserStatusServiceImpl(final UserStatusDAO userStatusDAO) {
        this.userStatusDAO = userStatusDAO;
    }

    @Override
    public final UserStatusDTO getById(final int id) {
        UserStatus userStatus = userStatusDAO.getUserStatus(id);
        return Transformer.userStatus2UserStatusDTO(userStatus);
    }
}
