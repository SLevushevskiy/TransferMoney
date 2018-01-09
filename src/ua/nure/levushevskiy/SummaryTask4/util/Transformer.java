package ua.nure.levushevskiy.SummaryTask4.util;

import ua.nure.levushevskiy.SummaryTask4.dto.*;
import ua.nure.levushevskiy.SummaryTask4.entity.*;
import ua.nure.levushevskiy.SummaryTask4.service.api.*;
import ua.nure.levushevskiy.SummaryTask4.service.impl.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Utility class for conversion objects to objectDTO & objectDTO to object.
 */
public class Transformer {


    /**
     * A method that converts a list of UserDTO into a User list.
     *
     * @param userDTOList - list of UserDTO.
     * @return - list of User.
     */
    public static List<User> userDTOList2UserList(final List<UserDTO> userDTOList) {
        List<User> userList = new ArrayList<>();

        for (UserDTO userDTO : userDTOList) {
            userList.add(userDTO2User(userDTO));
        }

        return userList;
    }

    /**
     * A method that converts UserDTO into a User.
     *
     * @param userDTO - UserDTO object.
     * @return - User object.
     */
    public static User userDTO2User(final UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setUserStatusId(userDTO.getUserStatusDTO().getIdUserStatusDTO());
        user.setRoleId(userDTO.getUserRoleDTO().getIdUserRole());
        return user;
    }

    /**
     * A method that converts a list of User into a UserDTO list.
     *
     * @param userList          - list of User.
     * @param roleService       - service for obtaining a Role object by its ID.
     * @param userStatusService - service for obtaining a UserStatus object by its ID.
     * @return - list of UserDTO.
     */
    public static List<UserDTO> userList2UserDTOList(final List<User> userList, final UserRoleService roleService,
                                                     final UserStatusService userStatusService) {
        List<UserDTO> userDTOList = new ArrayList<>();

        for (User user : userList) {
            userDTOList.add(user2UserDTO(user, roleService, userStatusService));
        }

        return userDTOList;
    }

    /**
     * A method that converts User into a UserDTO.
     *
     * @param user              - User object.
     * @param roleService       - service for obtaining a Role object by its ID.
     * @param userStatusService - service for obtaining a UserStatus object by its ID.
     * @return - UserDTO object.
     */
    public static UserDTO user2UserDTO(final User user, final UserRoleService roleService, final UserStatusService userStatusService) {
        UserDTO userDTO = new UserDTO();
        userDTO.setIdUser(user.getIdUser());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setUserRoleDTO(roleService.getById((int) user.getRoleId()));
        userDTO.setUserStatusDTO(userStatusService.getById((int) user.getUserStatusId()));
        return userDTO;
    }

    /**
     * A method that converts a list of UserRole into a UserRoleDTO list.
     *
     * @param roleList - list of UserRole.
     * @return - list of UserRoleDTO.
     */
    public static List<UserRoleDTO> roleList2RoleListDTO(final List<UserRole> roleList) {
        List<UserRoleDTO> roleDTOList = new ArrayList<>();

        for (UserRole role : roleList) {
            roleDTOList.add(role2RoleDTO(role));
        }

        return roleDTOList;
    }

    /**
     * A method that converts UserRole into a UserRoleDTO.
     *
     * @param role - UserRole object.
     * @return - UserRoleDTO object.
     */
    public static UserRoleDTO role2RoleDTO(final UserRole role) {
        UserRoleDTO roleDTO = new UserRoleDTO();
        roleDTO.setIdUserRole(role.getIdUserRole());
        roleDTO.setRank(role.getRank());
        return roleDTO;
    }

    /**
     * A method that converts a list of UserStatus into a UserStatusDTO list.
     *
     * @param userStatusList - list of UserStatus.
     * @return - list of UserStatusDTO.
     */
    public static List<UserStatusDTO> userStatusList2UserStatusListDTO(final List<UserStatus> userStatusList) {
        List<UserStatusDTO> userStatusDTOList = new ArrayList<>();

        for (UserStatus userStatus : userStatusList) {
            userStatusDTOList.add(userStatus2UserStatusDTO(userStatus));
        }

        return userStatusDTOList;
    }

    /**
     * A method that converts UserStatus into a UserStatusDTO.
     *
     * @param userStatus - UserStatus object.
     * @return - UserStatusDTO object.
     */
    public static UserStatusDTO userStatus2UserStatusDTO(final UserStatus userStatus) {
        UserStatusDTO userStatusDTO = new UserStatusDTO();
        userStatusDTO.setIdUserStatusDTO(userStatus.getIdUserStatus());
        userStatusDTO.setStatus(userStatus.getStatus());
        return userStatusDTO;
    }


}
