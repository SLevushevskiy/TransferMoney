package ua.nure.levushevskiy.SummaryTask4.service.impl;

import org.apache.log4j.Logger;
import ua.nure.levushevskiy.SummaryTask4.dao.api.UserDAO;
import ua.nure.levushevskiy.SummaryTask4.dto.UserDTO;
import ua.nure.levushevskiy.SummaryTask4.entity.User;
import ua.nure.levushevskiy.SummaryTask4.mail.MailSenderRunner;
import ua.nure.levushevskiy.SummaryTask4.mail.api.ConfirmationMailSender;
import ua.nure.levushevskiy.SummaryTask4.service.api.UserRoleService;
import ua.nure.levushevskiy.SummaryTask4.service.api.UserService;
import ua.nure.levushevskiy.SummaryTask4.service.api.UserStatusService;
import ua.nure.levushevskiy.SummaryTask4.util.Cryptographer;
import ua.nure.levushevskiy.SummaryTask4.util.Transformer;

import java.util.List;

public class UserServiceImpl implements UserService {
    /**
     * The Logger object for logging events of DAO class.
     */
    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    /**
     * The object to interact with the table 'user' in database.
     */
    private final UserDAO userDAO;

    /**
     * Object to Convert UserService to UserServiceDTO.
     */
    private final UserRoleService roleService;

    /**
     * Object to Convert UserService to UserServiceDTO.
     */
    private final UserStatusService userStatusService;

    /**
     * Object for sending confirmation letter.
     */
    //private final ConfirmationMailSender confirmationMailSender;

    public UserServiceImpl(final UserDAO userDAO, final UserRoleService roleService,
                           final UserStatusService userStatusService) {
        this.userDAO = userDAO;
        this.roleService = roleService;
        this.userStatusService = userStatusService;
        //this.confirmationMailSender = confirmationMailSender;
    }

    @Override
    public final UserDTO saveUser(UserDTO userDTO) {
        if (userDAO.hasUserWithEmail(userDTO.getEmail())) {
            throw new IllegalStateException("User with this email already exists!");
        }
        userDTO.setPassword(Cryptographer.md5Custom(userDTO.getPassword()));
        User user = Transformer.userDTO2User(userDTO);
        userDTO =  Transformer.user2UserDTO(userDAO.save(user), roleService, userStatusService);
        LOG.info("New user was added!");/*
        MailSenderRunner mailSenderRunner = new MailSenderRunner(confirmationMailSender, userDTO);
        mailSenderRunner.setDaemon(true);
        mailSenderRunner.start();
        LOG.info("Sending confirmation letter.");*/
        return userDTO;
    }

    @Override
    public final UserDTO getByEmailAndPassword(final String email, String password) {
        password = Cryptographer.md5Custom(password);
        User user = userDAO.getByEmailAndPassword(email, password);
        if (user == null) {
            throw new IllegalStateException("Incorrect e-mail and password combination!");
        }
        return Transformer.user2UserDTO(user, roleService, userStatusService);
    }

    @Override
    public final UserDTO getById(final int id) {
        return Transformer.user2UserDTO(userDAO.getById(id), roleService, userStatusService);
    }

    @Override
    public final List<UserDTO> getAll() {
        return Transformer.userList2UserDTOList(userDAO.getAll(), roleService, userStatusService);
    }

    @Override
    public final boolean updateUserStatus(final int id, final String userStatus) {
        return userDAO.updateUserStatus(id, userStatus);
    }

}
