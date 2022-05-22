function escapeXss(html) {
    return html
        .replaceAll('&', '&amp;')
        .replaceAll('<', '&lt;')
        .replaceAll('>', '&gt;')
        .replaceAll('"', '&quot;')
        .replaceAll('\'', '&#x27;');
}

/**
 * 댓글 추가 및 새로 갱신
 */
function saveCommentAndReload() {
    const body = document.getElementById('comment-body').value;
    const nickname = document.getElementById('comment-nickname').value;
    const password = document.getElementById('comment-password').value;

    document.getElementById('comment-body').value = '';
    document.getElementById('comment-nickname').value = '';
    document.getElementById('comment-password').value = '';

    const songId = window.location.href.split('/').pop();
    fetch(`/api/comment/${songId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            'nickname': nickname,
            'password': password,
            'body': body
        }),
    }).then(response => {
        if (response.ok) {
            return response.json();
        }
    }).then(() => {
        void reload();
    }).catch(() => {
        alert('뭔가 에러난듯??');
    })
}

async function reload() {
    const songId = window.location.href.split('/').pop();
    fetch(`/api/comment/${songId}`)
        .then(response => {
            if (response.ok) {
                return response.json();
            }
        })
        .then(json => {
            let html = '';

            json.forEach(obj => {
                const created = new Date(Date.parse(obj.created));
                obj.body = escapeXss(obj.body);
                obj.nickname = escapeXss(obj.nickname);

                html += `
                    <div>
                      <strong>${obj.nickname}</strong>
                      <span class="date">${created.getFullYear()}-${String(created.getMonth() + 1).padStart(2, '0')}-${String(created.getDate()).padStart(2, '0')} ${String(created.getHours()).padStart(2, '0')}:${String(created.getMinutes()).padStart(2, '0')}:${String(created.getSeconds()).padStart(2, '0')}</span>
                      <button type="button" onclick="deleteComment(${obj.id})" style="float: right;">삭제</button>
                      <p class="comment-body">${obj.body}</p>
                    </div>
                `;
            });

            if (html === '') {
                html = `
                    <div class="align-center" style="height: 300px">
                      <p class="no-comment"></p>
                    </div>
                `
            }

            document.getElementById('commentList').innerHTML = html;
        });
}

function deleteComment(commentId) {
    const password = prompt('비밀번호를 입력해주세요.');
    fetch(`/api/comment/${commentId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            'password': password,
        }),
    }).then(response => {
        if (response.ok) {
            return response.json();
        }
        throw Error('비밀번호가 올바르지 않습니다.');
    }).then(() => {
        void reload();
    }).catch(e => {
        alert(e.message);
    })
}
