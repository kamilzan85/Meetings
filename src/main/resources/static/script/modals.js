$(document).ready(function(){
    $('.modal').modal({
        onCloseStart: function(){
            $('#description').val(tinyMCE.activeEditor.getContent({format : 'text'}));
        },
    });
});

function openModal(){
    var elem = document.getElementById('modal1');
    var instance = M.Modal.getInstance(elem);
    instance.open();
}