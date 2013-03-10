SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `class_manager` DEFAULT CHARACTER SET latin1 ;
USE `class_manager` ;

-- -----------------------------------------------------
-- Table `class_manager`.`class`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class_manager`.`class` (
  `idclass` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `level` VARCHAR(45) NULL ,
  PRIMARY KEY (`idclass`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `class_manager`.`student`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class_manager`.`student` (
  `idstudent` INT NOT NULL AUTO_INCREMENT ,
  `last_name` VARCHAR(45) NULL ,
  `first_name` VARCHAR(45) NULL ,
  `class_idclass` INT NOT NULL ,
  PRIMARY KEY (`idstudent`, `class_idclass`) ,
  INDEX `fk_student_class_idx` (`class_idclass` ASC) ,
  CONSTRAINT `fk_student_class`
    FOREIGN KEY (`class_idclass` )
    REFERENCES `class_manager`.`class` (`idclass` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `class_manager`.`assignment_type`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class_manager`.`assignment_type` (
  `idassignment_type` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  PRIMARY KEY (`idassignment_type`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `class_manager`.`subject`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class_manager`.`subject` (
  `idsubject` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  PRIMARY KEY (`idsubject`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `class_manager`.`competency`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class_manager`.`competency` (
  `idcompetency` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `description` VARCHAR(45) NULL ,
  `weight` FLOAT NULL ,
  `subject_idsubject` INT NOT NULL ,
  PRIMARY KEY (`idcompetency`, `subject_idsubject`) ,
  INDEX `fk_competency_subject1_idx` (`subject_idsubject` ASC) ,
  CONSTRAINT `fk_competency_subject1`
    FOREIGN KEY (`subject_idsubject` )
    REFERENCES `class_manager`.`subject` (`idsubject` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `class_manager`.`assignment`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class_manager`.`assignment` (
  `idassignment` INT NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(45) NULL ,
  `total` FLOAT NULL ,
  `weight` FLOAT NULL ,
  `assignment_type_idassignment_type` INT NOT NULL ,
  `competency_idcompetency` INT NOT NULL ,
  PRIMARY KEY (`idassignment`, `assignment_type_idassignment_type`, `competency_idcompetency`) ,
  INDEX `fk_assignment_assignment_type1_idx` (`assignment_type_idassignment_type` ASC) ,
  INDEX `fk_assignment_competency1_idx` (`competency_idcompetency` ASC) ,
  CONSTRAINT `fk_assignment_assignment_type1`
    FOREIGN KEY (`assignment_type_idassignment_type` )
    REFERENCES `class_manager`.`assignment_type` (`idassignment_type` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_assignment_competency1`
    FOREIGN KEY (`competency_idcompetency` )
    REFERENCES `class_manager`.`competency` (`idcompetency` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `class_manager`.`assignment_has_student`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class_manager`.`assignment_has_student` (
  `assignment_idassignment` INT NOT NULL ,
  `student_idstudent` INT NOT NULL ,
  `mark` FLOAT NULL ,
  `adjusted total` FLOAT NULL ,
  `adjusted weight` VARCHAR(45) NULL ,
  PRIMARY KEY (`assignment_idassignment`, `student_idstudent`) ,
  INDEX `fk_assignment_has_student_student1_idx` (`student_idstudent` ASC) ,
  INDEX `fk_assignment_has_student_assignment1_idx` (`assignment_idassignment` ASC) ,
  CONSTRAINT `fk_assignment_has_student_assignment1`
    FOREIGN KEY (`assignment_idassignment` )
    REFERENCES `class_manager`.`assignment` (`idassignment` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_assignment_has_student_student1`
    FOREIGN KEY (`student_idstudent` )
    REFERENCES `class_manager`.`student` (`idstudent` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `class_manager`.`class_has_subject`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class_manager`.`class_has_subject` (
  `class_idclass` INT NOT NULL ,
  `subject_idsubject` INT NOT NULL ,
  PRIMARY KEY (`class_idclass`, `subject_idsubject`) ,
  INDEX `fk_class_has_subject_subject1_idx` (`subject_idsubject` ASC) ,
  INDEX `fk_class_has_subject_class1_idx` (`class_idclass` ASC) ,
  CONSTRAINT `fk_class_has_subject_class1`
    FOREIGN KEY (`class_idclass` )
    REFERENCES `class_manager`.`class` (`idclass` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_class_has_subject_subject1`
    FOREIGN KEY (`subject_idsubject` )
    REFERENCES `class_manager`.`subject` (`idsubject` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `class_manager` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
