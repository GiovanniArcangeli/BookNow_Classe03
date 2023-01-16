function LoginPage() {
    var username = $("#username").val();
    var password = $("#password").val();

    if (username.length > 45) {
        alert("Il campo Username presenta caratteri non consentiti");
        $("#username").focus();
        return false;
    } else if ((username == "") || (username == "undefined")) {
        alert("Il campo Username è obbligatorio.");
        $("#username").focus();
        return false;
    }
    if (password.length > 45) {
        alert("Il campo Password presenta caratteri non consentiti\"");
        $("#password").focus();
        return false;
    } else if ((password == "") || (password == "undefined")) {
        alert("Il campo Password è obbligatorio.");
        $("#password").focus();
        return false;
    }else {
        $("#loginForm").submit();
    }
}