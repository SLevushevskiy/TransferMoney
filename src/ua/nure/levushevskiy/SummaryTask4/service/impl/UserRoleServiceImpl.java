package ua.nure.levushevskiy.SummaryTask4.service.impl;

import ua.nure.levushevskiy.SummaryTask4.dao.api.UserRoleDAO;
import ua.nure.levushevskiy.SummaryTask4.dto.UserRoleDTO;
import ua.nure.levushevskiy.SummaryTask4.entity.UserRole;
import ua.nure.levushevskiy.SummaryTask4.service.api.UserRoleService;
import ua.nure.levushevskiy.SummaryTask4.util.Transformer;

public class UserRoleServiceImpl implements UserRoleService {
    /**
     * The object to interact with the table 'role' in database.
     */
    private final UserRoleDAO roleDAO;

    public UserRoleServiceImpl(final UserRoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public final UserRoleDTO getById(final int id) {
        UserRole role = roleDAO.getUserRole(id);
        return Transformer.role2RoleDTO(role);
    }

}
