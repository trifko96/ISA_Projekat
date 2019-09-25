insert into avio_kompanija (kompanija_id, adresa, naziv, prihod, broj_ocena, ocena, koordinata1, koordinata2, info_prtljag) values (3, 'Bulevar Kralja Aleksandra 17, 11000 Beograd Srbija', 'Air-Serbia', 0, 0, 0, 44.8, 20.5, '15');
insert into avio_kompanija (kompanija_id, adresa, naziv, prihod, broj_ocena, ocena, koordinata1, koordinata2, info_prtljag) values (4, 'Bulevar Kralja Aleksandra 17, 11000 Beograd Srbija', 'Kompanija', 0, 0, 0, 44.8, 20.5, '15');

insert into aerodrom (aerodrom_id, grad, ime) values (1, 'Beograd', 'Nikola Tesla');
insert into aerodrom (aerodrom_id, grad, ime) values (2, 'Nis', 'Konstantin Veliki');
insert into aerodrom (aerodrom_id, grad, ime) values (3, 'Mostar', 'Medjunarodni aerodrom');

insert into aerodromi_kompanije (kompanija_id, aerodrom_id) values (3, 1);
insert into aerodromi_kompanije (kompanija_id, aerodrom_id) values (3, 2);

insert into avion (id_aviona, ime, slobodan, id_avio_kompanije) values (1, 'Avion1', false, 3);
insert into avion (id_aviona, ime, slobodan, id_avio_kompanije) values (2, 'Avion2', true, 3);
insert into avion (id_aviona, ime, slobodan, id_avio_kompanije) values (3, 'Avion3', true, 4);

insert into korisnik (id, br_telefona, email, grad, ime, lozinka, prezime, uloga, verifikovan, avio_kompanija_id, prvo_logovanje) values (7, '061-3333-555', 'korisnik1@gmail.com', 'Novi Sad', 'Ime1', '9ee012ea8322c151576408181941188fd1402d7ed343717578e96c446a812162', 'Prezime1', 1, TRUE, 3, FALSE);

insert into let(id_leta, br_presedanja, br_prodatih_karata, broj_ocena, cena_karte_biznis_klase, cena_karte_ekonomske_klase, cena_prve_klase, ocene, popust, tip, vreme_dolaska_nazad, vreme_polaska_nazad, vreme_poletanja, vreme_putovanja, vreme_sletanja, id_avio_kompanije, id_aviona, odredisna_destinacija_id, polazna_destinacija_id, duzina_leta) values (1, 0, 0, 0, 10, 12, 14, 0, 0, 'ONE_WAY', NULL, NULL, '2019-09-02', 0, '2019-09-03', 3, 1, 1, 2, 0);

insert into segment (id_segmenta, broj_kolona, broj_redova, tip, avion_id) values (11, 2, 2, 0, 1);
insert into segment (id_segmenta, broj_kolona, broj_redova, tip, avion_id) values (12, 2, 2, 1, 1);
insert into segment (id_segmenta, broj_kolona, broj_redova, tip, avion_id) values (13, 2, 2, 2, 1);

insert into segment (id_segmenta, broj_kolona, broj_redova, tip, avion_id) values (21, 2, 2, 0, 2);
insert into segment (id_segmenta, broj_kolona, broj_redova, tip, avion_id) values (22, 2, 2, 1, 2);
insert into segment (id_segmenta, broj_kolona, broj_redova, tip, avion_id) values (23, 2, 2, 2, 2);

insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (1, 1, 1, 0, 1, 0, 0, 11);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (2, 1, 2, 1, 2, 0, 0, 11);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (3, 2, 1, 0, 3, 2, 0, 11);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (4, 2, 2, 1, 4, 0, 0, 11);

insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (5, 1, 1, 0, 5, 0, 0, 12);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (6, 1, 2, 1, 6, 0, 0, 12);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (7, 2, 1, 0, 7, 2, 0, 12);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (8, 2, 2, 1, 8, 0, 0, 12);

insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (9, 1, 1, 0, 9, 0, 0, 13);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (10, 1, 2, 1, 10, 1, 0, 13);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (11, 2, 1, 0, 11, 0, 0, 13);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (12, 2, 2, 1, 12, 0, 0, 13);

insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (13, 1, 1, 0, 1, 0, 0, 21);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (14, 1, 2, 1, 2, 0, 0, 21);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (15, 2, 1, 0, 3, 0, 0, 21);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (16, 2, 2, 1, 4, 0, 0, 21);

insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (17, 1, 1, 0, 5, 0, 0, 22);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (18, 1, 2, 1, 6, 0, 0, 22);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (19, 2, 1, 0, 7, 0, 0, 22);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (20, 2, 2, 1, 8, 0, 0, 22);

insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (21, 1, 1, 0, 9, 0, 0, 23);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (22, 1, 2, 1, 10, 0, 0, 23);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (23, 2, 1, 0, 11, 0, 0, 23);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (24, 2, 2, 1, 12, 0, 0, 23);

insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (1, NULL, NULL, 10, NULL, NULL, NULL, 0, NULL, 1, 1);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (2, NULL, NULL, 10, NULL, NULL, NULL, 0, NULL, 1, 2);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (3, NULL, NULL, 10, NULL, NULL, NULL, 0, NULL, 1, 3);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (4, NULL, NULL, 10, NULL, NULL, NULL, 0, NULL, 1, 4);

insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (5, NULL, NULL, 12, NULL, NULL, NULL, 0, NULL, 1, 5);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (6, NULL, NULL, 12, NULL, NULL, NULL, 0, NULL, 1, 6);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (7, NULL, NULL, 12, NULL, NULL, NULL, 0, NULL, 1, 7);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (8, NULL, NULL, 12, NULL, NULL, NULL, 0, NULL, 1, 8);

insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (9, NULL, NULL, 14, NULL, NULL, NULL, 0, NULL, 1, 9);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (10, '0612424235', '312313', 14, '2019-09-06', 'trifko96@gmail.com', 'Mihailo', 0, 'Trifkovic', 1, 10);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (11, NULL, NULL, 14, NULL, NULL, NULL, 0, NULL, 1, 11);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (12, NULL, NULL, 14, NULL, NULL, NULL, 0, NULL, 1, 12);

/*insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (1, NULL, NULL, 10, NULL, NULL, NULL, 0, NULL, NULL, 13);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (1, NULL, NULL, 10, NULL, NULL, NULL, 0, NULL, NULL, 14);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (1, NULL, NULL, 10, NULL, NULL, NULL, 0, NULL, NULL, 15);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (1, NULL, NULL, 10, NULL, NULL, NULL, 0, NULL, NULL, 16);

insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (1, NULL, NULL, 10, NULL, NULL, NULL, 0, NULL, NULL, 17);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (1, NULL, NULL, 10, NULL, NULL, NULL, 0, NULL, NULL, 18);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (1, NULL, NULL, 10, NULL, NULL, NULL, 0, NULL, NULL, 19);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (1, NULL, NULL, 10, NULL, NULL, NULL, 0, NULL, NULL, 20);

insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (1, NULL, NULL, 10, NULL, NULL, NULL, 0, NULL, NULL, 21);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (1, NULL, NULL, 10, NULL, NULL, NULL, 0, NULL, NULL, 22);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (1, NULL, NULL, 10, NULL, NULL, NULL, 0, NULL, NULL, 23);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (1, NULL, NULL, 10, NULL, NULL, NULL, 0, NULL, NULL, 24);*/


insert into rentacar (rentacar_id, adresa, broj_ocena, naziv, ocena, opis, prihod) values (89, 'Nemanje Vujovica 5, 37000 Cuprija', 0, 'Rentiranje', 0, 'opisano je', 0);
insert into rentacar (rentacar_id, adresa, broj_ocena, naziv, ocena, opis, prihod) values (67, 'Nemanje Vujovica 5, 37000 Cuprija', 0, 'Rentiranje', 0, 'opisano je', 0);

insert into lokacija (lokacija_id, adresa, id_rentacar) values (34, 'Popovica Komorasa 77', 89);
insert into lokacija (lokacija_id, adresa, id_rentacar) values (55, 'Bulevar Evrope 2', 67);
insert into lokacija (lokacija_id, adresa, id_rentacar) values (21, 'Maksima gorkog 6', 89);

insert into vozilo (vozilo_id, adresa_lokacije, br_ocena, br_sedista, cena, godina_proizvodnje, marka, model, na_popustu, naziv, prosecna_ocena, rezervisano, tip, lokacija_id, rentacar_id, popust, ocene) values (23, 'Popovica Komorasa 77', 0, 5, 100, '2011', 'Audi', 'A3', 'NE', 'Nemanja', 0, 0, 'MINI', 55, 89, 0, 0);
insert into vozilo (vozilo_id, adresa_lokacije, br_ocena, br_sedista, cena, godina_proizvodnje, marka, model, na_popustu, naziv, prosecna_ocena, rezervisano, tip, lokacija_id, rentacar_id, popust, ocene) values (24, 'Cara Dusana 7', 0, 3, 20, '2001', 'Ford', 'FIESTA', 'DA', 'Nemanja1', 0, 0, 'LIMUZINA', 21, 89, 20, 0);
insert into vozilo (vozilo_id, adresa_lokacije, br_ocena, br_sedista, cena, godina_proizvodnje, marka, model, na_popustu, naziv, prosecna_ocena, rezervisano, tip, lokacija_id, rentacar_id, popust, ocene) values (25, 'Cara Lazara 27', 0, 5, 45, '2017', 'Fiat', 'Z3', 'NE', 'Nemanja2', 0, 0, 'KARAVAN', 55, 67, 0, 0);
insert into vozilo (vozilo_id, adresa_lokacije, br_ocena, br_sedista, cena, godina_proizvodnje, marka, model, na_popustu, naziv, prosecna_ocena, rezervisano, tip, lokacija_id, rentacar_id, popust, ocene) values (26, 'Dunavska 4', 0, 4, 80, '2009', 'BMW', 'B5', 'DA', 'Nemanja3', 0, 0, 'MINI', 34, 89, 5, 0);

insert into korisnik (id, br_telefona, email, grad, ime, lozinka, prezime, uloga, verifikovan, id_rentacar, prvo_logovanje) values (78, '0613333551', 'korisnik1@gmail.com', 'Novi Sad', 'Ime1', '9ee012ea8322c151576408181941188fd1402d7ed343717578e96c446a812162', 'Prezime1', 1, TRUE, 67, FALSE);
