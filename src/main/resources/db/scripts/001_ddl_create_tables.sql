--liquibase formatted sql
--changeset Katerina163:1
create table client
(
    id        UUID primary key,
    full_name varchar(255) not null,
    phone     BIGINT      unique not null,
    email     varchar(255) unique not null,
    passport  BIGINT unique not null,
    password  varchar(255) not null
);
--rollback drop table client;

--changeset Katerina163:2
create table bank
(
    id   UUID primary key,
    name varchar(255) unique not null,
    password varchar(255) not null
);
--rollback drop table client;

--changeset Katerina163:3
create table credit
(
    id            UUID primary key,
    limit         BIGINT  not null,
    interest_rate INTEGER not null,
    bank_id       UUID not null references BANK(ID)
);
--rollback drop table credit;

--changeset Katerina163:4
create table loan_offer
(
    id          UUID primary key,
    client_id   UUID   not null references CLIENT(ID),
    credit_id   UUID   not null references CREDIT(ID),
    month       DATE   not null,
    loan_amount BIGINT not null
);
--rollback drop table loan_offer;