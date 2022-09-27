--liquibase formatted sql

--changeset vsolovyev:sc-5591
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS accounts
(
    id      uuid DEFAULT uuid_generate_v4()
        constraint accounts_pkey primary key,
    name    varchar not null,
    balance numeric not null
);

CREATE TABLE IF NOT EXISTS cards
(
    id         uuid DEFAULT uuid_generate_v4()
        constraint cards_pkey primary key,
    name       varchar not null,
    account_id uuid    NOT NULL REFERENCES accounts (id) on delete cascade
);

CREATE TABLE IF NOT EXISTS payment_limits
(
    id      uuid DEFAULT uuid_generate_v4()
        constraint payment_limits_pkey primary key,
    type    varchar not null,
    card_id uuid    NOT NULL REFERENCES cards (id) on delete cascade,
    amount  numeric
);