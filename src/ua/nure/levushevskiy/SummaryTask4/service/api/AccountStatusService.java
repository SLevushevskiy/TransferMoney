package ua.nure.levushevskiy.SummaryTask4.service.api;

import ua.nure.levushevskiy.SummaryTask4.dto.AccountStatusDTO;

/**
 * The interface that defines the logic of working with the entity AccountStatus.
 */
public interface AccountStatusService {
    /**
     * Getting account status by id.
     *
     * @param id - account status id.
     * @return - AccountStatusDTO object.
     */
    AccountStatusDTO getById(int id);
}
