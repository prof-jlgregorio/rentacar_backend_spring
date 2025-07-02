create table if not exists customers(
    id serial not null primary key,
    full_name varchar(50) not null,
    email varchar(50) not null,
    created_at timestamp not null,
    updated_at timestamp not null
);