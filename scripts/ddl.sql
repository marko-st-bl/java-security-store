-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema security_store
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `security_store` ;

-- -----------------------------------------------------
-- Schema security_store
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `security_store` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `security_store` ;

-- -----------------------------------------------------
-- Table `security_store`.`MJESTO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`MJESTO` ;

CREATE TABLE IF NOT EXISTS `security_store`.`MJESTO` (
  `Posta` INT(5) NOT NULL,
  `Naziv` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`Posta`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_store`.`POSLOVNICA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`POSLOVNICA` ;

CREATE TABLE IF NOT EXISTS `security_store`.`POSLOVNICA` (
  `IdPoslovnica` INT NOT NULL AUTO_INCREMENT,
  `Adresa` VARCHAR(45) NOT NULL,
  `Telefon` VARCHAR(20) NOT NULL,
  `Posta` INT(5) NOT NULL,
  PRIMARY KEY (`IdPoslovnica`),
  CONSTRAINT `fk_Poslovnica_Mjesto`
    FOREIGN KEY (`Posta`)
    REFERENCES `security_store`.`MJESTO` (`Posta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_store`.`PROIZVODJAC`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`PROIZVODJAC` ;

CREATE TABLE IF NOT EXISTS `security_store`.`PROIZVODJAC` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(45) NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_store`.`PROIZVOD`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`PROIZVOD` ;

