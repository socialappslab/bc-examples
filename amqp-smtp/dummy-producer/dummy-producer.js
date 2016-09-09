#!/usr/bin/env node

/**
 * @file
 * Dummy publisher to emulate a publish/subscribe Social Entity.
 *
 * Characteristics:
 * - Type: AMQP Publisher.
 */

var amqp = require('amqplib/callback_api');

amqp.connect('amqp://admin:admin@104.131.157.72:5672', function(err, conn) {
    conn.createChannel(function(err, ch) {
        var ex = 'appcivist.assembly.assemblytest.forum.post';
        var msg = process.argv.slice(2).join(' ') || 'New Forum Post!';

        ch.assertExchange(ex, 'fanout', {
            durable: true
        });
        ch.publish(ex, '', new Buffer(msg));
        console.log(" [x] Sent %s", msg);
    });

    setTimeout(function() {
        conn.close();
        process.exit(0)
    }, 500);
});
