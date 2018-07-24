window.onload = function () {
    print();
}

//将json转化为二维数组
function toarray(json) {
    var obj = eval(json);
    var arr = new Array();
    for (var i = 0; i < obj.length; i++) {
        arr[i] = new Array(7);
        if(obj[i].avatar == "null"){
            arr[i][0]= "/resource/icon.jpg";
        }
        else{
            arr[i][0] = "data:" + obj[i].avatar;
        }
        arr[i][1] = obj[i].school;
        arr[i][2] = obj[i].profession;
        arr[i][3] = obj[i].grade;
        arr[i][4] = obj[i].pcontent;
        arr[i][5] = obj[i].pid;
        arr[i][6] = obj[i].uid;
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
                page++;
            }
            document.getElementById("passage").value = page;
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
        page--;
        document.getElementById("passage").value = page;
        print();
    }
}

var page =1;
//将二维数组中的内容展示在网页上
function test(arr) {
    var boby_html = document.body.innerHTML;
    var div = document.getElementsByTagName('div');
    var f = '';
    var g = '';
    var h = '';
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
        var c = 'width:10px;height:78px;position:absolute;left:96%;top:0px;background:#5b336f;z-index:1;';
        //div3_i_PASSAGE的css
        var d = 'width:70%;height:90px;position:absolute;left:22.14%;top:' + d_y + 'px;text-align:left;line-height:90px;';
        var p = i%2;
        var j =  'width:70%;height:90px;position:absolute;left:22.14%;top:' + d_y + 'px;text-align:left;line-height:90px;background:url(/resource/bgimg_'+p+'.jpg);background-repeat:no-repeat;background-size:100%100%;-moz-background-size:100%100%;';
        var url;
        //f是一条HTML的语言 动态加载最多10次 主要內容如下
        //<img id=img' + i + '  class=img src= onclick=location="userPage.jsp"> 放用户头象 id 是imgi 最后一个i是根据for来定义 如img1 img2 onclick转跳个人主页
        //<a id=school' + i + ' class=msg href=>大学</a>放用户信息 id 是schooli最后一个i 和 onclick都和上面类似
        //<a id=collage' + i + ' class=msg href=>学院</a>放用户信息 id 是collagei最后一个i 和 onclick都和上面类似
        //<a id=year' + i + ' class=msg href=>年级</a>放用户信息 id 是yeari最后一个i 和 onclick都和上面类似
        //<a id=pas_title' + i + ' class=pas href=passage.jsp >url</a>放标题 id 是pas_titlei 最后一个i是根据for来定义  onclick转跳提问主页
        //跳转的步部onclick=location="" 以后要改
        g += '<a id=pid' + i + ' class="hide"  ></a>';
        h += '<a id=uid' + i + ' class="hide"  ></a>';
        f += '<div class=div3_' + i + ' id=div3_' + i + ' style=width:100%;height:90px;  >' +
            '<div class=div3_i_user_img style=' + a + '  >' +
            '<figure>' +
            '<div class=circle>' +
            '<img id=img' + i + '  class=img src="/resource/icon.jpg" onclick=location="otherUserPage.jsp?uid=' + arr[i][6] + '">' +
            '</div>' +
            '</figure>' +
            '<div  class=div3_' + i + '_user_msg style=' + b + '>' +
            '<a id=school' + i + ' class=msg  href=otherUserPage.jsp?uid=' + arr[i][6] + '>大学</a>' +
            '<br>' +
            '<a id=collage' + i + ' class=msg href=otherUserPage.jsp?uid=' + arr[i][6] + '>学院</a>' +
            '<br>' +
            '<a id=year' + i + ' class=msg href=otherUserPage.jsp?uid=' + arr[i][6] + '>年级</a>' +
            '</div>' +
            '<div id=div3_' + i + '_rectangle style=' + c + ' class=div3_rectangle_border>' +
            '</div>' +
            '</div >' +
            '<div id=div3_' + i + '_passage class=div3_passage_border style=' + d + ' >' +
            '<a id=pas_title' + i + ' class=pas   href=passage.jsp?pid=' + arr[i][5] + '>url</a>' +
            '</div>' +
            '<div class="backimg" style=' + j + ' class=test></div>'+
            '</div>';
    }
    //把f加到html中的div3的这个块
    //添加一个div块放上一页下一页的button
    //上一页的button onclick后调用passagedown()函数
    //下一页的button onclick后调用passageup()函数
    document.getElementById("div3").innerHTML = h + g + f + '<div id=div7>' +
        '<table id=pre_next ><tr><td align=right><input id=next type=button value=上一页  onclick=passagedown()></td><td><input type="text" value='+page+'  id="passage" readonly="readonly" align="center"></td><td align=right><input id=pre type=button value=下一页 onclick=passageup() ></td></table></div>';
    //改內容
    for (var i = 0; i < arr.length; i++) {
        if (arr[i][2] == "null") {
            break;
        }
        var z = "123";
        var y = "321";
        //根据传入的数组参数动态加载数据进去
        document.getElementById("img" + i).src = arr[i][0];
        document.getElementById("school" + i).innerHTML = arr[i][1];
        document.getElementById("collage" + i).innerHTML = arr[i][2];
        document.getElementById("year" + i).innerHTML = arr[i][3];
        document.getElementById("pas_title" + i).innerHTML = arr[i][4];
        //document.getElementById("pid"+i).innerHTML= arr[i][5];
        document.getElementById("uid" + i).innerHTML = arr[i][6];

    }
}

