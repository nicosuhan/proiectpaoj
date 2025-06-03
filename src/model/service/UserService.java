package model.service;

import model.dao.UserDAO;
import model.user.Admin;
import model.user.Client;
import model.user.User;
import model.AuditService;

import java.util.List;

public class UserService {
    private static UserService instance;
    private final UserDAO userDAO;
    private final AuditService auditService;

    private UserService() {
        this.userDAO = new UserDAO();
        this.auditService = AuditService.getInstance();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public void addUser(User user) {
        userDAO.create(user);
        auditService.logAction("add_user");
    }

    public User getUserById(int id) {
        auditService.logAction("get_user_by_id");
        return userDAO.findById(id);
    }

    public List<User> getAllUsers() {
        auditService.logAction("get_all_users");
        return userDAO.findAll();
    }

    public void updateUser(User user) {
        userDAO.update(user);
        auditService.logAction("update_user");
    }

    public void deleteUser(int id) {
        userDAO.deleteById(id);
        auditService.logAction("delete_user");
    }
}
