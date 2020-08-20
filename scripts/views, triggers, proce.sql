USE `security_store`;

#########
##VIEWS##
#########

drop view if exists zaposleni_info;
create view zaposleni_info(JMB, Ime, Prezime) as
select z.JMB, Ime, Prezime
from zaposleni z inner join osoba o on z.JMB=o.JMB;

drop view if exists kupac_info;
create view kupac_info(JMB, Ime, Prezime) as
select k.JMB, Ime, Prezime
from kupac k inner join osoba o on k.JMB=o.JMB;

drop view if exists zaposleni_bez_naloga_info;
create view zaposleni_bez_naloga_info(JMB, Ime, Prezime, Pozicija) as
select z.JMB, Ime, Prezime, Pozicija
from zaposleni z inner join osoba o on z.JMB=o.JMB
where IdKorisnickiNalog is null and KrajRadnogOdnosa is null;

drop view if exists ukupna_prodaja;
create view ukupna_prodaja(Mjesto, Adresa, Telefon, UkupanPromet) as
select m.Naziv, Adresa, Telefon, sum(Kolicina*Cijena) as UkupanPromet
from mjesto m inner join poslovnica p on m.posta=p.posta
inner join stavka_prodaja sp on p.IdPoslovnica=sp.IdPoslovnica
inner join prodaja  pr on sp.IdProdaja=pr.IdProdaja
group by p.IdPoslovnica;

drop view if exists ukupno_iznajmljivanje;
create view ukupno_iznajmljivanje(Mjesto, Adresa, Telefon, UkupanPromet) as
select m.Naziv, Adresa, Telefon, sum(Kolicina*Cijena) as UkupanPromet
from mjesto m inner join poslovnica p on m.posta=p.posta
inner join stavka_iznajmljivanje si on p.IdPoslovnica=si.IdPoslovnica
inner join iznajmljivanje i on i.IdIznajmljivanja=si.IdIznajmljivanja
group by p.IdPoslovnica;

drop view if exists ukupan_promet;
create view ukupan_promet(Mjesto, Adresa, Telefon, UkupanPromet) as
select p.Mjesto, p.Adresa, p.Telefon, (p.UkupanPromet + i.UkupanPromet) as Ukupno
from ukupna_prodaja p inner join ukupno_iznajmljivanje i on p.Adresa=i.Adresa;

drop view if exists plata_zaposlenog;
create view plata_zaposlenog(JMB, ImePrezime, Bruto, PIO, DJ, NZP, UkupnoPorez, Neto) as
select z.JMB, concat(Ime," ", Prezime), Bruto, round(Bruto*PIO/100,2), round(Bruto*DJ/100,2), round(Bruto*NEZAP/100,2),
round(Bruto*(NEZAP+PIO+DJ)/100,2), round(Bruto - Bruto*(NEZAP+PIO+DJ)/100,2)  from
zaposleni z inner join osoba o on z.JMB=o.JMB
inner join plata p on z.JMB=p.JMB
inner join stope_doprinosa sd on p.IdStopeDoprinosa=sd.IdStopeDoprinosa
where p.PeriodDo is null;


############
##TRIGGERS##
############

drop trigger if exists postavi_pol;
create trigger postavi_pol before insert
on osoba
for each row
set new.Pol=if(substr(new.JMB, 10, 1)<=4, 'M','Z');

drop trigger if exists prebaci_proizvod_u_poslovnicu;
create trigger prebaci_proizvod_u_poslovnicu before insert
on proizvod_u_poslovnici
for each row
update proizvod
set Kolicina=Kolicina-new.Kolicina
where Sifra=new.Sifra;

drop trigger if exists prodaja_proizvoda;
create trigger prodaja_proizvoda after insert
on stavka_prodaja
for each row
update proizvod_u_poslovnici
set Kolicina=Kolicina-new.Kolicina
where Sifra=new.Sifra;

##############
##PROCEDURES##
##############

drop procedure if exists prijava;
delimiter $$
create procedure prijava
(in zKorisnickoIme varchar(20), in zLozinka varchar(20), out oResult boolean,
out oJMB char(13), out oIme varchar(20), out oPrezime varchar(20))
begin
if exists
(select * from
zaposleni z inner join osoba o on z.JMB=o.JMB
inner join korisnicki_nalog k on z.IdKorisnickiNalog=k.Id
where KorisnickoIme=zKorisnickoIme and Lozinka=zLozinka)
then set oResult=true;
select z.JMB, Ime, Prezime into oJMB, oIme, oPrezime from
zaposleni z inner join osoba o on z.JMB=o.JMB
inner join korisnicki_nalog k on z.IdKorisnickiNalog=k.Id
where KorisnickoIme=zKorisnickoIme and Lozinka=zLozinka;
end if;
end$$
delimiter ;

