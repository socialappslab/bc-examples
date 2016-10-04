exports.message = function(req, res) {
    var body = JSON.stringify(req.body);

    console.log('Well, I received this message: ' + body);

    process.exit();

    res.send('OK');
}
