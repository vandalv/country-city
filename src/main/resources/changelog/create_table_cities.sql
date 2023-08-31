--changeset dauzhukv:1
-- Create the table for cities

CREATE TABLE IF NOT EXISTS country_city.cities (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    logo VARCHAR(255) NOT NULL,
    country_id INT,
    FOREIGN KEY (country_id) REFERENCES country_city.countries(id)
);

--rollback DROP TABLE country_city.cities;