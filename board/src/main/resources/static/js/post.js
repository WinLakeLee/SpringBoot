$(document).ready(() => {
	$('#content').summernote({
		height: 400,
		airmode: true,
		focus: true,
		toolbar: [
			['style', ['style']],
			['font', ['bold', 'underline', 'clear']],
			['color', ['color']],
			['para', ['ul', 'ol', 'paragraph']],
			['table', ['table']],
			['insert', ['link', 'picture', 'video']],
			['view', ['fullscreen', 'codeview', 'help']]
		]
	})
})

const userObject = {
	init: () => {
		const btnInsert = document.querySelector('#btn-insert')
		if (btnInsert) {
			btnInsert.addEventListener('click', (e) => {
				e.preventDefault()
				userObject.insertPost()

			})
		}
		const btnUpdate = document.querySelector('#btn-update')
		if (btnUpdate) {
			btnUpdate.addEventListener('click', (e) => {
				e.preventDefault()
				if (confirm('정말 수정하시겠습니까'))
					userObject.updatePost()
			})
		}
		const btnDelete = document.querySelector('#btn-delete')
		if (btnDelete) {
			btnDelete.addEventListener('click', (e) => {
				e.preventDefault()
				userObject.deletePost()
			})
		}

	},
	insertPost: () => {
		const data = {
			title: document.getElementById("title").value,
			content: document.getElementById("content").value
		}

		fetch("/post", {
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify(data)
		})
			.then(() =>
				window.location.href = "/"
			)
			.catch(err => console.log(err))
	},
	updatePost: () => {
		const id = document.getElementById("id").value
		const data = {
			title: document.getElementById("title").value,
			content: document.getElementById("content").value
		}

		fetch(`/post/update/${id}`, {
			method: "PUT",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify(data)
		})
			.then((res) => res.status == (200 || 201 || 202) ?
				window.location.href = "/"
				:
				console.log(res)
			)
			.catch(err => console.log(err))
	},
	deletePost: () => {
		const id = document.querySelector('.id').innerText;
		fetch(`/post/${id}`, {
			method: "DELETE",
			headers: {
				"Content-Type": "application/json"
			}
		})
			.then(res => res.status == (200 || 201 || 202) ?
				window.location.href = "/"
				: console.log(res))
			.catch(err => console.log(err))
	}

}
userObject.init();