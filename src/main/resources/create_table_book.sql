-- Create the table book
CREATE TABLE IF NOT EXISTS "book"(
     "id" SERIAL PRIMARY KEY,
     "book_name" VARCHAR(255) NOT NULL,
     "page_numbers" INT CHECK("page_numbers" > 0) NOT NULL,
     "release_date" DATE NOT NULL,
     "topics" "topic"[],
     "id_author" INT REFERENCES "author"("id")
);

-- Insert data for the table book
INSERT INTO book (book_name, page_numbers, release_date, topics, id_author) VALUES
('Book 1', 100, '2023-12-01', ARRAY['COMEDY'::"topic"], 1),
('Book 2', 200, '2023-11-01', ARRAY['ROMANCE'::"topic"], 2),
('Book 3', 300, '2023-10-01', ARRAY['OTHER'::"topic"], 3);