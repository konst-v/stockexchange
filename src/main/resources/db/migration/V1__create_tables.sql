create table item
(
    id              bigserial primary key,
    version         numeric not null,
    name            varchar(64) not null,
    price           numeric(12, 2) not null,
    quantity        numeric not null,
    status          varchar(10)
);

create table transaction
(
    id              bigserial primary key,
    version         numeric not null,
    date_time       timestamp,
    price           numeric(12, 2) not null,
    quantity        numeric not null
);