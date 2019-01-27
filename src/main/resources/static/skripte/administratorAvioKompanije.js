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
			$("#divAdmini").show();
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

});
