package org.example.CRUDOperationImplementations;

import org.example.CRUDOperations;
import org.example.DatabaseConnection;
import org.example.entity.Subscriber;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        try {
            ResultSet resultSet = connection.prepareStatement(SELECT_ALL_QUERY).executeQuery();
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
        toSave.forEach(e -> subscribers.add(save(e)));
        return subscribers;
    }

    @Override
    public Subscriber save(Subscriber toSave) {
        return null;
    }

    @Override
    public Subscriber delete(Subscriber toDelete) {
        return null;
    }
}
