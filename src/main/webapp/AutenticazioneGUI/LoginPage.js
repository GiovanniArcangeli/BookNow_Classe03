function LoginPage() {
    var username = $("#username").val();
    var password = $("#password").val();

    if (username.length > 45) {
        alert("Il campo Username può contenere massimo 45 caratteri.");
        $("#username").focus();
        return false;
    } else if ((username == "") || (username == "undefined")) {
        alert("Il campo Username è obbligatorio.");
        $("#username").focus();
        return false;
    }
    if (password.length > 45) {
        alert("Il campo Password può contenere massimo 45 caratteri.");
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