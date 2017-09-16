<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="t" %>
<!DOCTYPE HTML>
<html>
<head>
    <t:Head title="Developer Sam"/>
</head>
<body>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <t:Header title="Developer Sam" selected="0"/>
    <main class="mdl-layout__content">
        <t:Card title="Developer">
            <t:CardText>Specialized in machine learning and web development.</t:CardText>
        </t:Card>
        <t:Card title="Researcher">
            <t:CardText>Researched in machine learning and artificial intelligence.</t:CardText>
        </t:Card>
        <t:Card title="Student">
            <t:CardText>Major in computer science at Cornell University.</t:CardText>
        </t:Card>
        <t:Card title="Know more about Sam">
            <t:CardText>Specialized in machine learning and web development.</t:CardText>
            <t:CardActions>
                <t:LinkButton href="resource/docs/Resume.pdf" openInNewTab="true">My Resume</t:LinkButton>
            </t:CardActions>
        </t:Card>
    </main>
</div>
</body>
</html>
