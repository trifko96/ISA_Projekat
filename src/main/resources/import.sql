insert into avio_kompanija (kompanija_id, adresa, naziv, prihod, broj_ocena, ocena) values (1, 'Bulevar Kralja Aleksandra 17, 11000 Beograd Srbija', 'Air-Serbia', 0, 0, 0);

insert into rentacar (rentacar_id, adresa, naziv, prihod) values (1, 'Tesarska 6, 11253 Sremcica Srbija', 'ReCarD2', 0);

insert into korisnik (id, br_telefona, email, grad, ime, lozinka, prezime, uloga, avio_kompanija_id) values (1, '061-3333-555', 'korisnik1@gmail.com', 'Novi Sad', 'Ime1', 'korisnik1', 'Prezime1', 1, 1);
insert into korisnik (id, br_telefona, email, grad, ime, lozinka, prezime, uloga, id_rentacar) values (2, '064-5555-333', 'korisnik2@gmail.com', 'Beograd', 'Ime2', 'korisnik2', 'Prezime2', 3, 1);

