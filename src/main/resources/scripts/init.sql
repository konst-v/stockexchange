CREATE SCHEMA stockexchange;
CREATE USER stockexchange WITH password 'stockexchange';
GRANT USAGE ON SCHEMA stockexchange TO stockexchange;

SET search_path TO stockexchange;

create table item
(
    id              bigserial primary key,
    version         numeric not null,
    name            varchar(64) not null,
    price           numeric(12, 2) not null,
    quantity        numeric not null,
    status          varchar(10)
);

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA stockexchange TO stockexchange;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA stockexchange TO stockexchange;
