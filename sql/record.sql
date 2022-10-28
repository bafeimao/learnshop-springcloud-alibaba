create table t_user
(
    id         bigint unsigned,
    t_username varchar(50),
    t_password varchar(64),
    t_phone    varchar(20),
    t_address  varchar(255)
) comment 'user';

create table t_product
(
    id              bigint unsigned,
    t_product_name  varchar(50),
    t_product_price decimal(10, 2),
    t_product_stock int(11)
) comment 'product';

create table t_order
(
    id            bigint unsigned,
    t_user_id     bigint(20),
    t_user_name   varchar(50),
    t_phone       varchar(20),
    t_address     varchar(255),
    t_total_price decimal(10, 2)
) comment 'order';

create table t_order_item
(
    id              bigint unsigned,
    t_order_id      bigint(20),
    t_product_id    bigint(20),
    t_product_name  varchar(50),
    t_product_price decimal(10, 2),
    t_number        int(11)
) comment 'order_item';

INSERT INTO `shop`.`t_user`(`id`, `t_username`, `t_password`, `t_phone`,
                            `t_address`)
VALUES (1001, 'binghe', 'c26be8aaf53b15054896983b43eb6a65',
        '13212345678', '北京');

INSERT INTO `shop`.`t_product`(`id`, `t_product_name`, `t_product_price`, `t_product_stock`)
VALUES (1001, '华为', 2399.00, 100);
INSERT INTO `shop`.`t_product`(`id`, `t_product_name`, `t_product_price`, `t_product_stock`)
VALUES (1002, '小米', 1999.00, 100);
INSERT INTO `shop`.`t_product`(`id`, `t_product_name`, `t_product_price`, `t_product_stock`)
VALUES (1003, 'iphone', 4999.00, 100);