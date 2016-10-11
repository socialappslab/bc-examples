#!/usr/bin/env node

var amqp = require('amqplib/callback_api');
var Message = require('./lib/message');

amqp.connect('amqp://admin:admin@104.131.157.72', function(err, conn) {
    conn.createChannel(function(err, ch) {
        var destination = 'email-subscriber';
        ch.assertExchange(destination, 'fanout', {
            durable: true
        });

        message = new Message('', destination, '{"eventTitle": "News flash 6","instancedata": "This one weighed more than 16 megatons!!"}');
        ch.publish(destination, '', new Buffer(JSON.stringify(message)));
        console.log(" [x] Sent %s", JSON.stringify(message));
    });

    setTimeout(function() {
        conn.close();
        process.exit(0)
    }, 500);
});
