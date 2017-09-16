var Viewer = {
    renderOneSquare: function (i, j, type, id) {
        var colorBlack = "black", colorWhite = "white", colorMountain = "#795548";
        var color = "#DDDDDD"; // empty color
        var obj = $("#square-"+i+"-"+j).css("border", "2px solid #CCC");
        if (type === 1) {
            color = (id === 1)? colorBlack: colorWhite;
            var textColorForBlack = "#CCC", textColorForWhite = "#333";
            var textColor = (id === 1)? textColorForBlack: textColorForWhite;
            obj.css("color", textColor);
        }else if (type === 2) {
            color = colorMountain;
        }
        obj.css("background", color);
    },
    displayInfoForCity: function (i, j, wall, civilian, soldiers) {
        $("#square-"+i+"-"+j).html("Wall: " + wall + "<br>Civilian: " + civilian + "<br>Soldiers: " + soldiers);
    },
    renderActionForOneSquare: function (i, j, actionName, arg1, arg2) {
        var obj = $("#square-"+i+"-"+j);
        if (actionName === "grow") {
            obj.css("border", "2px solid #4caf50"); // green for grow
            obj.append("<br><br>grow");
        }else if (actionName === "draft") {
            obj.css("border", "2px solid #ff9800"); // orange for draft
            obj.append("<br><br>draft[" + arg1 + "]");
        }else if (actionName === "build") {
            obj.css("border", "2px solid #3f51b5"); // indigo for build
            obj.append("<br><br>build");
        }else if (actionName === "occupy") {
            obj.css("border", "2px solid #607d8b"); // blue grey for occupy
            obj.append("<br><br>occupy[" + arg1 + "]");
        }else if (actionName === "migrate") {
            obj.css("border", "2px solid #03a9f4"); // blue for migrate
            obj.append("<br><br>migrate[" + arg1 + "]");
        }else if (actionName === "attack") {
            obj.css("border", "2px solid #f44336"); // red for attack
            obj.append("<br><br>attack[" + arg1 + "," + arg2 + "]");

        }
    }
};