function getuserdetails() {
    alert("SHOW ALL BOOKS....");
    var xhttp=new XMLHttpRequest();
    xhttp.onreadystatechange= function () {
        if (this.readyState == 4 && this.status == 200) {
            _displayItems(JSON.parse(xhttp.responseText));
           console.log(xhttp.responseText);
          }
        };
        xhttp.open("GET","http://localhost:9090/UserServlet",true);
        xhttp.send();      
    }
function _displayItems(data){
    
    const tBody= document.getElementById("userset");
    tBody.innerHTML="";
    const button = document.createElement("button");
    data.forEach((item) => {
         //print edit button
        //  let editButton = button.cloneNode(false);
        //  editButton.innerText = "Edit";
        //  editButton.setAttribute("onclick", `editItem(${item.ISBN})`);
         //print delete button
        //  let deleteButton = button.cloneNode(false);
        //  deleteButton.innerText = "Delete";
        //  deleteButton.setAttribute("onclick", `deleteItem(${item.ISBN})`);
         //insert the row in the table
         let tr = tBody.insertRow();
           //1st column --UsersId
        let td1 = tr.insertCell(0);
        let bookid = document.createTextNode(item.ISBN);
        td1.appendChild(bookid);
        //2nd column --name
        let td2 = tr.insertCell(1);
        let custname = document.createTextNode(item.BookName);
        td2.appendChild(custname);
        //3rd column --password
        let td3 = tr.insertCell(2);
        let bookauthor= document.createTextNode(item.Author)
        td3.appendChild(bookauthor);
        //4th column --email id
        let td4 = tr.insertCell(3);
        let bookpublisher= document.createTextNode(item.PublisherName)
        td4.appendChild(bookpublisher);
        
      });
      userset=data;
    }
    // function saveAndUpdate() {
    //     ((document.getElementById('myBtn').innerHTML == "submit") ? this.add() : this.update());
    //     getuserdetails();
    // }
    // function add() {
    //     console.log("add")
    //  const newuser ={ 
    //      userid:document.getElementById("userid").value,
    //      username:document.getElementById("username").value,
    //      password:document.getElementById("password").value,
    //      emailid:document.getElementById("emailid").value,
    //      phonenumber:document.getElementById("phonenumber").value
    //  };
    //  console.log(newuser);
    //  var xhttp =new XMLHttpRequest();
    //  xhttp.open("POST","http://localhost:9090/User",true);
    //  xhttp.setRequestHeader("Content-type", "application/json");
    //  xhttp.send(JSON.stringify(newuser));
    // //  getuserdetails();
    // }
    // function update() {
    //     el = document.getElementById('name');
    //     var name = el.value;
    //     names[updateindex] = name;
    //     document.getElementById('name').value = '';
    //     document.getElementById('myBtn').innerHTML = 'submit';
    //     fetchall();
    // }