function formModificaStruttura() {
    var nomeStruttura = $("#nomeStruct").val();
    var indirizzo = $("#indirizzo").val();

    if (nomeStruttura.length > 100) {
        alert("Il campo Nome può contenere massimo 100 caratteri.");
        $("#nomeStruct").focus();
        return false;
    } else if ((nomeStruttura == "") || (nomeStruttura == "undefined")) {
        alert("Il campo Nome è obbligatorio.");
        $("#nomeStruct").focus();
        return false;
    }
    if (indirizzo.length > 100) {
        alert("Il campo Indirizzo può contenere massimo 100 caratteri.");
        $("#indirizzo").focus();
        return false;
    } else if ((indirizzo == "") || (indirizzo == "undefined")) {
        alert("Il campo Indirizzo è obbligatorio.");
        $("#indirizzo").focus();
        return false;
    }else {
        $("#strutturaForm").submit();
    }
}