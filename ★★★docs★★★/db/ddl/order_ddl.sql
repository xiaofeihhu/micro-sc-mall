-- 订单表
CREATE TABLE `order` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_uuid` varchar(45) DEFAULT NULL,
  `goods_id` int(11) DEFAULT NULL COMMENT '商品ID',
  `payment` decimal(14,2) DEFAULT NULL COMMENT '实付金额',
  `pay_type` tinyint(1) DEFAULT NULL COMMENT '支付类型：1 在线支付 2 货到付款',
  `post_fee` decimal(6,2) DEFAULT NULL,
  `status` tinyint(2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `pay_time` datetime DEFAULT NULL,
  `cosign_time` datetime DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime DEFAULT NULL COMMENT '交易关闭时间',
  `shipping_name` varchar(20) DEFAULT NULL COMMENT '物流名称',
  `shipping_code` varchar(45) DEFAULT NULL COMMENT '物流单号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;