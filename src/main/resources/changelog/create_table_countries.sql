--changeset dauzhukv:1
-- Create the table with the custom enum type column

CREATE TABLE IF NOT EXISTS country_city.countries (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

--rollback DROP TABLE country_city.countries;
