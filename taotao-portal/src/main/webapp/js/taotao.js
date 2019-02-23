var TT = TAOTAO = {
    checkLogin: function () {
        var _ticket = $.cookie("TT_TOKEN");
        if (!_ticket) {
            return;
        }
        var _this = this;
        $.ajax({
            url: "http://localhost:4004/user/token/" + _ticket,
            dataType: "jsonp",
            type: "GET",
            success: function (data) {
                if (data.status == 200) {
                    var username = data.data.username;
                    console.log(_ticket)
                    var _this = this;
                    var html = username + "，欢迎来到淘淘！<a class=\"link-logout\" onclick='logout()'>[退出]</a>";
                    $("#loginbar").html(html);
                }
            }
        });
    },
}

$(function () {
    // 查看是否已经登录，如果已经登录查询登录信息
    TT.checkLogin();
});

function logout() {
    var token = $.cookie("TT_TOKEN");
    if (token == null) {
        return
    }
    $.ajax({
        url: "http://localhost:4004/user/logout/" + token,
        type: "GET",
        success: function (data) {
            if (data.status == 200) {
                // window.href = "http://localhost:4002";
                location.reload()
            }
        }
    })
}
