drop table if exists prices;

create table prices (
    id varchar(36) not null,
    brand_id int not null,
    start_date timestamp not null,
    end_date timestamp not null,
    price_list int not null,
    product_id int not null,
    priority int not null,
    price decimal(10,2) not null,
    currency varchar(3) not null,
    primary key (id)
);

insert into prices (id, brand_id, start_date, end_date, price_list, product_id, priority, price, currency)
values ('98f732e6-3ec0-4b53-939e-15683926c60e', 1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR');

insert into prices (id, brand_id, start_date, end_date, price_list, product_id, priority, price, currency)
values ('08489708-5481-42f4-b4d1-2a972298c420', 1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR');

insert into prices (id, brand_id, start_date, end_date, price_list, product_id, priority, price, currency)
values ('761324a2-9cba-4cd8-b947-9c5cc7ce167f', 1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1, 30.50, 'EUR');

insert into prices (id, brand_id, start_date, end_date, price_list, product_id, priority, price, currency)
values ('525a8d7f-5b5d-4989-af10-eee51dea901c', 1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 1, 38.95, 'EUR');
