package org.example.CRUDOperationImplementations;

import org.example.CRUDOperations;
import org.example.DatabaseConnection;
import org.example.entity.Subscriber;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriberCrudOperations implements CRUDOperations<Subscriber> {
    private static final Connection connection = DatabaseConnection.getConnection();

    private static Subscriber subscriberInstance(ResultSet resultSet) throws SQLException {
        return new Subscriber(
                resultSet.getLong("subscriber_id"),
                resultSet.getString("subscriber_name"),
                resultSet.getString("sex")
        );
    }

    @Override
    public List<Subscriber> findAll() {
        String SELECT_ALL_QUERY = "SELECT * FROM subscriber;";
        List<Subscriber> subscribers = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                subscribers.add(subscriberInstance(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscribers;
    }

    @Override
    public List<Subscriber> saveAll(List<Subscriber> toSave) {
        List<Subscriber> subscribers = new ArrayList<>();

        String INSERT_QUERY = "INSERT INTO subscriber (subscriber_name, sex) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            for (Subscriber subscriber : toSave) {
                statement.setString(1, subscriber.getSubscriberName());
                statement.setString(2, subscriber.getSex());
                statement.executeUpdate();

                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    subscriber.setSubscriberId(resultSet.getLong(1));
                    subscribers.add(subscriber);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscribers;
    }

    @Override
    public Subscriber save(Subscriber toSave) {
        String INSERT_QUERY = "INSERT INTO subscriber (subscriber_name, sex) VALUES (?, ?);";
        Subscriber subscriber = null;

        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, toSave.getSubscriberName());
            statement.setString(2, toSave.getSex());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                toSave.setSubscriberId(resultSet.getLong(1));
                subscriber = toSave;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscriber;
    }

    @Override
    public Subscriber delete(Subscriber toDelete) {
        String DELETE_QUERY = "DELETE FROM subscriber WHERE subscriber_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setLong(1, toDelete.getSubscriberId());
            statement.executeUpdate();
            return toDelete;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
