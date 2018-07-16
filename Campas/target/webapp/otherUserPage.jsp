<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 13/7/2018
  Time: 上午10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>otherUserPage</title>


    <style type="text/css">@import "css/otherUserpage.css";</style>

    <script src="javascript/otherUserPageJS.js"></script>
    <script src="javascript/jquery.min.js"></script>
    <script src="javascript/jquery-2.1.4.js"></script>

</head>
<body id="body">
<input type="text" value="1" id="passage">

<%--//最上方DIV块--%>
<div class="div1" id="div1">
    <%--//存放查找的內容的text--%>
    <input id="ctx" type="text" value="" onfocus="if (this.value=='')this.value=''"
           nblur="if (this.value=='')this.value=''">
    <div id="div1_img">
        <img id="srh" onclick="retrun_homepage()" value="查找"  src="/resource/search.png">
    </div>
    <%--//点击后调用prin（）來查找--%>
    <input id="question" type="button" value="我要提问" onclick="question()">
</div>

<%--//放icon 的块--%>
<div id="logined_show" align="right">
    <div id="div1_user_icon">
        <%--//加载icon--%>
        <img id="div1_small_icon_img" class="icon_bell" onclick=location="userPage.jsp"  src="/resource/icon.jpg" onmouseover="user_icon_bigger()" onmouseout="user_icon_smaller()">
    </div>
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

<div style="position: absolute;width: 100%;top:0px;left: 0px;min-width: 1000px">
<%--//这是放用户的个人信息的DIV--%>
<div id="user_detail">
    <div id="user_msg">
        <%--放图片--%>
        <label class="label" data-toggle="tooltip" id="show_file">
            <img class="rounded" id="avatar" src="/resource/icon.jpg" alt="avatar">
        </label>
        <table>
            <tr height="25px">
                <td>
                    <%--放名字--%>
                    <a id="user_name">名字</a>
                </td>
            </tr>
            <tr height="25px">
                <td>
                    <%--//放用戶的学校--%>
                    <a id="user_school" class="user_msg">学校</a>
                </td>
            </tr>
            <tr>
                <td>
                    <%--放用戶的学院--%>
                    <a id="user_collage" class="user_msg">学院</a>
                </td>
            </tr>
            <tr height="25px">
                <td>
                    <%--放用戶的年级--%>
                    <a id="user_year" class="user_msg">年级</a>
                </td>
            </tr>
            <tr>
                <td id="show_button">
                    <%--//点了会把修改个人信息的一个DIV块显示出來--%>
                </td>
            </tr>
        </table>
    </div>
</div>
</div>

<div id="cover">
</div>


<div id="question_passage">
    <div class="yellow_div" align="right">
        <%--//关闭的图片 调用close_cover（）来把登入的div块不看可视化 --%>
        <img onclick="close_cover(question_passage)" src="/resource/close.png" class="close_div">
    </div>
    <%--//问题的标题--%>
    <input type="text" id="passage_title">
    <%--//问题的內容--%>
    <textarea id="passage_content"></textarea>
    <%--//发布问题--%>
    <input type="button" id="passage_submit" value="发布问题">
</div>



<div id="main_div" style="min-width: 500px">
<div id="navIcons"><!--选项卡部分-->
    <div class="navIcon" id="que" onclick="queOfUserShow()">
        <img class="navImg" id="queImg" src="/resource/que.png">
    </div>
    <div class="navSpace navIcon" id="ans" onclick="answedList()">
        <img class="navImg" id="ansImg" src="/resource/ans.png">
    </div>
    <div class="navSpace navIcon" id="star" onclick="f()">
        <img class="navImg" id="starImg" src="/resource/star.png">
    </div>
    <!--暂时不实现的提醒页-->
    <!--<div class="navSpace navIcon" id="inf" onclick="select('inf')">
        <img class="navImg" id="infImg" src="/resource/inf.png"></img>
    </div>-->
</div>

<div id="navFrame"></div>

<div id="container_1">
    <div id="content"><!--内容部分-->
    </div>
    <div id="btBar"><!--翻页按钮-->
        <button class="btnSpace btn_1" id="btnL" onclick="modifyPage(false)">上一页</button>
        <button class="btn_1" id="btnR" onclick="modifyPage(true)">下一页</button>
    </div>
</div>

<div class="br">
</div>
</div>
<script><%String isLogined=(String)session.getAttribute("username");%>
var isLoginDD="<%=isLogined%>";</script>
<script>


    function print() {
        var url = "/otherUPServlet";
        <%
        String uid=request.getParameter("uid");
        %>
        var uidd=<%=uid%>
            $.ajax({
                type: "GET",
                url: url,
                data: {"uid": uidd},
                success: function (data) {
                    //changToArray
                    var obj = eval(data);
                    var arr = new Array();
                    for (var i = 0; i < obj.length; i++) {
                        arr[i] = new Array(4);
                        arr[i][0] = obj[i].username;
                        arr[i][1] = obj[i].school;
                        arr[i][2] = obj[i].profession;
                        arr[i][3] = obj[i].grade;
                    }

                    changeInfo(arr);
                },
                error: function () {
                    alert("error");
                }
            });
    }
    function queOfUserShow() {
        var url = "/queOfUserServlet";
        var uidd=<%=uid%>
            $.ajax({
                type: "GET",
                url: url,
                data: {"uid": uidd},
                success: function (data) {
                    //changeToArray
                    var obj = eval(data);
                    var arr = new Array();
                    for (var i = 0; i < obj.length; i++) {
                        arr[i] = new Array(5);
                        arr[i][0] = obj[i].title;
                        arr[i][1] = obj[i].pid;
                        arr[i][2] = obj[i].commentNum;
                        arr[i][3] = obj[i].enshrineNum;
                        arr[i][4] = obj[i].date;

                    }

                    select("que");
                    getData(arr);
                    modifyPage(true);
                },
                error: function () {
                    alert("error");
                }
            });
    }
    function answedList() {
        var url = "/answedOtherServlet";
        var uidd=<%=uid%>
            $.ajax({
                type: "GET",
                url: url,
                data: {"uid": uidd},
                success: function (data) {
                    //changeToArray
                    var obj = eval(data);
                    var arr = new Array();
                    for (var i = 0; i < obj.length; i++) {
                        arr[i] = new Array(5);
                        arr[i][0] = obj[i].content;
                        arr[i][1] = obj[i].date;
                        arr[i][2] = obj[i].upNumber;
                        arr[i][3] = obj[i].ptitle;
                        arr[i][4] = obj[i].pid;
                    }
                    select('ans');
                    getData(arr);
                    console.log(arr);
                    modifyPage(true);
                },
                error: function () {
                    alert("error");
                }
            });

    }
    //后期用于展示收藏界面
    function f(){
        var arr=[1];
        arr[0]="test";
        select("star");
        getData(arr);
        modifyPage(true);
    }

    function  retrun_homepage() {
        var txt = document.getElementById("ctx").value;
        location="homePage.jsp?word=" + txt ;
    }

    function logined() {

        if(isLoginDD!="null"){
            hide_login();

        }
    }
    function hide_login(){
        document.getElementById("logined_show").style.opacity=1;
    }

    function question() {
        <%String islogined=(String)session.getAttribute("username");%>
        var isLogin="<%=islogined%>";
        if(isLogin=="null"){
            alert("登陆才能提问，请先登陆");
        }else{
            show_cover(question_passage);
        }
    }
</script>
</body>
</html>

