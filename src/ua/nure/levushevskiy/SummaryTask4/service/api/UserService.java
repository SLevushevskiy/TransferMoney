package ua.nure.levushevskiy.SummaryTask4.service.api;

import ua.nure.levushevskiy.SummaryTask4.dto.UserDTO;

import java.util.List;
/**
 * The interface that defines the logic of working with the entity User.
 */
public interface UserService {
    /**
     * Saving user.
     *
     * @param userDTO - saved object.
     * @return - this object.
     */
    UserDTO saveUser(UserDTO userDTO);

    /**
     * Getting user object by email and password.
     *
     * @param email    - user email.
     * @param password - user password.
     * @return - UserDTO object.
     */
    UserDTO getByEmailAndPassword(String email, String password);

    /**
     * Getting user object by user id.
     *
     * @param id - user id.
     * @return - UserDTO object.
     */
    UserDTO getById(int id);

    /**
     * Getting all users.
     *
     * @return list of users.
     */
    List<UserDTO> getAll();

    /**
     * Update user status by id.
     *
     * @param id         - user id.
     * @param userStatus - new status.
     * @return - true (if object was updated).
     */
    boolean updateUserStatus(int id, String userStatus);

}
