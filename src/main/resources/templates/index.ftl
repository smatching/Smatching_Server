<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <title>Smatching Admin Page</title>
</head>
<body>
<br><br>
<div>
    <label>Password : </label>
    <input type="password" id="password" onkeyup="enterkey();" />&nbsp;&nbsp;
    <input type="button" value="로그인" onclick="login()" />
</div>
<script>
    function enterkey() {
        if (window.event.keyCode == 13) {
            login();
        }
    }
    function login() {
        window.location = location.href + "/notices?password=" + document.getElementById("password").value;
    }
</script>
</body>
</html>


