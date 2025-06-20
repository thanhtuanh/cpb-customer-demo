let authHeader = "";
let currentUser = "";
let currentRole = "";

function setLoggedInUI(username, role) {
    document.getElementById('loginForm').style.display = "none";
    document.getElementById('logoutArea').style.display = "";
    document.getElementById('currentUser').textContent = username;
    document.getElementById('currentRole').textContent = role;
    document.getElementById('customerTable').style.display = "";
    document.getElementById('customerForm').style.display = role === "ADMIN" ? "" : "none";
}

function setLoggedOutUI() {
    document.getElementById('loginForm').style.display = "";
    document.getElementById('logoutArea').style.display = "none";
    document.getElementById('customerTable').style.display = "none";
    document.getElementById('customerForm').style.display = "none";
    document.getElementById('transactionTable').style.display = "none";
    document.getElementById('transactionForm').style.display = "none";
    document.getElementById('selectedCustomerName').textContent = "[bitte wählen]";
}

document.getElementById('loginForm').addEventListener('submit', function (e) {
    e.preventDefault();
    const user = document.getElementById('username').value.trim();
    const pw = document.getElementById('password').value;
    authHeader = "Basic " + btoa(user + ":" + pw);

    // Versuche, Kunden zu laden (Login-Test)
    fetch('/api/customers', { headers: { "Authorization": authHeader } })
        .then(r => {
            if (!r.ok) throw new Error();
            currentUser = user;
            // Hole die Rolle über ein Backend-API (siehe unten)
            return fetch('/api/userinfo', { headers: { "Authorization": authHeader } });
        })
        .then(r => {
            if (!r.ok) throw new Error();
            return r.json();
        })
        .then(userinfo => {
            currentRole = userinfo.role;
            setLoggedInUI(currentUser, currentRole);
            loadCustomers();
            document.getElementById('loginMsg').textContent = "";
        })
        .catch(() => {
            document.getElementById('loginMsg').textContent = "Login fehlgeschlagen!";
            setLoggedOutUI();
            authHeader = "";
            currentUser = "";
            currentRole = "";
        });
});

document.getElementById('logoutBtn').addEventListener('click', function () {
    authHeader = "";
    currentUser = "";
    currentRole = "";
    setLoggedOutUI();
});

// Seite initial im ausgeloggten Zustand
setLoggedOutUI();