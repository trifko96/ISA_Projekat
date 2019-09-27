insert into avio_kompanija (kompanija_id, adresa, naziv, prihod, broj_ocena, ocena, koordinata1, koordinata2, info_prtljag) values (1, 'Bulevar Kralja Aleksandra 17, 11000 Beograd Srbija', 'Air-Serbia', 0, 0, 0, 44.8, 20.5, '15');
insert into avio_kompanija (kompanija_id, adresa, naziv, prihod, broj_ocena, ocena, koordinata1, koordinata2, info_prtljag) values (2, 'Bulevar Kralja Aleksandra 17, 11000 Beograd Srbija', 'Airlines', 0, 0, 0, 44.8, 20.5, '15');
insert into avio_kompanija (kompanija_id, adresa, naziv, prihod, broj_ocena, ocena, koordinata1, koordinata2, info_prtljag) values (33, 'Bulevar Kralja Aleksandra 17, 11000 Beograd Srbija', 'Kompanija', 0, 0, 0, 44.8, 20.5, '15');

insert into rentacar (rentacar_id, adresa, naziv, prihod, broj_ocena, ocena) values (1, 'Tesarska 6, 11253 Sremcica Srbija', 'ReCarD2', 0, 0, 0);
insert into rentacar (rentacar_id, adresa, naziv, prihod, broj_ocena, ocena) values (2, 'Bulevar Kralja Petra, 21000 Novi Sad', 'AutoPlay', 0, 0, 0);
insert into rentacar (rentacar_id, adresa, naziv, prihod, broj_ocena, ocena) values (33, 'Bulevar Kralja Petra, 21000 Novi Sad', 'RCar', 0, 0, 0);

insert into korisnik (id, br_telefona, email, grad, ime, lozinka, prezime, uloga, verifikovan, avio_kompanija_id, prvo_logovanje) values (1, '0613333555', 'korisnik1@gmail.com', 'Novi Sad', 'Ime1', '9ee012ea8322c151576408181941188fd1402d7ed343717578e96c446a812162', 'Prezime1', 1, TRUE, 1, FALSE);
insert into korisnik (id, br_telefona, email, grad, ime, lozinka, prezime, uloga, verifikovan, id_rentacar, prvo_logovanje) values (2, '0645555333', 'korisnik2@gmail.com', 'Beograd', 'Ime2', '39e7220d702b4df6309a28352dac75d7d12231f9c35906e105976a51bdabb020', 'Prezime2', 3, TRUE, 1, FALSE);
insert into korisnik (id, br_telefona, email, grad, ime, lozinka, prezime, uloga, verifikovan, avio_kompanija_id, prvo_logovanje) values (3, '0613333555', 'korisnik3@gmail.com', 'Novi Sad', 'Ime1', '9ee012ea8322c151576408181941188fd1402d7ed343717578e96c446a812162', 'Prezime1', 1, TRUE, 2, FALSE);
insert into korisnik (id, br_telefona, email, grad, ime, lozinka, prezime, uloga, verifikovan, id_rentacar, prvo_logovanje) values (4, '0645555333', 'korisnik4@gmail.com', 'Beograd', 'Ime2', '39e7220d702b4df6309a28352dac75d7d12231f9c35906e105976a51bdabb020', 'Prezime2', 3, TRUE, 2, FALSE);
insert into korisnik (id, br_telefona, email, grad, ime, lozinka, prezime, uloga, verifikovan) values (5, '0645555333', 'trifko94@gmail.com', 'Beograd', 'Ime2', '9ee012ea8322c151576408181941188fd1402d7ed343717578e96c446a812162', 'Prezime2', 0, TRUE);

insert into aerodrom (aerodrom_id, grad, ime) values (111, 'Beograd', 'Nikola Tesla1');
insert into aerodrom (aerodrom_id, grad, ime) values (112, 'Nis', 'Konstantin Veliki1');
insert into aerodrom (aerodrom_id, grad, ime) values (113, 'Mostar', 'Medjunarodni aerodrom1');

insert into aerodromi_kompanije (kompanija_id, aerodrom_id) values (1, 111);
insert into aerodromi_kompanije (kompanija_id, aerodrom_id) values (1, 112);
insert into aerodromi_kompanije (kompanija_id, aerodrom_id) values (1, 113);
insert into aerodromi_kompanije (kompanija_id, aerodrom_id) values (2, 111);
insert into aerodromi_kompanije (kompanija_id, aerodrom_id) values (2, 112);

insert into avion (id_aviona, ime, slobodan, id_avio_kompanije) values (111, 'Avion1', false, 1);
insert into avion (id_aviona, ime, slobodan, id_avio_kompanije) values (112, 'Avion2', false, 1);
insert into avion (id_aviona, ime, slobodan, id_avio_kompanije) values (113, 'Avion3', true, 2);

