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
    public List<Label> getAll() { //TODO: add data and check
        String hql = "FROM Label";
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getNewSession()) {
            transaction = session.beginTransaction();
            List<Label> labels = session.createQuery(hql).list();
            transaction.commit();
            return labels;
        } catch (Exception e) {
            if (transaction != null) {
                System.err.println("Rolling back transaction");
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Label getById(Long id) {
//        Label label = null;
//        String sql = "SELECT * FROM labels WHERE id = ?";
//        try (PreparedStatement stmnt = DbConnectionUtils.getPreparedStatement(sql)) {
//            stmnt.setLong(1, id);
//            ResultSet rs = stmnt.executeQuery();
//            if (rs.next()) {
//                label = new Label();
//                label.setId(rs.getLong("id"));
//                label.setName(rs.getString("name"));
//                label.setStatus(Status.valueOf(rs.getString("status")));
//            }
//            return label;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
        return null;
    }

    @Override
    public Label update(Label label) {
//        String sql = "UPDATE labels SET name = ?, status = ? WHERE id = ?";
//        try (PreparedStatement stmnt = DbConnectionUtils.getPreparedStatement(sql)) {
//            stmnt.setString(1, label.getName());
//            stmnt.setString(2, label.getStatus().name());
//            stmnt.setLong(3, label.getId());
//            if (stmnt.executeUpdate() == 0) return null;
//            return label;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
//        String sql = "DELETE FROM labels WHERE id = ?";
//        String sql = "UPDATE labels SET status = 'DELETED' WHERE id = ?";
//        try (PreparedStatement stmnt = DbConnectionUtils.getPreparedStatement(sql)) {
//            stmnt.setLong(1, id);
//            return stmnt.executeUpdate() != 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
        return false;
    }
}
