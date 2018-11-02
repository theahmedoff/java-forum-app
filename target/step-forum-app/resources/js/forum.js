
$('#idReplyButton').click(function () {
    addComment();
})

function addComment() {
    var reply = $('#reply').val();
    var topicId = $('#topic-id').val();
    
    $.ajax({
        url: '/cs?action=add-comment',
        data: 'reply' + reply + '&topicId' + topicId,
        type: 'POST',
        seuccess: function () {
            alert('Comment added!');

        },
        error: function () {
            alert('Error comment added!')
        }
        
    })
}