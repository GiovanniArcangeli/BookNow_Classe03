function FormModificaPrenotazione() {
    var dataIn = $("#dataIn").val();
    var dataOut = $("#dataOut").val();
    var numOspiti = $("#numOspiti").val();

    var rxDatePattern = /^(\d{4})(\/|-)(\d{1,2})(\/|-)(\d{1,2})$/;

    if (isNaN(numOspiti)) {
        alert("Il campo Numero di Ospiti deve essere numerico.");
        $("#numOspiti").focus();
        return false;
    } else if (numOspiti == "" || numOspiti == "undefined"){
        alert("Il campo Numero di Ospiti è obbligatorio.");
        $("#numOspiti").focus();
        return false;
    } else if (!dataIn.match(rxDatePattern) || dataIn.substring(5,7) > 12 || dataIn.substring(8,10) > 31) {
        alert("La data di check-in deve essere nel formato aaaa-mm-dd.");
        $("#dataIn").focus();
        return false;
    } else if (!dataOut.match(rxDatePattern) || dataOut.substring(5,7) > 12 || dataOut.substring(8,10) > 31) {
        alert("La data di check-ouy deve essere nel formato aaaa-mm-dd.");
        $("#dataOut").focus();
        return false;
    } else if (Date.parse(dataIn) - Date.parse(dataOut) > 0) {
        alert("La data di check-out deve essere successiva alla data di check-in.");
        $("#dataOut").focus();
        return false;
    } else if (Date.parse(dataIn) - Date.now() < 0) {
        alert("La data di check-in deve essere futura.");
        $("#dataIn").focus();
        return false;
    } else if (Date.parse(dataOut) - Date.now() < 0) {
        alert("La data di check-out deve essere futura.");
        $("#dataOut").focus();
        return false;
    }else {
        $("#prenotazioneForm").submit();
    }
}