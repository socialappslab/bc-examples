#!/usr/bin/env node

/**
 * @file
 * Binding Component implementation automatically generated by
 * the Social Communication Platform.
 *
 * Characteristics:
 * - Type: HTTP sender.
 */

var WebHooks = require('request');
var Message = require('./lib/message');

var webHooks = new WebHooks({
    db: './conf/http-sender.conf.json', // json file that store webhook URLs
});

/**
 * Sends a HTTP message.
 * Protocol: HTTP.
 * @param {string} msg - The message to send.
 */
exports.post = function(msg) {
    console.log('http-sender received: ' + msg.getData());
    request.post(
        'http://appcivist.littlemacondo.com/emailsubscriber/' + msg.getData(), {
            json: {
                'eventTitle': eventTitle.getData().operation,
                'instancedata': eventTitle.getData().instancedata
            }
        },
        function(error, response, body) {
            //console.log(response);
            //console.log(error);
            if (!error && response.statusCode == 200) {
                console.log(body)
            }
        }
    );

};