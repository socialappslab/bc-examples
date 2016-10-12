#!/usr/bin/env node

/**
 * @file
 * Binding Component implementation automatically generated by
 * the Social Communication Platform.
 *
 * Characteristics:
 * - Type: HTTP sender.
 */

var request = require('request');
var Message = require('./lib/message');

/**
 * Sends a HTTP message.
 * Protocol: HTTP.
 * @param {string} msg - The message to send.
 */
exports.post = function(msg) {
    var data = JSON.parse(msg.getData());
    console.log('http-sender received: ' + msg.getData());
    request.post(
        'http://appcivist.littlemacondo.com/slacksender/slack', {
            json: {
                'channel': data.data.channel,
                'message': data.data.message
            }
	   // json: data.data
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