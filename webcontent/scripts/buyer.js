
$(document).ready(function()
{
var config = {

			apiKey : "AIzaSyCe-iCyGgPiaMlKzCFFfg78y6YLcsJCZak",
			authDomain: "kabadiwala-2.firebaseapp.com",
			databaseURL : "https://kabadiwala-2.firebaseio.com",
			projectId : "kabadiwala-2",
			storageBucket : "gs://kabadiwala-2.appspot.com"
		};

firebase.initializeApp(config);
	var ref = firebase.database().ref("card");

	ref.on('value',function(data)
			{
				var doc=document.getElementById('container');
				var len=data.numChildren();
				for(var i=0;i<len;i++)
				{
					var b=document.createElement('div');
					b.className="card car";
					b.setAttribute("id",i.toString());
					b.setAttribute("onClick","func(this.id);");
					b.innerHTML="<img src='https://firebasestorage.googleapis.com/v0/b/kabadiwala-2.appspot.com/o/def.jpg?alt=media&token=6beb9082-76f2-47e6-9a3c-8ae85ef36188' style='width:100%; height:30%;'>"
					var c=document.createElement('h1');
					c.innerHTML=data.child(i.toString()).child("name").val();
					
					var d=document.createElement('p');
					d.innerHTML="Weight :"+data.child(i.toString()).child("weight").val();
					
					var e=document.createElement('p');
					e.innerHTML="Starting Price :₹"+data.child(i.toString()).child("price").val();
					var f=document.createElement('b');
					f.innerHTML="Current Bid : ₹"+data.child(i.toString()).child("bid").val();
					b.appendChild(c);
					b.appendChild(d);
					b.appendChild(e);
					b.appendChild(f);
					doc.appendChild(b);
				}


			});
});

function func(id)
{
		window.location="bid.html?id="+id;

}



