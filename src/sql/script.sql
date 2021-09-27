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
    currency         varchar default '$'::character varying,
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


