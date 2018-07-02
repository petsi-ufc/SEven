$(document).ready(function () {
    $.getJSON('../js/estados_cidades.json', function (data) {

        var items = [];
        var options = '<option value="">Escolha um Estado</option>';
        var options_cidade_inicial = '<option value="">Escolha Primeiro um Estado</option>';

        $.each(data, function (key, val) {
            options += '<option value="' + val.nome + '">' + val.nome + '</option>';
        });
        $("#estados").html(options);
        
        if(document.getElementById("ufhidden").value !== ""){
            var e = document.getElementById("estados").options;
            for (var i = 0; i < e.length; i++) {
                if (e[i].value === document.getElementById("ufhidden").value) {
                    e[i].selected = true;
                    break;
                }
            }
            document.getElementById("ufhidden").value = "";          
            
        }
        
        $("#estados").change(function () {
                var options_cidades = '';
                var str = "";

                $("#estados option:selected").each(function () {
                    str += $(this).text();
                });
                
                $.each(data, function (key, val) {
                    if (val.nome == str) {
                        $.each(val.cidades, function (key_city, val_city) {
                            options_cidades += '<option value="' + val_city + '">' + val_city + '</option>';
                        });
                    }
                });

                $("#cidades").html(options_cidades);
            }).change();
            $("#cidades").html(options_cidade_inicial);
        
        if(document.getElementById("cidadehidden").value !== ""){
            var options_cidades = '';
            var str = "";

            $("#estados option:selected").each(function () {
                str += $(this).text();
            });

            $.each(data, function (key, val) {
                if (val.nome == str) {
                    $.each(val.cidades, function (key_city, val_city) {
                        options_cidades += '<option value="' + val_city + '">' + val_city + '</option>';
                    });
                }
            });
            
            $("#cidades").html(options_cidades);
            var c = document.getElementById("cidades").options;
            for (var i = 0; i < c.length; i++) {
                if (c[i].value === document.getElementById("cidadehidden").value) {
                    c[i].selected = true;
                    break;
                }
            }  
            document.getElementById("cidadehidden").value = "";
        }
    });

});