DROP TABLE IF EXISTS `runes`;
CREATE TABLE `runes` (
  `obj_id` int(11) NOT NULL,
  `expire_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

