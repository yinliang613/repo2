window.onload = function () {
    document.getElementById("form").onsubmit = function () {
        return checkUserName()&&checkPassWord()&&checkEmail();
    }
    document.getElementById("username").onblur = checkUserName;
    document.getElementById("password").onblur = checkPassWord;
    document.getElementById("email").onblur = checkEmail;
}
function checkUserName() {
    var username = document.getElementById("username").value;
    var check = /^\w{6,12}$/;
    var flag = check.test(username);

    if(flag){
        document.getElementById("s_username").innerHTML = "<img src='img/gou.png' width=30px height=20px>";
    }else {
        document.getElementById("s_username").innerHTML = "格式错误，请输入6-12个字母和数字组合";
    }
    return flag;
}
function checkPassWord() {
    var password = document.getElementById("password").value;
    var check = /^\w{6,12}$/;
    var flag = check.test(password);

    if(flag){
        document.getElementById("s_password").innerHTML = "<img src='img/gou.png' width=30px height=20px>";
    }else {
        document.getElementById("s_password").innerHTML = "格式错误，请输入6-12个字母和数字组合";
    }
    return flag;
}
function checkEmail() {

    var email = document.getElementById("email").value;
    var check = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
    var flag = check.test(email);

    if(flag){
        document.getElementById("s_email").innerHTML = "<img src='img/gou.png' width=30px height=20px>";
    }else {
        document.getElementById("s_email").innerHTML = "格式错误，须含有@";
    }
    return flag;
}