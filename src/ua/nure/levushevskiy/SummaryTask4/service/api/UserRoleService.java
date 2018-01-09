package ua.nure.levushevskiy.SummaryTask4.service.api;

import ua.nure.levushevskiy.SummaryTask4.dto.UserRoleDTO;

/**
 * The interface that defines the logic of working with the entity UserRole.
 */
public interface UserRoleService {
    /**
     * Getting role by id.
     *
     * @param id - role id.
     * @return - RoleDTO object.
     */
    UserRoleDTO getById(int id);
}
