insert into avio_kompanija (kompanija_id, adresa, naziv, prihod, broj_ocena, ocena) values (1, 'Bulevar Kralja Aleksandra 17, 11000 Beograd Srbija', 'Air-Serbia', 0, 0, 0);

insert into rentacar (rentacar_id, adresa, naziv, prihod) values (1, 'Tesarska 6, 11253 Sremcica Srbija', 'ReCarD2', 0);

insert into korisnik (id, br_telefona, email, grad, ime, lozinka, prezime, uloga, verifikovan, avio_kompanija_id) values (1, '061-3333-555', 'korisnik1@gmail.com', 'Novi Sad', 'Ime1', '9ee012ea8322c151576408181941188fd1402d7ed343717578e96c446a812162', 'Prezime1', 1, TRUE, 1);
insert into korisnik (id, br_telefona, email, grad, ime, lozinka, prezime, uloga, verifikovan, id_rentacar) values (2, '064-5555-333', 'korisnik2@gmail.com', 'Beograd', 'Ime2', '39e7220d702b4df6309a28352dac75d7d12231f9c35906e105976a51bdabb020', 'Prezime2', 3, TRUE, 1);

