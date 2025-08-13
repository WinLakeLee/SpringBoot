$(document).ready(() => {
	$('#content').summernote({
		height: 400
	})
})

const userObject = {
	init: () => {
		const btnInsert = document.querySelector('#btn-insert');
		if (btnInsert) {
			btnInsert.addEventListener('click', (e) => {
				e.preventDefault()
				const data = {
					title: document.getElementById("title").value,
					content: document.getElementById("content").value
				}
					const insert = () => {
						fetch("/post", {
							method: "POST",
							headers: {
								"Content-Type": "application/json; charset=UTF-8"
							},
							body: JSON.stringify(data)
						})
							.then(res => 
								window.location.href = "/"
							)
							.catch(err => console.log(err))
					}
					insert()
			})
		}
	}


}

userObject.init();