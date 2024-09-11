
-- -----------------------------------------------------
-- Table `itpal`.`Bank`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itpal`.`Bank` (
  `bank_name` VARCHAR(45) NOT NULL,
  `bank_type` VARCHAR(45) NULL,
  PRIMARY KEY (`bank_name`)
  );


-- -----------------------------------------------------
-- Table `itpal`.`Deposit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itpal`.`Deposit` (
  `D_id` INT NOT NULL,
  `product_name` VARCHAR(45) NOT NULL,
  `pre_tax_rate` DECIMAL NOT NULL,
  `after_tax_rate` DECIMAL NOT NULL,
  `target` VARCHAR(45) NOT NULL,
  `cal_method` VARCHAR(45) NOT NULL,
  `prime_cond` VARCHAR(45) NOT NULL,
  `D_url` VARCHAR(200) NOT NULL,
  `register_type` VARCHAR(45) NOT NULL,
  `deposit_type` VARCHAR(45) NOT NULL,
  `Bank_bank_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`D_id`, `Bank_bank_name`),
  INDEX `fk_Deposit_Bank1_idx` (`Bank_bank_name` ASC) VISIBLE,
  CONSTRAINT `fk_Deposit_Bank1`
    FOREIGN KEY (`Bank_bank_name`)
    REFERENCES `itpal`.`Bank` (`bank_name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `itpal`.`Card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itpal`.`Card` (
  `card_name` VARCHAR(45) NOT NULL,
  `img_url` VARCHAR(200) NULL,
  `c_url` VARCHAR(200) NULL,
  `company_name` VARCHAR(45) NULL,
  `card_detail` VARCHAR(200) NULL,
  `annual_fee` INT NULL,
  `card_type` VARCHAR(45) NULL,
  PRIMARY KEY (`card_name`)
  );


-- -----------------------------------------------------
-- Table `itpal`.`Benefit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itpal`.`Benefit` (
  `cafe` INT NULL,
  `mobile` INT NULL,
  `transport` INT NULL,
  `culture` INT NULL,
  `shopping` INT NULL,
  `travel` INT NULL,
  `digital_content` INT NULL,
  `cvs` INT NULL,
  `Card_card_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Card_card_name`),
  CONSTRAINT `fk_Benefit_Card1`
    FOREIGN KEY (`Card_card_name`)
    REFERENCES `itpal`.`Card` (`card_name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `itpal`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itpal`.`User` (
  `user_id` VARCHAR(45) NOT NULL,
  `user_name` VARCHAR(45) NULL,
  `user_pwd` VARCHAR(45) NULL,
  `phone_num` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `birthday` DATE NULL,
  PRIMARY KEY (`user_id`)
  );


-- -----------------------------------------------------
-- Table `itpal`.`Payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itpal`.`Payment` (
  `P_id` INT NOT NULL,
  `date` DATE NULL,
  `card_num` VARCHAR(45) NULL,
  `store` VARCHAR(45) NULL,
  `pay` INT NULL,
  `category` VARCHAR(45) NULL,
  `User_user_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`P_id`, `User_user_id`),
  INDEX `fk_Payment_User_idx` (`User_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_Payment_User`
    FOREIGN KEY (`User_user_id`)
    REFERENCES `itpal`.`User` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );


-- -----------------------------------------------------
-- Table `itpal`.`Account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itpal`.`Account` (
  `account_num` VARCHAR(45) NOT NULL,
  `account_type` VARCHAR(45) NULL,
  `bank_name` VARCHAR(45) NULL,
  `balance` INT NULL,
  `User_user_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`account_num`, `User_user_id`),
  INDEX `fk_Account_User1_idx` (`User_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_Account_User1`
    FOREIGN KEY (`User_user_id`)
    REFERENCES `itpal`.`User` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);