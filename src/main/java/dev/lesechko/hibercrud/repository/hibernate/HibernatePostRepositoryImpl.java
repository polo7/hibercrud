package dev.lesechko.hibercrud.repository.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dev.lesechko.hibercrud.model.Post;
import dev.lesechko.hibercrud.model.Status;
import dev.lesechko.hibercrud.repository.PostRepository;
import dev.lesechko.hibercrud.utils.HibernateConnectionUtils;


public class HibernatePostRepositoryImpl implements PostRepository {
    private void rollbackTransaction(Transaction t) {
        if (t != null) {
            t.rollback();
            System.err.println("Rolling back transaction");
        }
    }

//    private void addLabelsForPostId(List<Label> labels, Long postId) throws SQLException { //TODO: об этом Hibernate заботится
//        String sql = "INSERT INTO post_labels (postId, labelId) VALUES (?, ?)";
//        try (PreparedStatement stmnt = DbConnectionUtils.getPreparedStatement(sql)) {
//            for (var label : labels) {
//                stmnt.setLong(1, postId);
//                stmnt.setLong(2, label.getId());
//                stmnt.addBatch();
//            }
//            stmnt.executeBatch();
//        }
//    }

    @Override
    public Post save(Post post) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getNewSession()) {
            transaction = session.beginTransaction();
            session.persist(post);
            transaction.commit();
            return post;
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Post> getAll() {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getNewSession()) {
            transaction = session.beginTransaction();
            List<Post> posts = session.createQuery("FROM Post", Post.class).list();
            transaction.commit();
            return posts;
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Post getById(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getNewSession()) {
            transaction = session.beginTransaction();
            Post post = session.get(Post.class, id);
            transaction.commit();
            return post;
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Post update(Post post) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getNewSession()) {
            transaction = session.beginTransaction();
            session.merge(post);
            transaction.commit();
            return post;
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
            Post post = session.get(Post.class, id);
            post.setStatus(Status.DELETED);
            session.merge(post);
            transaction.commit();
            return true;
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
            return false;
        }
    }
}
