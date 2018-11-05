$(document).ready(function() {
    $( "#estado" ).change(function() {
      getCidades();
    });
});

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
