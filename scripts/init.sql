USE `security_store` ;

-- DODAVANJE MJESTA
INSERT INTO MJESTO(Posta, Naziv) VALUES (78230,"Kneževo");  
INSERT INTO MJESTO(Posta, Naziv) VALUES (78000,"Banja Luka");
INSERT INTO MJESTO(Posta, Naziv) VALUES (79220,"Novi Grad");
INSERT INTO MJESTO(Posta, Naziv) VALUES (78250,"Laktaši");
INSERT INTO MJESTO(Posta, Naziv) VALUES (79101,"Prijedor");
-- -----------------------------------------------------------------

-- DODAVANJE POSLOVNICA
INSERT INTO POSLOVNICA(Adresa, Telefon, Posta) VALUES ("Dujka Komjenovica bb", "051/591-099", 78230);
INSERT INTO POSLOVNICA(Adresa, Telefon, Posta) VALUES ("Sime Šolaje 10", "051/456-789", 78000);
INSERT INTO POSLOVNICA(Adresa, Telefon, Posta) VALUES ("Miće Šurlana 2", "052/595-299", 79220);
INSERT INTO POSLOVNICA(Adresa, Telefon, Posta) VALUES ("Bana Lazarevića 112", "050/132-465", 78250);
-- ------------------------------------------------------------------

INSERT INTO PROIZVODJAC(Naziv) VALUES ("Pongee Industries Co. Ltd.");
INSERT INTO PROIZVODJAC(Naziv) VALUES ("BOSCH");
INSERT INTO PROIZVODJAC(Naziv) VALUES ("Samsung electronics");
INSERT INTO PROIZVODJAC(Naziv) VALUES ("Videotech");
INSERT INTO PROIZVODJAC(Naziv) VALUES ("SONY");
INSERT INTO PROIZVODJAC(Naziv) VALUES ("CDVI");
INSERT INTO PROIZVODJAC(Naziv) VALUES ("Hikvision");
INSERT INTO PROIZVODJAC(Naziv) VALUES ("AJAX");
INSERT INTO PROIZVODJAC(Naziv) VALUES ("COMMAX");
  
-- DODAVNJE KATEGORIJA
INSERT INTO KATEGORIJA_PROIZVODA(Naziv) VALUES ('Elektronska');
INSERT INTO KATEGORIJA_PROIZVODA(Naziv) VALUES ('Mehanička');
INSERT INTO KATEGORIJA_PROIZVODA(Naziv) VALUES ('Bežična');
INSERT INTO KATEGORIJA_PROIZVODA(Naziv) VALUES ('Elektromagnetna');
INSERT INTO KATEGORIJA_PROIZVODA(Naziv) VALUES ('HD');
INSERT INTO KATEGORIJA_PROIZVODA(Naziv) VALUES ('Full-HD');
INSERT INTO KATEGORIJA_PROIZVODA(Naziv) VALUES ('IP');
INSERT INTO KATEGORIJA_PROIZVODA(Naziv) VALUES ('DVR');
INSERT INTO KATEGORIJA_PROIZVODA(Naziv) VALUES ('NVR');
INSERT INTO KATEGORIJA_PROIZVODA(Naziv) VALUES ('Audio');
INSERT INTO KATEGORIJA_PROIZVODA(Naziv) VALUES ('Video');
INSERT INTO KATEGORIJA_PROIZVODA(Naziv) VALUES ('Detektor pokreta');
INSERT INTO KATEGORIJA_PROIZVODA(Naziv) VALUES ('Detektor poplave');
INSERT INTO KATEGORIJA_PROIZVODA(Naziv) VALUES ('Analogni');
INSERT INTO KATEGORIJA_PROIZVODA(Naziv) VALUES ('Digitalni');
-- -----------------------------------------------------------------------------------

-- DODAVANJE ALARMA
INSERT INTO PROIZVOD(Naziv, Opis, KolicinaNaStanju, ProdajnaCijena, IdProizvodjac) VALUES('Alarmna centrala','EVO serija obezbeđuje najviši nivo zaštite namenjen bankama, vojnim objektma, državnim ustanovama, luksuznim stambenim objektima i svim objektima gde je neophodna maksimalna bezbednost.', 10,50.00, 1);
INSERT INTO ALARM(Sifra, Model) VALUES (1, 'EVO');
-- -------------------------------------------------------------------------------------

