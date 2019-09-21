(function () {
    Vue.component("my-select", {
        /*  template: `
                  <div style="display: inline" class="ffix">
                      <input id="sel" v-model="selectedLabel" class="keyWord" readonly @click="Show"/>
                      <ul id="ul" :class="{noShow:isShow,positionup:isFlag,positiondown:!isFlag}">
                          <li @click="selectOptionClick">
                              <span>请选择</span>
                          </li>
                          <slot></slot>
                      </ul>
                  </div>
  `,*/

        render: function (createElement) {
            var self = this;
            return createElement('div', {
                'class': {
                    ffix: true
                },
                style: {
                    display: 'inline'
                }
            }, [
                createElement('input', {
                    domProps: {
                        value: self.selectedLabel,
                        readonly: true
                    },
                    on: {
                        click: self.Show,
                        input: function (event) {
                            self.$emit('input', event.target.value)

                        }
                    },
                    attrs: {
                        id: 'sel',
                    },
                    'class': {
                        keyWord: true
                    }
                }),
                createElement('ul', {
                    attrs: {
                        id: 'ul',
                    },
                    'class': {
                        noShow: self.isShow, positiondown: !(self.isFlag), positionup: self.isFlag
                    }
                }, [
                    createElement('li', [
                        createElement('span', '请选择'),
                    ]),
                    this.$slots.default
                ])
            ])
        },
        provide: function () {
            return {
                'select': this
            }
        },
        created: function () {
            if (this.selectedValue == "") {
                this.selectedLabel = "请选择"
            }
            this.$on('handleOptionClick', this.handleOptionSelect);
        },
        mounted: function () {
            for (var i = 0; i < this.options.length; i++) {
                if (this.selectedValue == this.options[i].value) {
                    this.selectedLabel = this.options[i].label;
                }
            }
            var myheight;
            var myheight1;
            var myheight2;
            $(window).scroll(function () {
                myheight = $("#sel").offset().top;
                myheight1 = $(window).scrollTop();
                myheight2 = $(window).height() / 2
                if (myheight - myheight1 > myheight2) {
                    $('#ul').removeClass('positiondown')
                    $('#ul').addClass('positionup')
                } else {
                    $('#ul').removeClass('positionup')
                    $('#ul').addClass('positiondown')
                }
            })
        },
        data: function () {
            return {
                isFlag: false,
                isShow: true,
                selectedValue: this.value,
                selectedLabel: "",
                options: []
            }
        },
        props: ['value'],
        watch: {
            value: function () {
                this.selectedValue = this.value;
                for (var i = 0; i < this.options.length; i++) {
                    if (this.selectedValue == this.options[i].value) {
                        this.selectedLabel = this.options[i].label;
                    }
                }
            },
        },
        methods: {
            Show: function () {
                var myheight;
                var myheight1;
                var myheight2;
                this.isShow = !this.isShow;
                myheight = $("#sel").offset().top;
                myheight1 = $(window).scrollTop();
                myheight2 = $(window).height() / 2
                if (myheight - myheight1 > myheight2) {
                    this.isFlag = true;
                } else {
                    this.isFlag = false;
                }
            },
            handleOptionSelect: function (option, byClick) {
                this.$emit('input', option.value);
            },

        }

    })

    Vue.component("my-option", {
        /* template: `
                 <li @click="selectOptionClick">
                     <span>{{ currentLabel }}</span>
                 </li>
 `,*/

        render: function (h) {
            return h('li', {
                on: {
                    click: this.selectOptionClick
                }
            }, [
                h('span', {
                    domProps: {innerHTML: this.currentLabel}
                })
            ])
        },
        created() {
            this.select.options.push(this);
        },
        data: function () {
            return {
                isShow: false
            }
        },
        computed: {
            currentLabel() {
                return this.label;
            },
        },
        inject: ["select"],
        methods: {
            selectOptionClick() {
                this.select.isShow = !this.select.isShow;
                this.select.$emit('handleOptionClick', this, true);

            },
        },
        props: ["value", "label"],
    })
})();