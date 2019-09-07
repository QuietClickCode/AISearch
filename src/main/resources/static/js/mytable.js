/*var tpl1 = '<div class="div">' + '<table  >' + '<slot ></slot>' + '</table>' + '</div>';

var tpl2 = '<tr class="tr">' + '<slot></slot>' + '</tr>';

var tpl3 = ' <td class="td"  :colspan="colspan" :rowspan="rowspan"  >' + '<div class="tddiv"><slot></slot></div>' + '</td>';

var tpl4 = '<tr   class="bodys" @click=" $emit(\'expand\')">' + '<slot></slot>' + '</tr>';


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
        }
    }

;
var myTable = {
    data: function () {
        return {Name: 1112, propArr: [], titleArr: []};
    },
    props: ["text", 'isShow'],
    template: tpl1,
    provide: function () {
        return {grandParent: this};
    },
};

var myTr = {
        data: function () {
            return {Name: 'hello'};
        },
        props: ["prop", "title", "isShow", "isShowHead"],
        template: tpl2,
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
    props: ["prop", "title", "rowspan", 'colspan', "width", "style", "item", "ii"],
    template: tpl3,
    inject: ['grandParent'],
    mounted: function () {
        this.grandParent.propArr.push(this.prop);
        this.grandParent.titleArr.push(this.title);
    }
};

Vue.component('my-table', myTable);
Vue.component('my-tr', myTr);
Vue.component('my-td', myTd);
Vue.component('my-body', myBody);*/


var mybody = `
<div class="body">
<slot></slot>
</div>
`

var myhead = `
<div class="header">
<slot></slot>
</div>


`
var myfix = `
<div class="custom-table--fixed-wrapper" style="z-index: 100;height:1200px;width:107px;">
<div class="custom-table--header-wrapper" >
<slot name="fixhead"></slot>
</div>
<div class="custom-table--body-wrapper is-fixed " style="overflow: hidden">
<slot></slot>
</div>
</div>
`

var myBody = {
        data: function () {
            return {Name: 'hello'};
        },
        props: ["prop", "title", "isShow", "index"],
        template: mybody,
        inject: ['grandParent'],
        mounted: function () {
            this.grandParent.propArr.push(this.prop);
            this.grandParent.titleArr.push(this.title);
        }
    }
;
var myHead = {
        data: function () {
            return {Name: 'hello'};
        },
        props: ["prop", "title", "isShow", "index"],
        template: myhead,
        inject: ['grandParent'],
        mounted: function () {
            this.grandParent.propArr.push(this.prop);
            this.grandParent.titleArr.push(this.title);
        }
    }
;
var myFix = {
        data: function () {
            return {Name: 'hello'};
        },
        props: ["prop", "title", "isShow", "index"],
        template: myfix,
        inject: ['grandParent'],
        mounted: function () {
            this.grandParent.propArr.push(this.prop);
            this.grandParent.titleArr.push(this.title);
        }
    }
;

Vue.component('my-body', myBody);
Vue.component('my-head', myHead);
Vue.component('my-fix', myFix);

