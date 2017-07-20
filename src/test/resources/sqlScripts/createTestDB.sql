-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema xamTest
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `xamTest`;
-- -----------------------------------------------------
-- Schema xamTest
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `xamTest` DEFAULT CHARACTER SET utf8 ;
USE `xamTest` ;

-- -----------------------------------------------------
-- Table `xamTest`.`country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `xamTest`.`country` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `xamTest`.`region`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `xamTest`.`region` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `country_id` INT UNSIGNED NOT NULL,
  `region` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_country_id_idx` (`country_id` ASC),
  CONSTRAINT `fk_region_country_id`
    FOREIGN KEY (`country_id`)
    REFERENCES `xamTest`.`country` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `xamTest`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `xamTest`.`user` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `region` INT UNSIGNED NULL,
  `login` VARCHAR(15) NOT NULL UNIQUE,
  `password` VARCHAR(64) NOT NULL,
  `reg_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_region_id_idx` (`region` ASC),
  CONSTRAINT `fk_user_region_id`
    FOREIGN KEY (`region`)
    REFERENCES `xamTest`.`region` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `xamTest`.`message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `xamTest`.`message` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT UNSIGNED NOT NULL,
  `date_message` TIMESTAMP NOT NULL,
  `message` VARCHAR(2048) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_id_idx` (`user_id` ASC),
  CONSTRAINT `fk_message_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `xamTest`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `xamTest`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `xamTest`.`comment` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT UNSIGNED NOT NULL,
  `message_id` BIGINT UNSIGNED NOT NULL,
  `date_comment` TIMESTAMP NOT NULL,
  `comment` VARCHAR(2048) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_id_idx` (`user_id` ASC),
  INDEX `fk_message_id_idx` (`message_id` ASC),
  CONSTRAINT `fk_comment_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `xamTest`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_message_id`
    FOREIGN KEY (`message_id`)
    REFERENCES `xamTest`.`message` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `xamTest`.`like`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `xamTest`.`like` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT UNSIGNED NOT NULL,
  `message_id` BIGINT UNSIGNED NULL,
  `comment_id` BIGINT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_id_idx` (`user_id` ASC),
  INDEX `fk_message_id_idx` (`message_id` ASC),
  INDEX `fk_comment_id_idx` (`comment_id` ASC),
  CONSTRAINT `fk_like_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `xamTest`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_like_message_id`
    FOREIGN KEY (`message_id`)
    REFERENCES `xamTest`.`message` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_like_comment_id`
    FOREIGN KEY (`comment_id`)
    REFERENCES `xamTest`.`comment` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `xamTest`.`dis_like`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `xamTest`.`dis_like` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT UNSIGNED NOT NULL,
  `message_id` BIGINT UNSIGNED NULL,
  `comment_id` BIGINT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_id_idx` (`user_id` ASC),
  INDEX `fk_message_id_idx` (`message_id` ASC),
  INDEX `fk_comment_id_idx` (`comment_id` ASC),
  CONSTRAINT `fk_dislike_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `xamTest`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dislike_message_id`
    FOREIGN KEY (`message_id`)
    REFERENCES `xamTest`.`message` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dislike_comment_id`
    FOREIGN KEY (`comment_id`)
    REFERENCES `xamTest`.`comment` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `xamTest`.`favorite`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `xamTest`.`favorite` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT UNSIGNED NOT NULL,
  `message_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_id_idx` (`user_id` ASC),
  INDEX `fk_message_id_idx` (`message_id` ASC),
  CONSTRAINT `fk_favorite_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `xamTest`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_favorite_message_id`
    FOREIGN KEY (`message_id`)
    REFERENCES `xamTest`.`message` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
