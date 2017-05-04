'use strict'

export default(Vue) => {
    Vue.directive('touch', {
        bind: function (el, binding, vnode) {
            var touchType = binding.arg; //传入的模式 press swipeRight swipeLeft swipeTop swipeDowm Tap
            var timeOutEvent = 0;
            var direction = '';
            //滑动处理
            var startX, startY;

            //返回角度
            function GetSlideAngle(dx, dy) {
                return Math.atan2(dy, dx) * 180 / Math.PI;
            }

            //根据起点和终点返回方向 1：向上，2：向下，3：向左，4：向右,0：未滑动
            function GetSlideDirection(startX, startY, endX, endY) {
                var dy = startY - endY;
                var dx = endX - startX;
                var result = 0;

                //如果滑动距离太短
                if (Math.abs(dx) < 2 && Math.abs(dy) < 2) {
                    return result;
                }

                var angle = GetSlideAngle(dx, dy);
                var angle2 = Math.abs(dx);
                var angle3 = Math.abs(dy);
                if (angle >= -45 && angle < 45) {
                    result = 'swiperight';
                } else if (angle >= 45 && angle < 150 && angle3 > 30) {
                    result = 'swipeup';
                } else if (angle >= -135 && angle < -45) {
                    result = 'swipedown';
                }
                else if ((angle >= 135 && angle <= 180) || (angle >= -180 && angle < -135)) {
                    result = 'swipeleft';
                }
                return result;
            }


            el.addEventListener('touchstart', function (ev) {
                startX = ev.touches[0].pageX;
                startY = ev.touches[0].pageY;

                //判断长按
                timeOutEvent = setTimeout(() =>{
                    timeOutEvent = 0 ;
                    if(touchType === 'press'){
                        binding.value()
                    }
                } , 300);
			//	ev.preventDefault();
            }, false);

            el.addEventListener('touchmove' , function (ev) {
                clearTimeout(timeOutEvent);
                timeOutEvent = 0;
                var endX, endY;
                endX = ev.changedTouches[0].pageX;
                endY = ev.changedTouches[0].pageY;
                direction = GetSlideDirection(startX, startY, endX, endY);
                
                switch (direction) {
                    case 0:

                        break;
                    case 'swipeup':
                        if(touchType === 'swipeup'){
                            binding.value();
                        }
                        break;
                    default:
                }
            });

            el.addEventListener('touchend', function (ev) {
            	clearTimeout(timeOutEvent);
                timeOutEvent = 0;
            }, false);
        }
    });
    
    Vue.directive('more', {
	    bind(element, binding) {
			var startTime = null;
			var touchType = binding.arg;
			
			//根据时间确定点击或长按
			function GetType(endTime) {
			  var result = 0;
			
			  if(endTime < 300) {
			    result = 'click';
			  } else if(endTime > 800) {
			    result = 'longtap';
			  }
			  return result;
			}
			
			element.addEventListener('touchstart', function(e) {
			  var msgMore = document.getElementById('msg-more');
			  startTime = new Date().getTime();
			   e.preventDefault();
			}, false);
			
			element.addEventListener('touchend', function(e) {
			  var msgMore = document.getElementById('msg-more');
			  var endTime = new Date().getTime() - startTime;
			  var time = GetType(endTime);
			  switch(time) {
			    case 'longtap':
			      if(touchType === 'longtap') {
			      	if(msgMore){
			      		msgMore.style.left =  document.body.clientWidth/2  + 'px';
	                    msgMore.style.top = (element.offsetTop-38) + 'px';
	                    msgMore.style.display = "block";
				      	binding.value.func(binding.value.index);
			      	}
			      }
			      break;
			    case 'click':
			      if(touchType === 'click') {
			        binding.value.func(binding.value.index,binding.value.item);
			        if(msgMore){
			        	if(msgMore.style.display == "block"){
			        		msgMore.style.display = 'none';   // 解决删除提示框显示的时候，点击播放，不消失、
				        }
			        }
			        
			      }
			      break;
			    default:
			  }
			   e.preventDefault()
			}, false)
		}
	});
    
}
