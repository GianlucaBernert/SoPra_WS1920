-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema sopra_ws1920
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sopra_ws1920
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sopra_ws1920` DEFAULT CHARACTER SET utf8 ;
USE `sopra_ws1920` ;

-- -----------------------------------------------------
-- Table `sopra_ws1920`.`person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sopra_ws1920`.`person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `eMail` VARCHAR(100) NULL,
  `isAdmin` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sopra_ws1920`.`movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sopra_ws1920`.`movie` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` LONGTEXT NULL,
  `genre` VARCHAR(45) NULL,
  `personFK` INT NOT NULL,
  PRIMARY KEY (`id`, `personFK`),
  INDEX `fk_movie_person1_idx` (`personFK` ASC) VISIBLE,
  CONSTRAINT `fk_movie_person1`
    FOREIGN KEY (`personFK`)
    REFERENCES `sopra_ws1920`.`person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sopra_ws1920`.`group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sopra_ws1920`.`group` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sopra_ws1920`.`survey`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sopra_ws1920`.`survey` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `startDate` DATETIME NULL,
  `endDate` DATETIME NULL,
  `groupFK` INT NOT NULL,
  `personFK` INT NOT NULL,
  PRIMARY KEY (`id`, `groupFK`, `personFK`),
  INDEX `fk_survey_group1_idx` (`groupFK` ASC) VISIBLE,
  INDEX `fk_survey_person1_idx` (`personFK` ASC) VISIBLE,
  CONSTRAINT `fk_survey_group1`
    FOREIGN KEY (`groupFK`)
    REFERENCES `sopra_ws1920`.`group` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_survey_person1`
    FOREIGN KEY (`personFK`)
    REFERENCES `sopra_ws1920`.`person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sopra_ws1920`.`cinema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sopra_ws1920`.`cinema` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `postCode` VARCHAR(45) NULL,
  `street` VARCHAR(100) NULL,
  `streetNo` VARCHAR(45) NULL,
  `personFK` INT NOT NULL,
  PRIMARY KEY (`id`, `personFK`),
  INDEX `fk_cinema_person1_idx` (`personFK` ASC) VISIBLE,
  CONSTRAINT `fk_cinema_person1`
    FOREIGN KEY (`personFK`)
    REFERENCES `sopra_ws1920`.`person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sopra_ws1920`.`screening`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sopra_ws1920`.`screening` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NULL,
  `time` DATETIME NULL,
  `movieFK` INT NOT NULL,
  `cinemaFK` INT NOT NULL,
  `personFK` INT NOT NULL,
  PRIMARY KEY (`id`, `movieFK`, `cinemaFK`, `personFK`),
  INDEX `fk_screening_movie_idx` (`movieFK` ASC) VISIBLE,
  INDEX `fk_screening_cinema1_idx` (`cinemaFK` ASC) VISIBLE,
  INDEX `fk_screening_person1_idx` (`personFK` ASC) VISIBLE,
  CONSTRAINT `fk_screening_movie`
    FOREIGN KEY (`movieFK`)
    REFERENCES `sopra_ws1920`.`movie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_screening_cinema1`
    FOREIGN KEY (`cinemaFK`)
    REFERENCES `sopra_ws1920`.`cinema` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_screening_person1`
    FOREIGN KEY (`personFK`)
    REFERENCES `sopra_ws1920`.`person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sopra_ws1920`.`surveyentry`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sopra_ws1920`.`surveyentry` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `screeningFK` INT NOT NULL,
  `surveyFK` INT NOT NULL,
  PRIMARY KEY (`id`, `screeningFK`, `surveyFK`),
  INDEX `fk_surveyentry_screening1_idx` (`screeningFK` ASC) VISIBLE,
  INDEX `fk_surveyentry_survey1_idx` (`surveyFK` ASC) VISIBLE,
  CONSTRAINT `fk_surveyentry_screening1`
    FOREIGN KEY (`screeningFK`)
    REFERENCES `sopra_ws1920`.`screening` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_surveyentry_survey1`
    FOREIGN KEY (`surveyFK`)
    REFERENCES `sopra_ws1920`.`survey` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sopra_ws1920`.`vote`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sopra_ws1920`.`vote` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `votingWeight` INT NULL,
  `surveyentryFK` INT NOT NULL,
  `personFK` INT NOT NULL,
  PRIMARY KEY (`id`, `surveyentryFK`, `personFK`),
  INDEX `fk_vote_surveyentry1_idx` (`surveyentryFK` ASC) VISIBLE,
  INDEX `fk_vote_person1_idx` (`personFK` ASC) VISIBLE,
  CONSTRAINT `fk_vote_surveyentry1`
    FOREIGN KEY (`surveyentryFK`)
    REFERENCES `sopra_ws1920`.`surveyentry` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_vote_person1`
    FOREIGN KEY (`personFK`)
    REFERENCES `sopra_ws1920`.`person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sopra_ws1920`.`membership`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sopra_ws1920`.`membership` (
  `groupFK` INT NOT NULL,
  `personFK` INT NOT NULL,
  PRIMARY KEY (`groupFK`, `personFK`),
  INDEX `fk_group_has_person_person1_idx` (`personFK` ASC) VISIBLE,
  INDEX `fk_group_has_person_group1_idx` (`groupFK` ASC) VISIBLE,
  CONSTRAINT `fk_group_has_person_group1`
    FOREIGN KEY (`groupFK`)
    REFERENCES `sopra_ws1920`.`group` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_has_person_person1`
    FOREIGN KEY (`personFK`)
    REFERENCES `sopra_ws1920`.`person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
