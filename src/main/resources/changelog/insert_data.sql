-- Insert Data into the 'countries' table

INSERT INTO country_city.countries (id, name) VALUES (1, 'Poland');
INSERT INTO country_city.countries (id, name) VALUES (2, 'France');
INSERT INTO country_city.countries (id, name) VALUES (3, 'UK');
INSERT INTO country_city.countries (id, name) VALUES (4, 'USA');

-- Insert Data into the 'cities' table

INSERT INTO country_city.cities (id, name, logo, country_id) VALUES (4, 'Pila', 'g3WqxQt/Pila-PL.png', 1);
INSERT INTO country_city.cities (id, name, logo, country_id) VALUES (5, 'Bytom', '7RDqmK2/Bytom-PL.png', 1);
INSERT INTO country_city.cities (id, name, logo, country_id) VALUES (6, 'Lubawa', 'g9d9CBs/Lubawa-PL.png', 1);
INSERT INTO country_city.cities (id, name, logo, country_id) VALUES (2, 'Szczecin', 'qBw9snD/Szczecin-PL.png', 1);
INSERT INTO country_city.cities (id, name, logo, country_id) VALUES (3, 'Wroclaw', 'CKnqcpb/Wroclaw-PL.png', 1);
INSERT INTO country_city.cities (id, name, logo, country_id) VALUES (7, 'Brest', 'HYt1rWs/Brest-FR.png', 2);
INSERT INTO country_city.cities (id, name, logo, country_id) VALUES (8, 'Lille', '5Fm7253/Lille-FR.png', 2);
INSERT INTO country_city.cities (id, name, logo, country_id) VALUES (9, 'Marselle', 'fH1v15S/Marseille-FR.png', 2);
INSERT INTO country_city.cities (id, name, logo, country_id) VALUES (10, 'Paris', '2qK24BZ/Paris-FR.png', 2);
INSERT INTO country_city.cities (id, name, logo, country_id) VALUES (11, 'Cambridge', '58X2wcP/Cambridge-UK.png', 3);
INSERT INTO country_city.cities (id, name, logo, country_id) VALUES (12, 'Leeds', 'qr9v713/Leeds-UK.png', 3);
INSERT INTO country_city.cities (id, name, logo, country_id) VALUES (13, 'Westminster', 'W2JN5rQ/Westminster-UK.png', 3);
INSERT INTO country_city.cities (id, name, logo, country_id) VALUES (14, 'Bath', 'MZ0yHht/Bath-UK.png', 3);
INSERT INTO country_city.cities (id, name, logo, country_id) VALUES (15, 'Birmingham', 'j4DBSqT/Birmingham-UK.png', 3);
INSERT INTO country_city.cities (id, name, logo, country_id) VALUES (16, 'Paris', '4Z7qm6w/Paris-USA.png', 4);
INSERT INTO country_city.cities (id, name, logo, country_id) VALUES (1, 'Paris', 'qBw9snD/Szczecin-PL.png', 1);

-- Insert Data into the 'users' table

INSERT INTO country_city."user" (id, email, password, role) VALUES (2, '1@example.com', '$2a$11$BoFt4Pe6NBmTw.GL39rX1OG3p64fqKWmW4MVooILK8zjubNUhsuY2', 'USER');
INSERT INTO country_city."user" (id, email, password, role) VALUES (3, '2@example.com', '$2a$11$zFqyFQjB3tKhjvVUqSF.3ekzsylSnwSzmOLcNf3h/8e8bnrzwtB3m', 'EDITOR');