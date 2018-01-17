package ua.nure.levushevskiy.SummaryTask4.service.api;

import ua.nure.levushevskiy.SummaryTask4.dto.PaymentNameDTO;

import java.util.List;

/**
 * The interface that defines the logic of working with the entity PaymentName.
 */
public interface PaymentNameService {

    /**
     * Getting payment name by id.
     *
     * @param id - payment name id.
     * @return - PaymentNameDTO object.
     */
    PaymentNameDTO getById(int id);

    /**
     * Getting all payment name.
     *
     * @return list of payment name.
     */
    List<PaymentNameDTO> getAll();
}
