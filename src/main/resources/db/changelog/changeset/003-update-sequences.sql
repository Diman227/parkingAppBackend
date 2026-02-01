--liquibase formatted sql
--changeset kent04:update_sequences splitStatements:true endDelimiter:; runOnChange:true


ALTER SEQUENCE bookings_id_seq RESTART WITH 35;

ALTER SEQUENCE chats_id_seq RESTART WITH 35;

ALTER SEQUENCE coordinates_id_seq RESTART WITH 35;

ALTER SEQUENCE credentials_id_seq RESTART WITH 35;

ALTER SEQUENCE images_id_seq RESTART WITH 35;

ALTER SEQUENCE messages_id_seq RESTART WITH 35;

ALTER SEQUENCE passwords_id_seq RESTART WITH 35;

ALTER SEQUENCE reviews_id_seq RESTART WITH 35;

ALTER SEQUENCE spots_id_seq RESTART WITH 35;

ALTER SEQUENCE users_id_seq RESTART WITH 35;
