
var basePath = /*[[@{/}]]*/"";

function isEmpty(obj,msg){
    if(typeof obj == "undefined" || obj == null || obj == ""){
        alertMsg = msg+"，不能为空！";
        return true;
    }else{
        return false;
    }
}

// 使用 isNaN
// function checkDouble(d) {
//     var te = /^[0-9]+\.{0,1}[0-9]*$/;
//     if(!te.test(d)){
//         return false;
//     }
//     return true;
// }

function checkSpecialChar(str) {
    var patrn = /[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、]/im;
    if (!patrn.test(str)) {// 如果包含特殊字符返回false
        return false;
    }
    return true;
}

function checkStrLen(str,name) {
    if (str.length<=1 || checkDouble(str)) {
        checksite = name+"，不能少于两个字！";
        return true;
    }
    return false;
}
