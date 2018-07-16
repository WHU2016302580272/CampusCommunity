<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/7/3
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Passage</title>
    <style type="text/css">@import "css/passage.css";</style>
    <script src="javascript/passageJS.js"></script>
    <script src="javascript/jquery-2.1.4.js"></script>

</head>
<body onload="print(),commentPrint(),logined()">

<input type="text" value="1" id="passage">


<%--//最上方DIV块--%>
<div class="div1" id="div1">
    <%--//存放查找的內容的text--%>
    <input id="ctx" type="te    xt" value="" onfocus="if (this.value=='')this.value=''"
           nblur="if (this.value=='')this.value=''">
    <div id="div1_img">
        <img id="srh" type="button" value="查找" onclick="retrun_homepage()" src="/resource/search.png">
    </div>
    <%--//点击后调用prin（）來查找--%>
    <input id="question" type="button" value="我要提问" onclick="question()">

    <%--//放icon 的块--%>
    <div id="logined_show" align="right">
        <div id="div1_user_icon" title="点击进入个人主页">
            <%--//加载icon--%>
            <img id="div1_small_icon_img" class="icon_bell" src="/resource/icon.jpg" onclick=location="userPage.jsp" onmouseover="user_icon_bigger()" onmouseout="user_icon_smaller()">
        </div>
    </div>
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

<div id="question_passage">
    <div class="yellow_div">
        <%--//关闭的图片 调用close_cover（）来把登入的div块不看可视化 --%>
        <img onclick="close_cover(question_passage)" src="/resource/close.png" class="close_div">
    </div>
    <%--//问题的标题--%>
    <input type="text" id="passage_title">
    <%--//问题的內容--%>
    <textarea id="passage_content"></textarea>
    <%--//发布问题--%>
    <input type="button" id="passage_submit" onclick="passageSubmit()" value="发布问题">
</div>


<div id="answer_passage">
    <div class="yellow_div">
        <%--//关闭的图片 调用close_cover（）来把登入的div块不看可视化 --%>
        <img onclick="close_cover(answer_passage)" src="/resource/close.png" class="close_div">
    </div>
    <%--//问题的标题--%>
    <p id="answer_title" align="center">题目</p>
    <%--//问题的內容--%>
    <textarea id="answer_content"></textarea>
    <%--//发布问题--%>
    <input type="button" id="answer_submit" value="发布回答" onclick="addAnswer()">
</div>


<div id="cover">
</div>