-- DODAVANJE BRAVA
INSERT INTO PROIZVOD(Naziv, Opis, KolicinaNaStanju, ProdajnaCijena, IdProizvodjac) VALUES('Sigurnosna brava','', 50, 250.90, 3);
INSERT INTO PROIZVOD(Naziv, Opis, KolicinaNaStanju, ProdajnaCijena, IdProizvodjac) VALUES('Sigurnosna brava','', 5,310.50, 3);
INSERT INTO PROIZVOD(Naziv, Opis, KolicinaNaStanju, ProdajnaCijena, IdProizvodjac) VALUES("Sigurnosna brava",'', 11, 285.00, 6);

INSERT INTO BRAVA(Sifra, Model, MasaUkg) VALUES (2,'ECS5000M',500);
INSERT INTO BRAVA(Sifra, Model,MasaUkg) VALUES (3,'V3S',300);
INSERT INTO BRAVA(Sifra, Model) VALUES (4,'V5S');

INSERT INTO PROIZVOD_KATEGORIJA_PROIZVODA(Sifra, IdKategorija) VALUES(2,4);
INSERT INTO PROIZVOD_KATEGORIJA_PROIZVODA(Sifra, IdKategorija) VALUES(3,4);
INSERT INTO PROIZVOD_KATEGORIJA_PROIZVODA(Sifra, IdKategorija) VALUES(4,4);
-- ---------------------------------------------------------------------------------------

-- DODAVANJA REKORDERA
INSERT INTO PROIZVOD(Naziv, Opis, KolicinaNaStanju, ProdajnaCijena, IdProizvodjac) VALUES ('Video rekorder','4-kanalni ekonomični HD-TVI/AHD digitalni snimač u plastičnom kućištu. Podržava 4 HD-TVI (720p) ili AHD (720p) ili analogne kamere (slobodan izbor). Kompresija H.264/ H.264+. Brzina zapisa (po kanalu): 25 fps@720p', 20, 200.00, 4);

INSERT INTO REKORDER(Sifra, Model) VALUES(5, 'DS-7104HGHI-F1' );

INSERT INTO PROIZVOD_KATEGORIJA_PROIZVODA(Sifra, IdKategorija) VALUES(5,5);
-- ---------------------------------------------------------------------------------------

-- DODAVANJE KAMERA
INSERT INTO PROIZVOD(Naziv, Opis, KolicinaNaStanju, ProdajnaCijena, IdProizvodjac) VALUES('HD-TVI motirizovana PTZ kamera','HD-TVI je nova tehnologija u video nadzoru, koja omogućava prenos video signala u megapikeslnoj rezoluciji (720p/1080p) preko standardnog koaksijalnog kabla na rastojanju do 500 metara. HD-TVI sistemi ne zahtevaju izgradnju i konfiguraciju u mrežnom okruženju, a zahteva standardnu šemu povezivanja sistema: koaksijalni kabli, BNC konektori između HD-TVI kamere i DVR uređaja, što Vam pruža mogućnost da koristite postojeće koaksijalne kablovske trase. Ako tražite povoljan video nadzor, a visokog kvaliteta, HD-TVi je pravi izbor. ', 15, 215.99, 5);

INSERT INTO KAMERA(Sifra,Rezolucija) VALUES(6, 5.00);

INSERT INTO PROIZVOD_KATEGORIJA_PROIZVODA(Sifra, IdKategorija) VALUES(6,5);
-- ---------------------------------------------------------------------------------------

-- DODAVANJE ALARMA
INSERT INTO PROIZVOD(Naziv, Opis, KolicinaNaStanju, ProdajnaCijena, IdProizvodjac) VALUES('Protivprovalni alarm','3LED za indikaciju vrste detektovanog pokreta; Podesivi mikrotalasni opseg; 14m x 14m, 90° vidni ugao',10, 82.20, 4);
INSERT INTO PROIZVOD(Naziv, Opis, KolicinaNaStanju, ProdajnaCijena, IdProizvodjac) VALUES('Protivpozarni alarm', 'Bežični detektor sa ugrađenom sirenom i precizno detektuje dim, požar i nagle promene temperature u prostoriji. Montira se na plafon i kontroliše putem aplikacije na telefonu. Može da radi nezavisno od centralnog HUB-a .', 10, 228.50, 8);
INSERT INTO PROIZVOD(Naziv, Opis, KolicinaNaStanju, ProdajnaCijena, IdProizvodjac) VALUES('Protivprovalni alarm', 'Bežični detektor otvaranja koji obaveštava o prvim znakovima upada prostorije pomoću razbijenih vrata ili prozora. Detektuje i vibriranje. Može se montirati na sve vrste vrata uključujući i ona koja imaju metalnu osnovu. Dostupan u crnoj i beloj boji.', 15, 168.70, 8 );

