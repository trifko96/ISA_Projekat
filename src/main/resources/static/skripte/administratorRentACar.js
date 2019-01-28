$(document).ready(function(){
	
	$(".navbar-link").click(function(event){
		
		$(".container-fluid").each(function(index, el) {
            $(this).hide();
        });
		
		var id = $(this).attr("id");
		if(id === "tabInfStr"){
			$("#tabInfStr").addClass('active');
			$("#tabVoz").removeClass('active');
			$("#tabCen").removeClass('active');
			$("#tabRezVoz").removeClass('active');
			$("#tabPrih").removeClass('active');
			$("#tabPod").removeClass('active');
			$("#divInfoStranica").show();
		} else if (id === "tabVoz"){
			$("#tabVoz").addClass('active');
			$("#tabInfStr").removeClass('active');
			$("#tabCen").removeClass('active');
			$("#tabRedVoz").removeClass('active');
			$("#tabPrih").removeClass('active');
			$("#tabPod").removeClass('active');
			$("#divVozila").show();
		} else if (id === "tabRezVoz"){
			$("#tabRezVoz").addClass('active');
			$("#tabInfStr").removeClass('active');
			$("#tabVoz").removeClass('active');
			$("#tabCen").removeClass('active');
			$("#tabPrih").removeClass('active');
			$("#tabPod").removeClass('active');
			$("#divRezervisanaVozila").show();
		} else if (id === "tabPrih"){
			$("#tabPrih").addClass('active');
			$("#tabVoz").removeClass('active');
			$("#tabCen").removeClass('active');
			$("#tabInfStr").removeClass('active');
			$("#tabRezVoz").removeClass('active');
			$("#tabPod").removeClass('active');
			$("#divPrihodi").show();
		} else if (id === "tabPod"){
			$("#tabPod").addClass('active');
			$("#tabInfStr").removeClass('active');
			$("#tabVoz").removeClass('active');
			$("#tabCen").removeClass('active');
			$("#tabRezVoz").removeClass('active');
			$("#tabPrih").removeClass('active');
			$("#divRegistracija").show();
		}

	});
	
	$("#tabInfStr").trigger('click');
	
	$("small").each(function(index, el){
		$(this).hide();
	});

});
