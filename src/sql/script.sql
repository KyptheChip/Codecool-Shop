ALTER TABLE IF EXISTS ONLY products
    DROP CONSTRAINT IF EXISTS products_categories__fk,
    DROP CONSTRAINT IF EXISTS products_suppliers__fk;

DROP TABLE IF EXISTS suppliers;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS products;

create table suppliers
(
    id            serial
        constraint suppliers_pk
            primary key,
    supplier_name varchar
);


create unique index suppliers_id_uindex
    on suppliers (id);

create table categories
(
    id            serial
        constraint categories_pk
            primary key,
    category_name varchar
);

create table products
(
    id               serial
        constraint products_pk
            primary key,
    product_name     varchar,
    description      text,
    price            integer,
    currency         varchar default 'USD'::character varying,
    product_category integer
        constraint products_categories__fk
            references categories,
    product_supplier integer
        constraint products_suppliers__fk
            references suppliers
);

create unique index products_id_uindex
    on products (id);

create unique index categories_id_uindex
    on categories (id);

insert into categories (id, category_name)
values (1, 'Tablets'),
       (2, 'Phones'),
       (3, 'TVs'),
       (4, 'Tabletop Games'),
       (5, 'Laptops');

insert into suppliers (id, supplier_name)
values (1, 'Amazon'),
       (2, 'Lenovo'),
       (3, 'Samsung'),
       (4, 'LG'),
       (5, 'Sony'),
       (6, 'Apple');

insert into products (id, product_name, description, price, product_category, product_supplier)
values (1, 'Amazon Fire', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 49.9, 1, 1),
       (2, 'Lenovo IdeaPad Mix 700', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 479, 1, 2),
       (3, 'Amazon Fire HD 8', 'Amazon''s latest Fire HD 8 tablet is a great value for media consumption.', 89, 1, 1),
       (4, 'Samsung Galaxy S8', 'Testing in progress', 777, 2, 3),
       (5, 'LG Nanocell', 'Self-lit pixels. Spectacular picture quality. A lot of design possibilities. Latest cutting edge technologies.', 1999, 3, 4),
       (6, 'Sony BRAVIA XR A90J', 'Revolutionary OLED images. Advanced speakers and Cognitive Processor XR. Thousands of movies and shows, voice accessible.', 2847, 3, 5),
       (7, 'Cards Against Humanity', 'Cards Against Humanity is a party game for horrible people.', 50.82, 4, 1),
       (8, 'Dell Latitude 3400', 'Dell Latitude 3400 ultraportable laptop with Intel Core i5-8265U processor up to 3.90 GHz, 14, Full HD, 8GB, 256GB SSD, Intel UHD Graphics, Ubuntu, Black', 838.83, 5, 1),
       (9, 'Dell Latitude 3400', 'Redesigned and ready for professional use. A powerful and productive device. Beauty in every detail.', 644, 5, 1),
       (10, 'SAMSUNG Galaxy Tab A7', 'All the power you need for an epic game. You store more than you like. Wonderful screen and a rich sound', 221.87, 1, 3),
       (11, 'SAMSUNG Galaxy Tab S7 FE', 'Your favorite shows, movies and videos, seen in a new perspective. The fastest processor of a Galaxy Tab. Smart battery that lasts all day.', 723.31, 1, 3),
       (12, 'iPhone 12 Pro', 'A superpowerful chip. 5G speed. An advanced dual-camera system. A tough Ceramic Shield. And a bright, beautiful OLED display. iPhone 12 has it all', 999, 2, 6),
       (13, 'OnePlus 8 Pro', 'Powerful 5G performance. Enhanced display. Has OxygenOS which is smart, fast and efficient, with intuitive functions designed especially for you.', 942, 2, 1),
       (14, 'Lenovo IdeaPad Flex 5', 'Extraordinary blend of performance and value. One of the best popularly priced 2-in-1 convertibles', 679, 1, 2),
       (15, 'Asus VivoBook 17 M712', 'Large, sunny screen. Stylish, modern design. Stylish, modern design', 649, 5, 1),
       (16, 'Xiaomi Mi 10T Pro', 'Significantly improved gaming experience. An AdaptiveSync smart display. High capacity battery (5000 mAh).', 599, 2, 1);