INSERT INTO ALARM (Sifra, Model) VALUES(7, '525DM');
INSERT INTO ALARM (Sifra, Model) VALUES(8, 'FireDetector');
INSERT INTO ALARM (Sifra, Model) VALUES(9, 'DoorDetector');

INSERT INTO PROIZVOD_KATEGORIJA_PROIZVODA(Sifra, IdKategorija) VALUES(7,12);
INSERT INTO PROIZVOD_KATEGORIJA_PROIZVODA(Sifra, IdKategorija) VALUES(8,13);
INSERT INTO PROIZVOD_KATEGORIJA_PROIZVODA(Sifra, IdKategorija) VALUES(8,3);
INSERT INTO PROIZVOD_KATEGORIJA_PROIZVODA(Sifra, IdKategorija) VALUES(9,3);
INSERT INTO PROIZVOD_KATEGORIJA_PROIZVODA(Sifra, IdKategorija) VALUES(9,12);
-- ---------------------------------------------------------------------------------------

-- DODAVANJE INTERFONA
INSERT INTO PROIZVOD(Naziv, Opis, KolicinaNaStanju, ProdajnaCijena, IdProizvodjac) VALUES('DR-2GN', 'Pogodan za uzane stubove i montažu sa strane (na tzv. dovratke). Dimenzije (mm): 88×120×18, Temperatura: -10C do +40C', 25, 77.20, 9);
INSERT INTO PROIZVOD(Naziv, Opis, KolicinaNaStanju, ProdajnaCijena, IdProizvodjac) VALUES('CDV-70H2', 'Monitor: u boji 7″ LCD FINE VIEW (LED) do 2 ulazna uređaja (dodatna CCTV kamera ili drugi pozivni tablo) priključenje do dve dodatne slušalice DP-4VHP Handsfree komunikacija rukovanje pomoću tastera na dodir napajanje: 230 V AC', 30, 465.00, 9);
    
INSERT INTO INTERFON(Sifra, BrojKorisnika) VALUES (10, 10);
INSERT INTO INTERFON(Sifra, BrojKorisnika) VALUES (11, 8);
-- -----------------------------------------------------------
   
-- DODAVANJE OSOBA
INSERT INTO OSOBA(JMB, Ime, Prezime, DatumRodjenja, Pol, Adresa) VALUES ('2711991100106','Marko','Stojanovic','1991-11-27','M','Beogradska 3');
INSERT INTO OSOBA(JMB, Ime, Prezime, DatumRodjenja, Pol, Adresa) VALUES ('0705993715151','Jovana','Davidovic','1993-07-05','Z','Vase Pelagica 22');
INSERT INTO OSOBA(JMB, Ime, Prezime, DatumRodjenja, Pol, Adresa) VALUES ('2209891102106','Milan','Sukur','1989-09-22','M','Bulevar Svetog Save 112b');
INSERT INTO OSOBA(JMB, Ime, Prezime, DatumRodjenja, Pol, Adresa) VALUES ('2804950103891','Aleksa','Dijak','1988-04-28','M','Ulica 4');
INSERT INTO OSOBA(JMB, Ime, Prezime, DatumRodjenja, Pol, Adresa) VALUES ('1208986105108', 'Sanja', 'Stijak', '1986-08-12', 'Z', 'Ulica 5');
INSERT INTO OSOBA(JMB, Ime, Prezime, DatumRodjenja, Pol, Adresa) VALUES ('1712985500506', 'Mirko', 'Mirković', '1985-12-17', 'M', 'Ulica 6');
INSERT INTO OSOBA(JMB, Ime, Prezime, DatumRodjenja, Pol, Adresa) VALUES ('1309987500508', 'Savo', 'Baljak', '1987-09-13', 'M', 'Ulica 7');
INSERT INTO OSOBA(JMB, Ime, Prezime, DatumRodjenja, Pol, Adresa) VALUES ('0104957104155', 'Čedo', 'Bajić', '1957-04-01', 'M', 'Ulica 8');
-- ---------------------------------------------------------------

INSERT INTO STATUS (Naziv) VALUES ('Aktivan');
INSERT INTO STATUS (Naziv) VALUES ('Neaktivan');
INSERT INTO STATUS (Naziv) VALUES ('Blokiran');
INSERT INTO STATUS (Naziv) VALUES ('Suspendovan');

