insert into avio_kompanija (kompanija_id, adresa, naziv) values ('Air Serbia', 'Bulevar Kralja Aleksandra 17, 11000 Beograd Srbija', 1);

insert into rentacar (rentacar_id, adresa, naziv) values (1, 'Tesarska 6, 11253 Sremcica Srbija', 'ReCarD2')

insert into korisnik (id, br_telefona, email, grad, ime, lozinka, prezime, uloga, avio_kompanija_id) values (1, '061-3333-555', 'korisnik1@gmail.com', 'Novi Sad', 'Ime1', 'korisnik1', 'Prezime1', 'ADMINISTRATOR_AVIOKOMPANIJE', 1);
insert into korisnik (id, br_telefona, email, grad, ime, lozinka, prezime, uloga, id_rentacar) values (2, '064-5555-333', 'korisnik2@gmail.com', 'Beograd', 'Ime2', 'korisnik2', 'Prezime2', 'ADMINISTRATOR_RENT_A_CAR', 1);