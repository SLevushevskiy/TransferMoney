CREATE SCHEMA `st4db` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;

CREATE TABLE `st4db`.`user_role` (
  `idUserRole` INT NOT NULL,
  `rank` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUserRole`),
  UNIQUE INDEX `rank_UNIQUE` (`rank` ASC));

CREATE TABLE `st4db`.`user_status` (
  `idUser_status` INT NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUser_status`),
  UNIQUE INDEX `status_UNIQUE` (`status` ASC));

CREATE TABLE `st4db`.`user` (
  `idUser` INT NOT NULL,
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
  `idAcccount_status` INT NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idAcccount_status`),
  UNIQUE INDEX `status_UNIQUE` (`status` ASC));

CREATE TABLE `st4db`.`account_name` (
  `idAccount_name` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idAccount_name`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));

CREATE TABLE `st4db`.`account` (
  `idAccount` INT NOT NULL,
  `amound` DECIMAL NOT NULL,
  `client_id` INT NOT NULL,
  `account_status_id` INT NOT NULL,
  `account_name_id` INT NOT NULL,
  PRIMARY KEY (`idAccount`),
  INDEX `fk_client_id_idx` (`client_id` ASC),
  INDEX `fk_account_status_id_idx` (`account_status_id` ASC),
  INDEX `fk_account_name_id_idx` (`account_name_id` ASC),
  CONSTRAINT `fk_client_id`
    FOREIGN KEY (`client_id`)
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
  `idPayment_status` INT NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPayment_status`),
  UNIQUE INDEX `status_UNIQUE` (`status` ASC));

CREATE TABLE `st4db`.`payment_type` (
  `idPayment_type` INT NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPayment_type`));

CREATE TABLE `st4db`.`payment` (
  `idPayment` INT NOT NULL,
  `date` DATETIME NOT NULL,
  `total` DECIMAL NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `account_id` INT NOT NULL,
  `status_id` INT NOT NULL,
  `type_id` INT NOT NULL,
  PRIMARY KEY (`idPayment`),
  INDEX `fk_account_id_idx` (`account_id` ASC),
  INDEX `fk_status_id_idx` (`status_id` ASC),
  INDEX `fk_type_id_idx` (`type_id` ASC),
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
  CONSTRAINT `fk_type_id`
    FOREIGN KEY (`type_id`)
    REFERENCES `st4db`.`payment_type` (`idPayment_type`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

