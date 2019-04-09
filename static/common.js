(function($){
    $.fn.extend({
        setValue:function(value){
            if(!this[0]){
                return;
            }
            var tagName = this[0].tagName.toUpperCase();
            if(tagName=="INPUT"){
                var type = this.attr("type");
                //type没有设置 默认是text
                if(!type){
                    this.val(value);
                    return false;
                }
                type = type.toLowerCase();
                switch(type)
                {
                    case "text":
                        this.val(value);
                        break;
                    case "hidden":
                        this.val(value);
                        break;
                    case "radio":
                        var name = this.attr("name");
                        var $radio = $("[name="+name+"]:radio");
                        $radio.each(function(index,radio){
                            if($(radio).val()==value){
                                $(radio).attr("checked",true);
                                return true;
                            }
                        });
                        break;
                    case "checkbox":
                        if(!isArray(value)){
                            throw "value must be Array";
                        }
                        var name = this.attr("name");
                        var $checkbox = $("[name="+name+"]:checkbox");
                        $checkbox.each(function(index,checkbox){
                            if(value.contains($(checkbox).val())){
                                $(checkbox).attr("checked",true);
                            }
                        });
                        break;
                    default:
                        break;
                }
            }else if(tagName=="SELECT"||tagName=="TEXTAREA"){
                this.val(value);
            }else{
                this.text(value);
            }
        },
        getValue:function(){
            var tagName = this[0].tagName.toUpperCase();
            if(tagName=="INPUT"){
                var type = this.attr("type");
                if(!type){
                    return this.val();
                }
                type = type.toLowerCase();
                switch(type){
                    case "text":
                        return this.val();
                        break;
                    case "hidden":
                        return this.val();
                        break;
                    case "radio":
                        var name = this.attr("name");
                        var $radio = $("[name="+name+"]:radio");
                        var result;
                        $radio.each(function(index,radio){
                            if($(radio).is(":checked")){
                                result = $(radio).val();
                            }
                        });
                        return result;
                        break;
                    case "checkbox":
                        var name = this.attr("name");
                        var $radio = $("[name="+name+"]:checkbox");
                        var result = [];
                        $radio.each(function(index,checkbox){
                            if($(checkbox).is(":checked")){
                                result.push($(checkbox).val());
                            }
                        });
                        return result;
                        break;
                    default:
                        break;
                }
            }else if(tagName=="SELECT"||tagName=="TEXTAREA"){
                return this.val();
            }else{
                return this.text();
            }
        },
        loadForm:function(data,formatter){
            for(var p in data){
                this.find("[name="+p+"]").setValue(data[p]);
            }
            if(formatter){
                for(var p in formatter){
                    this.find("[name="+p+"]").setValue(formatter[p](data[p],data));
                }
            }
        },
        bootstrapTable:function (option) {
            var thiz = this;
            var dataList = option.data?option.data:[];
            var columns = option.columns?option.columns:[];
            var tableHTML = thiz.createTitle(columns);
            tableHTML+=thiz.createData(columns,dataList);
            thiz.empty().append(tableHTML);
        },
        createTitle:function (columns) {
            var tableHTML = "<tr>";
            for(var i=0;i<columns.length;i++){
                var column = columns[i];
                tableHTML+="<th>"+column.title+"</th>"
            }
            tableHTML+="</tr>";
            return tableHTML;
        },
        createData:function (columns,dataList) {
            var tableHTML="";
            for(var i=0;i<dataList.length;i++){
                tableHTML+="<tr>";
                for(var j=0;j<columns.length;j++){
                    var data = dataList[i]
                    var column = columns[j];
                    var field = column.field;
                    tableHTML+="<td>"+data[field]+"</td>";
                }
                tableHTML+="</tr>"
            }
            return tableHTML;
        }
    });

    $.fn.extend({
        postFormAjax:function(url) {
            var result;
            var data = _getData(this);
            postAjax(url,data,function(res){
                result = res;
            });
            return result;
        },
        putFormAjax:function(url){
            var result;
            var data = _getData(this);
            putAjax(url,data,function(res){
                result = res;
            });
            return result;
        },
    });
})(jQuery);

function _totalAjax(url,type,data,async,successFn,errorFn){
    var options = {
        url: url,
        type: type,
        async: async,
        data: JSON.stringify(data),
        contentType: 'application/json;utf-8',
        dataType: 'json',
        success: function (data) {
            if(successFn)
                successFn(data);
        },
        error: function (data, status, res) {
            alert("网络出现问题，及时联系网管");
            if(errorFn)
                errorFn(data, status, res);
        }
    };
    if(!data){
        delete  options.data;
    }
    $.ajax(
        options
    );
}
function getAjax(url,successFn,errorFn) {
    if(url.indexOf("?")>-1){
        url+='&='+Math.random();
    }else{
        url+='?='+Math.random();
    }
    _totalAjax(url,'get',null,true,successFn,errorFn);
}

function postAjax(url,data,successFn,errorFn) {
    _totalAjax(url,'post',data,false,successFn,errorFn);
}

function putAjax(url,data,successFn,errorFn) {
    _totalAjax(url,'put',data,false,successFn,errorFn);
}

function _getData(dom) {
    var data = {};
    var $dom = dom.find("[name]");
    for (var i = 0; i < $dom.length; i++) {

        data[$dom.eq(i).attr("name")] = dom.find("[name="+$dom.eq(i).attr("name")+"]").getValue();
    }
    return data;

}

function deleteAjax(url,successFn,errorFn) {
    _totalAjax(url,'delete',null,false,successFn,errorFn);
}