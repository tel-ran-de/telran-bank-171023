--liquibase formatted sql
--changeset marat:1

create table account (
    id bigint not null primary key
);