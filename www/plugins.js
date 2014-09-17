var exec = require('cordova/exec');

module.exports = {
    Scene: {
        SESSION:  0, // 聊天界面
        TIMELINE: 1, // 朋友圈
        FAVORITE: 2  // 收藏
    },

    Type: {
        APP:     1,
        EMOTION: 2,
        FILE:    3,
        IMAGE:   4,
        MUSIC:   5,
        VIDEO:   6,
        WEBPAGE: 7
    },

    wechatShare: function (message, onSuccess, onError) {
        exec(onSuccess, onError, "Plugins", "share", [message]);
    }
};
