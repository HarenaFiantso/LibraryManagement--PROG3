-- Create a new table book
CREATE TABLE IF NOT EXISTS book (
    book_id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author_id INT NOT NULL,
    page_numbers INT,
    topic ENUM('COMEDY', 'ROMANCE', 'OTHER') NOT NULL,
    release_date DATE,
    status VARCHAR(50) DEFAULT 'available'
);

-- Add three lines of data by default
INSERT INTO book (title, author_id, page_numbers, topic, release_date) VALUES
('Book 1', 1, 200, 'COMEDY', '2023-01-01'),
('Book 2', 2, 300, 'ROMANCE', '2023-02-01'),
('Book 3', 3, 400, 'OTHER', '2023-03-01');