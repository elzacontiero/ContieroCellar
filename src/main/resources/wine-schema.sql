drop table if exists wine cascade;

CREATE TABLE `wine` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `number_of_bottles` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `producer` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `year` int DEFAULT NULL,
  PRIMARY KEY (`id`)
);
