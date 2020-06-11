// 兼容 IE的placeholder功能
function placeholderForIE() {
    if (!('placeholder' in document.createElement('input'))) { // 判断浏览器是否支持placeholder
        $('input').each(function () {
            text = $(this).attr("placeholder");
            if (text != null && text != "")
                placeholder1($(this));
        });

    }

};

function placeholder1(input) {
    var $element = input;
    placeholder = $element.attr('placeholder');
    if (placeholder) {
        // 文本框ID
        var elementId = $element.attr('id');
        if (!elementId) {
            var now = new Date();
            elementId = 'lbl_placeholder' + now.getSeconds() + now.getMilliseconds();
            $element.attr('id', elementId);
        }

        // 添加label标签，用于显示placeholder的值
        var $label = $('<label>', {
            html: $element.val() ? '' : placeholder,
            'for': elementId,
            css: {
                position: 'absolute',
                cursor: 'text',
                color: '#a9a9a9',
                fontSize: $element.css('fontSize'),
                fontFamily: $element.css('fontFamily')
            }
        }).insertAfter($element);

        // 绑定事件
        var _setPosition = function () {
            $label.css({
                marginTop: '-'
                + (GetStringNumValue($element.css('height')) - 10)
                + 'px',
                marginLeft: 14 + 'px'
            });
        };
        var _resetPlaceholder = function () {
            if ($element.val()) {
                $label.html(null);
            }
            if ($element.val() == "" || $element.val() == null) {
                _setPosition();
                $label.html($element.attr('placeholder'));
            } else {
                $label.html(null);
            }
        };
        _setPosition();
        $element.on('focus blur input keyup propertychange resetplaceholder',
            _resetPlaceholder);

    }

};

function loadShow() {
    $("body").mLoading("show");//显示loading组件
}

function loadHide() {
    $("body").mLoading("hide");//隐藏loading组件
}

//定制弹出窗
function dialogAlert(type, content, size) {
    var dialogSize = BootstrapDialog.SIZE_SMALL
    if (size == "normal") {
        dialogSize = BootstrapDialog.SIZE_NORMAL
    }
    if (type == "success") {
        type = BootstrapDialog.TYPE_SUCCESS;
    } else if (type == "warning") {
        type = BootstrapDialog.TYPE_WARNING;
    } else if (type == "danger") {
        type = BootstrapDialog.TYPE_DANGER;
    } else {
        type = BootstrapDialog.TYPE_PRIMARY;
    }

    BootstrapDialog.show({
        type: type,
        title: '系统提示',
        message: content,
        draggable: true,
        size: dialogSize,
    });
};

function confirm(tipMes, successFunction, parm, size, type, parm2, parm3, falseFunction) {
    var dialogSize = BootstrapDialog.SIZE_SMALL;
    var dialogType = BootstrapDialog.TYPE_WARNING;
    var btnOKClass = 'btn-warning';
    if (size == "normal") {
        dialogSize = BootstrapDialog.SIZE_NORMAL;
    }
    if (type == "danger") {
        dialogType = BootstrapDialog.TYPE_DANGER;
    }
    if (type == "danger") {
        btnOKClass = 'btn-danger';
    }
    BootstrapDialog.confirm({
        title: '系统提示',
        message: tipMes,
        type: dialogType,
        closable: true,
        draggable: true,
        btnCancelLabel: '取消',
        btnOKLabel: '确认',
        btnOKClass: btnOKClass,
        size: dialogSize,
        callback: function (result) {
            if (result) {
                successFunction(parm, parm2, parm3);
            } else {
                if (falseFunction != null) {
                    falseFunction;
                } else
                    return;
            }
        }
    });

};

//下拉框的加载数据
function loadDataId(id, data) {
    $('#' + id).empty();
    $.each(data, function (i, item) {
        $("<option></option>").val(data[i].id).text(data[i].text).appendTo($('#' + id));
    });
};

//多选择框  里面的关键词中文化
function multiselectInit(id, width, selected) {
    $('#' + id).multiselect({
        enableFiltering: true, includeSelectAllOption: selected,
        nSelectedText: '项被选中', allSelectedText: '已全选',
        nonSelectedText: '请选择', selectAllText: '全选/取消全选',
        filterPlaceholder: '请输入搜索词', buttonWidth: width + 'px',
        maxHeight: 300
    });
};

function autocompleteInit(id, data, searchChar) {
    $("#" + id).autocomplete({
        source: data,
        minLength: searchChar,
        delay: 0,
    });
};

//用于编辑的时候  校验字段重复
function editableAjax(data, url, result) {
    var status = false;
    $.ajax({
        type: "post",
        dataType: "json",
        data: data,
        async: false,
        url: url,
        success: function (data) {
            if (data != null) {
                if (data.valid == true) {
                    status = true;
                    result = status;
                } else {
                    status = false;
                }
            }
            else {
                status = false;
            }

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            status = false;
        }
    });
    return status;
};

