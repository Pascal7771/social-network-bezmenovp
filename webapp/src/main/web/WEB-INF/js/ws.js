let stompClient = null;

function connect() {
    disconnect();
    var socket = new SockJS(url + '/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        if (senderId > recipientId) {
            stompClient.subscribe('/topic/' + recipientId + '&' + senderId, function (message) {
                addMessage(JSON.parse(message.body));
            });
        } else {
            stompClient.subscribe('/topic/' + senderId + '&' + recipientId, function (message) {
                addMessage(JSON.parse(message.body));
            });
        }
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
}

function sendMessage() {
    if (senderId > recipientId) {
        stompClient.send("/app/chat/" + recipientId + "&" + senderId, {},
            JSON.stringify({'textMessage': $("#textMessage").val(), 'recipientId': recipientId, 'senderId': senderId}));
        document.getElementById('textMessage').value = '';
    } else {
        stompClient.send("/app/chat/" + senderId + "&" + recipientId, {},
            JSON.stringify({'textMessage': $("#textMessage").val(), 'recipientId': recipientId, 'senderId': senderId}));
        document.getElementById('textMessage').value = '';
    }
}

function addMessage(message) {
    if (message.senderId !== senderId) {
        $('#message_template').prepend('<div class="row">' + '<div class="col">' + message.timeSend + ' ' +
            '<a href="${pageContext.request.contextPath}/account/info?id=' + message.senderId + '">' + recipient +
            '</a>' + '</div>' + '<div class="col-9">' + '<div class="alert alert-primary" role="alert">' + message.textMessage +
            '</div>' + '</div>' + '</div>');
    } else {
        $('#message_template').prepend('<div class="row">' + '<div class="col-9">' + '<div class="alert alert-primary" role="alert">' +
            message.textMessage + '</div>' + '</div>' + '<div class="col">' + message.timeSend + ' ' +
            '<a href="${pageContext.request.contextPath}/account/info?id=' + message.senderId + '">' + sender +
            '</a>' + '</div>' + '</div>');
    }

}