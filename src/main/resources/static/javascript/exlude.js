jQuery(document).ready(function($){
    //dialogHide();

    $("#exclude").click(function() {
    $(".dialog").css("visibility","visible");
    event.preventDefault();
    });

    $(".dialog").click(function(){
        dialogHide();
    });

    var href = $('[name=exludeHref]').attr('href');
    $("#exludeHref").attr({
        "href":href
    });

    $("#ok").click(function(){
        dialogHide();
    });
})


/*function dialogShow(){
              $("#diaWind").show();
          }
*/
function dialogHide(){
        $("#diaWind").hide();
  }
