$(function (){
   hentAlle();
});



function regKunde() {
    const kunde = {
        navn : $("#navn").val(),
        adresse : $("#adresse").val()
    }
    $.get("/lagre",kunde,function(retur){
        hentAlle();
    })
        .fail(function (jqXHR){
           const json = $.parseJSON(jqXHR.responseText);
           $("#feil").html(json.message);
        });
    $("#navn").val(""); //tøm input-feltene
    $("#adresse").val("");

};

function hentAlle() {
    $.get( "/hentAlle", function( data ) {
        formaterData(data);
    });
};

function formaterData(kunder){
    var ut = "<table class='table table-striped'>" +
        "<tr>" +
        "<th>Navn</th><th>Adresse</th>" +
        "</tr>";
    for(let i in kunder ){
        ut+="<tr><td>"+kunder[i].navn+"</td><td>"+kunder[i].adresse+"</td></tr>"
    }
    $("#kundene").html(ut);
}

function slettKundene() {
    $.get( "/slettAlle", function( data ) {
        hentAlle();
    });
};