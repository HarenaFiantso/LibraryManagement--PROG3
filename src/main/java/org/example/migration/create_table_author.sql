-- Create a new table author
CREATE TABLE IF NOT EXISTS author (
    author_id SERIAL PRIMARY KEY,
    author_name VARCHAR(255) NOT NULL,
    sex ENUM('M', 'F') NOT NULL
);

-- Add three lines of data by default
INSERT INTO author (author_name, sex) VALUE
('Author 1', 'M'),
('Author 2', 'F'),
('Author 3', 'F');