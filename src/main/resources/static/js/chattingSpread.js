function spreadChatList(chatBno, page=1){
    getChatListFromServer(chatBno).then(result=>{
        console.log(result);
        //댓글 뿌리기
        const ul = document.getElementById(`msgArea`);
        if(result.length>0){
            // 댓글을 다시 뿌릴 때 기존 값을 삭제 x , 1page일 경우만 삭제
            if(page==1){ //1페이지에서만 댓글 내용 지우기
				ul.innerHTML=``;
            }
            for(let chat of result){
                let li = `<li class="chat ch1">
                <div class="icon"><i class="fa-solid fa-user"></i></div>
                <span style="float: right;">${chat.senderNick}</span>
                <div class="textbox">${chat.chatContent}</div>
                </li>
                `;
                ul.innerHTML += li;
            }
        }else{
            let li = `<li class="list-group-item">대화가 없습니다</li>`;
            ul.innerHTML = li;
        }
    })
}
async function getChatListFromServer(chatBno){
    try {
        const resp = await fetch("/chat/chatRoom/"+chatBno);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

console.log('ChatRoom JavaScript Insert');
//임시로 값 넣어주기
let userEmail = "tester@naver.com";

function enterRoom(socket) {
    let enterMsg = {
        type: "ENTER",
        chatBno: chatBno,
        senderNick: userEmail,
        chatContent: ""
    };
    socket.send(JSON.stringify(enterMsg));
    console.log("enterMsg");    
    console.log(enterMsg);
}

let socket = new WebSocket("ws://localhost:8088/ws/chat");
console.log("socket");
console.log(socket);

socket.onopen = function (e) {
    console.log('서버에 연결되었습니다!');
    enterRoom(socket);
};

socket.onclose = function (e) {
    console.log('연결이 종료되었습니다');
};

socket.onerror = function (e) {
    console.log(e);
};

function sendMsg() {
    let content = document.getElementById('content').value;
    console.log(content);
    let talkMsg = {
        type: "TALK",
        chatBno: chatBno,
        senderNick: userEmail,
        chatContent: content
    };
    socket.send(JSON.stringify(talkMsg));
 	//서버로 메시지가 전송되었는지 확인용
    console.log("talkMsg :", talkMsg);
	spreadChatList(chatBno);
};


socket.onmessage = function (e) {
    console.log('WebSocket 메시지 수신:', e);
    let msgArea = document.getElementById('msgArea');
    
    try {
        let message = JSON.parse(e.data);
        console.log('파싱된 메시지:', message);

        //클라이언트에서 서버로부터 수신한 메시지 확인용
        console.log('Received message from server:', message);

        if (message.type === 'ENTER' || message.type === 'QUIT') {
            let newMsg = document.createElement('div');
            console.log(message);
            newMsg.innerText = message.chatContent; // 간소화된 메시지 표시
            msgArea.append(newMsg);
        } else if (message.type === 'TALK') {
            let newMsg = document.createElement('div');
            console.log(message);
            newMsg.innerText = message.senderNick + ': ' + message.chatContent;
            msgArea.append(newMsg);
        }
    } catch (error) {
        console.error('WebSocket 메시지 처리 중 오류 발생:', error);
    }
};

function quit() {
    let quitMsg = {
        type: "QUIT",
        chatBno: chatBno,
        senderNick: userEmail,
        chatContent: ""
    };
    socket.send(JSON.stringify(quitMsg));
    socket.close();
    location.href = "/chat/chatList";
}

document.getElementById(`sendBtn`).addEventListener(`click`,()=>{
	spreadChatList(chatBno);
	document.getElementById(`content`).value="";
});