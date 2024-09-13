
-- -----------------------------------------------------
-- Table `itpal`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itpal`.`user` (
  `user_id` VARCHAR(45) NOT NULL,
  `user_name` VARCHAR(45) NULL DEFAULT NULL,
  `user_pwd` VARCHAR(45) NULL DEFAULT NULL,
  `phone_num` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `birthday` DATE NULL DEFAULT NULL,
  `budget_set` INT NULL,
  `save_set` INT NULL,
  PRIMARY KEY (`user_id`)
);


-- -----------------------------------------------------
-- Table `itpal`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itpal`.`account` (
  `account_num` VARCHAR(45) NOT NULL,
  `account_type` VARCHAR(45) NULL DEFAULT NULL,
  `bank_name` VARCHAR(45) NULL DEFAULT NULL,
  `balance` INT NULL DEFAULT NULL,
  `User_user_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`account_num`),
  CONSTRAINT `fk_Account_User1`
    FOREIGN KEY (`User_user_id`)
    REFERENCES `itpal`.`user` (`user_id`)
);


-- -----------------------------------------------------
-- Table `itpal`.`bank`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itpal`.`bank` (
  `bank_name` VARCHAR(45) NOT NULL,
  `bank_type` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`bank_name`)
);


-- -----------------------------------------------------
-- Table `itpal`.`card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itpal`.`card` (
  `card_no` INT NOT NULL AUTO_INCREMENT,
  `card_name` VARCHAR(50) NOT NULL,
  `img_url` VARCHAR(2000) NOT NULL,
  `c_url` VARCHAR(2000) NOT NULL,
  `company_name` VARCHAR(45) NOT NULL,
  `card_detail` VARCHAR(2000) NOT NULL,
  `annual_fee` INT NOT NULL,
  `card_type` VARCHAR(45) NOT NULL,
  `view` INT NOT NULL,
  PRIMARY KEY (`card_no`)
);


-- -----------------------------------------------------
-- Table `itpal`.`benefit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itpal`.`benefit` (
  `cafe` INT NULL DEFAULT NULL,
  `mobile` INT NULL DEFAULT NULL,
  `transport` INT NULL DEFAULT NULL,
  `culture` INT NULL DEFAULT NULL,
  `shopping` INT NULL DEFAULT NULL,
  `travel` INT NULL DEFAULT NULL,
  `digital_content` INT NULL DEFAULT NULL,
  `cvs` INT NULL DEFAULT NULL,
  `card_card_no` INT NOT NULL,
  CONSTRAINT `fk_benefit_card1`
    FOREIGN KEY (`card_card_no`)
    REFERENCES `itpal`.`card` (`card_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `itpal`.`deposit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itpal`.`deposit` (
  `D_no` INT NOT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(50) NOT NULL,
  `before_tax_rate` DECIMAL(2,2) NOT NULL,
  `after_tax_rate` DECIMAL(2,2) NOT NULL,
  `target` VARCHAR(45) NOT NULL,
  `cal_method` VARCHAR(45) NOT NULL,
  `prime_cond` VARCHAR(45) NOT NULL,
  `D_url` VARCHAR(2000) NOT NULL,
  `register_type` VARCHAR(45) NOT NULL,
  `deposit_type` VARCHAR(45) NOT NULL,
  `deposit_period` INT NOT NULL,
  `bank_bank_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`D_no`),
  CONSTRAINT `fk_deposit_bank1`
    FOREIGN KEY (`bank_bank_name`)
    REFERENCES `itpal`.`bank` (`bank_name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


-- -----------------------------------------------------
-- Table `itpal`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itpal`.`payment` (
  `P_no` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NULL DEFAULT NULL,
  `card_num` VARCHAR(45) NULL DEFAULT NULL,
  `store` VARCHAR(45) NULL DEFAULT NULL,
  `pay` INT NULL DEFAULT NULL,
  `category` VARCHAR(45) NULL DEFAULT NULL,
  `User_user_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`P_no`),
  CONSTRAINT `fk_Payment_User`
    FOREIGN KEY (`User_user_id`)
    REFERENCES `itpal`.`user` (`user_id`)
);

desc account;
desc bank;
desc benefit;
desc card;
desc deposit;
desc payment;
desc user;

