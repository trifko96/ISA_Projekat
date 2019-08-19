package ISA.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ISA.project.model.AvionskaKarta;

public interface AvionskaKartaRepozitorijum extends JpaRepository<AvionskaKarta, Long> {

	@Query("select karta from AvionskaKarta karta where karta.sediste.idSedista = ?1")
	public AvionskaKarta vratiKartu(long id);
	
	@Query("select karta.datum, count(karta) from AvionskaKarta karta where "
			+ "karta.let.avioKompanija.kompanijaId = :idKompanije and karta.sediste.status = ISA.project.model.StatusSedista.REZERVISANO group by karta.datum")
	public List<Object[]> vratiStatistikuPoDanu(@Param("idKompanije") long idKompanije);
	
	@Query(value = "select concat(date_part('year',datum), '/', date_part('week',datum)), count(*) from AvionskaKarta karta where karta.let.avioKompanija.kompanijaId = :idKompanije and karta.sediste.status = ISA.project.model.StatusSedista.REZERVISANO group by concat(date_part('year',datum), '/', date_part('week',datum))",
			  nativeQuery = true)
	public List<Object[]> vratiStatistikuPoNedelji(@Param("idKompanije") long idKompanije);
	
	@Query(value = "select date_part('year',datum), count(*) from AvionskaKarta karta where karta.let.avioKompanija.kompanijaId = :idKompanije and karta.sediste.status = ISA.project.model.StatusSedista.REZERVISANO group by date_part('year',datum)",
			  nativeQuery = true)
	public List<Object[]> vratiStatistikuPoGodini(@Param("idKompanije") long idKompanije);
}
