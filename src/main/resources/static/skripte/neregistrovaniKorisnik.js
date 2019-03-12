$(document).ready(function(){
	
	$(".navbar-link").click(function(event){
		
		$(".container-fluid").each(function(index, el) {
            $(this).hide();
        });
		
		var id = $(this).attr("id");
		if(id === "tabKom"){
			$("#tabKom").addClass('active');
			$("#tabHot").removeClass('active');
			$("#tabRen").removeClass('active');
			$("#tabReg").removeClass('active');
			$("#tabLog").removeClass('active');
			$("#divKompanija").show();
		} else if (id === "tabHot"){
			$("#tabHot").addClass('active');
			$("#tabReg").removeClass('active');
			$("#tabLog").removeClass('active');
			$("#tabRen").removeClass('active');
			$("#tabKom").removeClass('active');
			$("#divHotel").show();
		} else if (id === "tabRen"){
			$("#tabRen").addClass('active');
			$("#tabReg").removeClass('active');
			$("#tabHot").removeClass('active');
			$("#tabLog").removeClass('active');
			$("#tabKom").removeClass('active');
			$("#divRentACar").show();
		} else if (id === "tabReg"){
			$("#tabLog").removeClass('active');
			$("#tabKom").removeClass('active');
			$("#tabHot").removeClass('active');
			$("#tabReg").addClass('active');
			$("#tabRen").removeClass('active');
			$("#divRegistracija").show();
		} else if (id === "tabLog"){
			$("#tabHot").removeClass('active');
			$("#tabReg").removeClass('active');
			$("#tabRen").removeClass('active');
			$("#tabLog").addClass('active');
			$("#tabKom").removeClass('active');
			$("#divLogovanje").show();
		}

	});
	
	$("#tabKom").trigger('click');
	
	$("small").each(function(index, el){
		$(this).hide();
	});
	
	function ispravanMail(email) {
		var mail = /\S+@\S+\.\S+/;
		return mail.test(email);
	}
	
	$("#butReg").click(function(event){
		event.preventDefault();
		var lozinka = $("#pass");
		var novaLozinka = $("#passPon");
		var ime = $("#ime");
		var prezime = $("#prezime");
		var brTelefona = $("#telefon");
		var email = $("#email");
		var grad = $("#grad");
		
		
		if(!lozinka.val().trim() || !ime.val().trim() || !prezime.val().trim()
				|| !brTelefona.val().trim() || !email.val().trim() || !grad.val().trim() || isNaN(brTelefona.val()) ||
				!ispravanMail(email.val()) || !(lozinka.val()==novaLozinka.val())){
			
			if(!grad.val().trim())
				grad.addClass('bg-danger');
			else
				grad.removeClass('bg-danger');
			
			if(!lozinka.val().trim()) {
				lozinka.addClass('bg-danger');
			}
			else {
				lozinka.removeClass('bg-danger');
			}
			
			if(!novaLozinka.val().trim())
				novaLozinka.addClass('bg-danger');
			else
				novaLozinka.removeClass('bg-danger');
			
			if(!ime.val().trim())
				ime.addClass('bg-danger');
			else
				ime.removeClass('bg-danger');
			
			if(!prezime.val().trim())
				prezime.addClass('bg-danger');
			else
				prezime.removeClass('bg-danger');
			
			if(!brTelefona.val().trim())
				brTelefona.addClass('bg-danger');
			else
				brTelefona.removeClass('bg-danger');
			
			if(!email.val().trim())
				email.addClass('bg-danger');
			else
				email.removeClass('bg-danger');
			
			if(isNaN(brTelefona.val()))
				$("#greskaTelefon").show().delay(4000).fadeOut();
			
			if(!ispravanMail(email.val()) && email.val().trim())
				$("#greskaEmailOblik").show().delay(4000).fadeOut();
			
			if(!(lozinka.val()==novaLozinka.val()))
				$("#greskaLozinka").show().delay(4000).fadeOut();
		} else {
			
			var korisnik = new Object();
			korisnik.ime = ime.val();
			korisnik.prezime = prezime.val();
			korisnik.email = email.val();
			korisnik.lozinka = lozinka.val();
			korisnik.grad = grad.val();
			korisnik.brTelefona = brTelefona.val();
			$.ajax({
				type: "POST",
				url: '/Korisnik/registracija',
				data: JSON.stringify(korisnik),
				contentType: 'application/json',
				success: function(data) {
					if(data == "ok"){
						window.location.href = "html/registrovani.html";
					} else if (data == "greskaKorIme"){
						$("#greskaKorIme").show().delay(4000).fadeOut();
					} else if (data == "greska"){
						$("#greskaEmail").show().delay(4000).fadeOut();
					}
				},
			});
		}
		
	});
	
	

});
