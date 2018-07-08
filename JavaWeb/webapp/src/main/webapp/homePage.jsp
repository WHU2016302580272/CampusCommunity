<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/7/3
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CampusCommunity</title>
    <style type="text/css">@import "css/homePage.css";</style>
    <script src='javascript/homePageJS.js'></script>
    <script src="javascript/jquery-2.1.4.js"></script>
</head>
<body id="body" onload="print()">
<%--//存放页数--%>
<input type="text" value="1"  id="passage">

<form name="form0" onsubmit="if (this.txt.value==''){this.txt.value='';alert('txt.value='+this.txt.value);}">
    <%--//最上方DIV块--%>
    <div class="div1" id="div1">
        <div id="div1_1">
            <%--//存放查找的內容的text--%>
            <input id="ctx" type="text" value="" onfocus="if (this.value=='')this.value=''" nblur="if (this.value=='')this.value=''">
            <%--//点击后调用prin（）來查找--%>
            <input id="srh" type="button" value="查找" onclick="print()">
        </div>
    </div>
</form>
<%--//放icon 的块--%>
<div>
    <div id="div1_user_icon">
        <%--//加载icon--%>
        <img id="div1_small_icon_img" class="icon_bell" src="/resource/icon.jpg">
    </div>
</div>

<%--//存放帖子的块 生成代码在js的test函数中--%>
<div id="div3">
</div>

<%--//最下方div块--%>
<div class="div4" id="div4">
    <div id="div4_1">
        <label id="feedback">|反馈与意见</label>
    </div>
    <div id="div4_2">
        <label id="about_us">|关于我们</label>
    </div>
    <div id="div4_3">
        <label id="edition">校园问答平台：第一版</label>
    </div>
</div>

<%--//放登录 注册 的块--%>
<div class="div5" id="div5">
    <p id="div5_text1"> 校园问答平台</p>
    <%--//点击后调用show_cover函数並把登录用的div块的id传进去--%>
    <p id="sign_in" onclick="show_cover('sign_in_page')">登录</p>
    <%--//点击后调用show_cover函数並把注册用的div块的id传进去--%>
    <p id="sign_up" onclick="show_cover('sign_up_page')">注册</p>
</div>

<div class="div6" id="div6">
    <p id="div6_text">筛选问题</p>
    <table width="100%">
        <tr>
            <%--//点击后调用new_changeStyle()改变部份CSS--%>
            <td width="47.5%" align="right"><input class="div6_sort" type="button" id="new" value="最新发布" onclick="new_changeStyle()"></input></td>
            <td width="5%"></td>
            <%--//点击后调用new_changeStyle()改变部份CSS--%>
            <td><input class="div6_sort" type="button" id="hot" value="最高热度" onclick="hot_changeStyle()"></input></td>
        </tr>
    </table>
    <br>
    <table width="100%">
        <tr>
            <td width="30%" align="right"><br> 学校：</td>
            <td>
                <br>
                <%--//设初值 鼠标点击后把初值变空白--%>
                <form onsubmit="if (this.txt.value=='武汉大学'){this.txt.value='';alert('txt.value='+this.txt.value);}">
                    <input class="search" type="text" id="div6_text1" value="武汉大学"
                           onfocus="if (this.value=='武汉大学')this.value=''" onblur="if (this.value=='')this.value='武汉大学'">
                </form>
            </td>
        </tr>
        <tr>
            <td width="30%" align="right"><br> 专业：</td>
            <td>
                <br>
                <%--//设初值 鼠标点击后把初值变空白--%>
                <form onsubmit="if (this.txt.value=='----'){this.txt.value='';alert('txt.value='+this.txt.value);}">
                    <input class="search" type="text" id="div6_text2" value="----"
                           onfocus="if (this.value=='----')this.value=''" onblur="if (this.value=='')this.value='----'">
                </form>
            </td>
        </tr>
        <tr>
            <td width="30%" align="right"><br> 年级：</td>
            <td><br><select class="search" id="select_year" align="right">
                <option value="volvo">----</option>
                <option value="volvo">2018级</option>
                <option value="saab">2017级</option>
                <option value="opel">2016级</option>
                <option value="audi">2015级</option>
            </select></td>
        </tr>
    </table>
    <br>
    <input id="find" type="button" value="查找"></input>