// 设置激活菜单
function setSidebarActive(tagUri) {
    var liObj = $("#" + tagUri);
    if (liObj.length > 0) {
        liObj.parent().parent().addClass("active");
        liObj.addClass("active");
    }
};

//去掉最后几个字符
function removeStr(str, removeNum) {
    return str.substring(0, str.length - removeNum);
};

// 全选或者全不选
function selAllAndNotAll(btnId, tableId) {
    $("#" + btnId).click(function () {
        var clicks = $(this).is(':checked');
        if (!clicks) {
            $("#" + tableId +" td input[type='checkbox']").iCheck("uncheck");
        } else {
            $("#" + tableId +" td input[type='checkbox']").iCheck("check");
        }
        $(this).data("clicks", !clicks);
    });
};

//table加载后处理全选按钮
function dealSelAllShow(btnId, tableId) {
    // 列表按钮样式
    $("#" + tableId +" td input[type='checkbox']").iCheck({
        checkboxClass: 'icheckbox_square-blue',
        increaseArea: '20%'
    });
    //解决点击按钮 刷新页面清空全选
    if ($("#" + btnId).is(':checked')) {
        $("#" + btnId).iCheck("uncheck");
    }

    $("#" + tableId +" td input[type='checkbox']").on('ifUnchecked', function(event){
        if ($("#" + btnId).is(':checked')) {
            $("#" + btnId).iCheck("uncheck");
        }
    });
}

//Modal验证销毁重构
function resetFormValidation(modal, form, validation) {
    $('#' + modal).on('hidden.bs.modal', function() {
        $('#' + form).data('formValidation').destroy();
        $('#' + form).data('formValidation', null);
        if (validation != undefined && validation != "") {
            validation();
        }
    });
};

function destroySelect2(selectId) {
    $("#" + selectId).select2().destroy;
    $("#" + selectId).html('');
}

function initSelect2(selectId, language, categorysData, placeholder) {
    $("#" + selectId).select2({
        language: language,
        data: categorysData,
        placeholder: placeholder,
        allowClear:true,
        width:"100%",
    });
}

//ajax失败处理
function ajaxError(status, forbiddenUrl) {
    if (status == 403) {
        window.location = forbiddenUrl;
    } else {
        if (status != 200) {
            dialogAlert("danger", "系统异常或者网络超时！");
        }
    }
}

//全局的ajax访问，处理ajax清求时sesion超时和单点登录
$(function () {
    $.ajaxSetup({
        complete: function(xhr, status) {
            if (xhr.responseText == 'expired') {
                confirmTip("会话已超时，请重新登录", toLoginPage, "normal", "danger");
            }
            if (xhr.responseText == 'maxSession') {
                confirmTip("当前账号已在其他设备登录", toLoginPage, "normal", "danger");
            }
        },
    });
});

function getProjectName() {
    var pathName = window.document.location.pathname;
    //获取带"/"的项目名，如：/my
    return pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
}

function toLoginPage() {
    window.location = getProjectName() + "/login.jsp";
}

function confirmTip(tipMes, work, size, type) {
    var dialogSize = BootstrapDialog.SIZE_SMALL;
    var dialogType = BootstrapDialog.TYPE_WARNING;
    var btnOKClass = 'btn-warning';
    if (size == "normal") {
        dialogSize = BootstrapDialog.SIZE_NORMAL;
    }
    if (type == "danger") {
        dialogType = BootstrapDialog.TYPE_DANGER;
    }
    if (type == "danger") {
        btnOKClass = 'btn-danger';
    }
    BootstrapDialog.show({
        title: '系统提示',
        message: tipMes,
        type: dialogType,
        size: dialogSize,
        closable: false,
        draggable: false,
        buttons: [{
            label: '确定',
            cssClass: btnOKClass,
            action: function(){
                work();
            }
        }]
    });

};

function getUrlParam(name) {
    var params = ('' + window.location).split('?');
    if(params.length > 1) {
        var keyValue = params[1].split('&');
        for(var i = 0; i < keyValue.length; i++) {
            var value = keyValue[i].split('=');
            if(value.length > 1 && value[0] == name) return value[1];
        }
    }
    return '';
}

//判断字符是否为空的方法
function isEmpty(obj){
    if(typeof obj == "undefined" || obj == null || obj == ""){
        return true;
    }else{
        return false;
    }
}

//判断arr是否包含obj
function isContain(obj, arr) {
    if (arr != null && arr.length > 0) {
        for (var j = 0; j < arr.length; j++) {
            if (obj == arr[j]) {
                return true;
            }
        }
    }
    return false;
}

