const userObject = {
	init: () => {
		const btnLogin = document.querySelector('#btn-login');
		if (btnLogin) {
			btnLogin.addEventListener('click', (e) => {
				e.preventDefault()
				const data = {
					userName: document.getElementById("username").value,
					password: document.getElementById("password").value
				}
				fetch("/auth/login", {
					method: "POST",
					headers: {
						"Content-Type": "application/json"
					},
					body: JSON.stringify(data)
				})
					.then(res => res.status == 200 ?
						window.location.href = "/" :
						console.log(res)
					)
					.catch(err => console.log(err))
			})
		}
	}


}

userObject.init();