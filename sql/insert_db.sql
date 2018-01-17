INSERT INTO `st4db`.`account_status` (`idAccount_status`, `status`) VALUES ('1', 'active');
INSERT INTO `st4db`.`account_status` (`idAccount_status`, `status`) VALUES ('2', 'blocked');

INSERT INTO `st4db`.`account_name` (`idAccount_name`, `name`) VALUES ('1', 'Card for payments');
INSERT INTO `st4db`.`account_name` (`idAccount_name`, `name`) VALUES ('2', 'Deposit card');

INSERT INTO `st4db`.`payment_type` (`idPayment_type`, `type`) VALUES ('1', 'debit');
INSERT INTO `st4db`.`payment_type` (`idPayment_type`, `type`) VALUES ('2', 'credit');

INSERT INTO `st4db`.`payment_status` (`idPayment_status`, `status`) VALUES ('1', 'prepared');
INSERT INTO `st4db`.`payment_status` (`idPayment_status`, `status`) VALUES ('2', 'sent');

INSERT INTO `st4db`.`payment_name` (`idpayment_name`, `payment_name`, `payment_type_id`) VALUES ('1', 'Recharge account', '1');
INSERT INTO `st4db`.`payment_name` (`idpayment_name`, `payment_name`, `payment_type_id`) VALUES ('2', 'Transfer to the card', '2');

INSERT INTO `st4db`.`user_role` (`idUserRole`, `rank`) VALUES ('1', 'user');
INSERT INTO `st4db`.`user_role` (`idUserRole`, `rank`) VALUES ('2', 'admin');

INSERT INTO `st4db`.`user_status` (`idUser_status`, `status`) VALUES ('1', 'active');
INSERT INTO `st4db`.`user_status` (`idUser_status`, `status`) VALUES ('2', 'blocked');
