package dev.lesechko.hibercrud.repository.hibernate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dev.lesechko.hibercrud.model.Label;
import dev.lesechko.hibercrud.model.Status;
import dev.lesechko.hibercrud.utils.HibernateConnectionUtils;
import dev.lesechko.hibercrud.repository.LabelRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class HibernateLabelRepositoryImpl implements LabelRepository {
    @Override
    public Label save(Label label) {


//        String sql = "INSERT INTO labels (name, status) VALUES (?, ?)";
//        try (PreparedStatement stmnt = DonnectionUtils.getPreparedStatement(sql)) {
//            stmnt.setString(1, label.getName());
//            stmnt.setString(2, label.getStatus().name());
//            if (stmnt.executeUpdate() == 0) return null;
//            ResultSet generatedKeys = stmnt.getGeneratedKeys();
//            generatedKeys.next();
//            label.setId(generatedKeys.getLong(1));
//            return label;
//        } catch (SQLException | NullPointerException e) {
//            e.printStackTrace();
//            return null;
//        }
        return null;
    }

    @Override
    public List<Label> getAll() {
        String hql = "FROM Label";
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getNewSession()) {
            transaction = session.beginTransaction();
            List<Label> labels = session.createQuery(hql).list();
            transaction.commit();
            return labels;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.err.println("Rolling back transaction");
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Label getById(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getNewSession()) {
            transaction = session.beginTransaction();
            Label label = session.get(Label.class, id);
            transaction.commit();
            return label;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.err.println("Rolling back transaction");
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Label update(Label label) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getNewSession()) {
            transaction = session.beginTransaction();
            session.merge(label);
            transaction.commit();
            return label;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.err.println("Rolling back transaction");
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getNewSession()) {
            transaction = session.beginTransaction();
            Label label = session.get(Label.class, id);
            label.setStatus(Status.DELETED);
            session.merge(label);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.err.println("Rolling back transaction");
            }
            e.printStackTrace();
            return false;
        }
//        String sql = "DELETE FROM labels WHERE id = ?";
//        String sql = "UPDATE labels SET status = 'DELETED' WHERE id = ?";
//        try (PreparedStatement stmnt = DbConnectionUtils.getPreparedStatement(sql)) {
//            stmnt.setLong(1, id);
//            return stmnt.executeUpdate() != 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
    }
}
