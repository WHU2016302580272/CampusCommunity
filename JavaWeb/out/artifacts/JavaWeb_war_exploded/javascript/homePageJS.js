window.onload = function () {
    print();
}

//将json转化为二维数组
function toarray(json) {
    var obj = eval(json);
    var arr = new Array();
    for (var i = 0; i < obj.length; i++) {
        arr[i] = new Array(5);
        arr[i][0] = obj[i].avatar;
        arr[i][1] = obj[i].school;
        arr[i][2] = obj[i].profession;
        arr[i][3] = obj[i].grade;
        arr[i][4] = obj[i].pcontent;
    }
    test(arr);
}

//点击下一页的处理
function passageup() {
    var url = "/pageControlServlet";
    var pageNo = $("#passage").val();
    var serchCon = $("#ctx").val();
    var pageC;
    $.ajax({
        type: "GET",
        url: url,
        data: {
            "pageNo": pageNo,
            "serchCon": serchCon
        },
        success: function (data) {
            pageC = parseInt(data / 10 + 1);
            var a = document.getElementById("passage").value;
            parseInt(a);
            if (a < pageC) {
                a++;
            }
            document.getElementById("passage").value = a;
            print();
        },
        error: function () {
            alert("error");
        }
    });

}

//点击上一页的处理
function passagedown() {
    var a = document.getElementById("passage").value;
    if (a > 1) {
        a--;
        document.getElementById("passage").value = a;
        print();
    }
}

//将二维数组中的内容展示在网页上
function test(arr) {
    var boby_html = document.body.innerHTML;
    var div1 = document.getElementsByTagName('div');
    var f = '';
    for (var i = 0; i < arr.length; i++) {
        if (arr[i][2] == "null") {
            break;
        }
        //定义div3_i_USER_IMG的Y坐标
        var a_y = 20 + i * 92;
        //定义div3_i_PASSAGE的Y坐标
        var d_y = 15 + i * 92;
        //把多于28个字的标题从第29个字开始全部变成...
        if (arr[i][4].length >= 28) {
            arr[i][4] = arr[i][4].substr(0, 28) + "...";
        }

        //div3_i_USER_IMG的css
        var a = 'top:' + a_y + 'px;width:22%;height:80px;background:#f3f2f3;border-radius:7.5px;position:absolute;left:0px;'
        //div3_i_USER_MSG的css
        var b = 'position:absolute;left:45%;top:15px;';
        //div3_i_RECTANGLE的css
        var c = 'width:10px;height:78px;position:absolute;left:164.5px;top:0px;background:#5b336f;z-index:1;';
        //div3_i_PASSAGE的css
        var d = 'width:70%;height:90px;position:absolute;background:#ffffff;left:170px;top:' + d_y + 'px;text-align:center;line-height:90px;'
        var url;
        //f是一条HTML的语言 动态加载最多10次 主要內容如下
        //<img id=img' + i + '  class=img src= onclick=location="userPage.jsp"> 放用户头象 id 是imgi 最后一个i是根据for来定义 如img1 img2 onclick转跳个人主页
        //<a id=school' + i + ' class=msg href=>大学</a>放用户信息 id 是schooli最后一个i 和 onclick都和上面类似
        //<a id=collage' + i + ' class=msg href=>学院</a>放用户信息 id 是collagei最后一个i 和 onclick都和上面类似
        //<a id=year' + i + ' class=msg href=>年级</a>放用户信息 id 是yeari最后一个i 和 onclick都和上面类似
        //<a id=pas_title' + i + ' class=pas href=passage.jsp >url</a>放标题 id 是pas_titlei 最后一个i是根据for来定义  onclick转跳提问主页
        f += '<div class=div3_' + i + ' id=div3_' + i + ' style=width:100%;height:90px;  ><div class=div3_i_user_img style=' + a + ' onclick=location="userPage.jsp" ><figure><div class=circle><img id=img' + i + '  class=img src= onclick=location="userPage.jsp"></div></figure><div  class=div3_' + i + '_user_msg style=' + b + '><a id=school' + i + ' class=msg href=>大学</a><br><a id=collage' + i + ' class=msg href=>学院</a><br><a id=year' + i + ' class=msg href=>年级</a></div><div id=div3_' + i + '_rectangle style=' + c + ' class=div3_rectangle_border></div></div ><div id=div3_' + i + '_passage class=div3_passage_border style=' + d + ' ><a id=pas_title' + i + ' class=pas href=passage.jsp >url</a></div></div>';
    }
    //把f加到html中的div3的这个块
    //添加一个div块放上一页下一页的button
    //上一页的button onclick后调用passagedown()函数
    //下一页的button onclick后调用passageup()函数
    document.getElementById("div3").innerHTML = f + '<div id=div7><table id=pre_next ><tr><td align=right><input id=next type=button value=上一页  onclick=passagedown()></td><td align=right><input id=pre type=button value=下一页 onclick=passageup() ></td></table></div>';
    //改內容
    for (var i = 0; i < arr.length; i++) {
        if (arr[i][2] == "null") {
            break;
        }
        //根据传入的数组参数动态加载数据进去
        document.getElementById("img" + i).src = arr[i][0];
        document.getElementById("school" + i).innerHTML = arr[i][1];
        document.getElementById("collage" + i).innerHTML = arr[i][2];
        document.getElementById("year" + i).innerHTML = arr[i][3];
        document.getElementById("pas_title" + i).innerHTML = arr[i][4];
    }
}

//显示一个div把后面的东西挡住不能用
// 其后再显示一个div块是相应的界面 如登入界面注册界面 根据传进来的参数来显示
function show_cover(change) {
    cover.style.opacity = 0.5;
    cover.style.zIndex = 2;
    var obj = document.getElementById(change);
    obj.style.zIndex = 3;
    obj.style.opacity = 1;
}

//隐藏一个div把后面的东西挡住不能用
//其后再隐藏一个div块是相应的界面 如登入界面注册界面 根据传进来的参数来隐藏
function close_cover(change) {
    cover.style.opacity = 0;
    cover.style.zIndex = -1;
    change.style.zIndex = -1;
    change.style.opacity = 0;
}

//改div6中的id=new的button的背景颜色紫色 文字颜色为白色 并把id=hot的button的背景颜色白色 文字颜色为紫色
function new_changeStyle() {
    var id_1 = document.getElementById("new");
    var id_2 = document.getElementById("hot");
    id_1.style.backgroundColor = "#9D85A9";
    id_1.style.color = "white";
    id_2.style.backgroundColor = "white";
    id_2.style.color = "black";
}

//改div6中的id=hot的button的背景颜色紫色 文字颜色为白色 并把id=new的button的背景颜色白色 文字颜色为紫色
function hot_changeStyle() {
    var id_1 = document.getElementById("new");
    var id_2 = document.getElementById("hot");
    id_2.style.backgroundColor = "#9D85A9";
    id_2.style.color = "white";
    id_1.style.backgroundColor = "white";
    id_1.style.color = "black";
}