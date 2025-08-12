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
						mode: "cors",
						cache: "no-cache",
						credentials: "same-origin",
						headers: {
							"Content-Type": "application/json; charset=UTF-8"
						},
						redirect: "follow",
						referrerPolicy: "no-referrer",
						body: JSON.stringify(data)
					})
						.then(res => res.json())
						.then(json => {
							alert(json.data)
							if (json.status == 400)
								return
							window.location.href = "/"
						})
						.catch(err => console.log(err))
				}	
				join()
			})
		}
	}


}

userObject.init();