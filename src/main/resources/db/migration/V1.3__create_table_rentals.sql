create table rentals(
    id serial not null primary key,
    rental_date date not null,
    rental_start date not null,
    rental_end date not null,
    start_km integer not null,
    end_km integer null,
    hourly_rate numeric(9,2),
    observations varchar(255),
    customer_id integer not null,
    vehicle_id integer not null,
    constraint pk_rental_customer foreign key(customer_id) references customers(id),
    constraint pk_rental_vehicle foreign key(vehicle_id) references vehicles(id)
);