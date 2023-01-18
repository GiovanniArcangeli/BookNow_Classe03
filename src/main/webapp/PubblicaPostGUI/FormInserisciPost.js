function formInserisciPost() {
    var titolo = $("#titolo").val();
    var corpo = $("#corpo").val();
    var tags = $("#tags").val();

    var rxAlphanumericPattern = /^[\w,!.?#\-\séèàòùì]+$/;

    if (titolo.length > 100 || !titolo.match(rxAlphanumericPattern)) {
        alert("Il campo Titolo presenta un formato errato.");
        $("#titolo").focus();
        return false;
    } else if ((titolo == "") || (titolo == "undefined")) {
        alert("Il campo Titolo è obbligatorio.");
        $("#titolo").focus();
        return false;
    } else if (corpo.length > 150 || !corpo.match(rxAlphanumericPattern)) {
        alert("Il campo Corpo presenta un formato errato.");
        $("#corpo").focus();
        return false;
    } else if ((corpo == "") || (corpo == "undefined")) {
        alert("Il campo Corpo è obbligatorio.");
        $("#corpo").focus();
        return false;
    } else if (tags.length > 100 || !tags.match(rxAlphanumericPattern)) {
        alert("Il campo Tags presenta un formato errato.");
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