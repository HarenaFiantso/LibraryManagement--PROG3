-- Create a new table visitor
CREATE TABLE IF NOT EXISTS visitor (
    visitor_id SERIAL PRIMARY KEY,
    visitor_name VARCHAR(255) NOT NULL,
    reference VARCHAR(255)
);

-- Add three lines of data by default
INSERT INTO visitor (visitor_name, reference) VALUES
('Visitor 1', 'Reference 1'),
('Visitor 2', 'Reference 2'),
('Visitor 3', 'Reference 3');