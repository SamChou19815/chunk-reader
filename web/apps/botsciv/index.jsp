<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.developersam.web.model.statistics.UserStatistics" %>
<%
    // Add usage userStatistics to database.
    UserService userService = UserServiceFactory.getUserService();
    User currentUser = userService.getCurrentUser();
    UserStatistics userStatistics = new UserStatistics("botsciv");
    userStatistics.usagePlusOne(currentUser);
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="t" %>
<!DOCTYPE HTML>
<html>
<head>
    <t:Head title="Bots Civ - Developer Sam Apps"/>
    <style>
        #gameCard {
            min-width: 660px;
        }

        #game {
            display: flex;
            justify-content: center;
        }

        #grids {
            display: flex;
            flex-wrap: wrap;
            align-items: center;
            position: relative;
            width: 600px;
            height: 600px;
        }

        .square {
            width: 90px;
            height: 90px;
            margin: 8px;
            background: #DDDDDD;
            border: 2px solid #CCC;
            padding: 4px;
        }
    </style>
    <script src="js/BotsCivController.js"></script>
    <script src="js/BotsCivViewer.js"></script>
    <script src="js/BotsCivGameStatesObjects.js"></script>
    <script>
        <%! int boardWidthHeight = 5; %>
        var boardWidthHeight = <%= boardWidthHeight%>;
        var allGameStates;
        var turnNumber;
    </script>
</head>
<body>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header mdl-layout--fixed-tabs">
    <t:Header title="Bots Civ - Developer Sam Apps" selected="6"/>
    <main class="mdl-layout__content app">
        <div class="mdl-card mdl-shadow--2dp" id="gameCard">
            <div class="mdl-card__title">
                <h2 class="mdl-card__title-text" id="status">Game</h2>
            </div>
            <div class="mdl-card__supporting-text mdl-card--border" id="game" style="display: none">
                <div id="grids">
                    <%
                        for (byte i = 0; i < boardWidthHeight; i++) {
                            for (byte j = 0; j < boardWidthHeight; j++) {
                                String sID = "square-" + i + "-" + j;
                    %>
                    <div class="square" id="<%= sID %>"></div>
                    <%
                            }
                        }
                    %>
                </div>
            </div>
            <div class="mdl-card__supporting-text mdl-card--border" id="inputs" style="display: none;">
                <t:TextAreaInput id="blackProgram" rows="5">Input civ program for Black.</t:TextAreaInput>
                <t:TextAreaInput id="whiteProgram" rows="5">Input civ program for White.</t:TextAreaInput>
                <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect"
                        style="margin:1em auto" onclick="Controller.submitPrograms();">
                    Submit Programs
                </button>
            </div>
            <div class="mdl-card__actions mdl-card--border">
                <a href="#" id="button_add_programs" onclick="Controller.addPrograms();"
                   class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Add programs</a>
                <a href="#" id="button_next" style="display: none;" onclick="Controller.next();"
                   class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Next step</a>
                <a href="#" id="button_see_result" style="display: none;" onclick="Controller.showResult();"
                   class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">View Result</a>
                <a href="#" id="button_reset" onclick="Controller.reset();"
                   class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Reset</a>
            </div>
        </div>
        <t:Card title="Help">
            <t:CardText>
                See rules <a href="https://github.com/SamChou19815/Bots-Civ" target=_blank>here</a>.
            </t:CardText>
            <t:CardText>
                A black tile means a city belongs to Black.<br>
                A white tile means a city belongs to White.<br>
                A brown tile means a mountain tile.<br>
                A grey tile means an empty tile.<br>
                A city tile with green border means it performs grow action.<br>
                A city tile with orange border means it performs draft action.<br>
                A city tile with indigo border means it performs build action.<br>
                A city tile with blue-grey border means it performs occupy action.<br>
                A city tile with blue border means it performs migrate action.<br>
                A city tile with red border means it performs attack action.
            </t:CardText>
        </t:Card>
    </main>
</div>
</body>
</html>
<script>
</script>
