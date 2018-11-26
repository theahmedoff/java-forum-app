$(function () {
    getCommentsByTopicId();
    $('#idButtonReply').click(function () {
        addReply();
    });

});
function getCommentsByTopicId () {
    var id = $('#idInputTopic').val();
    $.ajax({
        url: '/ts?action=getCommentsByTopicId',
        type: 'GET',
        data: 'id=' + id,
        dataType: 'html',
        success: function (data) {
            $('#commentsId').html(data);

        }

    })

}
function addReply () {
    var idInputTopic=$('#idInputTopic').val();
    var idInputDesc=$('#idInputDesc').val();
    $.ajax({
        url: '/ts?action=addComment',
        type: "POST",
        data: 'idTopic='+idInputTopic+'&desc='+idInputDesc,
        dataType: 'html',
        success: function (data) {
            getCommentsByTopicId();
            $('#idInputDesc').val("");

        },
        error: function () {
            alert("Internal Error");

        }


    })
}