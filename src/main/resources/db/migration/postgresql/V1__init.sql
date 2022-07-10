create table purchases(
    id            bigserial not null,
    age           int4,
    amount        numeric(19, 2),
    count         int4      not null,
    lastname      varchar(255),
    name          varchar(255),
    purchase_date date,
    purchase_item varchar(255),
    primary key (id)
);

create table users(
    id       bigserial not null,
    login    varchar(255),
    password varchar(255),
    role     varchar(255),
    primary key (id)
);

insert into users (login, password, role) values
('Bob', '$2a$12$skcOfSuKKVw.2hxymPMxae3P5hbCVPgKuoaKm/nL.I63hQRanXe5q', 'ROLE_ADMIN'),
('Mike', '$2a$12$skcOfSuKKVw.2hxymPMxae3P5hbCVPgKuoaKm/nL.I63hQRanXe5q', 'ROLE_USER');


insert into purchases (name, lastname, age, count, amount, purchase_item, purchase_date) values
('Bob', 'Winner', 18, 15, 100.55, 'JUICER', '2022-07-08'),
('Mike', 'Scott', 20, 25, 12.55, 'SMARTPHONE', '2022-07-07'),
('Bob', 'Winner', 18, 20, 14.30, 'HEADPHONES', '2022-07-06'),
('Michael', 'Jackson', 24, 13, 10.33, 'JUICER', '2022-07-01'),
('Tommy', 'Shelby', 25, 50, 34.23, 'TV', '2022-06-01'),
('Harry', 'Potter', 18, 34, 46.34, 'KEYBOARD', '2022-05-01'),
('Ron', 'Wisly', 15, 10, 546.62, 'TV', '2022-03-01'),
('Martin', 'Grey', 18, 5, 34.77, 'KEYBOARD', '2022-01-01');