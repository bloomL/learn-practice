SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
                          `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
                          `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES (1, '一班');
INSERT INTO `class` VALUES (2, '二班');

-- ----------------------------
-- Table structure for classroom
-- ----------------------------
DROP TABLE IF EXISTS `classroom`;
CREATE TABLE `classroom`  (
                              `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
                              `class_id` int(11) NULL DEFAULT NULL,
                              `student_id` int(11) NULL DEFAULT NULL,
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classroom
-- ----------------------------
INSERT INTO `classroom` VALUES (1, 1, 1);
INSERT INTO `classroom` VALUES (2, 1, 2);
INSERT INTO `classroom` VALUES (3, 2, 3);
INSERT INTO `classroom` VALUES (4, 2, 4);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
                            `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
                            `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                            `age` tinyint(3) UNSIGNED NULL DEFAULT NULL,
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '点点', 16);
INSERT INTO `student` VALUES (2, '平平', 16);
INSERT INTO `student` VALUES (3, '美美', 16);
INSERT INTO `student` VALUES (4, '团团', 16);
INSERT INTO `student` VALUES (8, '缓缓', 20);

SET FOREIGN_KEY_CHECKS = 1;