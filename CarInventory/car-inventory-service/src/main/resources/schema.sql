create schema if not exists car_inventory;
use car_inventory;

create table if not exists car (
	id int not null auto_increment primary key,
    vin varchar(20) not null,
    make varchar(20) not null,
    model varchar(20) not null,
    year varchar(4) not null,
    color varchar(20) not null
);