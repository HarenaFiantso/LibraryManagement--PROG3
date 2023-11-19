-- Create a new table subscribers
CREATE TABLE subscribers (
    subscriber_id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    subscription_date DATE,
    subscription_type VARCHAR(50)
);

-- Add three lines of data by default
INSERT INTO subscribers (username, password, email, subscription_date, subscription_type) VALUES
('User 1', 'password 1', 'user1@hei.school', '2023-01-01', 'premium'),
('User 2', 'password 2', 'user2@hei.school', '2023-02-01', 'standard'),
('User 3', 'password 3', 'user3@hei.school', '2023-03-01', 'premium');