CREATE SCHEMA `st4db` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;

CREATE TABLE `st4db`.`user_role` (
  `idUserRole` INT NOT NULL auto_increment,
  `rank` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUserRole`),
  UNIQUE INDEX `rank_UNIQUE` (`rank` ASC));

CREATE TABLE `st4db`.`user_status` (
  `idUser_status` INT NOT NULL auto_increment,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUser_status`),
  UNIQUE INDEX `status_UNIQUE` (`status` ASC));

CREATE TABLE `st4db`.`user` (
  `idUser` INT NOT NULL auto_increment,
  `Name` VARCHAR(45) NOT NULL,
  `Surname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `Role_id` INT NOT NULL,
  `UserStatus_id` INT NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_Role_id_idx` (`Role_id` ASC),
  INDEX `fk_UserStatus_id_idx` (`UserStatus_id` ASC),
  CONSTRAINT `fk_Role_id`
    FOREIGN KEY (`Role_id`)
    REFERENCES `st4db`.`user_role` (`idUserRole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_UserStatus_id`
    FOREIGN KEY (`UserStatus_id`)
    REFERENCES `st4db`.`user_status` (`idUser_status`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `st4db`.`acccount_status` (
  `idAcccount_status` INT NOT NULL auto_increment,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idAcccount_status`),
  UNIQUE INDEX `status_UNIQUE` (`status` ASC));

CREATE TABLE `st4db`.`account_name` (
  `idAccount_name` INT NOT NULL auto_increment,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idAccount_name`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));

CREATE TABLE `st4db`.`account` (
  `idAccount` INT NOT NULL auto_increment,
  `amound` DECIMAL NOT NULL,
  `end_date` DATE NOT NULL,
  `user_id` INT NOT NULL,
  `account_status_id` INT NOT NULL,
  `account_name_id` INT NOT NULL,
  PRIMARY KEY (`idAccount`),
  INDEX `fk_user_id_idx` (`user_id` ASC),
  INDEX `fk_account_status_id_idx` (`account_status_id` ASC),
  INDEX `fk_account_name_id_idx` (`account_name_id` ASC),
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `st4db`.`user` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_status_id`
    FOREIGN KEY (`account_status_id`)
    REFERENCES `st4db`.`acccount_status` (`idAcccount_status`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_name_id`
    FOREIGN KEY (`account_name_id`)
    REFERENCES `st4db`.`account_name` (`idAccount_name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `st4db`.`payment_status` (
  `idPayment_status` INT NOT NULL auto_increment,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPayment_status`),
  UNIQUE INDEX `status_UNIQUE` (`status` ASC));

CREATE TABLE `st4db`.`payment_type` (
  `idPayment_type` INT NOT NULL auto_increment,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPayment_type`));

CREATE TABLE `st4db`.`payment_name` (
  `idpayment_name` INT NOT NULL AUTO_INCREMENT,
  `payment_name` VARCHAR(45) NOT NULL,
  `payment_type_id` INT NOT NULL,
  PRIMARY KEY (`idpayment_name`),
  UNIQUE INDEX `payment_name_UNIQUE` (`payment_name` ASC),
  INDEX `fk_payment_type_id_idx` (`payment_type_id` ASC),
  CONSTRAINT `fk_payment_type_id`
    FOREIGN KEY (`payment_type_id`)
    REFERENCES `st4db`.`payment_type` (`idPayment_type`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `st4db`.`payment` (
  `idPayment` INT NOT NULL auto_increment,
  `date` DATETIME NOT NULL,
  `total` DECIMAL NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `account_id` INT NOT NULL,
  `status_id` INT NOT NULL,
  `payment_name_id` INT NOT NULL,
  PRIMARY KEY (`idPayment`),
  INDEX `fk_account_id_idx` (`account_id` ASC),
  INDEX `fk_status_id_idx` (`status_id` ASC),
  INDEX `fk_payment_name_id_idx` (`payment_name_id` ASC),
  CONSTRAINT `fk_account_id`
    FOREIGN KEY (`account_id`)
    REFERENCES `st4db`.`account` (`idAccount`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_status_id`
    FOREIGN KEY (`status_id`)
    REFERENCES `st4db`.`payment_status` (`idPayment_status`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_payment_name_id`
    FOREIGN KEY (`payment_name_id`)
    REFERENCES `st4db`.`payment_name` (`idpayment_name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


