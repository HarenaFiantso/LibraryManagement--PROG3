-- Create the table author
CREATE TABLE IF NOT EXISTS author (
    authorId SERIAL PRIMARY KEY,
    authorName VARCHAR(255) NOT NULL,
    sex CHAR(1) CHECK (sex IN ('M', 'F')) NOT NULL
);

-- Insert data for the table author
INSERT INTO author (authorName, sex) VALUES
('Author 1', 'M'),
('Author 2', 'F'),
('Author 3', 'M');