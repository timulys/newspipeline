var origin = "http://localhost:8080/news/";
var ecoTimerID;
var landTimerID;

function ecoStart() {
    ecoSearch();
}

function ecoStop() {
    clearTimeout(ecoTimerID);
    $("#contentEcoBody").empty();
}

function ecoSearch() {
    var url = origin + "eco-all";
    $.ajax({
        url: url,
        type: 'GET'
    }).then(newsList => {
        $("#contentEcoBody").empty();
        $("#contentEcoBody").append();
        $.each(newsList, function(index, news) {
            $("#contentEcoBody").append((index + 1) + ". " + news.title);
            $("#contentEcoBody").append(" <a href='" + news.url + "' target='_blank'>[" + news.newsId + "]</a>");
            $("#contentEcoBody").append("<br/>");
        });
    })
    ecoTimerID = setTimeout("ecoSearch()", 10000);// 10초 단위로 갱신 처리
}

function landStart() {
    landSearch();
}

function landStop() {
    clearTimeout(landTimerID);
    $("#contentLandBody").empty();
}

function landSearch() {
    var url = origin + "land-all";
    $.ajax({
        url: url,
        type: 'GET'
    }).then(newsList => {
        $("#contentLandBody").empty();
        $("#contentLandBody").append();
        $.each(newsList, function(index, news) {
            $("#contentLandBody").append((index + 1) + ". " + news.title);
            $("#contentLandBody").append(" <a href='" + news.url + "' target='_blank'>[" + news.newsId + "]</a>");
            $("#contentLandBody").append("<br/>");
        });
    })
    landTimerID = setTimeout("ecoSearch()", 10000);// 10초 단위로 갱신 처리
}