insert into let(id_leta, br_presedanja, br_prodatih_karata, broj_ocena, cena_karte_biznis_klase, cena_karte_ekonomske_klase, cena_prve_klase, ocene, popust, tip, vreme_dolaska_nazad, vreme_polaska_nazad, vreme_poletanja, vreme_putovanja, vreme_sletanja, id_avio_kompanije, id_aviona, odredisna_destinacija_id, polazna_destinacija_id, duzina_leta) values (111, 0, 0, 0, 10, 12, 14, 0, 0, 'ONE_WAY', NULL, NULL, '2019-09-02', 0, '2019-09-03', 1, 111, 111, 112, 100);
insert into let(id_leta, br_presedanja, br_prodatih_karata, broj_ocena, cena_karte_biznis_klase, cena_karte_ekonomske_klase, cena_prve_klase, ocene, popust, tip, vreme_dolaska_nazad, vreme_polaska_nazad, vreme_poletanja, vreme_putovanja, vreme_sletanja, id_avio_kompanije, id_aviona, odredisna_destinacija_id, polazna_destinacija_id, duzina_leta) values (112, 0, 0, 0, 10, 12, 14, 0, 0, 'ONE_WAY', NULL, NULL, '2019-09-02', 0, '2019-09-03', 1, 112, 111, 112, 100);

insert into segment (id_segmenta, broj_kolona, broj_redova, tip, avion_id) values (111, 2, 2, 0, 111);
insert into segment (id_segmenta, broj_kolona, broj_redova, tip, avion_id) values (112, 2, 2, 1, 111);
insert into segment (id_segmenta, broj_kolona, broj_redova, tip, avion_id) values (113, 2, 2, 2, 111);

insert into segment (id_segmenta, broj_kolona, broj_redova, tip, avion_id) values (121, 2, 2, 0, 112);
insert into segment (id_segmenta, broj_kolona, broj_redova, tip, avion_id) values (122, 2, 2, 1, 112);
insert into segment (id_segmenta, broj_kolona, broj_redova, tip, avion_id) values (123, 2, 2, 2, 112);

insert into segment (id_segmenta, broj_kolona, broj_redova, tip, avion_id) values (131, 2, 2, 0, 113);
insert into segment (id_segmenta, broj_kolona, broj_redova, tip, avion_id) values (132, 2, 2, 1, 113);
insert into segment (id_segmenta, broj_kolona, broj_redova, tip, avion_id) values (133, 2, 2, 2, 113);

insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (111, 1, 1, 0, 1, 0, 0, 111);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (112, 1, 2, 1, 2, 0, 0, 111);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (113, 2, 1, 0, 3, 2, 0, 111);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (114, 2, 2, 1, 4, 0, 0, 111);

insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (115, 1, 1, 0, 5, 0, 0, 112);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (116, 1, 2, 1, 6, 0, 0, 112);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (117, 2, 1, 0, 7, 2, 0, 112);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (118, 2, 2, 1, 8, 0, 0, 112);

insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (119, 1, 1, 0, 9, 0, 0, 113);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (210, 1, 2, 1, 10, 1, 0, 113);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (211, 2, 1, 0, 11, 0, 0, 113);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (212, 2, 2, 1, 12, 0, 0, 113);

insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (213, 1, 1, 0, 1, 0, 0, 121);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (214, 1, 2, 1, 2, 0, 0, 121);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (215, 2, 1, 0, 3, 0, 0, 121);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (216, 2, 2, 1, 4, 0, 0, 121);

insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (217, 1, 1, 0, 5, 0, 0, 122);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (218, 1, 2, 1, 6, 0, 0, 122);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (219, 2, 1, 0, 7, 0, 0, 122);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (220, 2, 2, 1, 8, 0, 0, 122);

insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (221, 1, 1, 0, 9, 0, 0, 123);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (222, 1, 2, 1, 10, 0, 0, 123);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (223, 2, 1, 0, 11, 0, 0, 123);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (224, 2, 2, 1, 12, 0, 0, 123);

insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (225, 1, 1, 0, 1, 0, 0, 131);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (226, 1, 2, 1, 2, 0, 0, 131);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (227, 2, 1, 0, 3, 0, 0, 131);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (228, 2, 2, 1, 4, 0, 0, 131);

insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (229, 1, 1, 0, 5, 0, 0, 132);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (230, 1, 2, 1, 6, 0, 0, 132);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (231, 2, 1, 0, 7, 0, 0, 132);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (232, 2, 2, 1, 8, 0, 0, 132);

insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (233, 1, 1, 0, 9, 0, 0, 133);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (234, 1, 2, 1, 10, 0, 0, 133);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (235, 2, 1, 0, 11, 0, 0, 133);
insert into sediste (id_sedista, br_kolone, br_reda, granica, natpis, status, zauzeto, segment_id) values (236, 2, 2, 1, 12, 0, 0, 133);

insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (111, NULL, NULL, 10, NULL, NULL, NULL, 0, NULL, 111, 111);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (112, NULL, NULL, 10, NULL, NULL, NULL, 0, NULL, 111, 112);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (113, NULL, NULL, 10, NULL, NULL, NULL, 0, NULL, 111, 113);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (114, NULL, NULL, 10, NULL, NULL, NULL, 0, NULL, 111, 114);

insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (115, NULL, NULL, 12, NULL, NULL, NULL, 0, NULL, 111, 115);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (116, NULL, NULL, 12, NULL, NULL, NULL, 0, NULL, 111, 116);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (117, NULL, NULL, 12, NULL, NULL, NULL, 0, NULL, 111, 117);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (118, NULL, NULL, 12, NULL, NULL, NULL, 0, NULL, 111, 118);

insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (119, NULL, NULL, 14, NULL, NULL, NULL, 0, NULL, 111, 119);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (210, '0612424235', '312313', 14, '2019-09-06', 'trifko96@gmail.com', 'Mihailo', 0, 'Trifkovic', 111, 210);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (211, NULL, NULL, 14, NULL, NULL, NULL, 0, NULL, 111, 211);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (212, NULL, NULL, 14, NULL, NULL, NULL, 0, NULL, 111, 212);

insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (213, NULL, NULL, 10, NULL, NULL, NULL, 0, NULL, 112, 213);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (214, NULL, NULL, 10, NULL, NULL, NULL, 0, NULL, 112, 214);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (215, NULL, NULL, 10, NULL, NULL, NULL, 0, NULL, 112, 215);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (216, NULL, NULL, 10, NULL, NULL, NULL, 0, NULL, 112, 216);

insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (217, NULL, NULL, 12, NULL, NULL, NULL, 0, NULL, 112, 217);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (218, NULL, NULL, 12, NULL, NULL, NULL, 0, NULL, 112, 218);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (219, NULL, NULL, 12, NULL, NULL, NULL, 0, NULL, 112, 219);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (220, NULL, NULL, 12, NULL, NULL, NULL, 0, NULL, 112, 220);

insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (221, NULL, NULL, 14, NULL, NULL, NULL, 0, NULL, 112, 221);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (222, NULL, NULL, 14, NULL, NULL, NULL, 0, NULL, 112, 222);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (223, NULL, NULL, 14, NULL, NULL, NULL, 0, NULL, 112, 223);
insert into avionska_karta (id_karte, br_telefona_putnika, broj_pasosa_putnika, cena, datum, email_putnika, ime_putnika, popust, prezime_putnika, id_leta, id_sedista) values (224, NULL, NULL, 14, NULL, NULL, NULL, 0, NULL, 112, 224);

insert into lokacija (lokacija_id, adresa, id_rentacar) values (334, 'Popovica Komorasa 77', 1);
insert into lokacija (lokacija_id, adresa, id_rentacar) values (335, 'Bulevar Evrope 21', 2);
insert into lokacija (lokacija_id, adresa, id_rentacar) values (336, 'Maksima gorkog 6', 1);

insert into vozilo (vozilo_id, adresa_lokacije, br_ocena, br_sedista, cena, godina_proizvodnje, marka, model, na_popustu, naziv, prosecna_ocena, rezervisano, tip, lokacija_id, rentacar_id, popust, ocene) values (223, 'Popovica Komorasa 77', 0, 5, 100, '2011', 'Audi', 'A3', 'NE', 'Nemanja', 0, 0, 'MINI', 334, 1, 0, 0);
insert into vozilo (vozilo_id, adresa_lokacije, br_ocena, br_sedista, cena, godina_proizvodnje, marka, model, na_popustu, naziv, prosecna_ocena, rezervisano, tip, lokacija_id, rentacar_id, popust, ocene) values (224, 'Bulevar Evrope 2', 0, 3, 20, '2001', 'Ford', 'FIESTA', 'DA', 'Nemanja1', 0, 0, 'LIMUZINA', 334, 1, 20, 0);
insert into vozilo (vozilo_id, adresa_lokacije, br_ocena, br_sedista, cena, godina_proizvodnje, marka, model, na_popustu, naziv, prosecna_ocena, rezervisano, tip, lokacija_id, rentacar_id, popust, ocene) values (225, 'Bulevar Evrope 2', 0, 5, 45, '2017', 'Fiat', 'Z3', 'NE', 'Nemanja2', 0, 0, 'KARAVAN', 335, 2, 0, 0);
insert into vozilo (vozilo_id, adresa_lokacije, br_ocena, br_sedista, cena, godina_proizvodnje, marka, model, na_popustu, naziv, prosecna_ocena, rezervisano, tip, lokacija_id, rentacar_id, popust, ocene) values (226, 'Maksima gorkog 6', 0, 4, 80, '2009', 'BMW', 'B5', 'DA', 'Nemanja3', 0, 0, 'MINI', 336, 1, 5, 0);
