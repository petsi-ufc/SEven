function validaNumeros(event)
{
    var Tecla = event.which;
    if (Tecla == null)
        Tecla = event.keyCode;
    if ((Tecla < 48 || Tecla > 57) && (Tecla < 0 || Tecla > 27)) {
        event.returnValue = false;
        alert("Só devem ser digitados números!")
        return false;
    }
    event.returnValue = true;
    return true;
}

function iniciarCalendario(event)
{
    document.getElementById("datenasc").focus();
}

function validaNumerosSilencioso(event)
{
    var Tecla = event.which;
    if (Tecla == null)
        Tecla = event.keyCode;
    if ((Tecla < 48 || Tecla > 57) && (Tecla < 0 || Tecla > 27)) {
        event.returnValue = false;
        return false;
    }
    event.returnValue = true;
    return true;
}

function confirmarExclucao() {

    if (confirm("Deseja Realmente Excluir?")) {
        return true;
    }
    return false;
}

function confirmarCadastrado() {

    if (confirm("Deseja Realmente Efetuar Operação?")) {
        return true;
    }
    return false;

}

function formatar(objeto, sMask, evtKeyPress) {
    var i, nCount, sValue, fldLen, mskLen, bolMask, sCod, nTecla;
    //funcao para formatar campo CPF, DATA, TEL, CEP, COD
    if (document.all) { // Internet Explorer
        nTecla = evtKeyPress.keyCode;
    } else if (document.layers) { // Nestcape
        nTecla = evtKeyPress.which;
    } else {
        nTecla = evtKeyPress.which;
        if (nTecla == 8) {
            return true;
        }
    }
    sValue = objeto.value;
    // Limpa todos os caracteres de formata‡ão que
    // j  estiverem no campo.
    sValue = sValue.toString().replace("-", "");
    sValue = sValue.toString().replace("-", "");
    sValue = sValue.toString().replace(".", "");
    sValue = sValue.toString().replace(".", "");
    sValue = sValue.toString().replace("/", "");
    sValue = sValue.toString().replace("/", "");
    sValue = sValue.toString().replace(":", "");
    sValue = sValue.toString().replace(":", "");
    sValue = sValue.toString().replace("(", "");
    sValue = sValue.toString().replace("(", "");
    sValue = sValue.toString().replace(")", "");
    sValue = sValue.toString().replace(")", "");
    sValue = sValue.toString().replace(" ", "");
    sValue = sValue.toString().replace(" ", "");
    fldLen = sValue.length;
    mskLen = sMask.length;
    i = 0;
    nCount = 0;
    sCod = "";
    mskLen = fldLen;
    while (i <= mskLen) {
        bolMask = ((sMask.charAt(i) == "-") || (sMask.charAt(i) == ".") || (sMask.charAt(i) == "/") || (sMask.charAt(i) == ":"))
        bolMask = bolMask || ((sMask.charAt(i) == "(") || (sMask.charAt(i) == ")") || (sMask.charAt(i) == " "))
        if (bolMask) {
            sCod += sMask.charAt(i);
            mskLen++;
        } else {
            sCod += sValue.charAt(nCount);
            nCount++;
        }
        i++;
    }
    objeto.value = sCod;
    if (nTecla != 8) { // backspace
        if (sMask.charAt(i - 1) == "9") { // apenas n£meros...
            return ((nTecla > 47) && (nTecla < 58));
        } else { // qualquer caracter...
            return true;
        }
    } else {
        return true;
    }
}

function formataContato(Campo, teclapres)
{
    if (!validaNumerosSilencioso(teclapres)) {
        return false;
    }
    return formatar(Campo, '(##)#########', teclapres);
}

function formataData(Campo, teclapres)
{
    if (!validaNumerosSilencioso(teclapres)) {
        return false;
    }
    return formatar(Campo, '##/##/####', teclapres);
}

function formataCPF(campo, teclapres)
{
    if (!validaNumerosSilencioso(teclapres)) {
        return false;
    }
    return formatar(campo, '###.###.###-##', teclapres);
}

//variavel auxiliar para saber se o usuario gerou certificado  na sessão
var flagCertificadoGerado = false;
function clickGerarCerticiado(flagUsuarioSessao, id) {
    if (flagUsuarioSessao || flagCertificadoGerado) {
        return true;
    } else {
        var link = document.getElementById('linkGerarCertificado').href;
        document.getElementById('linkGerarCertificado').href = link + id;
        location = "part_visualizar_inscricoes.jsp#openModal";
        return false;
    }
}

function clickSimModal() {
    flagCertificadoGerado = true;
    document.getElementById('openModal').style = "display: none";
}

$(function () {
    $('#checkTable tr').click(function (event) {
        if (event.target.type !== 'checkbox') {
            $(':checkbox', this).trigger('click');
        }
    });
});

$(function () {
    $('#rdSim').click(function (event) {
        if (event.target.type !== 'radio') {
            $(':radio', this).trigger('click');
        }
    });
     $('#rdNao').click(function (event) {
        if (event.target.type !== 'radio') {
            $(':radio', this).trigger('click');
        }
    });
     $('#rdCurso').click(function (event) {
        if (event.target.type !== 'radio') {
            $(':radio', this).trigger('click');
        }
    });
});

function searchPart() {
    var searchInput = $("#searchInput").val().toUpperCase();
    $("#myTable tbody tr").each(function () {
        if ($(this).find("td:first-child").html().toUpperCase().indexOf(searchInput) !== -1) {
            $(this).show();
        } else {
            $(this).hide();
        }
    })
};

$(document).ready(function () {
    $('[data-toggle="tooltip"]').tooltip();
});