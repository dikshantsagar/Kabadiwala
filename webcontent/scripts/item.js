

$(document).ready(function()
{
	var config = {

				apiKey : "AIzaSyCe-iCyGgPiaMlKzCFFfg78y6YLcsJCZak",
				authDomain: "kabadiwala-2.firebaseapp.com",
				databaseURL : "https://kabadiwala-2.firebaseio.com",
				projectId : "kabadiwala-2",
				storageBucket : "gs://kabadiwala-2.appspot.com"
			};


function getUrlVars() {
      var vars = {};
      var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
          vars[key] = value;
      });
      return vars;
  }
firebase.initializeApp(config);
	var ref = firebase.database().ref("card");

	ref.on('value',function(data)
			{
				
				var id=getUrlVars()["id"];
				
					var b=document.getElementById('container');
					b.innerHTML="<img src='https://firebasestorage.googleapis.com/v0/b/kabadiwala-2.appspot.com/o/def.jpg?alt=media&token=6beb9082-76f2-47e6-9a3c-8ae85ef36188' style='width:100%; height:30%; text-align:left;'>"
					var c=document.createElement('h1');
					c.innerHTML=data.child(id.toString()).child("name").val();
					
					var d=document.createElement('p');
					d.innerHTML="Weight :"+data.child(id.toString()).child("weight").val();
					
					var e=document.createElement('p');
					e.innerHTML="Starting Price :₹"+data.child(id.toString()).child("price").val();
					var f=document.createElement('p');
					f.innerHTML="Current Bid : ₹"+data.child(id.toString()).child("bid").val();
					var g =document.createElement('b');
			        g.setAttribute("style","display:inline:flex;");
			        g.innerHTML="Bid User : #"+data.child(id.toString()).child("bidusr").val();
					b.appendChild(c);
					b.appendChild(d);
					b.appendChild(e);
					b.appendChild(f);
					b.appendChild(g);
					var bt=document.createElement('button');
					bt.setAttribute("class","btn b");
					bt.innerHTML="Accept Bid";
					b.appendChild(bt);
					
				


			});
});