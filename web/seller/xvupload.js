/**
* input 图片上传 
* 功能点：分段上传
*/
(function (window) {
	//默认配置
	var options = {
		ids:null,
		start:function(){},//开始上传
		process:function(){},//每一次上传成功后实时回调，返回当前进度
		success:function(){},//上传成功
		error:function(){},//上传失败
		type:1,//上传类型  1普通上传  2 私密上传
		uploadUrl:"http://101.201.76.184:8999",
		blockSize:10*1024,
		base64:''
	},
	interfaces = {
		vldUrl:"/authUploadFile",
		url:"/binaryStreamUpload"
	};
	
	var startFunc = [],processFunc = [],successFunc = [],errorFunc = []
	
	//API
	var api = {
		init: function(opts){
			if(opts){
				for(var key in opts) {
					options[key] = opts[key];
				}
			}
			if(options.ids && options.ids.length > 0){
				for(var i=0;i<options.ids.length;i++){
					var id = options.ids[i];
					startFunc[id] = options.start;
					processFunc[id] = options.process;
					successFunc[id] = options.success;
					errorFunc[id] = options.error;
					dobind(id);
				}	
				
			}
			
			return this;
		}
	}
	
	var dobind = function(id){
		var ele = document.getElementById(id);
		ele.onchange = function(){
			if(ele.files){
				var file = ele.files[0];
				if(file.type.indexOf('image/')>-1){
					wang(file);
					validate(id,file);
				}else if(file && file.type.indexOf('image/')==-1){
					if(ele.getAttribute("upld")){
						errorFunc[id]({"status":false,"id":id,"message":"正在上传中，客官莫急！"});
					}else{
						validate(id,file);
					}
				}
			}else{
				errorFunc[id]({"status":false,"id":id,"message":"浏览器版本过低，请升级"});
			}
			
		}
	}
	
	var wang = function(fileObj){
		// var reader = new FileReader();  
	 //      reader.readAsDataURL(file);  
	 //      reader.onload = function (e) {  
	 //        var img = new Image,  
	 //        width = 500,    //图片resize宽度  
	 //        quality = 1.0,  //图像质量  
	 //        canvas = document.createElement("canvas"),  
	 //        drawer = canvas.getContext("2d");  
	 //        img.src = this.result,
	 //        width1 = img.width,
	 //        height1 = img.height;

	 //        canvas.width = width;  
	 //        canvas.height = width * (img.height / img.width);  
	 //        drawer.drawImage(img, 0 , 0, canvas.width, canvas.height);  
	 //        img.src = canvas.toDataURL('image/png',quality);  
	 //        var image_base64 = img.src.replace('data:image/png;base64,',''); 
	 //        options.base64 = image_base64 
	 //      }  



	 	var file = fileObj,
            cvs = document.createElement("canvas"),
            ctx = cvs.getContext("2d");
        if(file){
            var url = window.URL.createObjectURL(file);//PS:不兼容IE
            var img = new Image();
            img.src = url;
            img.onload = function(){
                ctx.clearRect(0,0,cvs.width,cvs.height);
                cvs.width = 500;
                cvs.height = 500 * (img.height / img.width);
                ctx.drawImage(img,0,0,cvs.width,cvs.height);
                var base64 = cvs.toDataURL("image/png",1.0);
                var image_base64 = base64.replace('data:image/png;base64,',''); 
                options.base64 = image_base64;
            }
        }
	}





	// function detectVerticalSquash(img) {
	// 	var iw = img.naturalWidth, ih = img.naturalHeight;
	// 	var canvas = document.createElement('canvas');
	// 	canvas.width = 1;
	// 	canvas.height = ih;
	// 	var ctx = canvas.getContext('2d');
	// 	ctx.drawImage(img, 0, 0);
	// 	var data = ctx.getImageData(0, 0, 1, ih).data;
	// 	// search image edge pixel position in case it is squashed vertically.
	// 	var sy = 0;
	// 	var ey = ih;
	// 	var py = ih;
	// 	while (py > sy) {
	// 		var alpha = data[(py - 1) * 4 + 3];
	// 		if (alpha === 0) {
	// 			ey = py;
	// 		} else {
	// 			sy = py;
	// 		}
	// 		py = (ey + sy) >> 1;
	// 	}
	// 	var ratio = (py / ih);
	// 	return (ratio===0)?1:ratio;
	// }
	// function drawImageIOSFix(ctx, img, sx, sy, sw, sh, dx, dy, dw, dh) {
	// 	var vertSquashRatio = detectVerticalSquash(img);
	// 	ctx.drawImage(img, sx * vertSquashRatio, sy * vertSquashRatio,
	// 			sw * vertSquashRatio, sh * vertSquashRatio,
	// 			dx, dy, dw, dh);
	// }









	//验证 上传
	var validate = function(id,file){
		var total = 1;
		try{
			total =  1;//Math.ceil(file.size/options.blockSize);
		}catch(e){}
		startFunc[id]();
		setTimeout(function(){
			ajax({
				url: options.uploadUrl + interfaces.vldUrl,
				dataType: 'json',
				type: 'POST',
				data: {
					"total":total,
					"name":file.name,
					"size":file.size,
					"type":file.type
				},
				success: function (data) {
					if (data.success) {
						if(options.base64){
							upload1(id,options.base64,data.uid,total);
						}else{
							upload(id,file,data.uid,total);
						}
						
					}else{
						errorFunc[id]({"status":false,"id":id,"message":"验证失败"});
						clearInput(id);
					}
					
				},
				error: function (data) {
					errorFunc[id]({"status":false,"id":id,"message":"验证失败"});
					clearInput(id);
				}
			});
		},1000)
		
		
	}
	
	//上传
	var upload = function(id,file,uid,total){
		var ele = document.getElementById(id);
		ele.setAttribute("upld","1");
		var breakFlag = false;
		var per = options.blockSize;
		for(var i=0;i<total;i++){
			var perFile;
			if(i < total-1){
				perFile = file.slice(i*per,(i+1)*per);
			}else{
				perFile = file.slice(i*per,file.size);
			}
			var fmData = new FormData();
			fmData.append("type", 1);
			fmData.append("uid", uid);
			fmData.append("num", 1);
			fmData.append("imgStr", file);
			ajax({
				url: options.uploadUrl + interfaces.url,
				dataType: 'json',
				contentType: false,
				type: 'POST',
				async:false,
				data:fmData,
				success: function (data) {
					if (data.success){
						processFunc[id]({"id":id,"percent":(i+1)/total});
						if(i+1 == total){
							var result = {"id":id,"url":data.idata};
							successFunc[id](result);
							clearInput(id);
						}
						
					}else{
						breakFlag = true;
						errorFunc[id]({"status":false,"id":id,"message":"上传失败"});
						clearInput(id);
					}
					
				},
				error: function (o, xhr, property) {
					breakFlag = true;
					errorFunc[id]({"status":false,"id":id,"message":"上传失败"});
					clearInput(id);
				}
			});
			if(breakFlag){
				break;
			}	
			
		}
		
	}

	var upload1 = function(id,file,uid,total){
		var ele = document.getElementById(id);
		ele.setAttribute("upld","1");
		var breakFlag = false;
		var per = options.blockSize;
		for(var i=0;i<total;i++){
			var perFile;
			if(i < total-1){
				perFile = file.slice(i*per,(i+1)*per);
			}else{
				perFile = file.slice(i*per,file.size);
			}
			ajax({
				url: options.uploadUrl + '/upload',
				dataType: 'json',
				type: 'POST',
				async:false,
				data:{
					'uid':uid,
					'type':1,
					'num':1,
					'imgStr':encodeURIComponent(file)
				},
				success: function (data) {
					if (data.success){
						processFunc[id]({"id":id,"percent":(i+1)/total});
						if(i+1 == total){
							var result = {"id":id,"url":data.idata};
							successFunc[id](result);
							clearInput(id);
						}
						
					}else{
						breakFlag = true;
						errorFunc[id]({"status":false,"id":id,"message":"上传失败"});
						clearInput(id);
					}
					
				},
				error: function (o, xhr, property) {
					breakFlag = true;
					errorFunc[id]({"status":false,"id":id,"message":"上传失败"});
					clearInput(id);
				}
			});
			if(breakFlag){
				break;
			}	
			
		}
		
	}

	//重置input属性
	var clearInput = function(id){
		var ele = document.getElementById(id);
		if(ele.getAttribute("upld")){
			ele.removeAttribute("upld");
		}
		ele.value = "";
	}
	
	//封装ajax
	function ajax(obj) {
		if (window.XMLHttpRequest) {//IE7+、Firefox、Opera、Chrome 和Safari
			 var xhr = new XMLHttpRequest();
		} else if (window.ActiveXObject) {   //IE6 及以下
			var xhr = new ActiveXObject("Microsoft.XMLHTTP");
		} else {
			alert('浏览器版本过低！');
			return;
		}
		//obj.url = obj.url + '?rand=' + Math.random();
		if (obj.type === 'get' || obj.type === 'GET') {
			obj.url += obj.url.indexOf('?') == -1 ? '?' + obj.data : '&' + obj.data; 
		}
		if (obj.async !== false) {//异步
			xhr.onreadystatechange = function () {
				if (xhr.readyState == 4) {
					callback();
				}
			};
		}
		xhr.open(obj.type, obj.url, obj.async);
		if (obj.type === 'post' || obj.type === 'POST') {
			if(obj.contentType !== false){
				obj.data = convertData(obj.data);
				xhr.setRequestHeader('Content-Type', "application/x-www-form-urlencoded");
			}
			
			try{
				xhr.send(obj.data);
			}catch(e){
				obj.error({"success":false,"message":"接口faile"});
			}
		} else {
			try{
				xhr.send(null);		//get方式则填null
			}catch(e){
				obj.error({"success":false,"message":"接口failed"});
			}
		}
		if (obj.async === false) {//同步
			callback();
		}
		function callback() {
			if (xhr.status == 200) {
				var resultData = xhr.responseText;
				if(obj.dataType == "json" && typeof resultData !== "object"){
					resultData = JSON.parse(resultData);
				}
				obj.success(resultData);
			} else {
				obj.error({"success":false,"message":"接口failed"});
			}	
		}
	}

	function convertData(data){ 
	  if( typeof data === 'object' ){ 
		var convertResult = "" ;  
		for(var c in data){  
		  convertResult+= c + "=" + data[c] + "&";  
		}  
		convertResult=convertResult.substring(0,convertResult.length-1) 
		return convertResult; 
	  }else{ 
		return data; 
	  } 
	}
	
	this.xvUpload = api;

})(window);