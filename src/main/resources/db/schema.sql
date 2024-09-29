create table if not exists book (
  id int not null primary key auto_increment,
  title varchar(100) not null,
  author varchar(50) not null,
  price number(6,2) not null,
  category varchar(10) not null,
  create_time date,
  modify_time date
);

create table if not exists shopping_cart (
  id int not null primary key auto_increment,
  goods_id varchar(100) not null,
  goods_quantity number(8,2) not null,
  create_time date,
  modify_time date
);
