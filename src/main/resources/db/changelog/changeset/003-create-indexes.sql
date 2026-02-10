--liquibase formatted sql
--changeset kent04:add_indexes splitStatements:true endDelimiter:; runONChange:true

--   foreign keys' indexes

CREATE index image_spot_id_index ON images (spot_id);

CREATE index spot_owner_id_index ON spots (owner_id);

CREATE index credentials_password_id_index ON credentials (password_id);

CREATE index review_author_id_index ON reviews (author_id);
CREATE index review_spot_id_index ON reviews (spot_id);

CREATE index chat_owner_id_index ON chats (owner_id);
CREATE index chat_consumer_id_index ON chats (consumer_id);

CREATE index booking_rented_spot_id_index ON bookings (rented_spot_id);
CREATE index booking_renter_id_index ON bookings (renter_id);

--   todo add other indexes for bookings