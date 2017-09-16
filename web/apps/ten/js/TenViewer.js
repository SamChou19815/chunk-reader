function Viewer(vBoard){
    this.renderOneSquare = function (a, b) {
        var content = vBoard[a][b];
        var color = "#DDDDDD";
        if (content !== 0) {
            color = (content === 1) ? "black": "white";
        }
        $("#square-"+a+"-"+b).css("background", color).css("border", "1px solid #CCC");
    };
    this.renderBoard = function () {
        for (var a = 0; a < 9; a++) {
            for (var b = 0; b < 9; b++) {
                this.renderOneSquare(a,b);
            }
        }
    };
}
Viewer.message = function (msg) {
    $("#status").html(msg);
};
Viewer.showControl = function () {
    $("#controllers").show();
};
Viewer.updateAIWinningProbability = function (prob) {
    $("#aiWinningProbability").html(prob);
};