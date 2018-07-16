//显示一个div把后面的东西挡住不能用
// 其后再显示一个div块是相应的界面 如登入界面注册界面 根据传进来的参数来显示
function show_cover(change) {
    cover.style.opacity = 0.5;
    cover.style.zIndex = 2;
    change.style.zIndex = 3;
    change.style.opacity = 1;
}

//隐藏一个div把后面的东西挡住不能用
//其后再隐藏一个div块是相应的界面 如登入界面注册界面 根据传进来的参数来隐藏
function close_cover(change) {
    cover.style.opacity = 0;
    cover.style.zIndex = -1;
    change.style.zIndex = -1;
    change.style.opacity = 0;
}

function collection() {
    document.getElementById("collection").src="/resource/icon_marked.png"
    document.getElementById("collection").title="已收藏"
}

function user_icon_bigger() {
    $("#div1_user_icon").css(
        {'width':'33px',
            'height':'33px',
            'transition':'all 0.2s',
            '-webkit-transition':'all 0.2s'
        });
    $("#div1_small_icon_img").css(
        {'width':'35px',
            'height':'35px',
            'transition':'all 0.2s',
            '-webkit-transition':'all 0.2s'
        });
}

function user_icon_smaller() {
    $("#div1_user_icon").css(
        {'width':'30px',
            'height':'30px',
            'transition':'all 0.2s',
            '-webkit-transition':'all 0.2s'
        });
    $("#div1_small_icon_img").css(
        {'width':'30px',
            'height':'30px',
            'transition':'all 0.2s',
            '-webkit-transition':'all 0.2s'
        });
}

function comment(arr) {
var i = 1;
    for (var i=arr.length;i>0;i--){
        //id=get_owner_like'+i+' 是显示作者对它给like 如果作者给了设opacity: 1;    DIV 标签
        //id=how_many_like'+i+'  是显示被like次数                                 P 标签
        //id=other_user_name'+i+' 是显示回答者的名字    还有href                          A 标签
        //id=other_user_school'+i+' 是显示回答者的大学   还有href                  A 标签
        //id=img'+i+'  是显示回答者的头象 onclick 跳转                                IMG 标签
        //id=other_user_answer'+i+' 是显示其他用戶的回答的內容                     P 标签
        //id=answer_date'+i+ '  是显示其他用戶的回答的日期                           P 标签
        //id=like' + i + '         是显示like的图 onclick要換图 like数加1          IMG 标签
        // id=dislike' + i + '      是显示dislike的图 onclick要換图 like数加1        IMG 标签
        document.getElementById("main_question").innerHTML += '<div class=comment_div style=width:91%><table cellspacing=0 class=like_other_user><tr><td width=70%><div id=get_owner_like'+i+'><p class=min_icon_msg id=get_owner_like>获得提问者赞同</p><div><img src=/resource/icon_adopted.png class=min_icon></div></div></td><td rowspan=2  valign=bottom class=table1_border ><div class=user_img> <a class=other_user_name href=otherUserPage.jsp?uid='+arr[i-1][6]+' id=other_user_name'+i+' href=otherUserPage.jsp?uid='+arr[i-1][6]+'><b>名字</b></a><br> <a class=other_user_school id=other_user_school'+i+' href=userPage.jsp?uid='+arr[i-1][6]+'><b>大学</b></a><figure><div class=other_circle><img id=img'+i+' class=other_img  onclick=location="otherUserPage.jsp?uid='+arr[i-1][6]+'"></div></figure></div> </td></tr><tr><td width=50% ><p class=min_icon_msg  id=how_many_like'+i+'>被赞次数</p><div ><img src=/resource/icon_agree_golden.png class=min_icon></div></td></tr></table><div class=comment><p id=other_user_answer'+i+'>內容</p><p id=answer_date'+i+ ' class=answer_date align=right>日期</p></div><div class=like_div ><img src=/resource/icon_agree.png id=like' + i + ' class=like><img src=/resource/icon_disagree.png id=dislike' + i + ' class=dislike></div></div>'
    }
    for (var i=arr.length;i>0;i--){
        document.getElementById("other_user_name"+i).innerHTML=arr[i-1][3];
        document.getElementById("other_user_school"+i).innerHTML=arr[i-1][5];
        document.getElementById("img"+i).innerHTML=arr[i-1][4];
        document.getElementById("other_user_answer"+i).innerHTML=arr[i-1][0];
        document.getElementById("answer_date"+i).innerHTML=arr[i-1][1];
        document.getElementById("how_many_like"+i).innerHTML="被赞 " + arr[i-1][2]+ " 次";
    }

}

function author(arr) {

    document.getElementById("question_date").innerText=arr[0][2];
    document.getElementById("title").innerHTML=arr[0][0];
    document.getElementById("answer_title").innerHTML=arr[0][0];
    document.getElementById("content").innerHTML=arr[0][1];
    document.getElementById("user_name").innerHTML=arr[0][3];
    document.getElementById("school").innerHTML=arr[0][4];
    document.getElementById("collage").innerHTML=arr[0][5];
    document.getElementById("year").innerHTML=arr[0][6];
}