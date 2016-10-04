var sender = require('./amqp-sender');

exports.send = function(req, res) {
    var body = JSON.stringify(req.body);

    sender.post(body, req.body.destination);

    res.send('OK');
}
