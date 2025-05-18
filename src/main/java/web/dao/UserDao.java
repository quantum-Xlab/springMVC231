package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    void del(Long id);
    void update(Long id, String newName, String newMail);
    User getUser(Long id);
    List<User> listUsers();
}
