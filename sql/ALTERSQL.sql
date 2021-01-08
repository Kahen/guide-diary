ALTER TABLE `guide_diary`.`blog_img` MODIFY COLUMN `img_url` varchar(8192) NOT NULL DEFAULT '' COMMENT '图片URL';
ALTER TABLE `guide_diary`.`diary` ADD COLUMN `img_url_id` varchar(32) NULL COMMENT '图片URLid';
