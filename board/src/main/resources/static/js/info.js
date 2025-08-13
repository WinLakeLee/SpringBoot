const userObject = {
	init: () => {
		const btnModify = document.querySelector('#btn-modify');
		if (btnModify) {
			btnModify.addEventListener('click', (e) => {
				e.preventDefault()
				const data = {
					id: document.getElementById("id").value,
					userName: document.getElementById("username").value,
					password: document.getElementById("password").value,
					newPassword: document.getElementById("new-password").value,
					email: document.getElementById("email").value
				}
				fetch("/auth/update", {
					method: "PUT",
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
		const btnDelete = document.querySelector('#btn-delete');
				if (btnDelete) {
					btnDelete.addEventListener('click', (e) => {
						e.preventDefault()
						const id = document.getElementById("id").value
						const data = {
							password: document.getElementById("password").value
						}
						fetch(`/auth/user?id=${id}`, {
							method: "DELETE",
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