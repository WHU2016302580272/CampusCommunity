//显示一个div把后面的东西挡住不能用
// 其后再显示一个div块是相应的界面 如登入界面注册界面 根据传进来的参数来显示
function show_cover(change) {
    cover.style.opacity = 0.5;
    cover.style.zIndex = 2;
    change.style.zIndex = 3;
    change.style.opacity = 1;
    $(document).ready(function(){
        $(change).animate({top:'50%' });
    })

}

//隐藏一个div把后面的东西挡住不能用
//其后再隐藏一个div块是相应的界面 如登入界面注册界面 根据传进来的参数来隐藏
function close_cover(change) {
    $(document).ready(function(){
        $(change).animate({top:'0%'});
    })
    cover.style.opacity = 0;
    cover.style.zIndex = -1;
    change.style.zIndex = -1;
    change.style.opacity = 0;
}

//变成黑色的字并把----变为空白
function focusStyle(id){
    if (document.getElementById(id).value=='----') {
        document.getElementById(id).value='';
        document.getElementById(id).style.color= "#000000";
    }
}

//离开的时候如果是空白就会把颜色变为灰色并加上----
function blurStyle(id) {
    if (document.getElementById(id).value==''){
        document.getElementById(id).value='----'
        document.getElementById(id).style.color="#cccccc";
    }
}

function user_icon_bigger() {
    $("#div1_user_icon").css(
        {'width':'36px',
            'height':'36px',
            'transition':'all 0.2s',
            '-webkit-transition':'all 0.2s'
        });
    $("#div1_small_icon_img").css(
        {'width':'38px',
            'height':'38px',
            'transition':'all 0.2s',
            '-webkit-transition':'all 0.2s'
        });
}

function user_icon_smaller() {
    $("#div1_user_icon").css(
        {'width':'33px',
            'height':'33px',
            'transition':'all 0.2s',
            '-webkit-transition':'all 0.2s'
        });
    $("#div1_small_icon_img").css(
        {'width':'33px',
            'height':'33px',
            'transition':'all 0.2s',
            '-webkit-transition':'all 0.2s'
        });
}

window.addEventListener('DOMContentLoaded', function () {
    var avatar = document.getElementById('avatar');
    var image = document.getElementById('image');
    var input = document.getElementById('input');
    var $modal = $('#modal');
    var user_image = document.getElementById('div1_small_icon_img');
    var cropper;
    //增加提示框
    $('[data-toggle="tooltip"]').tooltip();
    //点击图片，弹出modal框，并将选择的图片放到modal中
    input.addEventListener('change', function (event) {
        var files = event.target.files;
        var done = function (url) {
            input.value = '';
            image.src = url;
            $modal.modal('show');
        };

        var reader;
        var file;
        var url;

        if (files && files.length > 0) {
            file = files[0];

            if (URL) {
                done(URL.createObjectURL(file));
            } else if (FileReader) {
                reader = new FileReader();
                reader.onload = function (event) {
                    done(reader.result);
                };
                reader.readAsDataURL(file);
            }
        }
    });

    $modal.on('shown.bs.modal', function () {
        cropper = new Cropper(image, {
            aspectRatio: 1,
            viewMode: 2,
        });
    }).on('hidden.bs.modal', function () {
        cropper.destroy();
        cropper = null;
    });





    document.getElementById('crop').addEventListener('click', function () {
        var initialAvatarURL;
        var canvas;

        $modal.modal('hide');

        if (cropper) {
            canvas = cropper.getCroppedCanvas({
                width: 160,
                height: 160,
            });

            initialAvatarURL = avatar.src;
            avatar.src = canvas.toDataURL();
            user_image.src = canvas.toDataURL();
            /*      canvas.toBlob(function (blob) {
                    var formData = new FormData();

                    formData.append('avatar', blob);

                    $.ajax('https://jsonplaceholder.typicode.com/posts', {
                      method: 'POST',
                      data: formData,
                      processData: false,
                      contentType: false,

                      xhr: function () {
                        var xhr = new XMLHttpRequest();

                        xhr.upload.onprogress = function (e) {
                          var percent = '0';
                          var percentage = '0%';

                          if (e.lengthComputable) {
                            percent = Math.round((e.loaded / e.total) * 100);
                            percentage = percent + '%';
                            $progressBar.width(percentage).attr('aria-valuenow', percent).text(percentage);
                          }
                        };

                        return xhr;
                      },
                    });
                  });*/
        }
    });
});


var page="ans";//当前选项
var pageNum=0;//当前页
// 当前选项的数据数组
var records=[];
var reNum=0;//数组长度

window.onload = function () {
    print();
    questionOfUser();

};

//改变选项卡
function select(p){

    if(p===page){}//点击的是当前页面，什么也不做
    else{
        //改变之前选中按钮的样式
        var pageImg=page+"Img";
        var imgScr="/resource/"+page+".png";

        var pageO=document.getElementById(pageImg);
        pageO.src=imgScr;
        var pImgO=document.getElementById(page);
        pImgO.style.backgroundColor="rgba(84,0,96,0)";
        pImgO.style.border="solid rgba(221,202,220,1) 1px";

        //改变当前选中按钮的样式
        page=p;
        pageImg=page+"Img";
        imgScr="/resource/"+page+"S.png";

        pageO=document.getElementById(pageImg);
        pageO.src=imgScr;
        pImgO=document.getElementById(page);
        pImgO.style.backgroundColor="rgba(84,0,96,1)";
        pImgO.style.border="solid rgba(84,0,96,1) 1px";

        pageNum=0;//重置页数
        //getData();//获取本选项卡所有数据
        //modifyPage(true);//显示第一页*/

    }
}

