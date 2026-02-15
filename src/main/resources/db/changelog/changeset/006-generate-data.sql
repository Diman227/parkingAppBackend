--liquibase formatted sql
--changeset kent04:generated_data splitStatements:true endDelimiter:; runOnChange:true

INSERT INTO passwords (id, password)
SELECT
    generate_series + 554,
    '{noop}password' || generate_series
FROM generate_series(1, 1000000);

ANALYZE passwords;

INSERT INTO credentials (id, password_id, phone_number)
SELECT
    gs + 554,
    gs + 554,
    '+7' || (900 + (gs % 100)::int)::text || LPAD((gs % 10000000)::text, 7, '0')
FROM generate_series(1, 100000) AS gs;

ANALYZE credentials;

INSERT INTO users (id, name, surname, email, credentials_id)
SELECT
    generate_series + 554,
    'User_' || generate_series,
    'Surname_' || generate_series,
    'user' || (generate_series + 554) || '@example.com',
    generate_series + 554
FROM generate_series(1, 100000);

ANALYZE users;

INSERT INTO coordinates (id, lat, lon)
SELECT
    generate_series + 554,
    55.75 + (random() * 0.1),
    37.62 + (random() * 0.1)
FROM generate_series(1, 100000);

ANALYSE coordinates;

INSERT INTO spots (id, description, address, price, created_at, owner_id, coordinates_id)
SELECT
    generate_series + 554,
    'spot_' || (generate_series + 554),
    'address_' || (generate_series + 554),
    150 + (random() * 350),
    NOW() - (random() * interval '2 years'),
    555 + (random() * 95555)::int,
    generate_series + 554
FROM generate_series(1, 100000);

ANALYZE spots;

INSERT INTO bookings (rented_spot_id, renter_id, total_price, start_at, end_at, created_at)
SELECT
    555 + (random() * 95555)::int,
    555 + (random() * 95555)::int,
    (random() * 5000 + 500)::numeric(10,2),
    timestamp '2025-01-01' + (random() * (interval '730 days')),
    timestamp '2025-01-01' + (random() * (interval '730 days')) + (random() * interval '14 days'),
    NOW() - (random() * interval '365 days')
FROM generate_series(1, 100000);

ANALYZE bookings;

INSERT INTO reviews (id, message, rate, created_at, author_id, spot_id)
SELECT
    gs,
    'review_' || gs,
    3 + (random() * 2)::int,
    NOW() - (random() * interval '1 year'),
    gs,
    555 + (random() * 10000)::int
FROM generate_series(555, 100000) AS gs;

ANALYZE reviews;


