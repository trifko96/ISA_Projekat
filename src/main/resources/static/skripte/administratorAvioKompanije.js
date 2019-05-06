$(document).ready(function(){
	
	$(".navbar-link").click(function(event){
		
		$(".container-fluid").each(function(index, el) {
            $(this).hide();
        });
		
		var id = $(this).attr("id");
		if(id === "tabKom"){
			$("#tabKom").addClass('active');
			$("#tabLet").removeClass('active');
			$("#tabAer").removeClass('active');
			$("#tabAvi").removeClass('active');
			$("#tabPro").removeClass('active');
			$("#divKompanija").show();
		} else if (id === "tabLet"){
			$("#tabLet").addClass('active');
			$("#tabKom").removeClass('active');
			$("#tabAer").removeClass('active');
			$("#tabAvi").removeClass('active');
			$("#tabPro").removeClass('active');
			$("#divLet").show();
		} else if (id === "tabAer"){
			$("#tabAer").addClass('active');
			$("#tabKom").removeClass('active');
			$("#tabLet").removeClass('active');
			$("#tabAvi").removeClass('active');
			$("#tabPro").removeClass('active');
			$("#divAerodrom").show();
		} else if (id === "tabAvi"){
			$("#tabKom").removeClass('active');
			$("#tabLet").removeClass('active');
			$("#tabAvi").addClass('active');
			$("#tabAer").removeClass('active');
			$("#tabPro").removeClass('active');
			$("#divAvion").show();
		} else if (id === "tabPro"){
			$("#tabKom").removeClass('active');
			$("#tabLet").removeClass('active');
			$("#tabPro").addClass('active');
			$("#tabAer").removeClass('active');
			$("#tabAvi").removeClass('active');
			$("#divProfil").show();
		}

	});
	
	$("#tabKom").trigger('click');
	
	$("small").each(function(index, el){
		$(this).hide();
	});
	
	function unesiKompaniju(data){
		var zbir = data.ocena;
		var brojac = data.brojOcena;
		var prosek;
		if(brojac != 0)
			prosek = zbir / brojac;
		else
			prosek = "Nema ocena!";
		var pom = '<tr><td>'+ data.naziv +'</td>' +
				'<td>'+ data.adresa + '</td>' + 
				'<td>'+ data.opis + '</td>' +
				'<td>'+ prosek + '</td>' +
				'<td><button class="btn btn-link">Izmeni</button></td></tr>';
		$("#tabelaNovihKompanija").append(pom);
	}
	
	$.ajax({
		type: "GET",
		url: '/AvioKompanija/vratiKompaniju',
		contentType: 'application/json',
		success: function(data){
			unesiKompaniju(data);
		}
	});
	
	$("#tabOdjava").click(function(event){
		$.ajax({
			type: "POST",
			url: '/Korisnik/odjava',
			contentType: 'application/json',
			success: function(data){
				window.location.href = "/html/neregistrovaniKorisnik.html";
			},
		});
	});

});
