var sender = require('./amqp-sender');

exports.send = function(req, res) {
    var body = JSON.stringify(req.body);

    var destination = req.query.destination;

    sender.post(body, destination);

    res.send('OK');
}
