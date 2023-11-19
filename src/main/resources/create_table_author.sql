-- Create the table author
CREATE TABLE IF NOT EXISTS "author" (
    "id" SERIAL PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL,
    "sex" CHAR(1) CHECK (sex IN ('M', 'F')) NOT NULL
);

-- Insert data for the table author
INSERT INTO author (name, sex) VALUES
('Author 1', 'M'),
('Author 2', 'F'),
('Author 3', 'M');