//显示一个div把后面的东西挡住不能用
// 其后再显示一个div块是相应的界面 如登入界面注册界面 根据传进来的参数来显示
function show_cover(change) {
    $(document).ready(function () {
        $(change).animate({top: '50%'});
    })
    document.getElementById("user_id").value=null;
    document.getElementById("user_pw").value=null;
    document.getElementById("add_user_id").value=null;
    document.getElementById("add_user_pw").value=null;
    document.getElementById("add_user_pw_ag").value=null;
    cover.style.opacity = 0.5;
    cover.style.zIndex = 2;
    change.style.zIndex = 3;
    change.style.opacity = 1;
}

//隐藏一个div把后面的东西挡住不能用
//其后再隐藏一个div块是相应的界面 如登入界面注册界面 根据传进来的参数来隐藏
function close_cover(change) {
    $(document).ready(function () {
        $(change).animate({top: '0%'});
    })

    cover.style.opacity = 0;
    cover.style.zIndex = -1;
    change.style.zIndex = -1;
    change.style.opacity = 0;
}

function title_onfouse(id) {
    if (document.getElementById(id).value == '请输入题目…') {
        document.getElementById(id).value = '';
        document.getElementById(id).style.color = "#000000";
    }
}

function title_onblur(id) {
    if (document.getElementById(id).value == '') {
        document.getElementById(id).style.color = "#888888";
        document.getElementById(id).value = '请输入题目…';
    }
}

function content_onfouse(id) {
    if (document.getElementById(id).innerHTML == '请输入内容…') {
        document.getElementById(id).innerHTML = '';
        document.getElementById(id).style.color = "#000000";
    }
}

function content_onblur(id) {
    if (document.getElementById(id).innerHTML == '') {
        document.getElementById(id).style.color = "#888888";
        document.getElementById(id).innerHTML = '请输入内容…';
    }
}

var hot_time = 0;
//改div6中的id=new的button的背景颜色紫色 文字颜色为白色 并把id=hot的button的背景颜色白色 文字颜色为紫色
function new_changeStyle() {
    var id_1 = document.getElementById("new");
    var id_2 = document.getElementById("hot");
    id_1.style.backgroundColor = "#9D85A9";
    id_1.style.color = "white";
    id_2.style.backgroundColor = "white";
    id_2.style.color = "black";
    if (hot_time==1){
        hot_time =0;
        print();
    }
}

//改div6中的id=hot的button的背景颜色紫色 文字颜色为白色 并把id=new的button的背景颜色白色 文字颜色为紫色
function hot_changeStyle() {
    var id_1 = document.getElementById("new");
    var id_2 = document.getElementById("hot");
    id_2.style.backgroundColor = "#9D85A9";
    id_2.style.color = "white";
    id_1.style.backgroundColor = "white";
    id_1.style.color = "black";
    if (hot_time==0){
        hot_time =1;
        print();
    }
}

function user_icon_bigger() {
    $("#div1_user_icon").css(
        {
            'width': '33px',
            'height': '33px',
            'transition': 'all 0.2s',
            '-webkit-transition': 'all 0.2s'
        });
    $("#div1_small_icon_img").css(
        {
            'width': '35px',
            'height': '35px',
            'transition': 'all 0.2s',
            '-webkit-transition': 'all 0.2s'
        });
}

function user_icon_smaller() {
    $("#div1_user_icon").css(
        {
            'width': '30px',
            'height': '30px',
            'transition': 'all 0.2s',
            '-webkit-transition': 'all 0.2s'
        });
    $("#div1_small_icon_img").css(
        {
            'width': '30px',
            'height': '30px',
            'transition': 'all 0.2s',
            '-webkit-transition': 'all 0.2s'
        });
}



