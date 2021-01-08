ALTER TABLE `guide_diary`.`blog_img` MODIFY COLUMN `img_url` varchar(8192) NOT NULL DEFAULT '' COMMENT '图片URL';
ADD COLUMN `day_timestamp` bigint(20) NOT NULL COMMENT '时间索引',
ADD INDEX `day_index`(`day_timestamp`);
