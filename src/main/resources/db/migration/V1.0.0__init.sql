create table member
(
    id varchar(36) primary key,
    m_name varchar(100) not null,
    nickname varchar(100) not null,
    password varchar(1000) not null,
    create_at date not null
);

create table car_model
(
    id varchar(36) primary key,
    model varchar(100) not null,
    num int not null
);

create table t_order
(
    id varchar(36) primary key,
    m_id varchar(36) not  null,
    c_id varchar(36) not null,
    active boolean not null,
    create_at date not null
);

create index order_member_idx on t_order(m_id);
create index order_model_idx on t_order(c_id);

insert into car_model (id, model, num)
values
('ea895081-8670-4336-8597-b6565b1ec0bc', 'Toyota Camry', 2),
('1f025824-dedd-429b-b75d-750ef05bdb0b', 'BMW 650', 2);
