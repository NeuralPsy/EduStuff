
function showOwnerProfile(email){

    $.getJSON("user/email?email="+email, function (userInfo) {
            document.getElementById("name").innerText = "Name: "+ userInfo.name;
            document.getElementById("role").innerText = "Role: "+ userInfo.userType.name.toLowerCase();
            document.getElementById("birthdate").innerText = "Birthdate: "+ userInfo.birthdate;
            // document.getElementById("email").innerText = "Email: "+ userInfo.email;
        }
    );
}

