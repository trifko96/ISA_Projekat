package ISA.project.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import ISA.project.dto.AvionskaKartaDTO;
import ISA.project.dto.RezervacijaDTO;
import ISA.project.dto.RezervacijaKarataDTO;
import ISA.project.model.Korisnik;
import ISA.project.model.Let;
import ISA.project.model.Segment;

@Service
public class EmailServis {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment env;
	
	@Async
	public void sendNotificaitionAsync(Korisnik k) throws MailException, InterruptedException, MessagingException {

		System.out.println("Slanje emaila...");
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

		String htmlMsg = "<h3>Pozdrav "+k.getIme()+"</h3><br> <p>Da biste aktivirali profil posetite  <a href=\"http://localhost:11000/Korisnik/aktiviraj/"+k.getEmail()+"\">link</a></p>";
		mimeMessage.setContent(htmlMsg, "text/html");
		helper.setTo(k.getEmail());
		helper.setSubject("Verifikacija clanstva");
		helper.setFrom(env.getProperty("spring.mail.username"));
		javaMailSender.send(mimeMessage);
	
		System.out.println("Email poslat!");
	}
	
	@Async
	public void obavestenjeORezervaciji(Korisnik k, AvionskaKartaDTO a) throws MailException, InterruptedException, MessagingException {
		System.out.println("Slanje emaila...");
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
		
		String htmlMsg = "<h3>Pozdrav "+k.getIme()+"</h3><br> <p>Uspesno ste rezervisali kartu za let na relaciji "+
				a.getLet().getPolaznaDestinacija().getGrad()+ " - " + a.getLet().getOdredisnaDestinacija().getGrad()+
				".<br> Vase mesto u avionu je: "+ a.getKlasa() + " klasa, broj sedista: "+ a.getBrSedista()+
				".";
		mimeMessage.setContent(htmlMsg, "text/html");
		helper.setTo(k.getEmail());
		helper.setSubject("Obavestenje o rezervaciji");
		helper.setFrom(env.getProperty("spring.mail.username"));
		javaMailSender.send(mimeMessage);
		
		System.out.println("Email poslat!");
	}
	
	@Async
	public void rezervacijaInformacije(RezervacijaDTO r, Korisnik k, Let l, Segment s) throws MailException, InterruptedException, MessagingException {
		System.out.println("Slanje emaila...");
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
		
		String htmlMsg = "<h3>Pozdrav "+k.getIme()+"</h3><br> <p>Uspesno ste rezervisali let na relaciji "+
				l.getPolaznaDestinacija().getGrad()+ " - " + l.getOdredisnaDestinacija().getGrad()+
				".<br> Rezervisana mesta u avionu su: ";
		
		for(RezervacijaKarataDTO rez : r.getKarte()) {
			htmlMsg += "<br>"+ s.getTip() + " klasa, broj sedista: "+ rez.getBrSedista()+
					", "+ rez.getIme() + " "+rez.getPrezime()+".<br>";
		}
		mimeMessage.setContent(htmlMsg, "text/html");
		helper.setTo(k.getEmail());
		helper.setSubject("Obavestenje o rezervaciji");
		helper.setFrom(env.getProperty("spring.mail.username"));
		javaMailSender.send(mimeMessage);
		
		System.out.println("Email poslat!");
	}
	
	@Async
	public void pozivZaLet(Let l, double cena, RezervacijaKarataDTO r) throws MailException, InterruptedException, MessagingException {

		System.out.println("Slanje emaila...");
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

		String htmlMsg = "<h3>Pozdrav "+r.getIme()+"</h3><br> Dobili ste poziv za let na relaciji "+
		l.getPolaznaDestinacija().getGrad()+ " - "+l.getOdredisnaDestinacija().getGrad()+".<br>Cena karte je "+cena+" EUR."+
				"<br><br><a href=\"http://localhost:11000/Sediste/potvrdi/"+r.getIdSedista()+"\">Potvrdi</a> "+
				"     <a href=\"http://localhost:11000/Sediste/otkazi/"+r.getIdSedista()+"\">Otkazi</a>";
		mimeMessage.setContent(htmlMsg, "text/html");
		helper.setTo(r.getEmail());
		helper.setSubject("Poziv za let");
		helper.setFrom(env.getProperty("spring.mail.username"));
		javaMailSender.send(mimeMessage);
	
		System.out.println("Email poslat!");
	}
}
