<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="t" %>
<!DOCTYPE HTML>
<html>
<head>
    <t:Head title="Upload Document"/>
</head>
<body>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <t:Header title="Chunk Reader" selected="1"/>
    <main class="mdl-layout__content">
        <div id="input_area">
            <t:Card title="Put your passage here:">
                <t:CardText>
                    <t:TextAreaInput id="text" rows="7">
                        Input the passage that you want to read.
                    </t:TextAreaInput>
                </t:CardText>
                <t:CardActions>
                    <t:LinkButton href="#" openInNewTab="false"
                                  onClick="submit()">
                        Submit
                    </t:LinkButton>
                </t:CardActions>
            </t:Card>
        </div>
    </main>
</div>
</body>
</html>
<script>
    function submit() {
        var textVal = $('#text').val();
        $.post("/submit", {text: textVal}, function (data) {
            if (data === "true") {
                alert("You have successfully submitted a document for " +
                    "smart analysis!");
            } else {
                alert("Some error occurs. We don't know what's going on.");
            }
        })
    }
</script>