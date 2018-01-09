package ua.nure.levushevskiy.SummaryTask4.service.api;

import ua.nure.levushevskiy.SummaryTask4.dto.UserStatusDTO;

/**
 * The interface that defines the logic of working with the entity UserStatus.
 */
public interface UserStatusService {
    /**
     * Getting user status by id.
     *
     * @param id - user status id.
     * @return - UserStatusDTO object.
     */
    UserStatusDTO getById(int id);
}