//js本地图片预览，兼容ie[6-11]、火狐、Chrome17+、Opera11+、Maxthon3+、360浏览器、百度浏览器
function PreviewImage(fileObj, imgPreviewId,emptyImg) {
    var allowExtention = "jpg,bmp,gif,png,jpeg" ; //.jpg,.bmp,.gif,.png,允许上传文件的后缀名
    var extention = fileObj.value.substring(fileObj.value.lastIndexOf(".") + 1).toLowerCase(); //获取当前上传文件的扩展名
    //判断图片大小
    var fileSize = 0;
    var isIE = /msie/i.test(window.navigator.userAgent) && !window.opera;
    if (isIE && !fileObj.files) {
        fileObj.select();
        window.parent.focus();
        var path = document.selection.createRange().text;
        try{
            if(path != ""){
                var fso = new ActiveXObject("Scripting.FileSystemObject");
                fileSize = fso.GetFile(path).size;
            }
        }catch(e){
            var mes = "因为要读取图片大小，请按以下方法配置浏览器："+"\n"+"请打开【Internet选项-安全-Internet-自定义级别-ActiveX控件和插件-"+"\n"+"对未标记为可安全执行脚本的ActiveX控件初始化并执行脚本（不安全）-点击启用-确定】"+"\n"+"然后刷新页面或者重新打开浏览器";
            dialogAlert("warning",mes,"normal");
            fileObj.value = ""; //清空选中文件
            fileObj.select();
            document.selection.clear();
            $('#'+imgPreviewId).attr("src",emptyImg);
            $('#'+imgPreviewId+'New').remove();
            $('#'+imgPreviewId).show();
            return;
        }
    }else {
        fileSize = fileObj.files[0].size;
    }
    fileSize=Math.round(fileSize/1024/1024*100)/100; //单位为KB
    if(fileSize>=4){
        dialogAlert("warning","图片最大尺寸为4M，请重新上传!");
        fileObj.value = ""; //清空选中文件
        if (isIE && !fileObj.files) {
            fileObj.select();
            document.selection.clear();
        }
        fileObj.outerHTML = fileObj.outerHTML;
        $('#'+imgPreviewId).attr("src",emptyImg);
        $('#'+imgPreviewId+'New').remove();
        $('#'+imgPreviewId).show();
        return;
    }
    var browserVersion = window.navigator.userAgent.toUpperCase();
    if (allowExtention.indexOf(extention) > -1) {
        if (fileObj.files) {//兼容chrome、火狐7+、360浏览器5.5+等，应该也兼容ie10，HTML5实现预览
            if (window.FileReader) {
                var reader = new FileReader();
                reader.onload = function(e) {
                    document.getElementById(imgPreviewId).setAttribute("src", e.target.result);
                }
                reader.readAsDataURL(fileObj.files[0]);
            } else if (browserVersion.indexOf("SAFARI") > -1) {
                dialogAlert("warning","不支持Safari浏览器6.0以下版本的图片预览!");
            } else {
                dialogAlert("warning","不支持您当前使用的浏览器的图片预览!");
            }
        } else if (browserVersion.indexOf("MSIE") > -1) {//ie、360低版本预览
            if (browserVersion.indexOf("MSIE 6") > -1) {//ie6
                document.getElementById(imgPreviewId).setAttribute("src", fileObj.value);
            } else {//ie[7-9]
                fileObj.select();

                window.parent.focus(); //参考http://gallop-liu.iteye.com/blog/134477
                var newPreview = document.getElementById(imgPreviewId + "New");
                if (newPreview == null) {
                    newPreview = document.createElement("div");
                    newPreview.setAttribute("id", imgPreviewId + "New");
                    newPreview.style.width = document.getElementById(imgPreviewId).width + "px";
                    newPreview.style.height = document.getElementById(imgPreviewId).height + "px";
                    newPreview.style.border = "solid 1px #d2e2e2";
                }
                newPreview.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src='" + document.selection.createRange().text + "')";
                var tempDivPreview = document.getElementById(imgPreviewId);
                tempDivPreview.parentNode.insertBefore(newPreview, tempDivPreview);
                tempDivPreview.style.display = "none";
            }
        } else if (browserVersion.indexOf("FIREFOX") > -1) {//firefox
            var firefoxVersion = parseFloat(browserVersion.toLowerCase().match(/firefox\/([\d.]+)/)[1]);
            if (firefoxVersion < 7) {//firefox7以下版本
                document.getElementById(imgPreviewId).setAttribute("src", fileObj.files[0].getAsDataURL());
            } else {//firefox7.0+
                document.getElementById(imgPreviewId).setAttribute("src", window.URL.createObjectURL(fileObj.files[0]));
            }
        } else {
            dialogAlert("warning","不支持您当前使用的浏览器的图片预览!");
        }
    } else {
        dialogAlert("warning","仅支持" + allowExtention + "为后缀名的文件!");
        fileObj.value = ""; //清空选中文件
        if (browserVersion.indexOf("MSIE") > -1) {
            fileObj.select();
            document.selection.clear();
            $('#'+imgPreviewId+'New').remove();
            $('#'+imgPreviewId).show();
        }
        fileObj.outerHTML = fileObj.outerHTML;
        $('#'+imgPreviewId).attr("src",emptyImg);
    }
}

//判断变量是否以某个字符串结尾
function endWith(str, endStr){
    var len = str.length - endStr.length;
    return (len >= 0 && str.lastIndexOf(endStr) == len);
}





