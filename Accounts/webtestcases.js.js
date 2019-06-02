var request = require("request");

function createCustomer(name){
  var request = require("request");

var options = { method: 'POST',
  url: 'http://localhost:8080/acc/account',
  headers: 
   { 'cache-control': 'no-cache',
     'Content-Type': 'application/json' },
  body: { name: name, address: 'Bangalore' },
  json: true };

request(options, function (error, response, body) {
  if (error) throw new Error(error);

  console.log(body);
});

}

function getbal(acc){
  var request = require("request");

var options = { method: 'POST',
  url: 'http://localhost:8080/acc/getbalance',
  headers: 
   { 'cache-control': 'no-cache',
     'Content-Type': 'application/json' },
  body: { account: acc },
  json: true };

request(options, function (error, response, body) {
  if (error) throw new Error(error);

  console.log(acc+"-->"+body);
});

}

function addmoney(accnum){
  var request = require("request");

var options = { method: 'POST',
  url: 'http://localhost:8080/acc/addmoney',
  headers: 
   { 'cache-control': 'no-cache',
     'Content-Type': 'application/json' },
  body: { to: accnum, amount: '10000' },
  json: true };

request(options, function (error, response, body) {
  if (error) throw new Error(error);

  console.log(body);
});

}
function testapi(from,to,amount="100"){
  return new Promise((resolve,reject)=>{
        var options = { method: 'POST',
        url: 'http://localhost:8080/acc/transfer',
        headers: 
        { 'cache-control': 'no-cache',
          'Content-Type': 'application/json' },
        body: { from: from, to: to, amount: amount },
        json: true };
      
      request(options, function (error, response, body) {
        if (error) throw new Error(error);
      
        console.log(body);
        resolve(body);
      });

  });

}


let isnew=false;

let ctr=50;
if(isnew){
  
  for(let i=0;i<ctr;++i){
    createCustomer("Amrit "+i);
  }

  for(let i=0;i<ctr;++i){
    addmoney((1000+i)+"");
  }
  

}

function getpromises(ctr=10){
  res=[];
  for (let i=0;i<ctr;++i){
    let tonum=Math.floor(Math.random()*10);
    res.push(testapi(""+(1000+i),""+(1000+tonum),"100"));
  }

  return res;
  
}
//  testapi("1000","1001"),testapi("1000","1001"),testapi("1000","1001"), testapi("1000","1001"),testapi("1000","1001"),testapi("1000","1001"),testapi("1000","1001"),testapi("1003","1004"),  testapi("1004","1005"),testapi("1005","1006") 


Promise.all(getpromises(ctr)).then(function(value) {
  //console.log(value);
  // Both resolve, but promise2 is faster
  for(let i=0;i<ctr;++i){
    getbal((1000+i)+"");
  }
});




