/**
 * Created with IntelliJ IDEA.
 * User: welcome
 * Date: 11/24/22
 * Time: 4:55 PM
 * To change this template use File | Settings | File Templates.
 */

var decoder;
var trangthaigi = 0;
$(document).ready(function () {
    $("#begin").on('blur', function () {
        var datetime_begin = $("#begin").val();
        var date_begin = (datetime_begin).split("T")[0];
        var time_begin = (datetime_begin).split("T")[1];
        var datetime = (date_begin.replaceAll('-', '')) + "" + (time_begin.replace(':', ''));
        $("#time_begin").val(datetime + "00")
    });
    $("#end").on('blur', function () {
        var datetime_end = $("#end").val();
        var date_end = (datetime_end).split("T")[0];
        var time_end = (datetime_end).split("T")[1];
        var datetime = (date_end.replaceAll('-', '')) + "" + (time_end.replace(':', ''));
        $("#time_end").val(datetime + "00")
    });

    $("#playback").click(function () {
        Xemlai();
    });
});


function Xemlai() {
    var timebegin = $("#time_begin").val();
    var timeend = $("#time_end").val();
    var chooseCam = $("#url2 option:selected").val().trim();
    var url = "ezopen://open.ezviz.com/" + chooseCam + "/1.rec?begin=" + timebegin + "&end=" + timeend + "";
    var accessToken = $('#accessToken').val().trim();
    decoder = new EZUIKit.EZUIPlayer({
        id: 'playWind',
        autoplay: true,
        url: url,
        accessToken: "at.767v340v2h8x2jiqblnb3qvr32b2mekt-6m351ia063-1l7crho-fboj3v8mg",
        decoderPath: '',
        width: 1200,
        height: 800,
        splitBasis: 4,
        env: {
            domain: "https://isgpopen.ezvizlife.com"
        }
    });
    decoder.on('log', log);

    function log(str, className) {
        var div = document.createElement('DIV');
        div.className = className || 'normal';
        div.innerHTML = (new Date()).Format('yyyy-MM-dd hh:mm:ss.S') + JSON.stringify(str);
        document.body.appendChild(div);
    }

    $("#start").click(function () {
        decoder.play({
            //index:[0,1],
            // handleError: handleError,
            // handleSuccess: handleSuccess,
        });
    })

}


$(document).ready(function () {

    MoCam();

   $("#init").click(function () {
     MoCam();
     /* setInterval(checkVideo, 60000);*/
     }) ;
    function MoCam() {
        var url = $("#url2 option:selected").val();
        var accessToken = $('#accessToken').val().trim();
        decoder = new EZUIKit.EZUIPlayer({
            id: 'playWind',
            autoplay: true,
            url: url,
            accessToken: accessToken,
            decoderPath: '../',
            width: 1200,
            height: 800,
            splitBasis: 4,
            env: {
                domain: "https://isgpopen.ezvizlife.com"
            }
        });
         decoder.on('log', log);

        function log(str, className) {
            var div = document.createElement('DIV');
            div.className = className || 'normal';
            div.innerHTML = (new Date()).Format('yyyy-MM-dd hh:mm:ss.S') + JSON.stringify(str);
            document.body.appendChild(div);
        }

        /*$("#start").click(function () {
            decoder.play({
                //index:[0,1],
                // handleError: handleError,
                // handleSuccess: handleSuccess,
            });
        })*/

    }

    $("#stop").click(function () {
        var url = $('#url2').val().trim();
        var urlList = [];
        if (url.length > 0) {
            urlList = url.split(',');
        }
        for (var i = 0; i
            < urlList.length; i++) {
            if (urlList[i] && urlList[i].length > 0) {
                decoder.stop(i);
            }
        }
    });
    /*$("#capturePicture").click(function () {
     */
    /*screenshot method 1*/
    /*
     // decoder.capturePicture(0,'default');
     */
    /*screenshot method 2*/
    /*
     var capturePicturePromise = decoder.capturePicture(0,'default');
     capturePicturePromise.then(function(data){
     console.log("Screenshot success",data);
     })
     })*/

});
$(document).ready(function () {
    $.ajax({
        type: "POST",
        url: "/checkCamera",
        data: {
            appKey:"291da54b31034d1685d7661726fa2de5",
            appSecret:"b45f12dd0c474f9cbe1bdb6365c58ee2"

        },
        success: function (data) {

                console.log("log : "+ data)
                $("#url2").empty();
                $("#url2").append(data);
            }

    });
    $('body').on('change', '#url2', function () {

        stop();


    });
    $("#capturePicture").click(function () {
        /* console.log("Screenshot success",decoder.capturePicture());
         var capturePicturePromise = decoder.capturePicture(0,'default');
         capturePicturePromise.then(function(data){
         console.log("Screenshot success",data);*/

            html2canvas(document.querySelector('#testcam'), {
                onrendered: function (canvas) {
                    // document.body.appendChild(canvas);
                    return Canvas2Image.saveAsPNG(canvas);
                }
            });

    })

    /*$("#startSave").click(function () {
        console.log(" date start :" + new Date().getTime());
        trangthaigi = 1;
        var startSavePromise = decoder.startSave(0, (new Date().getTime() + 'video'));
        startSavePromise.then(function (data) {
            console.log("start save success", startSavePromise)
            console.log(" date2 :" + new Date().getTime() + startSavePromise );
        })
            .catch(function (error) {
                console.log("start Save error", error)
            })
    });
    $("#stopSave").click(function () {

        console.log(" date end :" + new Date().getTime());
        var stopSavePromise = decoder.stopSave(0);
        stopSavePromise.then(function (data) {
            console.log("stop save success", stopSavePromise)
            startSave();
        })
            .catch(function (error) {
                console.log("stop Save error", error)
            })
    });*/
});

function  startSave(){
    console.log(" date start :" + new Date().getTime());
           trangthaigi = 1;
    var startSavePromise = decoder.startSave(0, (new Date().getTime() + 'video'));
    startSavePromise.then(function (data) {
        console.log("start save success", startSavePromise)
        console.log(" date2 :" + new Date().getTime() + startSavePromise );
    })
        .catch(function (error) {
            console.log("start Save error", error)
        })
}
function stopSave(){
    console.log(" date end :" + new Date().getTime());
    var stopSavePromise = decoder.stopSave(0);
    stopSavePromise.then(function (data) {
        console.log("stop save success", stopSavePromise)
        startSave();
    })
        .catch(function (error) {
            console.log("stop Save error", error)
        })
}
function checkVideo(){
    if(trangthaigi == 0){
        startSave();

    }
    else if( trangthaigi == 1){
         stopSave();

    }
}
function stop() {
    var url = $('#url2').val().trim();
    var urlList = [];
    if (url.length > 0) {
        urlList = url.split(',');
    }
    for (var i = 0; i < urlList.length; i++) {
        if (urlList[i] && urlList[i].length > 0) {
            decoder.stop(i);
        }
    }

}


