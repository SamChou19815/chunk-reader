function Util() {}
Util.boardToString = function (aBoard) {
    var str = "";
    for (var i = 0; i < 9; i++) {
        for (var j = 0; j < 9; j++) {
            str += aBoard[i][j] + ",";
        }
        str = str.substr(0, str.length - 1);
        str += ";";
    }
    str = str.substr(0, str.length - 1);
    return str;
};
Util.generateClientInfo = function (aBoard, bCurrentBigSquareLegalPosition, bCurrentPlayerIdentity, mA, mB) {
    return Util.boardToString(aBoard) + " " + bCurrentBigSquareLegalPosition + " " +
    (bCurrentPlayerIdentity * (-1)) + " " + mA + "," + mB;
};