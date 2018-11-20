$(document).ready(function() {
    $("#estado").change(function() {
      getCidades();
    });
    $("#cpf").mask('000.000.000-00');
    $("#cep").mask('00000-000');
    
    $("#dataHora").val(getCurrentDate());
    
    $("#collapseButton").click(function() {
        toggleButtons();
    });    
});

function getCurrentDate() {
	return ((new Date()).toLocaleString());
}

function getCidades(){
    var estadoId = $("#estado").val();
    var url = "AJAXServlet";
    $.ajax({
        url: url,
        data: {
            estadoId : estadoId
        },
        dataType: 'json',
        success: function(data) {
            $("#cidade").empty();
            $.each(data, function(i, obj) {
                $("#cidade").append('<option value=' + obj.id + '>' + obj.nome + '</option>');
            });
        },
        error: function(request, textStatus, errorThrown) {
            alert(request.status + ', Error: ' + request.statusText);
        }
    });
}

function validaForm() {
	let cpf = $("#cpf").val();
	if(!validaCpf(cpf)) {
		alert("Favor digitar um cpf valido");
		return false;
	}
	if ($("#estado").val() == "" || $("#estado").val() == null) {
		alert("Favor selecionar estado");
		return false;
	}
	if ($("#cidade").val() == "" || $("#cidade").val() == null) {
		alert("Favor selecionar cidade");
		return false;
	}
	return true;
}

function validaCpf(cpf) {
	cpf = cpf.replace(/[^\d]+/g,'');	
	if(cpf == '') return false;	
	// Elimina CPFs invalidos conhecidos	
	if (cpf.length != 11 || 
		cpf == "00000000000" || 
		cpf == "11111111111" || 
		cpf == "22222222222" || 
		cpf == "33333333333" || 
		cpf == "44444444444" || 
		cpf == "55555555555" || 
		cpf == "66666666666" || 
		cpf == "77777777777" || 
		cpf == "88888888888" || 
		cpf == "99999999999")
			return false;		
	// Valida 1o digito	
	add = 0;	
	for (i=0; i < 9; i ++)		
		add += parseInt(cpf.charAt(i)) * (10 - i);	
		rev = 11 - (add % 11);	
		if (rev == 10 || rev == 11)		
			rev = 0;	
		if (rev != parseInt(cpf.charAt(9)))		
			return false;		
	// Valida 2o digito	
	add = 0;	
	for (i = 0; i < 10; i ++)		
		add += parseInt(cpf.charAt(i)) * (11 - i);	
	rev = 11 - (add % 11);	
	if (rev == 10 || rev == 11)	
		rev = 0;	
	if (rev != parseInt(cpf.charAt(10)))
		return false;		
	return true;  
}

function toggleButtons() {
	$("#collapsedItems").toggleClass('active');
}
