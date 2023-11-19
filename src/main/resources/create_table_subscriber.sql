-- Create the table subscriber
CREATE TABLE IF NOT EXISTS subscriber (
    subscriberId SERIAL PRIMARY KEY,
    subscriberName VARCHAR(255) NOT NULL,
    sex VARCHAR(255) NOT NULL,
);

-- Insert data for the table
INSERT INTO subscriber (username, password, email, subscription_date) VALUES
('User 1', 'M'),
('User 2', 'F'),
('User 3', 'M');
