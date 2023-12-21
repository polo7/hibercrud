package dev.lesechko.hibercrud.repository.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dev.lesechko.hibercrud.model.Post;
import dev.lesechko.hibercrud.model.Status;
import dev.lesechko.hibercrud.model.Writer;
import dev.lesechko.hibercrud.repository.WriterRepository;
import dev.lesechko.hibercrud.utils.HibernateConnectionUtils;


public class HibernateWriterRepositoryImpl implements WriterRepository {
    private void rollbackTransaction(Transaction t) {
        if (t != null) {
            t.rollback();
            System.err.println("Rolling back transaction");
        }
    }
    private void addWriterForPosts(Writer writer, List<Post> posts) { //TODO: Hibernate должен сам обновить FK или нет?
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getNewSession()) {
            transaction = session.beginTransaction();
            for (var post : posts) {
                post.setWriter(writer);
                session.merge(post);
            }
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }

    @Override
    public Writer save(Writer writer) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getNewSession()) {
            transaction = session.beginTransaction();
            session.persist(writer);
            transaction.commit();
            addWriterForPosts(writer, writer.getPosts());
            return writer;
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Writer> getAll() {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getNewSession()) {
            transaction = session.beginTransaction();
            List<Writer> writers = session.createQuery("FROM Writer", Writer.class).list();
            transaction.commit();
            return writers;
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Writer getById(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getNewSession()) {
            transaction = session.beginTransaction();
            Writer writer = session.get(Writer.class, id);
            transaction.commit();
            return writer;
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Writer update(Writer writer) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getNewSession()) {
            transaction = session.beginTransaction();
            session.merge(writer);
            transaction.commit();
            addWriterForPosts(writer, writer.getPosts());
            return writer;
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
            Writer writer = session.get(Writer.class, id);
            writer.setStatus(Status.DELETED);
            session.merge(writer);
            transaction.commit();
            return true;
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
            return false;
        }
    }
}
