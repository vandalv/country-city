--changeset dauzhukv:1
-- Create the custom enum type
CREATE TYPE user_role AS ENUM ('USER', 'EDITOR');

-- Changeset dauzhukv:2
-- Create the table for user information
CREATE TABLE IF NOT EXISTS country_city.user (
    id SERIAL PRIMARY KEY,
    email VARCHAR(50),
    password VARCHAR(255),
    role user_role NOT NULL
);

--rollback DROP TABLE country_city.user;
--rollback DROP user_role;