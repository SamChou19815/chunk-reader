function ActionObject(actionJSON) {
    this.render = function () {
        Viewer.renderActionForOneSquare(actionJSON[0], actionJSON[1], actionJSON[2], actionJSON[3], actionJSON[4]);
    }
}
function ActionsObject(actionsJSON) {
    var actions = new Array(actionsJSON.length);
    for (var i = 0; i < actions.length; i++) {
        actions[i] = new ActionObject(actionsJSON[i]);
    }
    this.render = function () {
        for (var i = 0; i < actions.length; i++) {
            actions[i].render();
        }
    }
}
function TileObject(i, j, tileJSON) {
    this.displayInfo = function () {
        Viewer.displayInfoForCity(i, j, tileJSON[2], tileJSON[3], tileJSON[4]);
    };
    this.render = function () {
        var type = tileJSON[0];
        var id = 0;
        if (type === 1) {
            id = tileJSON[1];
            this.displayInfo();
        }
        Viewer.renderOneSquare(i, j, type, id);
    };
}
function TilesObject(tilesJSON) {
    this.tiles = new Array(boardWidthHeight);
    for (var i = 0; i < boardWidthHeight; i++) {
        this.tiles[i] = new Array(boardWidthHeight);
        for (var j = 0; j < boardWidthHeight; j++) {
            this.tiles[i][j] = new TileObject(i, j, tilesJSON[i][j]);
        }
    }
    this.render = function () {
        for (var i = 0; i < boardWidthHeight; i++) {
            for (var j = 0; j < boardWidthHeight; j++) {
                this.tiles[i][j].render();
            }
        }
    }
}
function OneTurnObject(oneTurnJSON) {
    var tiles = new TilesObject(oneTurnJSON[1]);
    var actions = new ActionsObject(oneTurnJSON[0]);
    this.render = function () {
        tiles.render();
        actions.render();
    }
}
function AllGameStatesObject(allGameStatesJSON) {
    this.length = allGameStatesJSON[2].length;
    this.getWinner = function() {
        return allGameStatesJSON[0];
    };
    this.renderFinalSituation = function () {
        var finalTiles = new TilesObject(allGameStatesJSON[1]);
        finalTiles.render();
    };
    this.getTurnByTurnNumber = function (turnNumber) {
        return new OneTurnObject(allGameStatesJSON[2][turnNumber]);
    };
    this.getLastTurn = function () {
        return this.getTurnByTurnNumber(allGameStatesJSON[2].length-1);
    }
}