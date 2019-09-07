/**
 * Created by chenyihong on 14/12/4.
 */

KindEditor.plugin('fixtoolbar', function (K) {
    var self = this;
    if (!self.fixToolBar) {
        return;
    }

    function init() {
        var toolbar = self.toolbar.div;
        //var originY = toolbar.pos().y;
        var y = $(toolbar).closest(".myPart").offset().top;
        K(window).bind('scroll', function () {
            if (toolbar.css('position') == 'fixed') {
                if (document.body.scrollTop - y < 0) {
                    toolbar.css('position', 'static');
                    toolbar.css('top', 'auto');
                    toolbar.css('width', '318px');
                }
            } else {
                //if (toolbar.pos().y - document.body.scrollTop < 0) {
                if ($(toolbar).offset().top - document.body.scrollTop < 0) {
                    y = $(toolbar).closest(".myPart").offset().top;
                    toolbar.css('position', 'fixed');
                    toolbar.css('top', 0);
                    toolbar.css('width', '318px');
                }
            }
        });
    }

    if (self.isCreated) {
        init();
    } else {
        self.afterCreate(init);
    }

});
