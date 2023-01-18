function FormCercaStruttura() {
    var dataIn = $("#dataIn").val();
    var dataOut = $("#dataOut").val();
    var numOspiti = $("#numOspiti").val();

    var rxDatePattern = /^(\d{4})(\/)(\d{1,2})(\/)(\d{1,2})$/;

    if (isNaN(numOspiti) || numOspiti <= 0) {
        alert("Il campo Numero di Ospiti deve essere un numero positivo.");
        $("#numOspiti").focus();
        return false;
    } else if (numOspiti == "" || numOspiti == "undefined"){
        alert("Il campo Numero di Ospiti è obbligatorio.");
        $("#numOspiti").focus();
        return false;
    } else if (!dataIn.match(rxDatePattern)) {
        if(((meseIn == 1 || meseIn == 3 || meseIn == 5 || meseIn == 7 || meseIn == 8 || meseIn == 10 || meseIn == 12) && giornoIn > 31) ||
            ((meseIn == 4 || meseIn == 6 || meseIn == 9 || meseIn== 11) & giornoIn > 30) ||
            (annoIn%4 != 0 && meseIn == 2 && giornoIn > 28) || (annoIn%4 == 0 && meseIn == 2 && giornoIn > 29)) {
            alert("La data di check-in deve essere nel formato aaaa/mm/dd.");
            $("#dataIn").focus();
            return false;
        }
    } else if (!dataOut.match(rxDatePattern)) {
        if(((meseOut == 1 || meseOut == 3 || meseOut == 5 || meseOut == 7 || meseOut == 8 || meseOut == 10 || meseOut == 12) && giornoOut > 31) ||
            ((meseOut == 4 || meseOut == 6 || meseOut == 9 || meseOut== 11) & giornoOut > 30) ||
            (annoOut%4 != 0 && meseOut == 2 && giornoOut > 28) || (annoOut%4 == 0 && meseOut == 2 && giornoOut > 29)) {
            alert("La data di check-out deve essere nel formato aaaa/mm/dd.");
            $("#dataOut").focus();
            return false;
        }
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
        $("#prenotaStrutturaForm").submit();
    }
}