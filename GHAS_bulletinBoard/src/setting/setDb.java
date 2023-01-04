package setting;

import jdbc.DbManager;

public class setDb {
	private DbManager db;
	
	public static void main(String[] args) {
		setDb setDb = new setDb();
		setDb.createDb();
	}
	
	public setDb() {
		db = new DbManager();
	}

	public void createDb() {
		db.setData("-- MySQL Script generated by MySQL Workbench\r\n" + "-- Mon Jan  2 14:15:36 2023\r\n"
				+ "-- Model: New Model    Version: 1.0\r\n" + "-- MySQL Workbench Forward Engineering\r\n" + "\r\n"
				+ "SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;\r\n"
				+ "SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;\r\n"
				+ "SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';\r\n"
				+ "\r\n" + "-- -----------------------------------------------------\r\n" + "-- Schema GHAS_notice\r\n"
				+ "-- -----------------------------------------------------\r\n"
				+ "DROP SCHEMA IF EXISTS `GHAS_notice` ;\r\n" + "\r\n"
				+ "-- -----------------------------------------------------\r\n" + "-- Schema GHAS_notice\r\n"
				+ "-- -----------------------------------------------------\r\n"
				+ "CREATE SCHEMA IF NOT EXISTS `GHAS_notice` DEFAULT CHARACTER SET utf8 ;\r\n"
				+ "USE `GHAS_notice` ;\r\n" + "\r\n" + "-- -----------------------------------------------------\r\n"
				+ "-- Table `GHAS_notice`.`user`\r\n" + "-- -----------------------------------------------------\r\n"
				+ "CREATE TABLE IF NOT EXISTS `GHAS_notice`.`user` (\r\n" + "  `u_no` INT NOT NULL AUTO_INCREMENT,\r\n"
				+ "  `u_id` VARCHAR(45) NOT NULL,\r\n" + "  `u_pw` VARCHAR(45) NOT NULL,\r\n"
				+ "  `name` VARCHAR(45) NOT NULL,\r\n" + "  PRIMARY KEY (`u_no`))\r\n" + "ENGINE = InnoDB;\r\n" + "\r\n"
				+ "\r\n" + "-- -----------------------------------------------------\r\n"
				+ "-- Table `GHAS_notice`.`notice`\r\n" + "-- -----------------------------------------------------\r\n"
				+ "CREATE TABLE IF NOT EXISTS `GHAS_notice`.`notice` (\r\n"
				+ "  `n_no` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `u_no` INT NULL,\r\n"
				+ "  `n_title` VARCHAR(45) NULL,\r\n" + "  `n_contents` VARCHAR(200) NULL,\r\n"
				+ "  `n_mkdate` DATETIME NULL,\r\n" + "  PRIMARY KEY (`n_no`),\r\n"
				+ "  INDEX `fk_notice_user_idx` (`u_no` ASC) VISIBLE,\r\n" + "  CONSTRAINT `u_no`\r\n"
				+ "    FOREIGN KEY (`u_no`)\r\n" + "    REFERENCES `GHAS_notice`.`user` (`u_no`)\r\n"
				+ "    ON DELETE NO ACTION\r\n" + "    ON UPDATE NO ACTION)\r\n" + "ENGINE = InnoDB;\r\n" + "\r\n"
				+ "\r\n" + "SET SQL_MODE=@OLD_SQL_MODE;\r\n" + "SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;\r\n"
				+ "SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;", null);

		System.out.println("DB생성 완료");
	}

}