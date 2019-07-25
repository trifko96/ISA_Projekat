$(document).ready(function(){
	
	$(".navbar-link").click(function(event){
		
		$(".container-fluid").each(function(index, el) {
            $(this).hide();
        });
		
		var id = $(this).attr("id");
		if(id === "tabIst"){
			$("#tabIst").addClass('active');
			$("#tabRez").removeClass('active');
			$("#tabPro").removeClass('active');
			$("#tabZah").removeClass('active');
			$("#divKompanija").show();
		} else if (id === "tabRez"){
			$("#tabRez").addClass('active');
			$("#tabIst").removeClass('active');
			$("#tabPro").removeClass('active');
			$("#tabZah").removeClass('active');
			$("#divHotel").show();
		} else if (id === "tabPro"){
			$("#tabPro").addClass('active');
			$("#tabRez").removeClass('active');
			$("#tabIst").removeClass('active');
			$("#tabZah").removeClass('active');
			$("#divProfil").show();
		} else if (id === "tabZah"){
			$("#tabIst").removeClass('active');
			$("#tabRez").removeClass('active');
			$("#tabZah").addClass('active');
			$("#tabPro").removeClass('active');
			$("#divPozivnice").show();
		}

	});
	
	$("#tabKom").trigger('click');
	
	$("small").each(function(index, el){
		$(this).hide();
	});

});
