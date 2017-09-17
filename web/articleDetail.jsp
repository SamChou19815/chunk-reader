<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="t" %>
<!DOCTYPE HTML>
<html>
<head>
    <t:Head title="Upload"/>
</head>
<body>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <t:Header title="Chunk Reader" selected="0"/>
    <main class="mdl-layout__content">
        <t:Card title="Details of article xxxx">
            <t:CardText>
                We predict that the author thinks the opinion is <%-- data from Util.java.getSentimentScore().
                Conversion from number 1-6 to words[strongly/weakly+positive/neutral/negative].--%>.
            </t:CardText>
            <t:CardText>
                <div>You should know these things:</div>
                <%--Top k keywords--%>
                <div><%-- Fetch keyword name--%>:A term about <%-- Fetch keyword type--%>.
                        <%-- Fetch URL, if there is one:See <a href="...">url</a>.--%></div>

                <%--examples--%>
                <div>Arithmetic:
                    <a href="https://en.wikipedia.org/wiki/Principia_Mathematica">1+1=2</a>
                </div>
                <div>Organization: <a href="google.com">Google</a></div>

            </t:CardText>
            <t:CardText>
                Summary: show more show less<br>
                This is a test. This is another test.
            </t:CardText>
            <t:CardActions>
                <t:LinkButton href="#" openInNewTab="false">Show full text.</t:LinkButton>
            </t:CardActions>
        </t:Card>

        <t:Card title="Type Prediction">
            <t:CardText>
                We predict that the author thinks the opinion is BS.
            </t:CardText>
        </t:Card>
        <t:Card title="Knowledge Graph">
            <t:CardText>
                <div>You should know these things:</div>
                <div>Arithmetic:
                    <a href="https://en.wikipedia.org/wiki/Principia_Mathematica">1+1=2</a>
                </div>
                <div>Organization: <a href="google.com">Google</a></div>
            </t:CardText>
        </t:Card>
        <t:Card title="Summary">
            <t:CardText>
                Summary: show more show less<br>
                This is a test. This is another test.
            </t:CardText>
            <t:CardActions>
                <t:LinkButton href="#" openInNewTab="false">
                    Show More
                </t:LinkButton>
                <t:LinkButton href="#" openInNewTab="false">
                    Show Less
                </t:LinkButton>
            </t:CardActions>
        </t:Card>
    </main>
</div>
</body>
</html>
