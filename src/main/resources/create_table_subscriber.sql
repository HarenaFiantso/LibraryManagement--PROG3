-- Create the table subscriber
CREATE TABLE IF NOT EXISTS "subscriber" (
    "id" SERIAL PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL,
    "ref" VARCHAR(255) NOT NULL
);

-- Insert data for the table
INSERT INTO subscriber (name, ref) VALUES
('User 1', 'tsy aiko'),
('User 2', 'tsy aiko'),
('User 3', 'tsy aiko');
