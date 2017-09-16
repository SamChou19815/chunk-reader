<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="t" %>
<!DOCTYPE HTML>
<html>
<head>
    <t:Head title="Shield - Developer Sam Apps"/>
</head>
<body>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header mdl-layout--fixed-tabs">
    <t:Header title="Shield - Developer Sam Apps" selected="7"/>
    <main class="mdl-layout__content app">
        <div id="input_area">
            <t:Card title="Input Area">
                <t:CardText>
                    <t:TextAreaInput id="en" rows="2">Input the text that you want to encrypt.</t:TextAreaInput>
                    <t:TextAreaInput id="de" rows="2">Input the text that you want to decrypt.</t:TextAreaInput>
                    <t:LineInput id="key" defaultValue="Default Key">A key for encryption/decryption</t:LineInput>
                </t:CardText>
                <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect"
                        style="margin:1em auto" onclick="encrypt();decrypt();">
                    Encrypt/Decrypt
                </button>
            </t:Card>
        </div>
        <div id="output_area">
            <t:Card title="Encrypted Text:">
                <t:CardText id="en_res"/>
            </t:Card>
            <t:Card title="Decrypted Text:">
                <t:CardText id="de_res"/>
            </t:Card>
        </div>
    </main>
</div>
</body>
</html>
<script src="../../framework/cryptoJS/all.js"></script>
<script>
    function encrypt() {
        var encrypted = CryptoJS.AES.encrypt($('#en').val(), $('#key').val());
        $('#en_res').text(encrypted);
    }
    function decrypt() {
        var decrypted = CryptoJS.AES.decrypt($('#de').val(), $('#key').val());
        $('#de_res').text(CryptoJS.enc.Utf8.stringify(decrypted))
    }
</script>
