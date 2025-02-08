/**
 * Created with IntelliJ IDEA.
 * User: welcome
 * Date: 11/22/22
 * Time: 2:23 PM
 * To change this template use File | Settings | File Templates.
 */

var decoder;
$(document).ready(function(){

    var url1 = $( "#url542").val().trim();
    var accessToken1 = "at.366lv3tr8k9ckms2b7u3bd1zba9pimvh-8hfpk7rjtv-1bv57cv-9u07bnhyk";
    decoder = new EZUIKit.EZUIPlayer({
        id: 'playWind1',
        autoplay: true,
        url: url1,
        accessToken: accessToken1,
        decoderPath: '',
        width: 300,
        height: 200,
        splitBasis: 5,
        env:{
            domain: "https://isgpopen.ezvizlife.com"
        }
    }) ;
    decoder.on('log', log);

    function log(str,className) {
        var div = document.createElement('DIV');
        div.className=className || 'normal';
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

}) ;