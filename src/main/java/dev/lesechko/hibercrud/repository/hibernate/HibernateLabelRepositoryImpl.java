package dev.lesechko.hibercrud.repository.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dev.lesechko.hibercrud.model.Label;
import dev.lesechko.hibercrud.model.Status;
import dev.lesechko.hibercrud.repository.LabelRepository;
import dev.lesechko.hibercrud.utils.HibernateConnectionUtils;


public class HibernateLabelRepositoryImpl implements LabelRepository {
    private void rollbackTransaction(Transaction t) {
        if (t != null) {
            t.rollback();
            System.err.println("Rolling back transaction");
        }
    }

    @Override
    public Label save(Label label) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getNewSession()) {
            transaction = session.beginTransaction();
            session.persist(label);
            transaction.commit();
            return label;
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Label> getAll() {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getNewSession()) {
            transaction = session.beginTransaction();
            List<Label> labels = session.createQuery("FROM Label", Label.class).list();
            transaction.commit();
            return labels;
        } catch (Exception e) {
            rollbackTransaction(transaction);
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
            rollbackTransaction(transaction);
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
            rollbackTransaction(transaction);
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
            rollbackTransaction(transaction);
            e.printStackTrace();
            return false;
        }
    }
}
