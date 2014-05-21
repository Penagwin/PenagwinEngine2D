var io = require('socket.io').listen(3001, {log: false});

var server = true;
var connected = 0;
io.sockets.on('connection', function(socket) {
    var address = socket.handshake.address;
    console.log("New connection from " + address.address + ":" + address.port);
    socket.on('disconnect', function() {
        connected--;
        console.log("disconnect " + connected);
    });

        socket.emit('status', {status: "newId",
            id: connected
        });
        console.log("connected " + connected);
        connected++;

    socket.on('newpos', function(data) {
        socket.broadcast.emit('newpos', data);
    });
    socket.on('lost', function(data) {
        socket.broadcast.emit('lost', data);
    });
    socket.on('restart', function(data) {
        socket.broadcast.emit('restart', data);
    });
socket.on('tackle', function(data) {
        socket.broadcast.emit('tackle', data);
    });


});