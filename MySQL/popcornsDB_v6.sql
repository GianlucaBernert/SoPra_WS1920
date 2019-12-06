-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema popcorns
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema popcorns
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `popcorns` DEFAULT CHARACTER SET utf8 ;
USE `popcorns` ;

-- -----------------------------------------------------
-- Table `popcorns`.`businessobject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popcorns`.`businessobject` (
  `bo_id` INT(11) NOT NULL,
  `creationTimeStamp` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`bo_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `popcorns`.`person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popcorns`.`person` (
  `bo_id` INT(11) NOT NULL,
  `firstname` VARCHAR(45) NULL DEFAULT NULL,
  `lastname` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `isAdmin` INT(11) NULL DEFAULT NULL,
  `creationTimeStamp` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`bo_id`),
  INDEX `fk_person_businessobject1_idx` (`bo_id` ASC) VISIBLE,
  CONSTRAINT `fk_person_businessobject1`
    FOREIGN KEY (`bo_id`)
    REFERENCES `popcorns`.`businessobject` (`bo_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `popcorns`.`businessownership`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popcorns`.`businessownership` (
  `bo_id` INT(11) NOT NULL,
  `creationTimeStamp` TIMESTAMP NULL DEFAULT NULL,
  `personFK` INT(11) NOT NULL,
  PRIMARY KEY (`bo_id`),
  INDEX `fk_businessownership_person1_idx` (`personFK` ASC) VISIBLE,
  CONSTRAINT `fk_businessownership_businessobject`
    FOREIGN KEY (`bo_id`)
    REFERENCES `popcorns`.`businessobject` (`bo_id`),
  CONSTRAINT `fk_businessownership_person1`
    FOREIGN KEY (`personFK`)
    REFERENCES `popcorns`.`person` (`bo_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `popcorns`.`cinema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popcorns`.`cinema` (
  `bo_id` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `city` VARCHAR(45) NULL DEFAULT NULL,
  `postCode` VARCHAR(45) NULL DEFAULT NULL,
  `street` VARCHAR(45) NULL DEFAULT NULL,
  `streetNo` VARCHAR(45) NULL DEFAULT NULL,
  `creationTimeStamp` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`bo_id`),
  INDEX `fk_cinema_businessownership1_idx` (`bo_id` ASC) VISIBLE,
  CONSTRAINT `fk_cinema_businessownership1`
    FOREIGN KEY (`bo_id`)
    REFERENCES `popcorns`.`businessownership` (`bo_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `popcorns`.`cinemachain`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popcorns`.`cinemachain` (
  `bo_id` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `creationTimeStamp` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`bo_id`),
  CONSTRAINT `fk_cinemachain_businessownership1`
    FOREIGN KEY (`bo_id`)
    REFERENCES `popcorns`.`businessownership` (`bo_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `popcorns`.`group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popcorns`.`group` (
  `bo_id` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `creationTimeStamp` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`bo_id`),
  INDEX `fk_group_businessownership1_idx` (`bo_id` ASC) VISIBLE,
  CONSTRAINT `fk_group_businessownership1`
    FOREIGN KEY (`bo_id`)
    REFERENCES `popcorns`.`businessownership` (`bo_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `popcorns`.`membership`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popcorns`.`membership` (
  `groupFK` INT(11) NOT NULL,
  `personFK` INT(11) NOT NULL,
  PRIMARY KEY (`groupFK`, `personFK`),
  INDEX `fk_group_has_person_person1_idx` (`personFK` ASC) VISIBLE,
  INDEX `fk_group_has_person_group1_idx` (`groupFK` ASC) VISIBLE,
  CONSTRAINT `fk_group_has_person_group1`
    FOREIGN KEY (`groupFK`)
    REFERENCES `popcorns`.`group` (`bo_id`),
  CONSTRAINT `fk_group_has_person_person1`
    FOREIGN KEY (`personFK`)
    REFERENCES `popcorns`.`person` (`bo_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `popcorns`.`movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popcorns`.`movie` (
  `bo_id` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `genre` VARCHAR(45) NULL DEFAULT NULL,
  `description` LONGTEXT NULL DEFAULT NULL,
  `creationTimeStamp` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`bo_id`),
  INDEX `fk_movie_businessownership1_idx` (`bo_id` ASC) VISIBLE,
  CONSTRAINT `fk_movie_businessownership1`
    FOREIGN KEY (`bo_id`)
    REFERENCES `popcorns`.`businessownership` (`bo_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `popcorns`.`screening`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popcorns`.`screening` (
  `bo_id` INT(11) NOT NULL,
  `screeningdayTime` TIMESTAMP NULL DEFAULT NULL,
  `creationTimeStamp` TIMESTAMP NULL DEFAULT NULL,
  `cinemaFK` INT(11) NOT NULL,
  `movieFK` INT(11) NOT NULL,
  PRIMARY KEY (`bo_id`),
  INDEX `fk_screening_businessownership1_idx` (`bo_id` ASC) VISIBLE,
  INDEX `fk_screening_cinema1_idx` (`cinemaFK` ASC) VISIBLE,
  INDEX `fk_screening_movie1_idx` (`movieFK` ASC) VISIBLE,
  CONSTRAINT `fk_screening_businessownership1`
    FOREIGN KEY (`bo_id`)
    REFERENCES `popcorns`.`businessownership` (`bo_id`),
  CONSTRAINT `fk_screening_cinema1`
    FOREIGN KEY (`cinemaFK`)
    REFERENCES `popcorns`.`cinema` (`bo_id`),
  CONSTRAINT `fk_screening_movie1`
    FOREIGN KEY (`movieFK`)
    REFERENCES `popcorns`.`movie` (`bo_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `popcorns`.`survey`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popcorns`.`survey` (
  `bo_id` INT(11) NOT NULL,
  `startDate` TIMESTAMP NULL DEFAULT NULL,
  `endDate` TIMESTAMP NULL DEFAULT NULL,
  `creationTimeStamp` TIMESTAMP NULL DEFAULT NULL,
  `groupFK` INT(11) NOT NULL,
  PRIMARY KEY (`bo_id`),
  INDEX `fk_survey_businessownership1_idx` (`bo_id` ASC) VISIBLE,
  INDEX `fk_survey_group1_idx` (`groupFK` ASC) VISIBLE,
  CONSTRAINT `fk_survey_businessownership1`
    FOREIGN KEY (`bo_id`)
    REFERENCES `popcorns`.`businessownership` (`bo_id`),
  CONSTRAINT `fk_survey_group1`
    FOREIGN KEY (`groupFK`)
    REFERENCES `popcorns`.`group` (`bo_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `popcorns`.`surveyentry`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popcorns`.`surveyentry` (
  `bo_id` INT(11) NOT NULL,
  `surveyFK` INT(11) NOT NULL,
  `creationTimeStamp` TIMESTAMP NULL DEFAULT NULL,
  `screeningFK` INT(11) NOT NULL,
  PRIMARY KEY (`bo_id`),
  INDEX `fk_surveyentry_survey1_idx` (`surveyFK` ASC) VISIBLE,
  INDEX `fk_surveyentry_businessobject1_idx` (`bo_id` ASC) VISIBLE,
  INDEX `fk_surveyentry_screening1_idx` (`screeningFK` ASC) VISIBLE,
  CONSTRAINT `fk_surveyentry_businessobject1`
    FOREIGN KEY (`bo_id`)
    REFERENCES `popcorns`.`businessobject` (`bo_id`),
  CONSTRAINT `fk_surveyentry_screening1`
    FOREIGN KEY (`screeningFK`)
    REFERENCES `popcorns`.`screening` (`bo_id`),
  CONSTRAINT `fk_surveyentry_survey1`
    FOREIGN KEY (`surveyFK`)
    REFERENCES `popcorns`.`survey` (`bo_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `popcorns`.`vote`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popcorns`.`vote` (
  `bo_id` INT(11) NOT NULL,
  `votingWeight` INT(11) NULL DEFAULT NULL,
  `creationTimeStamp` TIMESTAMP NULL DEFAULT NULL,
  `surveyentryFK` INT(11) NOT NULL,
  PRIMARY KEY (`bo_id`),
  INDEX `fk_vote_surveyentry1_idx` (`surveyentryFK` ASC) VISIBLE,
  CONSTRAINT `fk_vote_businessownership1`
    FOREIGN KEY (`bo_id`)
    REFERENCES `popcorns`.`businessownership` (`bo_id`),
  CONSTRAINT `fk_vote_surveyentry1`
    FOREIGN KEY (`surveyentryFK`)
    REFERENCES `popcorns`.`surveyentry` (`bo_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
