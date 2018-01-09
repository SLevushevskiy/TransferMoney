package ua.nure.levushevskiy.SummaryTask4.dto;

import ua.nure.levushevskiy.SummaryTask4.entity.UserRole;
import ua.nure.levushevskiy.SummaryTask4.entity.UserStatus;

public class UserDTO {

    private long idUser;

    private String name;

    private String surname;

    private String email;

    private String password;

    private UserRoleDTO userRoleDTO;

    private UserStatusDTO userStatusDTO;

    public UserDTO(final long idUser,final String name,final String surname,final String email,final String password,
                   final UserRoleDTO userRoleDTO,final UserStatusDTO userStatusDTO) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.userRoleDTO = userRoleDTO;
        this.userStatusDTO = userStatusDTO;
    }
    public UserDTO() {
        userStatusDTO = new UserStatusDTO();
        userStatusDTO.setIdUserStatusDTO(1);
        userRoleDTO = new UserRoleDTO();
        userRoleDTO.setIdUserRole(1);
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoleDTO getUserRoleDTO() {
        return userRoleDTO;
    }

    public void setUserRoleDTO(UserRoleDTO userRoleDTO) {
        this.userRoleDTO = userRoleDTO;
    }

    public UserStatusDTO getUserStatusDTO() {
        return userStatusDTO;
    }

    public void setUserStatusDTO(UserStatusDTO userStatusDTO) {
        this.userStatusDTO = userStatusDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        if (idUser != userDTO.idUser) return false;
        if (name != null ? !name.equals(userDTO.name) : userDTO.name != null) return false;
        if (surname != null ? !surname.equals(userDTO.surname) : userDTO.surname != null) return false;
        if (email != null ? !email.equals(userDTO.email) : userDTO.email != null) return false;
        if (password != null ? !password.equals(userDTO.password) : userDTO.password != null) return false;
        if (userRoleDTO != null ? !userRoleDTO.equals(userDTO.userRoleDTO) : userDTO.userRoleDTO != null) return false;
        return userStatusDTO != null ? userStatusDTO.equals(userDTO.userStatusDTO) : userDTO.userStatusDTO == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (idUser ^ (idUser >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (userRoleDTO != null ? userRoleDTO.hashCode() : 0);
        result = 31 * result + (userStatusDTO != null ? userStatusDTO.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "idUser=" + idUser +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userRoleDTO=" + userRoleDTO +
                ", userStatusDTO=" + userStatusDTO +
                '}';
    }
}
