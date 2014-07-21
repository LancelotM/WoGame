<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript">
        function keepsession() {
            var href = document.location.href;
            var url = href.substring(0, href.indexOf("jsp")) + "jsp?RandStr=" + Math.random();
            document.location = url;
        }
        window.setTimeout("keepsession()", 600000);//request the server per 10 mins.
    </script>
</head>
<body>

</body>
</html>
