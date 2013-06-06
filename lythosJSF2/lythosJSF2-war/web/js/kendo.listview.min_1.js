/*
* Kendo UI Complete v2013.1.319 (http://kendoui.com)
* Copyright 2013 Telerik AD. All rights reserved.
*
* Kendo UI Complete commercial licenses may be obtained at
* https://www.kendoui.com/purchase/license-agreement/kendo-ui-complete-commercial.aspx
* If you do not own a commercial license, this file shall be governed by the trial license terms.
*/
("function"==typeof define&&define.amd?define:function(e,t){return t()})(["./kendo.data.min","./kendo.editable.min","./kendo.selectable.min"],function(){(function(e,t){var a=window.kendo,n="change",r="cancel",i="dataBound",l="dataBinding",o=a.ui.Widget,d=a.keys,s=">*",c="progress",u="error",m="k-state-focused",f="k-state-selected",p="k-edit-item",g="string",v="edit",b="remove",_="save",h="click",S=".kendoListView",E=e.proxy,w=a._activeElement,y=a.ui.progress,k=a.data.DataSource,T=o.extend({init:function(t,n){var r=this;n=e.isArray(n)?{dataSource:n}:n,o.fn.init.call(r,t,n),n=r.options,r.wrapper=t=r.element,t[0].id&&(r._itemId=t[0].id+"_lv_active"),r._element(),r._dataSource(),r.template=a.template(n.template||""),r.altTemplate=a.template(n.altTemplate||n.template),r.editTemplate=a.template(n.editTemplate||""),r._navigatable(),r._selectable(),r._pageable(),r._crudHandlers(),r.options.autoBind&&r.dataSource.fetch(),a.notify(r)},events:[n,r,l,i,v,b,_],options:{name:"ListView",autoBind:!0,selectable:!1,navigatable:!1,template:"",altTemplate:"",editTemplate:""},_item:function(e){return this.element.children()[e]()},items:function(){return this.element.children()},setDataSource:function(e){this.options.dataSource=e,this._dataSource(),this.options.autoBind&&e.fetch()},_unbindDataSource:function(){var e=this;e.dataSource.unbind(n,e._refreshHandler).unbind(c,e._progressHandler).unbind(u,e._errorHandler)},_dataSource:function(){var e=this;e.dataSource&&e._refreshHandler?e._unbindDataSource():(e._refreshHandler=E(e.refresh,e),e._progressHandler=E(e._progress,e),e._errorHandler=E(e._error,e)),e.dataSource=k.create(e.options.dataSource).bind(n,e._refreshHandler).bind(c,e._progressHandler).bind(u,e._errorHandler)},_progress:function(){y(this.element,!0)},_error:function(){y(this.element,!1)},_element:function(){this.element.addClass("k-widget k-listview").attr("role","listbox")},refresh:function(n){var r,o,d,s,c,u=this,m=u.dataSource.view(),f="",p=u.template,g=u.altTemplate,v=w();if(n&&"itemchange"===n.action)return u.editable||(r=n.items[0],s=e.inArray(r,m),s>=0&&(u.items().eq(s).replaceWith(p(r)),d=u.items().eq(s),d.attr(a.attr("uid"),r.uid),u.trigger("itemChange",{item:d,data:r}))),t;if(n=n||{},!u.trigger(l,{action:n.action||"rebind",items:n.items,index:n.index})){for(u._destroyEditable(),s=0,c=m.length;c>s;s++)f+=s%2?g(m[s]):p(m[s]);for(u.element.html(f),o=u.items(),s=0,c=m.length;c>s;s++)o.eq(s).attr(a.attr("uid"),m[s].uid).attr("role","option").attr("aria-selected","false");u.element[0]===v&&u.options.navigatable&&u.current(o.eq(0)),u.trigger(i)}},_pageable:function(){var t,n,r=this,i=r.options.pageable;e.isPlainObject(i)&&(n=i.pagerId,t=e.extend({},i,{dataSource:r.dataSource,pagerId:null}),r.pager=new a.ui.Pager(e("#"+n),t))},_selectable:function(){var e,r,i=this,l=i.options.selectable,o=i.options.navigatable;l&&(e=typeof l===g&&l.toLowerCase().indexOf("multiple")>-1,e&&i.element.attr("aria-multiselectable",!0),i.selectable=new a.ui.Selectable(i.element,{aria:!0,multiple:e,filter:s,change:function(){i.trigger(n)}}),o&&i.element.on("keydown"+S,function(a){if(a.keyCode===d.SPACEBAR){if(r=i.current(),a.target==a.currentTarget&&a.preventDefault(),e)if(a.ctrlKey){if(r&&r.hasClass(f))return r.removeClass(f),t}else i.selectable.clear();else i.selectable.clear();i.selectable.value(r)}}))},current:function(e){var a=this,n=a.element,r=a._current,i=a._itemId;return e===t?r:(r&&(r[0].id===i&&r.removeAttr("id"),r.removeClass(m),n.removeAttr("aria-activedescendant")),e&&e[0]&&(i=e[0].id||i,a._scrollTo(e[0]),n.attr("aria-activedescendant",i),e.addClass(m).attr("id",i)),a._current=e,t)},_scrollTo:function(t){var a,n,r=this,i=!1,l="scroll";"auto"==r.wrapper.css("overflow")||r.wrapper.css("overflow")==l?a=r.wrapper[0]:(a=window,i=!0),n=function(n,r){var o=i?e(t).offset()[n.toLowerCase()]:t["offset"+n],d=t["client"+r],s=e(a)[l+n](),c=e(a)[r.toLowerCase()]();o+d>s+c?e(a)[l+n](o+d-c):s>o&&e(a)[l+n](o)},n("Top","Height"),n("Left","Width")},_navigatable:function(){var t=this,n=t.options.navigatable,r=t.element,i=function(a){t.current(e(a.currentTarget)),e(a.target).is(":button,a,:input,a>.k-icon,textarea")||r.focus()};n&&(t._tabindex(),r.on("focus"+S,function(){var e=t._current;e&&e.is(":visible")||(e=t._item("first")),t.current(e)}).on("focusout"+S,function(){t._current&&t._current.removeClass(m)}).on("keydown"+S,function(n){var i,l,o=n.keyCode,s=t.current(),c=e(n.target),u=!c.is(":button,textarea,a,a>.t-icon,input"),m=c.is(":text"),f=a.preventDefault,g=r.find("."+p),v=w();if(!(!u&&!m&&d.ESC!=o||m&&d.ESC!=o&&d.ENTER!=o))if(d.UP===o||d.LEFT===o)s&&(s=s.prev()),t.current(s&&s[0]?s:t._item("last")),f(n);else if(d.DOWN===o||d.RIGHT===o)s&&(s=s.next()),t.current(s&&s[0]?s:t._item("first")),f(n);else if(d.PAGEUP===o)t.current(null),t.dataSource.page(t.dataSource.page()-1),f(n);else if(d.PAGEDOWN===o)t.current(null),t.dataSource.page(t.dataSource.page()+1),f(n);else if(d.HOME===o)t.current(t._item("first")),f(n);else if(d.END===o)t.current(t._item("last")),f(n);else if(d.ENTER===o)0!==g.length&&(u||m)?(i=t.items().index(g),v&&v.blur(),t.save(),l=function(){t.element.trigger("focus"),t.current(t.items().eq(i))},t.one("dataBound",l)):""!==t.options.editTemplate&&t.edit(s);else if(d.ESC===o){if(g=r.find("."+p),0===g.length)return;i=t.items().index(g),t.cancel(),t.element.trigger("focus"),t.current(t.items().eq(i))}}),r.on("mousedown"+S+" touchstart"+S,s,E(i,t)))},clearSelection:function(){var e=this;e.selectable.clear(),e.trigger(n)},select:function(a){var n=this,r=n.selectable;return a=e(a),a.length?(r.options.multiple||(r.clear(),a=a.first()),r.value(a),t):r.value()},_destroyEditable:function(){var e=this;e.editable&&(e.editable.destroy(),delete e.editable)},_modelFromElement:function(e){var t=e.attr(a.attr("uid"));return this.dataSource.getByUid(t)},_closeEditable:function(e){var t,n,r=this,i=r.editable,l=r.template,o=!0;return i&&(e&&(o=i.end()),o&&(i.element.index()%2&&(l=r.altTemplate),t=r._modelFromElement(i.element),r._destroyEditable(),n=i.element.index(),i.element.replaceWith(l(t)),r.items().eq(n).attr(a.attr("uid"),t.uid))),o},edit:function(e){var t,n=this,r=n._modelFromElement(e),i=e.index();n.cancel(),e.replaceWith(n.editTemplate(r)),t=n.items().eq(i).addClass(p).attr(a.attr("uid"),r.uid),n.editable=t.kendoEditable({model:r,clearContainer:!1,errorTemplate:!1}).data("kendoEditable"),n.trigger(v,{model:r,item:t})},save:function(){var e,t=this,a=t.editable;a&&(a=a.element,e=t._modelFromElement(a),!t.trigger(_,{model:e,item:a})&&t._closeEditable(!0)&&t.dataSource.sync())},remove:function(e){var t=this,a=t.dataSource,n=t._modelFromElement(e);t.trigger(b,{model:n,item:e})||(e.hide(),a.remove(n),a.sync())},add:function(){var e=this,t=e.dataSource,a=t.indexOf((t.view()||[])[0]);0>a&&(a=0),e.cancel(),t.insert(a,{}),e.edit(e.element.children().first())},cancel:function(){var e,t,a=this,n=a.dataSource;a.editable&&(e=a.editable.element,t=a._modelFromElement(e),a.trigger(r,{model:t,container:e})||(n.cancelChanges(t),a._closeEditable(!1)))},_crudHandlers:function(){var t=this,n=h+S;t.element.on(n,".k-edit-button",function(n){var r=e(this).closest("["+a.attr("uid")+"]");t.edit(r),n.preventDefault()}),t.element.on(n,".k-delete-button",function(n){var r=e(this).closest("["+a.attr("uid")+"]");t.remove(r),n.preventDefault()}),t.element.on(n,".k-update-button",function(e){t.save(),e.preventDefault()}),t.element.on(n,".k-cancel-button",function(e){t.cancel(),e.preventDefault()})},destroy:function(){var e=this;o.fn.destroy.call(e),e._unbindDataSource(),e._destroyEditable(),e.element.off(S),e.pager&&e.pager.destroy(),e.selectable&&e.selectable.destroy(),a.destroy(e.element)}});a.ui.plugin(T)})(window.kendo.jQuery)});