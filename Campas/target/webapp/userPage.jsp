<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/7/3
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserPage-CampusCommunity</title>
    <link rel="shortcut icon" href="/resource/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style type="text/css">@import "css/userPage.css";</style>
    <style type="text/css">@import "css/cropper.css";</style>
    <script src="javascript/userPageJS.js"></script>
    <script src="javascript/jquery.min.js"></script>
    <script src="javascript/jquery-2.1.4.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
    <script src="javascript/cropper.js"></script>
</head>

<body id="body">

<%--//最上方DIV块--%>
<div class="div1" id="div1">
    <%--//存放查找的內容的text--%>
    <input id="ctx" type="text" value="" onfocus="if (this.value=='')this.value=''"
           nblur="if (this.value=='')this.value=''">
    <div id="div1_img">
        <img id="srh" onclick="retrun_homepage()" value="查找"  src="/resource/search.png">
    </div>
    <%--//点击后调用prin（）來查找--%>
    <input id="question" type="button" value="我要提问" onclick="show_cover(question_passage)">
</div>

<%--//放icon 的块--%>
<div id="logined_show" align="right">
    <div id="div1_user_icon">
        <%--//加载icon--%>
        <img id="div1_small_icon_img" class="icon_bell" src="/resource/icon.jpg"  onmouseover="user_icon_bigger()" onmouseout="user_icon_smaller()">
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
        <label class="label" data-toggle="tooltip" title="点击修改你的头像" id="show_file">
            <img class="rounded" id="avatar" src="/resource/icon.jpg" alt="avatar">
            <input type="file" class="sr-only" id="input" name="image" accept="image/*">
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
                    <input id="change_user_msg" type="button" value="修改个人资料" onclick="show_cover(modify_user_msg)">
                </td>
            </tr>
        </table>
    </div>
</div>
</div>

<div id="cover">
</div>

<%--用來放修改的DIV块--%>
<div id="modify_user_msg">
    <div class="yellow_div" align="right">
        <%--//关闭的图片 调用close_cover（）来把登入的div块不看可视化 --%>
        <img onclick="close_cover(modify_user_msg)" src="/resource/close.png" class="close_div">
    </div>
    <div id="master_msg_div" class="msg_img">
        <table id="master_input_msg">
            <tr>
                <td colspan="2">
                    <label id="label_1">个人资料</label>
                </td>
            </tr>
            <tr>
                <td width="60" align="right">
                    <a>性別</a>
                </td>
                <td>
                    <%--选择性別--%>
                    <select class="master_msg" id="master_sex">
                        <option value="f">
                            ----
                        </option>
                        <option value="m">
                            男
                        </option>
                        <option value="f">
                            女
                        </option>
                    </select>
                </td>
            </tr>
            <tr>
                <td width="60" align="right">
                    <a>学校</a>
                </td>
                <td>
                    <%--放用戶的学校 滑鼠点击 和离开事件--%>
                    <input type="text" id="master_school" class="master_msg" value="----" onfocus="focusStyle(this.id)"
                           onblur="blurStyle(this.id)">
                </td>
            </tr>
            <tr>
                <td width="60" align="right">
                    <a>学院</a>
                </td>
                <td>
                    <%--放用戶的学院滑鼠点击 和离开事件--%>
                    <input type="text" id="master_collage" class="master_msg" value="----" onfocus="focusStyle(this.id)"
                           onblur="blurStyle(this.id)">
                </td>
            </tr>
            <tr>
                <td width="60" align="right">
                    <a>年级</a>
                </td>
                <td>
                    <select class="master_msg" id="master_year">
                        <option value="null">----</option>
                        <option value="2018">2018级</option>
                        <option value="2017">2017级</option>
                        <option value="2016">2016级</option>
                        <option value="2015">2015级</option>
                    </select>
                </td>
            </tr>
        </table>
        <input type="button" id="submit" onclick="modify()" value="保存资料">
    </div>

</div>

<div class="container">
    <div class="modal fade" id="modal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalLabel">裁剪图片</h5>
                </div>
                <div class="modal-body">
                    <div class="img-container">
                        <img id="image" src="/resource/icon.jpg">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="crop">裁剪</button>
                </div>
            </div>
        </div>
    </div>
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
<br>
<br>

<div style="min-width: 500px">
<div id="navIcons"><!--选项卡部分-->
    <div class="navIcon" id="que" onclick="questionOfUser()">
        <img class="navImg" id="queImg" src="/resource/que.png">
    </div>
    <div class="navSpace navIcon" id="ans" onclick="commendList()">
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
<script>
    function print() {
        var url = "/userPageServlet";

            $.ajax({
                type: "GET",
                url: url,

                success: function (data) {
                    //changeToArray
                        var obj = eval(data);
                        var arr = new Array();
                        for (var i = 0; i < obj.length; i++) {
                            arr[i] = new Array(4);
                            arr[i][0] = obj[i].username;
                            arr[i][1] = obj[i].school;
                            arr[i][2] = obj[i].profession;
                            arr[i][3] = obj[i].grade;
                        }
                        msg(arr);
                },
                error: function () {
                    alert("error");
                }
            });
    }

    function modify() {
        var url = "/modifyInfoServlet";
        var sex = $("#master_sex").val();
        var school= $("#master_school").val();
        var collage= $("#master_collage").val();
        var grade= $("#master_year").val();
        $.ajax({
            type: "POST",
            url: url,
            data:{
                "sex":sex,
                "school":school,
                "profession":collage,
                "grade":grade
            },
            success: function (data) {
                var dataStr = data + "";
                if(dataStr=="success"){
                    close_cover(modify_user_msg);
                    print();
                    location.reload();
                }else{
                    alert("数据填写错误,请重新修改");
                    close_cover(modify_user_msg);
                }
            },
            error: function () {
                alert("error");
            }
        });
    }
    function questionOfUser() {
        var url="/qouServlet";
            $.ajax({
                type: "GET",
                url: url,
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
    function commendList() {
        var url = "/comOfUsServlet";

            $.ajax({
                type: "GET",
                url: url,

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
</script>

</body>
</html>
