

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
  PRIMARY KEY (`user_id`)
 );
 
 desc user;


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

desc account;

-- -----------------------------------------------------
-- Table `itpal`.`bank`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itpal`.`bank` (
  `bank_name` VARCHAR(45) NOT NULL,
  `bank_type` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`bank_name`));

desc bank;

-- -----------------------------------------------------
-- Table `itpal`.`card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itpal`.`card` (
  `card_name` VARCHAR(45) NOT NULL,
  `img_url` VARCHAR(200) NULL DEFAULT NULL,
  `c_url` VARCHAR(200) NULL DEFAULT NULL,
  `company_name` VARCHAR(45) NULL DEFAULT NULL,
  `card_detail` VARCHAR(200) NULL DEFAULT NULL,
  `annual_fee` INT NULL DEFAULT NULL,
  `card_type` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`card_name`)
);

desc card;

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
  `Card_card_name` VARCHAR(45) NOT NULL,
  CONSTRAINT `fk_Benefit_Card1`
    FOREIGN KEY (`Card_card_name`)
    REFERENCES `itpal`.`card` (`card_name`)
);

desc benefit;
-- -----------------------------------------------------
-- Table `itpal`.`deposit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itpal`.`deposit` (
  `D_id` INT NOT NULL,
  `product_name` VARCHAR(45) NOT NULL,
  `pre_tax_rate` DECIMAL(10,0) NOT NULL,
  `after_tax_rate` DECIMAL(10,0) NOT NULL,
  `target` VARCHAR(45) NOT NULL,
  `cal_method` VARCHAR(45) NOT NULL,
  `prime_cond` VARCHAR(45) NOT NULL,
  `D_url` VARCHAR(200) NOT NULL,
  `register_type` VARCHAR(45) NOT NULL,
  `deposit_type` VARCHAR(45) NOT NULL,
  `Bank_bank_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`D_id`),
  CONSTRAINT `fk_Deposit_Bank1`
    FOREIGN KEY (`Bank_bank_name`)
    REFERENCES `itpal`.`bank` (`bank_name`)
);

desc deposit;

-- -----------------------------------------------------
-- Table `itpal`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itpal`.`payment` (
  `P_id` INT NOT NULL,
  `date` DATE NULL DEFAULT NULL,
  `card_num` VARCHAR(45) NULL DEFAULT NULL,
  `store` VARCHAR(45) NULL DEFAULT NULL,
  `pay` INT NULL DEFAULT NULL,
  `category` VARCHAR(45) NULL DEFAULT NULL,
  `User_user_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`P_id`),
  CONSTRAINT `fk_Payment_User`
    FOREIGN KEY (`User_user_id`)
    REFERENCES `itpal`.`user` (`user_id`)
 );
 
 desc payment;