drop procedure if exists dodaj_zaposlenog;
delimiter $$
create procedure dodaj_zaposlenog
(in zJMB char(13), in zIme varchar(20), in zPrezime varchar(20),
in zAdresa varchar(50), in zTelefon varchar(20),
in zDatumRodjenja date, in zDatumPrijema date, in zPozicija varchar(20), in zIdPoslovnica int)
begin
insert into osoba (JMB, Ime, Prezime, Adresa, Telefon, DatumRodjenja)
values (zJMB, zIme, zPrezime, zAdresa, zTelefon, zDatumRodjenja);
insert into zaposleni (JMB, PrimljenURadniOdnos, Pozicija) values (zJMB, zDatumPrijema, zPozicija);
insert into zaposleni_poslovnica (JMBZaposleni, IdPoslovnica, DatumOd) values (zJMB, zIdPoslovnica, zDatumPrijema);
end$$
delimiter ;

drop procedure if exists dodijeli_nalog;
delimiter $$
create procedure dodijeli_nalog
(in zJMB char(13))
begin
declare vIdKorisnickiNalog int;
insert into korisnicki_nalog (KorisnickoIme, Lozinka, IdStatus) values(zJMB, '12345', 1);
select Id into vIdKorisnickiNalog from korisnicki_nalog
where KorisnickoIme=zJMB;
update zaposleni
set IdKorisnickiNalog=vIdKorisnickiNalog
where JMB=zJMB;
end $$
delimiter ;

drop procedure if exists dodaj_novi_proizvod;
delimiter $$
create procedure dodaj_novi_proizvod
(in pIdDobavljac int, in pKolicina int, in pNabavnaCijena double, in pNaziv varchar(45), in pOpis text,
in pProizvodjac int, in pProdajnaCijena double, pKategorija varchar(20))
begin
declare vSifra int default 0;
insert into proizvod (Naziv, Opis, KolicinaNaStanju, ProdajnaCijena, IdProizvodjac) 
values (pNaziv, pOpis, pKolicina, pProdajnaCijena, pProizvodjac);
select Sifra into vSifra from Proizvod
where Naziv=pNaziv and Opis=pOpis;
insert into faktura (IdDobavljac, Sifra, NabavnaCijena, Kolicina, DatumNabavke) values (pIdDobavljac, vSifra, pNabavnaCijena, pKolicina, curdate());
end $$
delimiter ;

drop procedure if exists dodaj_postojeci_proizvod;
delimiter $$
create procedure dodaj_postojeci_proizvod
(in pIdDobavljac int, in pKolicina int, in pNabavnaCijena double, in pSifra int)
begin
insert into faktura (IdDobavljac, Sifra, NabavnaCijena, Kolicina, DatumNabavke) values (pIdDobavljac, pSifra, pNabavnaCijena, pKolicina, curdate());
update proizvod set KolicinaNaStanju=KolicinaNaStanju+pKolicina where Sifra=pSifra;
end $$
delimiter ;

drop procedure if exists promjena_plate;
delimiter $$
create procedure promjena_plate
(in zJMB char(13), in zBruto decimal)
begin
declare vIdStopeDoprinosa int default 1;
update plata set PeriodDo=curdate()
where JMB=zJMB and PeriodDo is null;
select IdStopeDoprinosa into vIdStopeDoprinosa
from stope_doprinosa
where VaziDo is null;
insert into plata (JMB, IdStopeDoprinosa, Bruto, PeriodOd) values (zJMB, vIdStopeDoprinosa, zBruto, curdate());
end $$
delimiter ;

#########
##USERS##
#########

drop user  'prodavac'@'localhost';
create user 'prodavac'@'localhost' identified by 'prodavac';
grant select, insert, update, delete on security_store.* to 'prodavac'@'localhost';
grant execute on procedure security_store.prijava to 'prodavac'@'localhost';

drop user 'menadzer'@'localhost';
create user 'menadzer'@'localhost' identified by 'menadzer';
grant select, insert, update, delete on security_store.* to 'menadzer'@'localhost';
grant execute on procedure security_store.dodijeli_nalog to 'menadzer'@'localhost';
grant execute on procedure security_store.dodaj_zaposlenog to 'menadzer'@'localhost';
grant execute on procedure security_store.dodaj_novi_proizvod to 'menadzer'@'localhost';
grant execute on procedure security_store.dodaj_postojeci_proizvod to 'menadzer'@'localhost';
grant execute on procedure security_store.prijava to 'menadzer'@'localhost';
flush privileges;