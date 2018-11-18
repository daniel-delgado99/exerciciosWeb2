$(document).ready(function() {
    $("#estado").change(function() {
      getCidades();
    });
    $("#cpf").mask('000.000.000-00');
    // validar cpf após submissão do form
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

function toggleButtons() {
	$("#collapsedItems").toggleClass('active');
}
