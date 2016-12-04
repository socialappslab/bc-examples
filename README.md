
# AppCivist Binding Components (BCs)

Here you can find BCs generated for the AppCivist project. These BCs were automatically generated by Social-MQ.


## Requirements for running The BCs

- RabbitMQ
- Node.js

## Configuring the BCs

All configuration files are located in the conf folder inside the BC's root folder. You have to rename the files name \*.conf.js.sample to \*.conf.js.
Configuration parameters depend on the type of the BC; however, all BCs have either and AMQP endpoint (subscriber) or an AMQP sender (publisher).

1) *AMQP* (for all BCs): you can find the AMQP configuration in the files `amqp-endpoint.conf.js.sample` or `amqp-sender.conf.js.sample`. It contains the information of the AMQP broker, including the name of the exchange to publish or subscribe to.
```json
module.exports = {
    address: '127.0.0.1',
    port: '5672',
    user: 'username',
    password: 'userpass',
    exchange:{
      name:'exchange-name',
      type:'fanout'
    }
};
```
2) *HTTP Senders*: the files `http-sender.conf.json.sample` or `http-sender.conf.js.sample` contain the address of the HTTP endpoint to send the message to.

3) *HTTP Endpoints*: the file `hhttp-endpoint.conf.js.sample` contains the port to listen to and the request path for the endpoint.

4) Some BC (e.g., email) might require extra configuration for the components used in the lib folder. For example, the bus-email requires a valid SMTP account to be configured in `lib/mailer.conf.js`
```
module.exports = {
    host: 'smtp.example.com',
    port: 465,
    email: 'user',
    password: 'pass'
};
```

Finally, you have to install the required modules of each BC using the command `npm install` in the BC's root folder.

## How to run the BCs

There is no specific way of running BCs; however, we recommend to follow the best production practices described in:

- [Production best practices if you are using Node.js BCs](https://expressjs.com/en/advanced/best-practice-performance.html).

1. Install the [Strong Loop Process Manager](http://strong-pm.io/prod/)
```
npm install -g strong-pm
```

2. Install Process Manager as a service to ensure it starts when the system boots
```bash
# Ubuntu 12.04+:
sudo sl-pm-install
sudo /sbin/initctl start strong-pm 

# Red Hat Enterprise Linux 7+:
sudo sl-pm-install --systemd
sudo /usr/bin/systemctl start strong-pm 

# Red Hat Enterprise Linux 5 and 6:
sudo sl-pm-install --upstart=0.6
sudo /sbin/initctl start strong-pm
```

3. Use the slc to run the BC (this way, BCs restart automatically if they crash).

```bash
# Build and run
cd $BC_DIR
npm i
slc start

# See BCs running
slc ctl ls

Id            Name            Scale
 1   bc-appcivistnorifier-bus.email    1
 2       bc-bus-email                  1
 3      bc-bus-http                    1
 4     bc-bus-slackchannel             1
  ```

  The main file of BCs is called **start-bc** and it is located inside the BC's root folder. To execute a BC, you have to run this file. How to run it depends on the platform the BC was generated for.

  - *Node.js BC execution*: node start-bc.js or slc start using the Strong Loop Process Manager.


### The AppCivist Notifier to Email BC

- Components:

1) *http-endpoint*: it is an HTTP server exposing a POST method.
2) *amqp-sender*: it sends received messages to Email through the AMQP broker.

- Dummy Producer:

There are two types of the HTTP producer: one sending **x-www-form-urlencoded** data (no longer supported); the other sending **json** data. Both types of producers are available in Java and un curl. For example,

```
curl -X POST http://localhost:3000/email\
    -H "Content-Type: application/json"\
    -d '{"to":["one@email.fr","two@email.fr"],
    "from":"AppCivist Bot <bot@appcivist.org>",
    "subject":"This is just a test!",
    "text":"<html><p><strong>Example Email from AppCivist</strong></p> <img src=\"http://www.airport-orly.com/images/paris-tour-eiffel-at-night.jpg\" alt=\"Mountain View\" style=\"width:304px;height:228px;\"></html>"
    }'
```
### The Email BC

- Components:

1) *amqp-endpoint*: it subscribes to the AMQP broker and waits for new messages for Email.
2) *smtp-sender*: it sends received messages via email.

### The HTTP Webhook BC

- Components:

1) *amqp-endpoint*: it subscribes to the AMQP broker and waits for new messages for Email.
2) *http-sender*: it sends received messages via HTTP using Webhooks.


### The Subscription Manager BC

- Components:

1) *amqp-endpoint*: it subscribes to the AMQP broker and waits for new messages for Email.
2) *http-sender*: it sends received messages to the [Subscription Manager service](https://github.com/rafaelangarita/email-notification-service)..

### The Slack Channel BC

- Components:

1) *amqp-endpoint*: it subscribes to the AMQP broker and waits for new messages for Email.
2) *http-sender*: it sends received messages to the [Slack Channel service](https://github.com/rafaelangarita/slack-sender).




## More


 You can also find some additional services you can play with. They are already participating in the Social Communication Bus.

- [Slack](https://github.com/rafaelangarita/slack-sender).


- [Subscription Manager](https://github.com/rafaelangarita/email-notification-service).
