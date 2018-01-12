package ua.nure.levushevskiy.SummaryTask4.service.api;

import ua.nure.levushevskiy.SummaryTask4.dto.PaymentTypeDTO;

import java.util.List;

/**
 * The interface that defines the logic of working with the entity PaymentType.
 */
public interface PaymentTypeService {

    /**
     * Getting payment type by id.
     *
     * @param id - payment type id.
     * @return - PaymentTypeDTO object.
     */
    PaymentTypeDTO getById(int id);

    /**
     * Getting all payment type.
     *
     * @return list of payment type.
     */
    List<PaymentTypeDTO> getAll();
}
