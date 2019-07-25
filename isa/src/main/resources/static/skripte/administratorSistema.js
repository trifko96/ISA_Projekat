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
			$("#tabSis").removeClass('active');
			$("#divKompanija").show();
		} else if (id === "tabHot"){
			$("#tabHot").addClass('active');
			$("#tabSis").removeClass('active');
			$("#tabRen").removeClass('active');
			$("#tabKom").removeClass('active');
			$("#divHotel").show();
		} else if (id === "tabRen"){
			$("#tabRen").addClass('active');
			$("#tabSis").removeClass('active');
			$("#tabHot").removeClass('active');
			$("#tabKom").removeClass('active');
			$("#divRentACar").show();
		} else if (id === "tabSis"){
			$("#tabKom").removeClass('active');
			$("#tabHot").removeClass('active');
			$("#tabSis").addClass('active');
			$("#tabRen").removeClass('active');
			$("#divAdmini").show();
		}

	});
	
	$("#tabKom").trigger('click');
	
	$("small").each(function(index, el){
		$(this).hide();
	});

});
