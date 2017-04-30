-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bank_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bank_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bank_db` DEFAULT CHARACTER SET utf8 ;
USE `bank_db` ;

-- -----------------------------------------------------
-- Table `bank_db`.`accounts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bank_db`.`accounts` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `clientID` INT(11) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `money` FLOAT NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8
COMMENT = '	';


-- -----------------------------------------------------
-- Table `bank_db`.`administrator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bank_db`.`administrator` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bank_db`.`clients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bank_db`.`clients` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `id_card_nr` BIGINT(20) UNSIGNED NOT NULL,
  `numerical_code` BIGINT(20) UNSIGNED NOT NULL,
  `adress` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC),
  CONSTRAINT `fk_clients_accounts`
    FOREIGN KEY (`ID`)
    REFERENCES `bank_db`.`accounts` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bank_db`.`employee_activity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bank_db`.`employee_activity` (
  `employeeID` INT(11) NOT NULL,
  `date` DATE NOT NULL,
  `activity` VARCHAR(500) NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bank_db`.`desk_employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bank_db`.`desk_employee` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC),
  CONSTRAINT `fk_desk_employee_employee_activity1`
    FOREIGN KEY (`ID`)
    REFERENCES `bank_db`.`employee_activity` (`employeeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
