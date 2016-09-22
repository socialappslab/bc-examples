    curl -X POST http://localhost:3000/send?destination=email\
        -H "Content-Type: application/json"\
        -d '{"to":["one@email.com","two@email.fr"],
        "from":"AppCivist Bot <bot@appcivist.org>",
        "subject":"This is just a test!",
        "text":"<html><p><strong>Example Email from AppCivist</strong></p> <img src=\"http://www.airport-orly.com/images/paris-tour-eiffel-at-night.jpg\" alt=\"Mountain View\" style=\"width:304px;height:228px;\"></html>"
        }'
