use mydb;

drop table if exists category, item;

create table category(
	id int primary key not null auto_increment,
	name varchar(50) not null unique
);

create table item(
	id int primary key not null auto_increment,
	category_id int not null,
	name varchar(50) not null unique,
	price int not null,

	foreign key(category_id) references category(id) on delete cascade
);