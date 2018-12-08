Vue.component('console-iframe', {
    props:['id','url','iscollapse'],
    mounted: function () {
        const oIframe = document.getElementById(this.id);
        const deviceWidth = document.documentElement.clientWidth;
        const deviceHeight = document.documentElement.clientHeight;
        console.log(deviceHeight);
        if(this.iscollapse){//水平折叠收起
            oIframe.style.width = (Number(deviceWidth)-70) + 'px'; //数字是页面布局宽度差值
        }else{
            oIframe.style.width = (Number(deviceWidth)-200) + 'px'; //数字是页面布局宽度差值
        }

        oIframe.style.height = (Number(deviceHeight)-120) + 'px'; //数字是页面布局高度差
    },
    template: '<iframe ref="iframe" :id="id" :src="url" frameborder="0" scrolling="auto"></iframe>'
});