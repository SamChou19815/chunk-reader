function Controller(){}
Controller.initializeBoard = function () {
    for (var i = 0; i < 9; i++) {
        var square3x3 = new Array(9);
        for (var j = 0; j < 9; j++) {
            square3x3[j] = 0;
        }
        board[i] = square3x3;
    }
    currentBigSquareLegalPosition = -1; // Black can choose any position initially
};
Controller.selectSide = function (side) {
    currentPlayerIdentity = side;
    $("#controllers").hide();
    Controller.initializeBoard();
    Viewer.updateAIWinningProbability(50);
    if (side === -1) {
        board[4][4] = 1; // hard code the first black move
        currentBigSquareLegalPosition = 4;
        Viewer.message("Select your next move.");
    }
    viewer.renderBoard();
};