--liquibase formatted sql
--changeset kent04:add_indexes splitStatements:true endDelimiter:; runONChange:true

--  индексы на вторичные ключи

CREATE index image_spot_id_index ON images (spot_id);
CREATE index images_review_id ON images (review_id);

CREATE index spot_owner_id_index ON spots (owner_id);

CREATE index credentials_password_id_index ON credentials (password_id);

CREATE index review_author_id_index ON reviews (author_id);
CREATE index review_spot_id_index ON reviews (spot_id);

CREATE index chat_owner_id_index ON chats (owner_id);
CREATE index chat_consumer_id_index ON chats (consumer_id);

CREATE index booking_rented_spot_id_index ON bookings (rented_spot_id);
CREATE index booking_renter_id_index ON bookings (renter_id);

-- составной индекс

CREATE index booking_spot_id_time_interval_index ON bookings (rented_spot_id, start_at, end_at);