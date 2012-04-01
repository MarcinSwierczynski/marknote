# Users schema

# --- !Ups

create table user (
  email                     varchar(255) not null primary key,
  name                      varchar(255) not null,
  password                  varchar(255) not null
);

# --- !Downs

DROP TABLE IF EXISTS user;;