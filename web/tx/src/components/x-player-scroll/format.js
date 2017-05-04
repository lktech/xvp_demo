'use strict'

exports.formatTime = function(num){
    num = parseFloat(num / 1000);//毫秒转成秒
    var h = this.fill(Math.floor(num / 3600));//时
    var m = this.fill(Math.floor(num % 3600 / 60));//分
    var s = this.fill(Math.floor(num % 60));//秒
    if (Math.floor(num / 3600)) {
        return h + ":" + m + ":" + s;
    } else if (Math.floor(num % 3600 / 60)) {
        return m + ":" + s;
    } else {
        return "00:" + s;
    }
}

exports.fill = function(num) {
    if (num < 10) {
        return "0" + num;
    } else {
        return num;
    }
}