$(document).ready(function()
{

	function getUrlVars() {
	    var vars = {};
	    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
	        vars[key] = value;
	    });
	    return vars;
	}
		$("#form").submit(function()
		{
			
			
		
		var id = getUrlVars()["id"];
		var ubid=$("#bid").val();
		var config = {

				apiKey : "AIzaSyCe-iCyGgPiaMlKzCFFfg78y6YLcsJCZak",
				authDomain: "kabadiwala-2.firebaseapp.com",
				databaseURL : "https://kabadiwala-2.firebaseio.com",
				projectId : "kabadiwala-2",
				storageBucket : "gs://kabadiwala-2.appspot.com"
			};

		firebase.initializeApp(config);
		var ref = firebase.database().ref("card");
		var token=0;
		ref.on('value',function(data)
				{
					if(token==0)
					{
						token=1;
						if(ubid>data.child(id.toString()).child("bid").val())
						{
							ref.child(id).update({bid:ubid,bidusr:id});
							var b=document.getElementById('container');
							var c=document.createElement('div');
							c.innerHTML=" Bid Successfully Placed !";
							b.appendChild(c);
						}
						else
						{
							var b=document.getElementById('container');
							var c=document.createElement('div');
							c.innerHTML=" Your Bid Is lower Than current Bid";
							b.appendChild(c);
						}
					}
				});
		this.disabled=true;
		return false;

		});
});
		
	



