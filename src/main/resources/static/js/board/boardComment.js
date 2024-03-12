console.log("boardComment js");


document.getElementById('cmtPostBtn').addEventListener('click',()=>{
    const cmtText=document.getElementById('cmtText');
    if(cmtText.value==null||cmtText.value==''){
        alert('댓글을 입력해주세요.');
        cmtText.focus();
        return false;
    } else {
        let cmtData={
            bno:bnoVal,
            email:document.getElementById('cmtEmail').value,
            nickName:document.getElementById('cmtNickName').value,
            content:cmtText.value
        };
        console.log(cmtData);
        postCommentToServer(cmtData).then(result=>{
             if(result==`1`){
                alert(`댓글 등록 성공`);
                cmtText.value=``;
            }
            //화면에 뿌리기
            spreadCommentList(bnoVal);
        })
    }
})

async function postCommentToServer(cmtData){
    try{
        const url=`/comment/post`;
        const config={
            method:`post`,
            headers:{
                'content-type':`application/json; charset=utf-8`
            },
            body:JSON.stringify(cmtData)
        };
        const resp=await fetch(url,config);
        const result=await resp.text();
        return result;
    } catch(error){
        console.log(error);
    }
}

async function getCommentListFromServer(bno,page){
    try {
        const resp=await fetch("/comment/"+bno+"/"+page);
        const result=await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

function spreadCommentList(bno,page=1){
    getCommentListFromServer(bno,page).then(result=>{
        const ul=document.getElementById(`cmtListArea`);
        if(result.cmtList.length>0){
            if(page==1){
                ul.innerHTML=``;
            }
            for(let cvo of result.cmtList){
                let li=`<ul class="list-group list-group-numbered">`;
                li += `<li class="list-group-item d-flex justify-content-between align-items-start">`;
                li += `<div class="ms-2 me-auto"></div>`;
                li += `${cvo.content}`;
                li += `</div>`;
                li += `<span class="badge text-bg-success">수정</span><span class="badge text-bg-danger">삭제</span>`;
                li += `</li>`;
                li += `</ul>`;
                ul.innerHTML += li;
            }
            let moreBtn = document.getElementById('moreBtn');
    
            console.log(moreBtn);
            if(result.pgvo.pageNo<result.endPage){
                moreBtn.style.visibility=`visible`;
                moreBtn.dataset.page=`page+1`;
            }else{
                moreBtn.style.visibility=`hidden`;
            }
        } else {
            let li=`<ul class="list-group list-group-numbered">Comment List</ul>`;
            ul.innerHTML = li;
        }
    })
    
} 

