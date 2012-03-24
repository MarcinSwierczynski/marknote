# Notes schema

# --- !Ups

CREATE SEQUENCE note_id_seq;
CREATE TABLE note (
    id integer NOT NULL DEFAULT nextval('note_id_seq'),
    content text
);

# --- !Downs

DROP TABLE note;
DROP SEQUENCE note_id_seq;