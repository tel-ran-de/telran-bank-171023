--liquibase formatted sql
--changeset marat:2

alter table account
    alter column id bigint auto_increment;
