
var user="vidit1000@gmail.com"
var pass=1234
var config = {

			apiKey : "AIzaSyCe-iCyGgPiaMlKzCFFfg78y6YLcsJCZak",
			authDomain: "kabadiwala-2.firebaseapp.com",
			databaseURL : "https://kabadiwala-2.firebaseio.com",
			projectId : "kabadiwala-2",
			storageBucket : "gs://kabadiwala-2.appspot.com"
		};
	var firebase = require("firebase");
	firebase.initializeApp(config);
	var ref = firebase.database().ref();

	var token=0;

	
	ref.update({cardCount:1});