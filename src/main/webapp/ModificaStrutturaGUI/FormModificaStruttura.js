function formModificaStruttura() {
    var nomeStruttura = $("#nomeStruct").val();
    var indirizzo = $("#indirizzo").val();

    var rxAlphanumericPattern = /^[\w,!.?#\-\s]+$/;

    if (nomeStruttura.length > 100 || !nomeStruttura.match(rxAlphanumericPattern)) {
        alert("Il campo Nome presenta un formato errato.");
        $("#nomeStruct").focus();
        return false;
    } else if ((nomeStruttura == "") || (nomeStruttura == "undefined")) {
        alert("Il campo Nome è obbligatorio.");
        $("#nomeStruct").focus();
        return false;
    }
    if (indirizzo.length > 100 || !indirizzo.match(rxAlphanumericPattern)) {
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