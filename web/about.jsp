<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="t" %>
<!DOCTYPE HTML>
<html>
<head>
    <t:Head title="About"/>
</head>
<body>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <t:Header title="Chunk Reader" selected="2"/>
    <main class="mdl-layout__content">
        <div id="input_area">
            <t:Card title="About">
                <t:CardText>
                    This web-app is intended to help anyone who has trouble
                    comprehending the text or finding the main ideas of the
                    text, especially non-native speakers and people with lower
                    education level, and researchers. <br>

                    With the app, you can: <br>

                    <ul>
                        <li>find author’s main attitude towards the topic in
                            the text</li>
                        <li>know the keyword and have some background
                            information for the text</li>
                        <li>get a list of important sentences in the text</li>
                    </ul>
                </t:CardText>
            </t:Card>
        </div>
    </main>
</div>
</body>
</html>
