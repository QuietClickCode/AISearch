
(function($) {
    /*options = {
        "data": {},
        "columns":[[]],
        "click": click
    }*/
    $.fn.mobileTable = function(options) {

       this.addClass("table-container")
       this.height(this.parent().height())
        var ltwidthJson = [];//width
        var widthJson = [];//width
        var data = options.data;
        var columns = options.columns[0];
        var clickFn = options.click
        
        if(!clickFn){
            clickFn = function(){
                console.log("没有定义点击事件");
            }
        }

        var tableLtHtml = '<div class="table-lt"><table><tbody><tr>'//固定列头
        var tableLeftHtml = '<div class="table-left"><div class="table-mask"><table><tbody>';//固定列body

        var tableTopHtml = '<div class="table-top"><div class="table-mask"><table><tbody><tr>';//普通列头
        var tableRightHtml = '<div class="table-right"><table><tbody>';//普通body

        for(var i=0; i<columns.length; i++){
            /*titleArr.push(columns[i].title);
            titleColArr.push(columns[i].field);
            
            widthJson.push(columns[i].width); */

            if(columns[i].fixed){
                tableLtHtml += '<td><div>' + columns[i].title + '</div></td>';
                ltwidthJson.push(columns[i].width);
            }else{
                tableTopHtml += '<td><div>' + columns[i].title + '</div></td>';
                widthJson.push(columns[i].width);
            }
        }//加载表头

        for (var j = 0; j < data.length; j++) {
            tableRightHtml += '<tr>';
            tableLeftHtml += '<tr>';
            for(var i=0; i<columns.length; i++){
                var tdText = data[j][columns[i].field]?data[j][columns[i].field]:"";
                if(columns[i].fixed){
                    tableLeftHtml += '<td field="'+columns[i].field+'"><div>' + tdText + '</div></td>'; 
                }else{
                    tableRightHtml += '<td field="'+columns[i].field+'"><div>' + tdText + '</div></td>'; 
                }
            }
            tableLeftHtml += '</tr>'
            tableRightHtml += '</tr>';
        }//加载表body

            

        tableLtHtml +='</tr></tbody></table></div>';
        tableTopHtml += ' </tr></tbody></table></div></div>';
        tableLeftHtml += '</tbody></table></div></div>';
        tableRightHtml += '</tbody></table></div>';


        this.html(tableLtHtml + tableTopHtml + tableLeftHtml + tableRightHtml);

        var leftTr = this.find(".table-left").find("tr");
        var rightTr = this.find(".table-right").find("tr");
        var row = {};

        leftTr.click(function(){
            row = {};
            var index = $(this).index();
            var lTds = $(this).find("td");
            for(var i=0; i<lTds.length; i++){
                row[$(lTds[i]).attr("field")] = $(lTds[i]).text();
            }
            var rTtd = rightTr.eq(index).find("td");
            for(var i=0; i<rTtd.length; i++){
                row[$(rTtd[i]).attr("field")] = $(rTtd[i]).text();
            }
            clickFn(index, row);
        })

        rightTr.click(function(){
            row = {};
            var index = $(this).index();
            if(leftTr.length){

                var lTds = leftTr.eq(index).find("td");
               
                for(var i=0; i<lTds.length; i++){
                    row[$(lTds[i]).attr("field")] = $(lTds[i]).text();
                }
            }
            
            var rTtd = $(this).find("td");
            for(var i=0; i<rTtd.length; i++){
                row[$(rTtd[i]).attr("field")] = $(rTtd[i]).text();
            }
            clickFn(index, row)
        })
        
        excelTableStyle(this, ltwidthJson, widthJson);
        return this;
    }
    function excelTableStyle($this, ltwidthJson, widthJson) {
    var container = $this;
    container.css("width", $(window).width());
    
    var ltTable = container.find(".table-lt"),
        topTable = container.find(".table-top"),
        leftTable = container.find(".table-left"),
        rightTable = container.find(".table-right"),

        containerWidth = 0,
        containerHeight = 0,

        ltTableWidth = 0,
        ltTableHeight = 0,

        widthJson = widthJson,

        timerLT = null;
    if(ltTable.find("tr").length){
        var ltWidth = 0
        for(var i=0; i<ltwidthJson.length; i++){
            ltWidth += ltwidthJson[i]
        }
        ltTableWidth = ltWidth; 
    }else{
        ltTableWidth = 0;
    }
    ltTable.width(ltTableWidth);
    topTable.css("marginLeft", ltTableWidth);
    leftTable.width(ltTableWidth);
    rightTable.css("marginLeft", ltTableWidth);
    ltTableHeight = 46;//暂时写死
    

    leftTable.css("marginTop", ltTableHeight + "px");
    rightTable.css("marginTop", ltTableHeight + "px");

    containerHeight = container.height();
    containerWidth = container.width();
    topTable.width(containerWidth - ltTableWidth - (container.innerWidth() - container[0].clientWidth));
    leftTable.height(containerHeight - ltTableHeight - (container.innerHeight() - container[0].clientHeight));

    
    function setDivWidth(obj) {

        $(obj).find("div").each(function (index) {
                if (!widthJson[index]) {
                    widthJson[index] = 0;
                }
                if (widthJson[index] < $(this).width()) {
                    widthJson[index] = $(this).width();
                }
            });
        }

        if(ltTable.find("tr").length){
            ltTable.find("tr").find("td").each(function(index){
                $(this).width(ltwidthJson[0])
            })
            ltTable.find("tr").find("td").each(function(index){
                $(this).width(ltwidthJson[0]);
            })
        }
        
        topTable.find("tr").each(function () {
            setDivWidth(this);
        });
        rightTable.find("tr").each(function () {
            setDivWidth(this);
        });

        topTable.find("tr:first div").each(function (index) {
            $(this).width(widthJson[index]);
        });
        rightTable.find("tr:first div").each(function (index) {
            $(this).width(widthJson[index]);
        });
        

        container.scroll(function () {
            var currentScrollTop = $(this).scrollTop(),
                currentScrollLeft = $(this).scrollLeft();
            topTable.find(".table-mask").css("left", -currentScrollLeft + "px");
            leftTable.find(".table-mask").css("top", -currentScrollTop + "px");

        });

        $(document).scroll(function () {
            var currentScrollTop = $(this).scrollTop(),
                currentScrollLeft = $(this).scrollLeft();
            ltTable.css("marginTop", -currentScrollTop + "px");
            ltTable.css("marginLeft", -currentScrollLeft + "px");
            topTable.css("marginTop", -currentScrollTop + "px");
            topTable.css("marginLeft", ltTableWidth - currentScrollLeft + "px");
            leftTable.css("marginTop", ltTableHeight - currentScrollTop + "px");
            leftTable.css("marginLeft", -currentScrollLeft + "px");

        });

        $(window).resize(function () {
            container.width($(window).width())
            $(document).scroll();
        });

        timerLT = setInterval(function () {
            if (containerWidth != container.width() || containerHeight != container.height()) {
                topTable.width(container.width() - ltTableWidth - (container.innerWidth() - container[0].clientWidth));
                leftTable.height(container.height() - ltTableHeight - (container.innerHeight() - container[0].clientHeight));

                containerWidth = container.width();
                containerHeight = container.height();

                container.scroll();// for IE

            }
            ;

        }, 0);
    };
})(jQuery);



