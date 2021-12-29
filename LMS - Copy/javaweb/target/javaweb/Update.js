
const uri = "http://localhost:9090/UserServlet";
let userset = [];
let updateIndex=0;
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
    function getItems() {
      fetch("http://localhost:9090/UserServlet")
        .then((response) => response.json())
        .then((data) => _displayItems(data))
        .catch((error) => console.error("Unable to get items.", error));
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
         let deleteButton = button.cloneNode(false);
         deleteButton.innerText = "Delete";
         deleteButton.setAttribute("onclick", `deleteItem(${item.ISBN})`);
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
        
        // let td5 = tr.insertCell(4);
        // td5.appendChild(editButton);
        //7th column--delete button
        let td5 = tr.insertCell(4);
        td5.appendChild(deleteButton);
      });
      
    }

    function deleteItem(id) {
      const item = {

        ISBN: id
         
      };
    
      console.log(item);
     var xhttp = new XMLHttpRequest();
      xhttp.open("DELETE", "/UserServlet", true);
      xhttp.setRequestHeader("Content-type", "application/json");
      xhttp.send(JSON.stringify(item));
      getItems();
      //getuserdetails(); 
    };

    
function add() {
  alert("ADDING...")
  console.log("add")
  // creating a obj with a enity class properties
  const newbook = {
    ISBN: document.getElementById("ISBN").value,
    BookName: document.getElementById("Book_Name").value,
    Author: document.getElementById("Author").value,
    PublisherName: document.getElementById("Publisher_Name").value
  };
  console.log(newbook);
  //creating xttp var
  var xhttp = new XMLHttpRequest();
  //calling post method
  xhttp.open("POST", "http://localhost:9090/UserServlet", true);
  //definding the type of data is transfered
  xhttp.setRequestHeader("Content-type", "application/json");
  xhttp.send(JSON.stringify(newbook));
}


data=userset;