SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for seller
-- ----------------------------
DROP TABLE IF EXISTS `seller`;
CREATE TABLE `seller` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(120) NOT NULL COMMENT '手机号',
  `password` varchar(120) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `xvp_store_id` bigint(11) DEFAULT NULL COMMENT '小V铺的店铺id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=86358 DEFAULT CHARSET=utf8mb4 COMMENT='卖家账户信息';
