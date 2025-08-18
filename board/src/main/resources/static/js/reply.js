const replyObject = {

	init: () => {
		const btnSaveReply = document.querySelector("#btn-save-reply")
		if (btnSaveReply) {
			btnSaveReply.addEventListener('click', (e) => {
				e.preventDefault()
				replyObject.insertReply()
			})
		}
		const replyList = document.querySelector('.replyList')
		if (replyList) {
			replyList.addEventListener('click', (e) => {
				if (e.target.tagName == "BUTTON") {
					e.preventDefault()
					replyObject.deleteReply(e.target.dataset.id)
				}
			})
		}
	},

	insertReply: () => {
		const data =
		{
			"content": document.getElementById('reply-content').value
		}
		const postid = document.querySelector('#postId').value
		fetch(`/reply/${postid}`, {
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify(data)
		}).then(res => {
			res.status == (202) ?
				window.location.href = `/post/${postid - 1}`
				: console.log(res)
		}).catch(err => console.log(err))
	},
	deleteReply: (e) => {
		const uri = e;
		fetch(`/reply/${uri}`, {
			method: "DELETE",
			headers: {
				"Content-Type": "application/json"
			}
		})
			.then(res => res.status == (200) ?
				location.reload(true)
				:
				""
			)
			.catch(err => console.log(err))
	}

}

replyObject.init()