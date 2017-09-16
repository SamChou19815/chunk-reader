var Controller = {
    submitPrograms: function () {
        var blackProgram = $('#blackProgram').val(), whiteProgram = $('#whiteProgram').val();
        $.post("loadEntireGame", {blackProgram: blackProgram, whiteProgram: whiteProgram}, function(data) {
            if (data === "Syntax Error in your Programs!") {
                $('#status').html(data);
            }else{
                allGameStates = new AllGameStatesObject(JSON.parse(data));
                $('#status').html("Game results loaded.");
                $('#inputs').hide();
                $('#button_add_programs').hide();
                $('#button_next').show();
                $('#button_see_result').show();
                $('#game').css("display", "flex");
                turnNumber = 0;
            }
        });
    },
    addPrograms: function() {
        $('#inputs').show();
    },
    next: function() {
        if (turnNumber === allGameStates.length) {
            this.showResult();
        }else {
            allGameStates.getTurnByTurnNumber(turnNumber).render();
            turnNumber++;
        }
    },
    showResult: function() {
        allGameStates.renderFinalSituation();
        var winner = allGameStates.getWinner();
        winner = winner === 1 ? "Black": "White";
        $('#status').html(winner + " wins.");
    },
    reset: function () {
        $('#blackProgram').val("");
        $('#whiteProgram').val("");
        $('#inputs').hide();
        $('#game').hide();
        $('.square').css("background", "#DDDDDD").css("border", "2px solid #CCC").html("");
        $('#status').html("Game");
        $('#button_add_programs').show();
        $('#button_next').hide();
        $('#button_see_result').hide();
    }
};