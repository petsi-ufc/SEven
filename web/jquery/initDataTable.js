var seta_normal = "imagens/seta_normal.png";
var seta_crescente = "imagens/seta_crescente.png";
var seta_decrescente = "imagens/seta_decrescente.png";

$(document).ready(function () {
    
    if (!file_exists(seta_normal)) {
        seta_normal = "../"+seta_normal;
        seta_crescente = "../"+seta_crescente;
        seta_decrescente = "../"+seta_decrescente;
    }

    $('#data_table, .data_table').dataTable({
        "bPaginate": false,
        "bJQueryUI": false,
        "scrollY": "",
        "scrollCollapse": true,
        "paging": false,
        "oLanguage": {
            "sLengthMenu": "Mostrar _MENU_ registros",
            "sZeroRecords": "<b>Nenhum registro</b>",
            "sInfoEmpty": "Nenhum registro",
            "sInfoFiltered": "",
            "sSearch": "<span style='color: #fff; font-family: Verdana, Tahoma, Arial;'></span>",
            "sInfoPostFix": "",
            "sUrl": "",
            "oPaginate": {
                "sFirst": "Primeira",
                "sPrevious": "Anterior",
                "sNext": "Seguinte",
                "sLast": "Última"
            }
        }
    });
    
    $('table').each(function(){
        $('th:not(th:first)', $(this)).append("<br><img src='" + seta_normal + "' width='13px' height='13px' style='float: left;' />");
        $('th:first', $(this)).append("<br><img src='" + seta_crescente + "' width='13px' height='13px' style='float: left;' />");
        $('th', $(this)).attr('onmouseup', 'atualizarImagensSetas(this);');
    });
    
});

function atualizarImagensSetas(obj){
    if($('img', obj).attr('src')===seta_normal){
        $('th img').attr('src', seta_normal);
        $(obj).children().remove();
        $(obj).append("<br><img src='" + seta_crescente + "' width='13px' height='13px' style='float: left;' />");
    } else {
        var seta_aux = ($('img', obj).attr('src')===seta_crescente) ? seta_decrescente : seta_crescente;
        $(obj).children().remove();
        $(obj).append("<br><img src='" + seta_aux + "' width='13px' height='13px' style='float: left;' />");
    }
}

function file_exists(url) {
    var req = this.window.ActiveXObject ? new ActiveXObject("Microsoft.XMLHTTP") : new XMLHttpRequest();
    if (!req) {
        throw new Error('XMLHttpRequest not supported');
    }
    req.open('HEAD', url, false);
    req.send(null);
    if (req.status === 200) {
        return true;
    }
    return false;
}
