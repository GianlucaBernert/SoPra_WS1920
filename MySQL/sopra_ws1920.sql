-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='';

-- -----------------------------------------------------
-- Schema popcorns
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema popcorns
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `popcorns` DEFAULT CHARACTER SET utf8 ;
USE `popcorns` ;

-- -----------------------------------------------------
-- Table `popcorns`.`BusinessObject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popcorns`.`BusinessObject` (
  `bo_id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`bo_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `popcorns`.`BusinessOwnership`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popcorns`.`BusinessOwnership` (
  `owner_id` INT NOT NULL AUTO_INCREMENT,
  `bo_id` INT NOT NULL,
  PRIMARY KEY (`owner_id`, `bo_id`),
  INDEX `fk_BusinessOwnership_BusinessObject_idx` (`bo_id` ASC) VISIBLE,
  CONSTRAINT `fk_BusinessOwnership_BusinessObject`
    FOREIGN KEY (`bo_id`)
    REFERENCES `popcorns`.`BusinessObject` (`bo_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `popcorns`.`Person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popcorns`.`Person` (
  `bo_id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `isAdmin` INT NULL,
  PRIMARY KEY (`bo_id`),
  CONSTRAINT `fk_Person_BusinessOwnership1`
    FOREIGN KEY (`bo_id`)
    REFERENCES `popcorns`.`BusinessOwnership` (`owner_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `popcorns`.`Cinema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popcorns`.`Cinema` (
  `bo_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `postCode` VARCHAR(45) NULL,
  `street` VARCHAR(45) NULL,
  `streetNo` VARCHAR(45) NULL,
  `owner_id` INT NOT NULL,
  PRIMARY KEY (`bo_id`, `owner_id`),
  INDEX `fk_Cinema_Person1_idx` (`owner_id` ASC) VISIBLE,
  CONSTRAINT `fk_Cinema_Person1`
    FOREIGN KEY (`owner_id`)
    REFERENCES `popcorns`.`Person` (`bo_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `popcorns`.`Movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popcorns`.`Movie` (
  `bo_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `genre` VARCHAR(45) NULL,
  `description` LONGTEXT NULL,
  `owner_id` INT NOT NULL,
  PRIMARY KEY (`bo_id`, `owner_id`),
  INDEX `fk_Movie_Person1_idx` (`owner_id` ASC) VISIBLE,
  CONSTRAINT `fk_Movie_Person1`
    FOREIGN KEY (`owner_id`)
    REFERENCES `popcorns`.`Person` (`bo_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `popcorns`.`Group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popcorns`.`Group` (
  `bo_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `owner_id` INT NOT NULL,
  PRIMARY KEY (`bo_id`, `owner_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `popcorns`.`Survey`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popcorns`.`Survey` (
  `bo_id` INT NOT NULL AUTO_INCREMENT,
  `startDate` DATETIME NULL,
  `endDate` DATETIME NULL,
  `owner_id` INT NOT NULL,
  `groupFK` INT NOT NULL,
  PRIMARY KEY (`bo_id`, `owner_id`, `groupFK`),
  INDEX `fk_Survey_Person1_idx` (`owner_id` ASC) VISIBLE,
  INDEX `fk_Survey_Group1_idx` (`groupFK` ASC) VISIBLE,
  CONSTRAINT `fk_Survey_Person1`
    FOREIGN KEY (`owner_id`)
    REFERENCES `popcorns`.`Person` (`bo_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Survey_Group1`
    FOREIGN KEY (`groupFK`)
    REFERENCES `popcorns`.`Group` (`bo_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `popcorns`.`SurveyEntry`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popcorns`.`SurveyEntry` (
  `bo_id` INT NOT NULL AUTO_INCREMENT,
  `surveyFK` INT NOT NULL,
  PRIMARY KEY (`bo_id`, `surveyFK`),
  INDEX `fk_SurveyEntry_Survey1_idx` (`surveyFK` ASC) VISIBLE,
  CONSTRAINT `fk_SurveyEntry_Survey1`
    FOREIGN KEY (`surveyFK`)
    REFERENCES `popcorns`.`Survey` (`bo_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `popcorns`.`Vote`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popcorns`.`Vote` (
  `bo_id` INT NOT NULL AUTO_INCREMENT,
  `votingWeight` INT NULL,
  `owner_id` INT NOT NULL,
  `surveyFK` INT NOT NULL,
  PRIMARY KEY (`bo_id`, `owner_id`, `surveyFK`),
  INDEX `fk_Vote_Person1_idx` (`owner_id` ASC) VISIBLE,
  INDEX `fk_Vote_SurveyEntry1_idx` (`surveyFK` ASC) VISIBLE,
  CONSTRAINT `fk_Vote_Person1`
    FOREIGN KEY (`owner_id`)
    REFERENCES `popcorns`.`Person` (`bo_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Vote_SurveyEntry1`
    FOREIGN KEY (`surveyFK`)
    REFERENCES `popcorns`.`SurveyEntry` (`bo_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `popcorns`.`Screening`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popcorns`.`Screening` (
  `bo_id` INT NOT NULL AUTO_INCREMENT,
  `screeningdayTime` DATETIME NULL,
  `owner_id` INT NOT NULL,
  `cinemaFK` INT NOT NULL,
  `movieFK` INT NOT NULL,
  `surveyEntryFK` INT NOT NULL,
  PRIMARY KEY (`bo_id`, `owner_id`, `cinemaFK`, `movieFK`, `surveyEntryFK`),
  INDEX `fk_Screening_Person1_idx` (`owner_id` ASC) VISIBLE,
  INDEX `fk_Screening_Cinema1_idx` (`cinemaFK` ASC) VISIBLE,
  INDEX `fk_Screening_Movie1_idx` (`movieFK` ASC) VISIBLE,
  INDEX `fk_Screening_SurveyEntry1_idx` (`surveyEntryFK` ASC) VISIBLE,
  CONSTRAINT `fk_Screening_Person1`
    FOREIGN KEY (`owner_id`)
    REFERENCES `popcorns`.`Person` (`bo_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Screening_Cinema1`
    FOREIGN KEY (`cinemaFK`)
    REFERENCES `popcorns`.`Cinema` (`bo_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Screening_Movie1`
    FOREIGN KEY (`movieFK`)
    REFERENCES `popcorns`.`Movie` (`bo_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Screening_SurveyEntry1`
    FOREIGN KEY (`surveyEntryFK`)
    REFERENCES `popcorns`.`SurveyEntry` (`bo_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `popcorns`.`Membership`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popcorns`.`Membership` (
  `bo_id` INT NOT NULL,
  `owner_id` INT NOT NULL,
  PRIMARY KEY (`bo_id`, `owner_id`),
  INDEX `fk_Person_has_Group_Group1_idx` (`owner_id` ASC) VISIBLE,
  INDEX `fk_Person_has_Group_Person1_idx` (`bo_id` ASC) VISIBLE,
  CONSTRAINT `fk_Person_has_Group_Person1`
    FOREIGN KEY (`bo_id`)
    REFERENCES `popcorns`.`Person` (`bo_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Person_has_Group_Group1`
    FOREIGN KEY (`owner_id`)
    REFERENCES `popcorns`.`Group` (`bo_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
