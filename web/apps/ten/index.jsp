<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.developersam.web.model.statistics.UserStatistics" %>
<%
    // Add usage userStatistics to database.
    UserService userService = UserServiceFactory.getUserService();
    User currentUser = userService.getCurrentUser();
    UserStatistics userStatistics = new UserStatistics("ten");
    userStatistics.usagePlusOne(currentUser);
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="t" %>
<!DOCTYPE HTML>
<html>
<head>
    <t:Head title="TEN - Developer Sam Apps"/>
    <style>
        #gameCard {
            min-width: 460px;
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
            width: 300px;
            height: 300px;
        }

        .grids-vertical-line {
            background: #333;
            width: 1px;
            height: 300px;
            position: absolute;
            top: 0;
        }

        .grids-horizontal-line {
            background: #333;
            width: 300px;
            height: 1px;
            position: absolute;
            left: 0;
        }

        .square {
            width: 23px;
            height: 23px;
            margin: 4px;
            cursor: pointer;
            background: #DDDDDD;
            border: 1px solid #CCC;
        }

        #controllers {
            display: flex;
            flex-wrap: wrap;
            margin-left: 1em;
            align-content: center;
            width: 100px;
        }
    </style>
    <script src="js/TenController.js"></script>
    <script src="js/TenViewer.js"></script>
    <script src="js/TenUtil.js"></script>
    <script>
        var board = new Array(9);
        var currentBigSquareLegalPosition = -1;
        var currentPlayerIdentity = 1;
        Controller.initializeBoard();
        var viewer = new Viewer(board);
    </script>
</head>
<body>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header mdl-layout--fixed-tabs">
    <t:Header title="TEN - Developer Sam Apps" selected="5"/>
    <main class="mdl-layout__content app">
        <div class="mdl-card mdl-shadow--2dp" id="gameCard">
            <div class="mdl-card__title">
                <h2 class="mdl-card__title-text" id="status">Game</h2>
            </div>
            <div class="mdl-card__title">
                <h2 class="mdl-card__title-text">
                    AI Winning Probability:&nbsp;<span id="aiWinningProbability">50</span>%
                </h2>
            </div>
            <div class="mdl-card__supporting-text" id="game">
                <div id="grids">
                    <%
                        for (byte i = 0; i < 9; i++) {
                            for (byte j = 0; j < 9; j++) {
                                int a = (i / 3) * 3 + (j / 3), b = i % 3 * 3 + j % 3;
                                String sID = "square-" + a + "-" + b;
                    %>
                    <div class="square" id="<%= sID %>" onclick="submit(<%=a %>, <%= b %>)"></div>
                    <%
                            }
                        }
                    %>
                    <div class="grids-vertical-line" style="left: 99px"></div>
                    <div class="grids-vertical-line" style="left: 198px"></div>
                    <div class="grids-horizontal-line" style="top: 99px"></div>
                    <div class="grids-horizontal-line" style="top: 199px"></div>
                </div>
                <div id="controllers">
                    <h6 style="margin: 1em auto;">New Game</h6>
                    <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect"
                            style="margin:1em auto" onclick="Controller.selectSide(1)">
                        Black
                    </button>
                    <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect"
                            style="margin:1em auto" onclick="Controller.selectSide(-1)">
                        White
                    </button>
                </div>
            </div>
        </div>
        <t:Card title="Rule of the Game">
            <t:CardText>
                <div>The main rules are described
                    <a href="https://mathwithbaddrawings.com/2013/06/16/ultimate-tic-tac-toe"
                       target=_blank>here</a>.
                </div>
                <div>Additional rules are described
                    <a href="https://github.com/SamChou19815/TEN-Board-Game#clarification-of-rules"
                       target=_blank>here</a>.
                </div>
                <div>The thinking time for AI is around 1.5s.</div>
            </t:CardText>
        </t:Card>
    </main>
</div>
</body>
</html>
<script>
    function submit(a, b) {
        // translate position representation
        var clientInfo = Util.generateClientInfo(board, currentBigSquareLegalPosition,
            currentPlayerIdentity, a, b);
        var tempState = board[a][b];
        board[a][b] = currentPlayerIdentity;
        viewer.renderBoard();
        Viewer.message("Waiting for server response...");
        $.post("response", {clientInfo: clientInfo}, function (data) {
            data = data.split(",");
            var aiA = parseInt(data[0]), aiB = parseInt(data[1]),
                currentBigSqrLegalPos = parseInt(data[2]), info = parseInt(data[3]),
                aiWinningProbability = parseInt(data[4]);
            if (info === 1) {
                // Black wins
                if (!(aiA === 1 && aiB === -1)) {
                    // Player does not win.
                    board[aiA][aiB] = currentPlayerIdentity * (-1);
                    viewer.renderBoard();
                }
                Viewer.message("Black wins!");
                Viewer.showControl();
            } else if (info === -1) {
                // White wins
                if (!(aiA === -1 && aiB === 1)) {
                    // Player does not win.
                    board[aiA][aiB] = currentPlayerIdentity * (-1);
                    viewer.renderBoard();
                }
                Viewer.message("White wins!");
                Viewer.showControl();
            } else if (info === 2) {
                // Illegal move
                board[a][b] = tempState;
                viewer.renderOneSquare(a, b);
                Viewer.message("Illegal move!");
            } else {
                // Game continues.
                currentBigSquareLegalPosition = currentBigSqrLegalPos;
                // keep track of legal big square position
                board[aiA][aiB] = currentPlayerIdentity * (-1);
                viewer.renderBoard();
                $('#square-' + aiA + "-" + aiB).css("border", "1px solid red"); // highlight AI's move
                Viewer.message("Select your next move.");
                Viewer.updateAIWinningProbability(aiWinningProbability);
            }
        });
    }
</script>
