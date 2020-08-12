<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="myRelease-list">
    <ul class="am-list am-list-static">
        <c:forEach var="course" items="${courses}">
            <li>
                <div class="am-g">
                    <div class="am-u-sm-10">
                        <a onclick="viewCourse(${course.id})">${course.name}</a>
                    </div>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
<script>
    function viewCourse (courseId) {
        document.location.href = "${pageContext.request.contextPath}/article/showCourse?courseId=" + courseId;
    }
</script>
