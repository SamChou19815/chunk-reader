<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="t" %>
<%@ taglib uri="/WEB-INF/chunkReaderCustom.tld" prefix="CRT" %>
<!DOCTYPE HTML>
<html>
<head>
    <t:Head title="Upload"/>
</head>
<body>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <t:Header title="Chunk Reader" selected="0"/>
    <main class="mdl-layout__content">
        <CRT:PredictionCard textDataStore="${requestScope.textData}" />
        <CRT:KnowledgeGraphCard textDataStore="${requestScope.textData}" />
        <CRT:SummaryCard textDataStore="${requestScope.textData}" />
    </main>
</div>
</body>
</html>
