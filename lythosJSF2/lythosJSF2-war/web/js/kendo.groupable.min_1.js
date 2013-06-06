/*
* Kendo UI Complete v2013.1.319 (http://kendoui.com)
* Copyright 2013 Telerik AD. All rights reserved.
*
* Kendo UI Complete commercial licenses may be obtained at
* https://www.kendoui.com/purchase/license-agreement/kendo-ui-complete-commercial.aspx
* If you do not own a commercial license, this file shall be governed by the trial license terms.
*/
("function"==typeof define&&define.amd?define:function(t,e){return e()})(["./kendo.core.min","./kendo.draganddrop.min"],function(){(function(t){function e(t){return t.position().top+3}var a=window.kendo,r=a.ui.Widget,n=t.proxy,i=".kendoGroupable",o=a.template('<div class="k-group-indicator" data-#=data.ns#field="${data.field}" data-#=data.ns#title="${data.title || ""}" data-#=data.ns#dir="${data.dir || "asc"}"><a href="\\#" class="k-link"><span class="k-icon k-si-arrow-${(data.dir || "asc") == "asc" ? "n" : "s"}">(sorted ${(data.dir || "asc") == "asc" ? "ascending": "descending"})</span>${data.title ? data.title: data.field}</a><a class="k-button k-button-icon k-button-bare"><span class="k-icon k-group-delete"></span></a></div>',{useWithBlock:!1}),d=function(e){return t('<div class="k-header k-drag-clue" />').css({width:e.width(),paddingLeft:e.css("paddingLeft"),paddingRight:e.css("paddingRight"),lineHeight:e.height()+"px",paddingTop:e.css("paddingTop"),paddingBottom:e.css("paddingBottom")}).html(e.attr(a.attr("title"))||e.attr(a.attr("field"))).prepend('<span class="k-icon k-drag-status k-denied" />')},s=t('<div class="k-grouping-dropclue"/>'),l=/("|'|\[|\]|\$|\.|\:|\+)/g,g=r.extend({init:function(o,l){var g,c,u=this,p=a.guid(),f=n(u._intializePositions,u),h=u._dropCuePositions=[];r.fn.init.call(u,o,l),u.draggable=c=u.options.draggable||new a.ui.Draggable(u.element,{filter:u.options.draggableElements,hint:d,group:p}),g=u.groupContainer=t(u.options.groupContainer,u.element).kendoDropTarget({group:c.options.group,dragenter:function(t){u._canDrag(t.draggable.currentTarget)&&(t.draggable.hint.find(".k-drag-status").removeClass("k-denied").addClass("k-add"),s.css({top:e(g),left:0}).appendTo(g))},dragleave:function(t){t.draggable.hint.find(".k-drag-status").removeClass("k-add").addClass("k-denied"),s.remove()},drop:function(e){var r,n=e.draggable.currentTarget,i=n.attr(a.attr("field")),o=n.attr(a.attr("title")),d=u.indicator(i),l=u._dropCuePositions,g=l[l.length-1];(n.hasClass("k-group-indicator")||u._canDrag(n))&&(g?(r=u._dropCuePosition(a.getOffset(s).left+parseInt(g.element.css("marginLeft"),10)+parseInt(g.element.css("marginRight"),10)),r&&u._canDrop(t(d),r.element,r.left)&&(r.before?r.element.before(d||u.buildIndicator(i,o)):r.element.after(d||u.buildIndicator(i,o)),u._change())):(u.groupContainer.append(u.buildIndicator(i,o)),u._change()))}}).kendoDraggable({filter:"div.k-group-indicator",hint:d,group:c.options.group,dragcancel:n(u._dragCancel,u),dragstart:function(t){var a=t.currentTarget,r=parseInt(a.css("marginLeft"),10),n=a.position().left-r;f(),s.css({top:e(g),left:n}).appendTo(g),this.hint.find(".k-drag-status").removeClass("k-denied").addClass("k-add")},dragend:function(){u._dragEnd(this)},drag:n(u._drag,u)}).on("click"+i,".k-button",function(e){e.preventDefault(),u._removeIndicator(t(this).parent())}).on("click"+i,".k-link",function(e){var r=t(this).parent(),n=u.buildIndicator(r.attr(a.attr("field")),r.attr(a.attr("title")),"asc"==r.attr(a.attr("dir"))?"desc":"asc");r.before(n).remove(),u._change(),e.preventDefault()}),c.bind(["dragend","dragcancel","dragstart","drag"],{dragend:function(){u._dragEnd(this)},dragcancel:n(u._dragCancel,u),dragstart:function(t){var e,a,r;return u.options.allowDrag||u._canDrag(t.currentTarget)?(f(),h.length?(e=h[h.length-1].element,a=parseInt(e.css("marginRight"),10),r=e.position().left+e.outerWidth()+a):r=0,undefined):(t.preventDefault(),undefined)},drag:n(u._drag,u)}),u.dataSource=u.options.dataSource,u.dataSource&&(u._refreshHandler=n(u.refresh,u),u.dataSource.bind("change",u._refreshHandler))},refresh:function(){var e=this,r=e.dataSource;e.groupContainer.empty().append(t.map(r.group()||[],function(t){var r=t.field.replace(l,"\\$1"),n=e.element.find(e.options.filter).filter("["+a.attr("field")+"="+r+"]");return e.buildIndicator(t.field,n.attr(a.attr("title")),t.dir)}).join("")),e._invalidateGroupContainer()},destroy:function(){var t=this;r.fn.destroy.call(t),t.groupContainer.off(i).kendoDropTarget("destroy").kendoDraggable("destroy"),t.options.draggable||t.draggable.destroy(),t.dataSource&&t._refreshHandler&&t.dataSource.unbind("change",t._refreshHandler)},options:{name:"Groupable",filter:"th",draggableElements:"th",messages:{empty:"Drag a column header and drop it here to group by that column"}},indicator:function(e){var r=t(".k-group-indicator",this.groupContainer);return t.grep(r,function(r){return t(r).attr(a.attr("field"))===e})[0]},buildIndicator:function(t,e,r){return o({field:t.replace(/"/g,"'"),dir:r,title:e,ns:a.ns})},descriptors:function(){var e,r,n,i,o,d=this,s=t(".k-group-indicator",d.groupContainer);return e=d.element.find(d.options.filter).map(function(){var e=t(this),n=e.attr(a.attr("aggregates")),d=e.attr(a.attr("field"));if(n&&""!==n)for(r=n.split(","),n=[],i=0,o=r.length;o>i;i++)n.push({field:d,aggregate:r[i]});return n}).toArray(),t.map(s,function(r){return r=t(r),n=r.attr(a.attr("field")),{field:n,dir:r.attr(a.attr("dir")),aggregates:e||[]}})},_removeIndicator:function(t){var e=this;t.remove(),e._invalidateGroupContainer(),e._change()},_change:function(){var t=this;t.dataSource&&t.dataSource.group(t.descriptors())},_dropCuePosition:function(e){var a,r,n,i,o=this._dropCuePositions;if(s.is(":visible")&&0!==o.length)return e=Math.ceil(e),a=o[o.length-1],r=a.right,n=parseInt(a.element.css("marginLeft"),10),i=parseInt(a.element.css("marginRight"),10),e>=r?e={left:a.element.position().left+a.element.outerWidth()+i,element:a.element,before:!1}:(e=t.grep(o,function(t){return e>=t.left&&t.right>=e})[0],e&&(e={left:e.element.position().left-n,element:e.element,before:!0})),e},_drag:function(t){var e=this._dropCuePosition(t.x.location);e&&s.css({left:e.left})},_canDrag:function(t){var e=t.attr(a.attr("field"));return"false"!=t.attr(a.attr("groupable"))&&e&&(t.hasClass("k-group-indicator")||!this.indicator(e))},_canDrop:function(t,e,a){var r=t.next();return t[0]!==e[0]&&(!r[0]||e[0]!==r[0]||a>r.position().left)},_dragEnd:function(e){var r=this,n=e.currentTarget.attr(a.attr("field")),i=r.indicator(n);e!==r.options.draggable&&!e.dropped&&i&&r._removeIndicator(t(i)),r._dragCancel()},_dragCancel:function(){s.remove(),this._dropCuePositions=[]},_intializePositions:function(){var e,r=this,n=t(".k-group-indicator",r.groupContainer);r._dropCuePositions=t.map(n,function(r){return r=t(r),e=a.getOffset(r).left,{left:parseInt(e,10),right:parseInt(e+r.outerWidth(),10),element:r}})},_invalidateGroupContainer:function(){var t=this.groupContainer;t.is(":empty")&&t.html(this.options.messages.empty)}});a.ui.plugin(g)})(window.kendo.jQuery)});