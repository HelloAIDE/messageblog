$(function(){
    var slide_list = $(".slide-list>ul>li");
    var slide_btn_list = $(".slide-left>.btn>li");
    var time=null;
    var num = 0;
    for(var i=0;i<slide_btn_list.length;i++)
    {
        var btn = slide_btn_list[i];
        btn.onclick = function () {
            clearInterval(time);
            for(var j=0;j<slide_btn_list.length;j++){
                slide_btn_list[j].className="";
            }
            this.className = "select";
            num = $(".slide-left>.btn>li").index(this)
            for(var j=0;j<slide_list.length;j++)
            {
                slide_list[j].className="";
            }
            if(slide_list[num]!=undefined)
            {
                slide_list[num].className="active";
            }else{
                slide_list[num-1].className="active";
            }
            console.log("num::::"+num);
            num++;
            autoPlay();
        }

    }
    function autoPlay(){
        time = setInterval(function(){
            console.log("slide_list.length:"+slide_list.length)
            console.log("num:"+num)
            if(num>slide_list.length-1){
                num=0;
            }
            for(var i=0;i<slide_list.length;i++)
            {
                slide_list[i].className="";
            }
            for(var i=0;i<slide_btn_list.length;i++)
            {
                slide_btn_list[i].className="";
            }

            slide_btn_list[num].className="select";
            slide_list[num].className="active";
            num++;
        },3000);
    };
    autoPlay();
});