<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="t" %>
<!DOCTYPE HTML>
<html>
<head>
    <t:Head title="Article"/>
</head>
<body>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <t:Header title="Chunk Reader" selected="0"/>
    <main class="mdl-layout__content">
        <%--Access date of the article--%>
        <t:Card title="Article uploaded on Sep 1">
            <t:CardText>Four score and seven years ago, ...<%--Access text in the datastore--%></t:CardText>
            <t:CardActions>
                <t:LinkButton href="#" openInNewTab="true">
                    Read more
                </t:LinkButton>
            </t:CardActions>
        </t:Card>
        <%--Access sorting with date, older dates on the bottom.--%>
        <t:Card title="Article uploaded on Aug 28">
            <t:CardText>Fifty-six years ago, ...</t:CardText>
            <t:CardActions>
                <t:LinkButton href="#" openInNewTab="true">
                    Read more
                </t:LinkButton>
            </t:CardActions>
        </t:Card>

        <%--More cards needed, depending on the number of articles. Maybe put a max value around 10.--%>

    </main>
</div>
</body>
</html>
