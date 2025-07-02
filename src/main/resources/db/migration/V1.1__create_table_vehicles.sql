create table if not exists vehicles(
    id serial not null primary key,
    name varchar(50) not null,
    year integer not null,
    color varchar(50) not null,
    manufacturer_id integer not null,
    created_at timestamp not null,
    updated_at timestamp not null,
    constraint fk_vehicle_manufacturer foreign key(manufacturer_id) references manufacturers(id)
);