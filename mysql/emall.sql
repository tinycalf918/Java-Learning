create table t_user(
    id int primary key auto_increment comment '主键',
    user_id varchar(32) not null comment '用户id',
    user_name varchar(32) not null comment '用户名称',
    unique index uk_user_id(user_id)
) engine=InnoDB default charset=utf8 comment='用户表';

create table t_product(
    id int primary key auto_increment comment '主键',
    product_id varchar(32) not null comment '商品id',
    product_name varchar(32) not null comment '商品名称',
    price varchar(32) not null comment '商品价格',
    unique index uk_product_id(product_id)
) engine=InnoDB default charset=utf8 comment='商品表';

create table t_order(
    id int primary key auto_increment comment '主键',
    user_id varchar(32) not null comment '用户id',
    address varchar(32) not null comment '地址',
    money varchar(32) not null comment '订单总价',
) engine=InnoDB default charset=utf8 comment='订单表';

create table t_order_detail(
    id int primary key auto_increment comment '主键',
    order_id varchar(32) not null comment '订单id',
    product_id varchar(32) not null comment '商品id',
    count int not null default 0 comment '商品数量',
) engine=InnoDB default charset=utf8 comment='订单详情表';