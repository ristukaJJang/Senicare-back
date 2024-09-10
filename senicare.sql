-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema senicare
-- -----------------------------------------------------
-- 시니케어 데이터베이스

-- -----------------------------------------------------
-- Schema senicare
--
-- 시니케어 데이터베이스
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `senicare` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `senicare` ;

-- -----------------------------------------------------
-- Table `senicare`.`Tel_auth_number`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `senicare`.`Tel_auth_number` (
  `tel_number` VARCHAR(11) NOT NULL COMMENT '요양사 전화번호',
  `auth_number` VARCHAR(4) NOT NULL COMMENT '요양사 전화번호 인증번호',
  PRIMARY KEY (`tel_number`),
  UNIQUE INDEX `tel_number_UNIQUE` (`tel_number` ASC) VISIBLE)
ENGINE = InnoDB
COMMENT = '전화번호 인증 테이블';


-- -----------------------------------------------------
-- Table `senicare`.`Nurses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `senicare`.`Nurses` (
  `user_id` VARCHAR(20) NOT NULL COMMENT '요양사 아이디',
  `password` VARCHAR(255) NOT NULL COMMENT '암호화된 비밀번호',
  `name` VARCHAR(15) NOT NULL COMMENT '요양사 이름',
  `tel_number` VARCHAR(11) NOT NULL COMMENT '요양사 전화번호',
  `join_path` VARCHAR(5) NOT NULL COMMENT '가입 경로(HOME: 기본, KAKAO : 카카오, NAVER: 네이버)',
  `sns_id` VARCHAR(255) NULL COMMENT 'sns 아이디',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `tel_number_UNIQUE` (`tel_number` ASC) VISIBLE,
  CONSTRAINT `tel_auth`
    FOREIGN KEY (`tel_number`)
    REFERENCES `senicare`.`Tel_auth_number` (`tel_number`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = '요양사 테이블';


-- -----------------------------------------------------
-- Table `senicare`.`Customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `senicare`.`Customers` (
  `custom_number` INT NOT NULL AUTO_INCREMENT COMMENT '고객 번호',
  `profile_image` TEXT NOT NULL COMMENT '고객 프로필 사진(링크로 들어올 예정)',
  `name` VARCHAR(15) NOT NULL COMMENT '고객 이름',
  `birth` VARCHAR(6) NOT NULL COMMENT '고객 생년 월일 yymmdd',
  `charger` VARCHAR(20) NOT NULL COMMENT '담당 요양사',
  `address` TEXT NOT NULL COMMENT '고객 주소',
  `location` VARCHAR(60) NOT NULL COMMENT '지역(시도)',
  PRIMARY KEY (`custom_number`),
  UNIQUE INDEX `custom_number_UNIQUE` (`custom_number` ASC) VISIBLE,
  INDEX `customer_charger_idx` (`charger` ASC) VISIBLE,
  CONSTRAINT `customer_charger`
    FOREIGN KEY (`charger`)
    REFERENCES `senicare`.`Nurses` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '고객 테이블';


-- -----------------------------------------------------
-- Table `senicare`.`Tools`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `senicare`.`Tools` (
  `tool_number` INT NOT NULL AUTO_INCREMENT COMMENT '용품 번호',
  `name` VARCHAR(255) NOT NULL COMMENT '용품 이름',
  `purpose` TEXT NOT NULL COMMENT '용품 용도',
  `count` INT NOT NULL COMMENT '용품 잔여 수량',
  PRIMARY KEY (`tool_number`),
  UNIQUE INDEX `tool_number_UNIQUE` (`tool_number` ASC) VISIBLE)
ENGINE = InnoDB
COMMENT = '용품 테이블';


-- -----------------------------------------------------
-- Table `senicare`.`Care_record`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `senicare`.`Care_record` (
  `record_number` INT NOT NULL AUTO_INCREMENT COMMENT '관리 기록 번호',
  `record_date` DATE NOT NULL COMMENT '관리 기록 날짜',
  `contents` TEXT NOT NULL COMMENT '관리 내용',
  `used_tool` INT NULL COMMENT '관리에 사용한 용품',
  `count` INT NULL COMMENT '관리시 사용한 용품 개수',
  `charger` VARCHAR(20) NOT NULL COMMENT '관리 담당 요양사',
  `custom_number` INT NOT NULL COMMENT '관리 대상 고객',
  PRIMARY KEY (`record_number`),
  UNIQUE INDEX `record_number_UNIQUE` (`record_number` ASC) VISIBLE,
  INDEX `used_tools_idx` (`used_tool` ASC) VISIBLE,
  INDEX `record_charger_idx` (`charger` ASC) VISIBLE,
  INDEX `cared_customer_idx` (`custom_number` ASC) VISIBLE,
  CONSTRAINT `used_tools`
    FOREIGN KEY (`used_tool`)
    REFERENCES `senicare`.`Tools` (`tool_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `record_charger`
    FOREIGN KEY (`charger`)
    REFERENCES `senicare`.`Nurses` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `cared_customer`
    FOREIGN KEY (`custom_number`)
    REFERENCES `senicare`.`Customers` (`custom_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '관리 기록부';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

create user 'senicare'@'%' identified by 'ristuka0801!!??';

grant all privileges on senicare.* to 'senicare'@'%';