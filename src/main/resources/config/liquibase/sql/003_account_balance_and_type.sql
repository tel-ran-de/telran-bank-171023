--liquibase formatted sql
--changeset andrii:3

alter table account
    add column type varchar(256) default 'checkingAccount';

alter table account
    add column balance decimal default 0;