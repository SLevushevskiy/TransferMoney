package ua.nure.levushevskiy.SummaryTask4.service.api;

import ua.nure.levushevskiy.SummaryTask4.dto.PaymentStatusDTO;

/**
 * The interface that defines the logic of working with the entity PaymentStatus.
 */
public interface PaymentStatusService {
    /**
     * Getting payment status by id.
     *
     * @param id - payment status id.
     * @return - PaymentStatusDTO object.
     */
    PaymentStatusDTO getById(int id);
}
