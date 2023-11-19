DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'topic') THEN
        CREATE TYPE "topic" AS ENUM ('COMEDY', 'ROMANCE', 'OTHER');
    END IF;
END $$;