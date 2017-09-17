<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/chunkReaderCustom.tld" prefix="CRT" %>
<!DOCTYPE HTML>
<html>
<head>
    <t:Head title="Article"/>
</head>
<body>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <t:Header title="Chunk Reader" selected="0"/>
    <main class="mdl-layout__content">
        <c:forEach items="${requestScope.shortTexts}" var="shortText">
            <CRT:ShortTextCard shortText="${shortText}" />
        </c:forEach>
    </main>
</div>
</body>
</html>
