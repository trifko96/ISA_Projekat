insert into avio_kompanija (kompanija_id, adresa, naziv, prihod, broj_ocena, ocena, koordinata1, koordinata2, info_prtljag) values (1, 'Bulevar Kralja Aleksandra 17, 11000 Beograd Srbija', 'Air-Serbia', 0, 0, 0, 44.8, 20.5, '15');
insert into avio_kompanija (kompanija_id, adresa, naziv, prihod, broj_ocena, ocena, koordinata1, koordinata2, info_prtljag) values (2, 'Bulevar Kralja Aleksandra 17, 11000 Beograd Srbija', 'Kompanija', 0, 0, 0, 44.8, 20.5, '15');

insert into rentacar (rentacar_id, adresa, naziv, prihod, broj_ocena, ocena) values (1, 'Tesarska 6, 11253 Sremcica Srbija', 'ReCarD2', 0, 0, 0);
insert into rentacar (rentacar_id, adresa, naziv, prihod, broj_ocena, ocena) values (2, 'Bulevar Kralja Petra, 21000 Novi Sad', 'AutoPlay', 0, 0, 0);

insert into korisnik (id, br_telefona, email, grad, ime, lozinka, prezime, uloga, verifikovan, avio_kompanija_id, prvo_logovanje) values (1, '061-3333-555', 'korisnik1@gmail.com', 'Novi Sad', 'Ime1', '9ee012ea8322c151576408181941188fd1402d7ed343717578e96c446a812162', 'Prezime1', 1, TRUE, 1, FALSE);
insert into korisnik (id, br_telefona, email, grad, ime, lozinka, prezime, uloga, verifikovan, id_rentacar, prvo_logovanje) values (2, '064-5555-333', 'korisnik2@gmail.com', 'Beograd', 'Ime2', '39e7220d702b4df6309a28352dac75d7d12231f9c35906e105976a51bdabb020', 'Prezime2', 3, TRUE, 1, FALSE);
insert into korisnik (id, br_telefona, email, grad, ime, lozinka, prezime, uloga, verifikovan, avio_kompanija_id, prvo_logovanje) values (3, '061-3333-555', 'korisnik3@gmail.com', 'Novi Sad', 'Ime1', '9ee012ea8322c151576408181941188fd1402d7ed343717578e96c446a812162', 'Prezime1', 1, TRUE, 2, FALSE);
insert into korisnik (id, br_telefona, email, grad, ime, lozinka, prezime, uloga, verifikovan, id_rentacar) values (4, '064-5555-333', 'korisnik4@gmail.com', 'Beograd', 'Ime2', '39e7220d702b4df6309a28352dac75d7d12231f9c35906e105976a51bdabb020', 'Prezime2', 3, TRUE, 2);