//改变页数
function modifyPage(dir){//点击下一页传true，上一页传false

    //alert(records[0]);
    var limit=getLimit();
    var remain=reNum;//剩余页记录条数
    var lPage=false;//是否存在上一页
    var rPage=false;//是否存在下一页
    var btnLeft=document.getElementById("btnL");
    var btnRight=document.getElementById("btnR");

    if(dir){
        //更新元数据
        if(pageNum!==0) {lPage=true;}//pageNum=2 l=true r=true
        remain=reNum-pageNum*limit;
        if(remain>limit) {rPage=true;}
        pageNum++;

    }else{
        //更新元数据
        if(pageNum!==0) rPage=true;
        if(pageNum!==2) lPage=true;
        pageNum--;
    }

    //在此更新本页数据——————————————————————————这里改成一个switch，然后执行不同的更新函数，这里的代码至少对收藏有效，啊分开多好啊
    switch(page){
        case 'star':updateStar();break;
        case 'que':updateQue();break;
        case 'ans':updateAns();break;
    }

    //处理按钮显示
    btnLeft.style.visibility="hidden";
    btnRight.style.visibility="hidden";
    if(lPage){//若存在上一页，显示上一页按钮
        btnLeft.style.marginLeft ="69%";
        btnLeft.style.visibility="visible";
        if(rPage){//若存在下一页,显示下一页按钮
            btnLeft.style.marginLeft ="69%";
            btnRight.style.visibility="visible";
        }else{//若是最后一页,上一页按钮取代下一页按钮的位置
            btnRight.style.visibility="hidden";
            btnLeft.style.marginLeft ="84.5%";
            btnLeft.style.visibility="visible";
        }
    }else{
        btnLeft.style.marginLeft ="69%";
        btnLeft.style.visibility="hidden";
        if(rPage) {//若存在下一页,显示下一页按钮
            btnRight.style.visibility = "visible";
        }
    }
}

function getLimit(){//切换不同页面可容纳的记录数目
    switch(page){
        case 'que':return 14;
        case 'ans': return 4;
        case 'star':return 16;
    }
}

function getData(arr){//从数据arr[i][4] = obj[i].date;库得到数据
    records.length=arr.length;
    reNum=records.length;

    switch(page){
        case 'star':
            for (var i = 0; i < arr.length; i++) {
                records[i] = arr[0];
            }
            break;
            case 'que':
            for (var i = 0; i < arr.length; i++) {
                records[i] = new Array(5);
                records[i][0] = arr[i][2];
                records[i][1] = arr[i][3];
                records[i][2] = arr[i][4];
                records[i][3] = arr[i][0];
                records[i][4]=arr[i][1];
            }
            break;
        case 'ans':

            for (var i = 0; i < arr.length; i++) {
                records[i] = new Array(5);
                records[i][0] = arr[i][2];
                records[i][1] = arr[i][1];
                records[i][2] = arr[i][3];
                records[i][3] = arr[i][0];
                records[i][4]=arr[i][4];
            }
            break;
    }
}

function updateQue(){
    var limit = getLimit();
    var firstRe = (pageNum - 1) * limit;
    var conO = document.getElementById("content");

    conO.innerHTML="";
    for(var i=firstRe;i<Math.min(reNum, limit*pageNum);i++){
        conO.innerHTML+=
            "<div class='queIcon'><!--第一个icon--><p class='p'><img class='queImg' src='/resource/ansF.png'><span class='queFont'>"
            +records[i][0]+//回答数
            "</span></p></div><div class='queIcon queSpace'><!--第二个icon--><p class='p'><img class='queImg' src='/resource/starF.png'><span class='queFont'>"
            +records[i][1]+//收藏数
            "</span></p></div><div class='queTxtArea'><!--日期与标题--><span class='queTxt'>"
            +records[i][2]+//日期
            "</span><a class='queTitle' href=passage.jsp?pid="+records[i][4]+">"
            +records[i][3]+//标题
            "</a></div><br><br>";
    }
}

function updateAns(){
    var limit = getLimit();
    var firstRe = (pageNum - 1) * limit;
    var conO = document.getElementById("content");

    conO.innerHTML ="";
    for(var i=firstRe;i<Math.min(reNum, limit*pageNum);i++) {
        conO.innerHTML +=
            "<div class='ansIcon'><!--icon--><p class='p'><img class='ansImg' src='/resource/agree.png'><span class='queFont'>"
            +records[i][0]+
            "</span></p></div><div class='ansTxtArea'><!--日期与标题--><span class='ansBlue'>"
            +records[i][1]+
            "</span><a class='ansTitle' href=passage.jsp?pid="+records[i][4]+">"
            +records[i][2]+
            "</a></div><div class='ansTxt'>"
            +records[i][3]+
            "</div>";
    }
}

function updateStar() {
    var conO = document.getElementById("content");
    var limit = getLimit();
    var firstRe = (pageNum - 1) * limit;

    conO.innerHTML = "";
    for (var j = firstRe; j < Math.min(reNum, limit*pageNum); j++) {
        /*if(typeof(records[j])!=="undefined") {//若本条数据不为空*/
        conO.innerHTML += "<a style='text-decoration:none' href='/passage.jsp' target='_blank'>"+
            "<p class='starTxt'>" + records[j] + "</p></a>";//插入数据
    }

}


function msg(arr) {
    document.getElementById("user_name").innerHTML=arr[0][0];
    document.getElementById("user_school").innerHTML=arr[0][1];
    document.getElementById("user_collage").innerHTML=arr[0][2];
    document.getElementById("user_year").innerHTML=arr[0][3];
}

function print_passage(arr) {


}