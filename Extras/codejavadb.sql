CREATE TABLE `codejavadb`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fullname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone` BIGINT(10) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  `DOB` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);
