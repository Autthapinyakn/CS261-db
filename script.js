function submitLogin() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    fetch('https://restapi.tu.ac.th/api/v1/auth/Ad/verify', {
        method: 'POST',
        headers: {
            'Application-Key': "TU5e4eb2cbe5154ae0e2c8e0114774e4cc9badea2ae91819780fddfcc35244f3f7c299bd99c0fc9071898547cd02ca7e6b",
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "Username": username,
            "Password": password
        })
    })
    .then(response => response.json())
    .then(data => {
        if (data.status === true) {
          alert("Success");
        } else {
          alert("Status: " + data.status);
        }
      })
    .catch(error => console.error('Error:', error));
}



function call_REST_API_Hello() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    const url = (
        'http://localhost:8080/hello?' +
        new URLSearchParams({ myName: username, lastName: password}).toString()
      );
    
    fetch(url)
    .then(response => response.text())
    .then(text => {
        document.getElementById('message').innerText = text;
    })
    .catch(error => console.error('Error:', error));
}
