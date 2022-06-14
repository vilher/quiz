const express = require("express");
const bodyParser = require("body-parser");
const ejs = require("ejs");
const http = require('http');

const app = express();
app.set('view engine', 'ejs');
app.use(bodyParser.urlencoded({ extended: true }));

let score=0, size=1;
let correct;
let quiz;

app.get("/", function (req, res) {

    score=0;
    size=1;
    res.render("film.ejs");
});
app.get("/score", function (req, res) {

    res.render("score.ejs",{score: score,size:size});
});
app.get("/play", function (req, res) {
    const url = "http://localhost:8080/";
   http.get(url + "film" + "/" +"size=4", function (responce) {
        responce.on("data", function (data) {
           correct=Math.floor(Math.random() * (3 + 1));
           const obj = JSON.parse(data);
           quiz=obj;
           console.log("correct answer:"+correct);
           res.render("quiz.ejs", {List: obj, Correct:correct});
        })
    });

});
app.post("/check", function (req, res) {

    if(req.body.click==quiz[correct].filmName){score++;}


});
app.get("/next", function (req, res) {
    size++;
    res.redirect("/play");
});
app.listen(3000, function () {
    console.log("Server is running on port 3000");
});