CREATE TABLE IF NOT EXISTS `user_register` (
  `user_id` int AUTO_INCREMENT PRIMARY KEY,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `ip_address` varchar(20) NOT NULL,
  `created_at` date NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `uuid` varchar(100) DEFAULT NULL,
    `city` varchar(100) DEFAULT NULL,
        `country` varchar(100) DEFAULT NULL
);