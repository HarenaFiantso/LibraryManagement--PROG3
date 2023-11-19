-- Create the table subscriber
CREATE TABLE IF NOT EXISTS subscriber (
    userId SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    subscription_date DATE
);

-- Insert data for the table
INSERT INTO subscriber (username, password, email, subscription_date) VALUES
('User 1', 'pass1', 'user1@hei.com', '2022-01-01'),
('User 2', 'pass2', 'user2@hei.com', '2022-02-01'),
('User 3', 'pass3', 'user3@hei.com', '2022-03-01');
