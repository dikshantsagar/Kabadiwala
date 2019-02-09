
var usr;
var pass;
$('#login').click(function()
{
	 usr=$('#usr').val();
	 pass=document.getElementById("pass").value;



	var config = {

			apiKey : "AIzaSyCe-iCyGgPiaMlKzCFFfg78y6YLcsJCZak",
			authDomain: "kabadiwala-2.firebaseapp.com",
			databaseURL : "https://kabadiwala-2.firebaseio.com",
			projectId : "kabadiwala-2",
			storageBucket : "gs://kabadiwala-2.appspot.com"
		};

	firebase.initializeApp(config);
	var ref = firebase.database().ref("user");
	var token=0;
	var ind=0;

	ref.on('value',function(data)
			{	
				
				var len=data.numChildren();
				for(var i=0;i<len;i++)
				{
					var name=data.child(i.toString()).child("email").val();
					var pwd=data.child(i.toString()).child("pass").val();
					
					if(name==usr && pwd ==pass)
					{
						token=1;
						ind=i;
						var typ=data.child(i.toString()).child("type").val();
					}
				}
					if(token==1)
					{
						console.log("login succ");
						
						if(typ=="Seller")
						{
							window.location="session.html?id="+ind;
						}
						else if(typ=="Buyer")
						{
							window.location="buyer.html?id="+ind;
						}
						
					}
					else
					{
						console.log("login fail");
						document.write("login fail");
					}



			});
	return false;

});

$('#signup').click(function()
{
	window.location="signup.html";
	return false;
});

