const userObject = {
	init: () => {
		const btnSave = document.querySelector('#btn-save');
		if (btnSave) {
			btnSave.addEventListener('click', (e) => {
				e.preventDefault()
				const data = {
					userName: document.getElementById("username").value,
					password: document.getElementById("password").value,
					email: document.getElementById("email").value
				}
				const join = async () => {
					await fetch("/auth/join", {
						method: "POST",
						headers: {
							"Content-Type": "application/json; charset=UTF-8"
						},
						body: JSON.stringify(data)
					})
						.then(res => res.json())
						.then(json => {
							alert(json.data)
							if (json.status == 200) {
								alert(json.data)
								window.location.href = "/"
							} else {
								let msg = '';
								let errors = json.data;
								if (typeof errors == 'string')
									msg = errors
								if (errors.username != null)
									msg += errors.username + '\n'
								if (errors.password != null)
									msg += errors.password + '\n'
								if (errors.email != null)
									msg += errors.email + '\n'
								alert(msg)	
							}
						})
						.catch(err => console.log(err))
				}
				join()
			})
		}
	}


}

userObject.init();