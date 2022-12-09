var userId = 0;
var userName = "";
var role = "";
var birthdate = "";
var userEmail = "";

function getUserDetails() {
    userEmail = [[${#authentication.name}]];
    var request = "user/email?email=" + userEmail;
    request = request.setRequestHeader("X-Content-Type-Options", "nosniff");

    $.getJSON(request, function (userInfo) {
        userId = userInfo.userId;
        userName = userInfo.name;
        role = userInfo.userType.name.toLowerCase();
        birthdate = userInfo.birthdate;

            document.getElementById("userId").innerText = userId;
            document.getElementById("name").innerText = userName;
            document.getElementById("role").innerText = role;
            document.getElementById("birthdate").innerText = birthdate;
            document.getElementById("email").innerText = userEmail;
        }
    );
}


