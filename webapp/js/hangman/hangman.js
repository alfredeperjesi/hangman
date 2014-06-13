const HOST = "http://localhost:8080/hangman/rest/";
const GAMES = "games";
const SLASH = "/";
const PLAYER_COOKIE = "playerName";
const PLAYER = "#playerName";
const GUESS_LETTER = "#guessLetter";
const ACTUAL_WORD = "#actualWord";
const MISSED_LETTERS = "#missedLetters";
const MISSED_LETTER_COUNT = "#missedLetterCount";
const GUESS_BUTTON = "#guess";
const IMAGE = "#image";
const JSON_TYPE = "json";
const JSON_PADDING = 4;
const POST = "POST";
const GET = "GET";

$(document).ready(function () {
    restoreState();
});

function restoreState() {
    var playerName = $.cookie(PLAYER_COOKIE);
    $(PLAYER).val(playerName);
    if(playerName) {
        getGame();
    }
}

function successHandler(data, textStatus, xhr) {
    updateView(data);
    validateGameState();
}

function updateView(data) {
    $(ACTUAL_WORD).val(data.actualWord);
    $(MISSED_LETTERS).val(data.missedLetters);
    $(MISSED_LETTER_COUNT).val(data.missedLetterCount);
    $(IMAGE).attr("src", "images/hang" + data.missedLetterCount + ".gif");
}

function validateGameState() {
    if(isLost()) {
        alert("Game over! You Lost! The word is " + $(ACTUAL_WORD).val());
        disableGuessButton();
    } else if(isWin()) {
        alert("Congratulation! You win! The word is " + $(ACTUAL_WORD).val());
        disableGuessButton();
    }
}

function disableGuessButton() {
    $(GUESS_BUTTON).attr("disabled", "disabled");
}

function enableGuessButton() {
    $(GUESS_BUTTON).attr("disabled", "false");
}

function isWin() {
    return $(ACTUAL_WORD).val().indexOf("_") == -1;
}

function isLost() {
    return $(MISSED_LETTER_COUNT).val() == 10;
}

function errorHandler(data, textStatus, xhr) {
    alert(JSON.stringify(data, null, JSON_PADDING));
}

function guessLetter() {
    var guessLetter = $(GUESS_LETTER).val();
    if(isLetterAllowed()) {
        var url = HOST + GAMES + SLASH + $(PLAYER).val() + SLASH + guessLetter;
        $(GUESS_LETTER).val("");
        sendPost(url);
    } else {
        alert(guessLetter + " is not allowed! (it has already been guessed or not alphabetic or not 1 character length");
    }
}

function isLetterAllowed() {
    return isLengthOne() && hasNotBeenGuessed() && isAlphabeticOnly();
}

function isLengthOne() {
    return $(GUESS_LETTER).val().length == 1;
}

function hasNotBeenGuessed() {
    return $(MISSED_LETTERS).val().indexOf($(GUESS_LETTER).val()) == -1 && $(ACTUAL_WORD).val().indexOf($(GUESS_LETTER).val()) == -1;
}

function isAlphabeticOnly() {
    return $(GUESS_LETTER).val().match(/^[a-zA-Z]$/);
}

function createGame() {
    $.cookie(PLAYER_COOKIE, $(PLAYER).val());
    var url = HOST + GAMES + SLASH + $(PLAYER).val();
    enableGuessButton();
    sendPost(url);
}

function getGame() {
    var url = HOST + GAMES + SLASH + $(PLAYER).val();
    sendGet(url);
}

function successManagementHandler(data, textStatus, xhr) {
    alert(JSON.stringify(data, null, JSON_PADDING));
}

function gotoManagement() {
    var url = HOST + GAMES;
    sendGet(url, successManagementHandler);
}

function sendGet(url, success) {
    $.ajax({
        url: url,
        type: GET,
        async: false,
        success: success || successHandler,
        error : errorHandler
    });
}

function sendPost(url) {
    $.ajax({
        url: url,
        type: POST,
        data: "",
        async: false,
        success: successHandler,
        error : errorHandler
    });
}