</div>

<%--//注册的div块 注册介面--%>
<div id="sign_up_page">
    <div class="yellow_div">
        <%--//关闭的图片 调用close_cover（）来把登入的div块不看可视化 --%>
        <img onclick="close_cover(sign_up_page)" src="/resource/close.png" class="close_div">
    </div>
    <table width="100%" style="margin-top:10%">
        <tr>
            <td width="30%" align="right"><br>用戶名﹕</td>
            <td width="5%"></td>
            <td><br><input class="div6_sort" type="text" id="add_user_id"></td>
        </tr>
        <tr>
            <td width="30%" align="right"><br>密码﹕</td>
            <td width="5%"></td>
            <td><br><input class="div6_sort" type="text" id="add_user_pw"></td>
        </tr>
        <td width="30%" align="right"><br>确认密码﹕</td>
        <td width="5%"></td>
        <td><br><input class="div6_sort" type="text" id="add_user_pw_ag"></td>
        </tr>
    </table>
    <div>
        <%--//点击后调用register()函数--%>
        <input type="button" id="register" value="注册" onclick="register()">
    </div>
</div>

<%--//注册的div块 登入介面--%>
<div id="sign_in_page">
    <div class="yellow_div">
        <%--//关闭的图片 调用close_cover（）来把注册的div块不看可视化 --%>
        <img onclick="close_cover(sign_in_page)" src="/resource/close.png" class="close_div"></img>
    </div>
    <table width="100%" style="margin-top:10%">
        <tr>
            <td width="25%" align="right"><br>用戶名﹕</td>
            <td width="5%"></td>
            <td><br><input class="div6_sort" type="text" id="user_id"></td>
        </tr>
        <tr>
            <td width="25%" align="right"><br>密码﹕</td>
            <td width="5%"></td>
            <td><br><input class="div6_sort" type="password" id="user_pw"></td>
        </tr>
    </table>
    <div>
        <%--//点击后调用register()函数--%>
        <input type="button" id="login" value="登入" onclick="login()"></input>
    </div>
</div>


<div id="cover">
</div>

<script type="application/javascript">
    function login() {
        var url = "/LoginServlet";
        var userName = $("#user_id").val();
        var password = $("#user_pw").val();
        $.ajax({
            type: "GET",
            url: url,
            data: {
                "userName": userName,
                "passwd": password
            },
            success: function (data) {
                var datastr = data + "";
                alert(datastr);
            }
        });
        close_cover(sign_in_page);

    }

    function register() {
        var url = "/RegisterServlet";
        var userName = $("#add_user_id").val();
        var password = $("#add_user_pw").val();
        var password_confirm = $("#add_user_pw_ag").val();
        if (password !== password_confirm) {
            alert("两次输入密码不一致！")
        } else {
            $.ajax({
                type: "POST",
                url: url,
                data: {
                    "userName": userName,
                    "passwd": password
                },
                success: function (data) {
                    var dataStr = data + "";
                    if (dataStr == "existed") {
                        alert("该用户名已注册");
                    } else if (dataStr == "success") {
                        alert("注册成功！");
                    } else {
                        alert("服务器遇到了未知错误");
                    }
                }
            });
            close_cover(sign_up_page);
        }
    }

    function print() {
        var url = "/HomePageServlet";
        var pageNo = $("#passage").val();
        var serchCon = $("#ctx").val();

        $.ajax({
            type: "GET",
            url: url,
            data: {
                "pageNo": pageNo,
                "serchCon": serchCon
            },
            success: function (data) {
                var dataStr = data + "";
                var passageList = toarray(dataStr);

            },
            error: function () {
                alert("error");
            }
        });
    }
</script>
</body>
</html>