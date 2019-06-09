jQuery(document).ready(function($){
    PopUpHide();
    $( "#loginform" ).submit(function( event ) {
        if($('#username').val()==="admin" && $('#password').val()==="admin"){
            return;
    }else{
    PopUpShow();
    event.preventDefault();
    }
    });
    $(".b-popup").click(function(){
        PopUpHide();
    })
})


function PopUpShow(){
              $("#popup1").show();
          }

function PopUpHide(){
        $("#popup1").hide();
  }

