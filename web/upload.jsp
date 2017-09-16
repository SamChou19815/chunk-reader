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
        <div id="input_area">
            <t:Card title="Put your passage here:">
                <t:CardText>
                    <t:TextAreaInput id="en" rows="2">Input the passage that you want to read.</t:TextAreaInput>
                </t:CardText>
                <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect"
                        style="margin:1em auto" onclick="<%-- JS function that sends the input into datastore--%>">
                    Submit
                </button>
            </t:Card>
        </div>
    </main>
</div>
</body>
</html>
