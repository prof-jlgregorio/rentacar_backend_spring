create table if not exists manufacturers (
    id serial primary key,
    name varchar(50) not null,
    country varchar(50) not null,
    created_at timestamp not null,
    updated_at timestamp not null
);