CREATE TABLE IF NOT EXISTS `security_store`.`PROIZVOD` (
  `Sifra` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(45) NOT NULL,
  `Opis` TEXT NULL,
  `KolicinaNaStanju` INT NOT NULL,
  `IdProizvodjac` INT NOT NULL,
  `ProdajnaCijena` DOUBLE NOT NULL,
  PRIMARY KEY (`Sifra`),
  CONSTRAINT `fk_PROIZVOD_PROIZVODJAC1`
    FOREIGN KEY (`IdProizvodjac`)
    REFERENCES `security_store`.`PROIZVODJAC` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_store`.`INTERFON`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`INTERFON` ;

CREATE TABLE IF NOT EXISTS `security_store`.`INTERFON` (
  `Sifra` INT NOT NULL,
  `BrojKorisnika` INT NULL,
  PRIMARY KEY (`Sifra`),
  CONSTRAINT `fk_SEF_PROIZVOD1`
    FOREIGN KEY (`Sifra`)
    REFERENCES `security_store`.`PROIZVOD` (`Sifra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_store`.`BRAVA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`BRAVA` ;

CREATE TABLE IF NOT EXISTS `security_store`.`BRAVA` (
  `Sifra` INT NOT NULL,
  `Model` VARCHAR(20) NOT NULL,
  `MasaUkg` INT NULL,
  PRIMARY KEY (`Sifra`),
  CONSTRAINT `fk_BRAVA_PROIZVOD1`
    FOREIGN KEY (`Sifra`)
    REFERENCES `security_store`.`PROIZVOD` (`Sifra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_store`.`OSOBA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`OSOBA` ;

CREATE TABLE IF NOT EXISTS `security_store`.`OSOBA` (
  `JMB` CHAR(13) NOT NULL,
  `Ime` VARCHAR(20) NOT NULL,
  `Prezime` VARCHAR(20) NOT NULL,
  `DatumRodjenja` DATE NOT NULL,
  `Pol` ENUM('M', 'Z') NOT NULL,
  `Adresa` VARCHAR(50) NULL,
  `Telefon` VARCHAR(20) NULL,
  PRIMARY KEY (`JMB`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_store`.`STATUS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`STATUS` ;

CREATE TABLE IF NOT EXISTS `security_store`.`STATUS` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(20) NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_store`.`KORISNICKI_NALOG`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`KORISNICKI_NALOG` ;

CREATE TABLE IF NOT EXISTS `security_store`.`KORISNICKI_NALOG` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `KorisnickoIme` VARCHAR(20) NOT NULL,
  `Lozinka` VARCHAR(20) NOT NULL,
  `IdStatus` INT NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `KorisnickoIme_UNIQUE` (`KorisnickoIme` ASC) VISIBLE,
  CONSTRAINT `fk_KORISNICKI_NALOG_STATUS1`
    FOREIGN KEY (`IdStatus`)
    REFERENCES `security_store`.`STATUS` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_store`.`ZAPOSLENI`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`ZAPOSLENI` ;

CREATE TABLE IF NOT EXISTS `security_store`.`ZAPOSLENI` (
  `PrimljenURadniOdnos` DATE NOT NULL,
  `JMB` CHAR(13) NOT NULL,
  `IdKorisnickiNalog` INT NULL,
  `KrajRadnogOdnosa` DATE NULL,
  `Pozicija` ENUM('Prodavac', 'Montazer', 'Menadzer') NOT NULL,
  PRIMARY KEY (`JMB`),
  CONSTRAINT `fk_ZAPOSLENI_Osoba1`
    FOREIGN KEY (`JMB`)
    REFERENCES `security_store`.`OSOBA` (`JMB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ZAPOSLENI_KORISNICKI_NALOG1`
    FOREIGN KEY (`IdKorisnickiNalog`)
    REFERENCES `security_store`.`KORISNICKI_NALOG` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_store`.`STOPE_DOPRINOSA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`STOPE_DOPRINOSA` ;

CREATE TABLE IF NOT EXISTS `security_store`.`STOPE_DOPRINOSA` (
  `PIO` DECIMAL(4,2) NOT NULL,
  `ZDR` DECIMAL(4,2) NOT NULL,
  `DJ` DECIMAL(4,2) NOT NULL,
  `NEZAP` DECIMAL(4,2) NOT NULL,
  `VaziOd` DATE NOT NULL,
  `VaziDo` DATE NULL,
  `IdStopeDoprinosa` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`IdStopeDoprinosa`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_store`.`PLATA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`PLATA` ;

CREATE TABLE IF NOT EXISTS `security_store`.`PLATA` (
  `PeriodOd` DATE NOT NULL,
  `PeriodDo` DATE NULL,
  `Bruto` DECIMAL NULL,
  `IdStopeDoprinosa` INT NOT NULL,
  `JMB` CHAR(13) NOT NULL,
  PRIMARY KEY (`PeriodOd`, `JMB`),
  CONSTRAINT `fk_PLATA_STOPE_DOPRINOSA1`
    FOREIGN KEY (`IdStopeDoprinosa`)
    REFERENCES `security_store`.`STOPE_DOPRINOSA` (`IdStopeDoprinosa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PLATA_ZAPOSLENI1`
    FOREIGN KEY (`JMB`)
    REFERENCES `security_store`.`ZAPOSLENI` (`JMB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_store`.`ODJECA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`ODJECA` ;

CREATE TABLE IF NOT EXISTS `security_store`.`ODJECA` (
  `Sifra` INT NOT NULL,
  `Velicina` VARCHAR(5) NULL,
  PRIMARY KEY (`Sifra`),
  CONSTRAINT `fk_ODJECA_PROIZVOD1`
    FOREIGN KEY (`Sifra`)
    REFERENCES `security_store`.`PROIZVOD` (`Sifra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_store`.`ALARM`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`ALARM` ;

CREATE TABLE IF NOT EXISTS `security_store`.`ALARM` (
  `Sifra` INT NOT NULL,
  `Model` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`Sifra`),
  CONSTRAINT `fk_ALARM_PROIZVOD1`
    FOREIGN KEY (`Sifra`)
    REFERENCES `security_store`.`PROIZVOD` (`Sifra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_store`.`KAMERA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`KAMERA` ;

CREATE TABLE IF NOT EXISTS `security_store`.`KAMERA` (
  `Sifra` INT NOT NULL,
  `Rezolucija` DOUBLE(4,2) NOT NULL,
  PRIMARY KEY (`Sifra`),
  CONSTRAINT `fk_KAMERA_PROIZVOD1`
    FOREIGN KEY (`Sifra`)
    REFERENCES `security_store`.`PROIZVOD` (`Sifra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_store`.`REKORDER`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`REKORDER` ;

CREATE TABLE IF NOT EXISTS `security_store`.`REKORDER` (
  `Sifra` INT NOT NULL,
  `Model` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`Sifra`),
  CONSTRAINT `fk_REKORDER_PROIZVOD1`
    FOREIGN KEY (`Sifra`)
    REFERENCES `security_store`.`PROIZVOD` (`Sifra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_store`.`DOBAVLJAC`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`DOBAVLJAC` ;

CREATE TABLE IF NOT EXISTS `security_store`.`DOBAVLJAC` (
  `IdDobavljac` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(45) NOT NULL,
  `Adresa` VARCHAR(45) NOT NULL,
  `Posta` INT(5) NOT NULL,
  PRIMARY KEY (`IdDobavljac`),
  CONSTRAINT `fk_DOBAVLJAC_MJESTO`
    FOREIGN KEY (`Posta`)
    REFERENCES `security_store`.`MJESTO` (`Posta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_store`.`KUPAC`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`KUPAC` ;

CREATE TABLE IF NOT EXISTS `security_store`.`KUPAC` (
  `JMB` CHAR(13) NOT NULL,
  PRIMARY KEY (`JMB`),
  CONSTRAINT `fk_KUPAC_OSOBA1`
    FOREIGN KEY (`JMB`)
    REFERENCES `security_store`.`OSOBA` (`JMB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_store`.`PRODAJA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`PRODAJA` ;

CREATE TABLE IF NOT EXISTS `security_store`.`PRODAJA` (
  `IdProdaja` INT NOT NULL AUTO_INCREMENT,
  `DatumProdaje` DATE NOT NULL,
  `JMBKupac` CHAR(13) NULL,
  `JMBProdavac` CHAR(13) NOT NULL,
  PRIMARY KEY (`IdProdaja`),
  CONSTRAINT `fk_PRODAJA_KUPAC1`
    FOREIGN KEY (`JMBKupac`)
    REFERENCES `security_store`.`KUPAC` (`JMB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PRODAJA_ZAPOSLENI1`
    FOREIGN KEY (`JMBProdavac`)
    REFERENCES `security_store`.`ZAPOSLENI` (`JMB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_store`.`IZNAJMLJIVANJE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`IZNAJMLJIVANJE` ;

CREATE TABLE IF NOT EXISTS `security_store`.`IZNAJMLJIVANJE` (
  `IdIznajmljivanja` INT NOT NULL AUTO_INCREMENT,
  `JMBKupac` CHAR(13) NOT NULL,
  `DatumIznajmljivanja` DATE NULL,
  `JMBProdavac` CHAR(13) NOT NULL,
  PRIMARY KEY (`IdIznajmljivanja`),
  CONSTRAINT `fk_IZNAJMLJIVANJE_KUPAC1`
    FOREIGN KEY (`JMBKupac`)
    REFERENCES `security_store`.`KUPAC` (`JMB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_IZNAJMLJIVANJE_ZAPOSLENI1`
    FOREIGN KEY (`JMBProdavac`)
    REFERENCES `security_store`.`ZAPOSLENI` (`JMB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_store`.`PROIZVOD_U_POSLOVNICI`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`PROIZVOD_U_POSLOVNICI` ;

CREATE TABLE IF NOT EXISTS `security_store`.`PROIZVOD_U_POSLOVNICI` (
  `Sifra` INT NOT NULL,
  `IdPoslovnica` INT NOT NULL,
  `Kolicina` INT NULL,
  PRIMARY KEY (`Sifra`, `IdPoslovnica`),
  CONSTRAINT `fk_PROIZVOD_has_POSLOVNICA_PROIZVOD1`
    FOREIGN KEY (`Sifra`)
    REFERENCES `security_store`.`PROIZVOD` (`Sifra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROIZVOD_has_POSLOVNICA_POSLOVNICA1`
    FOREIGN KEY (`IdPoslovnica`)
    REFERENCES `security_store`.`POSLOVNICA` (`IdPoslovnica`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_store`.`STAVKA_PRODAJA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`STAVKA_PRODAJA` ;

CREATE TABLE IF NOT EXISTS `security_store`.`STAVKA_PRODAJA` (
  `IdProdaja` INT NOT NULL,
  `Kolicina` INT NOT NULL,
  `Cijena` DOUBLE NOT NULL,
  `Sifra` INT NOT NULL,
  `IdPoslovnica` INT NOT NULL,
  PRIMARY KEY (`IdProdaja`, `Sifra`, `IdPoslovnica`),
  CONSTRAINT `fk_PRODAJA_STAVKA_PRODAJA`
    FOREIGN KEY (`IdProdaja`)
    REFERENCES `security_store`.`PRODAJA` (`IdProdaja`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_STAVKA_PRODAJA_PROIZVOD_has_POSLOVNICA1`
    FOREIGN KEY (`Sifra` , `IdPoslovnica`)
    REFERENCES `security_store`.`PROIZVOD_U_POSLOVNICI` (`Sifra` , `IdPoslovnica`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_store`.`STAVKA_IZNAJMLJIVANJE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`STAVKA_IZNAJMLJIVANJE` ;

CREATE TABLE IF NOT EXISTS `security_store`.`STAVKA_IZNAJMLJIVANJE` (
  `datumOd` DATE NOT NULL,
  `datumDo` DATE NULL,
  `Cijena` DOUBLE NOT NULL,
  `IdIznajmljivanja` INT NOT NULL,
  `Sifra` INT NOT NULL,
  `IdPoslovnica` INT NOT NULL,
  `Kolicina` INT NOT NULL,
  PRIMARY KEY (`datumOd`, `IdIznajmljivanja`, `Sifra`, `IdPoslovnica`),
  CONSTRAINT `fk_STAVKA_IZNAJMLJIVANJE_IZNAJMLJIVANJE1`
    FOREIGN KEY (`IdIznajmljivanja`)
    REFERENCES `security_store`.`IZNAJMLJIVANJE` (`IdIznajmljivanja`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_STAVKA_IZNAJMLJIVANJE_PROIZVOD_has_POSLOVNICA1`
    FOREIGN KEY (`Sifra` , `IdPoslovnica`)
    REFERENCES `security_store`.`PROIZVOD_U_POSLOVNICI` (`Sifra` , `IdPoslovnica`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_store`.`FAKTURA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`FAKTURA` ;

CREATE TABLE IF NOT EXISTS `security_store`.`FAKTURA` (
  `IdFaktura` INT NOT NULL AUTO_INCREMENT,
  `Sifra` INT NOT NULL,
  `IdDobavljac` INT NOT NULL,
  `NabavnaCijena` DOUBLE NULL,
  `Kolicina` INT NULL,
  `DatumNabavke` DATE NOT NULL,
  PRIMARY KEY (`IdFaktura`),
  CONSTRAINT `fk_PROIZVOD_has_DOBAVLJAC_PROIZVOD1`
    FOREIGN KEY (`Sifra`)
    REFERENCES `security_store`.`PROIZVOD` (`Sifra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROIZVOD_has_DOBAVLJAC_DOBAVLJAC1`
    FOREIGN KEY (`IdDobavljac`)
    REFERENCES `security_store`.`DOBAVLJAC` (`IdDobavljac`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_store`.`ZAPOSLENI_POSLOVNICA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`ZAPOSLENI_POSLOVNICA` ;

CREATE TABLE IF NOT EXISTS `security_store`.`ZAPOSLENI_POSLOVNICA` (
  `JMBZaposleni` CHAR(13) NOT NULL,
  `IdPoslovnica` INT NOT NULL,
  `DatumOd` DATE NOT NULL,
  `DatumDo` DATE NULL,
  PRIMARY KEY (`JMBZaposleni`, `IdPoslovnica`, `DatumOd`),
  CONSTRAINT `fk_ZAPOSLENI_has_POSLOVNICA_ZAPOSLENI1`
    FOREIGN KEY (`JMBZaposleni`)
    REFERENCES `security_store`.`ZAPOSLENI` (`JMB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ZAPOSLENI_has_POSLOVNICA_POSLOVNICA1`
    FOREIGN KEY (`IdPoslovnica`)
    REFERENCES `security_store`.`POSLOVNICA` (`IdPoslovnica`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_store`.`KATEGORIJA_PROIZVODA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`KATEGORIJA_PROIZVODA` ;

CREATE TABLE IF NOT EXISTS `security_store`.`KATEGORIJA_PROIZVODA` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_store`.`PROIZVOD_KATEGORIJA_PROIZVODA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `security_store`.`PROIZVOD_KATEGORIJA_PROIZVODA` ;

CREATE TABLE IF NOT EXISTS `security_store`.`PROIZVOD_KATEGORIJA_PROIZVODA` (
  `Sifra` INT NOT NULL,
  `IdKategorija` INT NOT NULL,
  PRIMARY KEY (`Sifra`, `IdKategorija`),
  CONSTRAINT `fk_PROIZVOD_has_KATEGORIJA_PROIZVODA_PROIZVOD1`
    FOREIGN KEY (`Sifra`)
    REFERENCES `security_store`.`PROIZVOD` (`Sifra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROIZVOD_KATEGORIJA_PROIZVODA_KATEGORIJA_PROIZVODA1`
    FOREIGN KEY (`IdKategorija`)
    REFERENCES `security_store`.`KATEGORIJA_PROIZVODA` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
