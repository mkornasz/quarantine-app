const API_URL = 'http://localhost:8080';
const ORDERS_API_URL = `${API_URL}/orders`;
const COURIERS_API_URL = `${API_URL}/couriers`;

window.onload=function(){
    initCounts();
    loadOrdersTable();
    loadCouriersTable();
    document.getElementById('newOrderBtn').addEventListener('click', (event) => {
        document.getElementById("modalWindow").style.display = "block";
    });
    document.getElementById('resetBtn').addEventListener('click', (event) => {
        document.getElementById("modalWindow").style.display = "none";
    });
    document.getElementById('submitBtn').addEventListener('click', (event) => {
        event.preventDefault();
        submitOrder();
    });
}

function submitOrder() {

        fetch(ORDERS_API_URL, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                routeLength: document.getElementById('routeLength').value,
                deliveryDate: document.getElementById('deliveryDate').value,
                clientName: document.getElementById('firstname').value,
                clientSurname: document.getElementById('surname').value,
                clientPhone: document.getElementById('phone').value
            })})
            .then(processOkResponse)
            .then(o => {
                addOrderToTable(o);
                document.getElementById("modalWindow").style.display = "none";
                document.getElementById("modalForm").reset();
                initCounts();})
            .catch(error => alert(error.message == '422' ?
                'Chosen delivery date is not available. ' : 'SERVER ERROR'));
}

function initCounts() {
    fetch(`${COURIERS_API_URL}/count`)
        .then(processOkResponse)
        .then(count => {
            document.getElementById('couriersSpan').innerHTML = `
                    ${count}
                    <p>KURIERÓW</p>`
        });
    fetch(`${ORDERS_API_URL}/count`)
        .then(processOkResponse)
        .then(count => {
            document.getElementById('ordersSpan').innerHTML = `
                    ${count}
                    <p>ZAMÓWIEŃ</p>`
        });
}


function addOrderToTable(order) {
    var row = '<tr><td>' + order.clientName + '</td><td>' + order.clientSurname
        + '</td><td>' + order.clientPhone + '</td><td>' + order.orderDate
        + '</td><td>' + order.routeLength + '</td><td>' + order.deliveryDate + '</td></tr>';
    document.getElementById("ordersTbody").innerHTML += row;
}

function loadCouriersTable() {
    fetch(`${COURIERS_API_URL}`)
        .then(processOkResponse)
        .then(courierArr => courierArr.map(courier => {
            var row = '<tr><td>' + courier.name + '</td><td>' + courier.surname
                + '</td><td>' + courier.phone + '</td><td>' + courier.capacity + '</td></tr>';
            document.getElementById("couriersTbody").innerHTML += row;
        }));
}

function loadOrdersTable() {
    fetch(`${ORDERS_API_URL}`)
        .then(processOkResponse)
        .then(orderArr => orderArr.map(o => addOrderToTable(o)));
}


function processOkResponse(response = {}) {
    if (response.ok) {
        return response.json();
    }
    throw new Error(`${response.status}`);
}