INSERT INTO KORISNICKI_NALOG (KorisnickoIme, Lozinka, IdStatus) VALUES ('prodavac123','test',1);
INSERT INTO KORISNICKI_NALOG (KorisnickoIme, Lozinka, IdStatus) VALUES ('admin','admin',1);
INSERT INTO KORISNICKI_NALOG (KorisnickoIme, Lozinka, IdStatus) VALUES ('test','test',1);
    
INSERT INTO ZAPOSLENI(JMB, PrimljenURadniOdnos, IdKorisnickiNalog, Pozicija) VALUES ('2711991100106','2019-08-01',2, 'Prodavac');
INSERT INTO ZAPOSLENI(JMB, PrimljenURadniOdnos) VALUES ('2209891102106','2019-08-01');
INSERT INTO ZAPOSLENI(JMB, PrimljenURadniOdnos) VALUES ('1208986105108','2020-01-01');
INSERT INTO ZAPOSLENI(JMB, PrimljenURadniOdnos) VALUES ('0705993715151','2020-01-01');
INSERT INTO ZAPOSLENI(JMB, PrimljenURadniOdnos) VALUES ('1712985500506','2020-01-01');

INSERT INTO KUPAC(JMB) VALUES ('2804950103891');
INSERT INTO KUPAC(JMB) VALUES ('0104957104155');
INSERT INTO KUPAC(JMB) VALUES ('1309987500508');

  
INSERT INTO STOPE_DOPRINOSA(PIO, ZDR, DJ, NEZAP, VaziOd)  VALUES (18.50, 12.00, 1.70, 0.8, '2018-01-01');
    
INSERT INTO PLATA(JMB, PeriodOd, Bruto, IdStopeDoprinosa) VALUES ('2209891102106', '2019-08-01', 800.00, 1);
INSERT INTO PLATA(JMB, PeriodOd, Bruto, IdStopeDoprinosa) VALUES ('2711991100106', '2019-09-01', 1200.00, 1);
INSERT INTO PLATA(JMB, PeriodOd, Bruto, IdStopeDoprinosa) VALUES ('1208986105108', '2020-01-01', 1000.00, 1);
INSERT INTO PLATA(JMB, PeriodOd, Bruto, IdStopeDoprinosa) VALUES ('0705993715151', '2020-01-01', 950.00, 1);
INSERT INTO PLATA(JMB, PeriodOd, Bruto, IdStopeDoprinosa) VALUES ('1712985500506', '2020-01-01', 700.00, 1);

INSERT INTO DOBAVLJAC (Naziv, Adresa, Posta) VALUES ('ProVision', 'Heroja Pinkija 11', 78000);
INSERT INTO DOBAVLJAC (Naziv, Adresa, Posta) VALUES ('ComTrade', 'Bulevar Svetog Save 13', 78000);

INSERT INTO PROIZVOD_U_POSLOVNICI (Sifra, IdPoslovnica, Kolicina) VALUES (1,1,10);
INSERT INTO PROIZVOD_U_POSLOVNICI (Sifra, IdPoslovnica, Kolicina) VALUES (2,1,10);
INSERT INTO PROIZVOD_U_POSLOVNICI (Sifra, IdPoslovnica, Kolicina) VALUES (3,2,10);
INSERT INTO PROIZVOD_U_POSLOVNICI (Sifra, IdPoslovnica, Kolicina) VALUES (4,1,10);

INSERT INTO ZAPOSLENI_POSLOVNICA(JMBZaposleni, IdPoslovnica, DatumOd) VALUES ('2711991100106',1,'2019-08-01');
INSERT INTO ZAPOSLENI_POSLOVNICA(JMBZaposleni, IdPoslovnica, DatumOd) VALUES ('2209891102106',1,'2019-08-01');
INSERT INTO ZAPOSLENI_POSLOVNICA(JMBZaposleni, IdPoslovnica, DatumOd) VALUES ('1208986105108',1,'2020-01-01');
INSERT INTO ZAPOSLENI_POSLOVNICA(JMBZaposleni, IdPoslovnica, DatumOd) VALUES ('0705993715151',2,'2020-01-01');
INSERT INTO ZAPOSLENI_POSLOVNICA(JMBZaposleni, IdPoslovnica, DatumOd) VALUES ('1712985500506',3,'2020-01-01');