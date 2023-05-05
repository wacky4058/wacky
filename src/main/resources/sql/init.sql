DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`       int          NOT NULL AUTO_INCREMENT,
    `userName` varchar(255) NOT NULL,
    `passWord` varchar(255) NULL DEFAULT NULL,
    `realName` varchar(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS `contacts`;
CREATE TABLE `contacts`
(
    `id`          int          NOT NULL AUTO_INCREMENT,
    `name`        varchar(255) NOT NULL DEFAULT '',
    `sex`         int NULL DEFAULT NULL,
    `address`     varchar(255) NULL DEFAULT NULL,
    `remark`      varchar(255) NULL DEFAULT NULL,
    `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `create_by`   int NULL DEFAULT NULL,
    `update_by`   int NULL DEFAULT NULL,
    `owner`       int NULL DEFAULT NULL,
    `delete_flag` int NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
        INDEX `inde_contacts_name`(`name` ASC)
);

