#!/usr/bin/env node

var amqp = require('amqplib/callback_api');
var Message = require('./lib/message');

amqp.connect('amqp://admin:admin@104.131.157.72', function(err, conn) {
    conn.createChannel(function(err, ch) {
        var destination = 'appcivist';
        ch.assertExchange(destination, 'fanout', {
            durable: true
        });

        message = new Message('', destination, 'Hello World!');
        ch.publish(destination, '', new Buffer(JSON.stringify(message)));
        console.log(" [x] Sent %s", JSON.stringify(message));
    });

    setTimeout(function() {
        conn.close();
        process.exit(0)
    }, 500);
});
