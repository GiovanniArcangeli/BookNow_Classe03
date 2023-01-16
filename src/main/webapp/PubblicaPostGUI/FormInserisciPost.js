function formInserisciPost() {
    var titolo = $("#titolo").val();
    var corpo = $("#corpo").val();
    var tags = $("#tags").val();

    if (titolo.length > 100) {
        alert("Il campo Titolo può contenere massimo 100 caratteri.");
        $("#titolo").focus();
        return false;
    } else if ((titolo == "") || (titolo == "undefined")) {
        alert("Il campo Titolo è obbligatorio.");
        $("#titolo").focus();
        return false;
    } else if (corpo.length > 150) {
        alert("Il campo Corpo può contenere massimo 150 caratteri.");
        $("#corpo").focus();
        return false;
    } else if ((corpo == "") || (corpo == "undefined")) {
        alert("Il campo Corpo è obbligatorio.");
        $("#corpo").focus();
        return false;
    } else if (tags.length > 100) {
        alert("Il campo Tags può contenere massimo 100 caratteri.");
        $("#titolo").focus();
        return false;
    } else if ((tags == "") || (tags == "undefined")) {
        alert("Il campo Tags è obbligatorio.");
        $("#titolo").focus();
        return false;
    }else {
        $("#postForm").submit();
    }
}