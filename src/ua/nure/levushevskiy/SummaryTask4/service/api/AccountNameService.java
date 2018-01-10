package ua.nure.levushevskiy.SummaryTask4.service.api;

import ua.nure.levushevskiy.SummaryTask4.dto.AccountNameDTO;

import java.util.List;

/**
 * The interface that defines the logic of working with the entity AccountName.
 */
public interface AccountNameService {
    /**
     * Getting account name by id.
     *
     * @param id - account name id.
     * @return - AccountNameDTO object.
     */
    AccountNameDTO getById(int id);

    /**
     * Getting all accounts name.
     *
     * @return list of accounts name.
     */
    List<AccountNameDTO> getAll();
}
