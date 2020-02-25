package dev.innate.persistance;

import dev.innate.entity.Brew;
import dev.innate.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class BrewDao {
    private SessionFactory sessionFactory;

    public BrewDao() {
        sessionFactory = SessionFactoryProvider.getSessionFactory();
    }

    public List<Brew> getAllBrews() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Brew> query = criteriaBuilder.createQuery(Brew.class);
        Root<Brew> root = query.from(Brew.class);

        List<Brew> users = session.createQuery(query).getResultList();
        session.close();
        return users;
    }

    public Brew getBrewById(int id) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Brew> query = criteriaBuilder.createQuery(Brew.class);
        Root<Brew> root = query.from(Brew.class);

        Expression<String> propertyPath = root.get("id");
        query.where(criteriaBuilder.equal(propertyPath, id));

        Brew brew =  session.createQuery(query).getSingleResult();
        session.close();
        return brew;
    }

    // TODO figure out how to implement this.
//    public List<Brew> getBrewsByUser(int userId) {
//        Session session = sessionFactory.openSession();
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery<Brew> query = criteriaBuilder.createQuery(Brew.class);
//        Root<Brew> root = query.from(Brew.class);
//
//        Expression<String> propertyPath = root.get("user_id");
//        query.where(criteriaBuilder.equal(propertyPath, userId));
//
//        List<Brew> brews =  session.createQuery(query).getResultList();
//        session.close();
//        return brews;
//    }

    public void updateBrew(Brew brew) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(brew);
        transaction.commit();
        session.close();
    }

    public void deleteBrew(Brew brew) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(brew);
        transaction.commit();
        session.close();
    }

    public int createBrew(Brew brew) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(brew);
        transaction.commit();
        session.close();
        return id;
    }
}
