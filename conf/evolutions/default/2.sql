# Users schema

# --- !Ups

create table user0 (
  email                         varchar(255) not null primary key,
  name                          varchar(255) not null,
  password                      varchar(255) not null,
  dropbox_uid                   varchar(255),
  dropbox_access_token_key      varchar(255),
  dropbox_access_token_secret   varchar(255)
);

# --- !Downs

DROP TABLE IF EXISTS user0;