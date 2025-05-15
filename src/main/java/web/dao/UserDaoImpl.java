package web.dao;

import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void add(User user) {
       entityManager.persist(user);
    }

    @Override
    @Transactional
    public void del(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    @Transactional
    public void update(Long id, String newName, String newMail) {
        User updUser = entityManager.find(User.class, id);
        if (!newName.isBlank()) {updUser.setUserName(newName);};
        if (!newMail.isBlank()) {updUser.setUserMail(newMail);}
     }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query=entityManager.createQuery("select usr FROM User usr", User.class);
        return query.getResultList();
    }

}