<%--放问题和別人回答的一个块--%>
<div id="main_question">
    <div id="main_passage">
        <p class="min_icon_msg">问题详情</p>
        <div class="div_backgroundcolor">
            <img src="/resource/icon_question_black.png" class="min_icon">
        </div>
        <%--放用戶什么时候时候发布--%>
        <p id="question_date">日期</p>
        <table width="100%">
            <tr>
                <%--放多有多少个回答--%>
                <td width="20%"><a id="how_many_answer" class="how_many">多小个回答</a></td>
                <%--放多有多少个收藏--%>
                <td width="20%"><a id="how_many_collection" class="how_many">多小个收藏</a></td>
                <td align="right">
                    <%--收藏的IMG 点了要把这个页加到用户的收藏目前collection()只有換图片--%>
                    <img src="/resource/icon_mark.png" id="collection" onclick="collection()" title="收藏">
                </td>
                <%--点了跳出一个回答的DIV块--%>
                <td width="155px" align="right"><input type="button" value="我要回答" id="answer"
                                                       onclick="answer()"></td>
            </tr>
        </table>
        <%--用戶发布的题目和內容--%>
        <table id="pas_table" class="div_backgroundcolor">
            <tr align="center">
                <td>
                    <a id="title" class="how_many"><b>题目</b></a>
                </td>
            </tr>
            <tr>
                <td>
                    <a id="content" class="how_many">內容</a>
                </td>
            </tr>
        </table>
    </div>
    <div id="has_answer">
        <p class="min_icon_msg">已有回答</p>
        <div>
            <img src="/resource/icon_answer.png" class="min_icon">
        </div>
    </div>


    <%--
    <div style="width:91%" >
        <table class="like_other_user" >
            <tr>
                <td width="70%" >
                    <p class="min_icon_msg">获得提问者赞同</p>
                    <div >
                        <img src="/resource/icon_adopted.png" class="min_icon">
                    </div>
                </td>
                <td rowspan="2"  valign="bottom"  style="background: #eae8eb; border-radius:4px;width:90px; " >

                    <div class="user_img">

                        <a class="other_user_name" href="userPage.jsp"><b>名字</b></a>
                        <br>
                        <a class="other_user_school" href="userPage.jsp"><b>大学</b></a>
                        <figure>
                            &lt;%&ndash;//放icon&ndash;%&gt;
                            <div class="other_circle"><img id="img_1" class="other_img"  onclick=location="userPage.jsp">
                            </div>
                        </figure>
                        &lt;%&ndash;//放名字&ndash;%&gt;
                    </div>
                </td>
            </tr>
            <tr>
                <td width="50%" >
                    <p class="min_icon_msg">被赞次数</p>
                    <div >
                        <img src="/resource/icon_agree_golden.png" class="min_icon">
                    </div>
                </td>
            </tr>
        </table>
        <div>
            <p id="other_user_answer">asd asd aa</p>
            <p id="answer_date" class="answer_date" align="right">asd asd</p>
        </div>
        <div class="like_div" >
            <img src="/resource/icon_agree.png" id="like" class="like">
            <img src="/resource/icon_disagree.png" id="dislike" class="dislike">
        </div>
    </div>--%>
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
<%--//放登录 注册 的块--%>
<div class="div5" id="div5">
    <p align="right" id="div5_text1"> 校园问答平台</p>
    <%--//点击后调用show_cover函数並把登录用的div块的id传进去--%>
    <p id="sign_in" onclick="show_cover(sign_in_page)">登录</p>
    <%--//点击后调用show_cover函数並把注册用的div块的id传进去--%>
    <p id="sign_up" onclick="show_cover(sign_up_page)">注册</p>
</div>
</div>

<div style="position: absolute;width: 100%;top:0px;left: 0px;min-width:1000px">
<%--//放提问者的个人信息 --%>
<div id="owner_msg_div">
    <div id="owner">
        <a><b>提问者﹕</b> </a>

        <%--放icon 和三个用戶的个人信息--%>
        <div id="user_img">
            <figure>
                <%--//放icon--%>
                <div id="owner_circle"><img id="img" onclick=location="userPage.jsp">
                </div>
            </figure>
            <%--//放名字--%>
            <a id="user_name" href="userPage.jsp"><b>名字</b></a>
            <div id="user_msg">
                <table width="300px">
                    <tr>
                        <tb>
                            <%--//放学校--%>
                            <a id="school" class="msg" href="userPage.jsp">大学</a>
                            <br>
                        </tb>
                        <tb>
                            <%--//放学院--%>
                            <a id="collage" class="msg" href="userPage.jsp">学院</a>
                            <br>
                        </tb>
                        <tb>
                            <%--//放年级--%>
                            <a id="year" class="msg" href="userPage.jsp">年级</a>
                            <br>
                        </tb>
                    </tr>
                </table>
            </div>
        </div>
        <%--<div id="owner_pre_passage">--%>
            <%--<a><b>最近问了﹕ </b></a>--%>
            <%--&lt;%&ndash;//放发帖者以前发过的title&ndash;%&gt;--%>
            <%--<div id="owner_pre_title">--%>
                <%--<table>--%>
                    <%--<tr>--%>
                        <%--<tb>--%>
                            <%--&lt;%&ndash;//放发帖者以前发过的title&ndash;%&gt;--%>
                            <%--<a id="owner_pre_passage_title1" class="pre_title"--%>
                               <%--href="userPage.jsp">owner_pre_passage_title1</a>--%>
                        <%--</tb>--%>
                        <%--<br><br>--%>
                        <%--<tb>--%>
                            <%--<a id="owner_pre_passage_title2" class="pre_title"--%>
                               <%--href="userPage.jsp">owner_pre_passage_title2</a>--%>
                        <%--</tb>--%>
                        <%--<br><br>--%>
                        <%--<tb>--%>
                            <%--<a id="owner_pre_passage_title3" class="pre_title"--%>
                               <%--href="userPage.jsp">owner_pre_passage_title3</a>--%>
                        <%--</tb>--%>
                    <%--</tr>--%>
                <%--</table>--%>
            <%--</div>--%>
        <%--</div>--%>

    </div>
