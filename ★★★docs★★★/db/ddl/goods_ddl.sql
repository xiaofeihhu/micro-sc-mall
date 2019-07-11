-- 商品系统设计参考：https://blog.csdn.net/yinghe_one/article/details/84110166
-- 商品表
CREATE TABLE `goods` (
  `goods_id` mediumint(8)  NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(100) NOT NULL COMMENT '商品名称',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品价格',
  `category_id` mediumint(8)  NOT NULL COMMENT '所属类目',
  `shop_id` mediumint(8)  NOT NULL COMMENT '归属店铺',
  `stock` int(11) NOT NULL COMMENT '库存',
  `goods_desc` longtext COMMENT '商品描述',
  `logo` varchar(150) NOT NULL DEFAULT '' COMMENT '商品logo',
  `sm_logo` varchar(150) NOT NULL DEFAULT '' COMMENT '商品缩略图logo',
  `is_on_sale` tinyint(3)  NOT NULL DEFAULT '1' COMMENT '是否上架：1：上架，0：下架',
  `is_delete` tinyint(3)  NOT NULL DEFAULT '0' COMMENT '是否已经删除，1：已经删除 0：未删除',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '修改时间',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 类目表
CREATE TABLE `category` (
  `cate_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cate_pid` bigint(20) DEFAULT NULL COMMENT '父id',
  `leaf` tinyint(4) NOT NULL COMMENT '是否叶子节点 1：是0：不是',
  `level` tinyint(4) NOT NULL COMMENT '层级',
  `title` varchar(64) NOT NULL COMMENT 'title',
  `root_cate_id` bigint(20) DEFAULT NULL COMMENT '根类目id',
  `order_seq` int(11) NOT NULL COMMENT '排序序列',
  `picture_url` varchar(255) DEFAULT NULL COMMENT '图片url',
  `need_audit` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否需要审核 1：是0：不是',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0'  COMMENT '状态0：正常 1：删除',
  `biz_type` varchar(64) NOT NULL COMMENT '平台类型',
  `language` varchar(64) DEFAULT 'zh' COMMENT '语言',
  `country` varchar(64) DEFAULT 'CN' COMMENT '国家',
  `extension` mediumtext COMMENT '扩展字段',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '修改时间',
  PRIMARY KEY (`cate_id`,`sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 类目属性表
CREATE TABLE `category_attr` (
  `cate_id` mediumint(8)  NOT NULL,
  `order_seq` int(3)  NOT NULL COMMENT '排序',
  `attr_id` mediumint(8) NOT NULL COMMENT '属性id',
  `attr_value` varchar(100)  NOT NULL COMMENT '属性值',
  `is_valid` tinyint(3)  NOT NULL DEFAULT '1' COMMENT '是否有效：1：有效，0：失效',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '修改时间',
  PRIMARY KEY (`cate_id`,`sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 属性字典表
CREATE TABLE `attr` (
  `attr_id` mediumint(8)  NOT NULL AUTO_INCREMENT,
  `attr_name` varchar(100)  NOT NULL COMMENT '属性名称',
  `attr_value` varchar(100)  NOT NULL COMMENT '属性值',
  `attr_value_remark` varchar(100)  NOT NULL COMMENT '属性值说明',
  `is_valid` tinyint(3)  NOT NULL DEFAULT '1' COMMENT '是否有效：1：有效，0：失效',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '修改时间',
  PRIMARY KEY (`attr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 店铺表

-- 商品快照表

-- 商品历史表
