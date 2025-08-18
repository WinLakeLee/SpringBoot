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
					.then(res => res.json)
					.then(json => {
						if (json.status == 200)
							window.location.href = "/"
						else {
							let msg = ''
							if (json.status == 400) {
								msg += "존재하지 않는 유저입니다."
							} else if (json.status == 401) {
								msg += "비밀번호가 잘못되었습니다"
							}
							alert(msg)
						}
					}

					)
					.catch(err => console.log(err))
			})
		}
	}

}

userObject.init();