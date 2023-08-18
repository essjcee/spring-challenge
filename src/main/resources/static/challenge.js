function populateDropDown(){
    const url = "/transactions/statetransactions";
    fetch(url)//promise object to return data from Rest API
        .then(response => { return response.json(); }) //resolve , data from resolve is passed to next then
        .then(items => {
            if (items.length > 0) {
                items.forEach((itemData) => {
                    let select = document.getElementById("stateDdown")
                    let option = document.createElement('option');
                    option.setAttribute('value', itemData.state);
                    option.appendChild(document.createTextNode(itemData.state));
                    select.appendChild(option);
                });
            }
       });
}

function btnState() {
    let x = document.getElementById("stateTransactions");
    let y = document.getElementById("categoryTransactions");
    let z = document.getElementById("lowHighTransactions");
    y.style.display = "none";
    x.style.display = "block";
    z.style.display = "none"
    getStateTransactions();
}

function btnCategory() {
    let x = document.getElementById("stateTransactions");
    let y = document.getElementById("categoryTransactions");
    let z = document.getElementById("lowHighTransactions");
    x.style.display = "none";
    y.style.display = "block";
    z.style.display = "none"
    getCategorySpendByState(document.getElementById("stateDdown").value);
}

function btnHighLowTransactionValues() {
    let x = document.getElementById("stateTransactions");
    let y = document.getElementById("categoryTransactions");
    let z = document.getElementById("lowHighTransactions");
    x.style.display = "none";
    y.style.display = "none";
    z.style.display = "block"
    getHLTransactionValues(document.getElementById("lowhigh").value);
}

function getStateTransactions() {
	const urlstate = "/transactions/statetransactions";
	fetch(urlstate)//promise object to return data from Rest API
		.then(response => { return response.json(); }) //resolve , data from resolve is passed to next then
		.then(items => {
			if (items.length > 0) {
				let temp = "";
				items.forEach((itemData) => {
					temp += "<tr>";
					temp += "<td>" + itemData.state + "</td>";
					temp += "<td>" + itemData.sales + "</td>";
					temp += "</tr>"
				});
				document.getElementById('transactions').innerHTML = temp;
		}
	}
	);
    fetch(urlstate).then(response => { return response.json(); })
    .then( items => {
        labels = [];
        values = [];
        items.forEach((itemData) => {
            labels.push(itemData.state);
            values.push(itemData.sales);
        });
        const myChart = new Chart("transactionsChart", {
          type: "pie",
          data: {
            labels: labels,
            datasets: [
                {
                    data: values,
                    backgroundColor : [ "#51EAEA", "#FCDDB0", "#FF9D76", "#FB3569", "#82CD47", '#3393FF']
                }
           ]},
          options: {
            legend: { display: true },
            title: {
                display: true,
                text: 'Total Transaction Amount By State'
            }
          }
        });
    });
}

function getCategorySpendByState(state){
    const urlcat = `/transactions/statetransactions/${state}`;
    	fetch(urlcat)//promise object to return data from Rest API
    		.then(response => { return response.json(); }) //resolve , data from resolve is passed to next then
    		.then(items => {
    			if (items.length > 0) {
    				let temp = "";
    				items.forEach((itemData) => {
    					temp += "<tr>";
    					temp += "<td>" + itemData.category + "</td>";
    					temp += "<td>" + itemData.spend + "</td>";
    					temp += "</tr>"
    				});
    				document.getElementById('spendcategory').innerHTML = temp;
    		}
    	}
    	);
        fetch(urlcat).then(response => { return response.json(); })
        .then( items => {
            labels = [];
            values = [];
            items.forEach((itemData) => {
                labels.push(itemData.category);
                values.push(itemData.spend);
            });
            const myChart = new Chart("categoryChart", {
              type: "horizontalBar",
              data: {
                labels: labels,
                datasets: [
                    {
                        data: values,
                        backgroundColor: '#3393FF'
                    }
               ]},
              options: {
                legend: { display: false },
                title: {
                    display: true,
                    text: 'Total Spend by Category'
                },
              }
            });
        });
}

function getHLTransactionValues(amount) {
	const urltrans = `/transactions/statetransactions/highlow/${amount}`;
	fetch(urltrans)//promise object to return data from Rest API
		.then(response => { return response.json(); }) //resolve , data from resolve is passed to next then
		.then(items => {
			if (items.length > 0) {
				let temp = "";
				items.forEach((itemData) => {
					temp += "<tr>";
					temp += "<td>" + itemData.lowHigh + "</td>";
					temp += "<td>" + itemData.transTotal + "</td>";
					temp += "</tr>"
				});
				console.log(temp);
				document.getElementById('lhspend').innerHTML = temp;
		}
	}
	);
    fetch(urltrans).then(response => { return response.json(); })
    .then( items => {
        labels = [];
        values = [];
        items.forEach((itemData) => {
            labels.push(itemData.lowHigh);
            values.push(itemData.transTotal);
        });
        const myChart = new Chart("lowHighChart", {
          type: "pie",
          data: {
            labels: labels,
            datasets: [
                {
                    data: values,
                    backgroundColor : [ "#51EAEA", "#FCDDB0"]
                }
           ]},
          options: {
            legend: { display: true },
            title: {
                display: true,
                text: 'Value of low and high Transactions'
            }
          }
        });
    });
}

/*
labels: labels,
            datasets: [
                {
                    label: "Sales",
                    data: values,
                    borderColor: '#36A2EB',
                    backgroundColor: '#3393FF'
                }
           ]
           },
          options: {
            legend: { display: false },
            title: {
                display: true,
                text: 'Country Sales'
            }
          }
*/


