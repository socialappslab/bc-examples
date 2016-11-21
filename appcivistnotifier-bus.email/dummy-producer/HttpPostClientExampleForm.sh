    curl -X POST http://localhost:3000/send\
        -F 'from'='AppCivist Bot <bot@appcivist.org>' \
        -F destination=email \
        -F to=one@email.com \
        -F to=two@email.fr \
        -F subject='This is just a test' \
--form-string text='<html><p><strong>Example Email from AppCivist</strong></p> <img src="http://www.airport-orly.com/images/paris-tour-eiffel-at-night.jpg" alt="Mountain View" style="width:304px;height:228px;">
</html>'
