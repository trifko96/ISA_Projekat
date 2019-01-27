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

});
