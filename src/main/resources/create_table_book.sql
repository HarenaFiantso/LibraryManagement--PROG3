-- Create the table book
CREATE TABLE IF NOT EXISTS book (
    bookId SERIAL PRIMARY KEY,
    bookName VARCHAR(255) NOT NULL,
    pageNumbers INT CHECK(pageNumbers > 0) NOT NULL,
    topics "topic"[],
    release_date DATE NOT NULL,
    status VARCHAR(255),
    id_author INT REFERENCES author(authorId)
);

-- Insert data for the table book
INSERT INTO book (bookName, pageNumbers, release_date, topics, id_author) VALUES
('Book 1', 100, '2023-12-01', ARRAY['COMEDY'::"topic"], 1),
('Book 2', 200, '2023-11-01', ARRAY['ROMANCE'::"topic"], 2),
('Book 3', 300, '2023-10-01', ARRAY['OTHER'::"topic"], 3);