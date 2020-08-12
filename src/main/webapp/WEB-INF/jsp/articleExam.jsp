<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<input type="hidden" id="articleId" value="${articleId}">
<span>是否通过：</span>
<select id="ispass-select" data-am-selected>
    <option value="1" selected>通过</option>
    <option value="-1">不通过</option>
</select>
<div id="reason-div">
    <span>理由:</span>
    <input id="reason" type="text"/>
</div>