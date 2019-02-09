
document.addEventListener("DOMContentLoaded", function(event) {

  var config = {

      apiKey : "AIzaSyCe-iCyGgPiaMlKzCFFfg78y6YLcsJCZak",
      authDomain: "kabadiwala-2.firebaseapp.com",
      databaseURL : "https://kabadiwala-2.firebaseio.com",
      projectId : "kabadiwala-2",
      storageBucket : "gs://kabadiwala-2.appspot.com"
      };

      firebase.initializeApp(config);
    var ref1 = firebase.database().ref("cardCount");
    var ref2 = firebase.database().ref("card");
    var ref3= firebase.database().ref();

  function getUrlVars() {
      var vars = {};
      var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
          vars[key] = value;
      });
      return vars;
  }
  function func(id)
{
    window.location="item.html?id="+id;

}

  var id=getUrlVars()["id"];

  ref2.on('value',function(data)
  {
      var len=data.numChildren();
      for(var i=0;i<len;i++)
      {
        if(data.child(i.toString()).child("userid").val()==id)
        {
          var main=document.getElementById('main');
          var box=document.createElement('div');
          box.setAttribute("class"," card car");
          box.setAttribute("id",id.toString());
          box.setAttribute("onClick","func(this.id);");
          box.innerHTML="<img src='https://firebasestorage.googleapis.com/v0/b/kabadiwala-2.appspot.com/o/default.png?alt=media&token=68854daf-8594-42f3-9a15-11fabd5ab184' style='width:100%; height:35%'>";
          var c=document.createElement('h1');
          c.innerHTML=data.child(i.toString()).child("name").val();
          var d=document.createElement('p');
          d.setAttribute("style","display:inline:flex;");
          d.innerHTML="Weight :"+data.child(i.toString()).child("weight").val()+"Kg";
          var e=document.createElement('p');
          e.setAttribute("style","display:inline:flex;");
          e.innerHTML="Starting Price :₹"+data.child(i.toString()).child("price").val();
          var f =document.createElement('b');
          f.setAttribute("style","display:inline:flex;");
          f.innerHTML="Current Bid : ₹"+data.child(i.toString()).child("bid").val();
          var g =document.createElement('b');
          g.setAttribute("style","display:inline:flex;");
          g.innerHTML="Bid User : #"+data.child(i.toString()).child("bidusr").val();
          box.appendChild(c);
          box.appendChild(d);
          box.appendChild(e);
          box.appendChild(f);
          box.appendChild(g);
          main.appendChild(box);
          var bt=document.createElement('button');
          bt.setAttribute("class","btn b");
          bt.setAttribute("id","accbid");
          bt.innerHTML="Accept Bid";
          box.appendChild(bt);
        }
      }
  });



$("#sbut").click(function()
    	{
    		
    		var main=document.getElementById('main');
    		var box=document.createElement('div');
    		box.className="btn card car";
    		box.innerHTML="<img src='https://firebasestorage.googleapis.com/v0/b/kabadiwala-2.appspot.com/o/default.png?alt=media&token=68854daf-8594-42f3-9a15-11fabd5ab184' style='width:100%; height:35%'>";
        var c=document.createElement('h1');
        c.style.textAlign="center";
        c.innerHTML=$('#name').val();
        var d=document.createElement('p');
        d.setAttribute("style","display:inline:flex;");
        d.innerHTML="Weight :"+$('#quantity').val()+"Kg";
        var e=document.createElement('p');
        e.setAttribute("style","display:inline:flex;");
        e.innerHTML="Starting Price :₹"+$('#price').val();
        var f =document.createElement('b');
        f.setAttribute("style","display:inline:flex;");
        f.innerHTML="Current Bid : ₹"+0;
        var g =document.createElement('b');
        g.setAttribute("style","display:inline:flex;");
        g.innerHTML="Bid User : #";
        box.appendChild(c);
        box.appendChild(d);
        box.appendChild(e);
        box.appendChild(f);
        box.appendChild(g);
        main.appendChild(box);
        var moda = document.getElementById('myModal');
        moda.style.display = "none";

        
    var count;
    var token=0;
      ref1.on('value',function(data)
      {
        count=data.val();


      });

       ref2.on('value',function(data)
      {
        ref2.child(count+1).set({
          bid:0,
          name:$('#name').val(),
          price:$('#price').val(),
          userid:id,
          weight:$('#quantity').val(),
          bidusr:"empty"
        });
          if(token==0)
          {
            ref3.update({cardCount:count+1});
            token=1;
          }

      });

        

    		return false;
    	});	
});





