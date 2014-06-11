const HOST = "http://127.0.0.1:8080/hangman/";
const REST_URL_CREATE_GAME = "games/";
const PLAYER = "#playerName";
const JSON_TYPE = "json";
const JSON_PADDING = 4;
const POST = "POST";
const GET = "GET";

$(document).ready(function () {
});

function createGame() {

    var url = HOST + REST_URL_CREATE_GAME + $(PLAYER).val();
    sendPost(url, "", JSON_TYPE);
}
//function sendGet(url, receiveType) {
//    recordRequestParameters(url, GET, receiveType);
//    var content;
//    $.ajax({
//        url: url,
//        type: GET,
//        async: false
//    }).always(function (data, textStatus, params) {
//            clearRequestResponseFields();
//            content = data;
//            if (receiveType == JSON_TYPE) {
//                content = JSON.stringify(data, null, JSON_PADDING);
//            }
//            $(REQUEST).val(url);
//            $(RESPONSE).html(STATUS + params.status + NEW_LINE + STATUS_TEXT + params.statusText + NEW_LINE + content);
//        }
//    );
//    return content;
//}
//function sendPostWithJsonContent(url, dataToSend, receiveType) {
//    recordRequestParameters(url, POST, receiveType);
//    $.ajax({
//        url: url,
//        type: POST,
//        headers: {
//            "Content-Type": APPLICATION_JSON
//        },
//        data: dataToSend,
//        dataType: receiveType,
//        async: false
//    }).always(function (data, textStatus, params) {
//            clearRequestResponseFields();
//            $(REQUEST).val(dataToSend);
//            $(RESPONSE).html(STATUS + params.status + NEW_LINE + STATUS_TEXT + params.statusText + NEW_LINE + JSON.stringify(data, null, JSON_PADDING));
//        }
//    );
//}
function sendPost(url, dataToSend, receiveType) {
    $.ajax({
        url: url,
        type: POST,
        data: dataToSend,
        dataType: receiveType,
        async: false
    }).always(function (data, textStatus, params) {
            JSON.stringify(data, null, JSON_PADDING);
        }
    );
}