CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `roles` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `usr` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `balance` (
  `id` bigint(20) NOT NULL,
  `balance` int(11) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `balance`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKi5y80j5fircow4fiahs5bmush` (`user_id`);

ALTER TABLE `usr`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `user_role`
  ADD KEY `FKfpm8swft53ulq2hl11yplpr5` (`user_id`);

ALTER TABLE `user_role`
  ADD CONSTRAINT `FKfpm8swft53ulq2hl11yplpr5` FOREIGN KEY (`user_id`) REFERENCES `usr` (`id`);

ALTER TABLE `balance`
  ADD CONSTRAINT `FKi5y80j5fircow4fiahs5bmush` FOREIGN KEY (`user_id`) REFERENCES `usr` (`id`);