</div>
</div>
<script><%String isLogined=(String)session.getAttribute("username");%>
var isLoginDD="<%=isLogined%>";
</script>

<script>
    function print() {
        var url = "/questionServlet";
        <%
        String pid=request.getParameter("pid");
        %>
        var pidd=<%=pid%>
        $.ajax({
            type: "GET",
            url: url,
            data: {"pid": pidd},
            success: function (data) {
                var dataStr = data + "";
                author(toArray(dataStr));
            },
            error: function () {
                alert("error");
            }
        });
    }

    function toArray(json) {
        var obj = eval(json);
        var arr = new Array();
        for (var i = 0; i < obj.length; i++) {
            arr[i] = new Array(7);
            arr[i][0] = obj[i].title;
            arr[i][1] = obj[i].passageContent;
            arr[i][2] = obj[i].time;
            arr[i][3] = obj[i].username;
            arr[i][4] = obj[i].school;
            arr[i][5] = obj[i].grade;
            arr[i][6] = obj[i].professtion;

        }
        return arr;
    }

    function commentPrint() {
        var url = "/commentServlet";
        var pidd=<%=pid%>
            $.ajax({
                type: "GET",
                url: url,
                data: {"pid": pidd},
                success: function (data) {
                    var dataStr = data + "";
                    comment(CommenttoArray(dataStr));
                }
            });
    }

    function CommenttoArray(json) {
        var obj = eval(json);
        var arr = new Array();
        for (var i = 0; i < obj.length; i++) {
            arr[i] = new Array(7);
            arr[i][0] = obj[i].content;
            arr[i][1] = obj[i].time;
            arr[i][2] = obj[i].thumbsupNum;
            arr[i][3] = obj[i].userName;
            arr[i][4] = obj[i].avator;
            arr[i][5] = obj[i].school;
            arr[i][6] = obj[i].uid;
        }
        return arr;
    }


    <%String islogined=(String)session.getAttribute("username");%>
    var isLogin="<%=islogined%>";
    function answer(){
        if(isLogin=="null"){
            alert("都不登陆，就互动？？");
        }else{
            show_cover(answer_passage);
        }
    }
    function addAnswer() {
        var url = "/addAnswerServlet";
        var answer = $("#answer_content").val();
        var pidd=<%=pid%>
        $.ajax({
                type: "POST",
                url: url,
                data: {
                    "pid": pidd,
                    "answer": answer
                },
                success: function (data) {
                    var dataStr = data + "";
                    alert(dataStr);
                    close_cover(answer_passage);
                }
            });
    }

    function question() {
        if(isLogin=="null"){
            alert("登陆才能提问，请先登陆");
        }else{
            show_cover(question_passage);
        }
    }
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
                location.reload(true);
            }
        });
        close_cover(sign_in_page);
        logined();
    }
    function logined() {

        if(isLogin!="null"){
            hide_login();
        }
    }
    function hide_login(){
        document.getElementById("sign_in").innerHTML=isLogin+"，欢迎你";
        document.getElementById("sign_in").onclick="";
        document.getElementById("sign_up").innerHTML="";
        document.getElementById("logined_show").style.opacity=1;
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

    function passageSubmit() {
        var url = "/passageServlet";
        var title = $("#passage_title").val();
        var content = $("#passage_content").val();

        $.ajax({
            type: "POST",
            url: url,
            data: {
                "title": title,
                "content": content
            },
            success: function (data) {
                var dataStr = data + "";
                if(dataStr!="标题和内容不能为空")
                //暂时转换到主界面
                    alert(dataStr);
                close_cover(question_passage);
            }
        });
    }

    function  retrun_homepage() {
        var txt = document.getElementById("ctx").value;
        location="homePage.jsp?word=" + txt ;
    }

</script>

</body>
</html>
