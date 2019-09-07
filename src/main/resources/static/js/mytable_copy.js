var tpl1l = '<div  id="left_div">';
tpl1l += '<table border="1">';
tpl1l += '<slot ></slot>';
tpl1l += '</table>';
tpl1l += '</div>';


var tpl1r = '<div id="right_div" style="width:130px">';
tpl1r += '<div id="right_div2">';
tpl1r += '<div id="right_table2">';
tpl1r += '<table border="1">';
tpl1r += '<slot ></slot>';
tpl1r += '</table>';
tpl1r += '</div>';
tpl1r += '</div>';

var tpl2 =
    '           <tr >' +
    '            <slot></slot>' +
    '           </tr>';
var tpl22 =
    '           <tr v-show="isShowHead">' +
    '            <slot></slot>' +
    '           </tr>';

var tpl3 = ' <td :colspan="colspan_" :rowspan="rowspan_"> <slot></slot></td>';

var tpl4 = '<tr style="text-align: center" @click=" $emit(\'expandbody\')"><slot></slot></tr>';
var tpl5 = '<th @click="expandhead"><slot></slot></th>';
var myBody = {
        data: function () {
            return {Name: 'hello'};
        },
        props: ["prop", "title", "isShow", "index"],
        template: tpl4,
        inject: ['grandParent'],
        mounted: function () {
            this.grandParent.propArr.push(this.prop);
            this.grandParent.titleArr.push(this.title);
        },
        methods: {
            haha: function (item) {
            },

        }
    }
;
var myHead = {
        data: function () {
            return {Name: 'hello'};
        },
        props: ["prop", "title", "isShow"],
        template: tpl5,
        inject: ['grandParent'],
        mounted: function () {
            this.grandParent.propArr.push(this.prop);
            this.grandParent.titleArr.push(this.title);
        },
        methods: {
            expandhead: function () {
                if (store.isShowHead_ == true) {

                    store.isShowHead_ = false;
                } else if (store.isShowHead_ == false) {
                    store.isShowHead_ = true;

                }
            },
        }
    }
;
var myTablel = {
    data: function () {
        return {Name: 1112, propArr: [], titleArr: []};
    },
    props: ["text", 'isShow'],
    template: tpl1l,
    provide: function () {
        return {grandParent: this};
    },
};
var myTabler = {
    data: function () {
        return {Name: 1112, propArr: [], titleArr: []};
    },
    props: ["text", 'isShow'],
    template: tpl1r,
    provide: function () {
        return {grandParent: this};
    },
};

var myTr = {
        data: function () {
            return {Name: 'hello'};
        },
        props: ["prop", "title", "isShow", "isShowBody", "index"],
        template: tpl2,
        inject: ['grandParent'],
        mounted: function () {
            this.grandParent.propArr.push(this.prop);
            this.grandParent.titleArr.push(this.title);
        },
        methods: {}
    }
;
var myTr2 = {
        data: function () {
            return {Name: 'hello'};
        },
        props: ["prop", "title", "isShow", "isShowHead"],
        template: tpl22,
        inject: ['grandParent'],
        mounted: function () {
            this.grandParent.propArr.push(this.prop);
            this.grandParent.titleArr.push(this.title);
        },
        methods: {}
    }
;

var myTd = {
    data: function () {
        return {Name: 'hello', ii: '你好'};
    },
    props: ["prop", "title", "rowspan_", 'colspan_', "width", "style", "item", "ii"],
    template: tpl3,
    inject: ['grandParent'],
    mounted: function () {
        this.grandParent.propArr.push(this.prop);
        this.grandParent.titleArr.push(this.title);
    }
};

Vue.component('my-tabler', myTabler);
Vue.component('my-tablel', myTablel);
Vue.component('my-tr', myTr);
Vue.component('my-th', myTr2);
Vue.component('my-td', myTd);
Vue.component('my-body', myBody);
Vue.component('my-head', myHead);



