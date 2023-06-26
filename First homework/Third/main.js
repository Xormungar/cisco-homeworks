var app         = require('express')(),
    fs          = require('fs');

var data        = fs.readFileSync('database.json');
var documents   = JSON.parse(data);


app.listen(8080, function() {
    console.log('Server is running on port 8080');
});

app.get('/taggedContent', function(req, res) {
    var tag = req.query.tag;

    for(key in documents) {
        if(key == tag) {
            res.json(documents[key].files);
            return;
        } else {
            //Warning: Super hacky and ugly solution 
            var soup = JSON.stringify(documents[key].tags);
            if(soup.includes(tag)) {
                res.json(documents[key].files);
                return;
            }
        }
    }

    res.json("Nothing found");

});